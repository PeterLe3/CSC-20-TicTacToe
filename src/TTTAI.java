import java.util.Random;

/**
 * This TTTAI class is the artificial intelligence that interacts with the user while playing Tic-Tac-Toe.
 * The AI makes a move that will either help it win the game, block the opponent, or make a random move to
 * progress the game.
 * 
 * @author Stanley Lee | 4-Digit Code: 2734 | email: stanleylee@csus.edu
 * @author Peter Le	   | 4-Digit Code: 2104 | email: peterle3@csus.edu
 * @version 13.3.8 4 April 2016
 */
public class TTTAI {
	
	/**
	 * Random r generates a random number that the AI will use to make random moves on the board.
	 * boolean aiTurn checks if the AI can move or not.
	 * char user is the user's preferred character of choice when playing Tic-Tac-Toe.
	 */
	private static Random r = new Random();
	private static boolean aiTurn = true;
	private static char user;
	
	/**
	 * This move method determines the move that the AI will make. The AI prioritizes 
	 * making a winning move for AI, block the oponent's winning move, then making a random move.
	 * if AiTurn is false, the AI will not make another move. 
	 * 
	 * @param board gets the board that the AI will play on
	 * @param who is the AI's preferred character of choice to play Tic-Tac-Toe.
	 * @returns void to break out of the move method once a move is made to prevent
	 * 			multiple AI moves in one turn.
	 */
	public static void move(TTTBoard board, char who) {
		getSpacesLeft(board);
		if(board.winner() != getEmpty() || getSpacesLeft(board) == 0) {
			throw new IllegalArgumentException();
		}
		aiTurn = true;
		aiWin(board, who);
		if(!aiTurn) {
			return;
		}
		aiBlock(board, who);
		if(!aiTurn) { 
			return;
		}
		aiRandom(board, who);
	}
	
	/**
	 * The aiWin method checks to see if there is a possible AI winning move. 
	 * If so, it will make that move.
	 * 
	 * @param board gets the board that the AI will play on
	 * @param who is the AI's preferred character of choice to play Tic-Tac-Toe.
	 * @returns void to break out of the for-loop and end the method once an AI winning move is made.
	 */
	public static void aiWin(TTTBoard board, char who) { 
		char[][] letter = new char[board.size()][board.size()];
		for(int row = 0; row < letter.length; row++) { 
			for(int col = 0; col < letter[0].length; col++) { 
				if(board.get(row, col)!= who && board.get(row,col) != getEmpty()) {
					user = board.get(row, col);
				}
				if(board.get(row, col) == getEmpty()) {
					board.set(row, col, who);
					if(board.winner() == who) {
						aiTurn = false;
						return;
					} else {
						board.set(row, col, getEmpty());
					}
				} 
			}
		}
	}
	
	/**
	 * If there is no possible AI winning move, the AI will attempt to find a possible
	 * User winning move. If there is one, the AI will block it, preventing a possible user win.
	 * 
	 * @param board gets the board that the AI will play on
	 * @param who is the AI's preferred character of choice to play Tic-Tac-Toe.
	 * @returns void to break out of the for-loop and end the method once the AI blocks the user's
	 *  		possible winning move.
	 */
	public static void aiBlock(TTTBoard board, char who) { 
		char[][] letter = new char[board.size()][board.size()];
		for(int row = 0; row < letter.length; row++) { 
			for(int col = 0; col < letter[0].length; col++) { 
				if(board.get(row, col)!= who && board.get(row,col) != getEmpty()) {
					user = board.get(row, col);
				}
				if(board.get(row, col) == getEmpty()) {
					board.set(row, col, user);
					if(board.winner() == user) {
						board.set(row, col, who);
						aiTurn = false;
						return;
					} else {
						board.set(row, col, getEmpty());
					}
				} 
			}
		}
	}
	
	/**
	 * If there is no possible user winning move, the AI will simply make a random
	 * move on the board.
	 * 
	 * @param board gets the board that the AI will play on
	 * @param who is the AI's preferred character of choice to play Tic-Tac-Toe.
	 * @returns void to break out of the for-loop and end the method once an AI winning move is made.
	 * @boolean unChanged initializes an AI random move until it makes a valid move.
	 */
	public static void aiRandom(TTTBoard board, char who) {
		boolean unChanged = true;
		while(unChanged) {
			int row = r.nextInt(board.size());
			int col = r.nextInt(board.size());
			if(board.get(row, col) == getEmpty()) {
				board.set(row, col, who);
				unChanged = false;
				aiTurn = false;
			}
		}
	}

	/**
	 * The getSpacesLeft method gets the amount of spaces left on the board
	 * before it is completely filled.
	 * 
	 * @param board gets the board that the AI will play on.
	 * @returns spacesLeft to be made on the board before it is filled.
	 */
	public static int getSpacesLeft(TTTBoard board) { 
		int spacesLeft = 0;
		char[][] letter = new char[board.size()][board.size()];
		for(int row = 0; row < letter.length; row++) { 
			for(int col = 0; col < letter[0].length; col++) {
				if(board.get(row, col) == getEmpty()) {
					spacesLeft++;
				}
			}
		}
		return spacesLeft;
	}

	/**
	 * This method returns an empty character.
	 * 
	 * @returns ' ' 
	 */
	public static char getEmpty() {
		return ' ';
	}
}













