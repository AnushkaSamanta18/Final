
package com.cts.Flexride.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cts.Flexride.Entity.BookingEntity;
import com.cts.Flexride.Entity.CarEntity;

@Repository
public interface BookingRepo extends JpaRepository<BookingEntity, Integer> {
	List<BookingEntity>findByCar(CarEntity car);
}