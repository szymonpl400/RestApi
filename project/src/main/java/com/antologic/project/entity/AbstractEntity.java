package com.antologic.project.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @EqualsAndHashCode.Include
    @Column(name = "uuid", nullable = false, unique = true)
    private UUID uuid = UUID.randomUUID();
}
