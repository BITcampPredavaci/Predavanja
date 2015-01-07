package abc.train.a;

import abc.train.LetterTruck;

public class AbcSong {
	public static void main(String[] args) {
		// create ABC train
		Train abcTrain = new Train();
		// get engine
		Engine engine = abcTrain.getEngine();
		// create first truck
		LetterTruck a = new LetterTruck('A');
		engine.attachTruck(a);
		LetterTruck lastTruck = a;
		for (int i=1; i<26; i++) {
			LetterTruck next = new LetterTruck((char) ('A' + i));
			lastTruck.attachNextTruck(next);
			lastTruck = next;
		}
		// sing song
		System.out.print(engine);
		LetterTruck letterTruck = engine.getFirstTruck();
		while (letterTruck != null) {
			System.out.print("-->" + letterTruck);
			letterTruck = letterTruck.nextTruck();
		}
		System.out.println("\nNow I know my ABC, next time won't u sing with me!");
	}
}
