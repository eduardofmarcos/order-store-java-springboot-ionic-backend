package com.efm.orderstore.services.validations;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.efm.orderstore.domains.Client;
import com.efm.orderstore.domains.enums.ClientType;
import com.efm.orderstore.dto.ClientNewDTO;
import com.efm.orderstore.repositories.ClientRepository;
import com.efm.orderstore.resources.exceptions.FieldMessage;
import com.efm.orderstore.services.validations.utils.BR;

public class ClientInsertValidation implements ConstraintValidator<ClientInsert, ClientNewDTO> {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public void initialize(ClientInsert ann) {
	};

	@Override
	public boolean isValid(ClientNewDTO objDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if(objDTO.getClientType().equals(ClientType.PESSOAFISICA.getCode()) && !BR.isValidCPF(objDTO.getCpfOrCnpj())) {
			list.add(new FieldMessage("cpfOrCnpj", "Invalid CPF"));
		}
		
		if(objDTO.getClientType().equals(ClientType.PESSOAJURIDICA.getCode()) && !BR.isValidCNPJ(objDTO.getCpfOrCnpj())) {
			list.add(new FieldMessage("cpfOrCnpj", "Invalid CNPJ"));
		}
		
		Client clientToFind = clientRepository.findByEmail(objDTO.getEmail());
		
		if(clientToFind!= null) {
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
