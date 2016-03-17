package view;

import model.Labyrinth;

public class LabyrinthViewText implements LabyrinthView {
	
	private Labyrinth model;
	
	
	public LabyrinthViewText()
	{
		
	}
	public LabyrinthViewText(Labyrinth model){
		this.model=model;
	}
	@Override
	public Labyrinth getLabyrinth() {
		// TODO Auto-generated method stub
		return this.model;
	}

	@Override
	public void setLabyrinth(Labyrinth model) {
	
		this.model=model;

	}
	
	@Override
	public String toString()
	{
		StringBuilder result = new StringBuilder();
		
		int nrRows,nrColumn;
		
		nrRows=model.getRowCount();
		nrColumn=model.getColumnCount();
		for(int i=0;i<nrRows;i++)
		{
			for(int j=0;j<nrColumn;j++)
			{
				int content=model.getContentAt(i,j);
			
				result.append('|');
				if(content==-1) result.append('S');
				if(content==1) result.append('*');
				if(content==0) result.append(' ');
				if(content==2) result.append('F');
				if(content==3) result.append('x');
			}
			result.append('|');
			result.append('\n');
		}
		
		return result.toString();
		
	}

}
