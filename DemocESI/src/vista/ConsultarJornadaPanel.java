package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.JornadaDAO;
import dao.MaterialDAO;
import modelo.Jornada;
import modelo.Material;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ConsultarJornadaPanel extends JPanel {
	private JTable table;
	ArrayList<Jornada> listado = new ArrayList<Jornada>();

	DefaultTableModel data = new DefaultTableModel(new Object[][] {},
			new String[] { "Titulo", "Referente Institucional", "Objetivo" });

	public ConsultarJornadaPanel(JFrame marco) {
		setBackground(new Color(255, 128, 128));
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 30, 663, 328);
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		table.setModel(data);
		traerListadoJornadas(data);

		JButton btnNewButton = new JButton("volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marco.setContentPane(new MenuJornadaPanel(marco));
				marco.validate();
			}
		});
		btnNewButton.setBounds(74, 402, 89, 23);
		add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Ver detalle");
		btnNewButton_1.setBounds(555, 402, 89, 23);
		add(btnNewButton_1);

	}

	public void traerListadoJornadas(DefaultTableModel datos) {
		datos.setRowCount(0);
		JornadaDAO jornadas = new JornadaDAO();

		listado = jornadas.traerTodas();

		for (Jornada j : listado) {
			Object[] fila = new Object[] { j.getTitulo(), j.getReferenteInstitucional(), j.getObjetivo() };
			data.addRow(fila);
		}

	}

	private Jornada seleccionarMaterial() {
		int filaSeleccionada = table.getSelectedRow();
		return listado.get(filaSeleccionada);
	}
}