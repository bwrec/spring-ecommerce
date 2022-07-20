package com.company.Ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.Ecommerce.model.User;
import com.company.Ecommerce.model.Wishlist;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {

	List<Wishlist> findAllByUserOrderByCreatedDateDesc(User user);

}
