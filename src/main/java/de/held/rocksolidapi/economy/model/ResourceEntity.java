package de.held.rocksolidapi.economy.model;

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

	public ResourceIdVO getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
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
		ResourceEntity resource = (ResourceEntity) o;
		return Double.compare(resource.price, price) == 0 &&
				id.equals(resource.id) &&
				name.equals(resource.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, price);
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
