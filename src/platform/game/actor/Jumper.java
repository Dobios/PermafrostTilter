package platform.game.actor;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;

public class Jumper extends Actor {
	private double cooldown;
	private Vector position;
	private double width;
	private double height;
	
	public Jumper(Vector position, double width, double height) {
		this.setPriority(44);
		this.position = position;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public boolean isSolid() { 
		return true;
	}
	
	@Override
	public void update(Input input) {
		super.update(input);
		cooldown -= input.getDeltaTime();
	}
	
	@Override
	public void interact(Actor other) {
		super.interact(other);
		Vector delta = other.getBox().getCollision(getBox()); 
		if(delta!=null){
			if(other.isPlayer()){
				other.interact(this);
			}
			if(cooldown <= 0.0){
				Vector below = new Vector(0.0,15.0);
				if (other.hurt(this, Damage.AIR, 1.0, below)) {
					cooldown = 1.0;
				}
			}
			
		}
	}
	
	public Sprite getSprite() {
		if (cooldown > 0.0) {
			return getSprite("jumper.extended");
		} else {
			return getSprite("jumper.normal");
		}
	}
	
	public void draw(Input input, Output output) {
			output.drawSprite(getSprite(), getBox());
	}
	
	public Box getBox() { 
		return new Box(position, width, height);
	}
	
	public Vector getPosition() { 
		return position;
	}
	
}
