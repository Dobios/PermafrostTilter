package platform.game.actor;

import platform.util.Box;
import platform.util.Input;
import platform.util.Sprite;
import platform.util.Vector;
import platform.util.Output;

public class StuckItem extends Actor {
	private Actor actor;
	private Vector position;
	private String skin;
	private double cooldown;
	private double height;
	private double width;
	private Vector offset;

	public StuckItem(Vector delta, Actor other, double width, double height, String skin) {
		this.setPriority(55);
		this.actor = other;
		this.cooldown = 5;
		this.skin = skin;
		offset = delta;
		this.position = other.getPosition().add(delta);
		this.height = height;
		this.width = width;
	}
	
	@Override
	public Box getBox() {
		return new Box(position, height, width);
	}

	public void update(Input input){
		double delta = input.getDeltaTime();
		cooldown-=delta;
		position = actor.getPosition().add(offset);
	}
	
	public void postUpdate(Input input) {
		if (actor.getWorld() == null || actor == null || cooldown <= 0.0) {
			getWorld().unregister(this);
		}
	}
	
	public void draw(Input input, Output output) {
		if(getWorld()!=null)
			output.drawSprite(getSprite(), getBox());
	}
	
	public Sprite getSprite() {
		if (cooldown >= 0.35) {
			return getSprite(skin);
		} else if (cooldown >= 0.25) {
			return getSprite("particules/smoke/gray.1");
		} else if (cooldown >= 0.1) {
			return getSprite("particules/smoke/gray.2");
		} else{
			return getSprite("particules/smoke/gray.3");
		}
	}
}