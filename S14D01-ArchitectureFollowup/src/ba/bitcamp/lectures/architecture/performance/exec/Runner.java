package ba.bitcamp.lectures.architecture.performance.exec;

public class Runner {
	public static void main(String[] args) throws Exception {
		int numTasks = 10;
		int numOps = 10000000;
		
		new SequentialExecution(numTasks, numOps);
		System.out.println("Parallel");
		new ParallelExecution(numTasks, numOps);
		
		System.out.println("Flow");
		new FlowExecution(numTasks, numOps).run();
	}
}
