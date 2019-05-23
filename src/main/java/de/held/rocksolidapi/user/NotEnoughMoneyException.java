package de.held.rocksolidapi.user;

import de.held.rocksolidapi.market.Money;

/**
 * Thrown when the {@link UserEntity} does not have enough money to execute an operation.
 */
public class NotEnoughMoneyException extends Exception {

	private Money currentMoney;

	private Money moneyToSubtract;

}
