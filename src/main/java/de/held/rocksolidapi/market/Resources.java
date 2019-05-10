package de.held.rocksolidapi.market;

import java.util.List;
import java.util.Objects;

public class Resources {

	private final List<ResourceEntity> resources;

	public static Resources of(ResourceEntity... resources) {
		return new Resources(List.of(resources));
	}

	private Resources(List<ResourceEntity> resources) {
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
		Resources that = (Resources) o;
		return Objects.equals(resources, that.resources);
	}

	@Override
	public int hashCode() {
		return Objects.hash(resources);
	}

	@Override
	public String toString() {
		return "Resources{" +
				"resources=" + resources +
				'}';
	}
}
