
public class Game {

	private char[][] gameField = new char[7][6];
		
	private char currentPlayer;
	private char lastPlayer;
	
	private boolean finished = false;
	
	public Game() {
		currentPlayer = 'b';
		lastPlayer = 'r';
		
	}
	
	public boolean addToRow(int row) {
		if(row >= gameField.length) return false;
		int lowestIndex = getLowest(row);
		if(lowestIndex < 0) 
			return false;
		
		gameField[row][lowestIndex] = (currentPlayer == 'b') ? 'b' : 'r';
				
		//Change player
		char hold = lastPlayer;
		lastPlayer = currentPlayer;
		currentPlayer = hold;
		
		return true;
	}
	
	private int getLowest(int row) {
		char[] charRow = gameField[row];
		int lowestIndex = -1;
		
		for(int i = charRow.length - 1; i >= 0; i--) {
			if(charRow[i] == 0) {
				lowestIndex = i;
				break;
			}
		}
		
		return lowestIndex;
	}
	
	public void printField() {

		checkWinner();
		
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("Wählen Sie einer der folgenen Spalten aus: ");
		
		System.out.print(" ");
		for(int i = 0; i < gameField.length; i++) {
			System.out.print((i + 1) + " ");
			if(i < gameField[0].length) 
				System.out.print("| ");
		}
		System.out.println();
		
		for(int i = 0; i < gameField.length * 4 - 1; i++) {
			System.out.print("_");
		}
		System.out.println();
		
		int offsetX = 0;
		for(int x = 0; x < gameField[0].length * 2 - 1 ; x++) {
			String outputRow = "";
			
			if(x % 2 != 0) {
				for(int i = 0; i < gameField.length * 4 - 1; i++) {
					System.out.print("-");
				}
				System.out.println("");
				offsetX++;
				continue;
			}
			
			for(int y = 0; y < gameField.length; y++) {
				outputRow += " " + getDisplayOfPlayer(gameField[y][x - offsetX]) + " ";
				
				if(y < gameField.length - 1)
					outputRow += "|";
				
			}
			
			if(x == 4 && !finished)
				outputRow += "\t Der Spieler > " + playerCharToString(currentPlayer) + " < ist dran";
			if(x == 4 && finished)
				if(checkWinner() == 'd')
					outputRow += "\t Niemand hat gewonnen!";
				else
					outputRow += "\t " + playerCharToString(lastPlayer) + " hat gewonnen!"; 
				
			
			System.out.println(outputRow);
		}
		
		if(!finished)
			System.out.print("\nIhre Auswahl => ");
		
	}
	
	private char checkWinner() {
		
		//check for 4 across
		for(int row = 0; row<gameField.length; row++){
			for (int col = 0;col < gameField[0].length - 3;col++){
				if (gameField[row][col] == lastPlayer   && 
						gameField[row][col+1] == lastPlayer &&
								gameField[row][col+2] == lastPlayer &&
										gameField[row][col+3] == lastPlayer){
					finished = true;
					return lastPlayer;
				}
			}			
		}
		//check for 4 up and down
		for(int row = 0; row < gameField.length - 3; row++){
			for(int col = 0; col < gameField[0].length; col++){
				if (gameField[row][col] == lastPlayer   && 
						gameField[row+1][col] == lastPlayer &&
								gameField[row+2][col] == lastPlayer &&
										gameField[row+3][col] == lastPlayer){
					finished = true;
					return currentPlayer;
				}
			}
		}
		//check upward diagonal
		for(int row = 3; row < gameField.length; row++){
			for(int col = 0; col < gameField[0].length - 3; col++){
				if (gameField[row][col] == lastPlayer   && 
						gameField[row-1][col+1] == lastPlayer &&
								gameField[row-2][col+2] == lastPlayer &&
										gameField[row-3][col+3] == lastPlayer){
					finished = true;
					return lastPlayer;
				}
			}
		}
		//check downward diagonal
		for(int row = 0; row < gameField.length - 3; row++){
			for(int col = 0; col < gameField[0].length - 3; col++){
				if (gameField[row][col] == lastPlayer   && 
						gameField[row+1][col+1] == lastPlayer &&
								gameField[row+2][col+2] == lastPlayer &&
										gameField[row+3][col+3] == lastPlayer){
					finished = true;
					return lastPlayer;
				}
			}
		}
		if(getEmptyFields() <= 0) {
			finished = true;
			return 'd';
		}
		return 0;
	}
	
	private int getEmptyFields() {
		int a = 0;
		for(int y = 0; y < gameField.length; y++) {
			for(int x = 0; x < gameField[0].length; x++) {
				if(gameField[y][x] == 0) a++;
			}
		}
		
		return a;
	}
	
	private String getDisplayOfPlayer(char c) {
		if(c == 0) return " ";		
		return ((c == 'b') ? "\033[0;104m" : "\033[0;101m") + "#\033[0m";
	}
	
	private String playerCharToString(char c) {
		if(c == 0) 
			return "NOVAL";
		if(c == 'r')
			return "Rot";
		if(c == 'b')
			return "Blau";
		
		return "";
	}

	public boolean isFinished() {
		return finished;
	}
	
}
