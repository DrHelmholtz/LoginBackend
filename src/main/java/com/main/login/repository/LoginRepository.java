package com.main.login.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.main.login.domain.User;

@Repository
public interface LoginRepository extends CrudRepository<User, Long>{
	
	User findFirstByUsername(String username);
	
	boolean existsByUsername(String username);

}
