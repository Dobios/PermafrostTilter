package platform.game.level;

import platform.game.World;
import platform.game.actor.block.Material;
import platform.game.actor.block.Terrain;
import platform.util.Box;
import platform.util.Vector;
import platform.game.actor.*;
import platform.game.actor.block.*;
import platform.game.actor.interactors.*;

public class HappyEnd extends Level{
	
	@Override
	public void register(World world) {
		super.register(world);
		world.setNextLevel(new InterLevel(Levels.END));
		world.setView(Vector.ZERO, 6);
		
		world.register(new Player(new Vector(-4,-1),true));
		world.register(new Exit(new Vector(-4,-1.5), false));
		
		Worm worm = new Worm(new Vector(6,-1.6));
		world.register(worm);
		
		world.register(new Cloud(new Vector(0,6), 12, 1, 0.2));
		world.register(new Portal(new Vector(4.5,-1.5),worm));
		world.register(new Tree(new Vector(5.5,-1)));
		world.register(new Tree(new Vector(-3,-1)));
		world.register(new Terrain(new Box(new Vector(0,-4),12,4), Material.GRASS));
		world.register(new Limits(Vector.ZERO, 12, 12));
		
		world.register(new Block(new Box(new Vector(-6,0), 0.5, 12), false));
		world.register(new Block(new Box(new Vector(6,0), 0.5, 12), false));
		world.register(new Background(new Box(Vector.ZERO,12,12), "terrain/bg/end"));
		
		
	}
	
	
}
