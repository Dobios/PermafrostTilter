package platform.game.actor.mob;

import platform.game.actor.Actor;
import platform.game.actor.Damage;
import platform.game.actor.Difficulty;
import platform.game.actor.projectiles.Snowball;
import platform.game.signals.Signal;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;

public class IceBlob extends Mob {

	private MobState state;
	private double stateCd;
	private Signal trigger;
	private double cooldown;

	public IceBlob(Vector position, Signal trigger) {
		super(position, 3.0, 0.6, 0.5);
		this.trigger = trigger;
	}

	public void update(Input input) {
		super.update(input);
		double delta = input.getDeltaTime();
		if (getWorld().getDifficulty() == Difficulty.HARDCORE) {
			stateCd -= delta * 4;
			cooldown -= delta * 4;
		} else {
			stateCd -= delta;
			cooldown -= delta;
		}
		
		if(state != MobState.FRENZY){
			if(trigger.isActive() ){
				state = MobState.AGGRESSIVE;
			}else{
				state = MobState.ROAMING;
			}
		}
		if(getHealth() <=1.0  && stateCd <= 0.0){
			state = MobState.FRENZY;
		}

		switch(state){
		case FRENZY: 
			super.setSize(1.2, 0.9);
			if(cooldown<=0.0){
				getWorld().register(new Snowball(this.getPosition(),new Vector(getVelocity().getX()*2,1.0),this));
				cooldown = Math.random()+0.5;
			}
			super.setSpeed(6.0);
			break;
		case AGGRESSIVE:
			if(cooldown<=0.0){
				getWorld().register(new Snowball(this.getPosition(),new Vector(getVelocity().getX()*3,1.0),this));
				cooldown = Math.random()*2.0+1.0;
			}
			break;
		default:
			break;
		}
	}

	private Sprite getSprite() {
		if(super.getDirection()){
			return getSprite("creature/slime/mob.r");
		}else{
			return getSprite("creature/slime/mob.l");
		}
	}
	public void draw(Input input, Output output){
		if(getWorld()!=null)
			output.drawSprite(getSprite(), getBox());
	}

	@Override
	public boolean hurt(Actor instigator, Damage type, double amount, Vector location) {
		switch (type) {
		case COLD:
			if(instigator.isPlayer()){
				state = MobState.ROAMING;
				super.setSize(0.6, 0.5);
				stateCd = 2.0;
				return true;
			}
			return false;
		default:
			return super.hurt(instigator, type, amount, location);
		}
	}

}
