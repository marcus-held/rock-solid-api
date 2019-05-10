package de.held.rocksolidapi.user;

import org.springframework.stereotype.Service;

@Service
public class UserRepository {

	private UserEntity user = new UserEntity(500d);

	public UserEntity getUser() {
		return user;
	}

}
