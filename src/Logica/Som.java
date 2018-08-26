package Logica;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class Som {
	
	public static final byte FADE_NULO = 0;
	public static final byte FADE_LINEAR = 1;
	public static final byte FADE_QUADRATICO = 2;
	
	public static final byte ONDA_SENO = 0;
	public static final byte ONDA_QUADRADA = 1;
	public static final byte ONDA_TRIANGULAR = 2;
	public static final byte ONDA_DENTEDESERRA = 3;
	
	private static final int TAXA_AMOSTRAGEM = 44100;
	private static final double BYTE_OSCILANTE = 127.0;
	
	private static double getFade(byte fade, double indice, int maximo) {
		switch(fade) {
			case FADE_LINEAR:
				return (maximo-(float)indice) / maximo;
			case FADE_QUADRATICO:
				return (-1/Math.pow(maximo, 2))*Math.pow(indice, 2) + 1;
			case FADE_NULO:
			default:
				return 1;
		}
	}
	
	private static double getOnda(byte onda, double indice, double periodo) {
		switch (onda) {
			case ONDA_DENTEDESERRA:
				return -1 + 2 * ((indice % periodo) / periodo);
			case ONDA_QUADRADA:
				return (indice % periodo) / periodo < .5 ? 1 : -1;
			case ONDA_TRIANGULAR:
				return Math.asin(Math.sin((2 * Math.PI) * (indice / periodo)));
			case ONDA_SENO:
				return Math.sin(2.0 * Math.PI * indice / periodo);
			default:
				return 1;
		}
	}
	
	private static double getValor(double indice, double duracao, 
			double frequencia, double volume, byte fade, byte onda) {
		double periodo_ = ((double)TAXA_AMOSTRAGEM) / frequencia;
		int comprimento_ = (int) (duracao * TAXA_AMOSTRAGEM);
		double fade_ = getFade(fade, indice, comprimento_);
		double onda_ = getOnda(onda, indice, periodo_);
		return (onda_ * BYTE_OSCILANTE * fade_ * volume);
	}
	
	private static double[] getDadosDeSom(double frequencia, double duracao, 
			double volume, byte fade, byte onda) {
		double[] dados = new double[(int)(duracao * TAXA_AMOSTRAGEM)];
		
		for(int i=0;i<dados.length;i++) {
			dados[i] = getValor(i, duracao, frequencia, volume, fade, onda);
		}
		return dados;
	}
	
	public static void tocar(double frequencia, double duracao, double volume, byte fade, byte onda) {
		
		double[] dadosDeSom = getDadosDeSom(frequencia, duracao, volume, fade, onda);
		byte[] dadosDeFrenquencia = new byte[dadosDeSom.length];
		
		for(int i=0;i<dadosDeSom.length;i++) {
			dadosDeFrenquencia[i] = (byte) dadosDeSom[i];
		}
		try {
			final AudioFormat format = new AudioFormat(TAXA_AMOSTRAGEM, 8, 1, true, true);
			SourceDataLine linha = AudioSystem.getSourceDataLine(format);
			linha.open(format, TAXA_AMOSTRAGEM);
			linha.start();
			linha.write(dadosDeFrenquencia, 0, dadosDeFrenquencia.length);
			linha.drain();
			linha.close();
		}catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	public static void tocar(double freqInicial, double freqFinal, double duracao, double volume, byte fade, byte onda) {
		byte[] dadosDeFrenquencia = new byte[(int) (duracao * TAXA_AMOSTRAGEM)];
		for(int i = 0; i < dadosDeFrenquencia.length; i++) {
			dadosDeFrenquencia[i] = (byte)getValor(i, duracao, freqInicial + (freqFinal-freqInicial) * (i/(double)dadosDeFrenquencia.length), volume, fade, onda);
		}
		try {
			final AudioFormat format = new AudioFormat(TAXA_AMOSTRAGEM, 8, 1, true, true);
			SourceDataLine linha = AudioSystem.getSourceDataLine(format);
			linha.open(format, TAXA_AMOSTRAGEM);
			linha.start();
			linha.write(dadosDeFrenquencia, 0, dadosDeFrenquencia.length);
			linha.drain();
			linha.close();
		}catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	public static void tocar(double frequencia, double duracao) {
		tocar(frequencia, duracao, 1, FADE_NULO, ONDA_SENO);
	}
	
	public static void tocar(double frequencia, double duracao, byte onda) {
		tocar(frequencia, duracao, 1, FADE_NULO, onda);
	}
	
}
