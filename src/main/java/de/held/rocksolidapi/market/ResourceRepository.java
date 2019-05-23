package de.held.rocksolidapi.market;

import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

/**
 * Stores and gives access to {@link ResourceEntity}s. Creates default resources that are effectively static in
 * {@link ResourceRepository#initResources()}.
 * <p>
 * The stored resources mustn't have the same id or name.
 */
@Component
public class ResourceRepository {

	private final ResourceFactory resourceFactory;

	private Resources store = new Resources();

	public ResourceRepository(ResourceFactory resourceFactory) {
		this.resourceFactory = resourceFactory;
	}

	/**
	 * Initializes the repository with default {@link ResourceEntity}s.
	 */
	@PostConstruct
	public void initResources() {
		store.add(
				resourceFactory.create("comic", "3"),
				resourceFactory.create("videogame", "50"),
				resourceFactory.create("doll", "15")
		);
	}

	/**
	 * Returns a list of all {@link ResourceEntity}s in the repository. The list has not guaranteed order. Returns an
	 * empty list when no resource is stored.
	 *
	 * @return - A list of all {@link ResourceEntity}s.
	 */
	public List<ResourceEntity> findAll() {
		return store.getResources();
	}

	/**
	 * Returns the {@link ResourceEntity} with the given name.
	 *
	 * @param resourceName - The name of the resource to find.
	 * @return - The {@link ResourceEntity} with the given name.
	 */
	public ResourceEntity findByName(String resourceName) throws ResourceNotFoundException {
		return store.getResources().stream()
				.filter(r -> r.getName().equals(resourceName))
				.findFirst()
				.orElseThrow(ResourceNotFoundException::new);
	}

	/**
	 * Returns the {@link ResourceEntity} with the given {@link ResourceIdVO}.
	 *
	 * @param id - The id of the resource to find.
	 * @return - The {@link ResourceEntity} with the given id.
	 */
	public ResourceEntity findById(ResourceIdVO id) throws ResourceNotFoundException {
		return store.getResources().stream()
				.filter(r -> r.getId().equals(id))
				.findFirst()
				.orElseThrow(ResourceNotFoundException::new);
	}
}
