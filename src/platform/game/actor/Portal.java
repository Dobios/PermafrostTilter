package platform.game.actor;

import platform.game.signals.Signal;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

public class Portal extends Actor{
	private Vector position;
	private Signal signal;
	
	public Portal(Vector position, double width, double height,Signal signal) {
		this.setPriority(1111);
		this.position = position;
		this.signal = signal;
	}
	public Portal(Vector vect, Signal signal) {
		this.setPriority(1111);
		this.position = vect;
		this.signal = signal;
	}
	

	@Override
	public void interact(Actor other) {
		super.interact(other);
		Vector delta = other.getBox().getCollision(getBox());
		if (delta !=null) {
			if (other.isPlayer() && signal.isActive()) {
				getWorld().nextLevel();
			}
		}
	}

	public void draw(Input input, Output output) {
		if(signal.isActive()){
			output.drawSprite(getSprite("portal"), getBox(),input.getDeltaTime()*50);
			output.drawSprite(getSprite("particules/bubble"), getBox().add(new Vector(Math.random()/10,Math.random()/10)));
		}else{
			output.drawSprite(getSprite("particules/bubble"), getBox(),input.getDeltaTime());
		}
	}
	
	public Box getBox() { 
		return new Box(position,1, 1);
	}
	
	public Vector getPosition() { 
		return position;
	}
}
