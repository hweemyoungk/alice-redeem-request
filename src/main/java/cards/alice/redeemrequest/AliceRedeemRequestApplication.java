package cards.alice.redeemrequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"cards.alice"})
public class AliceRedeemRequestApplication {

    public static void main(String[] args) {
        SpringApplication.run(AliceRedeemRequestApplication.class, args);
    }

}
