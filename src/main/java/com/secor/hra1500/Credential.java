package com.secor.hra1500;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "credentials")
public class Credential
{
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "phone", length = 100)
    private String phone;

    @Column(name = "password", length = 20)
    private String password;

}