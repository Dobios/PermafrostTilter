package platform.game.actor;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;
import platform.game.actor.particle.PoufEffect;
import platform.game.actor.zone.EffectArea;
import platform.game.level.InterLevel;
import platform.game.level.Levels;
import platform.game.signals.*;

public class Worm extends Actor implements Signal{
	private Vector position;
	private double width = 0.8;
	private double height = 0.8;
	private double variation;
	private double cooldown = 1.0;
	private boolean dead = false;
	private boolean freeze = false;
	private boolean stuck = false;
	private Actor other = null;

	public Worm(Vector position) {
		this.setPriority(44);
		this.position = position;
	}

	public String getSpriteString() {
		if(freeze){
			return "creature/worm/freeze";
		}else if(dead){
			return "creature/worm/dead";
		}else{
			if (variation < 0.2) {
				return "creature/worm/1";
			}else{
				return "creature/worm/2";
			}
		}
	}


	@Override
	public void update(Input input) {
		super.update(input);
		double delta = input.getDeltaTime();
		cooldown -= delta;
		if(dead && cooldown<=0.0){
			getWorld().register(new Cloud(new Vector(0,6), 12, 1, Math.random()/2.0));
			getWorld().register(new Cloud(new Vector(0,6), 12, 1, Math.random()/2.0));
			getWorld().register(new Cloud(new Vector(0,6), 12, 1, Math.random()));
			getWorld().register(new Cloud(new Vector(0,6), 12, 1, Math.random()));
			getWorld().register(new EffectArea(new Box(Vector.ZERO,12,13), Damage.COLD, 1,new Constant(true)));
			cooldown = 1.0;
			
		}
		if(!stuck && !dead){
			variation -= delta;
			if (variation < 0.0) {
				variation = 0.4;
			}
			position = position.add(new Vector(-2,0).mul(delta));
		}
		if(stuck && other!=null){
			position = other.getPosition().add(new Vector(0,0.75));
		}
		
		if (dead){
			getWorld().setNextLevel(new InterLevel(Levels.BADEND));
		}
	}

	@Override
	public void interact(Actor other) {
		super.interact(other);
		Vector delta = other.getBox().getCollision(getBox()); 
		if(delta!=null && other.isPlayer() && !stuck){
			stuck = true;
			getWorld().register(new PoufEffect(this.getBox(), 0.6));
			this.other = other;
		}
	}

	public boolean hurt(Actor instigator, Damage type, double amount, Vector location) {
		switch (type) {
		case VOID:
			dead = true;
		case COLD:
			freeze = true;
			dead = true;
			return true;
		case FIRE:
			dead = true;
			return true;
		default:
			return super.hurt(instigator, type, amount, location);
		}
	}

	public void draw(Input input, Output output) {
		if(getWorld()!=null)
			output.drawSprite(getSprite(getSpriteString()), getBox());
	}
	
	public boolean isDead() {
		return dead;
	}

	public Box getBox() { 
		return new Box(position, width, height);
	}

	public boolean isSolid() { 
		return false;
	}

	public Vector getPosition() { 
		return position;
	}

	public boolean isActive() {
		return stuck;
	}

}

