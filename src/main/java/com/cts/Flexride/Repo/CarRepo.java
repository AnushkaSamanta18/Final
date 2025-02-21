
package com.cts.Flexride.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cts.Flexride.Entity.CarEntity;

@Repository
public interface CarRepo extends JpaRepository<CarEntity, Integer> {
}
