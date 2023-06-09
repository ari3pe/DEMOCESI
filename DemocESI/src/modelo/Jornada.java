package modelo;

import java.util.ArrayList;

public class Jornada {

	private ArrayList<Material> materiales = new ArrayList<Material>();
	private String titulo;
	private String referenteInstitucional;
	private String objetivo;

	public Jornada(ArrayList<Material> materiales, String titulo, String referenteInstitucional, String objetivo) {
		this.materiales = materiales;
		this.titulo = titulo;
		this.referenteInstitucional = referenteInstitucional;
		this.objetivo = objetivo;
	}

	public ArrayList<Material> getMateriales() {
		return materiales;
	}

	public void setMateriales(ArrayList<Material> materiales) {
		this.materiales = materiales;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getReferenteInstitucional() {
		return referenteInstitucional;
	}

	public void setReferenteInstitucional(String referenteInstitucional) {
		this.referenteInstitucional = referenteInstitucional;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public boolean esPrioritaria() {
		ArrayList<Material> prioritarias = new ArrayList<Material>();
		for (Material a : materiales) {
			if (a.esDeTratamientoPrioritario()) {
				prioritarias.add(a);
			}
		}
		return prioritarias.size() > materiales.size() / 2;
	}
}
