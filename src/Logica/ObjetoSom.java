package Logica;

public class ObjetoSom {
	private double frequencia;
	private double frequenciaFinalGradiente;
	private double duracao;
	private double volume;
	private byte fade;
	private byte onda;
	
	public ObjetoSom(double frequencia, double frequenciaFinalGradiente, double duracao, double volume, byte fade, byte onda) {
		setFrequencia(frequencia);
		setFrequenciaFinalGradiente(frequenciaFinalGradiente);
		setDuracao(duracao);
		setVolume(volume);
		setFade(fade);
		setOnda(onda);
	}
	
	public void tocar() {
		Som.tocar(this.frequencia, this.duracao, this.volume, this.fade, this.onda);
	}

	public void tocarGradiente() {
		Som.tocar(this.frequencia, this.frequenciaFinalGradiente, this.duracao, this.volume, this.fade, this.onda);
	}
	
	public double getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(double frequencia) {
		this.frequencia = frequencia;
	}

	public double getDuracao() {
		return duracao;
	}

	public void setDuracao(double duracao) {
		this.duracao = duracao;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public byte getFade() {
		return fade;
	}

	public void setFade(byte fade) {
		this.fade = fade;
	}

	public byte getOnda() {
		return onda;
	}

	public void setOnda(byte onda) {
		this.onda = onda;
	}

	public double getFrequenciaFinalGradiente() {
		return frequenciaFinalGradiente;
	}

	public void setFrequenciaFinalGradiente(double frequenciaFinalGradiente) {
		this.frequenciaFinalGradiente = frequenciaFinalGradiente;
	}
}
