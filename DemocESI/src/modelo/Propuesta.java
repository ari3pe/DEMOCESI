package modelo;

import java.time.LocalDate;
import java.util.Date;

public class Propuesta {

	private String titulo;
	private String categoria;
	private String autor;
	private String descripcion;
	private String motivacion;
	private LocalDate fecha;
	private String origen; // donde se origin� la propuesta ej: en el ministerio de educaci�n
	private String estado;
	private String motivo_rechazo;

	public Propuesta(String titulo, String categoria, String autor, String descripcion, String motivacion,
			LocalDate fecha, String origen, String estado, String motivo_rechazo) {
		this.titulo = titulo;
		this.categoria = categoria;
		this.autor = autor;
		this.descripcion = descripcion;
		this.motivacion = motivacion;
		this.fecha = fecha;
		this.origen = origen;
		this.estado = estado;
		this.motivo_rechazo = motivo_rechazo;
	}

	public Propuesta(String titulo, String categoria, String autor, String descripcion, String motivacion,
			String origen, String estado, String motivo_rechazo) {

		this.titulo = getTitulo();
		this.categoria = getCategoria();
		this.autor = getAutor();
		this.descripcion = getDescripcion();
		this.motivacion = getMotivacion();
		this.origen = getOrigen();
		this.estado = getEstado();
		this.motivo_rechazo = getMotivo_rechazo();
	}

	public Propuesta() {
		//Constructor para el método verDetalle en propuestaDAO
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

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getMotivacion() {
		return motivacion;
	}

	public void setMotivacion(String motivacion) {
		this.motivacion = motivacion;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getMotivo_rechazo() {
		return motivo_rechazo;
	}

	public void setMotivo_rechazo(String motivo_rechazo) {
		this.motivo_rechazo = motivo_rechazo;
	}

	public void cambiarEstado(String texto) {
		setEstado(texto);
	}

}
