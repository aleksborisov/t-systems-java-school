package com.marsarmy.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Role implements GrantedAuthority {

    public Role(Long id) {
        this.id = id;
    }

    @Id
    @Column(name = "role_id")
    private long id;

    @Column(name = "name", length = 20, nullable = false, unique = true)
    @NotBlank
    @Size(min = 1, max = 20)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<Customer> customers;

    @Override
    public String getAuthority() {
        return getName();
    }
}
