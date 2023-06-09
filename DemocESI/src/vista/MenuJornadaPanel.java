package vista;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuJornadaPanel extends JPanel {
	
	public MenuJornadaPanel(JFrame marco) {
		setBackground(new Color(255, 128, 128));
		setLayout(null);
		
		JButton Agregar = new JButton("Agregar Jornada");
		Agregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marco.setContentPane(new SubirOModificarJornadaPanel(marco));
				marco.validate();
			}
		});
		Agregar.setBounds(316, 190, 154, 23);
		add(Agregar);
		
		JButton Consultar = new JButton("Consultar Jornada");
		Consultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				marco.setContentPane(new ConsultarJornadaPanel(marco));
				marco.validate();				
			}
		});
		Consultar.setBounds(316, 224, 154, 26);
		add(Consultar);
		
		JButton botonVolver = new JButton("Volver");
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marco.setContentPane(new MenuPrincipalPanel(marco));
				marco.validate(); 
			}
		});
		
		botonVolver.setBounds(47, 402, 89, 23);
		add(botonVolver);

	}

}
