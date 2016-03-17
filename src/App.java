
import controller.LabyrinthSolverAutomated;
import controller.LabyrinthSolverInteractive;
import model.Labyrinth;
import model.LabyrinthMatrixImpl;
import view.LabyrinthView;
import view.LabyrinthViewText;

public class App {

	public static void main(String[] args) {
		
		Labyrinth labyrinthModel= new LabyrinthMatrixImpl();
		LabyrinthView labyrinthView= new LabyrinthViewText();
		labyrinthModel.readMatrix("input.txt");
		
		
		//LabyrinthSolverInteractive labyrinthSolver= new LabyrinthSolverInteractive(labyrinthModel,labyrinthView);
		
		//labyrinthSolver.startSolver();
		
		LabyrinthSolverAutomated labyrinthSolver=new LabyrinthSolverAutomated(labyrinthModel,labyrinthView);
		labyrinthSolver.startSolver();
		
		
		
		
		
		

	}

}
