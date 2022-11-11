package platform.game.level;

import platform.game.World;
import platform.game.actor.*;
import platform.game.actor.block.*;
import platform.game.actor.interactors.*;
import platform.game.actor.mob.BossBlob;
import platform.game.actor.mob.Mob;
import platform.game.actor.zone.EffectArea;
import platform.game.actor.zone.Trigger;
import platform.game.signals.And;
import platform.game.signals.Constant;
import platform.util.Box;
import platform.util.Vector;

public class CaveEnd extends Level {

	@Override
	public void register(World world) {
		super.register(world);
		world.setNextLevel(new InterLevel(Levels.HAPPYEND));

		Player player = new Player(new Vector(-24.5, 2.5),new Vector(0.0,3.0));
		world.register(player);
		world.register(new Overlay(player));
		world.register(new Chrono());
		world.register(new Terrain(new Box(new Vector (-26.0, 1.0),6 ,2.0),Material.SNOWS));
		world.register(new Terrain(new Box(new Vector (-21.0, 13.5),2 ,1.0),Material.SNOWS));
		world.register(new Terrain(new Box(new Vector (-1.0, 19.5),4 ,1.0),Material.SNOWS));
		world.register(new Terrain(new Box(new Vector (-2.0, 14.5),2 ,1.0),Material.SNOWS));
		world.register(new Terrain(new Box(new Vector (2.0, 13.5),2 ,1.0),Material.SNOWS));
		world.register(new Terrain(new Box(new Vector (5.0, 11.5),2 ,1.0),Material.SNOWS));
		world.register(new Terrain(new Box(new Vector (16.0, 11.5),4 ,1.0),Material.SNOWS));
		world.register(new Terrain(new Box(new Vector (16.0, 6.0),8 ,2.0),Material.SNOWS));
		world.register(new Terrain(new Box(new Vector (22.0, -4.0),2.0 ,6.0),Material.ICE));
		world.register(new Terrain(new Box(new Vector (-1.5, -2.0),43 ,2.0),Material.ICE));
		world.register(new Terrain(new Box(new Vector (-22.0, -8.0),2 ,14.0),Material.ICE));
		world.register(new Terrain(new Box(new Vector (-15.0, -14.0),12 ,2.0),Material.ICE));
		world.register(new Terrain(new Box(new Vector (-8.0, -12.0),2 ,6.0),Material.ICE));
		world.register(new Terrain(new Box(new Vector (3.0, -10.0),24 ,2.0),Material.ICE));
		world.register(new Terrain(new Box(new Vector (16.0, -8.0),2 ,6.0),Material.ICE));
		world.register(new Terrain(new Box(new Vector (18.0, -6.0),6 ,2.0),Material.ICE));

		world.register(new Ladder(new Vector(-21.5, 18.0),1, 8));
		world.register(new Ladder(new Vector(-11.0, 21.5),20, 1));
		world.register(new Ladder(new Vector(3.5,10.5),1, 3));
		world.register(new Ladder(new Vector(5.0,9.5),2, 1));
		world.register(new Ladder(new Vector(6.5,10.5),1, 3));
		world.register(new Ladder(new Vector(20.5,1.0),1, 12));
		world.register(new Ladder(new Vector(-9.5,-11.0),1, 4));

		for(int i = 0; i < 6; ++i)
			world.register(new Destruct(new Vector(-14.5 + i, 10.5)));
		for(int i = 0; i < 5; ++i)
			world.register(new Destruct(new Vector(-20.5 + i, 17.5)));

		//for(int i = 0; i < 3; ++i)
			//world.register(new Trap(new Vector(-6.5, 1.5 + i), new Constant(true), "stone.1"));
		world.register(new Spike(new Vector(-22.5, -0.75), 42));

		world.register(new SnowStorm(new Vector(-16.0, 5.0), 2, 10, new Vector(0.0, 1.0)));
		world.register(new SnowStorm(new Vector(-15.0, 0.5), 16, 4, new Vector(0.0, 1.0)));
		world.register(new SnowStorm(new Vector(-12.5, 16.0), 6, 3, new Vector(0.0, 1.0)));
		world.register(new SnowStorm(new Vector(5.0, 6.0), 2, 4, new Vector(0.0, 1.0)));
		world.register(new SnowStorm(new Vector(9.0, 6.0), 6, 4, new Vector(0.5, 1.0)));

		world.register(new Jumper(new Vector(13.0, 7.4), 0.8, 0.8));
		world.register(new Jumper(new Vector(19.0, 7.4), 0.8, 0.8));

		Torch tor1 = new Torch(new Vector(-11.5, 12.5));
		Torch tor2 = new Torch(new Vector(4.5, 18.5));
		Torch tor3 = new Torch(new Vector(16.0, 13.5));
		Torch tor4 = new Torch(new Vector(18.5, -3.5));
		Torch tor5 = new Torch(new Vector(11.5, -7.5));
		Torch tor6 = new Torch(new Vector(5.0, -7.5));
		Torch tor7 = new Torch(new Vector(-1.0, -7.5));
		world.register(new EffectArea(tor1));
		world.register(new EffectArea(tor2));
		world.register(new EffectArea(tor3));
		world.register(new EffectArea(tor4));
		world.register(new EffectArea(tor5));
		world.register(new EffectArea(tor6));
		world.register(new EffectArea(tor7));
		And tors = new And(tor5, new And(tor6, tor7));
		world.register(tor1);
		world.register(tor2);
		world.register(tor3);
		world.register(tor4);
		world.register(tor5);
		world.register(tor6);
		world.register(tor7);

		world.register(new Heart(new Vector(-19.5, 15.0)));
		world.register(new Heart(new Vector(19.0, 20.0)));
		world.register(new Heart(new Vector(8.5, -8.0)));
		world.register(new Heart(new Vector(2.0, -8.0)));

		Key key1 = new Key(new Vector(5.0, 10.5), Keys.RED);
		Key key2 = new Key(new Vector(13.0, 20.0), Keys.BLUE);
		Key rune = new Key(new Vector(-16.0, -11.0),4, Keys.GBLUE);
		Keys[] keys = {Keys.RED, Keys.BLUE};
		world.register(key1);
		world.register(key2);
		world.register(rune);

		Trigger trig1 = new Trigger(new Box(new Vector(3.0,-6.0),24,6));
		world.register(trig1);

		Mob boss = new BossBlob(new Vector(-6.0,-8.5), trig1);
		world.register(boss);

		Lever lev1 = new Lever(new Vector(-19.5, 18.25), Double.POSITIVE_INFINITY);
		world.register(lev1);

		Activator activ = new Activator(new Box(new Vector(16.5, -4.5), 0.8, 0.8), "box.empty", keys);
		world.register(activ);

		world.register(new Mover(new Vector(-7.5, 10.5), new Vector(-7.5, 17.5), 3, 1, "stone.3", tor1));
		world.register(new Mover(new Vector(2.0, 19.5), new Vector(2.0, 15.5), 2, 1, "stone.2", tor2));
		world.register(new Mover(new Vector(13.0, 11.5), new Vector(13.0, 18.5), 2, 1, "stone.2", tor3));
		world.register(new Mover(new Vector(19.0, 11.5), new Vector(19.0, 18.5), 2, 1, "stone.2", tor3));

		for(int i = 0; i < 2; ++i) 
			world.register(new Door(new Vector(15.5, -4.5 + i), activ));
		for(int i = 0; i < 3; ++i) 
			world.register(new Door(new Vector(-20.5, 18.5 + i), lev1));
		for(int i = 0; i < 5; ++i)
			world.register(new Door(new Vector(-3.5, -8.5 + i), tors));
		for (int i = 0; i < 4; ++i)
			world.register(new Door(new Vector(-9.5, -8.5 + i), boss));

		world.register(new Exit(new Vector(-24.5, 2.5), false));
		world.register(new Exit(new Vector(-20.0, -12.5), rune));

		world.register(new Cloud(new Vector(-15.0,-3.5), 10, 1, 0.6));
		world.register(new EffectArea(new Box(new Vector(-15.0, -8.0),10,10), Damage.COLD, 0.05, new Constant(true)));

		world.register(new Background(new Box(new Vector(-3.0, 4.5), 52, 39), "terrain/bg/cave"));
		world.register(new Limits(new Vector(-3.0, 4.5), 52, 39));
	}
}
