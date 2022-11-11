package platform.game.actor.block;

import platform.util.Vector;
import platform.game.signals.*;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.game.actor.Actor;
import platform.game.actor.Damage;

public class Mover extends Block {
	private Vector on;
	private Vector off;
	private Signal state = null;
	private double current = 0.0;
	private double height;
	private double width;
	private double position;
	private Vector lastPosition;
	private double dT;
	private double speed = 0.5;
	private boolean status = false;

	public Mover(Vector on, Vector off, double width, double height, String skin, Signal signal) {
		super(off, 1, 1, skin);
		this.on = on;
		this.off = off;
		this.height = height;
		this.width = width;
		state = signal;
	}

	public Mover(Vector on, Vector off, double width, double height, String skin) {
		super(off, 1, 1, skin);
		this.on = on;
		this.off = off;
		this.height = height;
		this.width = width;

	}

	@Override 
	public void update (Input input) {
		super.update(input);
		double delta = input.getDeltaTime();
		this.dT = delta;
		this.lastPosition = getBox().getCenter();
		if (state == null) {
			if (!status) {
				current += delta*speed;
				if (current > 1.0) {
					status = !status;
				}
			} else {
				current -= delta*speed;
				if (current < 0.0) {
					status = !status;
				}
			}
		} else {
			if (state.isActive()) {
				if (!status) {
					current += input.getDeltaTime()*speed;
					if (current > 1.0) {
						current = 1.0;
						status = !status;
					}
				} else {
					current -= delta*speed;
					if (current < 0.0) {
						status = !status;
					}
				}
			} else {
				current -= input.getDeltaTime()*speed;
				if (current < 0.0) 
					current = 0.0;
				status = !status;
			}
		}	
	}

	@Override
	public Box getBox() { 
		this.position = - 2 * current * current * current + 3 * current * current; 
		//this.position = current;
		return new Box(off.mixed(on, position),width, height);
	}

	public void draw(Input input, Output output) {
		output.drawSprite(getSprite(), getBox());
	}

	public Vector getSpeed(){
		return  getBox().getCenter().sub(lastPosition).div(dT);
	}

	public Vector getPosition() { 
		return getBox().getCenter();
	}

	public boolean isSticky(){
		return state.isActive();
	}


	@Override
	public boolean hurt(Actor instigator, Damage type, double amount, Vector location) {
		switch (type) {
		case STICK:
			return true;
		default: 
			return false;
		}
	}
}
