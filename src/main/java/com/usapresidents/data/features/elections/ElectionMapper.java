package com.usapresidents.data.features.elections;

import com.usapresidents.data.core.domain.models.Election;
import com.usapresidents.data.features.elections.dto.ElectionDto;
import org.springframework.stereotype.Component;

@Component
public class ElectionMapper {
    public ElectionDto toElectionDto(Election election){
        return new ElectionDto(
                election.getId(),
                election.getElectionYear(),
                election.getCandidate(),
                election.getVotes(),
                election.getWinnerLoserIndic()
        );
    }
}
