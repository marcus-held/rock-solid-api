package de.held.rocksolidapi.user;

import java.util.Objects;

public class UserEntity {

	private final Inventory inventory = new Inventory();
	private double money;

	public UserEntity(double money) {
		this.money = money;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public double getMoney() {
		return money;
	}

	public double getHumanReadableMoney() {
		return Math.round(money * 100.0) / 100.0;
	}

	public void deductMoney(Double value) {
		if (money >= value) {
			money -= value;
		} else {
			throw new IllegalArgumentException("Not enough money to reduce from user.");
		}
	}

	public void addMoney(Double value) {
		money += value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		UserEntity user = (UserEntity) o;
		return Double.compare(user.money, money) == 0 &&
				Objects.equals(inventory, user.inventory);
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
