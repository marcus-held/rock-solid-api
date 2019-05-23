package de.held.rocksolidapi.market;

import java.math.BigDecimal;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

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

	@Test
	public void getHumanReadableRoundedUpValue_noDecimal_twoDecimalPlacesWithZero() {
		var money = new Money("1");

		Assertions.assertThat(money.getHumanReadableRoundedUpValue())
				.isEqualTo("1.00");
	}

	@Test
	public void getHumanReadableRoundedUpValue_singleDecimalPlaces_twoDecimalPlacesWithZero() {
		var money = new Money("1.1");

		Assertions.assertThat(money.getHumanReadableRoundedUpValue())
				.isEqualTo("1.10");
	}

	@Test
	public void getHumanReadableRoundedUpValue_twoDecimalPlacesWithZero_twoDecimalPlacesWithZero() {
		var money = new Money("1.10");

		Assertions.assertThat(money.getHumanReadableRoundedUpValue())
				.isEqualTo("1.10");
	}

	@Test
	public void getHumanReadableRoundedUpValue_twoDecimalPlaces_twoDecimalPlaces() {
		var money = new Money("1.12");

		Assertions.assertThat(money.getHumanReadableRoundedUpValue())
				.isEqualTo("1.12");
	}

	@Test
	public void getHumanReadableRoundedUpValue_threeDecimalPlaces_twoDecimalPlacesRoundedUp() {
		var money = new Money("1.121");

		Assertions.assertThat(money.getHumanReadableRoundedUpValue())
				.isEqualTo("1.13");
	}

	@Test
	public void getHumanReadableRoundedDownValue_noDecimal_twoDecimalPlacesWithZero() {
		var money = new Money("1");

		Assertions.assertThat(money.getHumanReadableRoundedDownValue())
				.isEqualTo("1.00");
	}

	@Test
	public void getHumanReadableRoundedDownValue_singleDecimalPlaces_twoDecimalPlacesWithZero() {
		var money = new Money("1.1");

		Assertions.assertThat(money.getHumanReadableRoundedDownValue())
				.isEqualTo("1.10");
	}

	@Test
	public void getHumanReadableRoundedDownValue_twoDecimalPlacesWithZero_twoDecimalPlacesWithZero() {
		var money = new Money("1.10");

		Assertions.assertThat(money.getHumanReadableRoundedDownValue())
				.isEqualTo("1.10");
	}

	@Test
	public void getHumanReadableRoundedDownValue_twoDecimalPlaces_twoDecimalPlaces() {
		var money = new Money("1.12");

		Assertions.assertThat(money.getHumanReadableRoundedDownValue())
				.isEqualTo("1.12");
	}

	@Test
	public void getHumanReadableRoundedDownValue_threeDecimalPlaces_twoDecimalPlacesRoundedUp() {
		var money = new Money("1.121");

		Assertions.assertThat(money.getHumanReadableRoundedDownValue())
				.isEqualTo("1.12");
	}

}
