package com.imchobo.demo2.util;

import at.favre.lib.crypto.bcrypt.BCrypt;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
public class PasswordEncoder {

	public static String encode(String rawPassword) {
		return BCrypt.withDefaults().hashToString(10, rawPassword.toCharArray());
	}

	public static boolean matches(String rawPassword, String encodedPassword) {
		BCrypt.Result result = BCrypt.verifyer().verify(rawPassword.toCharArray(), encodedPassword);
		return result.verified;
	}
	
	
}
