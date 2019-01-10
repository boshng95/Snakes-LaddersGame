import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
/**
 * 
 *
 * @author Bosh (Hou Jun Ng)
 * @java 10.0.2
 */
public class SnakeAndLadders {
	
	private JFrame frame;
	private JLabel map = new JLabel(new ImageIcon(new ImageIcon("src\\testing.jpg").getImage().getScaledInstance(600, 325, Image.SCALE_DEFAULT)));
	private JButton quit = new JButton("Quit Game");
	private JButton start = new JButton("Start Game");
	private JButton player2button = new JButton(new ImageIcon(new ImageIcon("src\\player2.jpg").getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT)));
	private JButton player1button = new JButton(new ImageIcon(new ImageIcon("src\\player1.jpg").getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT)));
	private JLabel player1map = new JLabel(new ImageIcon(new ImageIcon("src\\player1.jpg").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
	private JLabel player2map = new JLabel(new ImageIcon(new ImageIcon("src\\player2.jpg").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
	private JButton diceOne;
	private JButton diceTwo;
	private JLabel playeraction = new JLabel();
	private JLabel scoreboard1 = new JLabel();
	private JLabel scoreboard2 = new JLabel();
	private Players firstPlayer = new Players(1,true,20,240);
	private Players secondPlayer = new Players(1,false,20,270);
	private int playeronedice;
	private int initialdice1 = 0;
	private int initialdice2 = 0;
	DragAction da = new DragAction();
	Listener ls = new Listener();
	
	/**
	 * Java Frame & Main Menu Initialize
	 */
	public SnakeAndLadders() {
		makeFrame();
		mainMenu();
	}
	
	/**
	 * MouseMotionListener & MouseListener for the whole Snake & Ladders Programme
	 * Mainly for user's drag & click action in this program.
	 * @author Ng
	 *
	 */
	public class DragAction implements MouseMotionListener,MouseListener{
		Dimension PCscreenSize = Toolkit.getDefaultToolkit().getScreenSize();
		private int x = player1map.getX();
		private int y = player1map.getY();	
		
		public void setXY(int x, int y) {
			this.x=x;
			this.y=y;
		}
		
		@Override
		public void mouseClicked(MouseEvent me) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseEntered(MouseEvent me) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseExited(MouseEvent me) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseReleased(MouseEvent me) {
			// TODO Auto-generated method stub
			Point player1loc = frame.getMousePosition();
			int playerx = player1loc.x-20; 
			int playery = player1loc.y-30;
			player1map.setBounds(playerx,playery, 30,30);
			
			if(!(playerx>=firstPlayer.getPXposition() && playerx<=firstPlayer.getPXposition()+50 
					&& playery>=firstPlayer.getPYposition()-40 && playery<=firstPlayer.getPYposition()+40)) {
				player1map.setBounds(x,y, 30,30);
				player1map.addMouseListener(da);
				player1map.addMouseMotionListener(da);
			}
			
			else {
				player1map.setBounds(playerx,playery, 30,30);
				x = playerx;
				y = playery;
				if(playeronedice == 6) {
					player1button.setEnabled(true);
					player2button.setEnabled(false);
					diceOne.setEnabled(true);
					diceTwo.setEnabled(false);
					firstPlayer.setPlayerAction(true);
					secondPlayer.setPlayerAction(false);
					firstPlayer.setNextMove(true);
					secondPlayer.setNextMove(false);
					if(firstPlayer.getPlayerScore()==50){
						player1button.setEnabled(false);
						player2button.setEnabled(false);
						firstPlayer.setPlayerStatus("Player One Win The Game");
					}
				}
				else if(playeronedice>0) {
					player1button.setEnabled(false);
					player2button.setEnabled(true);
					diceOne.setEnabled(false);
					diceTwo.setEnabled(true);
					firstPlayer.setPlayerAction(false);
					secondPlayer.setPlayerAction(true);
					firstPlayer.setNextMove(false);
					secondPlayer.setNextMove(true);
					if(firstPlayer.getPlayerScore()==50){
						player1button.setEnabled(false);
						player2button.setEnabled(false);
						diceOne.setEnabled(false);
						diceTwo.setEnabled(false);
						firstPlayer.setPlayerStatus("Player One Win The Game");
					}
				}
				makePlayerAction(playeronedice,0);
				UpdatePlayerOne();
			}
			x = player1map.getX();
			y = player1map.getY();
			player1map.removeMouseListener(da);
			player1map.removeMouseMotionListener(da);
		}

		@Override
		public void mouseDragged(MouseEvent me) {
			// TODO Auto-generated method stub
			Point player1loc = frame.getMousePosition();
			player1map.setBounds(player1loc.x-20,player1loc.y-30, 30,30);
		}

		@Override
		public void mouseMoved(MouseEvent me) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mousePressed(MouseEvent me) {
			// TODO Auto-generated method stub
			Point player1loc = frame.getMousePosition();
			player1map.setBounds(player1loc.x-20,player1loc.y-30, 30,30);
		}
	}
	 /**
	  * This method is to automatically move the 2nd player icon to the right place
	  * if the player meet specific ladders or snakes
	  */
	public void UpdatePlayerTwo() {
		switch(secondPlayer.getPlayerScore()) {
			case 4:LadderSnakeUpdate(secondPlayer, 14);break;
			case 12:LadderSnakeUpdate(secondPlayer, 5);break;
			case 15: LadderSnakeUpdate(secondPlayer, 36);break;
			case 18:LadderSnakeUpdate(secondPlayer, 8);break;
			case 22: LadderSnakeUpdate(secondPlayer, 42);break;
			case 25: LadderSnakeUpdate(secondPlayer, 17);break;
			case 30: LadderSnakeUpdate(secondPlayer, 35);break;
			case 34: LadderSnakeUpdate(secondPlayer, 24);break;
			case 38: LadderSnakeUpdate(secondPlayer, 42);break;
			case 47: LadderSnakeUpdate(secondPlayer, 20);break;
			}
	}
	
	 /**
	  * This method is to get the value of start & end value of snake & ladders/
	  */
	public void UpdatePlayerOne() {
		switch(firstPlayer.getPlayerScore()) {
		case 4:LadderSnakeUpdate(firstPlayer, 14);break;
		case 12:LadderSnakeUpdate(firstPlayer, 5);break;
		case 15: LadderSnakeUpdate(firstPlayer, 36);break;
		case 18:LadderSnakeUpdate(firstPlayer, 8);break;
		case 22: LadderSnakeUpdate(firstPlayer, 42);break;
		case 25: LadderSnakeUpdate(firstPlayer, 17);break;
		case 30: LadderSnakeUpdate(firstPlayer, 35);break;
		case 34: LadderSnakeUpdate(firstPlayer, 24);break;
		case 38: LadderSnakeUpdate(firstPlayer, 42);break;
		case 47: LadderSnakeUpdate(firstPlayer, 20);break;
		}
	}
	
	/**
	 * This method is to update the player's icon in the program when they meet
	 * specific ladders or snakes.
	 * @param player
	 * @param difference
	 */
	public void LadderSnakeUpdate(Players player,int difference) {
		player.setPlayerScore(difference);
		makePlayerOneScoreBoard(player.getPlayerScore());
		player.bufferPlay(0);
		if(player == firstPlayer) {
			player1map.setBounds(player.getPXposition(),player.getPYposition(),30,30);
		}
		else {
			player2map.setBounds(player.getPXposition(),player.getPYposition(),30,30);
		}
	}
	
	/**
	 * This class is to ActionListener function for the whole programme.
	 * User will involve in Button Click on JButton.
	 * Each Button react different message and action for the user.
	 */
	public class Listener implements ActionListener
	{
		Random rn = new Random();
		public void actionPerformed(ActionEvent e)
	    {  
			Random rn = new Random();
			
			switch (((JButton)e.getSource()).getName()){
				case "start":
					start.setVisible(false);
					quit.setVisible(false);
					da.setXY(20,240);
					makePlayerOneMap();
					makePlayerTwoMap();
					makeLadders();
					makeSnakes();
					mapCreated();
					makePlayerOneButton();
					makePlayerTwoButton();
					makePlayerOneDice(0);
					makePlayerTwoDice(0);
					makeRestartButton();
					makePlayerOneScoreBoard(0);
					makePlayerTwoScoreBoard(0);
					initialdice1 = rn.nextInt(6) + 1;
					initialdice2 = rn.nextInt(6) + 1;
					if(initialdice1==initialdice2) {
						while(initialdice1==initialdice2){
							initialdice1 = rn.nextInt(6) + 1;
							initialdice2 = rn.nextInt(6) + 1;
							if(initialdice1>initialdice2) {
								firstPlayer.setPlayerStatus("Player 1 Dice is: "+initialdice1+". Player 1 lead the game");
								secondPlayer.setPlayerStatus("Player 2 Dice is: "+initialdice2);
								player2button.setEnabled(false);
								makePlayerAction(0,0);
								break;
							}
							else if(initialdice2>initialdice1) {
								firstPlayer.setPlayerStatus("Player 1 Dice is: "+initialdice1);
								secondPlayer.setPlayerStatus("Player 2 Dice is: "+initialdice2+". Player 2 lead the game");
								player1button.setEnabled(false);
								player2button.setEnabled(true);
								diceOne.setEnabled(false);
								diceTwo.setEnabled(true);
								firstPlayer.setPlayerAction(false);
								secondPlayer.setPlayerAction(true);
								firstPlayer.setNextMove(false);
								secondPlayer.setNextMove(true);
								makePlayerAction(0,0);
								break;
							}
							else if(initialdice1==initialdice2) {
								continue;
							}
						}
					}
					else if(initialdice1>initialdice2) {
						firstPlayer.setPlayerStatus("Player 1 Dice is: "+initialdice1+". Player 1 lead the game");
						secondPlayer.setPlayerStatus("Player 2 Dice is: "+initialdice2);
						player2button.setEnabled(false);
						makePlayerAction(0,0);
						break;
					}
					else if(initialdice2>initialdice1) {
						firstPlayer.setPlayerStatus("Player 1 Dice is: "+initialdice1);
						secondPlayer.setPlayerStatus("Player 2 Dice is: "+initialdice2+". Player 2 lead the game");
						player1button.setEnabled(false);
						player2button.setEnabled(true);
						diceOne.setEnabled(false);
						diceTwo.setEnabled(true);
						firstPlayer.setPlayerAction(false);
						secondPlayer.setPlayerAction(true);
						firstPlayer.setNextMove(false);
						secondPlayer.setNextMove(true);
						makePlayerAction(0,0);
						break;
					}
					makePlayerAction(0,0);
					break;
				case "quit":
					frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
					break;
				case "player1":
					firstPlayer.setPlayerStatus(null);
					int dice1 = firstPlayer.playerDice(1, secondPlayer.getPlayerAction());
					playeronedice = dice1;
					makePlayerOneDice(playeronedice);
					player1button.setEnabled(false);
					player2button.setEnabled(false);
					diceOne.setEnabled(false);
					diceTwo.setEnabled(false);
					if(dice1 == 6) {
						firstPlayer.bufferPlay(dice1);
						if(firstPlayer.getPlayerScore()<50) {
							player1map.addMouseListener(da);
							player1map.addMouseMotionListener(da);
						}
						else if(firstPlayer.getPlayerScore()==50){
							player1button.setEnabled(false);
							player2button.setEnabled(false);
							diceOne.setEnabled(false);
							diceTwo.setEnabled(false);
							firstPlayer.setPlayerStatus("Player One Win The Game");
						}
						else if(firstPlayer.getPlayerScore()>50) {
							firstPlayer.setPlayerScore(firstPlayer.getPlayerScore()-dice1);
							firstPlayer.setPlayerStatus("Dice value is too high. Player unable to move");
							player1button.setEnabled(true);
							player2button.setEnabled(false);
							diceOne.setEnabled(true);
							diceTwo.setEnabled(false);
							firstPlayer.setPlayerAction(true);
							secondPlayer.setPlayerAction(false);
							firstPlayer.setNextMove(true);
							secondPlayer.setNextMove(false);
							player1map.removeMouseListener(da);
							player1map.removeMouseMotionListener(da);
						}
						makePlayerAction(dice1, 0);
					}
					else if(dice1 != 6){
						player1map.addMouseListener(da);
						player1map.addMouseMotionListener(da);
						firstPlayer.bufferPlay(dice1);
						if(firstPlayer.getPlayerScore()<50) {
							player1map.addMouseListener(da);
							player1map.addMouseMotionListener(da);
						}
						else if(firstPlayer.getPlayerScore()==50){
							player1button.setEnabled(false);
							player2button.setEnabled(false);
							diceOne.setEnabled(false);
							diceTwo.setEnabled(false);
							firstPlayer.setPlayerStatus("Player One Win The Game");
						}
						else if(firstPlayer.getPlayerScore()>50) {
							firstPlayer.setPlayerScore(firstPlayer.getPlayerScore()-dice1);
							firstPlayer.setPlayerStatus("Dice value is too high. Player unable to move");
							player1button.setEnabled(false);
							player2button.setEnabled(true);
							diceOne.setEnabled(false);
							diceTwo.setEnabled(true);
							firstPlayer.setPlayerAction(false);
							secondPlayer.setPlayerAction(true);
							firstPlayer.setNextMove(true);
							secondPlayer.setNextMove(false);
							player1map.removeMouseListener(da);
							player1map.removeMouseMotionListener(da);
						}
						makePlayerAction(dice1, 0);
					}
					makePlayerOneScoreBoard(firstPlayer.getPlayerScore());
					break;
				case "player2":
					secondPlayer.setPlayerStatus(null);
					int dice2 = secondPlayer.playerDice(2, firstPlayer.getPlayerAction());
					makePlayerTwoDice(dice2);
					if(dice2 == 6) {
						secondPlayer.bufferPlay(dice2);
						player2map.setBounds(secondPlayer.getPXposition(), secondPlayer.getPYposition(), 30, 30);
						player1button.setEnabled(false);
						player2button.setEnabled(true);
						diceOne.setEnabled(false);
						diceTwo.setEnabled(true);
						firstPlayer.setPlayerAction(false);
						secondPlayer.setPlayerAction(true);
						firstPlayer.setNextMove(false);
						secondPlayer.setNextMove(true);
						UpdatePlayerTwo();
						if(secondPlayer.getPlayerScore()==50){
							player1button.setEnabled(false);
							player2button.setEnabled(false);
							diceOne.setEnabled(false);
							diceTwo.setEnabled(false);
							secondPlayer.setPlayerStatus("Player Two Win The Game");
						}
						else if(secondPlayer.getPlayerScore()>50){
							secondPlayer.setPlayerScore(secondPlayer.getPlayerScore()-dice2);
							secondPlayer.setPlayerStatus("Dice value is too high. Player unable to move");					
						}
						makePlayerAction(0, dice2);
					}
					else if(dice2 != 6) {
						secondPlayer.bufferPlay(dice2);
						player2map.setBounds(secondPlayer.getPXposition(), secondPlayer.getPYposition(), 30, 30);
						player1button.setEnabled(true);
						player2button.setEnabled(false);
						diceOne.setEnabled(true);
						diceTwo.setEnabled(false);
						firstPlayer.setPlayerAction(true);
						secondPlayer.setPlayerAction(false);
						firstPlayer.setNextMove(true);
						secondPlayer.setNextMove(false);
						UpdatePlayerTwo();
						if(secondPlayer.getPlayerScore()==50){
							player1button.setEnabled(false);
							player2button.setEnabled(false);
							diceOne.setEnabled(false);
							diceTwo.setEnabled(false);
							secondPlayer.setPlayerStatus("Player Two Win The Game");
						}
						else if(secondPlayer.getPlayerScore()>50){
							secondPlayer.setPlayerScore(secondPlayer.getPlayerScore()-dice2);
							secondPlayer.setPlayerStatus("Dice value is too high. Player unable to move");					
						}
						makePlayerAction(0, dice2);
					}
					makePlayerTwoScoreBoard(secondPlayer.getPlayerScore());
					break;
				case "diceOne":
					firstPlayer.setPlayerStatus(null);
					dice1 = firstPlayer.playerDice(1, secondPlayer.getPlayerAction());
					playeronedice = dice1;
					makePlayerOneDice(playeronedice);
					player1button.setEnabled(false);
					player2button.setEnabled(false);
					diceOne.setEnabled(false);
					diceTwo.setEnabled(false);
					if(dice1 == 6) {
						firstPlayer.bufferPlay(dice1);
						if(firstPlayer.getPlayerScore()<50) {
							player1map.addMouseListener(da);
							player1map.addMouseMotionListener(da);
						}
						else if(firstPlayer.getPlayerScore()==50){
							player1button.setEnabled(false);
							player2button.setEnabled(false);
							diceOne.setEnabled(false);
							diceTwo.setEnabled(false);
							firstPlayer.setPlayerStatus("Player One Win The Game");
						}
						else if(firstPlayer.getPlayerScore()>50) {
							firstPlayer.setPlayerScore(firstPlayer.getPlayerScore()-dice1);
							firstPlayer.setPlayerStatus("Dice value is too high. Player unable to move");
							player1button.setEnabled(true);
							player2button.setEnabled(false);
							diceOne.setEnabled(true);
							diceTwo.setEnabled(false);
							firstPlayer.setPlayerAction(true);
							secondPlayer.setPlayerAction(false);
							firstPlayer.setNextMove(true);
							secondPlayer.setNextMove(false);
							player1map.removeMouseListener(da);
							player1map.removeMouseMotionListener(da);
						}
						makePlayerAction(dice1, 0);
					}
					else if(dice1 != 6){
						player1map.addMouseListener(da);
						player1map.addMouseMotionListener(da);
						firstPlayer.bufferPlay(dice1);
						if(firstPlayer.getPlayerScore()<50) {
							player1map.addMouseListener(da);
							player1map.addMouseMotionListener(da);
						}
						else if(firstPlayer.getPlayerScore()==50){
							player1button.setEnabled(false);
							player2button.setEnabled(false);
							diceOne.setEnabled(false);
							diceTwo.setEnabled(false);
							firstPlayer.setPlayerStatus("Player One Win The Game");
						}
						else if(firstPlayer.getPlayerScore()>50) {
							firstPlayer.setPlayerScore(firstPlayer.getPlayerScore()-dice1);
							firstPlayer.setPlayerStatus("Dice value is too high. Player unable to move");
							player1button.setEnabled(false);
							player2button.setEnabled(true);
							diceOne.setEnabled(false);
							diceTwo.setEnabled(true);
							firstPlayer.setPlayerAction(false);
							secondPlayer.setPlayerAction(true);
							firstPlayer.setNextMove(true);
							secondPlayer.setNextMove(false);
							player1map.removeMouseListener(da);
							player1map.removeMouseMotionListener(da);
						}
						makePlayerAction(dice1, 0);
					}
					makePlayerOneScoreBoard(firstPlayer.getPlayerScore());
					break;
				case "diceTwo":
					secondPlayer.setPlayerStatus(null);
					dice2 = secondPlayer.playerDice(2, firstPlayer.getPlayerAction());
					makePlayerTwoDice(dice2);
					if(dice2 == 6) {
						secondPlayer.bufferPlay(dice2);
						player2map.setBounds(secondPlayer.getPXposition(), secondPlayer.getPYposition(), 30, 30);
						player1button.setEnabled(false);
						player2button.setEnabled(true);
						diceOne.setEnabled(false);
						diceTwo.setEnabled(true);
						firstPlayer.setPlayerAction(false);
						secondPlayer.setPlayerAction(true);
						firstPlayer.setNextMove(false);
						secondPlayer.setNextMove(true);
						UpdatePlayerTwo();
						if(secondPlayer.getPlayerScore()==50){
							player1button.setEnabled(false);
							player2button.setEnabled(false);
							diceOne.setEnabled(false);
							diceTwo.setEnabled(false);
							secondPlayer.setPlayerStatus("Player Two Win The Game");
						}
						else if(secondPlayer.getPlayerScore()>50){
							secondPlayer.setPlayerScore(secondPlayer.getPlayerScore()-dice2);
							secondPlayer.setPlayerStatus("Dice value is too high. Player unable to move");					
						}
						makePlayerAction(0, dice2);
					}
					else if(dice2 != 6) {
						secondPlayer.bufferPlay(dice2);
						player2map.setBounds(secondPlayer.getPXposition(), secondPlayer.getPYposition(), 30, 30);
						player1button.setEnabled(true);
						player2button.setEnabled(false);
						diceOne.setEnabled(true);
						diceTwo.setEnabled(false);
						firstPlayer.setPlayerAction(true);
						secondPlayer.setPlayerAction(false);
						firstPlayer.setNextMove(true);
						secondPlayer.setNextMove(false);
						UpdatePlayerTwo();
						if(secondPlayer.getPlayerScore()==50){
							player1button.setEnabled(false);
							player2button.setEnabled(false);
							diceOne.setEnabled(false);
							diceTwo.setEnabled(false);
							secondPlayer.setPlayerStatus("Player Two Win The Game");
						}
						else if(secondPlayer.getPlayerScore()>50){
							secondPlayer.setPlayerScore(secondPlayer.getPlayerScore()-dice2);
							secondPlayer.setPlayerStatus("Dice value is too high. Player unable to move");					
						}
						makePlayerAction(0, dice2);
					}
					makePlayerTwoScoreBoard(secondPlayer.getPlayerScore());
					break;
				case "restart":
					da.setXY(20,240);
					firstPlayer.setPlayerScore(1);
					secondPlayer.setPlayerScore(1);
					player1map.setBounds(20, 240, 30, 30);
					player2map.setBounds(20, 270, 30, 30);
					makePlayerOneScoreBoard(firstPlayer.getPlayerScore());
					makePlayerTwoScoreBoard(secondPlayer.getPlayerScore());
					initialdice1 = rn.nextInt(6) + 1;
					initialdice2 = rn.nextInt(6) + 1;
					if(initialdice1==initialdice2) {
						while(initialdice1==initialdice2){
							initialdice1 = rn.nextInt(6) + 1;
							initialdice2 = rn.nextInt(6) + 1;
							if(initialdice1>initialdice2) {
								firstPlayer.setPlayerStatus("Player 1 Dice is: "+initialdice1+". Player 1 lead the game");
								secondPlayer.setPlayerStatus("Player 2 Dice is: "+initialdice2);
								player1button.setEnabled(true);
								player2button.setEnabled(false);
								diceOne.setEnabled(true);
								diceTwo.setEnabled(false);
								firstPlayer.setPlayerAction(true);
								secondPlayer.setPlayerAction(false);
								firstPlayer.setNextMove(true);
								secondPlayer.setNextMove(false);
								makePlayerAction(0,0);
								break;
							}
							else if(initialdice2>initialdice1) {
								firstPlayer.setPlayerStatus("Player 1 Dice is: "+initialdice1);
								secondPlayer.setPlayerStatus("Player 2 Dice is: "+initialdice2+". Player 2 lead the game");
								player1button.setEnabled(false);
								player2button.setEnabled(true);
								diceOne.setEnabled(false);
								diceTwo.setEnabled(true);
								firstPlayer.setPlayerAction(false);
								secondPlayer.setPlayerAction(true);
								firstPlayer.setNextMove(false);
								secondPlayer.setNextMove(true);
								makePlayerAction(0,0);
								break;
							}
							else if(initialdice1==initialdice2) {
								continue;
							}
						}
					}
					else if(initialdice1>initialdice2) {
						firstPlayer.setPlayerStatus("Player 1 Dice is: "+initialdice1+". Player 1 lead the game");
						secondPlayer.setPlayerStatus("Player 2 Dice is: "+initialdice2);
						player1button.setEnabled(true);
						player2button.setEnabled(false);
						diceOne.setEnabled(true);
						diceTwo.setEnabled(false);
						firstPlayer.setPlayerAction(true);
						secondPlayer.setPlayerAction(false);
						firstPlayer.setNextMove(true);
						secondPlayer.setNextMove(false);
						makePlayerAction(0,0);
						break;
					}
					else if(initialdice2>initialdice1) {
						firstPlayer.setPlayerStatus("Player 1 Dice is: "+initialdice1);
						secondPlayer.setPlayerStatus("Player 2 Dice is: "+initialdice2+". Player 2 lead the game");
						player1button.setEnabled(false);
						player2button.setEnabled(true);
						diceOne.setEnabled(false);
						diceTwo.setEnabled(true);
						firstPlayer.setPlayerAction(false);
						secondPlayer.setPlayerAction(true);
						firstPlayer.setNextMove(false);
						secondPlayer.setNextMove(true);
						makePlayerAction(0,0);
						break;
					}
					makePlayerAction(0,0);
					break;
				default:
					System.out.println(((JButton)e.getSource()).getName());
					break;
			}
		}
	}
	
	/**
	 * Snakes picture created in JFrame using JLabel Snakes Image
	 */
	public void makeSnakes() {
		JLabel snake1 = new JLabel(new ImageIcon(new ImageIcon("src\\snake1.png").getImage().getScaledInstance(350, 450, Image.SCALE_DEFAULT)));
		JLabel snake2 = new JLabel(new ImageIcon(new ImageIcon("src\\snake2.png").getImage().getScaledInstance(150, 200, Image.SCALE_DEFAULT)));
		JLabel snake3 = new JLabel(new ImageIcon(new ImageIcon("src\\snake3.png").getImage().getScaledInstance(120, 60, Image.SCALE_DEFAULT)));
		JLabel snake4 = new JLabel(new ImageIcon(new ImageIcon("src\\snake4.png").getImage().getScaledInstance(300, 200, Image.SCALE_DEFAULT)));
		JLabel snake5 = new JLabel(new ImageIcon(new ImageIcon("src\\snake5.png").getImage().getScaledInstance(219, 230, Image.SCALE_DEFAULT)));
		
		frame.getContentPane().add(snake1);
		frame.getContentPane().add(snake2);
		frame.getContentPane().add(snake3);
		frame.getContentPane().add(snake4);
		frame.getContentPane().add(snake5);
		
		Dimension size1 = snake1.getMaximumSize();
		Dimension size2 = snake2.getMaximumSize();
		Dimension size3 = snake3.getMaximumSize();
		Dimension size4 = snake4.getMaximumSize();
		Dimension size5 = snake5.getMaximumSize();
		
		snake1.setBounds(50,-100, size1.width,size1.height);
		snake2.setBounds(230,30, size2.width,size2.height);
		snake3.setBounds(180,150, size3.width,size3.height);
		snake4.setBounds(150,150, size4.width,size4.height);
		snake5.setBounds(280,130, size5.width,size5.height);
	}
	
	/**
	 * Ladders image created in JFrame using JLabel ladder images
	 */
	public void makeLadders() {
		JLabel ladder1 = new JLabel(new ImageIcon(new ImageIcon("src\\ladder1.png").getImage().getScaledInstance(160, 50, Image.SCALE_DEFAULT)));
		JLabel ladder2 = new JLabel(new ImageIcon(new ImageIcon("src\\ladder2.png").getImage().getScaledInstance(50, 100, Image.SCALE_DEFAULT)));
		JLabel ladder3 = new JLabel(new ImageIcon(new ImageIcon("src\\ladder3.png").getImage().getScaledInstance(20, 100, Image.SCALE_DEFAULT)));
		JLabel ladder4 = new JLabel(new ImageIcon(new ImageIcon("src\\ladder4.png").getImage().getScaledInstance(250, 50, Image.SCALE_DEFAULT)));
		JLabel ladder5 = new JLabel(new ImageIcon(new ImageIcon("src\\ladder5.png").getImage().getScaledInstance(80, 60, Image.SCALE_DEFAULT)));
		
		frame.getContentPane().add(ladder1);
		frame.getContentPane().add(ladder2);
		frame.getContentPane().add(ladder3);
		frame.getContentPane().add(ladder4);
		frame.getContentPane().add(ladder5);
		
		Dimension size1 = ladder1.getMaximumSize();
		Dimension size2 = ladder2.getMaximumSize();
		Dimension size3 = ladder3.getMaximumSize();
		Dimension size4 = ladder4.getMaximumSize();
		Dimension size5 = ladder5.getMaximumSize();
		
		ladder1.setBounds(220,220, size1.width,size1.height);
		ladder2.setBounds(280,100, size2.width,size2.height);
		ladder3.setBounds(100,50, size3.width,size3.height);
		ladder4.setBounds(310,100, size4.width,size4.height);
		ladder5.setBounds(100,40, size5.width,size5.height);
	}
	
	/**
	 * This is the first page of of program to initialize the game.
	 */
	public void mainMenu() {
		start.setName("start");
		quit.setName("quit");
		
		start.addActionListener(ls);
		quit.addActionListener(ls);
		
		frame.getContentPane().add(start);
		frame.getContentPane().add(quit);
		Dimension size = start.getPreferredSize();
		Dimension mapSize = map.getPreferredSize();
		start.setBounds(((mapSize.width-size.width)/2), mapSize.height/2,size.width,size.height); 
		quit.setBounds(((mapSize.width-size.width)/2),mapSize.height/2+size.height,size.width,size.height); 
	}
	
	/**
	 * This method is to create JLabel to provide user sufficient message & steps to interact with users.
	 * @param dice1
	 * @param dice2
	 */
	public void makePlayerAction(int dice1, int dice2) {
		if (!playeraction.getText().isEmpty()) {
			refreshComponent(playeraction);
		}
		playeraction = new JLabel("<html>Player Action<br>Player 1: "+ firstPlayer.playerNextmove()+"<br>Movement: "+ dice1
				+ "<br>Status: "+ firstPlayer.getPlayerStatus()
				+ "<br>Player 2: "+secondPlayer.playerNextmove() +"<br>Movement: "+ dice2
				+ "<br>Status: "+ secondPlayer.getPlayerStatus()
				+ "</html>");
		
		Border border = BorderFactory.createLineBorder(Color.BLACK, 3);
		Border margin = new EmptyBorder(5,5,5,5);
		playeraction.setBorder(new CompoundBorder(border, margin));
		playeraction.setFont(new Font("Serif", Font.PLAIN, 15));
		frame.getContentPane().add(playeraction);
		
		Dimension size = playeraction.getPreferredSize();
		Dimension mapSize = map.getPreferredSize();
		playeraction.setBounds((mapSize.width-size.width)/2,mapSize.height, size.width,size.height);
	}
	
	/**
	 * This is the scoreboard of Player 2.
	 * @param score
	 */
	public void makePlayerTwoScoreBoard(int score) {
		
		if (!scoreboard2.getText().isEmpty()) {
			refreshComponent(scoreboard2);
		}
		scoreboard2 = new JLabel("<html>ScoreBoard<br>Player 2:		"+score+" </html>");
		Border border = BorderFactory.createLineBorder(Color.BLACK, 3);
		Border margin = new EmptyBorder(10,10,10,10);
		scoreboard2.setBorder(new CompoundBorder(border, margin));
		scoreboard2.setFont(new Font("Serif", Font.PLAIN, 15));
		frame.getContentPane().add(scoreboard2);
		
		Dimension size = scoreboard2.getPreferredSize();
		Dimension mapSize = map.getPreferredSize();
		scoreboard2.setBounds(mapSize.width-120,480, size.width,size.height);
	}
	
	/**
	 * This is the scoreboard for Player 1.
	 * @param score
	 */
	public void makePlayerOneScoreBoard(int score) {
		
		if (!scoreboard1.getText().isEmpty()) {
			refreshComponent(scoreboard1);
		}
		scoreboard1 = new JLabel("<html>ScoreBoard<br>Player 1:		"+score+"</html>");
		Border border = BorderFactory.createLineBorder(Color.BLACK, 3);
		Border margin = new EmptyBorder(10,10,10,10);
		scoreboard1.setBorder(new CompoundBorder(border, margin));
		scoreboard1.setFont(new Font("Serif", Font.PLAIN, 15));
		frame.getContentPane().add(scoreboard1);
		
		Dimension size = scoreboard1.getPreferredSize();
		scoreboard1.setBounds(20,480, size.width,size.height);
	}
	
	/**
	 * This method is to delete a JComponent in the game when is necessary.
	 * @param c
	 */
	public void refreshComponent(Component c) {
		frame.getContentPane().remove(c);
		frame.revalidate();
		frame.repaint();
	}
	
	/**
	 * This is the dice button for Player 2 to roll the dice.
	 * @param dice
	 */
	public void makePlayerTwoDice(int dice) {
		switch(dice) {
			case 1:
				refreshComponent(diceTwo);
				diceTwo = new JButton(new ImageIcon(new ImageIcon("src\\dice1.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
				break;
			case 2:
				refreshComponent(diceTwo);
				diceTwo = new JButton(new ImageIcon(new ImageIcon("src\\dice2.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
				break;
			case 3:
				refreshComponent(diceTwo);
				diceTwo = new JButton(new ImageIcon(new ImageIcon("src\\dice3.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
				break;
			case 4:
				refreshComponent(diceTwo);
				diceTwo = new JButton(new ImageIcon(new ImageIcon("src\\dice4.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
				break;
			case 5:
				refreshComponent(diceTwo);
				diceTwo = new JButton(new ImageIcon(new ImageIcon("src\\dice5.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
				break;
			case 6:
				refreshComponent(diceTwo);
				diceTwo = new JButton(new ImageIcon(new ImageIcon("src\\dice6.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
				break;
			default: 
				diceTwo = new JButton(new ImageIcon(new ImageIcon("src\\dice6.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
				break;
		}
		diceTwo.setName("diceTwo");
		frame.getContentPane().add(diceTwo);
		diceTwo.setBounds(500,550,50,50);
		diceTwo.addActionListener(ls);
	}
	
	/**
	 * This is the dice button for Player 1 to roll the dice.
	 * @param dice
	 */
	public void makePlayerOneDice(int dice) {
		switch(dice) {
			case 1:
				refreshComponent(diceOne);
				diceOne = new JButton(new ImageIcon(new ImageIcon("src\\dice1.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
				break;
			case 2:
				refreshComponent(diceOne);
				diceOne = new JButton(new ImageIcon(new ImageIcon("src\\dice2.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
				break;
			case 3:
				refreshComponent(diceOne);
				diceOne = new JButton(new ImageIcon(new ImageIcon("src\\dice3.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
				break;
			case 4:
				refreshComponent(diceOne);
				diceOne = new JButton(new ImageIcon(new ImageIcon("src\\dice4.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
				break;
			case 5:
				refreshComponent(diceOne);
				diceOne = new JButton(new ImageIcon(new ImageIcon("src\\dice5.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
				break;
			case 6:
				refreshComponent(diceOne);
				diceOne = new JButton(new ImageIcon(new ImageIcon("src\\dice6.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
				break;
			default: 
				diceOne = new JButton(new ImageIcon(new ImageIcon("src\\dice6.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
				break;
		}
		diceOne.setName("diceOne");
		frame.getContentPane().add(diceOne);
		diceOne.setBounds(40,550,50,50);
		diceOne.addActionListener(ls);
	}
	
	/**
	 * Restart button in the Snake & Ladders game to reset all the value.
	 */
	public void makeRestartButton() {
		
		ImageIcon restartbutton = new ImageIcon(new ImageIcon("src\\restart.jpg").getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT));
		JButton restart = new JButton(restartbutton);
		restart.setName("restart");
		restart.addActionListener(ls);
		frame.getContentPane().add(restart);
		Dimension size = map.getPreferredSize();
		restart.setBounds(((size.width-restartbutton.getIconWidth())/2), size.height+240, restartbutton.getIconHeight(), restartbutton.getIconWidth());
	}
	
	/**
	 * Create the icon of Player 1.
	 */
	public void makePlayerOneMap() {
		
		Dimension playerone = player1map.getPreferredSize();
		frame.getContentPane().add(player1map);
        player1map.setBounds(firstPlayer.getPXposition(),firstPlayer.getPYposition() , playerone.height, playerone.width);
	}
	
	/**
	 * Create the icon of Player 2.
	 */
	public void makePlayerTwoMap() {
			
		Dimension playertwo = player2map.getPreferredSize();
		frame.getContentPane().add(player2map);
		player2map.setBounds(secondPlayer.getPXposition(), secondPlayer.getPYposition(), playertwo.height, playertwo.width);
	}
	
	/**
	 * Create the JButton of Player 1 to roll the dice.
	 */
	public void makePlayerOneButton() {
		
		player1button.setName("player1");
		player1button.addActionListener(ls);
		frame.getContentPane().add(player1button);
		Dimension size = map.getPreferredSize();
		Dimension playerone = player1button.getPreferredSize();
		player1button.setBounds(0, size.height, playerone.height, playerone.width);
	}
	
	/**
	 * Create the JButton of Player 2 to roll the dice.
	 */
	public void makePlayerTwoButton() {
		
		player2button.setName("player2");
		player2button.addActionListener(ls);
		frame.getContentPane().add(player2button);
		Dimension size = map.getPreferredSize();
		Dimension playertwo = player2button.getPreferredSize();
		player2button.setBounds(size.width-playertwo.width+20, size.height, playertwo.height, playertwo.width);		
	}
	
	/**
	 * Create the JLabel image to create the Snake & Ladders map.
	 */
	public void mapCreated() {
		frame.getContentPane().add(map);
	    Dimension size = map.getPreferredSize();
        map.setBounds(0, 0, size.width, size.height);
	}
	
	/**
	 * Core of the program. JFrame created in this method.
	 */
	public void makeFrame() {
		
		frame = new JFrame("Snake & Ladders");
		frame.setMinimumSize(new Dimension(615, 700));
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.pack();
		frame.setVisible(true);
	}
}



