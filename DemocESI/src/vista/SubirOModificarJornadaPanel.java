package vista;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import dao.JornadaDAO;
import modelo.Jornada;
import modelo.Material;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SubirOModificarJornadaPanel extends JPanel {
	private JTextField CuadroTitulo;
	private JTextField CuadroReferenteInst;
	private JTextField CuadroObjetivo;
	

	public SubirOModificarJornadaPanel(JFrame marco) {
		setBackground(new Color(255, 128, 128));
		setLayout(null);
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marco.setContentPane(new MenuJornadaPanel(marco));
				marco.validate();
			}
		});
		btnNewButton.setBounds(161, 314, 89, 23);
		add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("T\u00EDtulo: ");
		lblNewLabel.setBounds(172, 81, 46, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Referente Institucional:");
		lblNewLabel_1.setBounds(90, 118, 139, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Objetivo:");
		lblNewLabel_2.setBounds(161, 156, 46, 14);
		add(lblNewLabel_2);
		
		CuadroTitulo = new JTextField();
		CuadroTitulo.setBounds(228, 78, 181, 17);
		add(CuadroTitulo);
		CuadroTitulo.setColumns(10);
		
		CuadroReferenteInst = new JTextField();
		CuadroReferenteInst.setColumns(10);
		CuadroReferenteInst.setBounds(228, 115, 181, 17);
		add(CuadroReferenteInst);
		
		CuadroObjetivo = new JTextField();
		CuadroObjetivo.setColumns(10);
		CuadroObjetivo.setBounds(228, 153, 280, 95);
		add(CuadroObjetivo);
		
		JButton BotonGuardar = new JButton("Guardar");
		BotonGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Material> materiales = null; //faltaArreglarEsto
				Jornada j = new Jornada(materiales, CuadroTitulo.getText() , CuadroReferenteInst.getText(), CuadroObjetivo.getText());
				 
				JornadaDAO jdao = new JornadaDAO();
				jdao.Agregar(j);
				
				marco.setContentPane(new MenuJornadaPanel(marco));
				marco.validate();
			}
		});
		BotonGuardar.setBounds(504, 314, 89, 23);
		add(BotonGuardar);

	}
}
