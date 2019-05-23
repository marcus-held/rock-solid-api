package de.held.rocksolidapi.market;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Objects;

public class Money {

	private BigDecimal value;

	public Money(String value) {
		this(new BigDecimal(value));
	}

	public Money(BigDecimal value) {
		this.value = value;
	}

	public Money(int value) {
		this(new BigDecimal(value));
	}

	public String getHumanReadableRoundedDownValue() {
		var format = new DecimalFormat("#0.00", new DecimalFormatSymbols(Locale.ENGLISH));
		format.setRoundingMode(RoundingMode.DOWN);
		return format.format(value);
	}

	public String getHumanReadableRoundedUpValue() {
		var format = new DecimalFormat("#0.00", new DecimalFormatSymbols(Locale.ENGLISH));
		format.setRoundingMode(RoundingMode.UP);
		return format.format(value);
	}

	public Money subtract(Money moneyToSubtract) {
		return new Money(value.subtract(moneyToSubtract.value));
	}

	public Money add(Money moneyToAdd) {
		return new Money(value.add(moneyToAdd.value));
	}

	public boolean isNegative() {
		return value.compareTo(BigDecimal.ZERO) < 0;
	}

	public boolean isNegativeOrZero() {
		return value.compareTo(BigDecimal.ZERO) <= 0;
	}

	public Money multiply(int multiplier) {
		return new Money(value.multiply(new BigDecimal(multiplier)));
	}

	public Money multiply(double multiplier) {
		return new Money(value.multiply(BigDecimal.valueOf(multiplier)));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Money money = (Money) o;
		return Objects.equals(value, money.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}
}
