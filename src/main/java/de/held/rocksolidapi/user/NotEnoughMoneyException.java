package de.held.rocksolidapi.user;

import de.held.rocksolidapi.market.Money;

/**
 * Thrown when the {@link UserEntity} does not have enough money to execute an operation.
 */
public class NotEnoughMoneyException extends RuntimeException {

	private Money currentMoney;

	private Money moneyToSubtract;

	public NotEnoughMoneyException(Money currentMoney, Money moneyToSubtract) {
		this.currentMoney = currentMoney;
		this.moneyToSubtract = moneyToSubtract;
	}

	public Money getCurrentMoney() {
		return currentMoney;
	}

	public Money getMoneyToSubtract() {
		return moneyToSubtract;
	}
}
