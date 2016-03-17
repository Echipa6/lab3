package controller;

import model.Labyrinth;
import model.LabyrinthMatrixImpl;
import view.LabyrinthView;

public interface LabyrinthSolver {
	
	
	public Labyrinth getLabyrinth();
	public void setLabyrinth(LabyrinthMatrixImpl aux);
	public LabyrinthView getView();
	public void setView(LabyrinthView view);
	public boolean nextCellToExplore(int option);
	
	public void getStartCell();
	public void startSolver();
	

}
