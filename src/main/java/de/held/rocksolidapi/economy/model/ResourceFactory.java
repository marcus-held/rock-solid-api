package de.held.rocksolidapi.economy.model;

import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Component;

@Component
public class ResourceFactory {

	private final ResourceIdGenerator resourceIdGenerator = new ResourceIdGenerator();

	public ResourceEntity create(String name, double price) {
		return new ResourceEntity(resourceIdGenerator.newId(), name, price);
	}

	private class ResourceIdGenerator {

		private AtomicInteger counter = new AtomicInteger(0);

		ResourceIdVO newId() {
			return new ResourceIdVO(counter.incrementAndGet());
		}
	}

}
