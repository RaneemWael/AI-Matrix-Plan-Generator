package code;
import java.util.Random;

public class Hostage implements Cloneable {
	
	public int posX;
	public int posY;
	public int damage;
	public boolean carried;
	public boolean becameAgent;
	public boolean saved;
	public boolean dead;
	public int initialPosX;
	public int initialPosY;
	
	public Hostage (int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		this.damage = new Random().nextInt(99) + 1;
		this.carried = false;
		this.becameAgent = false;
		this.saved = false;
		this.dead = false;
		this.initialPosX = posX;
		this.initialPosY = posY;
	}
	
	public Hostage (int posX, int posY, int damage) {
		this.posX = posX;
		this.posY = posY;
		this.damage = damage;
		this.carried = false;
		this.becameAgent = false;
		this.saved = false;
		this.dead = false;
		this.initialPosX = posX;
		this.initialPosY = posY;
	}
	
	public Hostage (int posX, int posY, int damage, boolean carried, boolean becameAgent, boolean saved, boolean dead) {
		this.posX = posX;
		this.posY = posY;
		this.damage = damage;
		this.carried = carried;
		this.becameAgent = becameAgent;
		this.saved = saved;
		this.dead = dead;
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

	public boolean isCarried() {
		return carried;
	}

	public void setCarried(boolean carried) {
		this.carried = carried;
	}

	public boolean isBecameAgent() {
		return becameAgent;
	}

	public void setBecameAgent(boolean becameAgent) {
		this.becameAgent = becameAgent;
	}

	public boolean isSaved() {
		return saved;
	}

	public void setSaved(boolean saved) {
		this.saved = saved;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public int getInitialPosX() {
		return initialPosX;
	}

	public void setInitialPosX(int initialPosX) {
		this.initialPosX = initialPosX;
	}

	public int getInitialPosY() {
		return initialPosY;
	}

	public void setInitialPosY(int initialPosY) {
		this.initialPosY = initialPosY;
	}

	public String toString() {
		return "H (" + damage + ")";
	}
	
	//the boolean check helps us know if we did increase the damage or not to be able to add it to the appropriate vector in the matrix class
	public boolean incDamage() {
		//if it will increase beyond 100, set to 100 and set is dead to true
		if (!(this.dead)) {
			if (this.damage + 2 >= 100) {
				this.damage = 100;
				dead = true;
				//check if hostage is not carried if so set is agent to true
				if (!(this.carried)) {
					becameAgent = true;
				}
			}
			else {
				this.damage += 2;
			}
			return true;
		}
		return false;
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
