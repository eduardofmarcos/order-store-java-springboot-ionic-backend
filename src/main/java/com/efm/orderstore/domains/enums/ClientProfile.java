package com.efm.orderstore.domains.enums;

public enum ClientProfile {

	ADMIN(1,"ROLE_ADMIN"),
	CLIENT(2,"ROLE_CLIENT");
	
	private Integer code;
	private String description;

	private ClientProfile(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static ClientProfile toEnum(Integer code) {
		if (code == null) {
			return null;
		}

		for (ClientProfile el : ClientProfile.values()) {
			if (code.equals(el.getCode())) {
				return el;
			}
		}

		throw new IllegalArgumentException("Invalid Id: " + code);

	}
}
