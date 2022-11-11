package platform.game.level;

import platform.game.World;
import platform.game.actor.*;
import platform.game.actor.interactors.*;
import platform.game.actor.mob.IceBlob;
import platform.game.actor.mob.Mob;
import platform.game.actor.zone.EffectArea;
import platform.game.actor.zone.Trigger;
import platform.game.actor.block.*;
import platform.util.Box;
import platform.util.Vector;

public class Cave2 extends Level{
	
	@Override
	public void register(World world) {
		super.register(world);
		world.setNextLevel(new InterLevel(Levels.CAVE3));

		Player player = new Player(new Vector(-18.5,1.0),new Vector(0.0,3.0));
		world.register(player);
		world.register(new Overlay(player));

		world.register(new Chrono());
		world.register(new Terrain(new Box(new Vector (-24.0, 4.0),2 ,12),Material.STONE));
		world.register(new Terrain(new Box(new Vector (-20.5, -4.5),9 ,9),Material.STONE));
		world.register(new Terrain(new Box(new Vector (-13.0, -7.5),6 ,3),Material.STONE));
		world.register(new Terrain(new Box(new Vector (10.0, 5.0),2 ,28.0),Material.STONE));
		world.register(new Terrain(new Box(new Vector (6.0, -7.5),8 ,3.0),Material.STONE));
		world.register(new Terrain(new Box(new Vector (-1.0, -8.5),18 ,1.0),Material.STONE));
		world.register(new Terrain(new Box(new Vector (4.5, 15.5),7 ,1.0),Material.STONE));
		world.register(new Terrain(new Box(new Vector (6.0, 9.0),8 ,2.0),Material.STONE));
		world.register(new Terrain(new Box(new Vector (4.0, 3.0),6 ,2.0),Material.STONE));
		
		world.register(new Destruct(new Vector(-21.5, 4.5)));
		for (int i = 0; i < 7; ++i) 
			for(int j = i; j < 7; ++j)
				world.register(new Destruct(new Vector(-20.5 + j, 4.5 + i)));
		for (int i = 0; i < 3; ++i) 
			for(int j = i; j < 3; ++j)
				world.register(new Destruct(new Vector(-13.5 + j, 8.5 + i)));
		for (int i = 0; i < 4; ++i)
			world.register(new Destruct(new Vector(-13.5 + i, 4.5)));
		for (int i = 0; i < 4; ++i)
			world.register(new Destruct(new Vector(-10.5 + i, 15.5)));
		for (int i = 0; i < 3; ++i)
			world.register(new Destruct(new Vector(5.5, 16.5 + i)));
		for (int i = 0; i < 3; ++i)
			world.register(new Destruct(new Vector(3.5, -5.5 + i)));
		
		world.register(new Jumper(new Vector (-22.5, 0.4), 0.8, 0.8));
		world.register(new Jumper(new Vector (-11.5, 11.4), 0.8, 0.8));
		world.register(new SnowStorm(new Vector(-8.0, 8.0), 4, 8, new Vector(0.0, 0.5)));
		
		Key key1 = new Key(new Vector(-11.5, 5.5), Keys.RED);
		Key key2 = new Key(new Vector(6.5, 16.5), Keys.YELLOW);
		Key key3 = new Key(new Vector(-13.5, 9.5), Keys.BLUE);
		Key key4 = new Key(new Vector(4.5, -5.5), Keys.GREEN);
		Keys[] keys1 = {Keys.RED, Keys.YELLOW};
		Keys[] keys2 = {Keys.BLUE};
		Keys[] keys3 = {Keys.GREEN};
		world.register(key1);
		world.register(key2);
		world.register(key3);
		world.register(key4);
		
		Torch tor1 = new Torch(new Vector(-11.5 ,-4.5));
		Torch tor2 = new Torch(new Vector(-9.5, 17.0));
		Torch tor3 = new Torch(new Vector(-17.5, 1.5), true);
		Torch tor4 = new Torch(new Vector(4.5, 17.5), true);
		world.register(new EffectArea(new Box(new Vector(-11.5 ,-4.5),3,3), Damage.HEAT, 0.5, tor1));
		world.register(new EffectArea(new Box(new Vector(-9.5, 17.0),3,3), Damage.HEAT, 0.5, tor2));
		world.register(new EffectArea(new Box(new Vector(-17.5, 1.5),3,3), Damage.HEAT, 0.5, tor3));
		world.register(new EffectArea(new Box(new Vector(4.5, 17.5),3,3), Damage.HEAT, 0.5, tor4));
		world.register(tor1);
		world.register(tor2);
		world.register(tor3);
		world.register(tor4);
		
		
		world.register(new Ladder(new Vector(-15.5, -3.0), 1, 6));
		world.register(new Ladder(new Vector(8.5, 13.0), 1, 6));
		world.register(new Ladder(new Vector(1.5, 7.0), 1, 6));
		world.register(new Ladder(new Vector(7.5, 2.5), 1, 3));
		
		world.register(new Mover(new Vector(-9.0, -6.5), new Vector(1.0, -6.5), 2, 1,"stone.2", tor1));
		world.register(new Mover(new Vector(0.0, 15.5), new Vector(-6.0, 15.5), 2, 1,"stone.2", tor2));
		world.register(new Spike(new Vector(-9.5, -7.75), 12));
		
		Activator activ1 = new Activator(new Box(new Vector(5.5, -5.6), 0.8, 0.8),"box.empty", keys1);
		Activator activ2 = new Activator(new Box(new Vector(-13.5, 5.4), 0.8, 0.8),"box.empty", keys2);
		Activator activ3 = new Activator(new Box(new Vector(-20.5, 0.4), 0.8, 0.8),"box.empty", keys3);
		world.register(activ1);
		world.register(activ2);
		world.register(activ3);
		
		Trigger trig1 = new Trigger(new Box(new Vector(5,6),8,4));
		Trigger trig2 = new Trigger(new Box(new Vector(5,12),8,4));
		world.register(trig1);
		world.register(trig2);
		
		Mob mob1 = new IceBlob(new Vector(5.0,4.5), trig1);
		Mob mob2 = new IceBlob(new Vector(5.0,10.5), trig2);
		world.register(mob1);
		world.register(mob2);
		
		for(int i = 0; i < 4; ++i)
			world.register(new Door(new Vector(7.5, 4.5 + i), mob1, false));
		for(int i = 0; i < 4; ++i)
			world.register(new Door(new Vector(0.5, 4.5 + i), mob1, false));
		for(int i = 0; i < 4; ++i)
			world.register(new Door(new Vector(1.5, 10.5 + i), mob2, false));
		world.register(new Door(new Vector(8.5, -3.5), activ1, false));
		world.register(new Door(new Vector(7.5, -3.5), activ1, false));
		world.register(new Door(new Vector(6.5, -3.5), activ1, false));
		world.register(new Door(new Vector(6.5, -4.5), activ1, false));
		world.register(new Door(new Vector(6.5, -5.5), activ1, false));
		world.register(new Door(new Vector(-10.5, 5.5), activ2, false));
		world.register(new Door(new Vector(-11.5, 6.5), activ2, false));
		world.register(new Door(new Vector(-12.5, 5.5), activ2, false));
		world.register(new Door(new Vector(-21.5, 0.5), activ3, false));
		world.register(new Door(new Vector(-22.5, 1.5), activ3, false));
		
		world.register(new Exit(new Vector(-18.5,0.5), false));
		world.register(new Exit(new Vector(8.5,-5.5), activ1));
		world.register(new Background(new Box(new Vector(-7.0, 5.0), 36, 29), "terrain/bg/cave"));
		world.register(new Limits(new Vector(-7.0, 5.0), 36, 29));	
	}
}
