package de.held.rocksolidapi.market;

import de.held.rocksolidapi.user.UserEntity;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Central logic that operates on the market. That includes changing prices of {@link Resources}.
 */
@Service
public class MarketService {

	public static final long INFLATION_RATE = 30_000L;

	private final ResourceRepository resourceRepository;

	private Date nextInflationDate = new Date();

	public MarketService(ResourceRepository resourceRepository) {
		this.resourceRepository = resourceRepository;
	}

	/**
	 * Deducts the money that is necessary to buy the resource with the given name and adds it to the users
	 * {@link de.held.rocksolidapi.user.Inventory}.
	 */
	public void buy(UserEntity user, String resourceName, int amount) {
		var resource = resourceRepository.findByName(resourceName);

		user.deductMoney(resource.getPrice() * amount);
		user.getInventory().add(resource.getId(), amount);
	}

	/**
	 * Removes the resource with the given name from the inventory of the player and adds the price of them to the
	 * users money.
	 */
	public void sell(UserEntity user, String resourceName, int amount) {
		var resource = resourceRepository.findByName(resourceName);

		user.getInventory().deduct(resource.getId(), amount);
		user.addMoney(resource.getPrice() * amount);
	}

	/**
	 * Returns the duration in seconds between the current system time and the next scheduled inflation.
	 */
	public long secondsToNextInflation() {
		return Duration.between(Instant.now(), nextInflationDate.toInstant()).toSeconds();
	}

	@Scheduled(fixedDelay = INFLATION_RATE)
	private void inflation() {
		resourceRepository.findAll().forEach(resource -> {
			var inflation = ThreadLocalRandom.current().nextDouble(0.3) + 0.9;
			resource.setPrice(resource.getPrice() * inflation);
		});

		nextInflationDate = Date.from(Instant.now().plus(INFLATION_RATE, ChronoUnit.MILLIS));
	}

}
