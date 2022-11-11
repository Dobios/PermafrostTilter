package platform.game.actor;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;

public class Background extends Actor {

	private Box box;
	private String skin;


	public Background(Box box, String skin){
		setPriority(-1);
		this.box = box;
		this.skin = skin;

	}

	public void draw(Input input, Output output){
		output.drawSprite(getSprite(skin), getBox(), 0, 0.9);
	}

	public  Box getBox(){
		return box;
	}
}
