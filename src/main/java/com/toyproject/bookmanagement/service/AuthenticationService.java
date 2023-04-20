package com.toyproject.bookmanagement.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.toyproject.bookmanagement.dto.auth.JwtTokenRespDto;
import com.toyproject.bookmanagement.dto.auth.LoginReqDto;
import com.toyproject.bookmanagement.dto.auth.SignupReqDto;
import com.toyproject.bookmanagement.entity.Authority;
import com.toyproject.bookmanagement.entity.User;
import com.toyproject.bookmanagement.exception.CustomException;
import com.toyproject.bookmanagement.exception.ErrorMap;
import com.toyproject.bookmanagement.repository.UserRepository;
import com.toyproject.bookmanagement.security.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final UserRepository userRepository;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private final JwtTokenProvider jwtTokenProvider;
	
	public void checkDuplicatedEmail(String email) {
		if(userRepository.findUserByEmail(email) != null) {
			
			throw new CustomException("Duplicated Email",
					ErrorMap.builder()
					.put("email", "사용중인 이메일입니다.")
					.build());
		}
	}
	
	public void registeUser(SignupReqDto signupReqDto) {
		User userEntity = signupReqDto.toEntity();
		
		userRepository.saveUser(userEntity);
		userRepository.saveAuthorities(Authority.builder()
												.userId(userEntity.getUserId())
												.roleId(1)
												.build());
		userRepository.saveAuthorities(Authority.builder()
				.userId(userEntity.getUserId())
				.roleId(2)
				.build());
	}
	
	public JwtTokenRespDto login(LoginReqDto loginReqDto) {
		UsernamePasswordAuthenticationToken authenticationToken = 
				new UsernamePasswordAuthenticationToken(loginReqDto.getEmail(), loginReqDto.getPassword());
		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
		return jwtTokenProvider.createToken(authentication);
	}
}
