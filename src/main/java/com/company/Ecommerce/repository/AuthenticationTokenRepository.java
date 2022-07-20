package com.company.Ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.Ecommerce.model.AuthenticationToken;
import com.company.Ecommerce.model.User;

public interface AuthenticationTokenRepository extends JpaRepository<AuthenticationToken, Integer> {

	AuthenticationToken findByUser(User user);
}
