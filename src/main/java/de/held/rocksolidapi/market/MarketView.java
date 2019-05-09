package de.held.rocksolidapi.market;

import de.held.rocksolidapi.user.UserService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class MarketView {

	private final MarketService marketService;
	private final UserService userService;

	public MarketView(MarketService marketService, UserService userService) {
		this.marketService = marketService;
		this.userService = userService;
	}

	@ShellMethod(key = {"sell"}, value = "Sells the specified resource")
	public void sell(int amount, String resourceName) {
		marketService.sell(resourceName, amount);
		System.out.println("Sold " + amount + " " + resourceName + ". You have " + userService.getUser().getMoney()
				+ " money now");
	}

	@ShellMethod(key = {"buy"}, value = "Buys the specified resource")
	public void buy(int amount, String resourceName) {
		marketService.buy(resourceName, amount);
		System.out.println("Bought " + amount + " " + resourceName + ". You have " + userService.getUser().getMoney()
				+ " money now");
	}

}
