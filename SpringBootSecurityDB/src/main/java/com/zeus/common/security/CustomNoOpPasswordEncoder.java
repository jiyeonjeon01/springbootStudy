package com.zeus.common.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomNoOpPasswordEncoder implements PasswordEncoder {
	private final PasswordEncoder passwordEncoder;
    public CustomNoOpPasswordEncoder() {
       this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
	public String encode(CharSequence rawPassword) {
		log.info("CustomNoOpPasswordEncoder before encode :" + rawPassword);
		return passwordEncoder.encode(rawPassword);
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		log.info("CustomNoOpPasswordEncoder matches: " + rawPassword + ":" + encodedPassword);
		return passwordEncoder.matches(rawPassword, encodedPassword); 
	}

}