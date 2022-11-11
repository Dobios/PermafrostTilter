package platform.game.actor;

import platform.game.actor.particle.Particle;
import platform.util.Box;
import platform.util.Input;
import platform.util.Vector;

public class SnowStorm extends Actor {
	private Vector position;
	private double width;
	private double height;
	private Vector direction;
	private static final double STRENGTH = 9.0;
	private double cooldown = 1.0;
	
	public SnowStorm(Vector position, double width, double height, Vector direction) {
		this.setPriority(55);
		this.position = position;
		this.width = width;
		this.height = height;
		this.direction = direction;
	}
	
	@Override
	public void update(Input input) {
		super.update(input);
		cooldown -= input.getDeltaTime();
	}
	
	@Override
	public void postUpdate(Input input) {
		if (cooldown <= 0.0) {
			getWorld().register(new Particle(new Vector(getPosition().getX() + ((Math.random()-0.5)*width), 
														getPosition().getY() + ((Math.random()-0.5)*height)), 
														0.02,0.4+Math.random(), 1.0, "particules/smoke/white.1", true, direction.mul(STRENGTH)));
			cooldown = Math.random()/16.0;
		}
	}
	
	@Override
	public void interact(Actor other) {
		super.interact(other);
		if (getBox().isColliding(other.getBox())) {
			other.hurt(this, Damage.AIR, STRENGTH, direction);
		}
	}
	
	@Override
	public Box getBox() { 
		return new Box(position, width, height);
	}
	
	@Override
	public Vector getPosition() { 
		return position;
	}
	
}
