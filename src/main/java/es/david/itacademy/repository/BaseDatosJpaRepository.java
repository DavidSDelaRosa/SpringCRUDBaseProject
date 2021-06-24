package es.david.itacademy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.david.itacademy.bean.Libro;

public interface BaseDatosJpaRepository extends JpaRepository<Libro, Integer>{

}
