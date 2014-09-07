package barbut.game;

import java.util.Random;

public class Dice {
	private short value;
	private boolean isKept;
	Random randomGenerator = new Random();
	
	public boolean isKept() {
		return isKept;
	}
	public void setKept(boolean isKept) {
		this.isKept = isKept;
	}
	
	public short getValue() {
		return value;
	}
	
	public void roll() {
		value = (short)(randomGenerator.nextInt(6) + 1);
	}
	
}
