package platform.game.level;

import platform.game.World;
import platform.game.actor.*;
import platform.game.actor.block.*;
import platform.game.actor.interactors.Exit;
import platform.util.Box;
import platform.util.Vector;

public class TestLevel extends Level {
	@Override
	public void register(World world) {
		super.register(world);

		// Register a new instance, to restart level automatically
		world.setNextLevel(new InterLevel(Levels.MENU));
		
		world.register(new Exit(new Vector(15,-4),true));
		world.register(new Limits(Vector.ZERO, 100, 100));
		world.register(new Heart(Vector.ZERO));
		world.register(new Spike(new Vector(0, -4.25), 6));
		Player player = new Player(new Vector(1.0,1.0),new Vector(0.0,10.0));
		world.register(player);
		world.register(new Overlay(player));
		//world.register(new Block(new Vector(-1, -4),1 ,1,skinBlock));
		//world.register(new Block(new Vector(3, -4),1 ,1,skinBlock));
		world.register(new Torch(new  Vector(-2,-3), false));
		world.register(new Jumper(new Vector(10, -4), 1, 1));
		world.register(new Jumper(new Vector(-6, -4), 1, 1));
		world.register(new Terrain(new Box(new Vector(0.0, 1.0),8 ,1),Material.ICE));
		world.register(new Terrain(new Box(new Vector (0, -5.5), 25, 2),Material.GRASS));
		world.register(new Terrain(new Box(new Vector(-8.5, -0.5),2 ,10),Material.STONE));
		world.register(new Ladder(new Vector(8, -0.5),1 ,10)); 
		world.register(new Block(new Vector(15, -5), 1, 1, "box.empty"));
		world.register(new Block(new Vector(-7, -2), 1, 1, "box.empty"));
		//world.register(new Background(new Box(Vector.ZERO,100 ,100),Material.CASTLE));

	}

}

