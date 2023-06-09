package vista;

import java.time.*;
import vista.*;
import java.util.ArrayList;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import dao.PropuestaDAO;
import dao.CategoriaDAO;
import modelo.Propuesta;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.JList;

public class SubirOModificarPropuestaPanel extends JPanel {
	private JTextField CuadroTitulo;
	private JTextField CuadroDescripcion;
	private JTextField CuadroMotivacion;
	private JTextField CuadroAutor;
	private JComboBox CuadroCategoria;
	private JComboBox CuadroOrigen;
	private JButton botonCancelar;
	private JButton botonCrear;
	private JLabel lblTitulo;
	private JLabel lblDescripcin;
	private JLabel lblMotivacion;
	private JLabel lblAutoropcional;
	private JLabel lblOrigen;
	private JLabel lblCategora;
	private JLabel lblEstado;
	private JLabel lblFecha;
	private JLabel lblNewLabel_4;
	JLabel lblMotivoRechazo;
	private Propuesta propuesta;
	private JTextField CuadroEstado;
	private JTextField CuadroFecha;
	private JButton RechazarBoton;
	private JButton AceptarBoton;
	private JTextField CuadroMotivo_Rechazo;

	/**
	 * @wbp.parser.constructor
	 */
	public SubirOModificarPropuestaPanel(JFrame marco) {
		setBackground(new Color(255, 128, 128));
		setLayout(null);

		CuadroTitulo = new JTextField();
		CuadroTitulo.setBounds(124, 44, 144, 20);
		add(CuadroTitulo);
		CuadroTitulo.setColumns(10);

		CuadroDescripcion = new JTextField();
		CuadroDescripcion.setColumns(10);
		CuadroDescripcion.setBounds(124, 75, 284, 84);
		add(CuadroDescripcion);

		CuadroMotivacion = new JTextField();
		CuadroMotivacion.setColumns(10);
		CuadroMotivacion.setBounds(120, 184, 284, 38);
		add(CuadroMotivacion);

		CuadroAutor = new JTextField();
		CuadroAutor.setColumns(10);
		CuadroAutor.setBounds(141, 250, 144, 20);
		add(CuadroAutor);

		CuadroOrigen = new JComboBox();
		CuadroOrigen.setModel(new DefaultComboBoxModel(new String[] { "Docente", "Estudiante" }));
		CuadroOrigen.setBounds(132, 299, 153, 22);
		add(CuadroOrigen);

		CuadroCategoria = new JComboBox();
		CuadroCategoria.setModel(new DefaultComboBoxModel(new String[] {}));
		CuadroCategoria.setBounds(132, 330, 153, 20);
		add(CuadroCategoria);
		for (String categoria : lascategorias()) {
			CuadroCategoria.addItem(categoria);
		}

		botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marco.setContentPane(new MenuPropuestaPanel(marco));
				marco.validate();
			}
		});
		botonCancelar.setBounds(166, 423, 89, 23);
		add(botonCancelar);

		botonCrear = new JButton("Guardar");
		botonCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocalDate fecha = LocalDate.now();

				PropuestaDAO pd = new PropuestaDAO();

				if (esEdicion()) {

					Propuesta p = new Propuesta(CuadroTitulo.getText(), CuadroCategoria.getSelectedItem().toString(),
							CuadroAutor.getText(), CuadroDescripcion.getText(), CuadroMotivacion.getText(), fecha,
							CuadroOrigen.getSelectedItem().toString(), CuadroEstado.getText(),
							CuadroMotivo_Rechazo.getText());
					
					pd.modificar(propuesta.getTitulo(), p, p.getCategoria().toString());

				} else {
					Propuesta p = new Propuesta(CuadroTitulo.getText(), CuadroCategoria.getSelectedItem().toString(),
							CuadroAutor.getText(), CuadroDescripcion.getText(), CuadroMotivacion.getText(), fecha,
							CuadroOrigen.getSelectedItem().toString(), "Pendiente", null);
					
					pd.agregar(p, p.getCategoria().toString());
					
					if (CuadroTitulo.getText().isEmpty() || CuadroDescripcion.getText().isEmpty() || CuadroMotivacion.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null,"Los campos obligatorios no pueden quedar vacios.");
					}
					else {
					pd.agregar(p, p.getCategoria());
				}}
				marco.setContentPane(new MenuPropuestaPanel(marco));
				marco.validate();

			}
		});
		botonCrear.setBounds(496, 423, 89, 23);
		add(botonCrear);

		lblTitulo = new JLabel("T\u00EDtulo:");
		lblTitulo.setBounds(72, 47, 46, 14);
		add(lblTitulo);

		lblDescripcin = new JLabel("Descripci\u00F3n:");
		lblDescripcin.setBounds(47, 80, 81, 14);
		add(lblDescripcin);

		lblMotivacion = new JLabel("Motivaci\u00F3n:");
		lblMotivacion.setBounds(47, 184, 101, 14);
		add(lblMotivacion);

		lblAutoropcional = new JLabel("Autor (opcional):");
		lblAutoropcional.setBounds(39, 250, 109, 20);
		add(lblAutoropcional);

		lblOrigen = new JLabel("Origen:");
		lblOrigen.setBounds(72, 303, 46, 14);
		add(lblOrigen);

		lblCategora = new JLabel("Categor\u00EDa:");
		lblCategora.setBounds(61, 332, 58, 17);
		add(lblCategora);

		lblNewLabel_4 = new JLabel("*");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(278, 44, 23, 17);
		add(lblNewLabel_4);

		lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(460, 80, 46, 14);
		lblEstado.setVisible(false);
		add(lblEstado);

		CuadroEstado = new JTextField();
		CuadroEstado.setColumns(10);
		CuadroEstado.setBounds(525, 75, 144, 20);
		CuadroEstado.setVisible(false);
		add(CuadroEstado);

		CuadroFecha = new JTextField();
		CuadroFecha.setColumns(10);
		CuadroFecha.setBounds(525, 44, 144, 20);
		CuadroFecha.setVisible(false);
		add(CuadroFecha);

		lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(460, 47, 46, 14);
		lblFecha.setVisible(false);
		add(lblFecha);

		JLabel lblNewLabel_4_1 = new JLabel("*");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_4_1.setBounds(418, 80, 23, 17);
		add(lblNewLabel_4_1);

		JLabel lblNewLabel_4_2 = new JLabel("*");
		lblNewLabel_4_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_4_2.setBounds(414, 184, 23, 17);
		add(lblNewLabel_4_2);

		JLabel lblNewLabel_4_3 = new JLabel("*");
		lblNewLabel_4_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_4_3.setBounds(294, 303, 23, 17);
		add(lblNewLabel_4_3);

		JLabel lblNewLabel_4_4 = new JLabel("*");
		lblNewLabel_4_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_4_4.setBounds(295, 330, 23, 17);
		add(lblNewLabel_4_4);
		JLabel lblMotivoRechazo = new JLabel("Motivo Rechazo: ");
		lblMotivoRechazo.setBounds(382, 252, 144, 18);
		lblMotivoRechazo.setVisible(false);
		add(lblMotivoRechazo);

		AceptarBoton = new JButton("Aceptar");
		AceptarBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CuadroEstado.setText("Aprobada");

				LocalDate fecha = LocalDate.now();

				PropuestaDAO pd = new PropuestaDAO();
				Propuesta p = new Propuesta(CuadroTitulo.getText(), CuadroCategoria.getSelectedItem().toString(),
						CuadroAutor.getText(), CuadroDescripcion.getText(), CuadroMotivacion.getText(), fecha,
						CuadroOrigen.getSelectedItem().toString(), CuadroEstado.getText(),
						CuadroMotivo_Rechazo.getText());

				pd.modificar(propuesta.getTitulo(), p, CuadroCategoria.getSelectedItem().toString());

				CuadroMotivo_Rechazo.setVisible(false);
				lblMotivoRechazo.setVisible(false);
				CuadroMotivo_Rechazo.setText("");
				//marco.setContentPane(new AsignarPropuestaAlMaterial(marco));
				//marco.validate();
			}
		});
		AceptarBoton.setBounds(496, 158, 89, 23);
		AceptarBoton.setVisible(false);
		add(AceptarBoton);

		RechazarBoton = new JButton("Rechazar");
		RechazarBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CuadroEstado.setText("Rechazada");
				lblMotivoRechazo.setVisible(true);
				CuadroMotivo_Rechazo.setVisible(true);
			}
		});
		RechazarBoton.setBounds(496, 192, 89, 23);
		RechazarBoton.setVisible(false);
		add(RechazarBoton);

		CuadroMotivo_Rechazo = new JTextField();
		CuadroMotivo_Rechazo.setBounds(474, 250, 268, 128);
		add(CuadroMotivo_Rechazo);
		CuadroMotivo_Rechazo.setVisible(false);
		CuadroMotivo_Rechazo.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(306, 426, 134, 104);
		add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);

	}

	public SubirOModificarPropuestaPanel(JFrame marco, Propuesta p) {

		this(marco);
		if (p.getEstado().equalsIgnoreCase("Aceptada") || p.getEstado().equalsIgnoreCase("pendiente")) {
			CuadroTitulo.setText(p.getTitulo());
			CuadroDescripcion.setText(p.getDescripcion());
			CuadroAutor.setText(p.getAutor());
			CuadroOrigen.setSelectedItem(p.getOrigen());
			CuadroMotivacion.setText(p.getMotivacion());
			CuadroCategoria.setSelectedItem(p.getCategoria());
			CuadroEstado.setText(p.getEstado());
			CuadroFecha.setText(p.getFecha().toString());
			this.propuesta = p;

			lblFecha.setVisible(true);
			lblEstado.setVisible(true);
			CuadroEstado.setVisible(true);
			CuadroFecha.setVisible(true);
			CuadroEstado.setEditable(false);
			CuadroFecha.setEditable(false);
			AceptarBoton.setVisible(true);
			RechazarBoton.setVisible(true);
		} else {

			CuadroTitulo.setText(p.getTitulo());
			CuadroDescripcion.setText(p.getDescripcion());
			CuadroAutor.setText(p.getAutor());
			CuadroOrigen.setSelectedItem(p.getOrigen());
			CuadroMotivacion.setText(p.getMotivacion());
			CuadroCategoria.setSelectedItem(p.getCategoria());
			CuadroEstado.setText(p.getEstado());
			CuadroFecha.setText(p.getFecha().toString());
			CuadroMotivo_Rechazo.setText(p.getMotivo_rechazo());
		}

		this.propuesta = p;

		lblFecha.setVisible(true);
		lblEstado.setVisible(true);
		CuadroEstado.setVisible(true);
		CuadroFecha.setVisible(true);
		CuadroEstado.setEditable(false);
		CuadroFecha.setEditable(false);
		AceptarBoton.setVisible(true);
		RechazarBoton.setVisible(true);
	}

	public boolean esEdicion() {
		return this.propuesta != null;
	}

	private ArrayList<String> lascategorias() {
		CategoriaDAO c = new CategoriaDAO();
		ArrayList<String> ca = c.TraerLasCategorias();

		return ca;
	}
}

/*
 * Punto de interrupcion - Debug app ( el bichito de a la izq del play) despues
 * pones switch
 */
