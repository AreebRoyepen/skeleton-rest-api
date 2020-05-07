package com.example.skeleton.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.skeleton.models.Role;

public interface RoleRepo extends JpaRepository<Role, Long>{

}
