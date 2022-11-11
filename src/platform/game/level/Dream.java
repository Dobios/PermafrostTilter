package platform.game.level;

import platform.game.World;
import platform.game.actor.Overlay;
import platform.game.actor.Player;
import platform.game.actor.GUI.Text;
import platform.game.actor.block.Material;
import platform.game.actor.block.Terrain;
import platform.game.signals.*;
import platform.util.Box;
import platform.util.Vector;
import platform.game.actor.*;
import platform.game.actor.block.*;
import platform.game.actor.interactors.*;
import platform.game.actor.mob.IceBlob;
import platform.game.actor.mob.Mob;
import platform.game.actor.zone.EffectArea;
import platform.game.actor.zone.Trigger;

public class Dream extends Level{
	
	@Override
	public void register(World world) {
		super.register(world);
		world.setNextLevel(new InterLevel(Levels.OUTSIDE1));
		
		world.setView(new Vector(1.0, 6.0), 15);
		
		Player player = new Player(new Vector(-26.0,1.0),new Vector(0.0,3.0));
		world.register(player);
		world.register(new Overlay(player));
		world.register(new Chrono());
		world.register(new Terrain(new Box(new Vector (-27.0, 4.0), 2, 12),Material.GRASS));
		world.register(new Terrain(new Box(new Vector (-23.5, 0.0), 1, 2),Material.GRASS));
		world.register(new Terrain(new Box(new Vector (-22.5, 0.0), 1, 4),Material.GRASS));
		world.register(new Terrain(new Box(new Vector (-21.5, 0.0), 1, 2),Material.GRASS));
		world.register(new Terrain(new Box(new Vector (-20.5, 2.0), 1, 8),Material.GRASS));
		world.register(new Terrain(new Box(new Vector (-19.0, 0.0), 2, 2),Material.GRASS));
		world.register(new Terrain(new Box(new Vector (-17.0, 0.0), 2, 4),Material.GRASS));
		world.register(new Terrain(new Box(new Vector (-22.0, -1.0), 12, 2),Material.GRASS));
		world.register(new Terrain(new Box(new Vector (-13.0, 0.0), 2, 4),Material.GRASS));
		world.register(new Terrain(new Box(new Vector (-8.0, 2.0), 4, 8),Material.GRASS));
		world.register(new Terrain(new Box(new Vector (-3.0, 8.0), 2, 10),Material.GRASS));
		world.register(new Terrain(new Box(new Vector (2.0, 5.5), 8, 5),Material.GRASS));
		world.register(new Terrain(new Box(new Vector (2.0, 5.5), 8, 5),Material.GRASS));
		world.register(new Terrain(new Box(new Vector (29.0, 1.0), 2, 6),Material.GRASS));
		world.register(new Terrain(new Box(new Vector (14.0, 7.0), 20, 2),Material.GRASS));
		world.register(new Terrain(new Box(new Vector (23.0, 5.0), 2, 6),Material.GRASS));
		world.register(new Terrain(new Box(new Vector (26.0, 3.0), 4, 2),Material.GRASS));
		world.register(new Terrain(new Box(new Vector (7.0, -1.0), 2, 2),Material.STONE));
		world.register(new Terrain(new Box(new Vector (9.0, -0.5), 2, 3),Material.STONE));
		world.register(new Terrain(new Box(new Vector (11.0, 1.0), 2, 6),Material.STONE));
		world.register(new Terrain(new Box(new Vector (10.0, 3.5), 4, 1),Material.STONE));
		world.register(new Terrain(new Box(new Vector (21.0, -1.0), 18, 2),Material.STONE));
		world.register(new Terrain(new Box(new Vector (-4.0, -1.0), 4, 2),Material.STONE));
		world.register(new Terrain(new Box(new Vector (-1.0, -0.5), 2, 3),Material.STONE));
		
		world.register(new Jumper(new Vector (-21.5, 1.5), 1, 1));
		world.register(new Spike(new Vector (-19.5, 1.2), 2.0));
		world.register(new SnowStorm(new Vector(-11.0, 2.0), 2, 8, new Vector(0.0, 0.3)));
		
		Torch tor1 = new Torch(new Vector(-3.5, 1.5));
		Torch tor2 = new Torch(new Vector(20.5, 1.5));
		world.register(new EffectArea(tor1));
		world.register(new EffectArea(tor2));
		world.register(tor1);
		world.register(tor2);
		
		Lever lev1 = new Lever(new Vector(9.0, 1.25), 3.0);
		world.register(lev1);
		
		Key key1 = new Key(new Vector (-17.5, 2.5), Keys.RED);
		Keys[] keys = {Keys.RED};
		world.register(key1);
		
		Activator acti = new Activator(new Box(new Vector(-7.0,6.5),1,1), "box.empty", keys);
		world.register(acti);
		
		Trigger trig1 = new Trigger(new Box(new Vector(18,4),8,8));
		world.register(trig1);
		
		Mob mob1 = new IceBlob(new Vector(20.0,5.0), trig1);
		Mob mob2 = new IceBlob(new Vector(19.0,5.0), trig1);
		Mob mob3 = new IceBlob(new Vector(17.0,5.0), trig1);
		world.register(mob1);
		world.register(mob2);
		world.register(mob3);
		And mobAnd = new And(new And(mob1,mob2),mob3);
		
		world.register(new Door(new Vector (-5.5, 5.5), acti,false));
		world.register(new Door(new Vector (-4.5, 5.5), acti,false));
		world.register(new Door(new Vector (22.5, 0.5),mobAnd, false));
		world.register(new Door(new Vector (22.5, 1.5),mobAnd, false));
		
		world.register(new Mover(new Vector (1.0, 0.5), new Vector (7.0, 0.5),2,1,"stone.2", tor1));
		world.register(new Mover(new Vector (7.0, 0.5), new Vector (7.0, 3.5),2,1,"stone.2", lev1));
		
		world.register(new Exit(new Vector (27.0, 0.5),true));
		world.register(new Background(new Box(new Vector(1.0, 6.0),58.0,16.0), "terrain/bg/dream"));
		world.register(new Limits(new Vector(1.0, 6.0), 58.0, 16.0));
		
		//HELP
		world.register(new Text(new Vector(-20.0,0.0), 0.3, "Press 'up' to jump"));
		world.register(new Text(new Vector(-21.0,-0.8), 0.3, "The jumper throws you into the air"));
		world.register(new Text(new Vector(-17.5,7.6), 0.3, "Ouch those spikes"));
		world.register(new Text(new Vector(-17.5,7.0), 0.3, "look painfull"));
		world.register(new Text(new Vector(-17.0,4.0), 0.3, "Pick up the key"));
		world.register(new Text(new Vector(-15.0,3.0), 0.3, "Dodge the hole"));
		world.register(new Text(new Vector(-7.2,8.0), 0.3, "Use the key to continue"));
		world.register(new Text(new Vector(1,7.0), 0.3, "Press space to light the torch"));
		world.register(new Text(new Vector(1,6.4), 0.3, "Shooting fireballs drains vigor"));
		world.register(new Text(new Vector(1,5.8), 0.3, "then drains your life"));
		world.register(new Text(new Vector(1,5.2), 0.3, "up to 2 hearts"));
		world.register(new Text(new Vector(1,4.6), 0.3, "then you shoot snowballs"));
		world.register(new Text(new Vector(1,4.0), 0.3, "snowballs calm IceBlobs"));
		world.register(new Text(new Vector(1,3.4), 0.3,"and turn out torches"));
		world.register(new Text(new Vector(13,7.4), 0.3, "Kill the IceBlobs with your fireballs"));
		world.register(new Text(new Vector(13,6.8), 0.3, "A lit torch also  hurts them"));
		world.register(new Text(new Vector(13,6.2), 0.3, "go close to a lit torch to regen your vigor"));
		
		world.register(new Text(new Vector(26,3.4), 0.3, "Now go and"));
		world.register(new Text(new Vector(26,2.8), 0.3, "find your lost worm"));
	}
	
	
}
