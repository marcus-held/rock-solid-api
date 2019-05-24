package de.held.rocksolidapi.market;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Objects;

/**
 * Value object of a decimal number representing money. Will do precise arithmetic on the value it represents. The
 * represented value is always scaled to two decimal points and rounded up in doubt.
 */
public class Money {

	private BigDecimal value;

	public Money(String value) {
		this(new BigDecimal(value).setScale(2, RoundingMode.UP));
	}

	public Money(BigDecimal value) {
		this.value = value;
	}

	public Money(int value) {
		this(Integer.toString(value));
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

	/**
	 * Multiplies the {@link Money}  with the provided value. Since multiplier is a double the result is not an exact
	 * arithmetic.
	 *
	 * @param multiplier The value to multiply with
	 * @return A new instance of {@link Money} with the result.
	 * @see BigDecimal#valueOf(double)
	 */
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

	@Override
	public String toString() {
		var format = new DecimalFormat("#0.00", new DecimalFormatSymbols(Locale.ENGLISH));
		return format.format(value);
	}
}
