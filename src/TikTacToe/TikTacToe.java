package TikTacToe;

import java.util.Scanner;

public class TikTacToe {
	
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		String play = "Yes";
		do {
		String[] xo = {" ", " ", " ",
					   " ", " ", " ",
					   " ", " ", " "};
		
		// Make first move of game
		int compChoice = readyStart(xo);
		int playerChoice, whoWon;
		
		// Determines who plays as X and O
		if(compChoice == 0) {
			System.out.println("You get the first move !!");
			System.out.println("What will your move be?\n");
			playerChoice = playerXOChoice(stdIn);
			compChoice = (playerChoice == 1 ? 2 : 1);
		}
		playerChoice = compChoice == 1 ? 2 : 1;
		
		// Play the game
		whoWon = play(stdIn, xo, playerChoice, compChoice);
		if(whoWon == 1) 
			System.out.println("You Won!!");
		else if(whoWon == 2) 
			System.out.println("The Computer Won :(");
		else 
			System.out.println("Its a Tie...");
		
		// Check if wants to play again
		do {
			System.out.print("\nWhould you like to play again? (Yes or No) :");
			play = stdIn.next();
		}while(!(play.equalsIgnoreCase("yes") || play.equalsIgnoreCase("no")));
		}while(play.equalsIgnoreCase("yes"));
		
		// Closes scanner
		stdIn.close();
	}
	
	
	
	
	/**
	 * The Display for the game board
	 * @param xo array
	 */
	public static void printBoard(String[] xo) {
		System.out.println();
		System.out.println("[" + xo[0] + "]" + "[" + xo[1] + "]" + "[" + xo[2] + "]");
		System.out.println("[" + xo[3] + "]" + "[" + xo[4] + "]" + "[" + xo[5] + "]");
		System.out.println("[" + xo[6] + "]" + "[" + xo[7] + "]" + "[" + xo[8] + "]");
		System.out.println();
	}
	
	
	
	
	/**
	 * Figures out who will play first in the game
	 * If computer plays first makes its first move
	 * @param xo array
	 * @return 1 computer play as x or 2 play O or 0 player first move
	 */
	public static int readyStart(String[] xo) {
		System.out.println("Flipping a coin !!!\n");
		int coinFlip = (int)(Math.random()*2)+1;
		if(coinFlip == 2) {
			System.out.println("The computer gets the first move !!");
			System.out.println("Thinking...");
			int compChoice = (int)(Math.random()*2)+1;
			compMove(xo, compChoice);
			System.out.println("What will your move be?");
			return compChoice;
		}
		return 0;
	}
	
	
	
	
	/**
	 * Gets random value between 1 and max
	 * @param Max
	 * @return random int
	 */
	public static int randMove(int Max) {
		return (int)(Math.random()*Max)+1;
	}
	
	
	
	
	/**
	 * Makes move for player or computer
	 * @param xo array
	 * @param xoPick X or O to put on board
	 * @param pos Where to put X or O
	 * @return
	 */
	public static int makeMove(String[] xo, int xoPick, int pos) {
		if(xo[pos-1].equalsIgnoreCase("X")||xo[pos-1].equalsIgnoreCase("O")) {
			return -1;
		}
		xo[pos-1] = xoPick == 1 ? "X" : "O";
		printBoard(xo);
		return 0;
	}
	
	
	
	
	/**
	 * Checks if someone won the game
	 * @param xo array
	 */
	public static boolean checkIfWon(String[] xo) {
		return ((xo[0].equals(xo[1]) && xo[1].equals(xo[2]) && !xo[0].equals(" ")) || // Top Horizontal
				   (xo[3].equals(xo[4]) && xo[4].equals(xo[5]) && !xo[3].equals(" ")) || // Middle Horizontal
				   (xo[6].equals(xo[7]) && xo[7].equals(xo[8]) && !xo[6].equals(" ")) || // Bottom Horizontal
				   (xo[0].equals(xo[3]) && xo[3].equals(xo[6]) && !xo[0].equals(" ")) || // Left Vertical
				   (xo[1].equals(xo[4]) && xo[4].equals(xo[7]) && !xo[1].equals(" ")) || // Middle Vertical
				   (xo[2].equals(xo[5]) && xo[5].equals(xo[8]) && !xo[2].equals(" ")) || // Right Vertical
				   (xo[0].equals(xo[4]) && xo[4].equals(xo[8]) && !xo[0].equals(" ")) || // TopLeft Diagonal
				   (xo[2].equals(xo[4]) && xo[4].equals(xo[6]) && !xo[2].equals(" ")))
				? true : false;
	}
	
	
	
	/**
	 * Checks if the game resulted in a tie
	 * @param xo array
	 */
	public static boolean checkIfTie(String[] xo) {
		for(String i : xo) {
			if(i.equals(" "))
				return false;
		}
		return true;
	}
	
	
	
	
	/**
	 * Gets players choice of X or O
	 * @param stdIn
	 * @return 1 for X or 2 for O
	 */
	public static int playerXOChoice(Scanner stdIn) {
		String i;
		do {
			System.out.print("What is your choice? (X or O) :");
			i = stdIn.next();
		}while(!(i.equalsIgnoreCase("x") || i.equalsIgnoreCase("o")));
		
		return i.equalsIgnoreCase("x") ? 1 : 2;
	}
	
	
	
	
	/**
	 * Where does the player want to make their move
	 * @param stdIn
	 * @return valid board move choice 1-9
	 */
	public static int playerMoveChoice(Scanner stdIn) {
		int i;
		do {
			System.out.print("What is your move choice? (1-9) :");
			i = stdIn.nextInt();
		}while(!(i >= 1 || i <= 9));
		return i;
	}
	
	
	/**
	 * Makes move for player but checks if its possible
	 * @param xo array
	 * @param stdIn
	 * @param choiceXorO player as X or O
	 * @param boardMove Where does the player want to make move
	 */
	public static void playerMove(String[] xo, Scanner stdIn, int choiceXorO, int boardMove) {
		int move = -1; 
		do {
		move = makeMove(xo, choiceXorO, boardMove);
		if(move == -1) {
			boardMove = playerMoveChoice(stdIn);
			move = makeMove(xo, choiceXorO, boardMove);
		}
		}while(move==-1);
	}
	
	
	/**
	 * Makes move for computer and makes sure its possible
	 * @param xo array
	 * @param choiceXorO
	 */
	public static void compMove(String[] xo, int choiceXorO) {
		int move = -1;
		do {
			int firstMove = randMove(9);
			move = makeMove(xo, choiceXorO, firstMove);
		}while(move==-1);
	}
	
	
	
	
	/**
	 * Plays the game making moves with player and for computer
	 * until there is a winner or tie
	 * @param stdIn
	 * @param xo array
	 * @param playerChoice
	 * @param compChoice
	 * @param won
	 */
	public static int play(Scanner stdIn, String[] xo, int playerChoice, int compChoice) {
		while(true) {
			int playerMove = playerMoveChoice(stdIn); // Player Move
			playerMove(xo, stdIn, playerChoice, playerMove);
			if(checkIfWon(xo)) 
				return 1;
			if(checkIfTie(xo)) 
				return 3;
			System.out.println("Thinking...");
			compMove(xo, compChoice); // Computer Move
			if(checkIfWon(xo)) 
				return 2;
			if(checkIfTie(xo)) 
				return 3;
		}
	}
}