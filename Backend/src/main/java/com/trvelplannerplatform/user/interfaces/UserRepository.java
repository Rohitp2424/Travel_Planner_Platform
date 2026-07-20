package com.trvelplannerplatform.user.interfaces;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trvelplannerplatform.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);
	
	boolean existsByEmail(String email);

	boolean existsByPhone(String phone);
	
}
