package platform.game.actor.projectiles;

import platform.game.World;
import platform.game.actor.Actor;
import platform.util.Box;
import platform.util.Input;
import platform.util.Vector;

public class Projectile extends Actor {
	private Vector position;
	private Vector velocity;
	private Actor owner;
	
	public Projectile(Vector position, Vector velocity, Actor owner){
		this.setPriority(666);
		this.position = position;
		this.velocity = velocity;
		this.owner = owner;

		if (position == null || velocity == null) {
			throw new NullPointerException();
		}
	}
	
	public void update(Input input) {
		super.update(input);
		double delta = input.getDeltaTime();
		Vector acceleration = World.getGravity();
		velocity = velocity.add(acceleration.mul(delta));
		position = position.add(velocity.mul(delta));
	}
	
	public Vector getPosition(){
		return position;
	}
	public void setPosition(Vector position){
		this.position = position;
	}
	
	public Vector getVelocity(){
		return velocity;
	}
	public void setVelocity(Vector velocity){
		this.velocity = velocity;
	}
	
	public Actor getOwner(){
		return owner;
	}
	
	@Override
	public Box getBox() {
		double SIZE = 0.42;
		return new Box(position, SIZE, SIZE);
	}
	
}
