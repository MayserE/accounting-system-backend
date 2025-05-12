package com.accounting_system.accounting_system_backend.entities;

import com.accounting_system.accounting_system_backend.enums.CompanyStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "companies")
@Getter
@Setter
public class Company {
    @Id
    @GeneratedValue
    private UUID id;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private CompanyStatus status;

    @Column(nullable = false, length = 128)
    private String name;

    @Column(name = "tax_id", nullable = false, length = 32)
    private String taxId;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column(name = "phone_number", length = 32)
    private String phoneNumber;
}
