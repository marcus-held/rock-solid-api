package de.held.rocksolidapi.market;

import de.held.rocksolidapi.user.UserEntity;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Central logic that operates on the market. Performs an inflation every {@value INFLATION_RATE_IN_SECONDS} seconds.
 */
@Service
public class MarketService {

	public static final long INFLATION_RATE_IN_SECONDS = 30;

	private final ResourceRepository resourceRepository;

	private Date nextInflationDate = new Date();

	public MarketService(ResourceRepository resourceRepository) {
		this.resourceRepository = resourceRepository;
	}

	/**
	 * Deducts the money that is necessary to buy the resource with the given name and adds it to the users
	 * {@link de.held.rocksolidapi.user.Inventory}.
	 *
	 * @param user - The user that wants to buy the {@link ResourceEntity}.
	 * @param resourceName - The name of the {@link ResourceEntity} the user wants to buy.
	 * @param amount - How many {@link ResourceEntity} of the given name the user wants to buy.
	 */
	public void buy(UserEntity user, String resourceName, int amount) {
		var resource = resourceRepository.findByName(resourceName);

		user.subtractMoney(resource.getPrice().multiply(BigDecimal.valueOf(amount)));
		user.getInventory().add(resource.getId(), amount);
	}

	/**
	 * Removes the resource with the given name from the inventory of the player and adds the price of them to the
	 * users money.
	 *
	 * @param user - The user that wants to sell the {@link ResourceEntity}.
	 * @param resourceName - The name of the {@link ResourceEntity}  the user wants to sell.
	 * @param amount - How many {@link ResourceEntity} the user wants to sell.
	 */
	public void sell(UserEntity user, String resourceName, int amount) {
		var resource = resourceRepository.findByName(resourceName);

		user.getInventory().deduct(resource.getId(), amount);
		user.addMoney(resource.getPrice().multiply(BigDecimal.valueOf(amount)));
	}

	/**
	 * Returns the duration in seconds between the current system time and the next scheduled inflation.
	 */
	public long secondsToNextInflation() {
		return Duration.between(Instant.now(), nextInflationDate.toInstant()).toSeconds();
	}

	@Scheduled(fixedDelay = INFLATION_RATE_IN_SECONDS * 1000)
	private void inflation() {
		resourceRepository.findAll().forEach(resource -> {
			var inflation = ThreadLocalRandom.current().nextDouble(0.3) + 0.9;
			resource.setPrice(resource.getPrice().multiply(BigDecimal.valueOf(inflation)));
		});

		nextInflationDate = Date.from(Instant.now().plus(INFLATION_RATE_IN_SECONDS, ChronoUnit.SECONDS));
	}

}
