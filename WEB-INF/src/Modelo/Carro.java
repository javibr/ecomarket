package Modelo;

public class Carro {
	private int idCliente;
	private String producto;
	private int idPresentacion;
	private int unidades;
	private float precio;

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdPresentacion() {
		return idPresentacion;
	}

	public void setIdPresentacion(int idPresentacion) {
		this.idPresentacion = idPresentacion;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
//Depurar
	public String toString() {
		return "" + idCliente + producto + unidades + "" + precio;
	}
}