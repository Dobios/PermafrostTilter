package platform.game.actor;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;

public class Tree extends Actor {
	private Vector position;
	private double width = 1;
	private double height = 2;
	private Sprite skin;
	private boolean dead = false;
	private boolean burning = false;
	private boolean burnt = false;
	private double burntime = 3.0;

	public Tree(Vector position) {
		this.setPriority(40);
		this.position = position;
	}

	public Sprite getSprite() {
		if (dead) {
			skin = this.getSprite("tree.dead");
		} else {
			skin = this.getSprite("tree");
		}
		return skin;
	}
	
	public void update(Input input){
		double delta = input.getDeltaTime();
		if(burning) {
			burntime -= delta;
			if(burntime <= 0.0) {
				burnt=true;
			}
		}
	}

	public boolean hurt(Actor instigator, Damage type, double amount, Vector location) {
		switch (type) {
		case AIR:
			dead = true;
			burning = false;
			return true;
		case FIRE:
			burning = true;
			dead = true;
			return true;
		default:
			return false;
		}
	}

	public void draw(Input input, Output output) {
		output.drawSprite(getSprite(), getBox());
		double y = this.getBox().getMin().getY();
		double x = this.getBox().getMin().getX();
		if(dead && !burnt){
			
			output.drawSprite(this.getSprite("leaf.left"), new Box(new Vector(x-0.5,y+0.5),1.0,1.0));
			output.drawSprite(this.getSprite("leaf.center"), new Box(new Vector(x+0.5,y+0.5),1.0,1.0));
			output.drawSprite(this.getSprite("leaf.right"), new Box(new Vector(x+1.5,y+0.5),1.0,1.0));
		}
		if(burning){
			Sprite fire = this.getSprite("particules/flame");
			if(!burnt){
				output.drawSprite(fire, new Box(new Vector(x-0.1,y+0.3),0.25,0.6), 3.1415);
				output.drawSprite(fire, new Box(new Vector(x+0.8,y+0.2),0.2,0.5), 3.1415);
				output.drawSprite(fire, new Box(new Vector(x+0.5,y+0.2),0.25,0.55), 3.1415);
			}
			output.drawSprite(fire, new Box(new Vector(x+0.4,y+2),0.2,0.5), 3.1415);
			output.drawSprite(fire, new Box(new Vector(x+0.9,y+1.2),0.2,0.5), 3.1415);
			output.drawSprite(fire, new Box(new Vector(x+0.1,y+1),0.2,0.5), 3.1415);
			output.drawSprite(fire, new Box(new Vector(x+0.8,y+1.8),0.3,0.6), 3.1415);
		}
	}

	public Box getBox() { 
		return new Box(position, width, height);
	}

	public Vector getPosition() { 
		return position;
	}

}

