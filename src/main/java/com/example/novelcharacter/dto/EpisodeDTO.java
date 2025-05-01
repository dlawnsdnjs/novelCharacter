package com.example.novelcharacter.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EpisodeDTO {
    private long novelNum;
    private long episodeNum;
    private String episodeTitle;
    private String episodeSummary;
}
