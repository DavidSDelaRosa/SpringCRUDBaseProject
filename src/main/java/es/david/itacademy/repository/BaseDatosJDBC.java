package es.david.itacademy.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import es.david.itacademy.bean.Libro;

public class BaseDatosJDBC {

	private Connection conexion;
	
	public BaseDatosJDBC() {
		try {
			//Class.forName("com.myslq.cj.jdbc.Driver");
			String conex = "jdbc:mysql://localhost:3306/biblioteca_online";
			this.conexion = DriverManager.getConnection(conex, "root", "");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void inserta(Libro libro) {
		
		String query = "INSERT INTO libros (id, titulo, autor, editorial, fecha, tematica) VALUES (?,?,?,?,?,?)";
		
		try {
			
			System.out.println("Antes de insertar: " + libro.toString());

			
			PreparedStatement prepStmt;
			prepStmt = conexion.prepareStatement(query);
			prepStmt.setInt(1, libro.getId());
			prepStmt.setString(2, libro.getTitulo());
			prepStmt.setString(3, libro.getAutor());
			prepStmt.setString(4, libro.getEditorial());
			prepStmt.setString(5, libro.getFecha());
			prepStmt.setString(6, libro.getTematica());
			
			prepStmt.executeUpdate();
			
			System.out.println("Despues de insertar: "+ libro.toString());
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void borrar(int id) {
		String query = "DELETE FROM libros WHERE id="+id; 
		
		try {
			PreparedStatement prepStmt = conexion.prepareStatement(query);
			prepStmt.executeUpdate();
			System.out.println("Borrado libro con ID: " + id);
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void modifica(Libro libro) {
		String query = "UPDATE libros SET titulo=?, autor=?, editorial=?, fecha=?, tematica=? WHERE id=?";
		
		try {
			PreparedStatement prepStmt = conexion.prepareStatement(query);
			prepStmt.setString(1, libro.getTitulo());
			prepStmt.setString(2, libro.getAutor());
			prepStmt.setString(3, libro.getEditorial());
			prepStmt.setString(4, libro.getFecha());
			prepStmt.setString(5, libro.getTematica());
			prepStmt.setInt(6, libro.getId());
			
			//System.out.println(prepStmt.toString());
			prepStmt.executeUpdate();
			
			System.out.println("Modificado libro: " + libro.toString());
			
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
	}
	
	public Libro getLibroById(int id) {
		Libro libro = null;
		
		try {
			Statement stmt = conexion.createStatement();
			String sql = "SELECT * FROM libros WHERE id="+id;
			
			stmt.execute(sql);
			
			ResultSet rs =stmt.getResultSet();
			rs.next();
			libro = new Libro(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		System.out.println("Encontrado libro: " + libro.toString());
		
		return libro;
	}
	
	public ArrayList<Libro> getAllLibros(){
		ArrayList<Libro> listaLibros = new ArrayList<>();
		
		try {
		
			Statement stmt = conexion.createStatement();
			String sql = "SELECT * FROM libros";
			
			stmt.execute(sql);
			
			ResultSet rs = stmt.getResultSet();
			
			while(rs.next()) {
				Libro libro = new Libro(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
				
				listaLibros.add(libro);
			}
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		return listaLibros;
	}
	
	public boolean comprobarUsuario(String usuario, String password) {
		boolean check = false;
		
		try {
		
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
