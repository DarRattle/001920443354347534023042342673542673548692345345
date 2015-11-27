package me.darkrattle.vaccine.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import me.darkrattle.vacation.engine.hack.HackManager;
import me.darkrattle.vaccine.VACcine;
import me.darkrattle.vaccine.hacks.Aimbot;
import me.darkrattle.vaccine.hacks.Bhop;
import me.darkrattle.vaccine.hacks.ESP;
import me.darkrattle.vaccine.hacks.Triggerbot;

public class UI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private static JTextField textFieldTriggerbot;
	private static JTextField textFieldAimbot;
	private static JTextField textFieldEsp;
	private JTextField textFieldTriggerSlider;
	private static JTextField textFieldBhop;

	public UI() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		setResizable(false);
		setTitle(VACcine.NAME + " " + VACcine.VERSION);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 298, 233);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 272, 182);
		contentPane.add(tabbedPane);

		JPanel main = new JPanel();
		tabbedPane.addTab("Main", null, main, null);
		main.setLayout(null);

		JLabel labelTriggerBot = new JLabel("Trigger-Bot:");
		labelTriggerBot.setBounds(10, 123, 58, 14);
		main.add(labelTriggerBot);

		JButton buttonTriggerBot = new JButton("Toggle");
		buttonTriggerBot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				toggleTriggerbot();
			}
		});
		buttonTriggerBot.setBounds(78, 114, 106, 33);
		main.add(buttonTriggerBot);

		textFieldTriggerbot = new JTextField();
		textFieldTriggerbot.setText("OFF");
		textFieldTriggerbot.setEditable(false);
		textFieldTriggerbot.setColumns(10);
		textFieldTriggerbot.setBounds(194, 114, 63, 32);
		main.add(textFieldTriggerbot);

		textFieldAimbot = new JTextField();
		textFieldAimbot.setText("OFF");
		textFieldAimbot.setEditable(false);
		textFieldAimbot.setColumns(10);
		textFieldAimbot.setBounds(194, 12, 63, 32);
		main.add(textFieldAimbot);

		JButton buttonAimbot = new JButton("Toggle");
		buttonAimbot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				toggleAimbot();
			}
		});
		buttonAimbot.setBounds(78, 11, 106, 33);
		main.add(buttonAimbot);

		JLabel labelAimbot = new JLabel("Aimbot:");
		labelAimbot.setBounds(10, 20, 58, 14);
		main.add(labelAimbot);

		JLabel labelEsp = new JLabel("ESP:");
		labelEsp.setBounds(10, 88, 58, 14);
		main.add(labelEsp);

		JButton buttonEsp = new JButton("Toggle");
		buttonEsp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				toggleEsp();
			}
		});
		buttonEsp.setBounds(78, 79, 106, 33);
		main.add(buttonEsp);

		textFieldEsp = new JTextField();
		textFieldEsp.setText("OFF");
		textFieldEsp.setEditable(false);
		textFieldEsp.setColumns(10);
		textFieldEsp.setBounds(194, 80, 63, 32);
		main.add(textFieldEsp);

		textFieldBhop = new JTextField();
		textFieldBhop.setText("OFF");
		textFieldBhop.setEditable(false);
		textFieldBhop.setColumns(10);
		textFieldBhop.setBounds(194, 46, 63, 32);
		main.add(textFieldBhop);

		JButton btnToggleBhop = new JButton("Toggle");
		btnToggleBhop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				toggleBhop();
			}
		});
		btnToggleBhop.setBounds(78, 46, 106, 32);
		main.add(btnToggleBhop);

		JLabel lblBhop = new JLabel("B-Hop:");
		lblBhop.setBounds(10, 55, 46, 14);
		main.add(lblBhop);

		JPanel config = new JPanel();
		tabbedPane.addTab("Config", null, config, null);
		config.setLayout(null);

		JLabel lblTriggerbotRT = new JLabel("Trigger-Bot RT (MS):");
		lblTriggerbotRT.setBounds(10, 11, 104, 14);
		config.add(lblTriggerbotRT);

		textFieldTriggerSlider = new JTextField();
		textFieldTriggerSlider.setEditable(false);
		textFieldTriggerSlider.setBounds(218, 8, 39, 20);
		config.add(textFieldTriggerSlider);
		textFieldTriggerSlider.setColumns(10);

		JSlider sliderTrigger = new JSlider();
		sliderTrigger.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				textFieldTriggerSlider.setText("" + sliderTrigger.getValue());
				Triggerbot.attackDelayMS = sliderTrigger.getValue();
			}
		});
		sliderTrigger.setValue(100);
		sliderTrigger.setBounds(124, 11, 84, 14);
		sliderTrigger.setMinimum(0);
		sliderTrigger.setMaximum(255);
		config.add(sliderTrigger);
	}

	public static void toggleAimbot() {
		HackManager.getInstance().getHack(Aimbot.class).toggle();

		if (HackManager.getInstance().getHack(Aimbot.class).isEnabled()) {
			textFieldAimbot.setText("ON");
		} else {
			textFieldAimbot.setText("OFF");
		}
	}

	public static void toggleBhop() {
		HackManager.getInstance().getHack(Bhop.class).toggle();

		if (HackManager.getInstance().getHack(Bhop.class).isEnabled()) {
			textFieldBhop.setText("ON");
		} else {
			textFieldBhop.setText("OFF");
		}
	}

	public static void toggleTriggerbot() {
		HackManager.getInstance().getHack(Triggerbot.class).toggle();

		if (HackManager.getInstance().getHack(Triggerbot.class).isEnabled()) {
			textFieldTriggerbot.setText("ON");
		} else {
			textFieldTriggerbot.setText("OFF");
		}
	}

	public static void toggleEsp() {
		HackManager.getInstance().getHack(ESP.class).toggle();

		if (HackManager.getInstance().getHack(ESP.class).isEnabled()) {
			textFieldEsp.setText("ON");
		} else {
			textFieldEsp.setText("OFF");
		}
	}

}
