/**
 * 
 *
 * @author Bosh (Hou Jun Ng)
 * @java 10.0.2
 */
public class Board {
    private int[] ladderStarts = { 4, 15, 22, 30, 38 };
    private int[] ladderEnds =   {14, 36, 42, 35, 42 };
    private String[] ladderMessages = {
        ":-) You have worked on this unit <br>every day this week. Score +10",
        ":-) All your tutorial work and <br>your record book is up to date. Score +21",
        ":-) You have your assignment <br>completed two days before it is due. Score +20",
        ":-) You have read over the notes <br>before this week's lectures. Score +5",
        ":-) You have revised the topics from <br>last week's lectures. Score +4"
    };
    
    private int[] snakeStarts = {47, 34, 25, 18, 12 };
    private int[] snakeEnds =   {20, 24, 17,  8, 5  };
    private String[] snakeMessages = {
        ":-( You have copied your assignment <br>from another student. Score -27",
        ":-( You have written 100 lines of <br>code without compiling. Score -10",
        ":-( You have not done any work on <br>this unit for 10 days. Score -8",
        ":-( You are just starting work on <br>your assignment - it is due in 2 days. Score -10",
        ":-( You spent the last lecture <br>reading the newspaper. Score -7"
    };
    
    /**
     * Getters of difference between decrease on snakes
     * @param i
     * @return int
     */
    public int getSnakeDecrease(int i) {
    	return snakeStarts[i]-snakeEnds[i];
    }
    
    /**
     * Getters of difference between increase on ladders
     * @param i
     * @return int
     */
    public int getLadderIncrease(int i) {
    	return ladderEnds[i]-ladderStarts[i];
    }
    
    /**
     * Getters of SnakeNumber
     * @param i
     * @return snakeStarts[i]
     */
    public int getSnakeNumber(int i) {
    	return snakeStarts[i];
    }
    
    /**
     * 
     * @param i
     * @return ladderStarts[i];
     */
    public int getLadderNumber (int i) {
    	return ladderStarts[i];
    }
    
    /**
     * Getters of Snake MEssage
     * @param i
     * @return String snakeMessages[i]
     */
    public String getSnakeMessage(int i) {
    	return snakeMessages[i];
    }
    
    /**
     * Getters of ladder message
     * @param i
     * @return ladderMessages[i]
     */
    public String getLadderMessage (int i) {
    	return ladderMessages[i];
    }
}
