package Modelo;

public class Producto {
	private int id;
	private int idPresentacion;
	public 	int stock;
	public 	boolean hayStock;
	private float precio;
	private String nombre;
	private String cantidad;
	private String denominacion;
	private String envase;
	private String tipo;
	private String marca;

	public int getId() {
		return id;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public boolean getHayStock() {
		return hayStock;
	}

	public void setHayStock(boolean hayStock) {
		this.hayStock = hayStock;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getTipo() {
		return tipo;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdPresentacion() {
		return idPresentacion;
	}

	public void setIdPresentacion(int idPresentacion) {
		this.idPresentacion = idPresentacion;
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getEnvase() {
		return envase;
	}

	public void setEnvase(String envase) {
		this.envase = envase;
	}

	//Depurar
	public String toString() {
		return "" + id + nombre + denominacion + marca ;
	}
}