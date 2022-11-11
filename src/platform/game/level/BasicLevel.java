package platform.game.level;

import platform.game.World;
import platform.game.actor.*;
import platform.game.actor.block.*;
import platform.game.actor.interactors.Exit;
import platform.game.actor.interactors.Key;
import platform.game.actor.interactors.Keys;
import platform.game.actor.interactors.PressurePlate;
import platform.util.Vector;

public class BasicLevel extends Level {
	// UNCOMMENT ME WHEN NEEDED
	@Override
	public void register(World world) {
		super.register(world);

		// Register a new instance, to restart level automatically
		world.setNextLevel(new InterLevel(Levels.MENU));

		// Create blocks
		// world.register(new Block(new Box(new Vector(0, 0), 4, 2), world.getLoader().getSprite("stone.broken.2")));
		//world.register(new Block(new Box(new Vector(-1.5, 1.5), 1, 1), world.getLoader().getSprite("stone.broken.1")));


		Player player = new Player(new Vector(0,0),new Vector(0.0,10.0));
		
		world.register(player);
		world.register(new Overlay(player));
		world.register(new Destruct(new Vector(0, -4)));
		world.register(new Jumper(new Vector(15, -9), 1, 1));
		world.register(new Jumper(new Vector(10, -4), 1, 1));
		world.register(new Block(new Vector(0.0, 1.0),8 ,1,"box.empty"));
		world.register(new Exit(new Vector(7,-4),true));
		world.register(new Destruct(new Vector(5, -4)));
		world.register(new Destruct(new Vector(5, -3)));
		world.register(new Destruct(new Vector(5, -2)));
		world.register(new Destruct(new Vector(5, -1)));
		world.register(new Destruct(new Vector(6, -1)));
		world.register(new Destruct(new Vector(7, -1)));
		world.register(new Destruct(new Vector(7, 0)));
		world.register(new Destruct(new Vector(6, 0)));
		world.register(new Destruct(new Vector(5, 0)));
		world.register(new Destruct(new Vector(4, 0)));
		world.register(new Destruct(new Vector(3, 0)));
		world.register(new Destruct(new Vector(2, 0)));
		world.register(new Destruct(new Vector(1, 0)));
		world.register(new Destruct(new Vector(0, -1)));
		world.register(new Destruct(new Vector(-1, 0)));
		world.register(new Destruct(new Vector(-2, 0)));
		world.register(new Destruct(new Vector(-3, 0)));
		world.register(new Destruct(new Vector(-4, 0)));
		world.register(new Destruct(new Vector(-5, 0)));
		world.register(new Destruct(new Vector(-6, 0)));
		world.register(new Destruct(new Vector(-7, -1)));
		world.register(new Destruct(new Vector(-7, -2)));
		world.register(new Destruct(new Vector(-7, -3)));
		world.register(new Destruct(new Vector(-7, -4)));
		world.register(new Destruct(new Vector(-6, -4)));
		PressurePlate plat = (new PressurePlate(new Vector(5,-4.5)));
		world.register(plat);
		world.register(new Door(new Vector(6, -4),plat,true));
		world.register(new Block(new Vector (0, -5), 25, 1, "box.empty"));
		world.register(new Block(new Vector(-8, -0.5),1 ,10,"box.empty"));
		world.register(new Block(new Vector(8, -0.5),1 ,10,"box.empty")); 
		world.register(new Block(new Vector(-7, -2), 1, 1, "box.empty"));
		world.register(new Block(new Vector(7, -2), 1, 1, "box.empty"));
		world.register(new Block(new Vector(-10, 6.5), 1, 1, "box.empty"));
		world.register(new Block(new Vector(-14, 9), 2, 1, "stone.2"));
		world.register(new Block(new Vector(-10, 11.5), 1, 1, "box.empty"));
		world.register(new Block(new Vector(-3, 10), 1, 1, "box.empty"));
		world.register(new Block(new Vector(3, 10), 3, 1, "stone.3"));
		world.register(new Block(new Vector(8, 10), 2, 1, "box.empty"));
		world.register(new Block(new Vector(12, 13), 1, 1, "box.empty"));
		world.register(new Block(new Vector(8, 15), 1, 1, "box.empty"));
		world.register(new Block(new Vector(5, 17), 1, 1, "box.empty"));
		world.register(new Block(new Vector(3, 19), 1, 1, "box.empty"));
		world.register(new Block(new Vector(1, 21), 1, 1, "box.empty"));
		world.register(new Block(new Vector(6, 23), 1, 1, "box.empty"));
		world.register(new Ladder(new Vector(9, 27), 1, 4));
		world.register(new Block(new Vector(11, 28), 1, 1, "box.empty"));
		world.register(new Jumper(new Vector(11, 29), 1, 1));
		world.register(new Block(new Vector(5, 30), 1, 1, "box.empty"));
		Key ki = new Key(new Vector(-4, 29), Keys.GREEN);
		world.register(ki);
		world.register(new Block(new Vector(-3, 27), 3, 1, "stone.3"));
		world.register(new Block(new Vector(-14, 22), 15, 1, "stone.3"));
		world.register(new Exit(new Vector(-21,23),true));
		world.register(new Door(new Vector(-19, 23),ki));
		world.register(new Block(new Vector(0.0, -10), 45, 1, "box.empty"));
		world.register(new Block(new Vector(0, 40), 45, 1, "box.empty"));
		world.register(new Block(new Vector(-22, 15), 1, 50, "box.empty"));
		world.register(new Block(new Vector(22, 15), 1, 50, "box.empty"));

	}

}
