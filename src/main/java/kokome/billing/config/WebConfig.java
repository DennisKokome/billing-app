package kokome.billing.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by oaitse on Sep, 10, 2021
 */

@Configuration
@EnableJpaRepositories(basePackages = {"kokome.billing.role.repository","kokome.billing.profile.repository",
        "kokome.billing.product.repository","kokome.billing.bill.repository",})
@EntityScan(basePackages = {"kokome.billing.role.entity", "kokome.billing.profile.entity", "kokome.billing.product.entity",
        "kokome.billing.bill.entity"})
@EnableTransactionManagement
@EnableAsync
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
}
