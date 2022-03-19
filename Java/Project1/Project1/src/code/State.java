package code;
import java.util.Vector;

public class State implements Cloneable {
	
	public int cost;
	public int g1Cost;
	public int g2Cost;
	public int a1Cost;
	public int a2Cost;
	public int neoPosX;
	public int neoPosY;
	public int neoDamage;
	public int neoPrevPosX;
	public int neoPrevPosY;
	public Vector<Agent> agents;
	public Vector<Pill> pills;
	public Vector<Pad> pads;
	public Vector<Hostage> hostages;
	public Vector<Hostage> carriedHostages;
	//for checking of repeated states
	public int kills;
	public int deaths;
	
	public State (int neoPosX, int neoPosY, int neoDamage, int neoPrevPosX, int neoPrevPosY, Vector<Agent> agents, Vector<Pill> pills, Vector<Pad> pads, Vector<Hostage> hostages, Vector<Hostage> carriedHostages, int kills) {
		this.neoPosX = neoPosX;
		this.neoPosY = neoPosY;
		this.neoDamage = neoDamage;
		this.neoPrevPosX = neoPrevPosX;
		this.neoPrevPosY = neoPrevPosY;
		this.agents = agents;
		this.pills = pills;
		this.pads = pads;
		this.hostages = hostages;
		this.carriedHostages = carriedHostages;
		this.kills = kills;
		this.deaths = deaths();
		this.cost = kills + deaths();
		this.g1Cost = (Math.abs(neoPosX - Matrix.telBooth.posX) + Math.abs(neoPosY - Matrix.telBooth.posY))/4;
		this.g2Cost = (Math.abs(neoPosX - Matrix.telBooth.posX) + Math.abs(neoPosY - Matrix.telBooth.posY) + hostagesLeftToSave())/8;
		this.a1Cost = g1Cost + cost;
		this.a2Cost = g2Cost + cost;
		
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getNeoPosX() {
		return neoPosX;
	}

	public void setNeoPosX(int neoPosX) {
		this.neoPosX = neoPosX;
	}

	public int getNeoPosY() {
		return neoPosY;
	}

	public void setNeoPosY(int neoPosY) {
		this.neoPosY = neoPosY;
	}

	public int getNeoDamage() {
		return neoDamage;
	}

	public void setNeoDamage(int neoDamage) {
		this.neoDamage = neoDamage;
	}

	public int getNeoPrevPosX() {
		return neoPrevPosX;
	}

	public void setNeoPrevPosX(int neoPrevPosX) {
		this.neoPrevPosX = neoPrevPosX;
	}

	public int getNeoPrevPosY() {
		return neoPrevPosY;
	}

	public void setNeoPrevPosY(int neoPrevPosY) {
		this.neoPrevPosY = neoPrevPosY;
	}

	public Vector<Agent> getAgents() {
		return agents;
	}

	public void setAgents(Vector<Agent> agents) {
		this.agents = agents;
	}

	public Vector<Pill> getPills() {
		return pills;
	}

	public void setPills(Vector<Pill> pills) {
		this.pills = pills;
	}

	public Vector<Pad> getPads() {
		return pads;
	}

	public void setPads(Vector<Pad> pads) {
		this.pads = pads;
	}

	public Vector<Hostage> getHostages() {
		return hostages;
	}

	public void setHostages(Vector<Hostage> hostages) {
		this.hostages = hostages;
	}

	public Vector<Hostage> getCarriedHostages() {
		return carriedHostages;
	}

	public void setCarriedHostages(Vector<Hostage> carriedHostages) {
		this.carriedHostages = carriedHostages;
	}
	
	public int getKills() {
		return kills;
	}

	public void setKills(int kills) {
		this.kills = kills;
	}

	public int getDeaths() {
		return deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}
	
	public int deaths() {
		int result = 0;
		//add any not carried that died
		for (int i = 0; i < hostages.size(); i++) {
			Hostage hostage = hostages.get(i);
			if (hostage.dead) {
				result++;
			}
		}
		//add any carried that died
		for (int i = 0; i < carriedHostages.size(); i++) {
			Hostage hostage = carriedHostages.get(i);
			if (hostage.dead) {
				result++;
			}
		}
		
		return result;
	}
	
	public int hostagesLeftToSave() {
		int result = 0;
		//add any not carried that died
		for (int i = 0; i < hostages.size(); i++) {
			Hostage hostage = hostages.get(i);
			if (!hostage.dead && !hostage.becameAgent) {
				result++;
			}
		}
		
		return result;
	}
	
	public static void incDamageHostage(Vector<Hostage> hostages) {
		for (int i = 0; i < hostages.size(); i++) {
			Hostage hostage = hostages.get(i);
			//make sure hostage is not dead or an agent or is saved
			if (!(hostage.dead) && !(hostage.becameAgent) && !(hostage.saved)){
				//check if hostage damage will exceed 100
				if (hostage.damage + 2 >= 100) {
					hostages.get(i).damage = 100;
					//check if carried to only set as dead hostage
					if (hostage.carried) {
						hostages.get(i).dead = true;
					}
					else {
						hostages.get(i).becameAgent = true;
					}
				}
				else {
					hostages.get(i).damage += 2;
				}
			}
		}
	}

	//override toString method of state to be able to use it in hashtable comparison
	public String toString() {
		//initialize empty results string
		String result = "";
		//add current position of neo
		result +=  "Neo's Position: " + neoPosX + "," + neoPosY + " + " ;
		//add neo's current damage
		result += "Neo's Damage: " + neoDamage;
		//add current hostages with their positions and data
		for(int i = 0; i < hostages.size(); i++) {
			result += "Hostage Left: " + hostages.get(i).posX + "," + hostages.get(i).posY + "," + hostages.get(i).saved + "," + hostages.get(i).dead + " + ";
		}
		//add current carried hostages with their positions and data
		for(int i = 0; i < carriedHostages.size(); i++) {
			result += "Carried Hostage: " + carriedHostages.get(i).posX + "," + carriedHostages.get(i).posY + "," + carriedHostages.get(i).saved + "," + carriedHostages.get(i).dead + " + ";
		}
		//add pills remaining with their positions
		for(int i = 0; i < pills.size(); i++) {
			result += "Pill Left: " + pills.get(i).posX + "," + pills.get(i).posY + " + ";
		}
		//add agents remaining in grid
		for(int i = 0; i < agents.size(); i++){
			result += "Agent Left: " + agents.get(i).posX + "," + agents.get(i).posY + " + ";
		}
		return result;
	}
	
	protected Object clone() throws CloneNotSupportedException {
	    return super.clone();
	}
}
