package com.quadrangle.depOfEnv.repository.management;

import com.quadrangle.depOfEnv.entity.management.ERoomNumber;
import com.quadrangle.depOfEnv.entity.management.Weekly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WeeklyRepository extends JpaRepository<Weekly, Integer> {
    Optional<Weekly> findByRoomNumber(ERoomNumber roomNumber);
}
