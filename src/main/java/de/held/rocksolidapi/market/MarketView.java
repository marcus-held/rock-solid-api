package de.held.rocksolidapi.market;

import de.held.rocksolidapi.user.UserEntity;
import de.held.rocksolidapi.user.UserRepository;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

/**
 * Defines all shell operations that are bound
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

	@ShellMethod(key = {"sell"}, value = "Sells the specified resource")
	public void sell(int amount, String resourceName) {
		var user = userRepository.getUser();

		marketService.sell(user, resourceName, amount);
		System.out.println("Sold " + amount + " " + resourceName + ". You have " + user.getHumanReadableMoney()
				+ " money now");
	}

	@ShellMethod(key = {"buy"}, value = "Buys the specified resource")
	public void buy(int amount, String resourceName) {
		var  user = userRepository.getUser();

		marketService.buy(user, resourceName, amount);
		System.out.println("Bought " + amount + " " + resourceName + ". You have " + user.getHumanReadableMoney()
				+ " money now");
	}

	@ShellMethod(key = {"listPrices"}, value = "Lists the current prices of all resources")
	public void listPrices() {
		resourceRepository.findAll()
				.forEach(resource -> System.out.println(resource.getName() + " -> " + resource.getHumanReadablePrice()));
		System.out.println("Hurry, next inflation in " + marketService.secondsToNextInflation() + " seconds");
	}

}
