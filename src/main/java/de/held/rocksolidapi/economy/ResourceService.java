package de.held.rocksolidapi.economy;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ResourceService {

	public static final long INFLATION_RATE = 30_000L;

	private final ResourceRepository resourceRepository;
	private Date nextInflationDate = new Date();

	public ResourceService(ResourceRepository resourceRepository) {
		this.resourceRepository = resourceRepository;
	}

	public Date getNextInflationDate() {
		return nextInflationDate;
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
