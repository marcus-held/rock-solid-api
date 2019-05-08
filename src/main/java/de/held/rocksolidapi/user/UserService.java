package de.held.rocksolidapi.user;

import de.held.rocksolidapi.economy.ResourceRepository;
import de.held.rocksolidapi.user.model.User;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

	private final ResourceRepository resourceRepository;

	@Getter
	private User user = new User(500d);

	public Map<String, Integer> getReadableInventory() {
		return user.getInventory().getAll().entrySet().stream()
				.collect(Collectors.toMap(
						entry -> resourceRepository.findById(entry.getKey()).getName(),
						Entry::getValue
				));
	}

}
