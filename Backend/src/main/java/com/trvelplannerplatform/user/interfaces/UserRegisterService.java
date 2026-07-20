package com.trvelplannerplatform.user.interfaces;

import com.trvelplannerplatform.user.dto.UserRegisterRequest;

public interface UserRegisterService<T> {
	
	public void validate(UserRegisterRequest request);

}
