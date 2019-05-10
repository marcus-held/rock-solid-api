package de.held.rocksolidapi.user;

import de.held.rocksolidapi.market.ResourceRepository;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private final ResourceRepository resourceRepository;
	private final UserRepository userRepository;

	public UserService(ResourceRepository resourceRepository, UserRepository userRepository) {
		this.resourceRepository = resourceRepository;
		this.userRepository = userRepository;
	}

	public Map<String, Integer> getReadableInventory() {
		return userRepository.getUser().getInventory().getAll().entrySet().stream()
				.collect(Collectors.toMap(
						entry -> resourceRepository.findById(entry.getKey()).getName(),
						Entry::getValue
				));
	}

}
