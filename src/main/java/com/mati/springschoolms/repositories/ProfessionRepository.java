package com.mati.springschoolms.repositories;

import com.mati.springschoolms.entities.Profession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessionRepository extends JpaRepository<Profession, Long> {
    Profession findByName(String name);
}
