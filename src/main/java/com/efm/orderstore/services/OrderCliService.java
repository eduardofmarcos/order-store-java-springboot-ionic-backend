package com.efm.orderstore.services;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.efm.orderstore.domains.Client;
import com.efm.orderstore.domains.OrderCli;
import com.efm.orderstore.domains.OrderItem;
import com.efm.orderstore.domains.PaymentSlip;
import com.efm.orderstore.domains.enums.PaymentStatus;
import com.efm.orderstore.repositories.OrderCliRepository;
import com.efm.orderstore.repositories.OrderItemRepository;
import com.efm.orderstore.repositories.PaymentRepository;
import com.efm.orderstore.security.UserSS;
import com.efm.orderstore.services.exceptions.AuthorizationException;
import com.efm.orderstore.services.exceptions.ObjectNotFoundException;

@Service
public class OrderCliService {
	
	@Autowired
	private EmailService emailService;
	@Autowired
	private OrderCliRepository orderCliRepository;
	
	@Autowired 
	private SlipService slipService;
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private OrderItemRepository oderItemRepository;
	
	public OrderCli findById(Integer id) {
		Optional<OrderCli> obj = orderCliRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Object not found. Id: " + id + ", Type: " + OrderCli.class.getName()));
		}
	
	@Transactional
	public OrderCli insert(OrderCli obj) {
		
		obj.setId(null);
		obj.setInstant(new Date());
		obj.setClient(clientService.findById(obj.getClient().getId()));
		obj.getPayment().setPaymentStatus(PaymentStatus.PENDING);
		obj.getPayment().setOrder(obj);
		if(obj.getPayment() instanceof PaymentSlip) {
			PaymentSlip payment = (PaymentSlip) obj.getPayment();
			SlipService.fillTheSlip(payment, obj.getInstant());
		}
		obj = orderCliRepository.save(obj);
		paymentRepository.save(obj.getPayment());
		for(OrderItem oi: obj.getOrderItems()) {
			oi.setDiscount(0.0);
			oi.setProduct(productService.findById(oi.getProduct().getId()));
			oi.setPrice(oi.getProduct().getPrice());
			oi.setOrderCli(obj);
		}
		
		oderItemRepository.saveAll(obj.getOrderItems());
		
		emailService.sendOrderConfirmationHtmlEmail(obj);
		
		return obj;
	}
	
	public Page<OrderCli> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		UserSS user = UserService.authenticatedUser();
		if(user==null) {
			throw new AuthorizationException("Access denied");
		}
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Client client = clientService.findById(user.getId());
		return orderCliRepository.findByClient(client, pageRequest);
	}
	
}
