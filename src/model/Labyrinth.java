package model;

import java.awt.Point;

public interface Labyrinth {

	
	public Integer getRowCount();
	public Integer getColumnCount();
	
	public Integer getCurrentRow();
	public Integer getCurrentColumn();
	
	public void setCurrentRow(int currentRow);
	public void setCurrentColumn(int currentColumn);
	
	public boolean isFreeAt(int row,int column);
	public boolean isWallAt(int row,int column);
	public void markVisited(int row, int column);
	public void markUnVisited(int row, int column);
	public boolean isMarkedAt(int row,int column);
	
	public Point getStartCell();
	public Point getFinishCell();
	public boolean markCell(Integer row, Integer column);
	
	public void readMatrix(String filename);
	public void writeMatrix();
	
	public int getContentAt(int i, int j);
}
