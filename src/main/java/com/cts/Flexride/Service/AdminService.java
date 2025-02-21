/*package com.cts.Flexride.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.Flexride.Entity.AdminEntity;
import com.cts.Flexride.Repo.AdminRepo;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepo adminrepo;
	
	// Saves the AdminEntity using JPA.
	public AdminEntity saveAdmin(AdminEntity admin) {
		return adminrepo.save(admin);
		    }
	

	public AdminEntity validateAdmin(String email, String password) {
		 AdminEntity admin = adminrepo.findByEmail(email);
		 System.out.println("email matched");
	        if (admin != null && admin.getPassword().equals(password)) {
	            return admin; // Return the admin object
	        }
		// TODO Auto-generated method stub
		return null;
	}
}

*/








package com.cts.Flexride.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cts.Flexride.Entity.AdminEntity;
import com.cts.Flexride.Repo.AdminRepo;

@Service
public class AdminService {
    private static final Logger logger = LoggerFactory.getLogger(AdminService.class);
    
    @Autowired
    private AdminRepo adminrepo;
    
    public AdminEntity saveAdmin(AdminEntity admin) {
        logger.info("Saving admin entity: {}", admin); //This line logs the information that an admin entity is being saved. It uses a logger to output the message, which helps in tracking the application’s behavior.
        return adminrepo.save(admin); //use to save the admin entity to the DB
    }

    public AdminEntity validateAdmin(String email, String password) {
        logger.info("Validating admin with email: {}", email); //Logs the start of the validation process.
        AdminEntity admin = adminrepo.findByEmail(email);  //Calls the findByEmail method of the adminrepo repository to retrieve an AdminEntity by email. 
        if (admin != null && admin.getPassword().equals(password)) {
            logger.info("Admin validation successful");
            return admin;
        }
        logger.warn("Invalid admin credentials");
        return null;
    }
}
