package com.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FarmerLoginResponse {
	private long farmerId;
	private String firstName;
	private String lastName;
	private String email;

	public FarmerLoginResponse(long farmerId, String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.farmerId = farmerId;
	}

}
