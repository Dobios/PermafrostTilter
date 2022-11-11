package platform.game.level;

import platform.game.GameType;
import platform.game.World;
import platform.game.actor.*;
import platform.game.actor.GUI.*;
import platform.util.Vector;

public class Menu extends Level {
	
	public void register(World world) {
		super.register(world);
		world.setView(Vector.ZERO, 11);
		world.resetChrono();
		world.setGameType(GameType.FREE);
		// Register a new instance, to restart level automatically
		world.setNextLevel(new BasicInteract());
		
		Cursor cursor = new Cursor();
		// Create Button & text
		world.register(cursor);
		world.register(new Cloud(new Vector(0,15), 80, 3, 1.0));
		world.register(new Text(new Vector(0,9), 1.2, "Permafrost Tilter"));
		world.register(new SelectLevel(new Vector(0,5),1,"Start Game",cursor, new InterLevel(Levels.DREAM)));

		world.register(new MoveView(new Vector(0,2.5),1, "Options", cursor, new Vector(26,42)));
		world.register(new Text(new Vector(26,47.5), 1.2, "Difficulty"));
		world.register(new SetDifficulty(new Vector(26,45.5),1,cursor));
		world.register(new Text(new Vector(26,41.5), 1.2, "Respawn"));
		world.register(new OneLife(new Vector(26,39.5),1, cursor));
		world.register(new MoveView(new Vector(18.0,34.0),1, "Back", cursor, Vector.ZERO));
		
		world.register(new MoveView(new Vector(0,0),1, "Select Level", cursor, new Vector(-26,0)));
		world.register(new Text(new Vector(-26,7.5), 1.2, "Main Game Levels"));
		
		world.register(new SelectLevel(new Vector(-30.5,4),1,"0",cursor, new Dream()));
		world.register(new SelectLevel(new Vector(-27.5,4),1,"1",cursor, new Outside1()));
		world.register(new SelectLevel(new Vector(-24.5,4),1,"2",cursor, new Outside2()));
		world.register(new SelectLevel(new Vector(-21.5,4),1,"3",cursor, new Cave1()));
		world.register(new SelectLevel(new Vector(-30.5,1),1,"4",cursor, new Cave2()));
		world.register(new SelectLevel(new Vector(-27.5,1),1,"5",cursor, new Cave3()));
		world.register(new SelectLevel(new Vector(-24.5,1),1,"6",cursor, new CaveEnd()));
		world.register(new SelectLevel(new Vector(-21.5,1),1,"7",cursor, new HappyEnd()));
		//world.register(new SelectLevel(new Vector(-21.5,1),1,"8",cursor, new Survival()));
		
		world.register(new MoveView(new Vector(-26,-4),1, "Back", cursor, Vector.ZERO));
		
		
		world.register(new MoveView(new Vector(0,-3),1, "More Levels", cursor, new Vector(26,0)));
		
		world.register(new Text(new Vector(26,7.5), 1.2, "Dev Levels"));
		world.register(new SelectLevel(new Vector(26,5),1,"BasicInteract",cursor, new BasicInteract()));
		world.register(new SelectLevel(new Vector(26,3),1,"Test Level",cursor, new TestLevel()));
		world.register(new SelectLevel(new Vector(26,1),1,"Basic Level",cursor, new BasicLevel()));
		world.register(new SelectLevel(new Vector(26,-1),1,"Box",cursor, new BoxLevel()));
		world.register(new MoveView(new Vector(26,-4),1, "Back", cursor, Vector.ZERO));
		
		world.register(new SelectLevel(new Vector(0,30),2, "CLICK ME", cursor, new InterLevel(Levels.EGG)));
		
		world.register(new SelectLevel(new Vector(0,-9),0.5, "Credits", cursor, new InterLevel(Levels.CREDITS)));
		world.register(new MoveView(new Vector(-3,-6.5),1, "Help", cursor, new Vector(0,-26)));
		world.register(new Quit(new Vector(3,-6.5),1,cursor));
		
		
		world.register(new Text(new Vector(0,-20), 1.2, "Help"));
		world.register(new MoveView(new Vector(0,-24.5),1, "Controls", cursor, new Vector(-26,-26)));
		world.register(new MoveView(new Vector(0,-27.5),1, "Mechanics", cursor, new Vector(26,-26)));
		
		world.register(new MoveView(new Vector(18,-35),1, "Back", cursor, new Vector(0,-26)));
		world.register(new MoveView(new Vector(-18,-35),1, "Back", cursor, new Vector(0,-26)));

		world.register(new Text(new Vector(-26,-18), 1.0, "Controls"));
		world.register(new Text(new Vector(-26,-21), 0.6, "Q - Restart"));
		world.register(new Text(new Vector(-26,-22), 0.6, "E - Interact "));
		world.register(new Text(new Vector(-26,-23), 0.6, "B - Blow"));
		world.register(new Text(new Vector(-26,-24), 0.6, "Space - Shoot"));
		world.register(new Text(new Vector(-26,-25), 0.6, "Arrow Keys - Move"));
		world.register(new Text(new Vector(-26,-26), 0.6, "ESC - Menu"));
		
		world.register(new Text(new Vector(26,-18), 1.0, "Mecanics"));
		world.register(new Text(new Vector(26,-20.5), 0.4, "You controll a little green block to solve puzzles"));
		world.register(new Text(new Vector(26,-21.5), 0.4, "Use your arrow keys to move and jump"));
		world.register(new Text(new Vector(26,-22), 0.4, "Use you space bar to shoot fireballs"));
		world.register(new Text(new Vector(26,-22.5), 0.4, "Fireballs turn on torches and hurt enemies"));
		world.register(new Text(new Vector(26,-23), 0.4, "Fireball drain your vigor and then your life !"));
		world.register(new Text(new Vector(26,-23.5), 0.4, "At 2 heart you will shoot snowballs"));
		world.register(new Text(new Vector(26,-24), 0.4, "Snowballs calm frenzy mobs and turn of torches"));
		world.register(new Text(new Vector(26,-24.5), 0.4, "To regain vigor you need to stand close to a heat source"));
		world.register(new Text(new Vector(26,-25), 0.4, "Being close to a cold source drains your vigor"));
		world.register(new Text(new Vector(26,-25.5), 0.4, "If no vigor is left you will take damage"));
		world.register(new Text(new Vector(26,-26.5), 0.4, "During your quest you will meet IceBlobs"));
		world.register(new Text(new Vector(26,-27), 0.4, "IceBlobs shoot snowballs and drain your vigor"));
		world.register(new Text(new Vector(26,-27.5), 0.4, "When low life they enter a frenzy !"));
		world.register(new Text(new Vector(26,-28), 0.4, "They take damage from heat sources"));
		world.register(new Text(new Vector(26,-28.5), 0.4, "You can kill them with torches without exposing yourself"));
		world.register(new Text(new Vector(26,-29.5), 0.4, "The Activator is a block who activate other elements"));
		world.register(new Text(new Vector(26,-30), 0.4, "The necessary keys are displayed on it"));
		world.register(new Text(new Vector(26,-31), 0.4, "The wind zones are pushing you in a certain direction"));
		world.register(new Text(new Vector(26,-32), 0.4, ""));
		
		world.register(new MoveView(new Vector(0,-34),1, "Back", cursor, Vector.ZERO));
		
	}

}
