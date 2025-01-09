package io.hardplastik.ds.model;

import java.util.Collection;

import org.hibernate.annotations.UuidGenerator;

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
@Table(name = "user")
public class User {

    @Id
    @UuidGenerator()
    @Column(name = "user_id")
    private String id;

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
    @JoinColumn(name = "user_id")
    private Collection<UserRole> roles;
}
