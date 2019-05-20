package de.held.rocksolidapi.user;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

/**
 * Defines the shell operations that can be executed on the user.
 */
@ShellComponent
public class UserView {

	private final UserService userService;
	private final UserRepository userRepository;

	public UserView(UserService userService, UserRepository userRepository) {
		this.userService = userService;
		this.userRepository = userRepository;
	}

	@ShellMethod(key = {"info"}, value = "Prints information about the user.")
	public void printUserInfo() {
		System.out.println(userRepository.getUser());
	}

	@ShellMethod(key = {"inventory", "inv"}, value = "Lists the inventory of the user")
	public void printInventory() {
		userService.getReadableInventory()
				.forEach((resource, amount) -> System.out.println(resource + " : " + amount));
	}

}
