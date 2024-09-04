package com.team02.best_properta;

import com.team02.best_properta.entity.concretes.business.Role;
import com.team02.best_properta.entity.enums.RoleType;
import com.team02.best_properta.payload.request.user.UserRequest;
import com.team02.best_properta.repository.business.RoleRepository;
import com.team02.best_properta.service.business.RoleService;
import com.team02.best_properta.service.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class BestPropertaApplication implements CommandLineRunner {

	private final RoleRepository roleRepository;
	private final UserService userService;
	private final RoleService roleService;

	// Constructor injection
	public BestPropertaApplication(RoleRepository roleRepository, UserService userService, RoleService roleService) {
		this.roleRepository = roleRepository;
		this.userService = userService;
		this.roleService = roleService;
	}

	public static void main(String[] args) {
		SpringApplication.run(BestPropertaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (roleService.getAllRole().isEmpty()) {

			Role admin = new Role();
			admin.setRoleType(RoleType.ADMIN);
			admin.setRoleName("Admin");
			roleRepository.save(admin);

			Role manager = new Role();
			manager.setRoleType(RoleType.MANAGER);
			manager.setRoleName("Dean");
			roleRepository.save(manager);

			Role customer = new Role();
			customer.setRoleType(RoleType.CUSTOMER);
			customer.setRoleName("Customer");
			roleRepository.save(customer);

			Role anonymous = new Role();
			anonymous.setRoleType(RoleType.ANONYMOUS);
			anonymous.setRoleName("Anonymous");
			roleRepository.save(anonymous);
		}

		if (userService.countAllAdmins() == 0) {
			UserRequest adminRequest = new UserRequest();
			adminRequest.setEmail("admin@admin.com");
			adminRequest.setPassword_hash("A1b@2345");
			adminRequest.setFirstName("Hızır");
			adminRequest.setLastName("İlyas");
			adminRequest.setPhone("111-111-1111");
			adminRequest.setCreateAt(LocalDateTime.now());

			userService.register(adminRequest, "Admin");
		}
	}

}
