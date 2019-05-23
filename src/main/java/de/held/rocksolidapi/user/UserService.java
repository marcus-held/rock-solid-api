package de.held.rocksolidapi.user;

import de.held.rocksolidapi.market.ResourceNotFoundException;
import de.held.rocksolidapi.market.ResourceRepository;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 * Performs operations on the {@link UserEntity} of the user of the application.
 */
@Service
public class UserService {

	private final ResourceRepository resourceRepository;
	private final UserRepository userRepository;

	public UserService(ResourceRepository resourceRepository, UserRepository userRepository) {
		this.resourceRepository = resourceRepository;
		this.userRepository = userRepository;
	}

	/**
	 * @return a map of the name of the resources and their amount that the player owns.
	 */
	public Map<String, Integer> getReadableInventory() {
		return userRepository.getUser().getInventory().getAll().entrySet().stream()
				.collect(Collectors.toMap(
						entry -> {
							try {
								return resourceRepository.findById(entry.getKey()).getName();
							} catch (ResourceNotFoundException e) {
								throw new IllegalArgumentException();
							}
						},
						Entry::getValue
				));
	}

}
