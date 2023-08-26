package com.quadrangle.depOfEnv.repository.management;

import com.quadrangle.depOfEnv.entity.management.Daily;
import com.quadrangle.depOfEnv.entity.management.ERoomNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DailyRepository extends JpaRepository<Daily, Integer> {
    Optional<Daily> findByRoomNumber(ERoomNumber roomNumber);
}
