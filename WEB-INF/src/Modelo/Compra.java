package Modelo;

import java.util.*;

public class Compra {
	private int id;
	private int id_cliente;
	private int id_recogida;
	private int cantidad;
	private String denominacion;
	private Date fecha_compra;
	private Date fecha_recogida;
	private float importe;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getIdCliente() {
		return id_cliente;
	}

	public void setIdCliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public int getIdRecogida() {
		return id_recogida;
	}

	public void setIdRecogida(int id_recogida) {
		this.id_recogida = id_recogida;
	}

	public float getImporte() {
		return importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	public Date getFechaCompra() {
		return fecha_compra;
	}

	public void setFechaCompra(Date fecha_compra) {
		this.fecha_compra = fecha_compra;
	}

	public Date getFechaRecogida() {
		return fecha_recogida;
	}

	public void setFechaRecogida(Date fecha_recogida) {
		this.fecha_recogida = fecha_recogida;
	}

	/* Solo depurar */
	public String toString() {
		return " " + id + " " + id_cliente + "" + id_recogida + "" + importe;
	}
}
