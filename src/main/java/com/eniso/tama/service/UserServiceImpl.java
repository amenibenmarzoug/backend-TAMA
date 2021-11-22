package com.eniso.tama.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eniso.tama.entity.Participant;
import com.eniso.tama.entity.User;
import com.eniso.tama.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder encoder;

	@Override
	public void resetPassword(long id, NewPassword newPassword) {
		User user = userRepository.findByIdAndDeletedFalse(id);

		user.setPassword(encoder.encode(newPassword.getPassword()));

		userRepository.save(user);

	}

	@Override
	public void deleteUser(long id) {
		User user = userRepository.findByIdAndDeletedFalse(id);
		user.setDeleted(true);
		userRepository.save(user);

	}

	public static class NewPassword {
		String email;
		String password;
		String passwordConfirm;

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getPasswordConfirm() {
			return passwordConfirm;
		}

		public void setPasswordConfirm(String passwordConfirm) {
			this.passwordConfirm = passwordConfirm;
		}

	}

}
