package com.efm.orderstore.services.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.efm.orderstore.domains.Client;
import com.efm.orderstore.dto.ClientDTO;
import com.efm.orderstore.repositories.ClientRepository;
import com.efm.orderstore.resources.exceptions.FieldMessage;

public class ClientUpdateValidation implements ConstraintValidator<ClientUpdate, ClientDTO> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public void initialize(ClientUpdate ann) {
	};

	@Override
	public boolean isValid(ClientDTO objDTO, ConstraintValidatorContext context) {
		
		Map<String, String> map = (Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer UrlId = Integer.parseInt(map.get("id"));
				
		List<FieldMessage> list = new ArrayList<>();
		
		Client clientToFind = clientRepository.findByEmail(objDTO.getEmail());
		
		if(clientToFind!= null && !clientToFind.getId().equals(UrlId)) {
			list.add(new FieldMessage("email", "Email already already exists"));
		}
		
		for (FieldMessage el : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(el.getMessage()).addPropertyNode(el.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
