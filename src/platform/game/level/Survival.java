package platform.game.level;

import platform.game.World;
import platform.game.actor.Background;
import platform.game.actor.Limits;
import platform.game.actor.Overlay;
import platform.game.actor.Player;
import platform.game.actor.block.Material;
import platform.game.actor.block.Terrain;
import platform.util.Box;
import platform.util.Vector;

public class Survival extends Level{

	@Override
	public void register(World world) {
		super.register(world);
		world.setNextLevel(new InterLevel(Levels.CAVEEND));

		Player player = new Player(new Vector(0.5, 0.5),new Vector(0.0,3.0));
		world.register(player);
		world.register(new Overlay(player));
		
		world.register(new Terrain(new Box(new Vector (0.0, 0.0),256 ,1.0),Material.GRASS));
		
		world.register(new Background(new Box(new Vector(0.0, 0.0), 256, 256), "terrain/bg/sky"));
		world.register(new Limits(new Vector(0.0, 0.0), 256, 256));
	}
}
