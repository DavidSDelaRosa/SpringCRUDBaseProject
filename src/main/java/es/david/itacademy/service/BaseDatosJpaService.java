package es.david.itacademy.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.david.itacademy.bean.Libro;
import es.david.itacademy.repository.BaseDatosJpaRepository;
import es.david.itacademy.repository.IBaseDatosJpa;

@Service
public class BaseDatosJpaService implements IBaseDatosJpa{

	@Autowired
	BaseDatosJpaRepository bd;
	
	@Override
	public void inserta(Libro libro) {
		bd.save(libro);
		System.out.println("Libro insertado: " + libro.toString());

	}

	@Override
	public void borrar(int id) {
		bd.deleteById(id);
		System.out.println("Libro con ID " +id+ " borrado.");
	}

	@Override
	public void modifica(Libro libro) {
		bd.save(libro);
		System.out.println("Libro modificado: " + libro.toString());

	}

	@Override
	public Libro getLibroById(int id) {

		Optional<Libro> libro = bd.findById(id);
		System.out.println("Libro encontrado: " + libro.toString());

		return libro.get();
	}

	@Override
	public ArrayList<Libro> getAllLibros() {
		return (ArrayList<Libro>) bd.findAll();
	}

	@Override
	public boolean compruebaUsuario(String usuario, String password) {

		boolean check = false;
		
		try {
			String conex = "jdbc:mysql://localhost:3306/biblioteca_online";
			Connection conexion = DriverManager.getConnection(conex, "root", "");
			Statement stmt = conexion.createStatement();
			String sql = "SELECT count(*) FROM usuarios WHERE usuario='" + usuario +"' AND password='"+ password +"'";
			
			stmt.execute(sql);
			
			ResultSet rs = stmt.getResultSet();
			
			rs.next();
			
			if(rs.getInt(1)>0) check=true;
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		return check;
	}

}
