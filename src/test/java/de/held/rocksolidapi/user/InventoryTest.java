package de.held.rocksolidapi.user;

import de.held.rocksolidapi.market.ResourceIdVO;
import de.held.rocksolidapi.user.Inventory;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class InventoryTest {


	@Test
	public void add_noResourceIdYet_resourceIdAdded(){

		var inventory = new Inventory();
		var resourceId = new ResourceIdVO(123);

		inventory.add(resourceId, 1);

		Assertions.assertThat(inventory.count(resourceId)).isEqualTo(1);

	}

	@Test
	public void add_multipleAdd_resourceIdAdded(){

		var inventory = new Inventory();
		var resourceId = new ResourceIdVO(123);

		inventory.add(resourceId, 1);
		inventory.add(resourceId, 2);

		Assertions.assertThat(inventory.count(resourceId)).isEqualTo(3);

	}

	@Test
	public void add_zeroAdded_exception(){

		var inventory = new Inventory();
		var resourceId = new ResourceIdVO(123);

		Assertions.assertThatThrownBy(() -> inventory.add(resourceId, 0))
				.isInstanceOf(RuntimeException.class);

	}

	@Test
	public void add_negativeAdded_exception(){

		var inventory = new Inventory();
		var resourceId = new ResourceIdVO(123);

		Assertions.assertThatThrownBy(() -> inventory.add(resourceId, -1))
				.isInstanceOf(RuntimeException.class);

	}

	@Test
	public void deduct_negativeAmount_exception() {
		var inventory = new Inventory();
		var resourceId = new ResourceIdVO(123);

		Assertions.assertThatThrownBy(() -> inventory.deduct(resourceId, -1))
				.isInstanceOf(RuntimeException.class);
	}

	@Test
	public void deduct_resourceAvailable_resourceDeducted() {
		var inventory = new Inventory();
		var resourceId = new ResourceIdVO(123);

		inventory.add(resourceId, 3);
		inventory.deduct(resourceId, 1);

		Assertions.assertThat(inventory.count(resourceId)).isEqualTo(2);
	}

	@Test
	public void deduct_resourceExactlyAvailable_resourceDeducted() {
		var inventory = new Inventory();
		var resourceId = new ResourceIdVO(123);

		inventory.add(resourceId, 2);
		inventory.deduct(resourceId, 2);

		Assertions.assertThat(inventory.count(resourceId)).isEqualTo(0);
	}

	@Test
	public void deduct_noResourceAvailable_exception() {
		var inventory = new Inventory();
		var resourceId = new ResourceIdVO(123);

		Assertions.assertThatThrownBy(() -> inventory.deduct(resourceId, 1))
				.isInstanceOf(RuntimeException.class);
	}

	@Test
	public void deduct_notEnoughResources_exception() {
		var inventory = new Inventory();
		var resourceId = new ResourceIdVO(123);

		inventory.add(resourceId, 1);

		Assertions.assertThatThrownBy(() -> inventory.deduct(resourceId, 2))
				.isInstanceOf(RuntimeException.class);
	}

}
