package com.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AdminLoginResponse {
	private long adminId;
	private String name;
	private String email;

	public AdminLoginResponse(long adminId, String name, String email) {
		super();
		this.name = name;
		this.email = email;
		this.adminId = adminId;
	}

}
