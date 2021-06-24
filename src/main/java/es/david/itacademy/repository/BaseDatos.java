package es.david.itacademy.repository;

import java.util.ArrayList;
import java.util.Iterator;

import es.david.itacademy.bean.Libro;

public class BaseDatos {

	ArrayList<Libro> libros = new ArrayList<>();
	
	public BaseDatos() {
		libros.add(new Libro(1, "HARRY POTTER Y EL PRISIONERO DE AZKABÁN", "J.K.ROWINS","SALAMANDRA", "26/9/2006", "INFANTIL"));
		libros.add(new Libro(2, "EL GRAN LABERINTO", "FERNANDO SABATER PEREZ","ARIEL", "26/9/2006", "FICCION"));
		libros.add(new Libro(3, "ROMEO Y JULIETA", "WILLIAM SHAKESPEARE","SALAMANDRA", "26/9/2006", "ROMANTICA"));
		libros.add(new Libro(4, "LA CARTA ESFERICA", "ARTURO PEREZ LOPEZ","SALAMANDRA", "26/9/2006", "FICCION"));
		libros.add(new Libro(5, "CODIGO DA VINCI","DAN BROWN","ARIEL", "26/9/2006", "FICCION"));
		libros.add(new Libro(6, "MUCHO RUIDO Y POCAS NUECES", "WILLIAM SHAKESPEARE","SALAMANDRA", "26/9/2006", "INFANTIL"));
		libros.add(new Libro(7, "PROTOCOLO", "JOSE LOPEZ MURILLO","SALAMANDRA", "26/9/2006", "SOCIAL"));
		libros.add(new Libro(8, "LINUX", "FERNANDO SABATER PEREZ","ARIEL", "26/9/2006", "INFORMATICA"));
		libros.add(new Libro(9, "EL TUMULO", "H.P LOVECRAFT","DEBATE", "26/9/2006", "CIENCIA"));
		libros.add(new Libro(10, "CAPITAN ALATRISTE", "ARTURO PEREZ LOPEZ","SALAMANDRA", "26/9/2006", "FICCION"));
		libros.add(new Libro(11, "PIEL DE TAMBOR", "ARTURO PEREZ LOPEZ","ALFAGUARA", "26/9/2006", "FICCION"));
		libros.add(new Libro(12, "TIEMPOS DE COLERA", "GABRIEL GARCIA GARCIA","OVEJA NEGRA", "26/9/2006", "OCIO"));
		libros.add(new Libro(13, "NOTICIA DE UN SECUESTRO", "GARBIEL GARCIA GARCIA","ALFAGUARA", "26/9/2006", "FICCION"));
		libros.add(new Libro(14, "FORTALEZA DIGITAL","DAN BROWN","ARIEL", "26/9/2006", "FICCION"));
		libros.add(new Libro(15, "DIETAS MEDITERRANEASL","ARTURO PEREZ LOPEZ","ARIEL", "26/9/2006", "ASTRONOMIA"));
	}
	
	public ArrayList<Libro> getAllLibros(){
		return libros;
	}
	
	public void setLibros(ArrayList<Libro> libros) {
		this.libros = libros;
	}
	
	public void inserta(Libro libro) {
		libros.add(libro);
	}

	public void borrar(int id) {
		Iterator<Libro> iter = libros.iterator();
		while(iter.hasNext()) {
			Libro lib = iter.next();
			if(lib.getId()==id) {
				iter.remove();
				System.out.println("Borrado libro: " + lib.toString());
				break;
			}
		}		
	}
	
	public Libro getLibroById(int id) {
		
		Libro libroEncontrado = null;
		
		for(Libro libro: this.getAllLibros()) {
			if(libro.getId()==id) {
				libroEncontrado = libro;
			}
		}
		return libroEncontrado;
	}

	public void modifica(Libro libro) {
		
		Iterator<Libro> iter = libros.iterator();
		
		while(iter.hasNext()) {
			Libro lib = iter.next();
		
			if(lib.getId() == libro.getId()) {
				lib.setTitulo(libro.getTitulo());
				lib.setAutor(libro.getAutor());
				lib.setEditorial(libro.getEditorial());
				lib.setFecha(libro.getFecha());
				lib.setTematica(libro.getTematica());
				
				System.out.println("Modificado libro: " + lib.toString());
				
				break;
			}
		}
		
	}
}
