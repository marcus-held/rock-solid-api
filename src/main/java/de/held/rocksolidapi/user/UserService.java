package de.held.rocksolidapi.user;

import de.held.rocksolidapi.economy.ResourceRepository;
import de.held.rocksolidapi.user.model.UserEntity;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private final ResourceRepository resourceRepository;
	private UserEntity user = new UserEntity(500d);

	public UserService(ResourceRepository resourceRepository) {
		this.resourceRepository = resourceRepository;
	}

	public Map<String, Integer> getReadableInventory() {
		return user.getInventory().getAll().entrySet().stream()
				.collect(Collectors.toMap(
						entry -> resourceRepository.findById(entry.getKey()).getName(),
						Entry::getValue
				));
	}

	public UserEntity getUser() {
		return user;
	}
}
