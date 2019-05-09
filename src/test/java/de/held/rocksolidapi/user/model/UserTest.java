package de.held.rocksolidapi.user.model;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class UserTest {

	private UserEntity user;

	@Before
	public void setup() {
		user = new UserEntity(0);
	}

	@Test
	public void addMoney_moneyAdded() {
		user.addMoney(2d);

		Assertions.assertThat(user.getMoney())
				.isEqualTo(2d);
	}

	@Test
	public void deductMoney_enoughMoney_moneyDeducted() {
		user.addMoney(3d);
		user.deductMoney(1d);

		Assertions.assertThat(user.getMoney())
				.isEqualTo(2d);
	}

	@Test
	public void deductMoney_exactlyEnoughMoney_moneyDeducted() {
		user.addMoney(3d);
		user.deductMoney(3d);

		Assertions.assertThat(user.getMoney())
				.isEqualTo(0);
	}

	@Test
	public void deductMoney_notEnoughMoney_exception() {
		user.addMoney(3d);

		Assertions.assertThatThrownBy(() -> user.deductMoney(4d))
				.isInstanceOf(RuntimeException.class);
	}

}
