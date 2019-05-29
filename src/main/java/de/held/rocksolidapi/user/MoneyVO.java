package de.held.rocksolidapi.user;

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
public class MoneyVO {

	private BigDecimal value;

	public MoneyVO(String value) {
		this(new BigDecimal(value).setScale(2, RoundingMode.UP));
	}

	public MoneyVO(BigDecimal value) {
		this.value = value;
	}

	public MoneyVO(int value) {
		this(Integer.toString(value));
	}

	public MoneyVO subtract(MoneyVO moneyToSubtract) {
		return new MoneyVO(value.subtract(moneyToSubtract.value));
	}

	public MoneyVO add(MoneyVO moneyToAdd) {
		return new MoneyVO(value.add(moneyToAdd.value));
	}

	public boolean isNegative() {
		return value.compareTo(BigDecimal.ZERO) < 0;
	}

	public boolean isNegativeOrZero() {
		return value.compareTo(BigDecimal.ZERO) <= 0;
	}

	public MoneyVO multiply(int multiplier) {
		return new MoneyVO(value.multiply(new BigDecimal(multiplier)));
	}

	/**
	 * Multiplies the {@link MoneyVO}  with the provided value. Since multiplier is a double the result is not applying
	 * exact arithmetic.
	 *
	 * @param multiplier The value to multiply with
	 * @return A new instance of {@link MoneyVO} with the result.
	 * @see BigDecimal#valueOf(double)
	 */
	public MoneyVO multiply(double multiplier) {
		return new MoneyVO(value.multiply(BigDecimal.valueOf(multiplier)));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		MoneyVO money = (MoneyVO) o;
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
