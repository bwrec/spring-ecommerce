package com.company.Ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.Ecommerce.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmail(String email);

}
