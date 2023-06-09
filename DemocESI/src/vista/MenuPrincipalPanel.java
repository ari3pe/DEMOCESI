package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.time.Duration;
import java.time.Instant;

public class MenuPrincipalPanel extends JPanel {
	private JMenuBar menuBarOpciones;

	public MenuPrincipalPanel(JFrame marco) {
		setBackground(new Color(255, 128, 128));
		setForeground(new Color(255, 255, 255));
		setLayout(null);

		JLabel lblNewLabel = new JLabel("DemocESI");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Modern No. 20", Font.PLAIN, 30));
		lblNewLabel.setBounds(329, 163, 174, 53);
		add(lblNewLabel);

		JMenuBar menuBar;
		menuBarOpciones = new JMenuBar();
		menuBarOpciones.setBounds(350, 260, 101, 22);
		add(menuBarOpciones);

		JMenu mnNewMenu = new JMenu("Opciones");
		menuBarOpciones.add(mnNewMenu);
		mnNewMenu.setForeground(new Color(0, 0, 0));
		mnNewMenu.setBackground(new Color(0, 0, 160));

		JMenuItem OpcionJornada = new JMenuItem("Jornada");
		mnNewMenu.add(OpcionJornada);

		JMenuItem OpcionMaterial = new JMenuItem("Material");
		mnNewMenu.add(OpcionMaterial);

		JMenuItem OpcionPropuesta = new JMenuItem("Propuesta");
		mnNewMenu.add(OpcionPropuesta);

		JLabel lblNewLabel_1 = new JLabel("Seleccione una opci\u00F3n:");
		lblNewLabel_1.setFont(new Font("Vani", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(319, 235, 218, 14);
		add(lblNewLabel_1);

		menuBar = new JMenuBar();

		OpcionMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				marco.setContentPane(new MenuMaterialPanel(marco));
				marco.validate();
			}
		});

		OpcionJornada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				marco.setContentPane(new MenuJornadaPanel(marco));
				marco.validate();
			}
		});

		OpcionPropuesta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				marco.setContentPane(new MenuPropuestaPanel(marco));
				marco.validate();
			}
		});

	}
}
