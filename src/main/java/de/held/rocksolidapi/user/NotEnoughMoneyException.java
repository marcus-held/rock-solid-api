package de.held.rocksolidapi.user;

/**
 * Thrown when the {@link UserEntity} does not have enough money to execute an operation.
 */
public class NotEnoughMoneyException extends RuntimeException {

	private MoneyVO currentMoney;

	private MoneyVO moneyToSubtract;

	public NotEnoughMoneyException(MoneyVO currentMoney, MoneyVO moneyToSubtract) {
		this.currentMoney = currentMoney;
		this.moneyToSubtract = moneyToSubtract;
	}

	public MoneyVO getCurrentMoney() {
		return currentMoney;
	}

	public MoneyVO getMoneyToSubtract() {
		return moneyToSubtract;
	}
}
