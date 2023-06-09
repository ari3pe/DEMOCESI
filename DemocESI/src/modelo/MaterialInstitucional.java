package modelo;

public class MaterialInstitucional extends Material {
	private String procedencia;
	private boolean prioritario;

	public MaterialInstitucional(String titulo, String categoria, String descripcion, String fuente, String enlace,
			String procedencia, boolean prioritario) {
		super(titulo, categoria, descripcion, fuente, enlace);
		this.procedencia = procedencia;
		this.prioritario = prioritario;
	}

	public String getProcedencia() {
		return procedencia;
	}

	public void setProcedencia(String procedencia) {
		this.procedencia = procedencia;
	}

	@Override
	public boolean esDeTratamientoPrioritario() {
		return prioritario;
	}

}
