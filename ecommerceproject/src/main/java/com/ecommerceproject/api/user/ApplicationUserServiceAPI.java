package com.ecommerceproject.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerceproject.dto.ApplicationUserDTO;
import com.ecommerceproject.dto.converters.ApplicationUserConverter;
import com.ecommerceproject.service.IApplicationUserService;

@RestController
@RequestMapping(path = "/users")
public class ApplicationUserServiceAPI {
	
//	@Autowired
//	private ApplicationUserRepository applicationUserRepository;
//	@Autowired
//	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private IApplicationUserService appUserService;
	@Autowired
	private ApplicationUserConverter applicationUserConverter;
	
//	@PostMapping("/sign-up")
//    public void signUp(@RequestBody ApplicationUser user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        applicationUserRepository.save(user);
//    }
	
	@GetMapping("/getUserByUsername/{username}")
	public ApplicationUserDTO getUserByUsername(@PathVariable(name = "username") String username) {
		ApplicationUserDTO user = applicationUserConverter.convertToDTO(appUserService.getByUsername(username));
		user.setPassword("");
		return user;
	}
}
