
package controller;

import java.util.Scanner;

import model.Labyrinth;
import model.LabyrinthMatrixImpl;
import view.LabyrinthView;


public class LabyrinthSolverInteractive implements LabyrinthSolver {

	private Labyrinth model;
	private LabyrinthView view;
	private LabyrinthObserverPrint observer1;
	
	Scanner keyboard;
	
	public LabyrinthSolverInteractive(Labyrinth model,LabyrinthView view)
	{
		this.model=model;
		this.setView(view);
		keyboard = new Scanner(System.in);
		observer1=new LabyrinthObserverPrint();
	}

	@Override
	public Labyrinth getLabyrinth() {
		return model;
	}


	@Override
	public void setLabyrinth(LabyrinthMatrixImpl model) {
		this.model=model;
	}
	
	public int readNextMove(){
		
			System.out.println("enter new command: U(p), D(own), L(eft), R(ight)");
			String move=keyboard.next();
			switch(move)
			{
				case "U": return 1;
				case "u": return 1;
				
				case "D": return 2;
				case "d": return 2;
				
				case "L": return 3;
				case "l": return 3;
				
				case "R": return 4;
				case "r": return 4;
				
				default: System.out.println("Command invalid.Try again!"); return 0;
			}
			
		
	}

	@Override
	public boolean nextCellToExplore(int option) {
		boolean validateMove;
		switch(option)
		{
			case 1:  validateMove=moveUp();
						observer1.processCell('U', validateMove);
						break;
			case 2:  validateMove=moveDown();
						observer1.processCell('D', validateMove);
						break;
			case 3:  validateMove=moveLeft();
						observer1.processCell('L', validateMove);
						break;
			case 4: validateMove=moveRight();
						observer1.processCell('R', validateMove);
						break;
			default: validateMove=false;
		}
		//Observer -> adauga mutare la solutie
		return validateMove;
		
	}
	
	private boolean moveRight() {
		
		if(model.markCell(model.getCurrentRow(), model.getCurrentColumn()+1)==true)
		{
			model.setCurrentColumn(model.getCurrentColumn()+1);
			return true;
		}
		return false;
		
	}

	private boolean moveLeft() {
		if(model.markCell(model.getCurrentRow(), model.getCurrentColumn()-1)==true)
		{
			model.setCurrentColumn(model.getCurrentColumn()-1);
			return true;
		}
		return false;
	}

	private boolean moveDown() {
		
		if(model.markCell(model.getCurrentRow()+1, model.getCurrentColumn())==true)
		{
			model.setCurrentRow(model.getCurrentRow()+1);
			return true;
		}
		return false;
		
	}

	private boolean moveUp() {
		if(model.markCell(model.getCurrentRow()-1, model.getCurrentColumn())==true)
		{
			model.setCurrentRow(model.getCurrentRow()-1);
			return true;
		}
		return false;
	}
	
	
	@Override
	public LabyrinthView getView() {
		return view;
	}
	
	@Override
	public void setView(LabyrinthView view) {
		this.view = view;
	}
	
	@Override
	public void getStartCell()
	{
		Integer startCell=model.getStartCell();
		Integer row=startCell/model.getColumnCount();
		Integer column=startCell%model.getColumnCount();
		
		if(model.markCell(row, column)==true)
		{
			model.setCurrentRow(model.getCurrentRow());
			model.setCurrentRow(row);
			model.setCurrentColumn(column);
		}
		else
		{
			System.out.println("Cannot find the start Cell!");
		}
		
	}
	@Override
	public void startSolver()
	{
		getStartCell();
		Integer finishRow=model.getFinishCell()/model.getColumnCount();
		Integer finishColumn=model.getFinishCell()%model.getColumnCount();
		while(finishRow!=model.getCurrentRow() || finishColumn!=model.getCurrentColumn())
		{
			model.writeMatrix();
			nextCellToExplore(this.readNextMove());
			
				
			
			
		}
		//Observer solutie
		observer1.processSolution();
		
		
	}

}
