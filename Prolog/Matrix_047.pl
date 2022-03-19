:- consult('KB.pl').

/*initialize M, N, X and Y (Neo's location) and the hostages locations and carried to 0 as initial state s0*/
state(M,N,X,Y,L,0,s0):-
	grid(M,N),
    	neo_loc(X,Y),
    	hostages_loc(L).

/*infer new state using successor state axioms on the current situation*/
state(M,N,X,Y,L,C,R) :-
    	state(M,N,X1,Y1,L1,C1,R1),
    	(
	/*up; check if x-1 is within grid bounds and create new state if yes*/
	(X is X1 - 1,
    	X >= 0,
    	Y1 = Y,
    	C1 = C,
    	L1 = L,
    	R = result(up,R1));
	/*down; check if x+1 is within grid bounds and create new state if yes*/
	(X is X1 + 1,
    	X < M,
    	Y1 = Y,
    	C1 = C,
    	L1 = L,
    	R = result(down,R1));
	/*left; check if y-1 is within grid bounds and create new state if yes*/
	(Y is Y1 - 1,
    	Y >= 0,
    	X1 = X,
    	C1 = C,
    	L1 = L,
    	R = result(left,R1));
	/*right; check if y+1 is within grid bounds and create new state if yes*/    	
	(Y is Y1 + 1,
    	Y < N,
    	X = X1,
    	C1 = C,
    	L1 = L,
    	R = result(right,R1));
	/*carry; check if neo can carry a hostage at current location; check if hostages exists in current location and neo is not carrying maximum number he can at a time and create new state if possible*/
    	(canCarry(X1,Y1,L1,L),
    	Y = Y1,
    	X = X1,
    	C is C1 + 1,
    	capacity(MaxCapacity),
    	C1 < MaxCapacity,
    	R = result(carry,R1));
	/*drop; check if neo can is at telephone booth and is carrying hostages to drop them and create new sate accordingly*/
    	(booth(X1,Y1),
    	C1 > 0,
    	C = 0,
    	X = X1,
    	Y = Y1,
    	L = L1, 
    	R = result(drop,R1))
	).

/*check if neo can carry a hostage at current location; check if any remaining hostage exists in current location*/
/*if yes, remove this hostage form the hostages list*/
canCarry(X,Y,RemainingHostages,R):-
    	member([X,Y],RemainingHostages),
	remove(X,Y,RemainingHostages,R).

/*remove from list function implemenation*/
/*base case; if the position is the same as the first location in the list*/
remove(X,Y,[[X,Y]|T],T).
/*recurse*/
remove(X,Y,[[Z,M]|T1],[[Z,M]|T2]):-
    	remove(X,Y,T1,T2).

/*goal function*/
/*case 1: if there are no more hostages to save, send neo to telephone booth*/
goal(S):-
    	hostages_loc([]),
    	state(M,N,X,Y,[],0,S),
    	grid(M,N),
    	booth(X,Y).
/*case 2: save all remaining hostages*/
/*print drop as a last action, as when neo drops the final carried hostages, he will be at a goal state*/
goal(S):-
    	S = result(drop,_),
    	state(M,N,X,Y,[],0,S),
	grid(M,N),
    	booth(X,Y).


