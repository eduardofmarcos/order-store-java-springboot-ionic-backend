package com.efm.orderstore.domains.enums;

public enum ClientType {

	PESSOAFISICA(1, "Pessoa Fisica"), PESSOAJURIDICA(2, "Pessoa Juridica");

	private Integer code;
	private String description;

	private ClientType(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static ClientType toEnum(Integer code) {
		if (code == null) {
			return null;
		}

		for (ClientType el : ClientType.values()) {
			if (code.equals(el.getCode())) {
				return el;
			}
		}

		throw new IllegalArgumentException("Invalid Id: " + code);

	}
}
