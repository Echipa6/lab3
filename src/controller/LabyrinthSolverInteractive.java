
package controller;

import java.util.Scanner;

import model.Labyrinth;
import model.LabyrinthMatrixImpl;
import view.LabyrinthView;


public class LabyrinthSolverInteractive implements LabyrinthSolver {

	private Labyrinth model;
	private LabyrinthView view;
	
	Scanner keyboard;
	
	public LabyrinthSolverInteractive(Labyrinth model,LabyrinthView view)
	{
		this.model=model;
		this.setView(view);
		keyboard = new Scanner(System.in);
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
						break;
			case 2:  validateMove=moveDown();
						break;
			case 3:  validateMove=moveLeft();
						break;
			case 4: validateMove=moveRight();
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
		Integer startCell=model.getStartCell().x;
		Integer row=model.getStartCell().y;
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
		Integer finishRow=model.getFinishCell().x;
		Integer finishColumn=model.getFinishCell().y;
		while(finishRow!=model.getCurrentRow() || finishColumn!=model.getCurrentColumn())
		{
			model.writeMatrix();
			if(nextCellToExplore(this.readNextMove()))
			{
				
				System.out.println("Mutare valida");
			}
			else
			{
				System.out.println("Mutare invalida");
			}
			
		}
		//Observer solutie
		
	}

}
