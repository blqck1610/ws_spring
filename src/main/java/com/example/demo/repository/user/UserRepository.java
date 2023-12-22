package com.example.demo.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.person.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query(value = "SELECT * FROM user WHERE username = :username" , nativeQuery = true)
	User findUserByUsername(@Param("username") String username);

	@Query( nativeQuery = true, value = "SELECT * FROM user e Where e.id = :id")
	User findUserById(@Param("id") Integer id);
	
	
}
