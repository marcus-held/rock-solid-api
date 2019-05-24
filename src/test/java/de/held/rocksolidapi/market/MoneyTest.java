package de.held.rocksolidapi.market;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class MoneyTest {


	@Test
	public void isNegative_negative_true() {
		var money = new Money(-123);

		Assertions.assertThat(money.isNegative()).isTrue();
	}

	@Test
	public void isNegative_zero_false() {
		var money = new Money(0);

		Assertions.assertThat(money.isNegative()).isFalse();
	}

	@Test
	public void isNegative_positive_false() {
		var money = new Money(13);

		Assertions.assertThat(money.isNegative()).isFalse();
	}

	@Test
	public void isNegativeOrZero_negative_true() {
		var money = new Money(-123);

		Assertions.assertThat(money.isNegativeOrZero()).isTrue();
	}

	@Test
	public void isNegativeOrZero_zero_true() {
		var money = new Money(0);

		Assertions.assertThat(money.isNegativeOrZero()).isTrue();
	}

	@Test
	public void isNegativeOrZero_positive_false() {
		var money = new Money(13);

		Assertions.assertThat(money.isNegativeOrZero()).isFalse();
	}

}
