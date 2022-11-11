package platform.game.actor.GUI;

import java.awt.event.KeyEvent;

import platform.game.actor.Actor;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;

public class Cursor extends Actor{
	private boolean click;
	private Vector position;
	private double size;
	
	public Cursor(){
		setPriority(100001);
		click = false;
		size = 1;
	}
	
	private Sprite getSprite(){
		if(click)
			return getSprite("ui/tapTick");
		else
			return getSprite("ui/tap");
	}
	
	public Box getBox(){
		return new Box(position,size,size);
	}
	
	public void update(Input input){
		position = input.getMouseLocation();
		if(input.getMouseButton(1).isDown()){
			click=true;
		}else{
			click=false;
		}
		if(input.getKeyboardButton(KeyEvent.VK_ESCAPE).isPressed()){
			getWorld().setView(Vector.ZERO, 11);
		}
	}
	
	@Override
	public void draw(Input input, Output output){
		output.drawSprite(getSprite(), getBox());
	}

}
