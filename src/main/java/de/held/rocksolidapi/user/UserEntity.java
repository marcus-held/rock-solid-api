package de.held.rocksolidapi.user;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Objects;

/**
 * Represents the user.
 */
public class UserEntity {

	private final Inventory inventory = new Inventory();
	private BigDecimal money;

	public UserEntity(BigDecimal money) {
		this.money = money;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public BigDecimal getMoney() {
		return money;
	}

	/**
	 * @return the current money of the player with two decimal places. The value is always rounded down.
	 */
	public String getHumanReadableMoney() {
		var format = new DecimalFormat("#0.00", new DecimalFormatSymbols(Locale.ENGLISH));
		format.setRoundingMode(RoundingMode.DOWN);
		return format.format(money);
	}

	/**
	 * Subtracts the value from the money and throws if the user has not enough money.
	 *
	 * @param value - The value to subtract.
	 */
	public void subtractMoney(BigDecimal value) {
		BigDecimal subtract = money.subtract(value);
		if (subtract.compareTo(BigDecimal.ZERO) >= 0) {
			money = subtract;
		} else {
			throw new IllegalArgumentException("Not enough money to reduce from user.");
		}
	}

	/**
	 * Adds the value to the users money.
	 *
	 * @param value - The value to add
	 */
	public void addMoney(BigDecimal value) {
		money = money.add(value);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		UserEntity that = (UserEntity) o;
		return Objects.equals(inventory, that.inventory) &&
				Objects.equals(money, that.money);
	}

	@Override
	public int hashCode() {
		return Objects.hash(inventory, money);
	}

	@Override
	public String toString() {
		return "UserEntity{" +
				"inventory=" + inventory +
				", money=" + money +
				'}';
	}
}
