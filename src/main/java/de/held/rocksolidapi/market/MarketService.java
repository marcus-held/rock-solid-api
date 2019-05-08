package de.held.rocksolidapi.market;

import de.held.rocksolidapi.economy.ResourceRepository;
import de.held.rocksolidapi.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MarketService {

	private final ResourceRepository resourceRepository;
	private final UserService userService;

	public void buy(String resourceName, int amount) {
		val resource = resourceRepository.findByName(resourceName);
		val user = userService.getUser();

		user.deductMoney(resource.getPrice() * amount);
		user.getInventory().add(resource.getId(), amount);
	}

	public void sell(String resourceName, int amount) {
		val resource = resourceRepository.findByName(resourceName);
		val user = userService.getUser();

		user.getInventory().deduct(resource.getId(), amount);
		user.addMoney(resource.getPrice() * amount);
	}

}
