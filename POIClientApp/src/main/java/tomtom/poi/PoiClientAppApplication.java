package tomtom.poi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PoiClientAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PoiClientAppApplication.class, args);
	}

}
