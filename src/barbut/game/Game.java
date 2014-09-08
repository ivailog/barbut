package barbut.game;

import java.util.ArrayList;
import java.util.List;


public class Game {
	
	private List<Player> players;
	private int currentPlayerIndex;
	private int currentScore;
	private List<Dice> dices;
	private boolean gameStarted;
	private RollScoreCalculator scoreCalculator;
	private RollScoreType prevScoreType;
	
	public Game(List<Player> players) {
		this.players = players;
		initOrResetDices();
	} 
	
	public void start() {
		if (!gameStarted) {
			gameStarted = true;
			currentPlayerIndex = 0;
			currentScore = 0;
			initOrResetDices();
		}
	}
	
	
	
	public void makeMove() {
		//roll dices
		List<Dice> dicesToRoll = new ArrayList<Dice>();
		for (Dice dice : dices){
			if (!dice.isKept())
				dicesToRoll.add(dice);
		}
		
		List<Integer> rolledNumbers = new ArrayList<Integer>();
		for (Dice dice : dicesToRoll) {
			dice.roll();
			rolledNumbers.add(Integer.valueOf(dice.getValue()));
			
		}
		
		//calculate score
		RollScore score = scoreCalculator.calculate(rolledNumbers);
		if (prevScoreType != null) {
			
			//Confirm 1000 points
			if (prevScoreType == RollScoreType._1000_POINTS) {
				
				if (score.getScore() > 0) {
					//confirmed
					currentScore += 1000;
				} else {
					currentScore = 0;
					switchPlayer();
				}
				
			}
			
			
		}
		
	}
	
	private void switchPlayer() {
		//set current player score
		Player currentPlayer = players.get(currentPlayerIndex);
		currentPlayer.setScore(currentScore);
		currentScore = 0;
		prevScoreType = null;
		
		//switch to next player
		currentPlayerIndex = ( ++currentPlayerIndex ) % players.size();
	}
	
	
	private void initOrResetDices() {
		if (dices == null)
			dices = new ArrayList<Dice>(3);
		
		dices.clear();
		dices.add(new Dice());
		dices.add(new Dice());
		dices.add(new Dice());
	}
	
	public List<Dice> getDices() {
		return dices;
	}
	
	public int getCurrentScore() {
		return currentScore;
	}

	public Player getCurrentPlayer() {
		return players.get(currentPlayerIndex);
	}

	public RollScoreCalculator getScoreCalculator() {
		return scoreCalculator;
	}

	public void setScoreCalculator(RollScoreCalculator scoreCalculator) {
		this.scoreCalculator = scoreCalculator;
	}


}
