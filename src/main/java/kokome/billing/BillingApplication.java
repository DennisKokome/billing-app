package kokome.billing;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import kokome.billing.profile.entity.User;
import kokome.billing.profile.service.ProfileService;
import kokome.billing.role.service.RoleService;
import kokome.billing.role.entity.Role;
import kokome.billing.bill.service.BillService;
import kokome.billing.product.entity.Product;
import kokome.billing.product.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

import static kokome.billing.enumaration.ProductType.*;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Billing Application", version = "1.0", description = "bill me"))
public class BillingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleService roleService, ProfileService profileService, ProductService productService, BillService billService) {
		return args -> {

			//Create system roles
			Role customer = roleService.createRole(new Role("CUSTOMER", 5F));
			Role employee = roleService.createRole(new Role("EMPLOYEE", 30F));
			Role affiliate = roleService.createRole(new Role("AFFILIATE", 10F));

			//create users
			profileService.saveUser(new User("Oaitse", "Kokome","kai",
					LocalDate.of(2017, 10, 11), customer));
			profileService.saveUser(new User("James", "Kedikilwe","Kedikilwe",
					LocalDate.of(2020, 10, 11), customer));
			profileService.saveUser(new User("john", "Mogorosi","Mogorosi",
					LocalDate.of(2021, 10, 11), customer));
			profileService.saveUser(new User("Simon", "Motimedi","Motimedi",
					LocalDate.of(2020, 10, 11), employee));
			profileService.saveUser(new User("Trish", "Michael","Michael",
					LocalDate.of(2019, 10, 11), employee));
			profileService.saveUser(new User("Dwayne", "Smith","Smith",
					LocalDate.of(2021, 10, 11), affiliate));
			profileService.saveUser(new User("Kefilwe", "Kefilwe","Kefilwe",
					LocalDate.of(2021, 10, 11), affiliate));

			//create products
			productService.saveProduct(new Product("Milk", 10.95F, GROCERY));
			productService.saveProduct(new Product("Eggs", 10.95F, GROCERY));
			productService.saveProduct(new Product("Phone", 90.95F, ELECTRONIC));
			productService.saveProduct(new Product("HeadPhone", 70.95F, ELECTRONIC));
			productService.saveProduct(new Product("Shoes", 200, CLOTHING));
			productService.saveProduct(new Product("Trouser", 10.95F, CLOTHING));
			productService.saveProduct(new Product("Shirt", 50.95F, CLOTHING));
			productService.saveProduct(new Product("Clean Code", 7.95F, BOOK));
			productService.saveProduct(new Product("Clean Architecture", 6.95F, BOOK));
			productService.saveProduct(new Product("Digging Fork", 35.95F, GARDENING));
			productService.saveProduct(new Product("Wheel Barrow", 18595F, GARDENING));
		};
	}

}
