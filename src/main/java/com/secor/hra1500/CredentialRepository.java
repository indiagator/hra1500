package com.secor.hra1500;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CredentialRepository extends JpaRepository<Credential, Long> {

    List<Credential> findByPhone(String phone);

    Credential findByEmail(String email);
}
