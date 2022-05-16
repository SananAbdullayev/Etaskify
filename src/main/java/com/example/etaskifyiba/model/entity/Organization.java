package com.example.etaskifyiba.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "organization")
@ToString
@Builder
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 100, nullable = false, unique = true)
    private String name;

    @Column(name = "phone_number", length = 40)
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "organization",cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<User> user;

    @OneToMany(mappedBy = "organization",cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Task> task;

}
