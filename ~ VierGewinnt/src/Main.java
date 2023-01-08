import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Game game = new Game();
		Scanner scanner = new Scanner(System.in);
		
		while(!game.isFinished()) {
			game.printField();
			
			while(!scanner.hasNextInt()) {
				scanner.next();
				game.printField();
			}
			
			int chosenRow = scanner.nextInt();			
			game.addToRow(chosenRow - 1);
			
		}
		
		System.exit(0);
		
	}
	
}
