package de.held.rocksolidapi.market;

import de.held.rocksolidapi.economy.ResourceRepository;
import de.held.rocksolidapi.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class MarketService {

	private final ResourceRepository resourceRepository;
	private final UserService userService;

	public MarketService(ResourceRepository resourceRepository, UserService userService) {
		this.resourceRepository = resourceRepository;
		this.userService = userService;
	}

	public void buy(String resourceName, int amount) {
		var resource = resourceRepository.findByName(resourceName);
		var user = userService.getUser();

		user.deductMoney(resource.getPrice() * amount);
		user.getInventory().add(resource.getId(), amount);
	}

	public void sell(String resourceName, int amount) {
		var resource = resourceRepository.findByName(resourceName);
		var user = userService.getUser();

		user.getInventory().deduct(resource.getId(), amount);
		user.addMoney(resource.getPrice() * amount);
	}

}
