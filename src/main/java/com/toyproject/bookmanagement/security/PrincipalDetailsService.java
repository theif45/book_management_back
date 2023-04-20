package com.toyproject.bookmanagement.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.toyproject.bookmanagement.entity.User;
import com.toyproject.bookmanagement.exception.CustomException;
import com.toyproject.bookmanagement.exception.ErrorMap;
import com.toyproject.bookmanagement.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService{
	
	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User userEntity = userRepository.findUserByEmail(email);
		
		if(userEntity == null) {
			throw new CustomException("User is not registered",
					ErrorMap.builder().put("email", "등록되지 않은 이메일입니다.").build());
		}
		return userEntity.toPrincipal();
	}

}
