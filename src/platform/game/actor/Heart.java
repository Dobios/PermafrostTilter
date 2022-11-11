package platform.game.actor;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

public class Heart extends Actor {
	private double cooldown;
	private Vector position;
	private double width = 0.5;
	private double height = 0.5;

	public Heart(Vector position) {
		this.setPriority(45);
		this.position = position;
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
		if (cooldown <= 0 && delta !=null) {
			if (other.isPlayer() && other.hurt(this, Damage.HEAL, 1, Vector.ZERO)) {
				cooldown = 10;
			}
		}
	}

	public void draw(Input input, Output output) {
		if (cooldown <= 0) {
			output.drawSprite(getSprite("ui/health/heart.full"), getBox());
		}
	}

	public Box getBox() { 
		return new Box(position, width, height);
	}

	public Vector getPosition() { 
		return position;
	}

}

