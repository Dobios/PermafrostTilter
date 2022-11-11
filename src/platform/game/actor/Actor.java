package platform.game.actor;

import platform.util.Box;
import platform.util.Input;
import platform.util.Vector;
import platform.util.Output;
import platform.util.Sprite;
import platform.game.World;

/**
 * Base class of all simulated actors, attached to a world.
 */
public abstract class Actor implements Comparable<Actor>{
	private int priority;
	private World world;

	//Pre-update
	public void preUpdate (Input input) {}

	// Update / Evolve
	public void update(Input input) {} 

	//Post-update
	public void postUpdate(Input input) {}

	// Draw on screeń
	public void draw(Input input, Output output) {}

	//Returns Actor Priority
	public int getPriority(){
		return priority;
	}

	public void setPriority(int priority){
		this.priority = priority;
	}

	//Interaction between Actors
	public void interact(Actor other) {}
	
	public Vector getSpeed() {
		return null; 
	}

	public boolean isSolid() { 
		return false;
	}

	public boolean isPlayer(){
		return false;
	}
	
	public boolean isClimbable(){
		return false;
	}
	
	public boolean isSticky(){
		return false;
	}
	
	public boolean isInteract(){
		return false;
	};

	public Box getBox() { 
		return null;
	}

	public Vector getPosition() { 
		Box box = getBox();
		if (box == null)
			return null;
		return box.getCenter();
	}

	//Compares to actors
	@Override
	public int compareTo(Actor other) {
		int prio1 = this.getPriority();
		int prio2 = other.getPriority();

		if (prio1 > prio2)
			return -1;
		else if (prio1 < prio2)
			return 1;
		else
			return 0;
	}

	public void register(World world) {
		this.world = world;
	}

	public void unregister() {
		world = null;
	}

	protected World getWorld() {
		return this.world;
	}

	protected Sprite getSprite(String name) {
		if (getWorld() == null) {
			return null;
		} else {
			return getWorld().getLoader().getSprite(name);
		}
	}
	
	public boolean hurt(Actor instigator, Damage type, double amount, Vector location) {
		switch(type) {
		case VOID:
			this.getWorld().unregister(this);
			return true;
		default:
			return false;		
		}
	}

	public double getFriction() {
		return 0.001;
	}
}
