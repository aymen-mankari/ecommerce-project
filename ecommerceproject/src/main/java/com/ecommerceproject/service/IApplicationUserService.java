package com.ecommerceproject.service;

import com.ecommerceproject.entities.ApplicationUser;

public interface IApplicationUserService {
	ApplicationUser getByUsername(String username);
}
