package platform.game.actor.block;

import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;
import platform.game.signals.*;

public class Door extends Block{
	private Signal signal;
	private boolean open = false;
	private boolean invisible = false;

	public Door(Vector position, Signal signal) {
		super(position, 1, 1,"lock.red");
		this.setPriority(41);
		this.signal = signal;
	}

	public Door(Vector position, Signal signal, boolean invisible) {
		super(position, 1, 1,"lock.red");
		this.setPriority(41);
		this.signal = signal;
		this.invisible = invisible;

	}

	@Override
	public void update(Input input) {
		super.update(input);
		if(signal.isActive())
			this.open = true;
	}
	public void postUpdate(Input input){
		if(this.open && !this.invisible)
			getWorld().unregister(this);
	}

	public void draw(Input input, Output output) {
		if(!open)
			output.drawSprite(getSprite(), getBox());
	}

	public boolean isSolid() { 
		if(!open)
			return true;
		else
			return false;
	}

}

