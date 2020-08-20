package com.efm.orderstore.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.efm.orderstore.domains.PaymentSlip;

@Service
public class SlipService {

	public static void fillTheSlip(PaymentSlip payment, Date orderCliInstant) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(orderCliInstant);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		payment.setExpiresDate(cal.getTime());
	}
	
}
