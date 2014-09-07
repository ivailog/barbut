package barbut.game;

import java.util.Arrays;
import java.util.List;
import java.util.Random;



public class RollScoreCalculator {
	
	public RollScore calculate(List<Integer> dices) {
		RollScore result = new RollScore();
		
		if ( (new Random().nextInt(10) + 1) == 10 ) {
			result.setScoreType(RollScoreType.THROW_AGAIN);
			result.setScore(0);
			return result;
		}
		
		//check for combinations
		if (dices.size() == 3) {
			
			//1-3-5
			if (checkCombination(dices, Arrays.asList(1, 3, 5))) {
				result.setScoreType(RollScoreType.COMBINATION);
				result.setScore(200);
				return result;
			}
			
			//2-4-6
			if (checkCombination(dices, Arrays.asList(2, 4, 6))) {
				result.setScoreType(RollScoreType.NEGATIVE);
				result.setScore(-200);
				return result;
			}
			
			//check for 6-6-6
			if (dices.get(0) == 6 && dices.get(1) == 6
					&& dices.get(2) == 6) {
				result.setScoreType(RollScoreType.CLEAR_ALL_POINTS);
				result.setScore(-1000);
				return result;
			}
			
			//check for 1-1-1
			if (dices.get(0) == 1 && dices.get(1) == 1
					&& dices.get(2) == 1) {
				result.setScoreType(RollScoreType._1000_POINTS);
				result.setScore(1000);
				return result;
			}
			
			//check if the 3 dices are equals
			if (dices.get(0) == dices.get(1)
					&& dices.get(1) == dices.get(2)) {
				result.setScoreType(RollScoreType.COMBINATION);
				result.setScore(dices.get(0) * 100);
				return result;
			}
			
			//check sequences
			if (checkCombination(dices, Arrays.asList(1, 2, 3))
					|| checkCombination(dices, Arrays.asList(2, 3, 4))
					|| checkCombination(dices, Arrays.asList(3, 4, 5))
					|| checkCombination(dices, Arrays.asList(4, 5, 6))) {
				result.setScoreType(RollScoreType.COMBINATION);
				result.setScore(200);
				return result;
			}
		}
		
		int score = 0;
		for (int dice : dices) {
			if (dice == 5) {
				score += 50;
			} else if (dice == 1) {
				score += 100;
			}
		}

		result.setScoreType( (score > 0)? RollScoreType.QUALIFIED : RollScoreType.NOT_QUALIFIED );
		result.setScore(score);
		
		return result;
	}
	
	private boolean checkCombination(List<Integer> dices, List<Integer> combination) {
		List<List<Integer>> diceIndexPermutations = Util.generatePermutations( Arrays.asList(0, 1, 2 ) );
		for (List<Integer> indexes : diceIndexPermutations) {
			int diceIndex1 = indexes.get(0);
			int diceIndex2 = indexes.get(1);
			int diceIndex3 = indexes.get(2);
			
			if (dices.get(diceIndex1) == combination.get(0) 
				&& dices.get(diceIndex2) == combination.get(1)
				&& dices.get(diceIndex3) == combination.get(2)) {
				
				return true;
			}
		}
		
		return false;
	}
	
}
