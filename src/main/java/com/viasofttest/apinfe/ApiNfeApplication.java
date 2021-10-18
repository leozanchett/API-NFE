package com.viasofttest.apinfe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ApiNfeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiNfeApplication.class, args);
	}

}
