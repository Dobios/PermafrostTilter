package platform.game.actor.particle;

import platform.util.Vector;
import platform.game.actor.Actor;
import platform.util.Box;
import platform.util.Sprite;
import platform.util.Input;
import platform.util.Output;

public class Particle extends Actor {
	private Vector position;
	private double width;
	private double height;
	private String skin;
	private double duration;
	private boolean moving;
	private Vector speed;
	private double angle;


	public Particle(Vector position, double width, double height, double duration, String name, boolean moving, Vector speed) {
		setPriority(10);
		this.position = position;
		this.width = width;
		this.height = height;
		this.duration = duration;
		this.skin = name;
		this.moving = moving;
		this.speed = speed;
		this.angle = speed.getAngle()+Math.PI/2.0;
	}
	
	public Particle() {
		super();
		setPriority(10);
	}

	public void Move() {
		moving = true;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setSpeed(Vector speed) {
		this.speed = speed;
	}

	@Override
	public void update(Input input) {
		super.update(input);
		double delta = input.getDeltaTime();
		duration -= delta;
		if (moving) {
			this.position = position.add(speed.mul(delta));
		} 
	}

	@Override
	public void postUpdate(Input input) {
		if (duration < 0.0 && getWorld()!=null) {
			getWorld().unregister(this);
		}
	}

	public String setSprite(String name) {
		skin = name;
		return skin;
	}

	public void setPosition(Vector position) {
		this.position = position;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public Box getBox() { 
		return new Box(position, width, height);
	}

	public Sprite getSprite() {
		return getSprite(skin);
	}

	public void draw(Input input, Output output) {
		if(getWorld()!=null)
			output.drawSprite(getSprite(), getBox(),angle);
	}
}
