package com.gang.domain.summoner;

import com.gang.api.common.Dto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.rithms.riot.dto.League.League;
import net.rithms.riot.dto.League.LeagueEntry;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Junwoo on 2017-04-28.
 */
@Getter
@Setter
@Builder
public class LeagueDto implements Dto{
    private String tier;
    private String queue;
    private String participantID;
    private String name;
    private List<LeagueEntryDto> entries;

    public static LeagueDto of(League league){
        List<LeagueEntryDto> entries = new LinkedList<LeagueEntryDto>();
        for(LeagueEntry tempLeague : league.getEntries()){
            entries.add(LeagueEntryDto.of(tempLeague));
        }

        Collections.sort(entries);

        return LeagueDto.builder()
                .tier(league.getTier())
                .queue(league.getQueue())
                .participantID(league.getParticipantId())
                .name(league.getName())
                .entries(entries)
                .build();
    }
}
