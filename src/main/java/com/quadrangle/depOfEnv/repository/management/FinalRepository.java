package com.quadrangle.depOfEnv.repository.management;

import com.quadrangle.depOfEnv.entity.management.ERoomNumber;
import com.quadrangle.depOfEnv.entity.management.Final;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FinalRepository extends JpaRepository<Final, Integer> {
    Optional<Final> findByRoomNumber(ERoomNumber roomNumber);
}
