package com.ecommerceproject;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ecommerceproject.dao.ApplicationUserRepository;
import com.ecommerceproject.dao.CustomerRepository;
import com.ecommerceproject.entities.ApplicationUser;
import com.ecommerceproject.entities.Customer;

@SpringBootApplication
public class EcommerceProjectApplication implements CommandLineRunner {

	@Autowired
	private ApplicationUserRepository applicationUserRepository;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(EcommerceProjectApplication.class, args);
		System.out.println("ecommerce project");
	}

	@Override
	public void run(String... args) throws Exception {
		
		Customer c = new Customer("dsf", "fsdfd@ds.com","fdsf","097832");
		
		ApplicationUser user1 = new ApplicationUser("aymen", passwordEncoder.encode("123"), true);
		ApplicationUser user2 = new ApplicationUser("john", passwordEncoder.encode("azerty"), false);
		
		applicationUserRepository.saveAll(Arrays.asList(user1, user2));
		
		customerRepository.save(c);
		
	}
}
