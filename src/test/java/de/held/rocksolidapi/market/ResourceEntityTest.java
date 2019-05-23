package de.held.rocksolidapi.market;

import java.math.BigDecimal;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class ResourceEntityTest {

	private ResourceEntity create(String price) {
		return new ResourceEntity(new ResourceIdVO(1), "foo", new Money(price));
	}

	private ResourceEntity create() {
		return create("1.1");
	}

	@Test
	public void setPrice_positive_priceSet() {
		ResourceEntity resource = create();

		Money newPrice = new Money("2.2");
		resource.setPrice(newPrice);

		Assertions.assertThat(resource.getPrice())
				.isEqualTo(newPrice);
	}

	@Test
	public void setPrice_zero_exception() {
		ResourceEntity resource = create();

		Money newPrice = new Money(0);

		Assertions.assertThatThrownBy(() -> resource.setPrice(newPrice)).isInstanceOf(RuntimeException.class);
	}

	@Test
	public void setPrice_negative_exception() {
		ResourceEntity resource = create();

		Money newPrice = new Money("-5");

		Assertions.assertThatThrownBy(() -> resource.setPrice(newPrice)).isInstanceOf(RuntimeException.class);
	}

}
