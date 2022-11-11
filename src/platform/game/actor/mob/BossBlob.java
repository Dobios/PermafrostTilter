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

public class BossBlob extends Mob {

	private MobState state;
	private double stateCd;
	private Signal trigger;
	private double cooldown;
	private double mobCd;

	public BossBlob(Vector position, Signal trigger) {
		super(position, 15.0, 3.5, 3.0);
		this.trigger = trigger;
	}

	private Sprite getSprite() {
		if(super.getDirection()){
			return getSprite("creature/slime/boss.r");
		}else{
			return getSprite("creature/slime/boss.l");
		}
	}

	public void draw(Input input, Output output){
		if(getWorld()!=null)
			output.drawSprite(getSprite(), getBox());
	}


	public void update(Input input) {
		super.update(input);
		double delta = input.getDeltaTime();
		if (getWorld().getDifficulty() == Difficulty.HARDCORE) {
			stateCd -= delta * 4;
			cooldown -= delta * 4;
			mobCd -= delta * 4;
		} else {
			stateCd -= delta;
			cooldown -= delta;
			mobCd -= delta;
		}

		if(state!=MobState.FRENZY){
			if(trigger.isActive() ){
				state = MobState.AGGRESSIVE;
			}else{
				state = MobState.ROAMING;
			}
		}
		if(getHealth()<=3 && stateCd<=0.0){
			state = MobState.FRENZY;
		}

		switch(state){
		case FRENZY: 
			if(mobCd <=0.0){
				getWorld().register(new IceBlob(this.getPosition(), this.trigger));
				mobCd = 1.2 + Math.random();
			}
			if(cooldown<=0.0){
				getWorld().register(new Snowball(this.getPosition(),new Vector(getVelocity().getX()*3.5,1.0),this));
				cooldown = Math.random()/6.0+0.1;
			}
			super.setSpeed(7.0);
			break;
		case AGGRESSIVE:
			if(mobCd <=0.0){
				getWorld().register(new IceBlob(this.getPosition(), this.trigger));
				mobCd = 1.0 + Math.random() * 2;
			}
			if(cooldown<=0.0){
				getWorld().register(new Snowball(this.getPosition(),new Vector(getVelocity().getX()*3,1.0),this));
				cooldown = Math.random()/4.0+0.2;
			}
			break;
		default:
			break;
		}
	}


	@Override
	public boolean hurt(Actor instigator, Damage type, double amount, Vector location) {
		switch (type) {
		case HEAT:
			if(amount<3){
				return false;
			}
		default:
			return super.hurt(instigator, type, amount, location);
		}
	}

}

