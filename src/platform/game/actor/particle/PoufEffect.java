package platform.game.actor.particle;

import platform.game.actor.Actor;
import platform.util.Box;
import platform.util.Input;
import platform.util.Sprite;
import platform.util.Output;

public class PoufEffect extends Actor{

	private Box box;
	private double cooldown;

	public PoufEffect(Box box, double cooldown) {
		this.setPriority(99);
		this.cooldown = cooldown;
		this.box = box;
	}

	@Override
	public Box getBox() {
		return box;
	}

	public void update(Input input){
		double delta = input.getDeltaTime();
		cooldown-=delta;
	}

	public void postUpdate(Input input) {
		if(cooldown<=0.0){
			getWorld().unregister(this);
		}
	}

	public void draw(Input input, Output output) {
		if(getWorld()!=null)
			output.drawSprite(getSprite(), getBox());
	}

	public Sprite getSprite() {
		if (cooldown >= 0.25) {
			return getSprite("particules/smoke/gray.1");
		} else if (cooldown >= 0.1) {
			return getSprite("particules/smoke/gray.2");
		} else{
			return getSprite("particules/smoke/gray.3");
		}
	}

}
