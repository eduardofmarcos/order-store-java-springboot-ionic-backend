package com.efm.orderstore.domains;

import java.util.Date;

import javax.persistence.Entity;

import com.efm.orderstore.domains.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("pagamentComBoleto")
public class PaymentSlip extends Payment{
	private static final long serialVersionUID = 1L;
	
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date expiresDate;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date paymentDate;
	
	public PaymentSlip() {}

	public PaymentSlip(Integer id, PaymentStatus paymentStatus, OrderCli order, Date expiresDate, Date paymentDate) {
		super(id, paymentStatus, order);
		this.expiresDate = expiresDate;
		this.paymentDate = paymentDate;
	}

	public Date getExpiresDate() {
		return expiresDate;
	}

	public void setExpiresDate(Date expiresDate) {
		this.expiresDate = expiresDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	};
	
	
	
}
