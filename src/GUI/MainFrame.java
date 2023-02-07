package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Logica.ObjetoSom;
import Logica.Som;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JLabel lblFade;
	private ButtonGroup grupoFade;
	private JRadioButton rdbtnFadeNulo;
	private JRadioButton rdbtnFadeLinear;
	private JRadioButton rdbtnFadeQuadratico;
	private JLabel lblOnda;
	private ButtonGroup grupoOnda;
	private JRadioButton rdbtnOndaSeno;
	private JRadioButton rdbtnOndaQuadrada;
	private JRadioButton rdbtnOndaTriangular;
	private JRadioButton rdbtnOndaDenteDeSerra;

	private ObjetoSom som;
	private JButton btnTocar;
	private JLabel lblVolume;
	private JSlider sliderVolume;
	private JLabel labelValorVolume;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 700, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		som = new ObjetoSom(400, 600, 1, 0.5, Som.FADE_NULO, Som.ONDA_SENO);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		lblFade = new JLabel("Escolha o Fade:");
		lblFade.setBounds(130, 25, 89, 19);
		panel.add(lblFade);

		rdbtnFadeNulo = new JRadioButton("Nulo", true);
		rdbtnFadeNulo.setFocusable(false);
		rdbtnFadeNulo.setBounds(130, 44, 89, 25);
		rdbtnFadeNulo.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				som.setFade(Som.FADE_NULO);
			}
		});
		panel.add(rdbtnFadeNulo);

		rdbtnFadeLinear = new JRadioButton("Linear", false);
		rdbtnFadeLinear.setFocusable(false);
		rdbtnFadeLinear.setBounds(130, 69, 89, 25);
		rdbtnFadeLinear.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				som.setFade(Som.FADE_LINEAR);
			}
		});
		panel.add(rdbtnFadeLinear);

		rdbtnFadeQuadratico = new JRadioButton("Quadratico", false);
		rdbtnFadeQuadratico.setFocusable(false);
		rdbtnFadeQuadratico.setBounds(130, 94, 89, 25);
		rdbtnFadeQuadratico.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				som.setFade(Som.FADE_QUADRATICO);
			}
		});
		panel.add(rdbtnFadeQuadratico);

		grupoFade = new ButtonGroup();
		grupoFade.add(rdbtnFadeNulo);
		grupoFade.add(rdbtnFadeLinear);
		grupoFade.add(rdbtnFadeQuadratico);

		lblOnda = new JLabel("Escolha a Onda:");
		lblOnda.setBounds(10, 25, 110, 19);
		panel.add(lblOnda);

		rdbtnOndaSeno = new JRadioButton("Seno", true);
		rdbtnOndaSeno.setBounds(10, 44, 110, 25);
		rdbtnOndaSeno.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				som.setOnda(Som.ONDA_SENO);
			}
		});
		panel.add(rdbtnOndaSeno);

		rdbtnOndaQuadrada = new JRadioButton("Quadratica", false);
		rdbtnOndaQuadrada.setBounds(10, 69, 110, 25);
		rdbtnOndaQuadrada.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				som.setOnda(Som.ONDA_QUADRADA);
			}
		});
		panel.add(rdbtnOndaQuadrada);

		rdbtnOndaTriangular = new JRadioButton("Triangular", false);
		rdbtnOndaTriangular.setBounds(10, 94, 110, 25);
		rdbtnOndaTriangular.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				som.setOnda(Som.ONDA_TRIANGULAR);
			}
		});
		panel.add(rdbtnOndaTriangular);

		rdbtnOndaDenteDeSerra = new JRadioButton("Dente de Serra", false);
		rdbtnOndaDenteDeSerra.setBounds(10, 119, 110, 25);
		rdbtnOndaDenteDeSerra.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				som.setOnda(Som.ONDA_DENTEDESERRA);
			}
		});
		panel.add(rdbtnOndaDenteDeSerra);

		grupoOnda = new ButtonGroup();
		grupoOnda.add(rdbtnOndaSeno);
		grupoOnda.add(rdbtnOndaQuadrada);
		grupoOnda.add(rdbtnOndaTriangular);
		grupoOnda.add(rdbtnOndaDenteDeSerra);

		btnTocar = new JButton("Tocar");
		btnTocar.setBounds(210, 154, 89, 23);
		btnTocar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						som.tocar();
					}
				}).start();
			}
		});
		panel.add(btnTocar);

		JLabel lblFrequencia = new JLabel("Frequ\u00EAncia inicial (hz):");
		lblFrequencia.setHorizontalAlignment(SwingConstants.CENTER);
		lblFrequencia.setBounds(238, 25, 131, 19);
		panel.add(lblFrequencia);

		JSlider sliderFrequencia = new JSlider();
		sliderFrequencia.setBounds(238, 50, 200, 26);
		sliderFrequencia.setMaximum(20000);
		sliderFrequencia.setMinimum(20);
		sliderFrequencia.setValue(400);

		SpinnerNumberModel snmF = new SpinnerNumberModel();
		snmF.setMaximum(20000);
		snmF.setMinimum(20);
		snmF.setValue(400);
		JSpinner spinnerFrequencia = new JSpinner(snmF);
		spinnerFrequencia.setEditor(new JSpinner.NumberEditor(spinnerFrequencia, "#"));
		spinnerFrequencia.setBounds(372, 24, 70, 19);
		spinnerFrequencia.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				som.setFrequencia((double) ((Integer) spinnerFrequencia.getValue()));
				sliderFrequencia.setValue((int) som.getFrequencia());
			}
		});
		panel.add(spinnerFrequencia);

		sliderFrequencia.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				som.setFrequencia((double) ((Integer) sliderFrequencia.getValue()));
				spinnerFrequencia.setValue((int) som.getFrequencia());
			}
		});
		panel.add(sliderFrequencia);

		JLabel lblDuracao = new JLabel("Dura\u00E7\u00E3o (milisegundos):");
		lblDuracao.setHorizontalAlignment(SwingConstants.CENTER);
		lblDuracao.setBounds(238, 94, 125, 19);
		panel.add(lblDuracao);

		SpinnerNumberModel snmD = new SpinnerNumberModel();
		snmD.setValue(1000);
		snmD.setMinimum(0);
		snmD.setStepSize(100);
		JSpinner spinnerDuracao = new JSpinner(snmD);
		spinnerDuracao.setEditor(new JSpinner.NumberEditor(spinnerDuracao, "#"));
		spinnerDuracao.setBounds(368, 94, 70, 19);
		spinnerDuracao.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				som.setDuracao((double) ((Integer) spinnerDuracao.getValue()) / 1000);
			}
		});
		panel.add(spinnerDuracao);

		lblVolume = new JLabel("Volume:");
		lblVolume.setHorizontalAlignment(SwingConstants.CENTER);
		lblVolume.setBounds(468, 87, 104, 14);
		panel.add(lblVolume);

		labelValorVolume = new JLabel("50%");
		labelValorVolume.setBounds(582, 87, 46, 14);
		panel.add(labelValorVolume);

		sliderVolume = new JSlider();
		sliderVolume.setBounds(468, 107, 195, 26);
		sliderVolume.setMaximum(100);
		sliderVolume.setMinimum(0);
		sliderVolume.setValue(50);
		sliderVolume.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				som.setVolume(((double) sliderVolume.getValue()) / ((double) 100));
				labelValorVolume.setText("" + sliderVolume.getValue() + "%");
			}
		});
		panel.add(sliderVolume);

		JButton btnTocarGradiente = new JButton("Tocar Gradiente");
		btnTocarGradiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						som.tocarGradiente();
					}
				}).start();
			}
		});
		btnTocarGradiente.setBounds(325, 154, 125, 23);
		panel.add(btnTocarGradiente);

		JLabel lblFrequenciaFinal = new JLabel("Frequ\u00EAncia final (hz):");
		lblFrequenciaFinal.setHorizontalAlignment(SwingConstants.CENTER);
		lblFrequenciaFinal.setBounds(468, 28, 120, 14);
		panel.add(lblFrequenciaFinal);

		JSlider sliderFrequenciaFinal = new JSlider();

		SpinnerNumberModel snmFF = new SpinnerNumberModel();
		snmFF.setMaximum(20000);
		snmFF.setMinimum(20);
		snmFF.setValue(400);
		JSpinner spinnerFrequenciaFinal = new JSpinner(snmFF);
		spinnerFrequenciaFinal.setEditor(new JSpinner.NumberEditor(spinnerFrequenciaFinal, "#"));
		spinnerFrequenciaFinal.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				som.setFrequenciaFinalGradiente((double) ((Integer) spinnerFrequenciaFinal.getValue()));
				sliderFrequenciaFinal.setValue((int) som.getFrequenciaFinalGradiente());
			}
		});
		spinnerFrequenciaFinal.setBounds(598, 24, 65, 20);
		panel.add(spinnerFrequenciaFinal);

		sliderFrequenciaFinal.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				som.setFrequenciaFinalGradiente((double) ((Integer) sliderFrequenciaFinal.getValue()));
				spinnerFrequenciaFinal.setValue((int) som.getFrequenciaFinalGradiente());
			}
		});
		sliderFrequenciaFinal.setMaximum(20000);
		sliderFrequenciaFinal.setMinimum(20);
		sliderFrequenciaFinal.setValue(600);
		sliderFrequenciaFinal.setBounds(467, 50, 196, 26);
		panel.add(sliderFrequenciaFinal);

	}
}
