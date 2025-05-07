package com.secor.hra1500;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CredentialRepository extends JpaRepository<Credential, Long> {

    List<Credential> findByPhone(String phone);

    Optional<Credential> findByEmail(String email);

}
