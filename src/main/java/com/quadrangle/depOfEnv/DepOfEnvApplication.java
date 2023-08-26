package com.quadrangle.depOfEnv;

import com.quadrangle.depOfEnv.entity.auth.ERole;
import com.quadrangle.depOfEnv.entity.auth.Role;
import com.quadrangle.depOfEnv.entity.management.*;
import com.quadrangle.depOfEnv.repository.RoleRepository;
import com.quadrangle.depOfEnv.repository.management.BiweeklyRepository;
import com.quadrangle.depOfEnv.repository.management.DailyRepository;
import com.quadrangle.depOfEnv.repository.management.FinalRepository;
import com.quadrangle.depOfEnv.repository.management.WeeklyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DepOfEnvApplication {

	public static void main(String[] args) {
		SpringApplication.run(DepOfEnvApplication.class, args);
	}

	@Bean
	public static CommandLineRunner commandLineRunner(RoleRepository roleRepository, DailyRepository dailyRepository, WeeklyRepository weeklyRepository, BiweeklyRepository biweeklyRepository, FinalRepository finalRepository) {
		return runner -> {
			if (!roleRepository.existsByName(ERole.ROLE_USER)) {
				roleRepository.save(new Role(ERole.ROLE_USER));
			}

			if (!roleRepository.existsByName(ERole.ROLE_MODERATOR)) {
				roleRepository.save(new Role(ERole.ROLE_MODERATOR));
			}

			if (!roleRepository.existsByName(ERole.ROLE_ADMIN)) {
				roleRepository.save(new Role(ERole.ROLE_ADMIN));
			}

			for (ERoomNumber roomNumber : ERoomNumber.values()) {
				if (dailyRepository.findByRoomNumber(roomNumber).isEmpty()) {
					dailyRepository.save(new Daily(roomNumber));
				}
				if (weeklyRepository.findByRoomNumber(roomNumber).isEmpty()) {
					weeklyRepository.save(new Weekly(roomNumber));
				}
				if (biweeklyRepository.findByRoomNumber(roomNumber).isEmpty()) {
					biweeklyRepository.save(new Biweekly(roomNumber));
				}
				if (finalRepository.findByRoomNumber(roomNumber).isEmpty()) {
					finalRepository.save(new Final(roomNumber));
				}
			}
		};
	}
}
