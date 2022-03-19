package code;

public class TelBooth implements Cloneable {
	
	public int posX;
	public int posY;
	
	public TelBooth (int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public String toString() {
		return "TB";
	}

	protected Object clone() throws CloneNotSupportedException {
	    return super.clone();
	}
}
