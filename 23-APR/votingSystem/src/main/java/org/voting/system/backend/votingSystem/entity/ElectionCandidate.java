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
@Table(name = "election_candidate_table", uniqueConstraints = @UniqueConstraint(
        name = "election_candidate_id_unique",
        columnNames = "election_candidate_id"
))
public class ElectionCandidate {
    @Id
    @SequenceGenerator(
            name = "election_candidate_sequence",
            sequenceName = "",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "election_candidate_sequence"
    )
    @Column(name = "election_id") // Specify the name of the column in the database
    private long electionId;

    private String electionDescription;
    private long votes;

    @ManyToOne
    @JoinColumn(name = "election_candidate")
    private Employee employee_id;

    @ManyToOne
    @JoinColumn(name = "election_candidates")
    private Election election_id;
}
