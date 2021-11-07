package com.eniso.tama.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eniso.tama.entity.User;
import com.eniso.tama.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder encoder;

	@Override
	public void resetPassword(long id, String newPassword) {
		User user = userRepository.findById(id);

		user.setPassword(encoder.encode(newPassword));

		userRepository.save(user);

	}

}
