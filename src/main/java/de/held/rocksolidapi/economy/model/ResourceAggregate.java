package de.held.rocksolidapi.economy.model;

import java.util.List;
import java.util.Objects;

public class ResourceAggregate {

	private final List<ResourceEntity> resources;

	public static ResourceAggregate of(ResourceEntity... resources) {
		return new ResourceAggregate(List.of(resources));
	}

	private ResourceAggregate(List<ResourceEntity> resources) {
		this.resources = resources;
	}

	public List<ResourceEntity> getResources() {
		return resources;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ResourceAggregate that = (ResourceAggregate) o;
		return Objects.equals(resources, that.resources);
	}

	@Override
	public int hashCode() {
		return Objects.hash(resources);
	}

	@Override
	public String toString() {
		return "ResourceAggregate{" +
				"resources=" + resources +
				'}';
	}
}
