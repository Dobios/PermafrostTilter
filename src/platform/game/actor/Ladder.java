package platform.game.actor;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

public class Ladder extends Actor{

	private Vector position;
	private double height;
	private double width;
	
	public Ladder(Vector position, double width, double height) {
		this.setPriority(0);
		this.position = position;
		this.width = width;
		this.height = height;
	}
	public Ladder(Box box) {
		this.setPriority(0);
		this.position = box.getCenter();
		this.height = box.getHeight();
		this.width = box.getWidth();
	}
	
	public void draw(Input input, Output output) {
		Box box = getBox();
		for(int i =0; i<width;++i){
			for(int j = 0; j<2*height; ++j){
				output.drawSprite( getSprite("ladder"), new Box(new Vector(box.getMin().getX()+i+0.5,box.getMax().getY()-(j+0.5)/2.0), 1, 0.5));
			}
		}
		
	}
	
	public Box getBox() { 
		return new Box(position,width, height);
	}
	
	public Vector getPosition() { 
		return position;
	}
	
	@Override
	public boolean isClimbable(){
		return true;
	}

}
