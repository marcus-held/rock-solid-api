package de.held.rocksolidapi.user;

import de.held.rocksolidapi.market.ResourceIdVO;
import java.util.HashMap;
import java.util.Map;

public class Inventory {

	private final Map<ResourceIdVO, Integer> store = new HashMap<>();

	public Map<ResourceIdVO, Integer> getAll() {
		return Map.copyOf(store);
	}

	public void add(ResourceIdVO resourceId, int amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("Can't add " + amount + " to the inventory.");
		}
		var amountInStore = store.getOrDefault(resourceId, 0);
		store.put(resourceId, amountInStore + amount);
	}

	public void deduct(ResourceIdVO resourceId, int amount) {
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

	public int count(ResourceIdVO resourceId) {
		return store.getOrDefault(resourceId, 0);
	}

}
