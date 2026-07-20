package com.trvelplannerplatform.user.interfaces;

import com.trvelplannerplatform.user.dto.LoginRequest;
import com.trvelplannerplatform.user.dto.LoginResponse;

public interface UserLoginService {
	
	public LoginResponse login(LoginRequest loginRequest);

}
