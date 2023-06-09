package vista;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuMaterialPanel extends JPanel {

	public MenuMaterialPanel(JFrame marco) {
		setBackground(new Color(255, 128, 128));
		setLayout(null);

		JButton Consultar = new JButton("Consultar material");
		Consultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				marco.setContentPane(new ConsultarMaterialPanel(marco));
				marco.validate();
			}
		});
		Consultar.setBounds(316, 229, 154, 23);
		add(Consultar);

		JButton Crear = new JButton("Crear material"); //

		JButton botonVolver = new JButton("Volver");
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marco.setContentPane(new MenuPrincipalPanel(marco));
				marco.validate();
			}
		});
		botonVolver.setBounds(47, 402, 89, 23);
		add(botonVolver);

		Crear.addActionListener(new ActionListener() { // es el listener del boton crear material
			public void actionPerformed(ActionEvent e) {
				marco.setContentPane(new SubirOModificarMaterialPanel(marco));
				marco.validate();
			}
		});
		Crear.setBounds(316, 195, 153, 23);
		add(Crear);

	}
}
