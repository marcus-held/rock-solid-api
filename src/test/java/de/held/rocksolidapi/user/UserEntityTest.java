package de.held.rocksolidapi.user;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class UserEntityTest {

	@Test
	public void addMoney_moneyAdded() {
		var user = new UserEntity(new Money(0));
		user.addMoney(new Money(2));

		Assertions.assertThat(user.getMoney())
				.isEqualTo(new Money(2));
	}

	@Test
	public void subtractMoney_enoughMoney_moneyDeducted() throws NotEnoughMoneyException {
		var user = new UserEntity(new Money(3));
		user.subtractMoney(new Money(1));

		Assertions.assertThat(user.getMoney())
				.isEqualTo(new Money(2));
	}

	@Test
	public void subtractMoney_exactlyEnoughMoney_moneyDeducted() throws NotEnoughMoneyException {
		var user = new UserEntity(new Money(3));
		user.subtractMoney(new Money(3));

		Assertions.assertThat(user.getMoney())
				.isEqualTo(new Money(0));
	}

	@Test
	public void subtractMoney_notEnoughMoney_exception() {
		var user = new UserEntity(new Money(3));

		Assertions.assertThatThrownBy(() -> user.subtractMoney(new Money(4)))
				.isInstanceOf(NotEnoughMoneyException.class);
	}

}
