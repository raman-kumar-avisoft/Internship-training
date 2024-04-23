package org.voting.system.backend.votingSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "employee_table", uniqueConstraints = @UniqueConstraint(
        name = "employee_id_unique",
        columnNames = "employee_id"
))
public class Employee {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @SequenceGenerator(
            name = "employee_sequence",
            sequenceName = "employee_sequence_name", // Specify the name of your sequence
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "employee_sequence"
    )
    private long employeeId;


    private String employeeName;
    @Column(
            name = "employee_profile",
            nullable = false
    )
    private String employeeProfile;

    private String employeeUserName;
    private String employeePassword;

    @OneToMany(mappedBy = "employee_id",
        cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ElectionCandidate> election_candidates;

    @OneToMany(mappedBy = "employee_id",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<VotingDetails> voting_employee;
}
