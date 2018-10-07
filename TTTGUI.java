package tictactoe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TTTGUI {
	protected JFrame frame;
	protected JPanel panel;
	protected JLabel marker;
	protected JLabel turnLabel;
	
	// Constructor
	protected TTTGUI() {
		frame = new JFrame("Tic Tac Toe");
		
		panel = new JPanel(null) { // null passed to constructor; same as: panel.setLayout(null)
			// Anonymous inner class to draw the TTT grid
			private static final long serialVersionUID = 1L;
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				// Draw vertical lines
				g.drawLine(250, 150, 250, 450);
				g.drawLine(350, 150, 350, 450);
				// Draw horizontal lines
				g.drawLine(150, 250, 450, 250);
				g.drawLine(150, 350, 450, 350);
			}
		};
		
		panel.setBounds(0, 0, 600, 600);
		panel.setBackground(Color.lightGray);
		
		panel.addMouseListener(new MouseListener() {
			// Anonymous inner class
			@Override
			public void mouseClicked(MouseEvent me) {
				if(TTTDriver.gameOver)
					return;
				
				int x = me.getX();
				int y = me.getY();
				
				marker = new JLabel(TTTDriver.turn);
				
				boolean flag = false, win = false;	// indicates validity of click / status of game
				
				if(x < 250 && x > 150) {	// marker to be placed in first column
					if(y < 250 && y > 150) {
						if(TTTDriver.grid[0][0].equals("blank")) {
							marker.setBounds(200, 200, 25, 25);
							TTTDriver.grid[0][0] = TTTDriver.turn; // record the move
							win = TTTDriver.checkForWin(0, 0);	// check for a win
							flag = true;
						}
					}
					else if(y < 350 && y > 250) {
						if(TTTDriver.grid[1][0].equals("blank")) {
							marker.setBounds(200, 300, 25, 25);
							TTTDriver.grid[1][0] = TTTDriver.turn;
							win = TTTDriver.checkForWin(1, 0);
							flag = true;
						}
					}
					else if(y < 450 && y > 350) {
						if(TTTDriver.grid[2][0].equals("blank")) {
							marker.setBounds(200, 400, 25, 25);
							TTTDriver.grid[2][0] = TTTDriver.turn;
							win = TTTDriver.checkForWin(2, 0);
							flag = true;
						}
					}
				}
				else if(x < 350 && x > 250) {	// marker to be placed in second column
					if(y < 250 && y > 150) {
						if(TTTDriver.grid[0][1].equals("blank")) {
							marker.setBounds(300, 200, 25, 25);
							TTTDriver.grid[0][1] = TTTDriver.turn;
							win = TTTDriver.checkForWin(0, 1);
							flag = true;
						}
					}
					else if(y < 350 && y > 250) {
						if(TTTDriver.grid[1][1].equals("blank")) {
							marker.setBounds(300, 300, 25, 25);
							TTTDriver.grid[1][1] = TTTDriver.turn;
							win = TTTDriver.checkForWin(1, 1);
							flag = true;
						}
					}
					else if(y < 450 && y > 350) {
						if(TTTDriver.grid[2][1].equals("blank")) {
							marker.setBounds(300, 400, 25, 25);
							TTTDriver.grid[2][1] = TTTDriver.turn;
							win = TTTDriver.checkForWin(2, 1);
							flag = true;
						}
					}
				}
				else if(x < 450 && x > 350) {	// marker to be placed in third column
					if(y < 250 && y > 150) {
						if(TTTDriver.grid[0][2].equals("blank")) {
							marker.setBounds(400, 200, 25, 25);
							TTTDriver.grid[0][2] = TTTDriver.turn;
							win = TTTDriver.checkForWin(0, 2);
							flag = true;
						}
					}
					else if(y < 350 && y > 250) {
						if(TTTDriver.grid[1][2].equals("blank")) {
							marker.setBounds(400, 300, 25, 25);
							TTTDriver.grid[1][2] = TTTDriver.turn;
							win = TTTDriver.checkForWin(1, 2);
							flag = true;
						}
					}
					else if(y < 450 && y > 350) {
						if(TTTDriver.grid[2][2].equals("blank")) {
							marker.setBounds(400, 400, 25, 25);
							TTTDriver.grid[2][2] = TTTDriver.turn;
							win = TTTDriver.checkForWin(2, 2);
							flag = true;
						}
					}
				}
				
				if(flag == true) {
					panel.add(marker);	// add marker
					--TTTDriver.emptyFiles; // decrement no. of vacant cells
					
					if(win || TTTDriver.emptyFiles == 0) { // we have a result
						JLabel result;
						if(win)
							result = new JLabel(TTTDriver.turn + " WINS!");
						else
							result = new JLabel("GAME DRAWN!");
						result.setBounds(265, 50, 150, 50);
						panel.add(result);
						turnLabel.setText("");
						TTTDriver.gameOver = true;
						
						JButton restart = new JButton("Restart");
						restart.setBounds(250, 500, 95, 30);
						
						restart.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent ae) {
								frame.dispose();
								TTTDriver.initGUI();
							}
						});
						
						panel.add(restart);
					}
					else {
						// Change turn
						if(TTTDriver.turn.equals("X"))
							TTTDriver.turn = "O";
						else
							TTTDriver.turn = "X";
						turnLabel.setText(TTTDriver.turn + "'s turn");
					}
				}
							
				// Refresh panel (this is needed to add components to already visible panel)
				panel.revalidate();
				panel.repaint();
			}
			
			@Override
			public void mousePressed(MouseEvent me) {
			}
			
			@Override
			public void mouseReleased(MouseEvent me) {
			}
			
			@Override
			public void mouseEntered(MouseEvent me) {
			}
			
			@Override
			public void mouseExited(MouseEvent me) {
			}
		});
		
		turnLabel = new JLabel("X's turn");
		turnLabel.setBounds(270, 500, 150, 50);
		panel.add(turnLabel);
		
		frame.add(panel);
		frame.setSize(600, 600);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JOptionPane.showMessageDialog(frame, "This is a 2-person game. Mark the cells by clicking on them!");
	}
}
