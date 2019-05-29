package de.held.rocksolidapi.market;

import de.held.rocksolidapi.user.MoneyVO;
import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Component;

/**
 * Creates new {@link ResourceEntity} and ensures unique {@link ResourceIdVO} for every instance.
 */
@Component
public class ResourceFactory {

	private final ResourceIdGenerator resourceIdGenerator = new ResourceIdGenerator();

	/**
	 * Creates a new {@link ResourceEntity} with a unique id. This method is thread-safe.
	 *
	 * @param name The name of the resource
	 * @param price The price of the resource. The String must be interpretable by
	 * {@link BigDecimal#BigDecimal(String)}. A price must be positive.
	 * @return A new {@link ResourceEntity}
	 */
	ResourceEntity create(String name, String price) {
		return new ResourceEntity(resourceIdGenerator.newId(), name, new MoneyVO(price));
	}

	private class ResourceIdGenerator {

		// Use AtomicInteger to make the generator thread safe.
		private AtomicInteger counter = new AtomicInteger(0);

		ResourceIdVO newId() {
			return new ResourceIdVO(counter.incrementAndGet());
		}
	}

}
