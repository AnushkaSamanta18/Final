package com.cts.Flexride.Repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.Flexride.Entity.AdminEntity;

@Repository
public interface AdminRepo extends JpaRepository<AdminEntity, Integer>{  //By extending JpaRepository, AdminRepo inherits several methods for working with AdminEntity persistence, including methods for saving, deleting, and finding entities.
	//finds an admin by email or password
	 AdminEntity findByEmail(String email); //Defines a custom query method to find an AdminEntity by its email.
}
