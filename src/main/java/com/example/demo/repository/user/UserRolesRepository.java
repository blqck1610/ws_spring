package com.example.demo.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.person.UserRoles;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, Integer> {


	@Query(value = "SELECT * FROM users_roles where user_id = :userId ", nativeQuery = true)
	List<UserRoles> getUserRolesById(@Param("userId") Integer userId);
	
}
