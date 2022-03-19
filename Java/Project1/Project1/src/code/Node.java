package code;

//we implement comparable to be able to sort the node queue we create later based on the node costs
public class Node implements Comparable<Node> {
	
	public Node parentNode;
	public int depth;
	public int cost;
	public String operator;
	public State state;
	
	public Node(Node parentNode, int depth, int cost, String operator, State state) {
		this.parentNode = parentNode;
		this.depth = depth;
		this.cost = cost;
		this.operator = operator;
		this.state = state;
	}

	public Node getparentNode() {
		return parentNode;
	}

	public void setparentNode(Node parentNode) {
		this.parentNode = parentNode;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	//we override the comparable class so when we compare between two nodes, we compare them by their cost
	public int compareTo(Node node) {
		return this.cost - node.getCost();
	}
}
