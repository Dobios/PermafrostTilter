package platform.game.actor.projectiles;

import platform.util.Output;
import platform.game.actor.Actor;
import platform.game.actor.Damage;
import platform.game.actor.particle.Particle;
import platform.game.actor.particle.PoufEffect;
import platform.util.Box;
import platform.util.Input;
import platform.util.Vector;

public class Snowball extends Projectile {


	private String skin = "particules/snowball/1";
	private int jumps = 1;

	public Snowball(Vector position, Vector velocity, Actor owner) {
		super(position, velocity, owner);

	}

	public Box getBox(){
		return new Box(getPosition(),1,1);
	}


	public void postUpdate(Input input){
		if(jumps<=0){
			getWorld().unregister(this);
		}
	}

	public void draw(Input input, Output output) {
		if(getWorld()!=null)
			output.drawSprite(getSprite(skin), getBox(), 20 * input.getTime());
	}

	@Override
	public void interact(Actor other) {
		super.interact(other);
		Vector delta = other.getBox().getCollision(getPosition());
		if (delta != null) {
			if (other.isSolid()) {	
				setPosition(getPosition().add(delta));
				getWorld().register(new Particle(this.getPosition(), 1, 1, 10, "particules/snowball/1", false, Vector.ZERO));
				jumps=0;
			}
			if (other!=getOwner() && other.hurt(this, Damage.COLD, 1.0, getPosition())){
				getWorld().register(new PoufEffect(this.getBox(),0.40));
				jumps=0;
			}
		}

	}
	@Override
	public boolean hurt(Actor instigator, Damage type, double amount, Vector location) {
		switch (type) {
		case HEAT:
			jumps = 0;
			return true;
		default:
			return super.hurt(instigator, type, amount, location);
		}

	}
}

