package com.ecommerceproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerceproject.entities.ApplicationUser;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {

}
