package de.held.rocksolidapi.market;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class ResourceFactoryTest {

	@Test
	public void create_multipleCalls_uniqueId() {
		var factory = new ResourceFactory();

		var resource1 = factory.create("foo", "2");
		var resource2 = factory.create("foo", "2");

		Assertions.assertThat(resource1.getId()).isNotEqualTo(resource2.getId());
	}
}
