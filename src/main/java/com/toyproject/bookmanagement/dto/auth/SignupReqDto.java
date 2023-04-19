package com.toyproject.bookmanagement.dto.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.toyproject.bookmanagement.entity.User;

import lombok.Data;

@Data
public class SignupReqDto {
	
	@Email
	private String email;
	
	// 최소 8글자,글자1개,숫자1개,특수문자1개
	// .은 모든 문자열을 말함
	// *은 있거나 없거나, +는 무조건 있어야함
	// [A-Za-z]대문자 A부터 소문자 z까지
	// (?=.*[A-Za-z])모든 글자 중에 대문자 A부터 소문자 z있는지
	// (?=.*\\d)는 (?=.*[0-9])모든 글자 중에 숫자가 있는지
	// (?=.*[@$!%*#?&]) 모든 글자 중에 @$!%*#?&가 있는지
	// [A-Za-z\\d@$!%*#?&]에 포함되어야 함
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,16}$",
			message = "비밀번호는 영문자, 숫자, 특수문자를 포함하여 8 ~ 16자로 작성")
	private String password;
	
	@Pattern(regexp = "^[가-힣]{2,7}$",
			message = "이름은 한글로만 작성 가능합니다.")
	private String name;
	
	public User toEntity() {
		return User.builder()
				.email(email)
				.password(new BCryptPasswordEncoder().encode(password))
				.name(name)
				.build();
	}
}
