package com.gang.domain.summoner;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.rithms.riot.dto.League.LeagueEntry;

/**
 * Created by Junwoo on 2017-04-28.
 */
@Builder
@Getter
@Setter
public class LeagueEntryDto implements Comparable<LeagueEntryDto>{
    private String division;
    private int wins;
    private int losses;
    private double winningRate;
    private String playerId;
    private String playerName;
    private int leaguePoints;

    public static LeagueEntryDto of(LeagueEntry leagueEntry) {
        double winningRate = ((double)(leagueEntry.getWins())/(leagueEntry.getWins()+leagueEntry.getLosses()))*100.0;

        return LeagueEntryDto.builder()
                .division(leagueEntry.getDivision())
                .wins(leagueEntry.getWins())
                .losses(leagueEntry.getLosses())
                .winningRate(winningRate)
                .playerId(leagueEntry.getPlayerOrTeamId())
                .playerName(leagueEntry.getPlayerOrTeamName())
                .build();
    }

    public int compareTo(LeagueEntryDto leagueEntryDto){
        return leaguePoints<leagueEntryDto.leaguePoints?-1:1;
    }
}
