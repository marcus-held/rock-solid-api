package de.held.rocksolidapi.user;

import java.math.BigDecimal;
import org.springframework.stereotype.Service;

/**
 * Repository for the user. The current implementation only supports a single user that is created statically.
 */
@Service
public class UserRepository {

	private UserEntity user = new UserEntity(BigDecimal.valueOf(500));

	public UserEntity getUser() {
		return user;
	}

}
