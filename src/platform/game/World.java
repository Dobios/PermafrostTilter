package platform.game;

import platform.game.actor.*;
import platform.util.Box;
import platform.util.Loader;
import platform.util.Vector;
import platform.game.level.*;

/**
 * Represents an environment populated by actors.
 */
public interface World {

	/** @return associated loader, not null */
	public Loader getLoader();

	public void setView(Vector center, double radius);
	
	public Vector getViewCenter();
	
	public double getViewRadius();
	
	//marque le moment de passage au niveau suivant
	public void nextLevel();
	
	//permet de passer au niveau level
	public void setNextLevel(Level level);
	
	public Level getCurrentLevel();
	
	public Difficulty getDifficulty();
	
	public void setDifficulty(Difficulty diff);
	
	public int[] getChrono();
	
	public void setChrono(int[] time);
	
	public void resetChrono();
	
	public void setGameType(GameType type);
	
	public GameType getGameType();
	
	public boolean isGameTypeStory();
	
	public void setRespawn(boolean respawn);
	
	public boolean getRespawn();
	
	public double getWWidth();
	
	public double getWHeight();

	public int hurt(Box area, Actor instigator, Damage type, double amount, Vector location);
	
	public void register(Actor actor);

	public void unregister(Actor actor);

	public static Vector getGravity() {
		return new Vector(0.0, -19.62);
	}
}

