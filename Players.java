/**
 * 
 *
 * @author Bosh (Hou Jun Ng)
 * @java 10.0.2
 */
public class Players {
	private int playerscore; //Score - mainly for player location
	private boolean playeraction; // mainly for update player dice
	private boolean nextmove; // mainly for update player move
	private String playerstatus; // mainly for update player status
    private int playerXposition; // player location on X
    private int playerYposition; // player location on Y
    private Die roller;        //the virtual Die
    private Board gameBoard = new Board();    //the virtual Board
    
    /**
     * Class for create players
     * @param playerscore
     * @param nextmove
     * @param playerXposition
     * @param playerYposition
     */
    public Players(int playerscore, boolean nextmove,
    		int playerXposition, int playerYposition) {
    	this.playerscore = playerscore;
    	this.nextmove = nextmove;
    	this.playerXposition = playerXposition;
    	this.playerYposition=playerYposition;
    }
    
    /**
     * To provide players exact & accurate position for each score they got.
     * @param dice
     */
    public void bufferPlay(int dice) {
    	playerscore += dice;
    	if(playerscore == 1) {playerXposition=20;playerYposition=240;}
    	else if(playerscore == 2) {playerXposition=20+55;playerYposition=240;}
    	else if(playerscore == 3) {playerXposition=20+55*2;playerYposition=240;}
    	else if(playerscore == gameBoard.getLadderNumber(0)) {
    		playerXposition=20+55*3;playerYposition=240; // same with 14
    		playerstatus = 	gameBoard.getLadderMessage(0);
    	}
    	//snakeladders from 4 to 14
    	else if(playerscore == 5) {playerXposition=30+55*4;playerYposition=250;}
    	else if(playerscore == 6) {playerXposition=20+55*5;playerYposition=240;}
    	else if(playerscore == 7) {playerXposition=20+55*6;playerYposition=240;}
    	else if(playerscore == 8) {playerXposition=20+55*7;playerYposition=240;}
    	else if(playerscore == 9) {playerXposition=20+55*8;playerYposition=240;}
    	else if(playerscore == 10) {playerXposition=20+55*9;playerYposition=240;}
    	else if(playerscore == 11) {playerXposition=20+55*9;playerYposition=240-50;}
    	else if(playerscore == gameBoard.getSnakeNumber(4)) {
    		playerXposition=20+55*8;playerYposition=240-50;
    		playerstatus = 	gameBoard.getSnakeMessage(4);
    		}
    	else if(playerscore == 13) {playerXposition=20+55*7;playerYposition=240-50;}
    	else if(playerscore == 14) {playerXposition=20+55*6;playerYposition=240-50;}
    	else if(playerscore == gameBoard.getLadderNumber(1)) {
    		playerXposition=20+55*5;playerYposition=240-50; //same with 36
    		playerstatus = 	gameBoard.getLadderMessage(1);	
    	}
    	//snakeladders from 15 to 36
    	else if(playerscore == 16) {playerXposition=20+55*4;playerYposition=240-50;}
    	else if(playerscore == 17) {playerXposition=20+55*3;playerYposition=240-50;}
    	else if(playerscore == gameBoard.getSnakeNumber(3)) {
    		playerXposition=20+55*2;playerYposition=240-50;
    		playerstatus = 	gameBoard.getSnakeMessage(3);
    		}
    	else if(playerscore == 19) {playerXposition=20+55;playerYposition=240-50;}
    	else if(playerscore == 20) {playerXposition=20;playerYposition=240-50;}
    	else if(playerscore == 21) {playerXposition=20;playerYposition=240-100;}
    	else if(playerscore == gameBoard.getLadderNumber(2)) {
    		playerXposition=20+55;playerYposition=240-100; //same with 42
    		playerstatus = 	gameBoard.getLadderMessage(2);	
    	}
    	//snakeladders from 22 to 42
    	else if(playerscore == 23) {playerXposition=20+55*2;playerYposition=240-100;}
    	else if(playerscore == 24) {playerXposition=20+55*3;playerYposition=240-100;}
    	else if(playerscore == gameBoard.getSnakeNumber(2)) {
    		playerXposition=20+55*4;playerYposition=240-100;
    		playerstatus = 	gameBoard.getSnakeMessage(2);
    	}
    	//snakes from 25 to 17
    	else if(playerscore == 26) {playerXposition=20+55*5;playerYposition=240-100;}
    	else if(playerscore == 27) {playerXposition=20+55*6;playerYposition=240-100;}
    	else if(playerscore == 28) {playerXposition=20+55*7;playerYposition=240-100;}
    	else if(playerscore == 29) {playerXposition=20+55*8;playerYposition=240-100;}
    	else if(playerscore == gameBoard.getLadderNumber(3)) {
    		playerXposition=20+55*9;playerYposition=240-100;
    		playerstatus = 	gameBoard.getLadderMessage(3);
    	}//same with 35
    	//ladders from 30 to 35
    	else if(playerscore == 31) {playerXposition=20+55*9;playerYposition=240-150;}
    	else if(playerscore == 32) {playerXposition=20+55*8;playerYposition=240-150;}
    	else if(playerscore == 33) {playerXposition=20+55*7;playerYposition=240-150;}
    	else if(playerscore == gameBoard.getSnakeNumber(1)) {
    		playerXposition=20+55*6;playerYposition=240-150;
    		playerstatus = 	gameBoard.getSnakeMessage(1);
    	}
    	//snakes from 34 to 24
    	else if(playerscore == 35) {playerXposition=20+55*5;playerYposition=240-150;}
    	else if(playerscore == 36) {playerXposition=20+55*4;playerYposition=240-150;}
    	else if(playerscore == 37) {playerXposition=20+55*3;playerYposition=240-150;}
    	else if(playerscore == gameBoard.getLadderNumber(4)) {
    		playerXposition=20+55*2;playerYposition=240-150;
    		playerstatus = 	gameBoard.getLadderMessage(4);
    	}
    	//ladders from 38 to 42
    	else if(playerscore == 39) {playerXposition=20+55;playerYposition=240-150;}
    	else if(playerscore == 40) {playerXposition=20;playerYposition=240-150;}
    	else if(playerscore == 41) {playerXposition=20;playerYposition=240-200;}
    	else if(playerscore == 42) {playerXposition=20+55;playerYposition=240-200;}
    	else if(playerscore == 43) {playerXposition=20+55*2;playerYposition=240-200;}
    	else if(playerscore == 44) {playerXposition=20+55*3;playerYposition=240-200;}
    	else if(playerscore == 45) {playerXposition=20+55*4;playerYposition=240-200;}
    	else if(playerscore == 46) {playerXposition=20+55*5;playerYposition=240-200;}
    	else if(playerscore == gameBoard.getSnakeNumber(0)) {
    		playerXposition=20+55*6;playerYposition=240-200;
    		playerstatus = 	gameBoard.getSnakeMessage(0);
    	}
    	//snakes from 47 to 20
    	else if(playerscore == 48) {playerXposition=20+55*7;playerYposition=240-200;}
    	else if(playerscore == 49) {playerXposition=20+55*8;playerYposition=240-200;}
    	else if(playerscore == 50) {
    		playerXposition=20+55*9;playerYposition=240-200;}
    }
    
    /**
     * Roll the dice for specific player.
     * @param player
     * @param action
     * @return
     */
    public int playerDice(int player, boolean action) {
    	if (action == true) {
    		return 0;
    	}
    	else {
    		roller = new Die();
    		int x = roller.roll();
    		return x;
    	}
    	
    }
    
    /**
     * Setters of player status
     * @param playerstatus
     */
    public void setPlayerStatus(String playerstatus) {
    	this.playerstatus=playerstatus;
    }
    
    /**
     * Getters of Player Status
     * @return playerstatus;
     */
    public String getPlayerStatus() {
    	return playerstatus;
    }
    
    /**
     * Getters of Next Move
     * @return nextmove;
     */
    public boolean getNextMove() {
    	return nextmove;
    }
    
    /**
     * Setters of next move action by using boolean
     * @param nextmove
     */
    public void setNextMove(boolean nextmove) {
    	this.nextmove=nextmove;
    }
    
    /**
     * Getters of player action by using boolean
     * @return playeraction
     */
    public boolean getPlayerAction() {
    	return playeraction;
    }
    
    /**
     * Providing user message and guidance for their next action in game
     * @return message;
     */
    public String playerNextmove() {
    	if (nextmove==true) {
    		return "Player Turn, Please roll the die.";
    	}
    	else {
    		return "Please Wait for next move.";
    	}
    }
    
    /**
     * Setters of Player Action using boolean
     * @param playeraction
     */
    public void setPlayerAction(boolean playeraction) {
    	this.playeraction=playeraction;
    }
    
    /**
     * Setters of Players score
     * @param playerscore
     */
    public void setPlayerScore(int playerscore) {
    	this.playerscore=playerscore;
    }
    
    /**
     * Getters of playerscore
     * @return playerscore
     */
    public int getPlayerScore() {
    	return playerscore;
    }
    
    /**
     * Set Player X Position in frame
     * @param playerXposition
     */
    public void setPXpostion(int playerXposition) {
    	this.playerXposition=playerXposition;
    }
    
    /**
     * Get Player X position in frame
     * @return playerXposition
     */
    public int getPXposition() {
    	return playerXposition;
    }
    
    /**
     * Set Players Y Position in frame
     * @param playerYposition
     */
    public void setPYpostion(int playerYposition) {
    	this.playerYposition=playerYposition;
    }
    
    /**
     * Get Player Y Position in frame.
     * @return playerYposition
     */
    public int getPYposition() {
    	return playerYposition;
    }
}
