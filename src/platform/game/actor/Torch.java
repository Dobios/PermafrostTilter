package platform.game.actor;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;
import platform.game.actor.particle.Particle;
import platform.game.signals.*;

public class Torch extends Actor implements Signal {
	private Vector position;
	private Signal signal = null;
	private double width = 0.8;
	private double height = 0.8;
	private Sprite skin;
	private boolean lit = false;
	private double variation;
	private double cooldown = 1.0;

	public Torch(Vector position, boolean lit) {
		this.setPriority(25);
		this.position = position;
		this.lit =  lit;
	}
	
	public Torch(Vector position) {
		this.setPriority(25);
		this.position = position;
	}
	
	public Torch(Vector position, Signal signal) {
		this.setPriority(25);
		this.position = position;
		this.signal = signal;
	}

	@Override
	public boolean isActive() {
		return lit;
	}

	public Sprite setSprite() {
		if (lit) {
			String name = "torch.lit.1";
			if (variation < 0.3) {
				name = "torch.lit.2";
			}
			skin = getWorld().getLoader().getSprite(name);
		} else {
			skin = getWorld().getLoader().getSprite("torch");
		}
		return skin;
	}


	@Override
	public void update(Input input) {
		super.update(input);
		variation -= input.getDeltaTime();
		cooldown -= input.getDeltaTime();
		if (variation < 0.0) {
			variation = 0.6;
		}
		if(signal!=null){
			if(signal.isActive())
				lit=true;
			else
				lit = false;
		}
	}
	
	@Override
	public void postUpdate(Input input) {
		if (lit && cooldown <= 0.0) {
			getWorld().register(new Particle(new Vector(getPosition().getX() + (Math.random()-0.5)/4.0, 
														getPosition().getY()+0.2), 
											0.1, 0.1, 0.5, "particules/smoke/white.1", true, new Vector(0.0,3.0)));
			cooldown = Math.random();
		}
	}

	public boolean hurt(Actor instigator, Damage type, double amount, Vector location) {
		switch (type) {
		case AIR:
			lit = false;
			return true;
		case FIRE:
			if(!lit){
				lit = true;
				return true;
			}
		default:
			return false;
		}
	}

	public void draw(Input input, Output output) {
		if (lit) {
			output.drawSprite(getSprite("particules/heat.zone"),new Box(position, 3, 3));
		}
		output.drawSprite(setSprite(), getBox());
	}

	public Box getBox() { 
		return new Box(position, width, height);
	}

	public Vector getPosition() { 
		return position;
	}

}

