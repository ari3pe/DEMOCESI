package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;

import dao.MaterialDAO;
import dao.PropuestaDAO;
import modelo.Material;
import modelo.MaterialPorPropuesta;
import modelo.Propuesta;

import java.awt.Color;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class AsignarPropuestaAlMaterial extends JPanel {
    DefaultListModel<String> propuestasAsocDefaultList = new DefaultListModel<String>();
    ArrayList<Material> MAP;
    JList lista;

	/**
	 * Create the panel.
	 */
	public AsignarPropuestaAlMaterial(JFrame marco) {
		setBackground(new Color(255, 128, 128));
		setLayout(null);

		lista = new JList();
		lista.setToolTipText("");
		lista.setBounds(151, 47, 554, 385);
		add(lista);
		
		MaterialDAO materialDao = new MaterialDAO();
        MAP  = materialDao.traerTodos();

        for (Material material : MAP) {
            String titulo = material.getTitulo();
            propuestasAsocDefaultList.addElement(titulo);
        }
		
		

		DefaultListModel<Material> data = new DefaultListModel<Material>();

		lista.setModel(new AbstractListModel() {
			String[] values = new String[] {  };

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
			
		});

		JButton botonGuardar = new JButton("Guardar");
		botonGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		botonGuardar.setBounds(575, 467, 89, 23);
		add(botonGuardar);
	}
	
	
	public void traerListaDePropuestas(MaterialPorPropuesta mp) {
		// ESTO ES PARA QUE AL SELECCIONAR LA CATEGORIA creando un material le salten la
		// lista de las propuestas con esa misma categoria.
		PropuestaDAO pdao = new PropuestaDAO();
		
		DefaultListModel propuestas = new DefaultListModel();
		
		JList<Propuesta> listadoPropuestas;
		
		for (Propuesta p2 : pdao.traerTodas()) {
			if (mp.coincidenLasCategorias(p2)) {
				
				propuestas.addElement(p2.getTitulo());
			}
		}
		
		
	}
}
