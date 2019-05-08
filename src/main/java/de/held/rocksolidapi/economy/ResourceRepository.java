package de.held.rocksolidapi.economy;

import de.held.rocksolidapi.economy.model.Resource;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ResourceRepository {

	private List<Resource> resources = new ArrayList<>(List.of(
			new Resource(new ResourceId(1), "comic", 3),
			new Resource(new ResourceId(2), "videogame", 50),
			new Resource(new ResourceId(3), "doll", 15)
	));

	public List<Resource> findAll() {
		return List.copyOf(resources);
	}

	public Resource findByName(String name) {
		return resources.stream()
				.filter(r -> r.getName().equals(name))
				.findFirst()
				.orElseThrow();
	}

	public Resource findById(ResourceId id) {
		return resources.stream()
				.filter(r -> r.getId().equals(id))
				.findFirst()
				.orElseThrow();
	}

	public void save(Resource resource) {
		resources.stream()
				.filter(r -> r.getId().equals(resource.getId()))
				.findFirst()
				.ifPresentOrElse(
						r -> resources.set(resources.indexOf(r), resource),
						() -> resources.add(resource)
				);
	}

}
