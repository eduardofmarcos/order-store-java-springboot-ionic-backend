package com.efm.orderstore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.efm.orderstore.domains.Client;
import com.efm.orderstore.repositories.ClientRepository;
import com.efm.orderstore.security.UserSS;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Client client = clientRepository.findByEmail(email);
		if (client == null) {
			throw new UsernameNotFoundException("User not found: " + email);
		}

		return new UserSS(client.getId(), client.getEmail(), client.getPassword(), client.getProfile());
	}

}
