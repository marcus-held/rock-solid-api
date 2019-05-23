package de.held.rocksolidapi.market;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Objects;

/**
 * Represents a resource. Every resource has a unique {@link ResourceIdVO}.
 * <p>
 * Use {@link ResourceFactory} to generate new instances.
 */
public class ResourceEntity {

	private final ResourceIdVO id;

	private final String name;
	private Money price;

	ResourceEntity(ResourceIdVO id, String name, Money price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	ResourceIdVO getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	Money getPrice() {
		return price;
	}

	/**
	 * Sets the price. The price mustn't be negative or zero.
	 *
	 * @param price - The new price of the resource.
	 */
	void setPrice(Money price) {
		if (price.isNegativeOrZero()) {
			throw new IllegalArgumentException();
		}
		this.price = price;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ResourceEntity that = (ResourceEntity) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "ResourceEntity{" +
				"id=" + id +
				", name='" + name + '\'' +
				", price=" + price +
				'}';
	}
}
