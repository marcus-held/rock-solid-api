package de.held.rocksolidapi.market;

import de.held.rocksolidapi.user.UserRepository;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

/**
 * Defines the shell operations that can be executed on the market.
 */
@ShellComponent
public class MarketView {

	private final MarketService marketService;
	private final UserRepository userRepository;
	private final ResourceRepository resourceRepository;

	public MarketView(ResourceRepository resourceRepository, MarketService marketService,
			UserRepository userRepository) {
		this.resourceRepository = resourceRepository;
		this.marketService = marketService;
		this.userRepository = userRepository;
	}

	/**
	 * Sells the resources with the given name to the market. Will print how much money the user has when the actions
	 * was successful.
	 *
	 * @param amount - How many of the resource should be sold
	 * @param resourceName - The name of the resource to sell
	 */
	@ShellMethod(key = {"sell"}, value = "Sells the specified resource")
	public void sell(int amount, String resourceName) {
		var user = userRepository.getUser();

		marketService.sell(user, resourceName, amount);
		System.out.println("Sold " + amount + " " + resourceName + ". You have " + user.getHumanReadableMoney()
				+ " money now");
	}

	/**
	 * Buys the resource with the given name from the market. Will print how much money the user has when the actions
	 * was successful.
	 *
	 * @param amount - How many of the resource should be bought
	 * @param resourceName - The name of the resource to buy
	 */
	@ShellMethod(key = {"buy"}, value = "Buys the specified resource")
	public void buy(int amount, String resourceName) {
		var user = userRepository.getUser();

		marketService.buy(user, resourceName, amount);
		System.out.println("Bought " + amount + " " + resourceName + ". You have " + user.getHumanReadableMoney()
				+ " money now");
	}

	/**
	 * Prints the current prices of all resources as well as when the next inflation will happen.
	 */
	@ShellMethod(key = {"listPrices"}, value = "Lists the current prices of all resources")
	public void listPrices() {
		resourceRepository.findAll()
				.forEach(
						resource -> System.out.println(resource.getName() + " -> " + resource.getHumanReadablePrice()));
		System.out.println("Hurry, next inflation in " + marketService.secondsToNextInflation() + " seconds");
	}

}
