package platform.game.actor;

import platform.game.actor.particle.Particle;
import platform.game.signals.Signal;
import platform.util.Box;
import platform.util.Input;
import platform.util.Vector;

public class Cloud extends Actor {
	private Vector position;
	private double width;
	private double height;
	private double size;
	private Vector direction;
	private static final double STRENGTH = 6.0;
	private double cooldown=0.0 ;
	private Signal signal= null;
	
	public Cloud(Vector position, double width, double height, double size) {
		this.setPriority(55);
		this.position = position;
		this.width = width;
		this.height = height;
		this.size = size;
	}
	public Cloud(Vector position, double width, double height, double size, Signal signal) {
		this.setPriority(55);
		this.position = position;
		this.width = width;
		this.height = height;
		this.size = size;
		this.signal = signal;
	}
	
	@Override
	public void update(Input input) {
		super.update(input);
		if(signal== null || signal.isActive())
			cooldown -= input.getDeltaTime();
	}
	
	@Override
	public void postUpdate(Input input) {
		if (cooldown <= 0.0) {
			double flakeSize = size*(Math.random()/2.0+0.3);
			String skin = Math.random()>=0.5?"particules/snowflake/1":"particules/snowflake/2";
			getWorld().register(new Particle(new Vector(getPosition().getX() + ((Math.random()-0.5)*width), 
														getPosition().getY() + ((Math.random()-0.5)*height)), 
											flakeSize,flakeSize, 15.0, skin, true, 
											new Vector(0,-(2+4.0*Math.random()))));
			cooldown = (Math.random()/width*2);
		}
	}
	
	@Override
	public void interact(Actor other) {
		super.interact(other);
		if (getBox().isColliding(other.getBox())) {
			other.hurt(this, Damage.AIR, STRENGTH, direction);
		}
	}
	
	public Box getBox() { 
		return new Box(position, width, height);
	}
	
	public void setSize(double size){
		this.size = size;
	}
	
	public Vector getPosition() { 
		return position;
	}
	
}
