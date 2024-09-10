package pl.tul.smartbot;

import org.springframework.boot.SpringApplication;

public class TestSmartBotApplication {

	public static void main(String[] args) {
		SpringApplication.from(SmartBotApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
