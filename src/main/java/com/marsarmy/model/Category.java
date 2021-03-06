package com.marsarmy.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * Entity of categories table
 */
@Entity
@Table(name = "categories")
@NoArgsConstructor
@Getter
@Setter
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private long id;

    @Column(name = "name", length = 50, nullable = false)
    @NotBlank
    @Size(min = 2, max = 50)
    private String name;

    @OneToMany(mappedBy = "category",
            cascade = CascadeType.ALL   )
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Product> products;
}
