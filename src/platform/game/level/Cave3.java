package platform.game.level;

import platform.game.World;
import platform.game.actor.*;
import platform.game.actor.block.*;
import platform.game.actor.interactors.*;
import platform.game.actor.mob.IceBlob;
import platform.game.actor.zone.EffectArea;
import platform.game.signals.And;
import platform.game.signals.Constant;
import platform.util.Box;
import platform.util.Vector;

public class Cave3 extends Level {

	@Override
	public void register(World world) {
		super.register(world);
		world.setNextLevel(new InterLevel(Levels.CAVEEND));

		Player player = new Player(new Vector(0.5, 0.5),new Vector(0.0,3.0));
		world.register(player);
		world.register(new Overlay(player));

		world.register(new Chrono());
		world.register(new Terrain(new Box(new Vector (1.0, -1.0),24 ,2.0),Material.STONE));
		world.register(new Terrain(new Box(new Vector (12.5, 11.0),1 ,24.0),Material.STONE));
		world.register(new Terrain(new Box(new Vector (6.5, 20.5),17 ,1),Material.STONE));
		world.register(new Terrain(new Box(new Vector (-7.5, 23.0),1 ,6),Material.STONE));
		world.register(new Terrain(new Box(new Vector (6.0, 25.0),28 ,2),Material.STONE));
		world.register(new Terrain(new Box(new Vector (2.0, 25.5),38 ,1),Material.SNOW));
		world.register(new Terrain(new Box(new Vector (10.0, 26.0),20 ,2),Material.SNOW));
		world.register(new Terrain(new Box(new Vector (11.5, 27.0),5 ,2),Material.SNOW));
		world.register(new Terrain(new Box(new Vector (15.5, 28.0),3 ,4),Material.SNOW));
		world.register(new Terrain(new Box(new Vector (-16.5, 26.5),1 ,3),Material.SNOW));
		world.register(new Terrain(new Box(new Vector (10.5, 7.5),1 ,1),Material.STONE));
		world.register(new Terrain(new Box(new Vector (3.5, 7.0),1 ,6),Material.STONE));
		world.register(new Terrain(new Box(new Vector (-2.5, 6.0),1 ,4),Material.STONE));
		world.register(new Terrain(new Box(new Vector (2.0, 4.5),10 ,1),Material.STONE));
		world.register(new Terrain(new Box(new Vector (0.5, 9.5),7 ,1),Material.STONE));
		world.register(new Terrain(new Box(new Vector (-5.0, 7.5),6 ,1),Material.STONE));
		world.register(new Terrain(new Box(new Vector (-7.5, 10.0),1 ,6),Material.STONE));
		world.register(new Terrain(new Box(new Vector (-9.0, 12.5),2 ,1),Material.STONE));
		world.register(new Terrain(new Box(new Vector (-15.0, 9.5),4 ,1),Material.STONE));
		world.register(new Terrain(new Box(new Vector (-19.0, 7.5),2 ,1),Material.STONE));
		world.register(new Terrain(new Box(new Vector (-16.5, -1.0),5 ,2),Material.STONE));
		world.register(new Terrain(new Box(new Vector (-18.5, 1.0),1 ,4),Material.STONE));
		world.register(new Terrain(new Box(new Vector (-14.5, 2.0),1 ,2),Material.STONE));
		world.register(new Terrain(new Box(new Vector (-17.5, 3.5),1 ,1),Material.STONE));
		world.register(new Terrain(new Box(new Vector (-15.5, 3.5),1 ,1),Material.STONE));
		world.register(new Terrain(new Box(new Vector (-16.5, 4.5),1 ,1),Material.STONE));
		world.register(new Terrain(new Box(new Vector (2.0, -12.5),6 ,1),Material.STONE));
		world.register(new Terrain(new Box(new Vector (7.0, 24.0),2 ,2),Material.STONE));

		world.register(new Tree (new Vector(4.5, 28.0)));
		world.register(new Heart(new Vector(-7.5, 6.5)));
		world.register(new Spike(new Vector(5.5, 21.25), 1));
		world.register(new Trap(new Vector(7.5, 22.5), new Constant(true), "stone.1"));
		world.register(new IceBlob(new Vector(9.5, 28.5), new Constant(false)));
		
		world.register(new Ladder(new Vector(11.5, 4.0), 1, 8));
		world.register(new Ladder(new Vector(4.5, 7.5), 1, 5));

		for (int i = 0; i < 5; ++i) {
			world.register(new Destruct(new Vector(-6.5 + i, 20.5)));
			world.register(new Destruct(new Vector(-13.5, 10.5 + i)));
		}

		world.register(new Jumper(new Vector(-6.5, 8.4), 0.8, 0.8));
		world.register(new Jumper(new Vector(2.5, 21.4), 0.8, 0.8));
		world.register(new Jumper(new Vector(-1.5, 5.4), 0.8, 0.8));

		world.register(new SnowStorm(new Vector(-7.5, -10.0), 3, 6, new Vector(0.0, 0.8)));
		world.register(new SnowStorm(new Vector(-12.5, -7.0), 3, 6, new Vector(0.0, 0.8)));
		world.register(new SnowStorm(new Vector(-2.5, -12.5), 3, 3, new Vector(0.0, 1.0)));
		world.register(new SnowStorm(new Vector(16.5, 9.5), 1, 25, new Vector(0.0, 0.5)));
		world.register(new SnowStorm(new Vector(-4.5, 16.5), 5, 1, new Vector(0.0, 0.5)));
		//world.register(new SnowStorm(new Vector(1.5, 23.5), 5, 1, new Vector(0.5, 0.0)));
		
		Torch tor1 = new Torch(new Vector(4.5, 1.5));
		Torch tor2 = new Torch(new Vector(-16.5, 1.5));
		Torch tor3 = new Torch(new Vector(1.5, 6.5));
		Torch tor4 = new Torch(new Vector(-6.5, 22.5));
		Torch tor5 = new Torch(new Vector(3.5, -10.5));
		Torch tor6 = new Torch(new Vector(-16.5, 11.5));
		And allTor = new And (tor1,new And(tor2,new And(tor3, new And(tor4, new And(tor5, tor6)))));
		world.register(new EffectArea(tor1));
		world.register(new EffectArea(tor2));
		world.register(new EffectArea(tor3));
		world.register(new EffectArea(tor4));
		world.register(new EffectArea(tor5));
		world.register(new EffectArea(tor6));
		world.register(tor1);
		world.register(tor2);
		world.register(tor3);
		world.register(tor4);
		world.register(tor5);
		world.register(tor6);
		
		Lever lev1 = new Lever(new Vector(-17.5, 0.25), Double.POSITIVE_INFINITY);
		Lever lev2 = new Lever(new Vector(3.5, -11.75), Double.POSITIVE_INFINITY);
		world.register(lev1);
		world.register(lev2);
		
		Key key1 = new Key(new Vector(-4.5, 21.5), Keys.YELLOW);
		Key key2 = new Key(new Vector(-15.5, 10.5), Keys.RED);
		Key key3 = new Key(new Vector(1.5, -11.5), Keys.BLUE);
		Keys[] keys1 = {Keys.YELLOW};
		Keys[] keys2 = {Keys.RED};
		Keys[] keys3 = {Keys.BLUE};
		world.register(key1);
		world.register(key2);
		world.register(key3);
		
		Activator activ1 = new Activator(new Box(new Vector(-3.5, 8.4), 0.8, 0.8), "box.empty", keys1);
		Activator activ2 = new Activator(new Box(new Vector(-10.5, 0.4), 0.8, 0.8), "box.empty", keys2);
		Activator activ3 = new Activator(new Box(new Vector(8.5, 21.4), 0.8, 0.8), "box.empty", keys3);
		world.register(activ1);
		world.register(activ2);
		world.register(activ3);
		
		world.register(new Mover(new Vector(-7.5, 4.5), new Vector(-12.5, -0.5), 3, 1, "stone.2", lev1));
		world.register(new Mover(new Vector(6.5, -12.5), new Vector(16.5, -3.5), 3, 1, "stone.2", lev2));
		
		world.register(new Door(new Vector(-2.5, 8.5), activ1));
		world.register(new Door(new Vector(-14.5, 0.5), activ2));
		world.register(new Door(new Vector(7.5, 21.5), activ3));
		
		world.register(new Exit(new Vector(0.5, 0.5), false));
		world.register(new Exit(new Vector(0.5, 5.5), allTor));
		
		world.register(new Background(new Box(new Vector(0.0, 6.0), 40, 40), "terrain/bg/cave"));
		world.register(new Background(new Box(new Vector(0.0, 28.0), 40, 4), "terrain/bg/sky"));
		world.register(new Limits(new Vector(0.0, 8.0), 40, 44));
	}
}
