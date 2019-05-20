package de.held.rocksolidapi.market;

import java.math.BigDecimal;
import java.util.UUID;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class ResourcesTest {

	private ResourceFactory resourceFactory;

	private ResourceEntity create(int id) {
		return new ResourceEntity(new ResourceIdVO(id), UUID.randomUUID().toString(), BigDecimal.ONE);
	}

	private ResourceEntity create(String name) {
		return resourceFactory.create(name, "1");
	}

	@Before
	public void setup() {
		resourceFactory = new ResourceFactory();
	}

	@Test
	public void add_empty_resourceAdded() {
		var resources = new Resources();
		var resource = Mockito.mock(ResourceEntity.class);

		resources.add(resource);

		Assertions.assertThat(resources.getResources())
				.containsExactly(resource);
	}

	@Test
	public void add_multipleResources_resourceAdded() {
		var resources = new Resources();
		var resource1 = create(1);
		var resource2 = create(2);

		resources.add(resource1, resource2);

		Assertions.assertThat(resources.getResources())
				.containsExactly(resource1, resource2);
	}

	@Test
	public void add_multipleCalls_resourceAdded() {
		var resources = new Resources();
		var resource1 = create(1);
		var resource2 = create(2);

		resources.add(resource1);
		resources.add(resource2);

		Assertions.assertThat(resources.getResources())
				.containsExactly(resource1, resource2);
	}

	@Test
	public void add_sameId_exception() {
		var resources = new Resources();
		var resource1 = create(1);
		var resource2 = create(1);

		Assertions.assertThatThrownBy(() -> resources.add(resource1, resource2))
				.isInstanceOf(RuntimeException.class);
	}

	@Test
	public void add_sameName_exception() {
		var resources = new Resources();
		var resource1 = create("foo");
		var resource2 = create("foo");

		Assertions.assertThatThrownBy(() -> resources.add(resource1, resource2))
				.isInstanceOf(RuntimeException.class);
	}

}
