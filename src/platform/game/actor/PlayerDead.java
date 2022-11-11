package platform.game.actor;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

public class PlayerDead extends Actor{
	private Vector position;
	private Vector spiritPosition;
	private double fat;
	private double cooldown = 2;

	public PlayerDead(Vector position, double fat){
		this.setPriority(42);
		this.position = position;
		this.spiritPosition = position;
		this.fat = fat;
	}
	
	public void update(Input input){
		double delta = input.getDeltaTime();
		cooldown-=delta;
		
		spiritPosition = spiritPosition.add(new Vector(2.0*Math.cos(cooldown*8.0),4.0).mul(delta));
		if(cooldown<=0.0)
			getWorld().nextLevel();
	}

	public void draw(Input input, Output output){
		output.drawSprite(getSprite("creature/wing_left"), getSBox().add(new Vector(-0.5,0.2)),0,0.8);
		output.drawSprite(getSprite("creature/wing_right"), getSBox().add(new Vector(0.5,0.2)),0,0.8);
		output.drawSprite(getSprite("creature/player/dead"), getSBox(),0,0.7);
		output.drawSprite(getSprite("creature/player/dead"), getBox());
	}
	
	@Override
	public Box getBox() { 
		return new Box(position, fat, fat);
	}
	
	public Box getSBox() { 
		return new Box(spiritPosition, fat, fat);
	}
	
	@Override
	public boolean hurt(Actor instigator, Damage type, double amount, Vector location) {
		return false;
		
	}
}
