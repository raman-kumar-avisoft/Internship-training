package org.voting.system.backend.votingSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "voting_table", uniqueConstraints = @UniqueConstraint(
        name = "voting_number_unique",
        columnNames = "voting_number"
))
public class VotingDetails {
    @Id
    @SequenceGenerator(
            name = "votingDetails_sequence",
            sequenceName = "",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "votingDetails_sequence"
    )
    private long votingNumber;

    private String electionCandidate;

    @ManyToOne
    @JoinColumn(name = "voting_employee")
    private Employee employee_id;

    @ManyToOne
    @JoinColumn(name = "voting_details")
    private Election election_id;
}
