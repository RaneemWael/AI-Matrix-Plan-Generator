package code;

public class Pad implements Cloneable {
	
	public int posX;
	public int posY;
	public int newPosX;
	public int newPosY;
	
	public Pad (int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	public Pad (int posX, int posY, int newPosX, int newPosY) {
		this.posX = posX;
		this.posY = posY;
		this.newPosX = newPosX;
		this.newPosY = newPosY;
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
	
	public int getNewPosX() {
		return newPosX;
	}

	public void setNewPosX(int newPosX) {
		this.newPosX = newPosX;
	}

	public int getNewPosY() {
		return newPosY;
	}

	public void setNewPosY(int newPosY) {
		this.newPosY = newPosY;
	}
	
	public String toString() {
		return "Pad (" + newPosX + ", " + newPosY + ")";
	}

	protected Object clone() throws CloneNotSupportedException {
	    return super.clone();
	}
}
