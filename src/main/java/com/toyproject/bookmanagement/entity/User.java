package com.toyproject.bookmanagement.entity;

import java.util.ArrayList;
import java.util.List;

import com.toyproject.bookmanagement.security.PrincipalUserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
	private int userId;
	private String email;
	private String password;
	private String name;
	private String provider;
	
	private List<Authority> authorities;
	
	public PrincipalUserDetails toPrincipal() {
		List<String> roles = new ArrayList<>();
		
		authorities.forEach(authority -> {
			roles.add(authority.getRole().getRoleName());
		});
		
		return PrincipalUserDetails.builder()
				.userId(userId)
				.email(email)
				.password(password)
				.roles(roles)
				.build();
	}
}
