package com.example.skeleton.repositories;

import com.example.skeleton.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepo extends JpaRepository<Member, Long> {
	
	List<Member> findByName(String name);
	List<Member> findByNameContains(String name);

}
