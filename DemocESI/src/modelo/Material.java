package modelo;

public abstract class Material {

	private String titulo;
	private String categoria;
	private String descripcion;
	private String fuente; //bibliograf�a
	private String enlace;


	public Material(String titulo, String categoria, String descripcion, String fuente, String enlace) {
		this.titulo = titulo;
		this.categoria = categoria;
		this.descripcion = descripcion;
		this.fuente = fuente;
		this.enlace = enlace;
	}

	public Material(String categoria) {
		this.categoria = categoria;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFuente() {
		return fuente;
	}

	public void setFuente(String fuente) {
		this.fuente = fuente;
	}

	public String getEnlace() {
		return enlace;
	}

	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}

	public abstract boolean esDeTratamientoPrioritario();
	
}
