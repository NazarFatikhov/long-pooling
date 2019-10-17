package ru.nazarfatichov.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "\"user\"")
@ToString(exclude = "tokens")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(name = "hash_password", nullable = false)
    private String hashPassword;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;



}
