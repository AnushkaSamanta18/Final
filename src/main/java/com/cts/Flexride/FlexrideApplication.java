/*package com.cts.Flexride;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import com.cts.Flexride.Entity.AdminEntity;
import com.cts.Flexride.Repo.AdminRepo;
import com.cts.Flexride.Service.AdminService;

@RestController
@SpringBootApplication
public class FlexrideApplication implements CommandLineRunner {
    
	@Autowired
	private AdminService adminservice;
	public static void main(String[] args) {
		SpringApplication.run(FlexrideApplication.class, args);
		
	}
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
*/

package com.cts.Flexride;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FlexrideApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(FlexrideApplication.class, args);
	}
}
