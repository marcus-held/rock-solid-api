package de.held.rocksolidapi.market;

import de.held.rocksolidapi.user.MoneyVO;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class MoneyVOTest {


	@Test
	public void isNegative_negative_true() {
		var money = new MoneyVO(-123);

		Assertions.assertThat(money.isNegative()).isTrue();
	}

	@Test
	public void isNegative_zero_false() {
		var money = new MoneyVO(0);

		Assertions.assertThat(money.isNegative()).isFalse();
	}

	@Test
	public void isNegative_positive_false() {
		var money = new MoneyVO(13);

		Assertions.assertThat(money.isNegative()).isFalse();
	}

	@Test
	public void isNegativeOrZero_negative_true() {
		var money = new MoneyVO(-123);

		Assertions.assertThat(money.isNegativeOrZero()).isTrue();
	}

	@Test
	public void isNegativeOrZero_zero_true() {
		var money = new MoneyVO(0);

		Assertions.assertThat(money.isNegativeOrZero()).isTrue();
	}

	@Test
	public void isNegativeOrZero_positive_false() {
		var money = new MoneyVO(13);

		Assertions.assertThat(money.isNegativeOrZero()).isFalse();
	}

}
