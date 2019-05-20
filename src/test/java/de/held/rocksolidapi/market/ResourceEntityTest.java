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
		return new ResourceEntity(new ResourceIdVO(1), "foo", new BigDecimal(price));
	}

	private ResourceEntity create() {
		return create("1.1");
	}

	@Test
	public void getHumanReadablePrice_noDecimal_twoDecimalPlacesWithZero() {
		ResourceEntity resource = create("1");

		Assertions.assertThat(resource.getHumanReadablePrice())
				.isEqualTo("1.00");
	}

	@Test
	public void getHumanReadablePrice_singleDecimalPlaces_twoDecimalPlacesWithZero() {
		ResourceEntity resource = create("1.1");

		Assertions.assertThat(resource.getHumanReadablePrice()).
				isEqualTo("1.10");
	}

	@Test
	public void getHumanReadablePrice_twoDecimalPlacesWithZero_twoDecimalPlacesWithZero() {
		ResourceEntity resource = create("1.10");

		Assertions.assertThat(resource.getHumanReadablePrice()).
				isEqualTo("1.10");
	}

	@Test
	public void getHumanReadablePrice_twoDecimalPlaces_twoDecimalPlaces() {
		ResourceEntity resource = create("1.12");

		Assertions.assertThat(resource.getHumanReadablePrice())
				.isEqualTo("1.12");
	}

	@Test
	public void getHumanReadablePrice_threeDecimalPlaces_twoDecimalPlacesRoundedUp() {
		ResourceEntity resource = create("1.121");

		Assertions.assertThat(resource.getHumanReadablePrice())
				.isEqualTo("1.13");
	}

	@Test
	public void setPrice_positive_priceSet() {
		ResourceEntity resource = create();

		BigDecimal newPrice = new BigDecimal("2.2");
		resource.setPrice(newPrice);

		Assertions.assertThat(resource.getPrice())
				.isEqualTo(newPrice);
	}

	@Test
	public void setPrice_zero_exception() {
		ResourceEntity resource = create();

		BigDecimal newPrice = BigDecimal.ZERO;

		Assertions.assertThatThrownBy(() -> resource.setPrice(newPrice)).isInstanceOf(RuntimeException.class);
	}

	@Test
	public void setPrice_negative_exception() {
		ResourceEntity resource = create();

		BigDecimal newPrice = new BigDecimal("-5");

		Assertions.assertThatThrownBy(() -> resource.setPrice(newPrice)).isInstanceOf(RuntimeException.class);
	}

}
