package platform.game.level;

import platform.game.World;
import platform.game.actor.block.Material;
import platform.game.actor.block.Terrain;
import platform.game.signals.And;
import platform.game.signals.Constant;
import platform.util.Box;
import platform.util.Vector;
import platform.game.actor.*;
import platform.game.actor.block.*;
import platform.game.actor.interactors.*;
import platform.game.actor.mob.BossBlob;
import platform.game.actor.mob.IceBlob;
import platform.game.actor.mob.Mob;
import platform.game.actor.zone.EffectArea;
import platform.game.actor.zone.Trigger;

public class Outside2 extends Level{
	
	@Override
	public void register(World world) {
		super.register(world);
		world.setNextLevel(new InterLevel(Levels.CAVE1));
		
		Player player = new Player(new Vector(-27.0,1.0),new Vector(0.0,3.0));
		world.register(player);
		world.register(new Overlay(player));

		world.register(new Chrono());
		world.register(new Terrain(new Box(new Vector (-29, 3.0), 2, 12),Material.ICE));
		world.register(new Exit(new Vector(-27.0,0.5), false));
		world.register(new Terrain(new Box(new Vector (-27.5, 5), 1,1),Material.STONE));
		world.register(new Key(new Vector (-27.5, 6), Keys.RED));
		Torch tor1 = new Torch(new Vector(-26.5, 1.5),true);
		world.register(new EffectArea(tor1));
		world.register(tor1);
		world.register(new Terrain(new Box(new Vector (-25.0, -0.5), 2, 3),Material.STONE));
		world.register(new Terrain(new Box(new Vector (-27.0, -1.5), 6, 3),Material.STONE));
		world.register(new Terrain(new Box(new Vector (-23.5, 2.5), 5, 1),Material.STONE));
		world.register(new Destruct(new Vector(-24.5,1.5)));
		world.register(new Terrain(new Box(new Vector (-22.5, -2.0), 3, 2),Material.STONE));
		world.register(new Terrain(new Box(new Vector (-20.5, -2.5), 1, 1),Material.STONE));
		world.register(new Terrain(new Box(new Vector (-22.0, 1.5), 2, 3),Material.STONE));
		world.register(new Key(new Vector (-23.5, -0.5), Keys.COAL));
		world.register(new Jumper(new Vector (-20.5, -1.5), 1, 1));
		world.register(new Terrain(new Box(new Vector (-19.0, 0.5), 2,7),Material.STONE));
		world.register(new Terrain(new Box(new Vector (-17.0, 1.5), 4,1),Material.STONE));
		world.register(new Key(new Vector (-17.5, 2.5), Keys.COAL));
		world.register(new Terrain(new Box(new Vector (-16.0, -2.0), 6,2),Material.STONE));
		world.register(new Terrain(new Box(new Vector (-12.0, -1.5), 2,3),Material.STONE));
		Keys[] keys1 = {Keys.RED};
		Activator acti1 = new Activator(new Box(new Vector(-14.5,-0.5),1,1), "box.empty", keys1);
		world.register(acti1);
		world.register(new Door(new Vector (-11.5, 0.5), acti1,false));
		world.register(new Terrain(new Box(new Vector (-10, 1.5), 4,1),Material.STONE));
		world.register(new Terrain(new Box(new Vector (-10.5, -2), 3,2),Material.STONE));
		Keys[] keys2 = {Keys.COAL, Keys.COAL};
		Activator acti2 = new Activator(new Box(new Vector(-9.5,-0.5),1,1), "oven", keys2);
		world.register(acti2);
		Torch tor2 = new Torch(new Vector(-9.5,-0.5), acti2);
		world.register(new EffectArea(tor2));
		world.register(tor2);
		world.register(new Trap(new Vector(-6.5,-0.5), tor2, "stone.2"));
		world.register(new Terrain(new Box(new Vector (-8, -0.5), 2,5),Material.STONE));
		world.register(new Terrain(new Box(new Vector (0, -2), 14,2),Material.STONE));
		
		Trigger trig1 = new Trigger(new Box(new Vector(0,0),14,4));
		world.register(trig1);
		
		world.register(new IceBlob(new Vector(-2.0,-1.0), trig1));
		world.register(new IceBlob(new Vector(-6.0,-1.0), trig1));
		world.register(new IceBlob(new Vector(6.0,-1.0), trig1));
		world.register(new IceBlob(new Vector(2.0,-1.0), trig1));
		world.register(new BossBlob(new Vector(4.0,-1.0), trig1));
		
		Torch tor3 = new Torch(new Vector(-2, -0.5));
		world.register(new EffectArea(tor3));
		world.register(tor3);
		
		world.register(new Terrain(new Box(new Vector (8, -1), 2,4),Material.STONE));

		world.register(new Terrain(new Box(new Vector (11.5, 8.5), 5,1),Material.ICE));
		world.register(new Ladder(new Vector(8.5,4),1.0,10.0));
		world.register(new Terrain(new Box(new Vector (9.5, 3.0), 1,12),Material.STONE));
		world.register(new SnowStorm(new Vector(12.0, 10.0), 6, 5, new Vector(0.5, -0.5)));
		world.register(new Terrain(new Box(new Vector (12, 3.0), 4,12),Material.STONE));
		world.register(new Terrain(new Box(new Vector (19, -1), 18,4),Material.STONE));
		world.register(new Terrain(new Box(new Vector (23, 7), 10,8),Material.STONE));
		world.register(new Terrain(new Box(new Vector (29, 4), 2,14),Material.STONE));
		
		
		Torch tor4 = new Torch(new Vector(16, 2.0));
		world.register(new EffectArea(tor4));
		world.register(tor4);
		Mob mob1 = new IceBlob(new Vector(24.0,2.0), trig1);
		Mob mob2 = new IceBlob(new Vector(22.0,2.0), trig1);
		Mob mob3 = new IceBlob(new Vector(21.0,2.0), trig1);
		world.register(mob1);
		world.register(mob2);
		world.register(mob3);
		And mobAnd = new And(new And(mob1,mob2),mob3);
		
		Torch tor5 = new Torch(new Vector(24, 2.0));
		world.register(new EffectArea(tor5));
		world.register(tor5);
		
		world.register(new Exit(new Vector (26.0, 1.5),mobAnd));
		world.register(new Cloud(new Vector(0,13.0), 60, 1, 0.6));
		world.register(new EffectArea(new Box(new Vector(0.0, 5.0),60.0,16.0), Damage.COLD, 0.05, new Constant(true)));
		world.register(new Background(new Box(new Vector(0.0, 5.0),60.0,16.0), "terrain/bg/snow"));
		world.register(new Limits(new Vector(0.0, 5.0), 60.0, 16.0));
		
	}
	
	
}
