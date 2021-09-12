package kokome.billing;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.main.lazy-initialization=true",
		classes = {BillingApplication.class})
class BillingApplicationTests {

	@Test
	void contextLoads() {
	}

}
