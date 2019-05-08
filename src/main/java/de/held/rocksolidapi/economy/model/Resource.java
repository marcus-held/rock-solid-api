package de.held.rocksolidapi.economy.model;

import de.held.rocksolidapi.economy.ResourceId;
import lombok.Value;

@Value
public class Resource {

	private ResourceId id;
	private String name;
	private double price;

}
