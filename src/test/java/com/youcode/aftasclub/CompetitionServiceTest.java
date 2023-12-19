package com.youcode.aftasclub;

import com.youcode.aftasclub.exception.RegistrationClosedException;
import com.youcode.aftasclub.model.Competition;
import com.youcode.aftasclub.model.Member;
import com.youcode.aftasclub.model.Ranking;
import com.youcode.aftasclub.repository.CompetitionRepository;
import com.youcode.aftasclub.repository.MemberRepository;
import com.youcode.aftasclub.repository.RankingRepository;
import com.youcode.aftasclub.service.CompetitionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompetitionServiceTest {

    @Mock
    private CompetitionRepository competitionRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private RankingRepository rankingRepository;

    @InjectMocks
    private CompetitionService competitionService;

    @Test
    void testAddParticipantToCompetition() {
        // Mock data
        Competition competition = new Competition();
        competition.setId(1L);
        competition.setEndTime(LocalTime.now().minusHours(1)); // Set an end time in the past

        Member participant = new Member();
        participant.setId(1L);

        List<Ranking> existingRankings = new ArrayList<>();
        existingRankings.add(new Ranking()); // Simulate an existing ranking

        // Mock behavior
        when(rankingRepository.findByCompetitionAndMember(any(), any())).thenReturn((Ranking) existingRankings);

        // Test the method
        assertThrows(RegistrationClosedException.class,
                () -> competitionService.addParticipantToCompetition(competition.getId(), participant.getId()));

        // Verify behavior
        verify(rankingRepository, times(1)).findByCompetitionAndMember(any(), any());
        verify(rankingRepository, never()).save(any());
    }
}
