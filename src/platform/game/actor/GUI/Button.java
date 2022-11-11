package platform.game.actor.GUI;

import platform.game.level.*;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;

public class Button extends Text {
	private boolean active = false;
	Cursor cursor;
	Level level = null;
	
	public Button(Vector position, double height, String text, Cursor cursor, Level level){
		super(position,height,text);
		this.cursor = cursor;
		this.level = level;
	}
	
	public Button(Vector position, double height, String text, Cursor cursor){
		super(position,height,text);
		this.cursor = cursor;

	}

	public Box getBox() {
		Box pBox = super.getBox();
		return new Box(pBox.getCenter(),pBox.getWidth()+1.0,pBox.getHeight()+0.5);
	}
	
	private Sprite getSprite(){
		if(active)
			return getSprite("/ui/buttonSelected");
		else
			return getSprite("/ui/buttonDefault");
	}
	
	public void update(Input input){
		Vector position = getBox().getCenter();
		double width = getBox().getWidth();
		double height = getBox().getHeight();
		double deltaX = Math.abs(input.getMouseLocation().getX()-position.getX());
		double deltaY = Math.abs(input.getMouseLocation().getY()-position.getY());
		if(deltaX<=width/2.0 && deltaY<=height/2.0){
			active=true;	
		}else{
			active = false;
		}
	}

	@Override
	public void draw(Input input, Output output){
		output.drawSprite(getSprite(), getBox());
		super.draw(input, output);
	}
	
	public boolean getActive() {
		return this.active;
	}

}
