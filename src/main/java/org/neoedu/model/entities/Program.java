package org.neoedu.model.entities;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "programs")
@Getter
@Setter
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "pr_name")
    private String name;
    
    @Column(name = "pr_descr")
    private String description;
    
    @ManyToMany
    @JoinTable(
        name = "program_questions",
        joinColumns = @JoinColumn(name = "program_id"),
        inverseJoinColumns = @JoinColumn(name = "question_id"))
    private Set<Question> questions;
}