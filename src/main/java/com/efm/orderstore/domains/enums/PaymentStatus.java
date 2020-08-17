package com.efm.orderstore.domains.enums;

public enum PaymentStatus {

	PENDING(1,"Pendente"),
	PAID(2,"Quitado"),
	CANCELLED(3,"Cancelado");
	
	private Integer code;
	private String description;

	private PaymentStatus(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static PaymentStatus toEnum(Integer code) {
		if (code == null) {
			return null;
		}

		for (PaymentStatus el : PaymentStatus.values()) {
			if (code.equals(el.getCode())) {
				return el;
			}
		}

		throw new IllegalArgumentException("Invalid Id: " + code);

	}
}
