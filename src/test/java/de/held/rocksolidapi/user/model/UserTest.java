package de.held.rocksolidapi.user.model;

import lombok.val;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class UserTest {

	@Test
	public void addMoney_moneyAdded() {
		val user = new User();

		user.addMoney(2d);

		Assertions.assertThat(user.getMoney())
				.isEqualTo(2d);
	}

	@Test
	public void deductMoney_enoughMoney_moneyDeducted() {
		val user = new User();

		user.addMoney(3d);
		user.deductMoney(1d);

		Assertions.assertThat(user.getMoney())
				.isEqualTo(2d);
	}

	@Test
	public void deductMoney_exactlyEnoughMoney_moneyDeducted() {
		val user = new User();

		user.addMoney(3d);
		user.deductMoney(3d);

		Assertions.assertThat(user.getMoney())
				.isEqualTo(0);
	}

	@Test
	public void deductMoney_notEnoughMoney_exception() {
		val user = new User();

		user.addMoney(3d);

		Assertions.assertThatThrownBy(() -> user.deductMoney(4d))
				.isInstanceOf(RuntimeException.class);
	}

}
