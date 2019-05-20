package de.held.rocksolidapi.user;

import de.held.rocksolidapi.market.ResourceIdVO;
import java.util.HashMap;
import java.util.Map;

/**
 * The inventory stores and manages the resources of the user.
 */
public class Inventory {

	private final Map<ResourceIdVO, Integer> store = new HashMap<>();

	/**
	 * @return a unmodifiable {@link Map} that maps the resources to their amount that the user owns.
	 */
	public Map<ResourceIdVO, Integer> getAll() {
		return Map.copyOf(store);
	}

	/**
	 * Adds the resource to the inventory.
	 *
	 * @param resourceId - The id of the resource to add.
	 * @param amount - How many of the specified resource should be added. Can only be positive.
	 */
	public void add(ResourceIdVO resourceId, int amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("Can't add " + amount + " to the inventory.");
		}

		var amountInStore = store.getOrDefault(resourceId, 0);
		store.put(resourceId, amountInStore + amount);
	}

	/**
	 * Deducts the resource in the inventory.Throws an exception when not enough resources are owned to remove.
	 *
	 * @param resourceId - The id of the resource to deduct.
	 * @param amount - How many resources should be deducted. Can only be positive.
	 */
	public void deduct(ResourceIdVO resourceId, int amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("Can't deduct " + amount + " in the inventory.");
		}

		var amountInStore = store.getOrDefault(resourceId, 0);
		var newAmountIntStore = amountInStore - amount;
		if (newAmountIntStore > 0) {
			store.put(resourceId, newAmountIntStore);
		} else if (newAmountIntStore == 0) {
			store.remove(resourceId);
		} else {
			throw new IllegalArgumentException("You don't have enough resources in your inventory");
		}
	}

	/**
	 * Counts how many resources of the given id are present in the repository.
	 *
	 * @param resourceId - The id of the resource to count.
	 * @return - How many resources of the given id are in the inventory.
	 */
	public int count(ResourceIdVO resourceId) {
		return store.getOrDefault(resourceId, 0);
	}

}
