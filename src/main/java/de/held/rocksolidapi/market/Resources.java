package de.held.rocksolidapi.market;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Aggregate of {@link ResourceEntity} that ensures that only resources are aggregated with a unique id and name.
 */
public class Resources {

	private final List<ResourceEntity> resources = new ArrayList<>();

	/**
	 * @return an unmodifiable list of all resources.
	 */
	public List<ResourceEntity> getResources() {
		return Collections.unmodifiableList(resources);
	}

	/**
	 * Adds the given resources to the internal store and throws an exception if one of the resources id or name is
	 * already present.
	 *
	 * @param resourcesToAdd - The resources to add.
	 */
	public void add(ResourceEntity... resourcesToAdd) {
		Stream.of(resourcesToAdd).forEach(resourceEntity -> {
			if (hasUniqueId(resourceEntity.getId()) && hasUniqueName(resourceEntity.getName())) {
				resources.add(resourceEntity);
			} else {
				throw new IllegalArgumentException("Can not add " + resourceEntity + " because its not unique.");
			}
		});
	}

	private boolean hasUniqueName(String name) {
		return resources.stream()
				.noneMatch(storedResource -> storedResource.getName().equals(name));
	}

	private boolean hasUniqueId(ResourceIdVO id) {
		return resources.stream()
				.noneMatch(storedResource -> storedResource.getId().equals(id));
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
