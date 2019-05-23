package de.held.rocksolidapi.user;

import de.held.rocksolidapi.market.Money;
import java.util.Objects;

/**
 * Represents the user.
 */
public class UserEntity {

	private final Inventory inventory = new Inventory();
	private Money money;

	public UserEntity(Money money) {
		this.money = money;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public Money getMoney() {
		return money;
	}

	/**
	 * Subtracts the value from the money and throws if the user has not enough money.
	 *
	 * @param value - The value to subtract.
	 */
	public void subtractMoney(Money value) throws NotEnoughMoneyException {
		Money subtract = money.subtract(value);
		if (subtract.isNegative()) {
			throw new NotEnoughMoneyException();
		}
		money = subtract;
	}

	/**
	 * Adds the value to the users money.
	 *
	 * @param value - The value to add
	 */
	public void addMoney(Money value) {
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
