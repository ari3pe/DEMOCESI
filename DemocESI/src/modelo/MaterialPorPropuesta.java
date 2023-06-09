package modelo;

import java.util.ArrayList;

import dao.PropuestaDAO;

public class MaterialPorPropuesta extends Material {

	private ArrayList<Propuesta> propuestasEnCuenta = new ArrayList<Propuesta>();


	public MaterialPorPropuesta(String titulo, String categoria, String descripcion, String fuente, String enlace,
			ArrayList<Propuesta> propuestasEnCuenta) {
		super(titulo, categoria, descripcion, fuente, enlace);
		this.propuestasEnCuenta = propuestasEnCuenta;
	} //Constructor para cargar un material por propuesta

	public MaterialPorPropuesta(String titulo, String categoria, String descripcion, String fuente, String enlace) {
		super(titulo, categoria, descripcion, fuente, enlace);
	} //Constructor para mostrar un material por propuesta

	public MaterialPorPropuesta(String categoria) {
		super(categoria);
	}


	public void setPropuestasEnCuenta(ArrayList<Propuesta> propuestasEnCuenta) {
		this.propuestasEnCuenta = propuestasEnCuenta;
	}

	@Override
	public boolean esDeTratamientoPrioritario() {
		return getPropuestasEnCuenta().size() > 3;
	}

	private ArrayList<Propuesta> getPropuestasEnCuenta() {
		// TODO Auto-generated method stub
		return this.propuestasEnCuenta;
	}

	public boolean coincidenLasCategorias(Propuesta p) {
		
		
			if (p.getCategoria().equals(getCategoria())) {
				return true;
		}
		return false;
	}

}
