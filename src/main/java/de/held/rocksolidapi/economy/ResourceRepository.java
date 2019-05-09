package de.held.rocksolidapi.economy;

import de.held.rocksolidapi.economy.model.ResourceAggregate;
import de.held.rocksolidapi.economy.model.ResourceEntity;
import de.held.rocksolidapi.economy.model.ResourceFactory;
import de.held.rocksolidapi.economy.model.ResourceIdVO;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class ResourceRepository {

	private final ResourceFactory resourceFactory;

	private ResourceAggregate store;

	public ResourceRepository(ResourceFactory resourceFactory) {
		this.resourceFactory = resourceFactory;
	}

	@PostConstruct
	public void initResources() {
		store = ResourceAggregate.of(
				resourceFactory.create("comic", 3),
				resourceFactory.create("videogame", 50),
				resourceFactory.create("doll", 15)
				);
	}

	public List<ResourceEntity> findAll() {
		return store.getResources();
	}

	public ResourceEntity findByName(String resourceName) {
		return store.getResources().stream()
				.filter(r -> r.getName().equals(resourceName))
				.findFirst()
				.orElseThrow();
	}

	public ResourceEntity findById(ResourceIdVO id) {
		return store.getResources().stream()
				.filter(r -> r.getId().equals(id))
				.findFirst()
				.orElseThrow();
	}
}
