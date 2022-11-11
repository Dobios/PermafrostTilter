package platform.game.actor.interactors;

import platform.util.Sprite;
import platform.util.Vector;
import platform.util.Output;
import java.util.ArrayList;
import platform.game.actor.*;
import platform.game.signals.Signal;
import platform.util.Box;
import platform.util.Input;

public class Activator extends Actor implements Signal {
	ArrayList<Keys> requiredKeys = new ArrayList<Keys>();
	ArrayList<Keys> activeKeys = new ArrayList<Keys>();
	Box box;
	String skin;
	int display;
	double displayTime = 1.0;

	public Activator(Box box, String skin, Keys[] keys){
		setPriority(40);
		this.box = box;
		this.skin = skin;
		this.display = 0;
		for(Keys key : keys)
			requiredKeys.add(key);
	}

	@Override
	public boolean isActive() {
		return(requiredKeys.size()==0);
	}

	public Box getBox(){
		return box;
	}

	public void update(Input input){
		if(displayTime>=0.0){
			displayTime-=input.getDeltaTime();
		}else{
			displayTime = 1.0;
			if(display<requiredKeys.size()-1){
				++display;
			}else{
				display=0;
			}
		}
	}

	public void draw(Input input, Output output){
		//if(!isActive()&&getSprite()==null){
		output.drawSprite(getSprite(), box,0,0.9);
		if(!isActive()){
			Keys current = requiredKeys.get(display);
			output.drawSprite(getSprite("particules/bubble"), new Box(box.getCenter(),0.5,0.5));
			output.drawSprite(getSprite(current.getText()), new Box(box.getCenter(),0.5,0.5));
		}
	}

	public Sprite getSprite(){
		return getSprite(skin);
	}

	public boolean isInteract(){
		if(this.isActive())
			return false;
		return true;
	}

	@Override
	public boolean hurt(Actor instigator, Damage type, double amount, Vector location) {
		switch (type) {
		case ACTIVATION:
			if ( instigator.isPlayer()) {
				instigator.interact(this);
				for(Keys key : requiredKeys){
					display=0;
					if(((Player) instigator).hasKey(key)){
						activeKeys.add(key);
						((Player) instigator).removeKey(key);
					}

				}for(Keys key : activeKeys){
					if(requiredKeys.indexOf(key)>=0)
						requiredKeys.remove(key);
				}
				return true;
			}else{
				return false;
			}
		default:
			return false;
		}
	}
}
