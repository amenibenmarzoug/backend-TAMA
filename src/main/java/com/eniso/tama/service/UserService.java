package com.eniso.tama.service;

import com.eniso.tama.service.UserServiceImpl.NewPassword;

public interface UserService {

	
	public void resetPassword(long id , NewPassword newPassword);
}
