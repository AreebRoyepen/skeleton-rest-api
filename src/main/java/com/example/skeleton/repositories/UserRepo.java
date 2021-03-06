package com.example.skeleton.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.skeleton.models.User;

public interface UserRepo extends JpaRepository<User, Long>{
	
	User findByUsername(String username);

}
