package de.held.rocksolidapi.user.model;

import de.held.rocksolidapi.economy.ResourceId;
import java.util.HashMap;
import java.util.Map;
import lombok.Value;
import lombok.val;

@Value
public class Inventory {

	private Map<ResourceId, Integer> store = new HashMap<>();

	public Map<ResourceId, Integer> getAll() {
		return Map.copyOf(store);
	}

	public void add(ResourceId resourceId, int amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("Can't add " + amount + " to the inventory.");
		}
		val amountInStore = store.getOrDefault(resourceId, 0);
		store.put(resourceId, amountInStore + amount);
	}

	public void deduct(ResourceId resourceId, int amount) {
		val amountInStore = store.getOrDefault(resourceId, 0);
		val newAmountIntStore = amountInStore - amount;
		if (newAmountIntStore > 0) {
			store.put(resourceId, newAmountIntStore);
		} else if (newAmountIntStore == 0) {
			store.remove(resourceId);
		} else {
			throw new IllegalArgumentException("You don't have enough resources in your inventory");
		}
	}

	public int count(ResourceId resourceId) {
		return store.getOrDefault(resourceId, 0);
	}

}
