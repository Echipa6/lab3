package controller;

import java.util.ArrayList;
import java.util.List;

public class LabyrinthObserverPrint implements LabyrinthObserver {

	 List<Character> path;
	 
	@Override
	public void processCell(char move, boolean canmove) {
		
		if(canmove){
			path.add(move);
			System.out.println("Mutare valida");
			
		}
		else
		{
			System.out.println("Mutare invalida");
		}
		
		
			
		
	}

	@Override
	public void processSolution() {
		
		System.out.println("Problem Solved \n Your path is:");
		for(char c:path)
		{
			System.out.print(c+" ");
		}
		System.out.println();
		path.clear();
		
	}
	
	LabyrinthObserverPrint(){
		 path= new ArrayList<Character>();
	}

	@Override
	public void sortPaths() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printAllSolutions() {
		// TODO Auto-generated method stub
		
	}

}
