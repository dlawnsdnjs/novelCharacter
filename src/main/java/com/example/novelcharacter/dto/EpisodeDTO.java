package com.example.novelcharacter.dto;

public class EpisodeDTO {
    private long novelNum;
    private long episodeNum;
    private String episodeTitle;
    private String episodeSummary;

    @Override
    public String toString() {
        return "EpisodeDTO{" +
                "novelNum=" + novelNum +
                ", episodeNum=" + episodeNum +
                ", episodeName='" + episodeTitle + '\'' +
                ", episodeSummary='" + episodeSummary + '\'' +
                '}';
    }

    public long getNovelNum() {
        return novelNum;
    }

    public void setNovelNum(long novelNum) {
        this.novelNum = novelNum;
    }

    public long getEpisodeNum() {
        return episodeNum;
    }

    public void setEpisodeNum(long episodeNum) {
        this.episodeNum = episodeNum;
    }

    public String getEpisodeTitle() {
        return episodeTitle;
    }

    public void setEpisodeTitle(String episodeTitle) {
        this.episodeTitle = episodeTitle;
    }

    public String getEpisodeSummary() {
        return episodeSummary;
    }

    public void setEpisodeSummary(String episodeSummary) {
        this.episodeSummary = episodeSummary;
    }

}
