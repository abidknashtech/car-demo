package com.nashtech.payment;

import com.nashtech.payment.config.AxonXstreamConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ AxonXstreamConfig.class })
public class PaymentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(com.nashtech.payment.PaymentServiceApplication.class, args);
	}

}
