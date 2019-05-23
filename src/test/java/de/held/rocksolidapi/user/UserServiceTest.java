package de.held.rocksolidapi.user;

import de.held.rocksolidapi.market.ResourceEntity;
import de.held.rocksolidapi.market.ResourceIdVO;
import de.held.rocksolidapi.market.ResourceNotFoundException;
import de.held.rocksolidapi.market.ResourceRepository;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class UserServiceTest {

	@Mock
	ResourceRepository resourceRepository;
	@Mock
	UserRepository userRepository;

	@InjectMocks
	UserService userService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getReadableInventory_emptyInventory_emptyMap() {
		mockUserWithResources(Map.of());

		Map<String, Integer> readableInventory = userService.getReadableInventory();

		Assertions.assertThat(readableInventory).isEmpty();
	}

	@Test
	public void getReadableInventory_singleResource_resourceWithName() throws ResourceNotFoundException {
		int resourceAmount = 1;
		String resourceName = "foo";

		var resourceId = new ResourceIdVO(123);
		mockUserWithResources(Map.of(resourceId, resourceAmount));

		var resource = mock(ResourceEntity.class);
		when(resource.getName()).thenReturn(resourceName);

		when(resourceRepository.findById(resourceId)).thenReturn(resource);

		Map<String, Integer> readableInventory = userService.getReadableInventory();

		Assertions.assertThat(readableInventory).containsOnly(Map.entry(resourceName, resourceAmount));
	}

	private void mockUserWithResources(Map<ResourceIdVO, Integer> resources) {
		var user = mock(UserEntity.class);
		var inventory = mock(Inventory.class);
		when(inventory.getAll()).thenReturn(resources);
		when(user.getInventory()).thenReturn(inventory);
		when(userRepository.getUser()).thenReturn(user);
	}
}
