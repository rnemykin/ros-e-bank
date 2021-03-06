package ru.tn.internalpayment;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.system.ApplicationPidFileWriter;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.CrossOrigin;
import ru.tn.errorhandler.EnableErrorHandle;
import ru.tn.gateway.publish.annotation.EnableGatewayPublishing;
import ru.tn.gateway.publish.annotation.GatewayService;

/**
 * @author dsnimshchikov on 04.05.17.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
@EnableGatewayPublishing(@GatewayService(path = "/internal-payments/**", url = "/internal-payments/"))
@EntityScan("ru.tn.model")
@EnableErrorHandle
@CrossOrigin
public class InternalServiceApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder().sources(InternalServiceApplication.class)
                .listeners(new ApplicationPidFileWriter())
                .run(args);
    }
}
