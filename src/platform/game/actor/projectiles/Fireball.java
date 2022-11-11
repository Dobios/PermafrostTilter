package platform.game.actor.projectiles;

import platform.util.Output;
import platform.game.actor.Actor;
import platform.game.actor.Damage;
import platform.game.actor.particle.PoufEffect;
import platform.util.Input;
import platform.util.Vector;

public class Fireball extends Projectile {

	private String skin = "fireball";
	private int jumps = 5;

	public Fireball(Vector position, Vector velocity, Actor owner) {
		super(position, velocity, owner);

	}

	public void postUpdate(Input input){
		if(jumps<=0){
			getWorld().register(new PoufEffect(this.getBox(),0.40));
			getWorld().unregister(this);
		}
	}

	public void draw(Input input, Output output) {
		if(getWorld()!=null)
			output.drawSprite(getSprite(skin), getBox(), (-20) * input.getTime());
	}

	@Override
	public void interact(Actor other) {
		super.interact(other);
		Vector delta = other.getBox().getCollision(getPosition());
		if (delta != null) {
			if (other.isSolid()) {	
				--jumps;
				setPosition(getPosition().add(delta));
				setVelocity(getVelocity().mirrored(delta));
			}
			if (other!=getOwner() && other.hurt(this, Damage.FIRE, 1.0, getPosition())) {
				jumps = 0;
			}
		}

	}
	@Override
	public boolean hurt(Actor instigator, Damage type, double amount, Vector location) {
		switch (type) {
		case AIR:
			jumps = 0;
			return true;
		default:
			return super.hurt(instigator, type, amount, location);
		}

	}
}

