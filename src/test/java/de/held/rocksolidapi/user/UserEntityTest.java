package de.held.rocksolidapi.user;

import de.held.rocksolidapi.user.UserEntity;
import java.math.BigDecimal;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class UserEntityTest {

	@Test
	public void getHumanReadableMoney_noMoney_twoDecimalPoints() {
		var user = new UserEntity(BigDecimal.ZERO);

		Assertions.assertThat(user.getHumanReadableMoney())
				.isEqualTo("0.00");
	}

	@Test
	public void getHumanReadableMoney_oneDecimalPoint_twoDecimalPoints() {
		var user = new UserEntity(new BigDecimal("5.2"));

		Assertions.assertThat(user.getHumanReadableMoney())
				.isEqualTo("5.20");
	}

	@Test
	public void getHumanReadableMoney_twoDecimalPoint_twoDecimalPoints() {
		var user = new UserEntity(new BigDecimal("5.22"));

		Assertions.assertThat(user.getHumanReadableMoney())
				.isEqualTo("5.22");
	}

	@Test
	public void getHumanReadableMoney_threeDecimalPoint_roundDown() {
		var user = new UserEntity(new BigDecimal("5.222"));

		Assertions.assertThat(user.getHumanReadableMoney())
				.isEqualTo("5.22");
	}

	@Test
	public void addMoney_moneyAdded() {
		var user = new UserEntity(BigDecimal.ZERO);
		user.addMoney(BigDecimal.valueOf(2));

		Assertions.assertThat(user.getMoney())
				.isEqualTo(BigDecimal.valueOf(2));
	}

	@Test
	public void subtractMoney_enoughMoney_moneyDeducted() {
		var user = new UserEntity(BigDecimal.valueOf(3));
		user.subtractMoney(BigDecimal.valueOf(1));

		Assertions.assertThat(user.getMoney())
				.isEqualTo(BigDecimal.valueOf(2));
	}

	@Test
	public void subtractMoney_exactlyEnoughMoney_moneyDeducted() {
		var user = new UserEntity(BigDecimal.valueOf(3));
		user.subtractMoney(BigDecimal.valueOf(3));

		Assertions.assertThat(user.getMoney())
				.isEqualTo(BigDecimal.ZERO);
	}

	@Test
	public void subtractMoney_notEnoughMoney_exception() {
		var user = new UserEntity(BigDecimal.valueOf(3));

		Assertions.assertThatThrownBy(() -> user.subtractMoney(BigDecimal.valueOf(4)))
				.isInstanceOf(RuntimeException.class);
	}

}
