package platform.game.level;

import platform.game.World;
import platform.game.actor.*;
import platform.game.actor.block.*;
import platform.game.actor.interactors.Activator;
import platform.game.actor.interactors.Exit;
import platform.game.actor.interactors.Key;
import platform.game.actor.interactors.Keys;
import platform.game.actor.interactors.Lever;
import platform.game.signals.*;
import platform.util.Box;
import platform.util.Vector;

public class BasicInteract extends Level{
	@Override 
	public void register(World world) {
		super.register(world);
		world.setNextLevel(new InterLevel(Levels.MENU));
		
		Player player = new Player(new Vector(1,1),new Vector(0.0,10.0));
		world.register(player);
		world.register(new Overlay(player));
		
		Key ki = new Key(new Vector(-4, 4), Keys.BLUE);
		Key ki2 = new Key(new Vector(-5, 7), Keys.YELLOW);
		Key ki3 = new Key(new Vector(-13, 4), Keys.RED);
		Lever lev1 = new Lever(new Vector(-15, 3.25), 3);
		world.register(new SnowStorm(new Vector(-14, 4),2.0 , 3.0, new Vector(0.5,0)));
		Lever lev = new Lever(new Vector(-4, 3.3), 3);
		Mover mov = new Mover(new Vector(-5, 5), new Vector(-5, 9), 2, 1, "stone.2");
		
		world.register(new Exit(new Vector(6,2),lev1));
		world.register(new Mover(new Vector(-6, 2.5),new Vector(-9.5, 2.5),  3, 1, "stone.2"));
		world.register(new Block (new Vector(-8, 1.5), 8, 1, "stone.2"));
		Keys keys[] = {Keys.RED,Keys.YELLOW,Keys.BLUE};
		Activator keydoor = new Activator(new Box(new Vector(3,2),1,1), "stone.2", keys );
		world.register(mov);
		world.register(lev);
		world.register(lev1);
		world.register(new Block (Vector.ZERO, 5, 2, "stone.2"));
		world.register(new Block (new Vector(4.5, 0.5), 4, 2, "stone.2"));
		world.register(new Trap (new Vector(-16, 4),lev1, "stone.2"));
		world.register(new Block (new Vector(-15, 2), 8, 2, "stone.2"));
		world.register(new Block (new Vector(-3.5, 1), 2, 4, "stone.8"));
		world.register(new Spike(new Vector(-0.2,1.25), 1));
		world.register(new Jumper(new Vector(-1.5, 1.5), 1, 1));
		world.register(new Torch(new Vector(2,2), false));
		world.register(new Tree(new Vector(1,2)));
		world.register(new Heart(new Vector(-3, 4)));
		world.register(ki);
		world.register(ki2);
		world.register(ki3);
		world.register(keydoor);
		world.register(lev);
		//world.register(new Key(new Vector(6, 2),2));
		for (int i = 0; i < 5; ++i) {
		world.register(new Door(new Vector(4, 2 + i),new Or(new And(ki,lev),keydoor),true));
		}
		for (int i = 1; i < 5; ++i) {
			world.register(new Door(new Vector(4 + i, 6),new Or(new And(ki,lev),keydoor),true));
			}
		
		//world.register(new Door(new Vector(-5, 4),2,true));
		
		world.register(new Limits(Vector.ZERO, 50, 50));
	}
}
