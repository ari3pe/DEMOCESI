package vista;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.protocol.Message;

import dao.PropuestaDAO;
import dao.CategoriaDAO;
import modelo.Propuesta;

import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ConsultarPropuestaPanel extends JPanel {

	ArrayList<Propuesta> Listado = new ArrayList<>();
	private JTable table;

	DefaultTableModel data = new DefaultTableModel(new Object[][] {}, new String[] { "titulo", "categoria", "autor",
			"descripcion", "motivacion", "fecha", "origen", "estado", "motivo_rechazo" });

	public ConsultarPropuestaPanel(JFrame marco) {

		setBackground(new Color(255, 128, 128));

		JButton botonVolver = new JButton("Volver");
		botonVolver.setBounds(170, 422, 89, 23);
		botonVolver.setForeground(new Color(0, 0, 0));
		botonVolver.setBackground(new Color(128, 255, 255));

		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marco.setContentPane(new MenuPropuestaPanel(marco));
				marco.validate();

			}
		});
		setLayout(null);
		add(botonVolver);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 43, 691, 329);
		add(scrollPane);

		table = new JTable();

		table.setModel(data);

		scrollPane.setViewportView(table);

		TraerListado(data);

		JButton btnNewButton = new JButton("Ver detalle");
		btnNewButton.setBounds(528, 422, 89, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Propuesta p = SeleccionarPropuesta();

				marco.setContentPane(new SubirOModificarPropuestaPanel(marco, p));
				marco.validate();

			}
		});
		add(btnNewButton);

		JButton botonEliminar = new JButton("Eliminar");
		botonEliminar.setBounds(351, 422, 89, 23);
		botonEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 0 = si
				// 1 = no
				// 2 = cancelar
				int s = JOptionPane.showConfirmDialog(botonEliminar, "¿Está seguro de que desea eliminar?");
				if (s == 0) {
					PropuestaDAO q = new PropuestaDAO();
					q.eliminar(SeleccionarPropuesta().getTitulo());

				}
				TraerListado(data);

			}

		});
		add(botonEliminar);

		JComboBox fecha1 = new JComboBox();
		fecha1.setModel(new DefaultComboBoxModel(new String[] { "todo" }));
		fecha1.setBounds(143, 483, 105, 22);
		add(fecha1);

		JComboBox fecha2 = new JComboBox();
		fecha2.setBounds(317, 483, 92, 22);
		fecha2.setModel(new DefaultComboBoxModel(new String[] { "todo" }));
		add(fecha2);

		JComboBox estadof = new JComboBox();
		estadof.setModel(new DefaultComboBoxModel(new String[] { "todo", "pendiente", "aceptada", "rechazada" }));
		estadof.setBounds(431, 483, 89, 22);
		add(estadof);

		JLabel entre = new JLabel("Entre");
		entre.setBounds(258, 487, 49, 14);
		add(entre);

		JComboBox Categf = new JComboBox();
		Categf.setModel(new DefaultComboBoxModel(new String[] { "todo" }));
		Categf.setBounds(530, 483, 71, 22);
		add(Categf);

		for (String fecha : lasfechas()) {
			fecha1.addItem(fecha);
			fecha2.addItem(fecha);
		}

		for (String categoria : lascategorias()) {
			Categf.addItem(categoria);
		}

		JButton btFiltrar = new JButton("Filtrar");
		btFiltrar.setBounds(32, 483, 89, 23);
		add(btFiltrar);

		btFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PropuestaDAO q = new PropuestaDAO();
				Listado = q.traerfiltradas(fecha1.getSelectedItem().toString(), fecha2.getSelectedItem().toString(),
						estadof.getSelectedItem().toString(), Categf.getSelectedItem().toString());
				data.setRowCount(0);
				PropuestaDAO propuestas = new PropuestaDAO();

				for (Propuesta p : Listado) {
					Object[] fila = new Object[] { p.getTitulo(), p.getCategoria(), p.getAutor(), p.getDescripcion(),
							p.getMotivacion(), p.getFecha(), p.getOrigen(), p.getEstado(), p.getMotivo_rechazo() };
					data.addRow(fila);

				}

			}
		});

	}

	public void TraerListado(DefaultTableModel dataModel) {
		dataModel.setRowCount(0);
		PropuestaDAO propuestas = new PropuestaDAO();

		Listado = propuestas.traerTodas();

		for (Propuesta p : Listado) {
			Object[] fila = new Object[] { p.getTitulo(), p.getCategoria(), p.getAutor(), p.getDescripcion(),
					p.getMotivacion(), p.getFecha(), p.getOrigen(), p.getEstado(), p.getMotivo_rechazo() };
			dataModel.addRow(fila);

		}

	}

	private Propuesta SeleccionarPropuesta() {

		int filaSeleccionada = table.getSelectedRow();
		return Listado.get(filaSeleccionada);

	}

	private ArrayList<String> lasfechas() {
		PropuestaDAO propuestas = new PropuestaDAO();
		ArrayList<String> fecha = propuestas.traerfechas();

		return fecha;

	}

	private ArrayList<String> lascategorias() {
		CategoriaDAO c = new CategoriaDAO();
		ArrayList<String> ca = c.TraerLasCategorias();

		return ca;
	}
}
