package com.ecommerceproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerceproject.dao.ApplicationUserRepository;
import com.ecommerceproject.entities.ApplicationUser;

@Service
public class ApplicationUserServiceImpl implements IApplicationUserService {

	@Autowired
	private ApplicationUserRepository applicationUserRepository;

	@Override
	public ApplicationUser getByUsername(String username) {
		return applicationUserRepository.findByUsername(username);
	}
	
	

}
