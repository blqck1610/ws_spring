package com.example.demo.entity.person;

import org.springframework.security.core.GrantedAuthority;

import com.example.demo.util.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users_roles")
@Data
public class UserRoles implements GrantedAuthority  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Role roleName;

	@JsonBackReference(value = "user")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user")
	private User user;

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return roleName.toString();
		
	}
}
