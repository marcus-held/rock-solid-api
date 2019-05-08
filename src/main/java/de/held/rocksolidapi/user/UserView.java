package de.held.rocksolidapi.user;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class UserView {

	private final UserService userService;

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
