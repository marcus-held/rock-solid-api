package de.held.rocksolidapi.user;

import de.held.rocksolidapi.market.Money;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

/**
 * Repository for the user. The current implementation only supports a single user that is created statically.
 */
@Service
public class UserRepository {

	private UserEntity user = new UserEntity(new Money("500"));

	public UserEntity getUser() {
		return user;
	}

}
