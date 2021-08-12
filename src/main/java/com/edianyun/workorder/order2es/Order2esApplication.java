package com.edianyun.workorder.order2es;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@Slf4j
public class Order2esApplication {

	public static void main(String[] args) {

		SpringApplication.run(Order2esApplication.class, args);
		log.info("========================= 应用启动完成 =========================");
	}


}
