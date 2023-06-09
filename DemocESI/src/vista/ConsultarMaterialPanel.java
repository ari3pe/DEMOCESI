package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.MaterialDAO;
import modelo.Material;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ConsultarMaterialPanel extends JPanel {

	ArrayList<Material> Listado = new ArrayList<>();
	DefaultTableModel data = new DefaultTableModel(new Object[][] {},
			new String[] { "titulo", "categoria", "descripcion", "fuente", "enlace", "procedencia", "prioritario" });
	private JTable table;

	public ConsultarMaterialPanel(JFrame marco) {
		
		
		setBackground(new Color(255, 128, 128));
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 649, 398);
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		table.setModel(data);
		traerListado(data);
		JButton botonVolver = new JButton("volver");
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marco.setContentPane(new MenuMaterialPanel(marco));
				marco.validate();
			}
		});
		botonVolver.setBounds(108, 460, 89, 23);
		add(botonVolver);

		JButton botonVerDetalle = new JButton("Ver detalle");
		botonVerDetalle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		botonVerDetalle.setBounds(429, 460, 89, 23);
		add(botonVerDetalle);
	}

	public void traerListado(DefaultTableModel data) {
		data.setRowCount(0);
		MaterialDAO materiales = new MaterialDAO();

		Listado = materiales.traerTodos();

		for (Material m : Listado) {
			Object[] fila = new Object[] { m.getTitulo(), m.getCategoria(), m.getDescripcion(),m.getFuente(),
					m.getEnlace() };
			data.addRow(fila);
		}

	}

	private Material seleccionarMaterial() {
		int filaSeleccionada = table.getSelectedRow();
		return Listado.get(filaSeleccionada);
	}
}
