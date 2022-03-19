package code;

import java.util.Vector;
import java.util.Collections;
import java.util.Hashtable;

public abstract class GenericSearchProblem {
	
	public String [] operators;
	public int expandedNodesCount = 0;
	public State initState;
	public int iterativeDepth = 0;
	public Hashtable<String, String> states = new Hashtable<String, String>();

	public String[] getOperators() {
		return operators;
	}

	public void setOperators(String[] operators) {
		this.operators = operators;
	}

	public int getExpandedNodesCount() {
		return expandedNodesCount;
	}

	public void setExpandedNodesCount(int expandedNodesCount) {
		this.expandedNodesCount = expandedNodesCount;
	}

	public State getInitState() {
		return initState;
	}

	public void setInitState(State initState) {
		this.initState = initState;
	}

	public int getIterativeDepth() {
		return iterativeDepth;
	}

	public void setIterativeDepth(int iterativeDepth) {
		this.iterativeDepth = iterativeDepth;
	}

	public Hashtable<String, String> getStates() {
		return states;
	}

	public void setStates(Hashtable<String, String> states) {
		this.states = states;
	}

	public abstract State neoAction(State state, String operator);
	
	public abstract boolean goalState(Node node);
	
	public Node genericSearch(String strategy) {
		//create a new queue to add the nodes in
		Vector<Node> nodesQueue = new Vector<Node>();
		//add the root node with no parent, a path cost of 0, a depth of 0, and an initial state
		nodesQueue.add(new Node(null, 0, 0, null, this.initState));
		//expand nodes in queue while it is not empty
		while (!(nodesQueue.isEmpty())) {
			//remove the first node
			Node node = nodesQueue.remove(0);
			//check if it is a goal state, if yes, return it
			if (this.goalState(node)) {
				return node;
			}
			//if not add it to the queue based on the search strategy till goal state node is found
			nodesQueue = addToQueue(strategy, nodesQueue, expandNode(node));
		}	
		return null;
	}
	
	//method to expand current node
	public Vector<Node> expandNode(Node node) {
		//add to count of expanded nodes
		expandedNodesCount += 1;
		//create a vector to add the resulting nodes to it
		Vector<Node> resultingNodes = new Vector<Node>();
		//call the transition function on the current node once for each operator
		for (int i=0; i<operators.length; i++) {
			State state = neoAction(node.state, operators[i]);
			//if transition function returns an output (i.e., possible solution/next step), add a child node to the nodes queue
			if (state != null) {
				Node newNode = new Node(node, node.depth+1, state.cost, operators[i], state);
				resultingNodes.add(newNode);
			}
		}
		//return the nodes queue
		return resultingNodes;
	}
	
	//method to add the resulting nodes from the expansion of a node to the nodes queue
	public Vector<Node> addToQueue(String strategy, Vector<Node> nodesQueue, Vector<Node> resultingNodes){
		//breadth first - hence we add the resulting nodes at the end of the queued nodes vector
		if (strategy.equals("BF")) {
			nodesQueue.addAll(resultingNodes);
			return nodesQueue;
		}
		//depth first - hence we add the queued nodes at the end of the resulting nodes vector 
		else if (strategy.equals("DF")) {
			resultingNodes.addAll(nodesQueue);
			return resultingNodes;
		}
		//iterative deepening - hence 
		else if (strategy.equals("ID")) {
			//check if we are at the maximum depth we decided on yet, if yes add the queued nodes to the end of the resulting nodes
			if (!(resultingNodes.isEmpty()) && resultingNodes.get(0).depth <= iterativeDepth){
				resultingNodes.addAll(nodesQueue);
				return resultingNodes;
			}
			//we check if we have no more nodes to expand so we reached the maximum depth, so we add 1 to the depth and repeat
			else if (nodesQueue.isEmpty()) {
				iterativeDepth += 1;
				states = new Hashtable<String, String>();
				//create a new queue with only the root node with no parent node, a depth of 0, a cost of 0, no operators and an initial state
				nodesQueue.add(new Node(null, 0, 0, null, initState)); 
				return nodesQueue;
			}
			return nodesQueue;
		}
		//uniform cost - hence we need to sort after the expansion on a breadth first traversal based on the node accumulative costs
		else if (strategy.equals("UC")) {
			nodesQueue.addAll(resultingNodes);
			Collections.sort(nodesQueue);
			//return nodesQueue;
			return sort(nodesQueue, "UC");
		}
		else if (strategy.equals("GR1")) {
			nodesQueue.addAll(resultingNodes);
			return sort(nodesQueue, "GR1");
		}
		else if (strategy.equals("GR2")) {
			nodesQueue.addAll(resultingNodes);
			return sort(nodesQueue, "GR2");
		}
		else if (strategy.equals("AS1")) {
			nodesQueue.addAll(resultingNodes);
			return sort(nodesQueue, "AS1");
		}
		else if (strategy.equals("AS2")) {
			nodesQueue.addAll(resultingNodes);
			return sort(nodesQueue, "AS2");
		}
		else {
			return nodesQueue;
		}
	}
	
	//insertion sort
	public Vector<Node> sort(Vector<Node> nodesQueue, String strategy) { 
        int nodesQueueLength = nodesQueue.size(); 
        for (int i = 1; i < nodesQueueLength; ++i) {
            if (strategy.equals("UC")) {
            	int key = nodesQueue.get(i).cost;
            	 int j = i - 1; 
                 while (j >= 0 && nodesQueue.get(j).cost > key) { 
                 	nodesQueue.get(j+1).cost = nodesQueue.get(j).cost; 
                     j = j - 1; 
                 } 
                 nodesQueue.get(j+1).cost = key; 
            }
            else if (strategy.equals("GR1")) {
            	int key = nodesQueue.get(i).state.g1Cost;
            	 int j = i - 1; 
                 while (j >= 0 && nodesQueue.get(j).state.g1Cost > key) { 
                 	nodesQueue.get(j+1).state.g1Cost = nodesQueue.get(j).state.g1Cost; 
                     j = j - 1; 
                 } 
                 nodesQueue.get(j+1).state.g1Cost = key; 
            }
            else if (strategy.equals("GR2")) {
            	int key = nodesQueue.get(i).state.g2Cost;
            	 int j = i - 1; 
                 while (j >= 0 && nodesQueue.get(j).state.g2Cost > key) { 
                 	nodesQueue.get(j+1).state.g2Cost = nodesQueue.get(j).state.g2Cost; 
                     j = j - 1; 
                 } 
                 nodesQueue.get(j+1).state.g2Cost = key; 
            }
            else if (strategy.equals("AS1")) {
            	int key = nodesQueue.get(i).state.a1Cost;
            	 int j = i - 1; 
                 while (j >= 0 && nodesQueue.get(j).state.a1Cost > key) { 
                 	nodesQueue.get(j+1).state.a1Cost = nodesQueue.get(j).state.a1Cost; 
                     j = j - 1; 
                 } 
                 nodesQueue.get(j+1).state.a1Cost = key; 
            }
            else if (strategy.equals("AS2")) {
            	int key = nodesQueue.get(i).state.a2Cost;
            	 int j = i - 1; 
                 while (j >= 0 && nodesQueue.get(j).state.a2Cost > key) { 
                 	nodesQueue.get(j+1).state.a2Cost = nodesQueue.get(j).state.a2Cost; 
                     j = j - 1; 
                 } 
                 nodesQueue.get(j+1).state.a2Cost = key; 
            }
        }
        return nodesQueue;
    }
}

