import java.util.*;
/**
 * 
 *
 * @author Bosh (Hou Jun Ng)
 * @java 10.0.2
 */
public class Die {
	public static final int defaultFaces = 6;
	public static final int minFaces = 1;
	private int numFaces;
	private int faceValue;
	private static Random generator;
	
	public Die() {
        this(defaultFaces);
    }
	
	/**
	 * To ensure the random number is generate within 1 to 6
	 * @param faces
	 */
	public Die(int faces) {
        if (faces >= minFaces) {
            numFaces = faces;
        } else {
            numFaces = defaultFaces;
        }
        faceValue = 1;
        generator = new Random();
    }
	
	/**
	 * Getter of die number of faces
	 * @return numFaces;
	 */
	public int getFaces() {
        return numFaces;
    }
	
	/**
	 * To roll the die with Random generator within 1 to 6.
	 * @return faceValue;
	 */
	public int roll() {
        faceValue = generator.nextInt(numFaces) + 1;
        return faceValue;
    }
	
	/**
	 * Getter of random face value
	 * @return faceValue;
	 */
	public int getFaceValue() {
        return faceValue;
    }
}
