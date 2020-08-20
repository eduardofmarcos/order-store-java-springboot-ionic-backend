package com.efm.orderstore.domains;

import javax.persistence.Entity;

import com.efm.orderstore.domains.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("pagamentoComCartao")
public class CreditCardPayment extends Payment{
	private static final long serialVersionUID = 1L;
	
	private Integer installments;
	
	public CreditCardPayment() {}

	public CreditCardPayment(Integer id, PaymentStatus paymentStatus, OrderCli order, Integer installments) {
		super(id, paymentStatus, order);
		this.installments = installments;
	}

	public Integer getInstallments() {
		return installments;
	}

	public void setInstallments(Integer installments) {
		this.installments = installments;
	};
	
	
}
