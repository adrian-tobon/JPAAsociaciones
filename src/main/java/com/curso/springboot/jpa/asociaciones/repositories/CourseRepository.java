package com.curso.springboot.jpa.asociaciones.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.curso.springboot.jpa.asociaciones.entities.Course;

public interface CourseRepository extends CrudRepository<Course, Long> {

	List<Course> findByIdNotIn(List<Long> ids);
}
