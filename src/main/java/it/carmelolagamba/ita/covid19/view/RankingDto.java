package it.carmelolagamba.ita.covid19.view;

import java.util.List;

public class RankingDto {

	private List<RankingItemDto> bestRanked;
	private List<RankingItemDto> worstRanked;
	private List<RankingItemDto> fullRanking;

	public List<RankingItemDto> getBestRanked() {
		return bestRanked;
	}

	public void setBestRanked(List<RankingItemDto> bestRanked) {
		this.bestRanked = bestRanked;
	}

	public List<RankingItemDto> getWorstRanked() {
		return worstRanked;
	}

	public void setWorstRanked(List<RankingItemDto> worstRanked) {
		this.worstRanked = worstRanked;
	}

	public List<RankingItemDto> getFullRanking() {
		return fullRanking;
	}

	public void setFullRanking(List<RankingItemDto> fullRanking) {
		this.fullRanking = fullRanking;
	}

}
