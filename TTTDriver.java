package tictactoe;

public class TTTDriver {
	protected static String turn;
	protected static String[][] grid = new String[3][3];
	protected static boolean gameOver;
	protected static int emptyFiles;
	
	public static void main(String[] args) {
		initGUI();
	}
	
	// Method to initialize the GUI
	protected static void initGUI() {
		turn = "X";
		gameOver = false;
		emptyFiles = 9;
		
		for(int i = 0; i < 3; ++i)
			for(int j = 0; j < 3; ++j)
				grid[i][j] = "blank";
		
		new TTTGUI();
	}
	
	// Method to check for a win
	protected static boolean checkForWin(int x, int y)
	{
		boolean flag = true;
		
		if(x == y) { // current cell lies on principal diagonal
			if(x == 0) {
				if(grid[0][1].equals(turn) && grid[0][2].equals(turn))
					return true;
				else if(grid[1][0].equals(turn) && grid[2][0].equals(turn))
					return true;
				else if(grid[1][1].equals(turn) && grid[2][2].equals(turn))
					return true;
			}
			else if(x == 1) {
				if(grid[0][0].equals(turn) && grid[2][2].equals(turn))
					return true;
				else if(grid[0][2].equals(turn) && grid[2][0].equals(turn))
					return true;
				else if(grid[0][1].equals(turn) && grid[2][1].equals(turn))
					return true;
				else if(grid[1][0].equals(turn) && grid[1][2].equals(turn))
					return true;
			}
			else {
				if(grid[0][0].equals(turn) && grid[1][1].equals(turn))
					return true;
				else if(grid[0][2].equals(turn) && grid[1][2].equals(turn))
					return true;
				else if(grid[2][0].equals(turn) && grid[2][1].equals(turn))
					return true;
			}
		}
		else if(Math.abs(x - y) == 2) { // current cell is either the bottom-left corner or the top-right corner
			if(x == 2) {
				if(grid[0][0].equals(turn) && grid[1][0].equals(turn))
					return true;
				else if(grid[2][1].equals(turn) && grid[2][2].equals(turn))
					return true;
				else if(grid[1][1].equals(turn) && grid[0][2].equals(turn))
					return true;
			}
			else {
				if(grid[0][0].equals(turn) && grid[0][1].equals(turn))
					return true;
				else if(grid[1][2].equals(turn) && grid[2][2].equals(turn))
					return true;
				else if(grid[2][0].equals(turn) && grid[1][1].equals(turn))
					return true;
			}
		}
		else { // non-corner, non-central cell
			for(int i = x, j = 0; j < 3; ++j) {
				if(grid[i][j].equals(turn))
					continue;
				else {
					flag = false;
					break;
				}
			}
			if(flag == true)
				return true;
			
			flag = true;
			
			for(int i = 0, j = y; i < 3; ++i) {
				if(grid[i][j].equals(turn))
					continue;
				else {
					flag = false;
					break;
				}
			}
			if(flag == true)
				return true;
		}
				
		return false;
	}
}
