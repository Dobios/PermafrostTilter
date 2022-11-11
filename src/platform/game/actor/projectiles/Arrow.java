package platform.game.actor.projectiles;

import platform.game.actor.Actor;
import platform.game.actor.Damage;
import platform.game.actor.StuckItem;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

public class Arrow extends Projectile {
	private boolean hit = false;
	private double height = 0.25;
	private double width = 0.42;

	public Arrow(Vector position, Vector velocity,Actor owner) {
		super(position,velocity,owner);
	}

	@Override
	public Box getBox() {
		return new Box(getPosition(), width, height);
	}

	public void draw(Input input, Output output) {
		if(getWorld() != null && !hit)
			output.drawSprite(getSprite("arrow"), getBox());
	}

	@Override
	public void interact(Actor other) {
		super.interact(other);
		Vector delta = getBox().getCenter().sub(other.getBox().getCenter());
		if (!hit && other.getBox().isColliding(getBox())) {
			if ((other.isSolid()&&other!=getOwner()) || other.hurt(this, Damage.PHYSICAL, 3.0, getPosition())) {
				getWorld().register(new StuckItem (delta, other, 0.15, 0.70, "arrow"));
				hit = true;
			}
		}
	}
	
	public void postUpdate() {
		if (hit) {
			getWorld().unregister(this);
		}
	}
}
