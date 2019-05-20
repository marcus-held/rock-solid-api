package de.held.rocksolidapi.market;

import java.util.Objects;

/**
 * Represents the id of a {@link ResourceEntity}. Gets created by {@link ResourceFactory}.
 */
public class ResourceIdVO {

	private final int id;

	public ResourceIdVO(int id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ResourceIdVO that = (ResourceIdVO) o;
		return id == that.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "ResourceIdVO{" +
				"id=" + id +
				'}';
	}
}
