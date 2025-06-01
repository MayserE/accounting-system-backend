package com.accounting_system.accounting_system_backend.domain.entities;

import com.accounting_system.accounting_system_backend.domain.entities.enums.ClientDocumentType;
import com.accounting_system.accounting_system_backend.domain.entities.enums.ClientStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "clients")
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "company_id", nullable = false)
    private UUID companyId;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private ClientStatus status;

    @Column(nullable = false, length = 128)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "document_type", nullable = false, length = 64)
    private ClientDocumentType documentType;

    @Column(name = "document_number", nullable = false, length = 64)
    private String documentNumber;

    @Column(nullable = false, length = 256)
    private String email;

    @Column(name = "tax_id", length = 32)
    private String taxId;

    @Column(name = "phone_number", length = 32)
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Company company;
}
