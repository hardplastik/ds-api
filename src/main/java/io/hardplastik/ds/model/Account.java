package io.hardplastik.ds.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "account")
@JsonInclude(Include.NON_ABSENT)
public class Account {

    @Id
    @UuidGenerator
    @Column(name = "account_id")
    private UUID id;

    private String username;
    
    @Column(name = "password_salt")
    private String passwordSalt;

    @Column(name = "password_hash")
    private String passwordHash;

    private String name;

    @Column(name = "last_name")
    private String lastName;

    private Boolean deleted;
    
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Collection<AccountRole> roles;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
