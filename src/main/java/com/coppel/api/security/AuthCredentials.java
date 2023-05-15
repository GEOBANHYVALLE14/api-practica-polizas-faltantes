package com.coppel.api.security;

import lombok.Data;

@Data
public class AuthCredentials {
	private String username;
	private String password;
}
