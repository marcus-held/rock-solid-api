package de.held.rocksolidapi.economy;

import de.held.rocksolidapi.economy.model.Resource;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResourceService {

	public static final long INFLATION_RATE = 30_000L;

	private final ResourceRepository resourceRepository;

	@Getter
	private Date nextInflationDate = new Date();

	@Scheduled(fixedDelay = INFLATION_RATE)
	private void inflation() {
		resourceRepository.findAll().forEach(resource -> {
			val inflation = ThreadLocalRandom.current().nextDouble(0.3) + 0.9;
			Resource newResource = new Resource(resource.getId(), resource.getName(), resource.getPrice() * inflation);
			resourceRepository.save(newResource);
		});

		nextInflationDate = Date.from(Instant.now().plus(INFLATION_RATE, ChronoUnit.MILLIS));
	}

}
