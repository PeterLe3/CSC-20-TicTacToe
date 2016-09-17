import java.util.Scanner;

public class Main {
    private static int aiWin;
    private static int userWin;
    private static int catGames;
    public static void main(String[] args) {
    	Scanner in = new Scanner(System.in);
    	boolean replay = false;
    	String again = "";
    	prompt();
    	while(!replay) { 
    		game();
			System.out.println("Would you like to play again? (y = yes, n = no)"); 
			if(in.hasNextLine()) { 
				again = in.nextLine();
				if(again.equalsIgnoreCase("y") || again.equalsIgnoreCase("n") && again.length() == 1) { 
					if(again.equalsIgnoreCase("n"))
						replay = true;
				}
				else  { 
					System.out.println("Please input y or n");
					in.nextLine();
				}	
			}
			else { 
				System.out.println("Please input y or n"); 
				in.nextLine();
			}
		}
    	stats(aiWin, userWin, catGames);
    }

    	
    public static void prompt() {
    	System.out.println("Welcome to Tic-Tac-Toe!");
    	System.out.println();
    	System.out.println("In this game, you will be playing against an AI.");
    	System.out.println("You are allowed to create any board size that is 3 or greater.");
    	System.out.println("Below is a sample TTT Board of size 3. Treat the board ");
    	System.out.println("like an x-y coordinate system where the top left corner is");
    	System.out.println("(0,0) and the bottom right corner is (2,2) for this board.");
    	System.out.println("Enjoy!");
    	TTTBoard b = new TTTBoard();
    	System.out.println(b);
    	
    	
    }
    public static void game() { 
    	Scanner in = new Scanner(System.in);
    	boolean play = true;
    	String s = "";
    	int x = 0;
    	int y = 0;
    	char user;
    	String w = "";
    	char who;
    	int turn = 0;
    	boolean validInput = false;
    	int size = 0;
   	
    	do {
    		System.out.println("What character would you like to use to play?");
    		s = in.nextLine();
    	}
    	while(s.length() != 1 || s.charAt(0) == ' ');
    	do {
    		System.out.println("What character would you like the AI to play?");
    		w = in.nextLine();
    	}
    	while(w.length() != 1 || w.charAt(0) == ' ' || w.charAt(0) == s.charAt(0));
    	
    	
		System.out.println("What board size would you like to play on?");
		System.out.println("Example: For a 3x3 board, enter 3.");
    	while(!validInput) { 
    		if(in.hasNextInt()){ 
    			size = in.nextInt();
    			if(size >=3){
    				validInput = true;	
    			}
    			else { 
    				System.out.println("You can't play Tic-Tac-Toe on board size less than 3!");
    				System.out.println("Please input an integer value greater or equal to 3.");
    			}
    		}
    		else {
    			System.out.println("Please input an actual integer value for board size.");
    		}
    		in.nextLine();
    	}
    		
    	TTTBoard board = new TTTBoard(size);
    	System.out.print(board);	
    	System.out.println("Would you like to play first or second? (1 = first, 2 = second)");
        validInput = false;
    	while(!validInput) {
    		if(in.hasNextInt()){
    			turn = in.nextInt();
    			if(turn == 1 || turn == 2){
    				validInput = true;
    			} else {
            		System.out.println("Please enter 1 or 2.");
    			}
    		}else{
        		System.out.println("Please enter 1 or 2.");
    		}
    		in.nextLine();
    	}
    	user = s.charAt(0);
      	who = w.charAt(0);
    	if(turn == 2) {
    		TTTAI.move(board, who);
        	System.out.println(board);
    	}
    	while(play) {
        	user = s.charAt(0);
        	who = w.charAt(0);
        	System.out.println("What x-coordinate would you like to play on?");
        	validInput = false;
        	while(!validInput) {   
        		if(in.hasNextInt())  { 
        			x = in.nextInt();
        			if(x >= 0 && x <=  size - 1) {
        				validInput = true;
        			}
        			else { 
        				System.out.println("Please pick an x-coordinate between 0 and " + (size - 1));
        				
        			}
        		}
        		else {
        		System.out.println("Please pick an x-coordinate between 0 and " + (size - 1));
        		}
        		in.nextLine();	
    		}
	    	System.out.println("What y-coordinate would you like to play on?");
			validInput = false;
	       	while(!validInput) {   
	        	if(in.hasNextInt())  { 
	        		y = in.nextInt();
	        		if(y >= 0 && y <=  size - 1) {
	        			validInput = true;
	        		}
	        		else { 
	        			System.out.println("Please pick an y-coordinate between 0 and " + (size - 1));
	        				
	        		}
	        	}
	        		else {
	        		System.out.println("Please pick an y-coordinate between 0 and " + (size - 1));
	        		}
	        		in.nextLine();
	        		
	    		}
	    		
	       		board.set(x, y, user);
				System.out.println(board);
				if(TTTAI.getSpacesLeft(board) != 0 && board.winner() != who && board.winner() != user ) {
		    		TTTAI.move(board, who);
		    	//	validInput = true;
		    		
				}
				// fix play = false, ask them if they want to play again
				if(board.winner() == user) { 
					System.out.println("Congradulations! You Win.");
					userWin++;
					play = false;
				}
				if(board.winner()== who) { 
					System.out.println("You lose.");
					aiWin++;
					play = false;
				}
				if(TTTAI.getSpacesLeft(board) == 0) { 
					System.out.println("Cat game");
					catGames++;
					play = false;
				}
				System.out.println(board);
    	}
    	
    }
    
    public static void stats(int aiWin, int userWin, int catGame) {
    	System.out.println("              RESULTS");
    	System.out.println("__________________________________");
    	System.out.println();
    	System.out.println("   Wins     Loss     Cat Games");
    	System.out.println("    " + userWin + "        " + aiWin +"           " + catGame);
    	System.out.println();
    }
}











