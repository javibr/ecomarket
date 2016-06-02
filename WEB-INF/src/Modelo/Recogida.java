package Modelo;

public class Recogida {
	private int id;
	private String lugar;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	//Depurar
	public String toString() {
		return "" + id + "" + lugar;
	}
}