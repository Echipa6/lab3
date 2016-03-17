package model;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LabyrinthListImpl implements Labyrinth {

	Map<Point, Integer> map;
	Map<Point,Integer>visited;
	private int numberRows,numberColumns;
	private int currentColumn,currentRow;
	private Point startCell,finishCell;
	
	public LabyrinthListImpl(){
		numberRows=0;
		numberColumns=0;
		map=new HashMap<Point, Integer>();
		visited= new HashMap<Point, Integer>();
		startCell= new Point();
		finishCell=new Point();
	}
	
	@Override
	public Integer getRowCount() {
		return numberRows;
	}

	@Override
	public Integer getColumnCount() {
		return numberColumns;
	}

	@Override
	public Integer getCurrentRow() {
		return currentRow;
	}

	@Override
	public Integer getCurrentColumn() {
		return currentColumn;
	}

	@Override
	public void setCurrentRow(int currentRow) {
		this.currentRow=currentRow;

	}

	@Override
	public void setCurrentColumn(int currentColumn) {
		this.currentColumn=currentColumn;
	}

	@Override
	public boolean isFreeAt(int row, int column) {
		Point currentPoint=new Point(row,column);
		
		if(visited.containsKey(currentPoint))
			return false;
		if(map.containsKey(currentPoint))
		{
			if(map.get(currentPoint)==1)
				return false;
		}
		return true;
	}

	@Override
	public boolean isWallAt(int row, int column) {
		Point currentPoint=new Point(row,column);
		if(map.containsKey(currentPoint))
		{
			if(map.get(currentPoint)==1)
				return true;
		}
		return false;
		
	}

	@Override
	public void markVisited(int row, int column) {
		visited.put(new Point(row,column), 3);

	}

	@Override
	public void markUnVisited(int row, int column) {
		visited.remove(new Point(row,column));

	}

	@Override
	public boolean isMarkedAt(int row, int column) {
		if(visited.containsKey(new Point(row,column)))
		{
			return true;
		}
			
		return false;
	}

	@Override
	public boolean markCell(Integer row, Integer column) {
			if(0>row || row>=numberRows)
				return false;
			if(0>column || column>=numberColumns)
				return false;
			Point currentPoint= new Point(row,column);
			if(map.containsKey(currentPoint))
			{
				if(map.get(currentPoint)==1)
					return false;
			}
			
		map.put(currentPoint, 3);
		return true;
	}

	@Override
	public void readMatrix(String filename) {
		Scanner scanner=null;
		int valueOfCell;
		try{
			scanner = new Scanner(new File(filename));
			
			numberRows=scanner.nextInt();
			numberColumns=scanner.nextInt();
			for(int i=0;i<numberRows;i++)
			{
				for(int j=0;j<numberColumns;j++)
				{
					valueOfCell=scanner.nextInt();
					if(valueOfCell!=0)
					{
						map.put(new Point(i,j), valueOfCell);
					}
					if(valueOfCell==-1)
					{
						this.startCell.x=i;
						this.startCell.y=j;
					}
					if(valueOfCell==2)
					{
						this.finishCell.x=i;
						this.finishCell.y=j;
					}
				}
				
			}
		}catch (IOException e){
			System.out.println("fisierul nu a fost gasit");
		}
		finally{
			scanner.close();
		}

	}

	@Override
	public void writeMatrix() {
		Point currentPoint=new Point();
		for(int i=0;i<numberRows;i++)
		{
			for(int j=0;j<numberColumns;j++)
			{
				currentPoint.x=i;
				currentPoint.y=j;
				if(map.containsKey(currentPoint))
				{
					System.out.print(map.get(currentPoint)+" ");
				}
				else
				{
					System.out.print("0 ");
				}
				
			}
			System.out.println(" ");
		}

	}

	public int getNumberRows() {
		return numberRows;
	}

	public void setNumberRows(int numberRows) {
		this.numberRows = numberRows;
	}

	public int getNumberColumns() {
		return numberColumns;
	}

	public void setNumberColumns(int numberColumns) {
		this.numberColumns = numberColumns;
	}

	@Override
	public Point getStartCell() {
		return this.startCell;
	}

	@Override
	public Point getFinishCell() {
		return this.finishCell;
	}

	@Override
	public int getContentAt(int row, int column) {
		if(isMarkedAt(row,column))
			return 3;
		if(isWallAt(row,column))
			return 1;
		if(this.getStartCell().x==row && this.getStartCell().y==column)
			return -1;
		if(isFreeAt(row,column))
			return 0;
		return 2;
	}
}