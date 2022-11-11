package platform.game.level;

import platform.game.World;
import platform.game.actor.Cloud;
import platform.game.actor.GUI.Cursor;
import platform.game.actor.GUI.SelectLevel;
import platform.game.actor.GUI.Text;
import platform.util.Vector;

public class GameOver extends Level{

	public void register(World world) {

		super.register(world);
		world.setView(Vector.ZERO, 11);

		// Register a new instance, to restart level automatically
		world.setNextLevel(new Menu());
		
		Cursor cursor = new Cursor();
		// Create Button & text
		world.register(cursor);
		world.register(new Cloud(new Vector(0,15), 80, 3, 1.0));
		world.register(new Text(new Vector(0,2), 1.2, "Game Over"));
		world.register(new SelectLevel(new Vector(-7,-7),1,"Main Menu",cursor, new Menu()));
	}
}
