package com.quadrangle.depOfEnv.repository.management;

import com.quadrangle.depOfEnv.entity.management.Biweekly;
import com.quadrangle.depOfEnv.entity.management.ERoomNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BiweeklyRepository extends JpaRepository<Biweekly, Integer> {
    Optional<Biweekly> findByRoomNumber(ERoomNumber roomNumber);
}
