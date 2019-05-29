package de.held.rocksolidapi.user;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class UserEntityTest {

	@Test
	public void addMoney_moneyAdded() {
		var user = new UserEntity(new MoneyVO(0));
		user.addMoney(new MoneyVO(2));

		Assertions.assertThat(user.getMoney())
				.isEqualTo(new MoneyVO(2));
	}

	@Test
	public void subtractMoney_enoughMoney_moneyDeducted() throws NotEnoughMoneyException {
		var user = new UserEntity(new MoneyVO(3));
		user.subtractMoney(new MoneyVO(1));

		Assertions.assertThat(user.getMoney())
				.isEqualTo(new MoneyVO(2));
	}

	@Test
	public void subtractMoney_exactlyEnoughMoney_moneyDeducted() throws NotEnoughMoneyException {
		var user = new UserEntity(new MoneyVO(3));
		user.subtractMoney(new MoneyVO(3));

		Assertions.assertThat(user.getMoney())
				.isEqualTo(new MoneyVO(0));
	}

	@Test
	public void subtractMoney_notEnoughMoney_exception() {
		var user = new UserEntity(new MoneyVO(3));

		Assertions.assertThatThrownBy(() -> user.subtractMoney(new MoneyVO(4)))
				.isInstanceOf(NotEnoughMoneyException.class);
	}

}
