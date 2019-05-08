package de.held.rocksolidapi.economy;

import java.time.Duration;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class EconomyView {

	private final ResourceService resourceService;
	private final ResourceRepository resourceRepository;

	@ShellMethod(key = {"listPrices"}, value = "Lists the current prices of all resources")
	public void listPrices() {
		resourceRepository.findAll()
				.forEach(resource -> System.out.println(resource.getName() + " -> " + resource.getPrice()));
		var nextInflation = Duration.between(Instant.now(), resourceService.getNextInflationDate().toInstant()).toSeconds();
		System.out.println("Hurry, next inflation in " + nextInflation + " seconds");
	}

}