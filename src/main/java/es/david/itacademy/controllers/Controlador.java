package es.david.itacademy.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import es.david.itacademy.bean.Libro;
import es.david.itacademy.bean.Usuario;
import es.david.itacademy.service.BaseDatosJpaService;

@Controller //Convierte en Servlet -> atiende peticiones http
@RequestMapping("")
public class Controlador {
	
	//BaseDatos bd = new BaseDatos(); //Backend con ArrayList
	//BaseDatosJDBC bd = new BaseDatosJDBC();
	@Autowired
	BaseDatosJpaService bd;
	
	Usuario usuario;
	
	@GetMapping("/")
	public String iniciar(Model model) {
		model.addAttribute("titulo","FORMULARIO DE ACCESO"); //busca un ${titulo} en el login y pone "FORM....."
		return "login";
	}
	
	@PostMapping("/") //puede recibir los datos enviados por el formulario de acceso uno a uno o empaquetarlos en una clase
	public String login(Usuario usuario, Model model) {
		//if(usuario.getNombre().equals("david") && usuario.getPassword().equals("david")) {
		if(bd.compruebaUsuario(usuario.getNombre(), usuario.getPassword())) {
			ArrayList<Libro> libros = bd.getAllLibros();
			model.addAttribute("usuario", usuario);
			this.usuario = usuario;
			model.addAttribute("libros", libros);
			
			System.err.println("Usuario.name = " + usuario.getNombre());
			
			return "consulta";
		}else
			return "login";
	}
	
	@PostMapping("/insertar")
	public String insertar(Libro libro, Model model) {
		bd.inserta(libro);
		ArrayList<Libro> libros = bd.getAllLibros();
		model.addAttribute("usuario", this.usuario);
		model.addAttribute("libros", libros);
		
		return "consulta";
	}

	@GetMapping("/borrado/{id}")
	public String borrar(@PathVariable int id, Model model) {
		
		bd.borrar(id);
		
		ArrayList<Libro> libros = bd.getAllLibros();
		model.addAttribute("usuario", this.usuario);
		model.addAttribute("libros", libros);
		model.addAttribute("boton", "Inserta Libro");
		model.addAttribute("action", "/insertar");
		
		return "consulta";
	}
	
	@GetMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model) {
		
		Libro libro = bd.getLibroById(id);
		ArrayList<Libro> libros= bd.getAllLibros();
		
		model.addAttribute("libros", libros);
		model.addAttribute("libro", libro);
		model.addAttribute("usuario", this.usuario);
		model.addAttribute("boton", "Actualiza Libro");
		model.addAttribute("action", "/modificar");
		
		return "consulta";
	}
	
	@PostMapping("/modificar")
	public String modificar2(Libro libro, Model model) {
		
		bd.modifica(libro);
		
		ArrayList<Libro> libros = bd.getAllLibros();
		model.addAttribute("usuario", this.usuario);
		model.addAttribute("libros", libros);
		model.addAttribute("libro", null);
		model.addAttribute("boton", "Inserta Libro");
		model.addAttribute("action", "/insertar");
		
		return "consulta";
	}
}
