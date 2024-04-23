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
@Table(name = "election_table", uniqueConstraints = @UniqueConstraint(
        name = "election_candidate_id_unique",
        columnNames = "election_candidate_id"
))
public class Election {
    @Id
    @SequenceGenerator(
            name = "election_sequence",
            sequenceName = "",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "election_sequence"
    )
    private long electionId;
    private long electionDescriptionName;

    @OneToMany(mappedBy = "election_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VotingDetails> voting_details;

    @OneToMany(mappedBy = "election_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ElectionCandidate> election_candidates;
}