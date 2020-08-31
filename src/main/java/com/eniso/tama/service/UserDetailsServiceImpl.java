package com.eniso.tama.service;
import java.util.List;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.eniso.tama.configuration.jwt.AuthEntryPointJwt;
import com.eniso.tama.entity.User;
import com.eniso.tama.repository.UserRepository;
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) {
		List<User> userlist = userRepository.findByEmail(email);
		for (User user : userlist) {
			System.out.println("user email");
			System.out.println(user.getEmail());
		}	
		
		
		
		return UserDetailsImpl.build(userlist.get(0));
	}

}
