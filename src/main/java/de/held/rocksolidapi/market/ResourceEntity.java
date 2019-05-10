package de.held.rocksolidapi.market;

import java.util.Objects;

public class ResourceEntity {

	private final ResourceIdVO id;

	private final String name;
	private double price;

	ResourceEntity(ResourceIdVO id, String name, double price) {
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

	double getPrice() {
		return price;
	}

	void setPrice(double price) {
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
