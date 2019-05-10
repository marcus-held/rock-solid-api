package de.held.rocksolidapi.market;

import de.held.rocksolidapi.user.UserRepository;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MarketService {

	public static final long INFLATION_RATE = 30_000L;

	private final ResourceRepository resourceRepository;
	private final UserRepository userRepository;

	private Date nextInflationDate = new Date();

	public MarketService(ResourceRepository resourceRepository, UserRepository userRepository) {
		this.resourceRepository = resourceRepository;
		this.userRepository = userRepository;
	}

	public void buy(String resourceName, int amount) {
		var resource = resourceRepository.findByName(resourceName);
		var user = userRepository.getUser();

		user.deductMoney(resource.getPrice() * amount);
		user.getInventory().add(resource.getId(), amount);
	}

	public void sell(String resourceName, int amount) {
		var resource = resourceRepository.findByName(resourceName);
		var user = userRepository.getUser();

		user.getInventory().deduct(resource.getId(), amount);
		user.addMoney(resource.getPrice() * amount);
	}

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
