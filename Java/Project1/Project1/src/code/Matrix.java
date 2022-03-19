package code;
import java.util.Random;
import java.util.Vector;

public class Matrix extends GenericSearchProblem {
	
	public Object[][] grid;
	public Neo neo;
	public static TelBooth telBooth;
	//max number of hostages neo can carry
	public int C;
	//matrix width
	public int M;
	//matrix height
	public int N;
	//number of agents
	public int K;
	//number of pills
	public int G;
	//number of hostages
	public int W;
	
	public Vector<Agent> agents = new Vector<Agent>();
	public Vector<Pill> pills = new Vector<Pill>();
	public Vector<Pad> pads = new Vector<Pad>();
	public Vector<Hostage> hostages = new Vector<Hostage>();
	public Vector<Hostage> carriedHostages = new Vector<Hostage>();
	
	public Matrix() {
		super();
	}
	
	public Object[][] getGrid() {
		return grid;
	}

	public void setGrid(Object[][] grid) {
		this.grid = grid;
	}

	public Neo getNeo() {
		return neo;
	}

	public void setNeo(Neo neo) {
		this.neo = neo;
	}

	public TelBooth getTelBooth() {
		return telBooth;
	}

	public void setTelBooth(TelBooth telBooth) {
		this.telBooth = telBooth;
	}

	public int getC() {
		return C;
	}

	public void setC(int c) {
		this.C = c;
	}

	public int getM() {
		return M;
	}

	public void setM(int m) {
		this.M = m;
	}

	public int getN() {
		return N;
	}

	public void setN(int n) {
		this.N = n;
	}

	public int getK() {
		return K;
	}

	public void setK(int k) {
		this.K = k;
	}

	public int getG() {
		return G;
	}

	public void setG(int g) {
		this.G = g;
	}

	public int getW() {
		return W;
	}

	public void setW(int w) {
		this.W = w;
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
	
	//helper function to generate a random int between two numbers
	public static int randomInt(int min, int max) {
		int res = new Random().nextInt((max-min)+1)+min;
		return res;
	}
	
	//helper function to ensure no overlapping on grid generation
	public static void findFreeCell(Object[][] grid, int x, int y) {
		while (grid[x][y] != null) {
			x = randomInt(0, grid.length-1);
			y = randomInt(0, grid[0].length-1);
		}
	}
	
	//initialize class variables
	public void initializeVariables(String gridString) {
	
		//initialize operators of parent class
		operators = new String[] {"up", "down", "left", "right", "carry", "drop", "takePill", "kill", "fly"};
		
		//dissect grid string 
		String[] removeColon = gridString.split(";");
		
		//m and n
		String[] dimensions = removeColon[0].split(",");
		N = Integer.parseInt(dimensions[0]);
		M = Integer.parseInt(dimensions[1]);
		//initialize grid
		grid = new Object[N][M];
		
		//c
		C = Integer.parseInt(removeColon[1]);
		
		//neo location
		String[] neoPos = removeColon[2].split(",");
		//initialize neo and add to grid
		neo = new Neo(Integer.parseInt(neoPos[0]), Integer.parseInt(neoPos[1]));
		grid[Integer.parseInt(neoPos[0])][Integer.parseInt(neoPos[1])] = neo;
		
		//telephone booth location
		String[] telBoothPos = removeColon[3].split(",");
		//initialize telephone booth and add to grid
		telBooth = new TelBooth(Integer.parseInt(telBoothPos[0]), Integer.parseInt(telBoothPos[1]));
		grid[Integer.parseInt(telBoothPos[0])][Integer.parseInt(telBoothPos[1])] = telBooth;
		
		//agents locations
		agents = new Vector<Agent>();
		String[] agentsPos = removeColon[4].split(",");
		//add to agents vector and add to grid
		for (int i = 0; i < agentsPos.length-1; i+=2) {
			Agent agent = new Agent(Integer.parseInt(agentsPos[i]), Integer.parseInt(agentsPos[i+1]));
			agents.add(agent);
			grid[Integer.parseInt(agentsPos[i])][Integer.parseInt(agentsPos[i+1])] = agent;
		}
		
		//pills locations
		pills = new Vector<Pill>();
		String[] pillsPos = removeColon[5].split(",");
		//add to pills vector and add to grid
		for (int i = 0; i < pillsPos.length-1; i+=2) {
			Pill pill = new Pill(Integer.parseInt(pillsPos[i]), Integer.parseInt(pillsPos[i+1]));
			pills.add(pill);
			grid[Integer.parseInt(pillsPos[i])][Integer.parseInt(pillsPos[i+1])] = pill;
		}

		//pads locations
		pads = new Vector<Pad>();
		String[] padsPos = removeColon[6].split(",");
		//add to pads vector and add to grid
		for (int i = 0; i < padsPos.length-3; i+=4) {
			//first pad
			Pad pad = new Pad(Integer.parseInt(padsPos[i]), Integer.parseInt(padsPos[i+1]), Integer.parseInt(padsPos[i+2]), Integer.parseInt(padsPos[i+3]));
			pads.add(pad);
			grid[Integer.parseInt(padsPos[i])][Integer.parseInt(padsPos[i+1])] = pad;
			//second pad
			pad = new Pad(Integer.parseInt(padsPos[i+2]), Integer.parseInt(padsPos[i+3]), Integer.parseInt(padsPos[i]), Integer.parseInt(padsPos[i+1]));
			pads.add(pad);
			grid[Integer.parseInt(padsPos[i+2])][Integer.parseInt(padsPos[i+3])] = pad;
		}

		//hostages locations
		hostages = new Vector<Hostage>();
		String[] hostagesPos = removeColon[7].split(",");
		//add to hostages vector and add to grid
		for (int i = 0; i < hostagesPos.length-2; i+=3) {
			Hostage hostage = new Hostage(Integer.parseInt(hostagesPos[i]), Integer.parseInt(hostagesPos[i+1]), Integer.parseInt(hostagesPos[i+2]));
			hostages.add(hostage);
			grid[Integer.parseInt(hostagesPos[i])][Integer.parseInt(hostagesPos[i+1])] = hostage;
		}
		//initialize initial state of parent class
		initState = new State(neo.getPosX(), neo.getPosY(), neo.getDamage(), neo.getPosX(), neo.getPosY(), agents, pills, pads, hostages, carriedHostages, 0);			
	}
	
	//check if current node is a goal state
	public boolean goalState(Node node) {		
		//get node state and hostages vector list 
		State nodeState = node.state;
		//check if neo is at telephone booth and his damage is less than 100
		if (nodeState.neoPosX == telBooth.posX && nodeState.neoPosY == telBooth.posY && nodeState.neoDamage < 100) {
			//check if there are no carried hostages needing to be dropped
			if (nodeState.carriedHostages.size() == 0) {
				//make sure no hostage is still alive in matrix and not saved
				for (int i = 0; i < nodeState.hostages.size(); i++) {
					if (!(nodeState.hostages.get(i).dead) && !(nodeState.hostages.get(i).saved)) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}

	//actions of neo and how it affects the state of node
	@SuppressWarnings("unchecked")
	public State neoAction(State state, String operator) {
		
		int neoPosXTemp = state.getNeoPosX();
		int neoPosYTemp = state.getNeoPosY();
		int neoDamageTemp = state.getNeoDamage();
		int neoPrevPosXTemp = state.getNeoPrevPosX();
		int neoPrevPosYTemp = state.getNeoPrevPosY();
		int killsTemp = state.getKills();
		
		Vector<Agent> agents = (Vector<Agent>) state.getAgents().clone();
		Vector<Agent> agentsTemp = new Vector<Agent>();
		for (int i = 0; i < agents.size(); i++) {
			agentsTemp.add(new Agent(agents.get(i).posX, agents.get(i).posY));
		}
		Vector<Pill> pills = (Vector<Pill>) state.getPills().clone();
		Vector<Pill> pillsTemp = new Vector<Pill>();
		for (int i = 0; i < pills.size(); i++) {
			pillsTemp.add(new Pill(pills.get(i).posX, pills.get(i).posY));
		}
		Vector<Pad> pads = (Vector<Pad>) state.getPads().clone();
		Vector<Pad> padsTemp = new Vector<Pad>();
		for (int i = 0; i < pads.size(); i++) {
			padsTemp.add(new Pad(pads.get(i).posX, pads.get(i).posY, pads.get(i).newPosX, pads.get(i).newPosY));
		}
		Vector<Hostage> hostages = (Vector<Hostage>) state.getHostages().clone();
		Vector<Hostage> hostagesTemp = new Vector<Hostage>();
		for (int i = 0; i < hostages.size(); i++) {
			hostagesTemp.add(new Hostage(hostages.get(i).posX, hostages.get(i).posY, hostages.get(i).damage, hostages.get(i).carried, hostages.get(i).becameAgent, hostages.get(i).saved, hostages.get(i).dead));
		}
		Vector<Hostage> carriedHostages = (Vector<Hostage>) state.getCarriedHostages().clone();
		Vector<Hostage> carriedHostagesTemp = new Vector<Hostage>();
		for (int i = 0; i < carriedHostages.size(); i++) {
			carriedHostagesTemp.add(new Hostage(carriedHostages.get(i).posX, carriedHostages.get(i).posY, carriedHostages.get(i).damage, carriedHostages.get(i).carried, carriedHostages.get(i).becameAgent, carriedHostages.get(i).saved, carriedHostages.get(i).dead));
		}
		
		if (operator.equals("left")) { 
			//check that movement is not out of bounds
			if (neoPosYTemp-1 >= 0) {
				//check that there is no agent or agent hostage in cell
				boolean action = true;
				for (int i = 0; i < agentsTemp.size(); i++) {
					if (agentsTemp.get(i).posX == neoPosXTemp && agentsTemp.get(i).posY == neoPosYTemp-1) {
						action = false;
					}
				}
				for (int i = 0; i < hostagesTemp.size(); i++) {
					if (hostagesTemp.get(i).posX == neoPosXTemp && hostagesTemp.get(i).posY == neoPosYTemp-1) {
						if ((hostagesTemp.get(i).becameAgent && !(hostagesTemp.get(i).dead)) || (!(hostagesTemp.get(i).becameAgent) && hostagesTemp.get(i).damage >= 98)) {
							action = false;
						}
					}
				}
				if (action) {
					//increase damage of any living hostage
					State.incDamageHostage(hostagesTemp);
					State.incDamageHostage(carriedHostagesTemp);
					//create a new state of situation for coming node
					State newState = new State(neoPosXTemp, neoPosYTemp-1, neoDamageTemp, neoPosXTemp, neoPosYTemp, agentsTemp, pillsTemp, padsTemp, hostagesTemp, carriedHostagesTemp, killsTemp);
					//check of state exists in hashtable to avoid repeated states
					if (states.containsKey(newState.toString())) {
						return null;
					}
					//if not, add to hashtable and return it
					else {
						states.put(newState.toString(), "");
						return newState;
					}
				}
			}
		}
		
		else if (operator.equals("right")) {
			//check that movement is not out of bounds
			if (neoPosYTemp+1 < M) {
				//check that there is no agent or agent hostage in cell
				boolean action = true;
				for (int i = 0; i < agentsTemp.size(); i++) {
					if (agentsTemp.get(i).posX == neoPosXTemp && agentsTemp.get(i).posY == neoPosYTemp+1) {
						action = false;
					}
				}
				for (int i = 0; i < hostagesTemp.size(); i++) {
					if (hostagesTemp.get(i).posX == neoPosXTemp && hostagesTemp.get(i).posY == neoPosYTemp+1) {
						if ((hostagesTemp.get(i).becameAgent && !(hostagesTemp.get(i).dead)) || (!(hostagesTemp.get(i).becameAgent) && hostagesTemp.get(i).damage >= 98)) {
							action = false;
						}
					}
				}
				if (action) {
					//increase damage of any living hostage
					State.incDamageHostage(hostagesTemp);
					State.incDamageHostage(carriedHostagesTemp);
					//create a new state of current situation for coming node
					State newState = new State(neoPosXTemp, neoPosYTemp+1, neoDamageTemp, neoPosXTemp, neoPosYTemp, agentsTemp, pillsTemp, padsTemp, hostagesTemp, carriedHostagesTemp, killsTemp);
					//check of state exists in hashtable to avoid repeated states
					if (states.containsKey(newState.toString())) {
						return null;
					}
					//if not, add to hashtable and return it
					else {
						states.put(newState.toString(), "");
						return newState;
					}
				}
			}
		}
		
		else if (operator.equals("up")) { 
			//check that movement is not out of bounds
			if (neoPosXTemp-1 >= 0) {
				//check that there is no agent or agent hostage in cell
				boolean action = true;
				for (int i = 0; i < agentsTemp.size(); i++) {
					if (agentsTemp.get(i).posX == neoPosXTemp-1 && agentsTemp.get(i).posY == neoPosYTemp) {
						action = false;
					}
				}
				for (int i = 0; i < hostagesTemp.size(); i++) {
					if (hostagesTemp.get(i).posX == neoPosXTemp-1 && hostagesTemp.get(i).posY == neoPosYTemp) {
						if ((hostagesTemp.get(i).becameAgent && !(hostagesTemp.get(i).dead)) || (!(hostagesTemp.get(i).becameAgent) && hostagesTemp.get(i).damage >= 98)) {
							action = false;
						}
					}
				}
				if (action) {
					//increase damage of any living hostage
					State.incDamageHostage(hostagesTemp);
					State.incDamageHostage(carriedHostagesTemp);
					//create a new state of current situation for coming node
					State newState = new State(neoPosXTemp-1, neoPosYTemp, neoDamageTemp, neoPosXTemp, neoPosYTemp, agentsTemp, pillsTemp, padsTemp, hostagesTemp, carriedHostagesTemp, killsTemp);
					//check of state exists in hashtable to avoid repeated states
					if (states.containsKey(newState.toString())) {
						return null;
					}
					//if not, add to hashtable and return it
					else {
						states.put(newState.toString(), "");
						return newState;
					}
				}
			}
		}
		
		else if (operator.equals("down")) { 
			//check that movement is not out of bounds
			if (neoPosXTemp+1 < N) {
				//check that there is no agent or agent hostage in cell
				boolean action = true;
				for (int i = 0; i < agentsTemp.size(); i++) {
					if (agentsTemp.get(i).posX == neoPosXTemp+1 && agentsTemp.get(i).posY == neoPosYTemp) {
						action = false;
					}
				}
				for (int i = 0; i < hostagesTemp.size(); i++) {
					if (hostagesTemp.get(i).posX == neoPosXTemp+1 && hostagesTemp.get(i).posY == neoPosYTemp) {
						if ((hostagesTemp.get(i).becameAgent && !(hostagesTemp.get(i).dead)) || (!(hostagesTemp.get(i).becameAgent) && hostagesTemp.get(i).damage >= 98)) {
							action = false;
						}
					}
				}
				if (action) {
					//increase damage of any living hostage
					State.incDamageHostage(hostagesTemp);
					State.incDamageHostage(carriedHostagesTemp);
					//create a new state of current situation for coming node
					State newState = new State(neoPosXTemp+1, neoPosYTemp, neoDamageTemp, neoPosXTemp, neoPosYTemp, agentsTemp, pillsTemp, padsTemp, hostagesTemp, carriedHostagesTemp, killsTemp);
					//check of state exists in hashtable to avoid repeated states
					if (states.containsKey(newState.toString())) {
						return null;
					}
					//if not, add to hashtable and return it
					else {
						states.put(newState.toString(), "");
						return newState;
					}
				}
			}
		}
		
		else if (operator.equals("carry")) { 
			//check if in same cell as neo
			for (int i = 0; i < hostagesTemp.size(); i++) {
				Hostage hostageTemp = hostagesTemp.get(i);
				if (hostageTemp.posX == neoPosXTemp && hostageTemp.posY == neoPosYTemp) {
					//check if hostage is not an agent and not carried and not saved
					if (!(hostageTemp.becameAgent) && !(hostageTemp.carried) && !(hostageTemp.saved)) {
						//check if carried hostages are less than C
						if (carriedHostagesTemp.size() < C) {
							hostageTemp.carried = true;
							carriedHostagesTemp.add(hostageTemp);
							hostagesTemp.remove(i);
							//increase damage of any living hostage
							State.incDamageHostage(hostagesTemp);
							State.incDamageHostage(carriedHostagesTemp);
							//create a new state of current situation for coming node
							State newState = new State(neoPosXTemp, neoPosYTemp, neoDamageTemp, neoPrevPosXTemp, neoPrevPosYTemp, agentsTemp, pillsTemp, padsTemp, hostagesTemp, carriedHostagesTemp, killsTemp);
							//check of state exists in hashtable to avoid repeated states
							if (states.containsKey(newState.toString())) {
								return null;
							}
							//if not, add to hashtable and return it
							else {
								states.put(newState.toString(), "");
								return newState;
							}
						}
					}
				}
			}
		}
		
		else if (operator.equals("drop")) { 
			//check if neo is in same cell as telephone booth
			if (telBooth.getPosX() == neoPosXTemp && telBooth.getPosY() == neoPosYTemp) {
				//check if neo is carrying hostages
				if (carriedHostagesTemp.size() != 0) {
					//drop hostages with carried status in the same cell as neo
					for (int i = 0; i < carriedHostagesTemp.size(); i++){
						//get hostage from carried
						Hostage hostageTemp = new Hostage(carriedHostagesTemp.get(i).getPosX(), carriedHostagesTemp.get(i).getPosY(), carriedHostagesTemp.get(i).damage, false, carriedHostagesTemp.get(i).becameAgent, carriedHostagesTemp.get(i).dead, carriedHostagesTemp.get(i).saved);
						//if hostage is not dead set saved to true
						if (!(hostageTemp.dead)) {
							hostageTemp.saved = true;
						}
						hostagesTemp.add(hostageTemp);
					}
					carriedHostagesTemp = new Vector<Hostage>();
					//increase damage of any living hostage
					State.incDamageHostage(hostagesTemp);
					//create a new state of current situation for coming node
					State newState = new State(neoPosXTemp, neoPosYTemp, neoDamageTemp, neoPrevPosXTemp, neoPrevPosYTemp, agentsTemp, pillsTemp, padsTemp, hostagesTemp, carriedHostagesTemp, killsTemp);
					//check of state exists in hashtable to avoid repeated states
					if (states.containsKey(newState.toString())) {
						return null;
					}
					//if not, add to hashtable and return it
					else {
						states.put(newState.toString(), "");
						return newState;
					}
				}
			}
		}

		else if (operator.equals("kill")) {
			//so neo wouldn't die
			if (neoDamageTemp < 80) {
				
				//to know if any agents were killed
				boolean killedAgents = false;
				//check that there is agent
				Vector<Agent> agents2 = (Vector<Agent>) agentsTemp.clone();
				Vector<Agent> agentsTemp2 = new Vector<Agent>();
				for (int i = 0; i < agents2.size(); i++) {
					agentsTemp2.add(new Agent(agents2.get(i).posX, agents2.get(i).posY));
				}
				for (int i = 0; i < agentsTemp2.size(); i++) {
					if (agentsTemp2.get(i).posX == neoPosXTemp+1 && agentsTemp2.get(i).posY == neoPosYTemp) {
						agentsTemp2.remove(i);
						i--;
						killedAgents = true;
						killsTemp++;
						continue;
					}
					if (agentsTemp2.get(i).posX == neoPosXTemp-1 && agentsTemp2.get(i).posY == neoPosYTemp) {
						agentsTemp2.remove(i);
						i--;
						killedAgents = true;
						killsTemp++;
						continue;
					}
					if (agentsTemp2.get(i).posX == neoPosXTemp && agentsTemp2.get(i).posY == neoPosYTemp+1) {
						agentsTemp2.remove(i);
						i--;
						killedAgents = true;
						killsTemp++;
						continue;
					}
					if (agentsTemp2.get(i).posX == neoPosXTemp && agentsTemp2.get(i).posY == neoPosYTemp-1) {
						agentsTemp2.remove(i);
						i--;
						killedAgents = true;
						killsTemp++;
						continue;
					}
				}
				
				//check that there agent hostage in cell
				Vector<Hostage> hostages2 = (Vector<Hostage>) hostagesTemp.clone();
				Vector<Hostage> hostagesTemp2 = new Vector<Hostage>();
				for (int i = 0; i < hostages2.size(); i++) {
					hostagesTemp2.add(new Hostage(hostages2.get(i).posX, hostages2.get(i).posY, hostages2.get(i).damage, hostages2.get(i).carried, hostages2.get(i).becameAgent, hostages2.get(i).saved, hostages2.get(i).dead));
				}
				for (int i = 0; i < hostagesTemp2.size(); i++) {
					if (hostagesTemp2.get(i).posX == neoPosXTemp+1 && hostagesTemp2.get(i).posY == neoPosYTemp) {
						//check the agent hostage is not dead
						if (hostagesTemp2.get(i).becameAgent && !(hostagesTemp2.get(i).dead) && !(hostagesTemp2.get(i).saved) && !(hostagesTemp2.get(i).carried)) {
							hostagesTemp2.get(i).dead = true;
							killedAgents = true;
							killsTemp++;
							continue;
						}
					}
					if (hostagesTemp2.get(i).posX == neoPosXTemp-1 && hostagesTemp2.get(i).posY == neoPosYTemp) {
						//check the agent hostage is not dead
						if (hostagesTemp2.get(i).becameAgent && !(hostagesTemp2.get(i).dead) && !(hostagesTemp2.get(i).saved) && !(hostagesTemp2.get(i).carried)) {
							hostagesTemp2.get(i).dead = true;
							killedAgents = true;
							killsTemp++;
							continue;
						}
					}
					if (hostagesTemp2.get(i).posX == neoPosXTemp && hostagesTemp2.get(i).posY == neoPosYTemp+1) {
						//check the agent hostage is not dead
						if (hostagesTemp2.get(i).becameAgent && !(hostagesTemp2.get(i).dead) && !(hostagesTemp2.get(i).saved) && !(hostagesTemp2.get(i).carried)) {
							hostagesTemp2.get(i).dead = true;
							killedAgents = true;
							killsTemp++;
							continue;
						}
					}
					if (hostagesTemp2.get(i).posX == neoPosXTemp && hostagesTemp2.get(i).posY == neoPosYTemp-1) {
						//check the agent hostage is not dead
						if (hostagesTemp2.get(i).becameAgent && !(hostagesTemp2.get(i).dead) && !(hostagesTemp2.get(i).saved) && !(hostagesTemp2.get(i).carried)) {
							hostagesTemp2.get(i).dead = true;
							killedAgents = true;
							killsTemp++;
							continue;
						}
					}
				}
				//check if neo killed any agents or agent hostages
				if (killedAgents) {
					neoDamageTemp += 20;
					//increase damage of any living hostage
					State.incDamageHostage(hostagesTemp);
					State.incDamageHostage(carriedHostagesTemp);
					//create new state
					State newState = new State(neoPosXTemp, neoPosYTemp, neoDamageTemp, neoPrevPosXTemp, neoPrevPosYTemp, agentsTemp2, pillsTemp, padsTemp, hostagesTemp2, carriedHostagesTemp, killsTemp);
					//check of state exists in hashtable to avoid repeated states
					if (states.containsKey(newState.toString())) {
						 return null;
					}
					//if not, add to hashtable and return it
					else {
						states.put(newState.toString(), "");
						return newState;
					}
				}
			}
		}
		
		else if (operator.equals("takePill")) { 
			//check that neo is in same cell as pill
			for (int i = 0; i < pillsTemp.size(); i++) {
				if (pillsTemp.get(i).posX == neoPosXTemp && pillsTemp.get(i).posY == neoPosYTemp) {
					pillsTemp.remove(i);
					//decrease damage of neo
					if (neoDamageTemp - 20 < 0) {
						neoDamageTemp = 0;
					}
					else {
						neoDamageTemp -= 20;
					}
					//decrease damage of hostages in grid
					for (int j = 0; j < hostagesTemp.size(); j++){
						Hostage hostage = hostagesTemp.get(j);
						//check that hostage is not saved and is not agent or dead
						if (!(hostage.dead) && !(hostage.becameAgent) && !(hostage.saved)){
							if (hostage.damage - 20 < 0) {
								hostagesTemp.get(j).damage = 0;
							}
							else {
								hostagesTemp.get(j).damage -= 20;
							}
						}
			        }
					for (int j = 0; j < carriedHostagesTemp.size(); j++){
						Hostage hostage = carriedHostagesTemp.get(j);
						//check that hostage is not saved and is not agent or dead
						if (!(hostage.dead) && !(hostage.becameAgent) && !(hostage.saved)){
							if (hostage.damage - 20 < 0) {
								carriedHostagesTemp.get(j).damage = 0;
							}
							else {
								carriedHostagesTemp.get(j).damage -= 20;
							}
						}
			        }
					State newState = new State(neoPosXTemp, neoPosYTemp, neoDamageTemp, neoPrevPosXTemp, neoPrevPosYTemp, agentsTemp, pillsTemp, padsTemp, hostagesTemp, carriedHostagesTemp, killsTemp);
					//check of state exists in hashtable to avoid repeated states
					if (states.containsKey(newState.toString())) {
						return null;
					}
					//if not, add to hashtable and return it
					else {
						states.put(newState.toString(), "");
						return newState;
					}
				}
			}	
		}
		
		else if (operator.equals("fly")) { 
			//check that neo is in same cell as pad
			int newPosX;
			int newPosY;
			for (int i = 0; i < padsTemp.size(); i++) {
				Pad pad = padsTemp.get(i);
				if (padsTemp.get(i).posX == neoPosXTemp && padsTemp.get(i).posY == neoPosYTemp) {
					newPosX = pad.newPosX;
					newPosY = pad.newPosY;
					//increase damage of any living hostage
					State.incDamageHostage(hostagesTemp);
					State.incDamageHostage(carriedHostagesTemp);
					//create a new state of current situation for coming node
					State newState = new State(newPosX, newPosY, neoDamageTemp, neoPosXTemp, neoPosYTemp, agentsTemp, pillsTemp, padsTemp, hostagesTemp, carriedHostagesTemp, killsTemp);
					//check of state exists in hashtable to avoid repeated states
					if (states.containsKey(newState.toString())) {
						return null;
					}
					//if not, add to hashtable and return it
					else {
						states.put(newState.toString(), "");
						return newState;
					}
				}
			}
		}
		else {
			return null;
		}
		return null;
	}
	
	public static String genGrid() {
		
		String res = "";
		
		//initialize grid dimensions (5x5 - 15x15)
		int N = randomInt(5, 15);
		int M = randomInt(5, 15);
		Object[][] grid = new Object[N][M];
		//add to grid string
		res += M + "," + N + ";";
		
		//initialize number of hostages neo can carry (1 - 4)
		int C = randomInt(1, 4);
		//add to grid string
		res += C + ";";
		
		//initialize neo location
		int x = randomInt(0, N-1);
		int y = randomInt(0, M-1);
		Neo neo = new Neo(x, y);
		grid[x][y] = neo;
		//add to grid string
		res += x + "," + y + ";";
		
		//initialize telephone booth location
		x = randomInt(0, N-1);
		y = randomInt(0, M-1);
		findFreeCell(grid, x, y);
		TelBooth telBooth = new TelBooth(x, y);
		grid[x][y] = telBooth;
		//add to grid string
		res += x + "," + y + ";";
		
		//initialize number of hostages (3 - 10)
		int W = randomInt(3, 10);
		
		//initialize number of pills (<=number of hostages)
		int G = randomInt(0, W);	
		
		//find number of free grid cells
		int numberOccupied = 2 + W + G;
		int freeGrid = (M * N) - numberOccupied;
		
		//initialize number of agents and pad pairs
		int K = 0;
		int countPadPairs = 0;
		for (int i = 0; i<freeGrid; i++) {
			x = randomInt(0, 2);
			if (x == 1) {
				K++;
			}
			else if (x == 2) {
				countPadPairs++;
				i++;
			}
		}
		
		//initialize locations of agents
		for (int i = 0; i<K; i++) {
			x = randomInt(0, N-1);
			y = randomInt(0, M-1);
			findFreeCell(grid, x, y);
			Agent agent = new Agent(x, y);
			grid[x][y] = agent;
			
			//add to grid string
			if (i==0) {
				res += x + "," + y;
			}
			else {
				res += "," + x + "," + y;
			}
		}
		
		res += ";";
		
		//initialize locations of pills
		for (int i = 0; i<G; i++) {
			x = randomInt(0, N-1);
			y = randomInt(0, M-1);
			findFreeCell(grid, x, y);
			Pill pill = new Pill(x, y);
			grid[x][y] = pill;
			
			//add to grid string
			if (i==0) {
				res += x + "," + y;
			}
			else {
				res += "," + x + "," + y;
			}
		}
		
		res += ";";
		
		//initialize locations of pads
		for (int i = 0; i<countPadPairs; i++) {
			x = randomInt(0, N-1);
			y = randomInt(0, M-1);
			findFreeCell(grid, x, y);
			Pad pad = new Pad(x, y);
			grid[x][y] = pad;
			
			//initialize accompanying pad location
			int xNew = randomInt(0, N-1);
			int yNew = randomInt(0, M-1);
			findFreeCell(grid, xNew, yNew);
			pad.setNewPosX(xNew);
			pad.setNewPosY(yNew);
			
			//initialize accompanying pads
			pad = new Pad(xNew, yNew);
			grid[xNew][yNew] = pad;
			pad.setNewPosX(x);
			pad.setNewPosY(y);
			
			//add to grid string
			if (i==0) {
				res += x + "," + y + "," + xNew + "," + yNew;
			}
			else {
				res += "," + x + "," + y + "," + xNew + "," + yNew;
				res += "," + xNew + "," + yNew + "," + x + "," + y;
			}
		}
		
		res += ";";
		
		//initialize locations of hostages
		for (int i = 0; i<W; i++) {
			x = randomInt(0, N-1);
			y = randomInt(0, M-1);
			findFreeCell(grid, x, y);
			Hostage hostage = new Hostage(x, y);
			grid[x][y] = hostage;
			
			//add to grid string
			if (i==0) {
				res += x + "," + y + "," + hostage.getDamage();
			}
			else {
				res += "," + x + "," + y + "," + hostage.getDamage();
			}
		}
		return res;
	}
	
	public static String solve(String grid, String strategy, boolean visualize) {
		//create new matrix search problem
		Matrix matrix = new Matrix();
		//initialize matrix variables
		matrix.initializeVariables(grid);
		
		//create vector to store nodes in to be able to visualize
		Vector<Node> nodes = new Vector<Node>();
		
		if (visualize) {
			//visualize node expanded/step
			System.out.println("Step 0: ");
			System.out.println("Carried hostages: 0");
			System.out.println();
			for (int i = 0; i<matrix.grid.length; i++) {
				for (int j = 0; j<matrix.grid[i].length; j++) {
					System.out.print(matrix.grid[i][j] + "  |  ");
				}
				System.out.println();
			}
			System.out.println();
			System.out.println();
		}
		
		//implement the generic search problem on the matrix using the requested strategy
		Node node = matrix.genericSearch(strategy);
		
		//initialize empty string for result of this method
		String result = "";

		//check if returned node state is goal state
		if (node != null) {
			State state = node.state;
			//if yes traverse back through parent nodes to create plan part of solution string
			while (node.parentNode != null) {
				result = node.operator + "," + result;
				//add to nodes vector for visualization
				if (visualize) {
					nodes.add(0, node);
				}
				//traverse parent node
				node = node.parentNode;
			}
			//remove extra last comma
			result = result.substring(0, result.length() - 1);
			result += ";" + state.deaths() + ";" + state.kills + ";" + matrix.getExpandedNodesCount();
			
			
			if (visualize) {
				for (int c = 0; c<nodes.size(); c++) {
					int count = c+1;
					System.out.println("Step " + count + ": " + nodes.get(c).operator);
					System.out.println("Carried hostages: " + nodes.get(c).state.carriedHostages.size());
					System.out.println();
					Object[][] outputGrid = new Object[matrix.N][matrix.M];
					outputGrid[matrix.telBooth.posX][matrix.telBooth.posY] = matrix.telBooth;
					for (int i = 0; i < nodes.get(c).state.agents.size(); i++) {
						outputGrid[nodes.get(c).state.agents.get(i).posX][nodes.get(c).state.agents.get(i).posY] = nodes.get(c).state.agents.get(i);
					}
					for (int i = 0; i < nodes.get(c).state.pills.size(); i++) {
						outputGrid[nodes.get(c).state.pills.get(i).posX][nodes.get(c).state.pills.get(i).posY] = nodes.get(c).state.pills.get(i);
					}
					for (int i = 0; i < nodes.get(c).state.pads.size(); i++) {
						outputGrid[nodes.get(c).state.pads.get(i).posX][nodes.get(c).state.pads.get(i).posY] = nodes.get(c).state.pads.get(i);
					}
					for (int i = 0; i < nodes.get(c).state.hostages.size(); i++) {
						if (!(nodes.get(c).state.hostages.get(i).dead)) {
							if (nodes.get(c).state.hostages.get(i).becameAgent) {
								outputGrid[nodes.get(c).state.hostages.get(i).posX][nodes.get(c).state.hostages.get(i).posY] = "Agent H";
							}
							else {
								outputGrid[nodes.get(c).state.hostages.get(i).posX][nodes.get(c).state.hostages.get(i).posY] = nodes.get(c).state.hostages.get(i);
							}
						}
					}
					for (int i = 0; i<outputGrid.length; i++) {
						for (int j = 0; j<outputGrid[0].length; j++) {
							if (i == nodes.get(c).state.neoPosX && j == nodes.get(c).state.neoPosY) {
								if (outputGrid[i][j] instanceof Neo) {
									System.out.print(outputGrid[i][j] + " (" + nodes.get(c).state.neoDamage + ")  |  ");
								}
								else {
									System.out.print(outputGrid[i][j] + ", Neo (" + nodes.get(c).state.neoDamage + ")   |  ");
								}
							}
							else {
								if (outputGrid[i][j] instanceof Neo) {
									System.out.print("null  |  ");
								}
								else {
									System.out.print(outputGrid[i][j] + "  |  ");
								}
							}
						}
						System.out.println();
					}
					System.out.println();
					System.out.println();
				}
			}
		} 
		else
			result = "No Solution";
		
		return result;
	}
	
	public static void main(String[] args){
		//testing generate grid function
		System.out.println(genGrid());
		System.out.println();
		
		//testing solve function
		String grid = "5,5;2;0,4;1,4;0,1,1,1,2,1,3,1,3,3,3,4;1,0,2,4;0,3,4,3,4,3,0,3;0,0,30,3,0,80,4,4,80";
		String solution = solve(grid, "UC", true);
		System.out.println(solution);	
	}
}
