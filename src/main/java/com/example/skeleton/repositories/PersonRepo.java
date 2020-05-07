package com.example.skeleton.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.skeleton.models.Person;

public interface PersonRepo extends JpaRepository<Person, Long> {
	
	List<Person> findByName(String name);
	List<Person> findByNameContains(String name);


}
