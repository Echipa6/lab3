package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;





public class LabyrinthObserverStore implements  LabyrinthObserver {

	 List<Character> currentPath;
	 List<ArrayList<Character>> allPaths;
	@Override
	public void processCell(char move, boolean canmove) {
		
		
		currentPath.add(move);
	}

	@Override
	public void processSolution() {
		ArrayList<Character> tempPath=new ArrayList<Character>();
		//System.out.println("Solution:");
		for(char move:currentPath)
		{
			tempPath.add(move);
			//System.out.print(move);
		}
		allPaths.add(tempPath);
		//System.out.println();
		currentPath.clear();
	}

	@Override
	public void sortPaths()
	{
		Collections.sort(allPaths, new Comparator<ArrayList<Character>>() 
		{
		    @Override
		    public int compare(ArrayList<Character> one, ArrayList<Character> two) 
		    {
		        	if (one.size()>two.size()) return 1;
		        	if (one.size()<two.size()) return -1;
		        	return 0;
		    }
		});
	}
	
	public LabyrinthObserverStore()
	{
		currentPath= new ArrayList<Character>();
		allPaths=new ArrayList<ArrayList<Character>>();
	}

	@Override
	public void printAllSolutions() {
		// TODO Auto-generated method stub

		System.out.println("All paths are:");
		for(ArrayList<Character> path:allPaths)
		{
			for(char move:path)
			{
				System.out.print(move+" ");
			}
			System.out.println();
		}
	}
	
	

}
