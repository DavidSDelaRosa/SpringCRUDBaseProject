package es.david.itacademy.repository;

import java.util.ArrayList;


import es.david.itacademy.bean.Libro;

public interface IBaseDatosJpa {
	
	public void inserta(Libro libro);
	public void borrar(int id);
	public void modifica(Libro libro);
	public Libro getLibroById(int id);
	public ArrayList<Libro> getAllLibros();
	public boolean compruebaUsuario(String usuario, String password);

}
