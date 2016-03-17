package model;

import java.awt.Point;
import java.io.*;
import java.util.Scanner;

public class LabyrinthMatrixImpl implements Labyrinth {

	public int matrix[][];
	public boolean visited[][];
	private int numberRows,numberColumns;
	private int currentColumn,currentRow;
	
	public LabyrinthMatrixImpl(){
		numberRows=0;
		numberColumns=0;
		matrix= new int[100][100];
		visited= new boolean[100][100];
	}
	@Override
	public void readMatrix(String filename){
		Scanner scanner=null;
		try{
			scanner = new Scanner(new File(filename));
			
			numberRows=scanner.nextInt();
			numberColumns=scanner.nextInt();
			for(int i=0;i<numberRows;i++)
			{
				for(int j=0;j<numberColumns;j++)
				{
					matrix[i][j]=scanner.nextInt();
					visited[i][j]=false;
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
	public void writeMatrix(){
		for(int i=0;i<numberRows;i++)
		{
			for(int j=0;j<numberColumns;j++)
			{
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println(" ");
		}
	}

	@Override
	public Integer getColumnCount() {
		
		return numberColumns;
	}

	@Override
	public boolean isFreeAt(int row,int column) {
		if(visited[row][column]==true || matrix[row][column]==1)
			return false;
		return true;
	}

	@Override
	public boolean isWallAt(int row,int column) {
		if(matrix[row][column]==3)
			return true;
		return false;
	}

	@Override
	public Point getStartCell() {
		for(int i=0;i<numberRows;i++)
		{
			for(int j=0;j<numberColumns;j++)
			{
				if(matrix[i][j]==-1)
					return new Point(i,j);
			}
					
		}
		return new Point(0,0);
	}

	@Override
	public Point getFinishCell() {
		for(int i=0;i<numberRows;i++)
		{
			for(int j=0;j<numberColumns;j++)
			{
				if(matrix[i][j]==2)
					return new Point(i,j);
			}
					
		}
		return new Point(0,0);
	}
	@Override
	public Integer getRowCount() {
		
		return numberRows;
	}
	@Override
	public Integer getCurrentRow() {
		return this.currentRow;
	}
	@Override
	public Integer getCurrentColumn() {
		
		return this.currentColumn;
	}
	@Override
	public void setCurrentColumn(int currentColumn) {
		this.currentColumn = currentColumn;
	}
	@Override
	public void setCurrentRow(int currentRow) {
		this.currentRow = currentRow;
	}
	@Override
	public boolean markCell(Integer row, Integer column) {
		
		if(0>row || row>=numberRows)
			return false;
		if(0>column || column>=numberColumns)
			return false;
		if(matrix[row][column]==1)
			return false;
		
		matrix[row][column]=3;
		return true;
	}
	@Override
	public void markVisited(int row, int column) {
		visited[row][column]=true;
		
	}
	
	@Override
	public void markUnVisited(int row, int column) {
		visited[row][column]=false;
		
	}
	@Override
	public boolean isMarkedAt(int row,int column){
		return visited[row][column];
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
