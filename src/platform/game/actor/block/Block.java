package platform.game.actor.block;

import platform.game.actor.Actor;
import platform.util.Box;
import platform.util.Input;
import platform.util.Sprite;
import platform.util.Vector;
import platform.util.Output;

/**
 * Simple solid actor that does nothing.
 */
public class Block extends Actor {
	private Vector position;
	private double height;
	private double width;
	private String skin;
	private boolean drawing = true;
	
	public Block(Vector position, double width, double height, String skin) {
		this.setPriority(0);
		this.position = position;
		this.width = width;
		this.height = height;
		this.skin = skin;
	}
	
	public Block(Box box) {
		this.setPriority(0);
		this.position = box.getCenter();
		this.height = box.getHeight();
		this.width = box.getWidth();
	}
	public Block(Box box, boolean drawing) {
		this.setPriority(0);
		this.drawing  = drawing;
		this.position = box.getCenter();
		this.height = box.getHeight();
		this.width = box.getWidth();
	}
	
	public Sprite getSprite() {
		return getSprite(skin);
	}
	
	public void draw(Input input, Output output) {
		if(drawing)
			output.drawSprite(getSprite(), getBox());
	}
	
	public Box getBox() { 
		return new Box(position,width, height);
	}
	
	public boolean isSolid() { 
		return true;
	}
	
	public Vector getPosition() { 
		return position;
	}
	
}
