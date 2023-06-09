package vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import vista.*;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import dao.PropuestaDAO;
import modelo.Propuesta;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class MenuPropuestaPanel extends JPanel {
	private JButton Agregar;

	public MenuPropuestaPanel(JFrame marco) {

		setBackground(new Color(255, 128, 128));
		setLayout(null);

		Agregar = new JButton("Agregar Propuesta");
		Agregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marco.setContentPane(new SubirOModificarPropuestaPanel(marco));
				marco.validate();
			}
		});
		Agregar.setBounds(316, 198, 154, 23);
		add(Agregar);

		JButton Consultar = new JButton("Consultar Propuestas");
		Consultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultarPropuestaPanel p = new ConsultarPropuestaPanel(marco);
				p.TraerListado(p.data);
				marco.setContentPane(new ConsultarPropuestaPanel(marco));
				marco.validate();

			}
		});
		Consultar.setBounds(316, 229, 153, 23);
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
