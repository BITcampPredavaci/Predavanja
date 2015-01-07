package abc.train.b;

import abc.train.LetterTruck;

public class AbcSong {
	public static void main(String[] args) {
		// create ABC train
		Train abcTrain = new Train();
		for (int i=0; i<26; i++) {
			LetterTruck truck = new LetterTruck((char) ('A' + i));
			abcTrain.attachTruck(truck);
		}
		// sing song
		System.out.print(abcTrain);
		System.out.println("\nNow I know my ABC, next time won't u sing with me!");
	}
}
