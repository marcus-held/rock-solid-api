package de.held.rocksolidapi.user;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class UserView {

	private final UserService userService;

	public UserView(UserService userService) {
		this.userService = userService;
	}

	@ShellMethod(key = {"info"}, value = "Prints information about the user.")
	public void printUserInfo() {
		System.out.println(userService.getUser());
	}

	@ShellMethod(key = {"inventory", "inv"}, value = "Lists the inventory of the user")
	public void printInventory() {
		userService.getReadableInventory()
				.forEach((resource, amount) -> System.out.println(resource + " : " + amount));
	}

}
