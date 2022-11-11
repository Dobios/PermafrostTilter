package platform.game.actor.zone;

import platform.game.actor.Damage;
import platform.game.actor.Torch;
import platform.game.signals.Signal;
import platform.util.Box;
import platform.util.Input;

public class EffectArea extends Zone {
	private Damage type;
	private double amount;
	private Signal signal;
	private double cooldown = 1.0;
	
	
	public EffectArea(Box box, Damage type, double amount, Signal signal) {
		super(box);
		this.type = type;
		this.amount = amount;
		this.signal = signal;
	}
	
	public EffectArea(Torch torch) {
		super(new Box(torch.getPosition(),3,3));
		this.type = Damage.HEAT;
		this.amount = 0.5;
		this.signal = torch;
	}
	
	public void update(Input input){
		cooldown-=input.getDeltaTime();
		if(signal.isActive() && cooldown <=0.0){
			getWorld().hurt(getBox(), this, type, amount, getPosition());
			cooldown = 0.5;
		}
	}

}
