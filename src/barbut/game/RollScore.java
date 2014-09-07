package barbut.game;

public class RollScore {
	private int score;
	private RollScoreType scoreType;
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public RollScoreType getScoreType() {
		return scoreType;
	}
	public void setScoreType(RollScoreType scoreType) {
		this.scoreType = scoreType;
	}
	
	@Override
	public String toString() {
		return "RollScore [score=" + score + ", scoreType=" + scoreType + "]";
	}
	
}
