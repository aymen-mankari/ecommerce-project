package com.ecommerceproject.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerceproject.dao.ApplicationUserRepository;
import com.ecommerceproject.entities.ApplicationUser;

@RestController
@RequestMapping(path = "/users")
public class ApplicationUserController {
	
	@Autowired
	private ApplicationUserRepository applicationUserRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/sign-up")
    public void signUp(@RequestBody ApplicationUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        applicationUserRepository.save(user);
    }
}
