package controller;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import model.Labyrinth;
import model.LabyrinthMatrixImpl;
import view.LabyrinthView;

public class LabyrinthSolverAutomated implements LabyrinthSolver {

	private Labyrinth model;
	private LabyrinthView view;
	private LabyrinthObserver observerStore;
	private LabyrinthObserver observerPrint;
	Stack<Point> stCoordinate = new Stack<Point>();
	
	Scanner keyboard;
	@Override
	public Labyrinth getLabyrinth() {
		// TODO Auto-generated method stub
		return model;
	}

	public LabyrinthSolverAutomated(Labyrinth model,LabyrinthView view)
	{
		this.model=model;
		this.view=view;
		keyboard = new Scanner(System.in);
		observerStore=new LabyrinthObserverStore();
		observerPrint=new LabyrinthObserverPrint();
	}
	
	@Override
	public void setLabyrinth(LabyrinthMatrixImpl model) {
		this.model=model;

	}

	@Override
	public LabyrinthView getView() {
		
		return this.view;
	}

	@Override
	public void setView(LabyrinthView view) {
		this.view=view;

	}

	@Override
	public boolean nextCellToExplore(int option) {
		 switch(option)
		   {
		    	case 0: solve(model.getCurrentRow()+1,model.getCurrentColumn());
		    		break;
		    	case 1: solve(model.getCurrentRow()-1,model.getCurrentColumn());
		    		break;
		    	case 2: solve(model.getCurrentRow(),model.getCurrentColumn()+1);
		    		break;
		    	case 3: solve(model.getCurrentRow(),model.getCurrentColumn()-1);
		    		break;
		    	default: System.out.println("Something is wrong!");
		   }
		   
		   while(stCoordinate.peek().x!=model.getCurrentRow() || stCoordinate.peek().y!=model.getCurrentColumn())
			{
			   Point pt2=new Point();
			   pt2=stCoordinate.pop();
			   model.markUnVisited(pt2.x,pt2.y);
			}
		return false;

	}

	@Override
	public void getStartCell() {
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
	public void startSolver() {
		getStartCell();
		for (int x = 0; x < model.getRowCount(); x++)
            for (int y = 0; y < model.getColumnCount(); y++)
                model.markUnVisited(x, y);
        
        solve(model.getCurrentRow(), model.getCurrentColumn());
        
      observerStore.sortPaths();
      observerStore.printAllSolutions();
	}
	
	private void solve(int row, int column) {
        if (row < 0 || column < 0 || row >= model.getRowCount() || column >= model.getColumnCount()) return;
        if ( model.isFreeAt(row, column)==false) return;
        
       Integer finishRow=model.getFinishCell()/model.getColumnCount();
 	   Integer finishColumn=model.getFinishCell()%model.getColumnCount();
 	   if(row==finishRow && column==finishColumn)
 		{
  	       Point pt=new Point();
  	       pt.x=row;
  	       pt.y=column;
  	       stCoordinate.push(pt);
  			
 			
 			traceRoute();
 			//Observer afisare
 			
 			//observerPrint.processSolution();
 			observerStore.processSolution();
 			return;
 			
 		
 		}
 	  model.markVisited(row,column);
      Point pt=new Point();
      pt.x=row;
      pt.y=column;
      stCoordinate.push(pt);
      
     
      
 	   for(int i=0;i<4;i++)
 	   {
 		  model.setCurrentRow(row);
 		  model.setCurrentColumn(column);
 		
 		  this.nextCellToExplore(i);
 		   
 	   }

        

    }
	
	public void traceRoute()
	{
		List<Point> points=new ArrayList<Point>();
		Stack<Point> aux = new Stack<Point>();
		aux.addAll(stCoordinate);
		while(aux.isEmpty()==false)
		{
			Point bb=aux.peek();
			
			points.add(0, bb);
			aux.pop();
		}
		
		for(int i=0;i<points.size()-1;i++)
		{
			Point A,B;
			A=points.get(i);
			B=points.get(i+1);
			if(A.x<B.x) 
				{
					observerStore.processCell('D', true);
					//observerPrint.processCell('D', true);
				}
			if(A.x>B.x) 
				{
					observerStore.processCell('U', true);
					//observerPrint.processCell('U', true);
				}
			if(A.y<B.y)
				{
					observerStore.processCell('R', true);
					//observerPrint.processCell('R', true);
				}
			if(A.y>B.y)
				{
					observerStore.processCell('L', true);
					//observerPrint.processCell('L', true);
				}
		}
	}
	public void afisare()
	{
		Stack<Point> aux = new Stack<Point>();
		aux.addAll(stCoordinate);
		
		while(aux.isEmpty()==false)
		{
			Point bb=aux.peek();
			
			System.out.println(bb.x+" "+bb.y);
			aux.pop();
		}
		System.out.println();
		System.out.println();
		
	}

}


