package platform.game.actor;

import platform.game.actor.interactors.Keys;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;

public class Overlay extends Actor{
	Player player;
	private final static double SIZE = 1.0/3.0;
	private final static double MAXHEARTS = 5;

	public Overlay(Player player){
		this.setPriority(1000);
		this.player = player;
	}

	public Box getBox() { 
		return player.getBox();
	}

	public void postUpdate(Input input){
		if(player == null || player.getWorld() == null){
			getWorld().unregister(this);
		}
	}

	public void draw(Input input, Output output){
		if(this.getWorld()!=null){
			Sprite skin;
			double trans;
			double maxHealth = player.getMaxHealth();
			double health = player.getHealth();
			double hearts = maxHealth;
			if(maxHealth > MAXHEARTS) {
				health = MAXHEARTS * player.getHealth() / maxHealth;
				hearts = MAXHEARTS;
			}
			Vector playerPos = player.getPosition();
			trans = ((hearts+1) / 6.0) ;
			for (int i = 1; i <= hearts; ++i) { 
				String name;
				if (health >= i)
					name = "ui/health/heart.full";
				else if (health >= i - 0.5) 
					name = "ui/health/heart.half";
				else
					name = "ui/health/heart.empty";
				skin = this.getSprite(name);
				output.drawSprite(skin, new Box(new Vector(
						playerPos.getX()+(i*SIZE)-trans,
						playerPos.getY()+2*SIZE), 
						SIZE, SIZE));
			}

			Keys keys[] = player.getKeys();
			int i = 0;
			trans = ((keys.length-1)/2.0) ;
			for(Keys key : keys){
				skin =this.getSprite(key.getText());
				output.drawSprite(skin, 
						new Box(new Vector(
								playerPos.getX()+(i*SIZE)-trans,
								playerPos.getY()+4*SIZE), 
								SIZE, SIZE));
				++i;
			}
			double vigor = player.getVigor();
			double MAXVIGOR = player.getMaxVigor();
			vigor = vigor<0.0?0.0:vigor;
			skin = getSprite("ui/vigour/blue");
			output.drawSprite(skin,
					new Box(new Vector(
							playerPos.getX(),
							playerPos.getY()+3*SIZE), 
							5*SIZE, SIZE/2.0));
			skin = getSprite("ui/vigour/orange");
			output.drawSprite(skin,
					new Box(new Vector(
							playerPos.getX()+(vigor/MAXVIGOR)-1,
							playerPos.getY()+3*SIZE), 
							5*SIZE*(vigor/MAXVIGOR), SIZE/2.0));
			if(player.getInteractor()!=null){
				skin = getSprite("ui/txt/E");
				output.drawSprite(skin, new Box(player.getInteractor().getBox().getCenter().add(new Vector(0,1)),0.5,0.5));
			}
		}
	}
}
