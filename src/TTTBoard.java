/**
 * This TTTBoard class models a Tic-Tac-Toe Board. 
 * The client can generate any board size greater than 0.
 * The board behaves like a traditional (x,y) coordinate system.
 * 
 * @author Stanley Lee | 4-Digit Code: 2734 | email: stanleylee@csus.edu
 * @author Peter Le	   | 4-Digit Code: 2104 | email: peterle3@csus.edu
 * @version 13.3.8 4 March 2016
 */
public class TTTBoard {
	
	/** 
	 * If client does not specify a parameter in the constructor, 
	 * then the default size will be 3.
	 */
	public static final int DEFAULT_SIZE = 3;
	
	/**
	 * char[][] letter is the array of characters on the board.
	 * int size is the size of the board, e.g. if size = 3, 
	 * a 3x3 board will be generated.
	 * int x is the x coordinate on the board.
	 * int y is the y coordinate on the board.
	 * char ch is the character that will be displayed on the board.
	 * char empty is a space holder for each memory location in the array.
	 * int count is used to keep track of matching pairs of characters in a 
	 * straight line.
	 * int row is an integer value passed as an index for the 2D array.
	 * int col is an integer value passed as an index for the 2D array.
	 */
	private char[][] letter;
	private int size;
	private int x;
	private int y;
	private char ch;
	private char empty;
	private int count;
	private int row;
	private int col;
	
	/**
	 * If the client constructs a new board without specifying a
	 * parameter, the board size will be automatically set to 3.
	 * The constant, DEFAULT_SIZE, is used to create the 2D
	 * array for the board. 
	 * The for-loop initializes every memory space in the array
	 * to the character variable empty, a blank space.
	 */
	public TTTBoard() {
		letter = new char[DEFAULT_SIZE][DEFAULT_SIZE];
		size = DEFAULT_SIZE;
		row = 0;
		col = 0;
		x = 0;
		y = 0;
		empty = ' ';
		count = 0;
		for(int row = 0; row < letter.length; row++) { 
			for(int col = 0; col < letter[0].length; col++) { 
				letter[row][col] = empty;
			}
		}
	}
	
	/**
	 * This constructor create a board according to the client's 
	 * desired size. 
	 * 
	 * @param size Client-specified board size.
	 * @throws IllegalArgumentException If size is less than 1. 
	 */
	public TTTBoard(int size) {
		if(size < 1) {
			throw new IllegalArgumentException("The board size must not be less than 1");
		}
		letter = new char[size][size];
		row = 0;
		col = 0;
		x = 0;
		y = 0;
		empty = ' ';	
		count = 0;
		for(int row = 0; row < letter.length; row++) { 
			for(int col = 0; col < letter[0].length; col++) { 
				letter[row][col] = empty;
			}
		}
	}
	
	/**
	 * This method gets the character at the given X and Y coordinate
	 * and returns it.
	 * 
	 * @param x coordinate on the board
	 * @param y coordinate on the board
	 * @return the char at this coordinate
	 * @throws IndexOutOfBoundsException if client chooses a negative
	 * X or Y coordinate or a coordinate that is greater than size.
	 */
	public char get(int x, int y) {
		size();
		if(x < 0 || y < 0) 
			throw new IndexOutOfBoundsException("Pick an X or Y coordinate between 0 and " + (size - 1) + "!");
		else if(x >= size || y >= size)
			throw new IndexOutOfBoundsException("Pick an X or Y coordinate between 0 and " + (size - 1) + "!");
		return letter[x][y];
	}
	
	/**
	 * This method sets a character at the specified X and Y coordinate.
	 * 
	 * @param x coordinate on the board
	 * @param y coordinate on the board
	 * @param ch character that will be placed at the specified X and Y coordinate.
	 */
	public void set(int x, int y, char ch) { 
		size();
		if(x < 0 || y < 0) 
			throw new IndexOutOfBoundsException("Pick an X or Y coordinate between 0 and " + (size - 1) + "!");
		else if(x >= size || y >= size)
			throw new IndexOutOfBoundsException(" Pick an X or Y coordinate between 0 and " + (size - 1) + "!");
		this.x = x;
		this.y = y;
		this.ch = ch;
		letter[this.x][this.y] = this.ch;
	}
	
	/**
	 * This size method gets the length of the array.
	 * 
	 * @return length of the array as int size.
	 */
	public int size() {
		size = letter.length;
		return size;
	}

	/**
	 * This method checks if there is a horizontal, vertical, or diagonal winner. 
	 * 
	 * @return the winning character.
	 */
	public char winner() {
		size();
		if(horizontal() != empty) {
			return horizontal();
		} else if(vertical() != empty) {
			return vertical();
		} else if(diagonal1() != empty) {
			return diagonal1();
		} else {
			return diagonal2();
		}
	}
	
	/**
	 * This method utilizes for-loops to check for matching character pairs 
	 * other than char empty in the array.
	 * If the same character in a vertical line match,
	 * 
	 * @return character in that vertical line.
	 */
	public char vertical() {
		size();
		count = 0;
		for(int row = 0; row < size; row++) {
			for(int col = 0; col < size - 1; col++) {
				if(letter[row][col] != empty) {    
					if(letter[row][col] == letter[row][col+1]) {
						count++;
					}
				}
			}
			if(count == size - 1) {
				return letter[row][col];
			} else {
				count = 0;
			}
		}
		return empty;
	}
	
	/**
	 * This method utilizes for-loops to check for matching character pairs 
	 * other than char empty in the array.
	 * If the same character in a horizontal line match,
	 * 
	 * @return character in that horizontal line.
	 */
	public char horizontal() {
		size();
		count = 0;
		for(int row = 0; row < size; row++) {
			for(int col = 0; col < size - 1; col++) {
				if(letter[col][row] != empty) {    
					if(letter[col][row] == letter[col + 1][row]) {
						count++;
					}
				}
			}
			if(count == size - 1) {
				return letter[col][row];
			} else {
				count = 0;
			}
		}
		return empty;
	}
	
	/**
	 * This method utilizes for-loops to check for matching character pairs 
	 * other than char empty in the array.
	 * If the same character in a diagonal line match,
	 * 
	 * @return character in that diagonal line.
	 */
	public char diagonal1() {
		size();
		x = 0;
		y= 0;
		for(int diag = 0; diag < size - 1; diag++) {
			if(letter[x][y] != empty) {
				if(letter[x][y] == letter[x + 1][y + 1]) {
					x++;
					y++;
				} else {
					x = 0;
					y = 0;
					return empty;
				}
			} else {
				x = 0;
				y = 0;
				return empty;
			}
		}
		return letter[x][y];
	}
	
	/**
	 * This method utilizes for-loops to check for matching character pairs 
	 * other than char empty in the array.
	 * If the same character in a diagonal line match,
	 * 
	 * @return character in that diagonal line.
	 */
	public char diagonal2() {
		size();
		x = 0;
		y = size - 1;
		for(int diag = size - 1; diag > 0; diag--) {
			if(letter[x][y] != empty) {
				if(letter[x][y] == letter[x + 1][y - 1]) {
					x++;
					y--;
				} else {
					x = 0;
					y = 0;
					return empty;
				}
			} else {
				x = 0;
				y = 0;
				return empty;
			}
		}
		return letter[x][y];
	}

	/**
	 * Presents a text-graphic of the board.
	 * 
	 * @return the board as a String.
	 */
	public String toString() {
		size();
		String s = "";
		int divider = 0;
		for(int i = 0; i < size; i++) {
			s += letter[0][i];
			for(int j = 1; j < letter[i].length; j++) {
				s = s + "|" + letter[j][i]; 
			}
			if(divider < size - 1) {
				s = s + "\n-";
				for(int k = 1; k < letter.length; k++) {
					s += "+-";
				}
			}
			divider++;
			s += "\n";
		} 
		return s;
	}
}












