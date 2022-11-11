package platform.game.actor.block;

import platform.game.actor.Actor;
import platform.game.actor.Damage;
import platform.game.actor.particle.PoufEffect;
import platform.util.Input;
import platform.util.Vector;

public class Destruct extends Block {
	private boolean hit;

	public Destruct(Vector position) {
		super(position, 1, 1, "box.single");
		this.setPriority(4);
	}

	public void postUpdate(Input input) {
		if (hit) {
			getWorld().register(new PoufEffect(this.getBox(),0.40));
			getWorld().unregister(this);
		}
	}

	@Override
	public boolean hurt(Actor instigator, Damage type, double amount, Vector location) {
		switch (type) {
		case FIRE:
			hit = true;
			return true;
		default:
			return false;
		}

	}
}
