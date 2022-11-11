package platform.game.actor.interactors;

import platform.game.actor.Actor;
import platform.game.actor.Damage;
import platform.game.signals.Signal;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

public class Exit extends Actor{
	private Vector position;
	private Signal signal = null;
	private boolean open = false;
	
	public Exit(Vector position, double width, double height, Signal signal) {
		this.setPriority(35);
		this.position = position;
		this.signal = signal;
	}
	public Exit(Vector position,Signal signal) {
		this.setPriority(35);
		this.position = position;
		this.signal = signal;
	}
	
	public Exit(Vector vect, boolean open) {
		this.setPriority(35);
		this.position = vect;
		this.open = open;
	}
	
	public void update(Input input){
		if(!this.open && signal !=null){
			if(signal.isActive()){
				this.open=true;
			}
		}
	}

	public void draw(Input input, Output output) {
		if(this.open)
			output.drawSprite(getSprite("door.open"), getBox());
		else
			output.drawSprite(getSprite("door.closed"), getBox());
	}
	
	public Box getBox() { 
		return new Box(position,1, 1);
	}
	
	public boolean isInteract(){
		return open;
	}
	
	public Vector getPosition() { 
		return position;
	}
	
	@Override
	public boolean hurt(Actor instigator, Damage type, double amount, Vector location) {
		switch (type) {
			case ACTIVATION:
				if(this.open){
					getWorld().nextLevel();
					return true;
				}
			default:
				return false;
		}
	}
}

