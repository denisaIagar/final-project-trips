package ro.siit.denisa.MyTripsApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.siit.denisa.MyTripsApp.controller.TripsController;
import ro.siit.denisa.MyTripsApp.model.Trips;

import java.io.File;

@SpringBootApplication
@ComponentScan({"ro.siit.denisa.MyTripsApp"})
public class MyTripsAppApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return super.configure(builder);
//		return application.sources(MyTripsAppApplication.class);
	}

	public static void main(String[] args) {
		new File(TripsController.uploadingDir).mkdir();
		SpringApplication.run(MyTripsAppApplication.class, args);
	}

}
