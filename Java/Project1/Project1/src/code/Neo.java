package code;

public class Neo implements Cloneable {
	
	public int posX;
	public int posY;
	public int damage;

	public Neo (int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		damage = 0;
	}
	
	public Neo (int posX, int posY, int damage) {
		this.posX = posX;
		this.posY = posY;
		this.damage = damage;
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

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	public String toString() {
		return "Neo";
	}
	
	public void incDamage() {
		//if it will increase beyond 100, set to 100 and set is dead to true
		if (this.damage + 20 >= 100) {
			this.damage = 100;
		}
		else {
			this.damage += 20;
		}
	}
	
	public void decDamage() {
		//if it will decrease beyond 0, set to 0
		if (this.damage - 20 < 0) {
			this.damage = 0;
		}
		else {
			this.damage -= 20;
		}
	}
	
	protected Object clone() throws CloneNotSupportedException {
	    return super.clone();
	}
}