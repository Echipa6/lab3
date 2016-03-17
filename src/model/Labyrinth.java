package model;



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
	
	public Integer getStartCell();
	public Integer getFinishCell();
	public boolean markCell(Integer row, Integer column);
	
	public void readMatrix(String filename);
	
	public void writeMatrix();

}
