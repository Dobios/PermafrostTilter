package platform.game;

import java.util.ArrayList;
import platform.util.Box;
import platform.game.actor.*;
import platform.util.Input;
import platform.util.Loader;
import platform.util.Output;
import platform.util.SortedCollection;
import platform.util.Vector;
import platform.util.View;
import platform.game.level.*;

/**
 * Basic implementation of world, managing a complete collection of actors.
 */
public class Simulator implements World {

	private Vector currentCenter;
	private double currentRadius;
	private Vector expectedCenter;
	private double expectedRadius;
	private Loader loader;
	private View view;
	private SortedCollection<Actor> actors = new SortedCollection<Actor>();
	private ArrayList <Actor> registered = new ArrayList<Actor>();
	private ArrayList <Actor> unregistered = new ArrayList<Actor>();
	private Level next;
	private Level currentLevel;
	private boolean transition = true;
	private Difficulty diff;
	private int[] chrono = new int[3];
	private boolean respawn;
	private GameType type = GameType.FREE;


	/**
	 * Create a new simulator.
	 * @param loader associated loader, not null
	 * @param args level arguments, not null
	 */
	public Simulator(Loader loader, String[] args) {
		if (loader == null) {
			throw new NullPointerException();
		}
		this.loader = loader; 
		currentCenter = Vector.ZERO;
		currentRadius = 10.0;
		expectedCenter = Vector.ZERO;
		expectedRadius = 10.0;
		diff = Difficulty.NORMAL;
		respawn = true;
	}

	public void setDifficulty(Difficulty diff) {
		this.diff = diff;
	}
	
	public Difficulty getDifficulty() {
		return diff;
	}
	
	public int[] getChrono() {
		return chrono;
	}

	public void setChrono(int[] time) {
		this.chrono=time;
	}
	
	public void resetChrono(){
		this.chrono = new int[3];
	}
	
	public void setGameType(GameType type){
		this.type = type;
	}
	
	public GameType getGameType(){
		return type;
	}

	public boolean isGameTypeStory(){
		return type==GameType.STORY;
	}
	
	public void setRespawn(boolean respawn) {
		this.respawn = respawn;
	}

	public boolean getRespawn() {
		return respawn;
	}
	
	public double getWWidth(){
		if(view!=null)
			return view.getSizeX();
		else
			return 0;
	}
	
	public double getWHeight(){
		if(view!=null)
			return view.getSizeY();
		else
			return 0;
	}

	@Override
	public void setView(Vector center, double radius) {
		if (center == null) {
			throw new NullPointerException();
		}
		if (radius <= 0) {
			throw new IllegalArgumentException ("radius must be positive");
		}
		expectedCenter = center;
		expectedRadius = radius;
	}
	
	@Override
	public Vector getViewCenter() {
		return this.currentCenter;
	}

	@Override
	public double getViewRadius() {
		return this.currentRadius;
	}

	/**
	 * Simulate a single step of the simulation.
	 * @param input input object to use, not null
	 * @param output output object to use, not null
	 */
	public void update(Input input, Output output) {
		double factor = 0.06;
		currentCenter = currentCenter.mul(1.0 - factor).add(expectedCenter.mul(factor));
		currentRadius = currentRadius * (1.0 - factor) + expectedRadius * factor;

		this.view = new View(input, output);
		this.view.setTarget(currentCenter, currentRadius);

		for (Actor a : actors) {
			a.preUpdate(this.view);
		}

		for (Actor actor : actors) for (Actor other : actors) {
			if (actor.getPriority() > other.getPriority()) {
				actor.interact(other);
			}
		}

		// Update Actors
		for (Actor a : actors) {
			a.update(this.view);
		}

		// Display Actors
		for (Actor a : actors.descending()) {
			a.draw(this.view, this.view);
		}

		for (Actor a : actors) {
			a.postUpdate(this.view);
		}

		//Add registered actors
		for (int i = 0; i < registered.size(); ++i) {
			Actor actor = registered.get(i);
			if (!actors.contains(actor)) {
				actors.add(actor);
			}
		}
		registered.clear();

		//Remove unregistered actors
		for (int i= 0; i < unregistered.size(); ++i) {
			Actor actor = unregistered.get(i);
			actors.remove(actor);
		}
		unregistered.clear();

		//si un acteur change next à true
		//à un autre niveau :
		if (transition) {
			transition = false;
			if (next == null) {
				next = Level.createDefaultLevel();
			}
			//si un acteur a appelé setNextLevel, next ne sera pas null:
			Level level = next;
			currentLevel = level;
			next = null;
			actors.clear();
			//tous les anciens acteurs sont désenregistrés,
			//y compris le level précédent:
			register(level);
		}
	}

	@Override
	public void register(Actor actor) {
		registered.add(actor);
		actor.register(this);
	}

	@Override
	public void unregister(Actor actor) {
		unregistered.add(actor);
		actor.unregister();
	}


	@Override
	public int hurt(Box area, Actor instigator, Damage type,
			double amount, Vector location) { 
		int victims = 0;
		for (Actor actor : actors)
			if (area.isColliding(actor.getBox()))
				if (actor.hurt(instigator, type, amount, location))
					++victims; 
		return victims;
	}

	@Override
	public Loader getLoader() {
		return loader;
	}

	public void nextLevel() {
		this.transition = true;
	}

	public void setNextLevel(Level level) {
		this.next = level;
	}

	@Override
	public Level getCurrentLevel() {
		return currentLevel;
	}
}
