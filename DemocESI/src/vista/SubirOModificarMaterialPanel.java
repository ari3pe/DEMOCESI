package vista;

import javax.swing.JFrame;

import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import dao.MaterialDAO;
import dao.MaterialPorPropuestaDAO;
import dao.PropuestaDAO;
import dao.CategoriaDAO;
import modelo.MaterialInstitucional;
import modelo.MaterialPorPropuesta;
import modelo.Material;
import modelo.Propuesta;


import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class SubirOModificarMaterialPanel extends JPanel {
	private JTextField cuadroTitulo;
	private JTextField cuadroDescripcion;
	private JTextField cuadroFuente;
	private JTextField cuadroEnlace;
	private CategoriaDAO cat = new CategoriaDAO();
	Material m;
	private JTextField cuadroProcedencia;
	private JLabel lblProcedencia;
	private JList listPropuestas;

	public SubirOModificarMaterialPanel(JFrame marco) {

		setBackground(new Color(255, 128, 128));
		setLayout(null);

		JButton botonVolver = new JButton("Volver");
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marco.setContentPane(new MenuMaterialPanel(marco));
				marco.validate();
			}
		});
		botonVolver.setBounds(55, 400, 89, 23);
		add(botonVolver);

		JLabel lblTitulo = new JLabel("T\u00ECtulo:");
		lblTitulo.setBounds(161, 44, 76, 14);
		lblTitulo.setVisible(false);
		add(lblTitulo);

		JLabel lblDescripcion = new JLabel("Descripci\u00F3n:");
		lblDescripcion.setBounds(132, 89, 101, 14);
		lblDescripcion.setVisible(false);
		add(lblDescripcion);

		JLabel lblFuente = new JLabel("Fuente/s:");
		lblFuente.setBounds(150, 204, 76, 14);
		lblFuente.setVisible(false);
		add(lblFuente);

		JLabel lblEnlace = new JLabel("Enlace:");
		lblEnlace.setBounds(161, 252, 46, 14);
		lblEnlace.setVisible(false);
		add(lblEnlace);

		JLabel lblCategoria = new JLabel("Categor\u00EDa:");
		lblCategoria.setBounds(150, 315, 89, 14);
		lblCategoria.setVisible(false);
		add(lblCategoria);

		JComboBox comboBoxCategoria = new JComboBox();
		comboBoxCategoria.setBounds(216, 321, 426, 23);
		comboBoxCategoria.setVisible(false);
		add(comboBoxCategoria);
		for (String categoria : cat.TraerLasCategorias()) {
			comboBoxCategoria.addItem(categoria);
		}

		cuadroTitulo = new JTextField();
		cuadroTitulo.setBounds(216, 37, 426, 29);
		add(cuadroTitulo);
		cuadroTitulo.setColumns(10);
		cuadroTitulo.setVisible(false);

		cuadroDescripcion = new JTextField();
		cuadroDescripcion.setColumns(10);
		cuadroDescripcion.setBounds(216, 80, 447, 106);
		add(cuadroDescripcion);
		cuadroDescripcion.setVisible(false);

		cuadroFuente = new JTextField();
		cuadroFuente.setColumns(10);
		cuadroFuente.setBounds(216, 203, 447, 23);
		cuadroFuente.setVisible(false);
		add(cuadroFuente);

		cuadroEnlace = new JTextField();
		cuadroEnlace.setColumns(10);
		cuadroEnlace.setBounds(217, 245, 432, 29);
		cuadroEnlace.setVisible(false);
		add(cuadroEnlace);

		cuadroProcedencia = new JTextField();
		cuadroProcedencia.setBounds(216, 285, 447, 23);
		add(cuadroProcedencia);
		cuadroProcedencia.setVisible(false);
		cuadroProcedencia.setColumns(10);

		lblProcedencia = new JLabel("Procedencia:");
		lblProcedencia.setBounds(132, 285, 94, 19);
		lblProcedencia.setVisible(false);
		add(lblProcedencia);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(208, 373, 342, 66);
		add(scrollPane);

		listPropuestas = new JList<String>();
		DefaultListModel<String> listaPropuestaModel = new DefaultListModel<String>();
		listPropuestas.setModel(listaPropuestaModel);
		scrollPane.setViewportView(listPropuestas);

		JButton botonGuardarInstitucional = new JButton("Guardar");
		botonGuardarInstitucional.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MaterialInstitucional mp = new MaterialInstitucional(cuadroTitulo.getText(),
						comboBoxCategoria.getSelectedItem().toString(), cuadroDescripcion.getText(),
						cuadroFuente.getText(), cuadroEnlace.getText(), cuadroProcedencia.getText(), true);
				MaterialDAO matDAO = new MaterialDAO();
				matDAO.agregarMaterialInstitucional(comboBoxCategoria.getSelectedItem().toString(), mp);
				marco.setContentPane(new MenuMaterialPanel(marco));
				marco.validate();

			}
		});
		botonGuardarInstitucional.setBounds(617, 400, 89, 23);
		botonGuardarInstitucional.setVisible(false);
		add(botonGuardarInstitucional);

		JButton botonAPartirDePropuestas = new JButton("A partir de propuestas"); //
		botonAPartirDePropuestas.setBackground(new Color(0, 255, 255));

		JButton botonInstitucional = new JButton("Institucional");
		botonInstitucional.setBackground(new Color(0, 255, 255));
		botonInstitucional.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botonInstitucional.setVisible(false);
				botonAPartirDePropuestas.setVisible(false);
				lblTitulo.setVisible(true);
				cuadroTitulo.setVisible(true);
				lblDescripcion.setVisible(true);
				cuadroDescripcion.setVisible(true);
				lblCategoria.setVisible(true);
				comboBoxCategoria.setVisible(true);
				lblEnlace.setVisible(true);
				cuadroEnlace.setVisible(true);
				lblFuente.setVisible(true);
				cuadroFuente.setVisible(true);
				lblProcedencia.setVisible(true);
				cuadroProcedencia.setVisible(true);
				botonGuardarInstitucional.setVisible(true);

			}
		});
		botonInstitucional.setBounds(294, 197, 204, 29);
		add(botonInstitucional);

		JButton botonGuardarPorPropuesta = new JButton("Guardar");
		botonGuardarPorPropuesta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MaterialPorPropuesta mpp = new MaterialPorPropuesta(cuadroTitulo.getText(),
						comboBoxCategoria.getSelectedItem().toString(), cuadroDescripcion.getText(),
						cuadroFuente.getText(), cuadroEnlace.getText(), null); // falta Agregar array del jList por eso
																				// está en null
			}
		});
		botonGuardarPorPropuesta.setBounds(617, 400, 89, 23);
		add(botonGuardarPorPropuesta);

		botonAPartirDePropuestas.addActionListener(new ActionListener() { //
			public void actionPerformed(ActionEvent e) {

				botonInstitucional.setVisible(false);
				botonAPartirDePropuestas.setVisible(false);
				lblTitulo.setVisible(true);
				cuadroTitulo.setVisible(true);
				lblDescripcion.setVisible(true);
				cuadroDescripcion.setVisible(true);
				lblCategoria.setVisible(true);
				comboBoxCategoria.setVisible(true);
				lblEnlace.setVisible(true);
				cuadroEnlace.setVisible(true);
				lblFuente.setVisible(true);
				cuadroFuente.setVisible(true);
				lblProcedencia.setVisible(false);
				cuadroProcedencia.setVisible(false);
				botonGuardarInstitucional.setVisible(false);
				botonGuardarPorPropuesta.setVisible(true);

			}
		});
		botonAPartirDePropuestas.setBounds(294, 237, 204, 23);
		add(botonAPartirDePropuestas);

		comboBoxCategoria.addActionListener(event -> {
			ArrayList<Propuesta> porps = traerListaDePropuestasDeLaMismaCategoria(comboBoxCategoria.getSelectedItem().toString());
			listaPropuestaModel.clear();
			for (Propuesta p : porps) {
				
				listaPropuestaModel.addElement(p.getTitulo());
			}
		});

	
		
	}

	public ArrayList<Propuesta> traerListaDePropuestasDeLaMismaCategoria(String categoria) {

		ArrayList<Propuesta> propuestas = new ArrayList<Propuesta>();
		MaterialPorPropuesta mp = new MaterialPorPropuesta(categoria);

		PropuestaDAO pDao = new PropuestaDAO();
		for (Propuesta p : pDao.traerTodas()) {

			if (mp.coincidenLasCategorias(p)) {
				
				propuestas.add(p);
			}
		}
		return propuestas;

	}

	public boolean esEdicion() {
		return this.m != null;
	}
}
