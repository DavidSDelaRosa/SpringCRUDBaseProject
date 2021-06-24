package es.david.itacademy.bean;

public class Usuario {
	
	//deben coincidir con el atributo  "name" de los input text del formulario. Son "nombre" y "password"
	private String nombre;
	private String password;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
}
