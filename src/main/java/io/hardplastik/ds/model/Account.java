package io.hardplastik.ds.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import io.hardplastik.ds.model.Serializable.AccountRolePk;
import io.hardplastik.ds.model.catalogs.CatRole;
import jakarta.persistence.CascadeType;
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
import lombok.ToString;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, of = "id")
@Table(name = "account")
@ToString
public class Account implements UserDetails {

    @Id
    @UuidGenerator
    @Column(name = "account_id")
    private UUID id;

    @JsonUnwrapped
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private List<CustomerData> customers;

    private String username;
    
    @JsonIgnore
    @Column(name = "password_salt")
    private String passwordSalt;

    @JsonIgnore
    @Column(name = "password_hash")
    private String passwordHash;

    private String name;

    @Column(name = "last_name")
    private String lastName;

    private Boolean deleted;
    
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Collection<AccountRole> roles;

    @Column(name = "is_trainer")
    private Boolean isTrainer;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Account(UUID id) {
        this.id = id;
    }

    public void setCustomer(CustomerData customer) {
        if (customers == null) {
            customers = new ArrayList<>();
        }
        customers.set(0, customer);
    }

    @JsonUnwrapped
    public CustomerData getCustomer() {
        return customers != null && !customers.isEmpty() ? customers.get(0) : new CustomerData();
    }

    public void setAuthorities(List<String> authorities) {
        Collection<AccountRole> roles = authorities
        .stream()
        .map(auth -> {
            AccountRole accountRole = new AccountRole();
            accountRole.setPk(new AccountRolePk("", ""));
            accountRole.setRole(new CatRole());
            accountRole.getRole().setName(auth);
            return accountRole;
        }).collect(Collectors.toList());

        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
            .map(role -> new SimpleGrantedAuthority(role.getRole().getName()))
            .collect(Collectors.toList());
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return passwordHash;
    }
}
