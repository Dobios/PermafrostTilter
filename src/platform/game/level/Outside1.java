package platform.game.level;

import platform.game.World;
import platform.game.actor.*;
import platform.game.actor.block.*;
import platform.game.actor.interactors.*;
import platform.game.actor.zone.EffectArea;
import platform.game.signals.Constant;
import platform.util.Box;
import platform.util.Vector;

public class Outside1 extends Level {

	@Override
	public void register(World world) {
		super.register(world);
		world.setNextLevel(new InterLevel(Levels.OUTSIDE2));

		Player player = new Player(new Vector(-25.5, -4.5),new Vector(0.0,3.0));
		world.register(player);
		world.register(new Overlay(player));
		world.register(new Chrono());
		world.register(new Terrain(new Box(new Vector (-25.0, -6.0),8 ,2.0),Material.SNOW));
		world.register(new Terrain(new Box(new Vector (-26.0, 0.0),4 ,2.0),Material.SNOW));
		world.register(new Terrain(new Box(new Vector (-28.0, -2.0),2 ,6.0),Material.SNOW));
		world.register(new Terrain(new Box(new Vector (-27.0, 5.0),2 ,12.0),Material.SNOW));
		world.register(new Terrain(new Box(new Vector (-25.0, 10.0),6 ,2.0),Material.SNOW));
		world.register(new Terrain(new Box(new Vector (-23.0, 13.0),2 ,6.0),Material.SNOW));
		world.register(new Terrain(new Box(new Vector (-17.0, 15.0),14 ,2.0),Material.SNOW));
		world.register(new Terrain(new Box(new Vector (-11.0, 11.0),2 ,8.0),Material.SNOW));
		world.register(new Terrain(new Box(new Vector (-16.0, 9.5),8 ,1.0),Material.SNOW));
		world.register(new Terrain(new Box(new Vector (-17.0, 10.0),4 ,2.0),Material.SNOW));
		world.register(new Terrain(new Box(new Vector (-13.0, 10.5),2 ,1.0),Material.SNOW));
		world.register(new Terrain(new Box(new Vector (-12.5, 11.0),1 ,2.0),Material.SNOW));
		world.register(new Terrain(new Box(new Vector (-10.0, 7.5),4 ,1.0),Material.SNOW));
		world.register(new Terrain(new Box(new Vector (-3.0, 8.0),4 ,2.0),Material.SNOW));
		world.register(new Terrain(new Box(new Vector (-2.0, 4.0),2 ,10.0),Material.SNOW));
		world.register(new Terrain(new Box(new Vector (-4.0, 4.0),4, 2.0),Material.SNOW));
		world.register(new Terrain(new Box(new Vector (-5.0, 3.0),2 ,4.0),Material.SNOW));
		world.register(new Terrain(new Box(new Vector (-5.0, 2.0),8 ,2.0),Material.SNOW));
		world.register(new Terrain(new Box(new Vector (-9.0, 1.0),2 ,6.0),Material.SNOW));
		world.register(new Terrain(new Box(new Vector (-9.5, 4.5),1 ,1.0),Material.SNOW));
		world.register(new Terrain(new Box(new Vector (-5.5, 7.5),1 ,1.0),Material.SNOW));
		world.register(new Terrain(new Box(new Vector (-13.0, -1.0),10 ,2.0),Material.SNOW));
		world.register(new Terrain(new Box(new Vector (-17.0, 0.0),2 ,12.0),Material.SNOW));
		world.register(new Terrain(new Box(new Vector (-19.0, -6.0),6 ,2.0),Material.SNOW));
		world.register(new Terrain(new Box(new Vector (-22.0, -4.0),2 ,6.0),Material.SNOW));
		world.register(new Terrain(new Box(new Vector (-21.5, -2.0),3 ,1.0),Material.SNOW));
		world.register(new Terrain(new Box(new Vector (-20.0, 3.5),4 ,1.0),Material.SNOW));
		world.register(new Terrain(new Box(new Vector (-22.5, 4.5),1 ,3.0),Material.SNOW));
		world.register(new Terrain(new Box(new Vector (-23.5, 5.5),3 ,1.0),Material.SNOW));
		world.register(new Terrain(new Box(new Vector (-19.0, 5.5),2 ,1.0),Material.SNOW));
		world.register(new Terrain(new Box(new Vector (4.5, 0.0),17 ,2.0),Material.SNOW));
		world.register(new Terrain(new Box(new Vector (12.0, 5.0),2 ,12.0),Material.SNOW));
		world.register(new Terrain(new Box(new Vector (6.0, 6.0),2 ,6.0),Material.SNOW));
		world.register(new Terrain(new Box(new Vector (11.0, 2.0),4 ,2.0),Material.SNOW));
		world.register(new Terrain(new Box(new Vector (16.0, 11.0),2 ,6.0),Material.SNOW));
		world.register(new Terrain(new Box(new Vector (19.0, 13.0),4 ,2.0),Material.SNOW));
		world.register(new Terrain(new Box(new Vector (20.0, 16.0),2 ,8.0),Material.SNOW));
		world.register(new Terrain(new Box(new Vector (15, 19.0),12 ,2.0),Material.SNOW));

		world.register(new Sign(new Vector(-0.5, 6.5), Math.PI/-2, "sign.r"));
		world.register(new Sign(new Vector(4.5, 6.5), Math.PI/2, "sign.l"));

		world.register(new Ladder(new Vector(-23.5, -2.5), 1, 3));
		world.register(new Ladder(new Vector(-25.5, 3.5), 1, 5));
		world.register(new Ladder(new Vector(-15.5, 2.5), 1, 7));

		for ( int i = 0; i < 2; ++i) {
			world.register(new Destruct(new Vector(-19.5 + i, -2.5)));
			world.register(new Destruct(new Vector(-19.5 + i, -3.5)));
		}

		world.register(new Jumper(new Vector(-10.5, 0.4), 0.8, 0.8));
		world.register(new Jumper(new Vector(-6.5, 3.4), 0.8, 0.8));
		world.register(new Jumper(new Vector(8.5, 1.4), 0.8, 0.8));
		world.register(new Jumper(new Vector(-18.5, -4.6), 0.8, 0.8));

		world.register(new SnowStorm(new Vector(9.0, 7.5), 4, 6, new Vector(0.0, 0.5)));
		world.register(new SnowStorm(new Vector(14.0, 11.5), 2, 4, new Vector(0.0, 0.5)));

		Torch tor1 = new Torch(new Vector(-21.5, 0.5), true);
		Torch tor2 = new Torch(new Vector(-21.5, 0.5), true);
		world.register(new EffectArea(tor1));
		world.register(new EffectArea(tor2));
		world.register(tor1);
		world.register(tor2);

		Lever lev1 = new Lever(new Vector(-3.5, 5.25), Double.POSITIVE_INFINITY);
		world.register(lev1);

		Key key1 = new Key(new Vector(-20.5, -4.5), Keys.RED);
		Key key2 = new Key(new Vector(-18.5, 4.5), Keys.BLUE);
		Key key3 = new Key(new Vector(-12.5, 12.5), Keys.GREEN);
		Key key4 = new Key(new Vector(5.5, 1.5), Keys.YELLOW);
		Keys[] keys1 = {Keys.RED, Keys.BLUE, Keys.GREEN};
		Keys[] keys2 = {Keys.YELLOW};
		world.register(key1);
		world.register(key2);
		world.register(key3);
		world.register(key4);

		Activator activ1 = new Activator(new Box(new Vector(-23.5, 6.4),0.8, 0.8), "box.empty",keys1);
		Activator activ2 = new Activator(new Box(new Vector(15.5, 14.5),0.8, 0.8), "box.empty",keys2);
		world.register(activ1);
		world.register(activ2);

		world.register(new Mover(new Vector(-21.0, 9.5), new Vector(-21.0, 5.5), 2, 1, "stone.2"));
		world.register(new Mover(new Vector(-9.5, 6.0), new Vector(-5.5, 6.0), 1, 2, "stone.8"));
		world.register(new Mover(new Vector(4.0, 8.5), new Vector(0.0, 8.5), 2, 1, "stone.2"));
		
		for(int i = 0; i < 3; ++i) 
			world.register(new Door(new Vector(-19.5, 6.5 + i), activ1));
		world.register(new Door(new Vector(-7.5, 7.5), lev1));
		world.register(new Door(new Vector(-6.5, 7.5), lev1));
		
		world.register(new Exit(new Vector(-25.5, -4.5), false));
		world.register(new Exit(new Vector(17.5, 14.5), activ2));
		
		world.register(new Cloud(new Vector(-4.0,19.0), 50, 1, 0.6));
		world.register(new EffectArea(new Box(new Vector(-4.0, 6.5),50,27), Damage.COLD, 0.05, new Constant(true)));
		world.register(new Background(new Box(new Vector(-4.0, 6.5), 50, 27),"terrain/bg/snow"));
		world.register(new Limits(new Vector(-4.0, 6.5), 50, 27));
	}
}
