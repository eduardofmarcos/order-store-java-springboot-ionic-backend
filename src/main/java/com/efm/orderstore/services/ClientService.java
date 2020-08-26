package com.efm.orderstore.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.efm.orderstore.domains.Address;
import com.efm.orderstore.domains.City;
import com.efm.orderstore.domains.Client;
import com.efm.orderstore.domains.enums.ClientProfile;
import com.efm.orderstore.domains.enums.ClientType;
import com.efm.orderstore.dto.ClientDTO;
import com.efm.orderstore.dto.ClientNewDTO;
import com.efm.orderstore.repositories.AddressRepository;
import com.efm.orderstore.repositories.CityRepository;
import com.efm.orderstore.repositories.ClientRepository;
import com.efm.orderstore.security.UserSS;
import com.efm.orderstore.services.exceptions.AuthorizationException;
import com.efm.orderstore.services.exceptions.DataIntegrityException;
import com.efm.orderstore.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {
	
	@Value("${img.prefix.client.profile}")
	private String prefix;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private S3Service s3Service;
	
	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private AddressRepository addressRepository;

	public Client findById(Integer id) {
		
		UserSS user = UserService.authenticatedUser();
		
		if(user==null || !user.hasRole(ClientProfile.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Access denied");
		}
		Optional<Client> obj = clientRepository.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Object not found. Id: " + id + ", Type: " + Client.class.getName()));
	}

	public List<Client> findAll() {
		return clientRepository.findAll();
	}

//	public Client insert(Client obj) {
//		obj.setId(null);
//		return clientRepository.save(obj);
//	}

	public Client update(Client obj) {
		Client newObj = findById(obj.getId());
		updateDate(newObj, obj);
		return clientRepository.save(newObj);

	}

	private void updateDate(Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());

	}

	@org.springframework.transaction.annotation.Transactional
	public Client insert(Client obj) {
		obj.setId(null);
		obj = clientRepository.save(obj);
		addressRepository.saveAll(obj.getAddresses());
		return obj;
	}

	public void delete(Integer id) {
		findById(id);
		try {
			clientRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("It is not possible to delete a category with associated products");
		}
	}

	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clientRepository.findAll(pageRequest);
	}

	public Client fromDTO(ClientDTO objDTO) {
		return new Client(objDTO.getId(), objDTO.getName(), objDTO.getEmail(), null, null, null);
	}

	public Client fromDTO(ClientNewDTO objDTO) {
		Client newClient = new Client(null, objDTO.getName(), objDTO.getEmail(), objDTO.getCpfOrCnpj(),
				ClientType.toEnum(objDTO.getClientType()), pe.encode(objDTO.getPassword()));
		City city = new City(objDTO.getCityId(), null, null);
		// Optional<City> city = cityRepository.findById(objDTO.getCityId());
		Address address = new Address(null, objDTO.getStreet(), objDTO.getNumber(), objDTO.getComplement(),
				objDTO.getDistrict(), objDTO.getCep(), newClient, city);
		newClient.getAddresses().add(address);
		newClient.getPhoneList().add(objDTO.getPhone1());
		if (objDTO.getPhone2() != null) {
			newClient.getPhoneList().add(objDTO.getPhone2());
		}
		if (objDTO.getPhone3() != null) {
			newClient.getPhoneList().add(objDTO.getPhone3());
		}
		return newClient;
	}
	
	public URI uploadProfilePicture(MultipartFile multipartFile) {
		UserSS user = UserService.authenticatedUser();
		if(user==null) {
			throw new AuthorizationException("Access denied");
		}
		
		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		
		String fileName = prefix + user.getId() + ".jpg";
		
		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
		
	}

}
