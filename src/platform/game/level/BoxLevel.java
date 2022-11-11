package platform.game.level;

import platform.game.World;
import platform.game.actor.*;
import platform.game.actor.block.*;
import platform.game.actor.zone.EffectArea;
import platform.game.signals.Constant;
import platform.util.Box;
import platform.util.Vector;

public class BoxLevel extends Level {
	// UNCOMMENT ME WHEN NEEDED
	@Override
	public void register(World world) {
		super.register(world);

		// Register a new instance, to restart level automatically
		world.setNextLevel(new InterLevel(Levels.MENU));

		Player player = new Player(new Vector(0,0),new Vector(0.0,0.0));
		
		world.register(player);
		world.register(new Overlay(player));
		final int SIZE = 15;
		for(int i=-SIZE; i<=SIZE;++i){
			for(int j=-SIZE; j<=SIZE;++j){
				if(i!=0 || j !=0)
					world.register(new Destruct(new Vector(i, j)));
			}
		}
		world.register(new EffectArea(new Box(Vector.ZERO,SIZE*2,SIZE*2), Damage.HEAT, 3, new Constant(true)));
		world.register(new EffectArea(new Box(Vector.ZERO,SIZE*2,SIZE*2), Damage.HEAL, 1, new Constant(true)));
		
		world.register(new Limits(Vector.ZERO, 40, 40));


	}

}
