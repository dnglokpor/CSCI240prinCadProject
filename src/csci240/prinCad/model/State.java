package csci240.prinCad.model;

import java.util.ArrayList;

/**State class:
 * class that implements a specific collection to use for the management of the model
 * of the PrinCad application. allows to track up to 5 different states of the
 * application and implement methods to move between these states.
 * @author dnglokpor
 */
public class State {
	// attributes
	private final int NUM_STATES;
	private ArrayList<ArrayList<CadItem>> states;
	private int current;
	
	/**constructor:
	 * initialize the states array and the current state tracker.
	 * and adds an empty list.
	 * @param uS the desired number of undo s
	 */
	public State(int uS) {
		NUM_STATES = uS + 1; // buffer for initial state
		states = new ArrayList<ArrayList<CadItem>>();
		current = 0;
		initStates(current);
		states.set(current, new ArrayList<CadItem>()); // initial empty state
	}
	
	/**constructor:
	 * initialize the states array and the current state tracker and stores the
	 * passed list as current state
	 * @param uS the desired number of undo s
	 * @param initialList an initial state to record 
	 */
	public State(int uS, ArrayList<CadItem> initialList) {
		NUM_STATES = uS; // undoSize
		states = new ArrayList<ArrayList<CadItem>>();
		current = 0;
		initStates(current);
		states.set(current, initialList); // initial state
	}
	
	// getters
	/**
	 * @return the current most state tracked
	 */
	public ArrayList<CadItem> getState() {
		if(states.get(current) != null)
			return states.get(current);
		return null;
	}
	
	// setters
	private void addState(ArrayList<CadItem> newState) {
		// dereference eldest state if needed
		if(full()){
			// states array is full
			for(int i= 0; i < NUM_STATES - 1; i++)
				states.set(i, states.get(i + 1));
		}
		// update current tracker
		trackNext();
		// save new state
		states.set(current, newState);
		// clear following states if needed
		if(!full()) {
			initStates(current + 1);
		}
	}
	
	/**
	 * adds a new CadItem to the current states and save changes to states.
	 * @param item the new item to add.
	 */
	public void add(CadItem item) {
		// create new state and add new item
		ArrayList<CadItem> newState = clone();
		newState.add(item);
		// update the states
		addState(newState);
	}
	
	/**
	 * reset the model for example when a new file command is received.
	 */
	public void clear() {
		current = 0; // reset tracker
		initStates(current); // clear all recorded states
		states.set(current, new ArrayList<CadItem>()); // record new empty state
	}
	
	/**
	 * deletes any item that was flagged as selected.
	 * updates the states.
	 */
	public void delete() {
		// create new state and delete items
		ArrayList<CadItem> newState = clone();
		newState.removeIf(item -> item._isSelected);
		// update the states
		addState(newState);
	}
	
	/**
	 * move to current state tracker right only if there are states
	 * to the right of the current state.
	 */
	public void redo() {
		if(!full() && states.get(current + 1) != null) {
			trackNext();
		}
	}
	
	/**
	 * move the current state tracker one state to the right
	 */
	private void trackNext() {
		if(!full())
			current++;
	}
	
	/**
	 * move the current state tracker one state to the left
	 */
	public void trackPrev() {
		if(!head())
			current--;
	}
	
	// helper
	/**
	 * @return returns a deep copy of the current most state.
	 */
	public ArrayList<CadItem> clone(){
		// make a new ArrayList
		ArrayList<CadItem> newState = new ArrayList<CadItem>();
		// deep copy each item
		getState().forEach((item) -> newState.add(item.clone()));
		// return it
		return newState;
	}
	/**
	 * set all cells to null.
	 * @param start the index at which to start nullifying - inclusive.
	 */
	private void initStates(int start) {
		// ensure capacity
		while(states.size() < NUM_STATES)
			states.add(null);
		// delete unneeded states
		for(int i = start; i < NUM_STATES; i++)
			states.set(i, null);
	}
	/**
	 * @return true if the current is the last state trackable
	 */
	private boolean full(){ return current == NUM_STATES - 1;}
	
	/**
	 * @return true if the current is the last state trackable
	 */
	private boolean head(){ return current == 0;}
}
