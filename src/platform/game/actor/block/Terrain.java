package platform.game.actor.block;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;

public class Terrain extends Block {
	Material type;

	public Terrain(Box box, Material type) {
		super(box);
		this.type = type;
	}

	public void draw(Input input, Output output) {
		Box box = this.getBox();
		Sprite skin;
		double minX = box.getMin().getX();
		double maxY = box.getMax().getY();
		double width = box.getWidth();
		double height = box.getHeight();
		for(int i = 0; i<width; ++i){
			for(int j = 0; j<height; ++j){
				if(j==0){
					skin = getSprite("terrain/"+type.getText()+"/middle");
				}else{
					skin = this.getWorld().getLoader().getSprite("terrain/"+type.getText()+"/center");
				}
				output.drawSprite(skin, new Box(new Vector(minX+i+0.5, maxY-j-0.5), 1, 1));
			}
		}

	}

	public double getFriction() {
		return this.type.getFriction();
	}

}
