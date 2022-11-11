package platform.game.actor.block;

import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

public class Sign extends Block {
	private double rotation;
	
	public Sign(Vector position, double rotation, String skin) {
		super(position, 1, 1, skin);
		this.rotation = rotation;
	}
	
	public void draw(Input input, Output output) {
		output.drawSprite(getSprite(), getBox(), rotation);
	}
	
	public boolean isSolid() {
		return false;
	}
}
