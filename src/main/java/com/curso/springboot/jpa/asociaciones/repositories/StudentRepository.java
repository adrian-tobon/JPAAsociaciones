package com.curso.springboot.jpa.asociaciones.repositories;

import org.springframework.data.repository.CrudRepository;
import com.curso.springboot.jpa.asociaciones.entities.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {
	
	

}
