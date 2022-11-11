package platform.game.actor;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

public class Limits extends Actor {
	private Vector position;
	private double height;
	private double width;

	public Limits(Vector position, double width, double height) {
		this.setPriority(11111);
		this.position = position;
		this.width = width;
		this.height = height;
	}

	public Box getBox() { 
		return new Box(position,width, height);
	}
	
	public void draw(Input input, Output output){
		Vector min = getBox().getMin();
		Vector max = getBox().getMax();
		Vector center = getBox().getCenter();
		output.drawSprite(getSprite("ui/limits"), new Box(new Vector(center.getX(),min.getY()),width,2));

		output.drawSprite(getSprite("ui/limits"), new Box(new Vector(min.getX(),center.getY()),height,2),Math.PI/-2.0);

		output.drawSprite(getSprite("ui/limits"), new Box(new Vector(center.getX(),max.getY()),width,2),Math.PI);

		output.drawSprite(getSprite("ui/limits"), new Box(new Vector(max.getX(),center.getY()),height,2),Math.PI/2.0);
	
	}
	
	public void interact(Actor other) {
		super.interact(other);
		Vector delta = other.getBox().getCollision(getBox()); 
		if (delta == null){
			other.hurt(this, Damage.VOID, Double.POSITIVE_INFINITY, Vector.ZERO);
		}
	}
}
