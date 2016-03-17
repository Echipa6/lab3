package controller;

public interface LabyrinthObserver {
	
	public void processCell(char move, boolean canmove);
	public void processSolution();
	public void sortPaths();
	public void printAllSolutions();
	
	

}
