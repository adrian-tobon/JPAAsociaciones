package com.curso.springboot.jpa.asociaciones.entities;

import java.util.List;
import java.util.Objects;

import org.hibernate.Hibernate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="courses")
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String instructor;
	
	private String content;
	
	/*private List<Student> students;*/
	

	public Course() {
		
	}
	
	public Course(String name, String instructor, String content) {
		this();
		this.name = name;
		this.instructor = instructor;
		this.content = content;
	}




	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(content, id, instructor, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return Objects.equals(content, other.content) && Objects.equals(id, other.id)
				&& Objects.equals(instructor, other.instructor) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "{id=" + id + ", name=" + name + ", instructor=" + instructor + ", content=" + content + "}";
	}
	
	

}
