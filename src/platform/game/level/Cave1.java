package platform.game.level;

import platform.game.World;
import platform.game.actor.*;
import platform.game.actor.interactors.*;
import platform.game.signals.*;
import platform.game.actor.block.Material;
import platform.game.actor.block.Mover;
import platform.game.actor.block.Terrain;
import platform.game.actor.Spike;
import platform.game.actor.Torch;
import platform.util.Box;
import platform.util.Vector;

public class Cave1 extends Level {

	@Override
	public void register(World world) {
		super.register(world);
		world.setNextLevel(new InterLevel(Levels.CAVE2));

		Player player = new Player(new Vector(-24.0,1.0),new Vector(0.0,3.0));
		world.register(player);
		world.register(new Overlay(player));

		world.register(new Chrono());
		world.register(new Terrain(new Box(new Vector (-26.5, -4.0),3 ,32.0),Material.STONE));
		world.register(new Terrain(new Box(new Vector (-23.0, -5.5), 6, 11),Material.STONE));
		world.register(new Terrain(new Box(new Vector (-13.5, 3.0), 9, 14),Material.STONE));
		world.register(new Terrain(new Box(new Vector (-19.0, -9.0), 2, 4),Material.STONE));
		world.register(new Terrain(new Box(new Vector (-13.5, -10.0), 9, 2),Material.STONE));
		world.register(new Terrain(new Box(new Vector (-8.0, -15.0), 8, 2),Material.STONE));
		world.register(new Terrain(new Box(new Vector (-6.5, -19.0), 11, 2),Material.STONE));
		world.register(new Terrain(new Box(new Vector (-17.5, -17.5), 5, 5),Material.STONE));
		world.register(new Terrain(new Box(new Vector (-22.5, -19.0), 5, 2),Material.STONE));
		world.register(new Terrain(new Box(new Vector (20.5, -15.0), 7, 2),Material.STONE));
		world.register(new Terrain(new Box(new Vector (25.5, -9.0), 3, 14),Material.STONE));
		world.register(new Terrain(new Box(new Vector (22.0, -10.5), 2, 1),Material.STONE));
		world.register(new Terrain(new Box(new Vector (20.0, -6.5), 2, 1),Material.STONE));
		world.register(new Terrain(new Box(new Vector (18.0, -2.0), 2, 20),Material.STONE));
		world.register(new Terrain(new Box(new Vector (24.0, -1.5), 6, 1),Material.STONE));
		world.register(new Terrain(new Box(new Vector (-7.0, -5.0), 2, 14),Material.STONE));
		world.register(new Terrain(new Box(new Vector (-1.0, 3.0), 2, 6),Material.STONE));
		world.register(new Terrain(new Box(new Vector (-4.0, 1.0), 8, 2),Material.STONE));
		world.register(new Terrain(new Box(new Vector (3.0, 13.5), 2, 1),Material.STONE));
		world.register(new Terrain(new Box(new Vector (5.5, 15.5), 1, 1),Material.STONE));
		world.register(new Terrain(new Box(new Vector (8, 12.5), 2, 1),Material.STONE));
		world.register(new Terrain(new Box(new Vector (11.5, 11.5), 1, 1),Material.STONE));
		world.register(new Terrain(new Box(new Vector (15.0, 8.5), 2, 1),Material.STONE));
		world.register(new Terrain(new Box(new Vector (17.5, 10.5), 1, 1),Material.STONE));
		world.register(new Terrain(new Box(new Vector (20.0, 11.5), 2, 1),Material.STONE));
		world.register(new Terrain(new Box(new Vector (18.0, 15.5), 2, 1),Material.STONE));
		world.register(new Terrain(new Box(new Vector (15.5, 16.5), 1, 1),Material.STONE));
		
		world.register(new Spike(new Vector (-17.5, -8.75), 9));
		world.register(new Spike(new Vector (-24.5, -17.75), 5));
		
		Lever lev1 = new Lever(new Vector(-18.5, -6.75), Double.POSITIVE_INFINITY);
		Lever lev2 = new Lever(new Vector(-1.5, -17.75), Double.POSITIVE_INFINITY);
		world.register(lev1);
		world.register(lev2);
		
		Key key1 = new Key(new Vector(5.5, 16.5), Keys.RED);
		Key key2 = new Key(new Vector(15.5, 17.5), Keys.BLUE);
		Keys[] keys1 = {Keys.BLUE, Keys.RED};
		world.register(key1);
		world.register(key2);
		
		Torch tor1 = new Torch(new Vector(-17.5, -14.0));
		Torch tor2 = new Torch(new Vector(9.0, -13.0));
		world.register(tor1);
		world.register(tor2);
		
		Activator activ = new Activator(new Box(new Vector (25.0,-0.6), 0.8, 0.8), "box.empty", keys1);
		world.register(activ);
		
		world.register(new Mover(new Vector(-17.0, -7.5), new Vector(-10.0, -7.5), 2, 1,"stone.2", lev1));
		world.register(new Mover(new Vector(0.0, -18.5), new Vector(7.0, -18.5), 2, 1,"stone.2", lev2));
		world.register(new Mover(new Vector(-13.5, -19.0), new Vector(-13.5, -16.0), 1, 1,"stone.1", tor1));
		world.register(new Mover(new Vector(16.0, -14.5), new Vector(11.0, -14.5), 2, 1,"stone.2", tor2));
		world.register(new Mover(new Vector(9.0, -18.5), new Vector(9.0, -14.5), 2, 1,"stone.2"));
		
		world.register(new Jumper(new Vector (23.5, -13.60), 0.8, 0.8));
		world.register(new Jumper(new Vector (22.0, -9.60), 0.8, 0.8));
		world.register(new Jumper(new Vector (19.5, -5.60), 0.8, 0.8));
		world.register(new Jumper(new Vector (-2.5, 2.4), 0.8, 0.8));
		world.register(new Jumper(new Vector (20.0, 12.4), 0.8, 0.8));
		
		world.register(new SnowStorm(new Vector (1.0, 7.5), 2, 12, new Vector(0.0,0.5)));
		world.register(new SnowStorm(new Vector (9.5, 0.0), 16, 6, new Vector(0.0,0.5)));
		
		world.register(new Ladder(new Vector(-8.5, -6.0), 1, 16));
		world.register(new Ladder(new Vector(19.5, 3.0), 1, 10));
		
		world.register(new Exit(new Vector(-24.0, 0.5), false));
		world.register(new Exit(new Vector(26.0, -0.5), new Or(new And(tor1, tor2), activ)));
		world.register(new Background(new Box(new Vector(-0.5, 1.0), 56, 43), "terrain/bg/cave"));
		world.register(new Limits(new Vector(-0.5, 1.0), 56, 43));
	}
}
