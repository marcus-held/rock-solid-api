package de.held.rocksolidapi.user.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@Getter
@ToString
public class User {

	private double money;
	private Inventory inventory = new Inventory();

	public User(double money) {
		this.money = money;
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

}
