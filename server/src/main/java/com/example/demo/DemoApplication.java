package com.example.demo;

import com.example.demo.model.domains.Device;
import com.example.demo.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.xml.bind.ValidationException;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	DeviceService deviceService;


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}


	// this was just a quick test to run the service to check what sort of data it returns, uncomment if you want to try
	// when the application starts this will be run first, this is particularly good if you want to initial some things
	// before hand eg database fields creation

/*	@PostConstruct
	private List<Device> init() throws ValidationException {
		List<Device> devices = deviceService.getDevices();
		return devices;
	}*/
}
