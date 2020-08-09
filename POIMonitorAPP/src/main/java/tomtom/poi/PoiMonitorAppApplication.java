package tomtom.poi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@EnableAdminServer
@SpringBootApplication
public class PoiMonitorAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PoiMonitorAppApplication.class, args);
	}

}
