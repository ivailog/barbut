package barbut.game;

import java.util.ArrayList;
import java.util.List;


public class Game {
	
	private List<Player> players;
	private int currentPlayerIndex;
	private int currentScore;
	private List<Dice> dices;
	private boolean gameStarted;
	
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
		
		for (Dice dice : dicesToRoll)
			dice.roll();
		
		//calculate score
		
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


}
