package platform.game.level;

import platform.game.GameType;
import platform.game.World;
import platform.game.actor.*;
import platform.game.actor.GUI.*;
import platform.util.Vector;

public class InterLevel extends Level {

	private int id;
	private Level nextLevel;
	//private Levels level;

	public InterLevel(Levels next){
		//this.level = next;
		this.id = next.getId();
		this.nextLevel = next.getLevel();
	}
	
	public InterLevel(int id, Level next){
		this.id = id;
		this.nextLevel = next;
	}

	public void register(World world) {
		super.register(world);
		world.setView(Vector.ZERO, 11);

		// Register a new instance, to restart level automatically
		world.setNextLevel(new BasicInteract());

		Cursor cursor = new Cursor();
		// Create Button & text
		world.register(cursor);

		world.register(new Chrono(false));
		world.register(new Cloud(new Vector(0,15), 30, 3, 1.0));
		switch(id){
		case -3:
			world.register(new Text(new Vector(0,8), 1.2, "You found it ?"));
			break;
		case -2: 
			world.register(new Text(new Vector(0,8), 1.2, "Credits"));
			break;
		case -1: 
			break;
		default:
			world.register(new Text(new Vector(0,8), 1.2, "Chapter - "+id));
		}
		world.register(new SelectLevel(new Vector(0,-7),1,"Continue",cursor, nextLevel));

		switch(id){
		case -1:
			world.register(new Text(new Vector(0,8), 1.2, "Level Completed"));
			break;
		case 0:
			world.setGameType(GameType.STORY);
			build0(world);//before dream
			break;
		case 1:
			build1(world);//before outside1
			break;
		case 2:
			build2(world);//before outside2
			break;
		case 3:
			build3(world);//before cave1
			break;
		case 4:
			build4(world);//before cave2
			break;
		case 5:
			build5(world);//before cave3
			break;
		case 6:
			build6(world);//before cave end
			break;
		case 7:
			build7(world);//before happy end
			break;
		case 8:
			build8(world);//the end !
			break;
		case 9:
			build9(world);//the  bad end !
			break;
		case -2:
			buildC(world);
			break;
		case -3:
			world.register(new Text(new Vector(0,4), 1.1, "Good Job !"));
			world.register(new Text(new Vector(0,1), 0.8, "you found the hidden button"));
			world.register(new Text(new Vector(0,-2), 1.1, "Now what ?"));
			world.register(new Text(new Vector(0,-4), 1.0, "More playing ?"));
			break;
		default:
			world.register(new Text(new Vector(0,0), 1.1, "Unknown Chapter"));
		}
	}

	private void build0(World world){
		world.register(new Text(new Vector(0,4), 0.7, "You lived a happy life with your worm"));
		world.register(new Text(new Vector(0,3), 0.7, "One day you went to his hole"));
		world.register(new Text(new Vector(0,2), 0.7, "and noticed something a bit abnormal"));
		world.register(new Text(new Vector(0,-1.5), 0.8, "You decided to investigate further"));
	}

	private void build1(World world){
		world.register(new Text(new Vector(0,4), 0.7, "You found only IceBlobs !"));
		world.register(new Text(new Vector(0,3), 0.7, "Your worm was missing"));
		world.register(new Text(new Vector(0,2), 0.7, "Where did he go ?"));
		world.register(new Text(new Vector(0,-1.5), 0.8, "You went through a strange door"));
	}

	private void build2(World world){
		world.register(new Text(new Vector(0,4), 0.7, "After going through that door"));
		world.register(new Text(new Vector(0,3), 0.7, "you found yourself outside"));
		world.register(new Text(new Vector(0,2), 0.7, "It was suddendly winter and cold"));
		world.register(new Text(new Vector(0,-1.5), 0.8, "What happened ?"));
	}

	private void build3(World world){
		world.register(new Text(new Vector(0,4), 0.7, "you are stuck at the bottom of a cliff"));
		world.register(new Text(new Vector(0,3), 0.7, "Something strange is happening here"));
		world.register(new Text(new Vector(0,2), 0.7, "What does this door lead to ?"));
		world.register(new Text(new Vector(0,-1.5), 0.8, "So many questions but for now"));
		world.register(new Text(new Vector(0,-2.5), 0.8, "You have to go on"));
	}

	private void build4(World world){
		world.register(new Text(new Vector(0,4), 0.7, "you are heading further into the cave "));
		world.register(new Text(new Vector(0,3), 0.7, "You hope to find something sooner or later"));
		world.register(new Text(new Vector(0,2), 0.7, "From where are those IceBlobs coming from ?"));
		world.register(new Text(new Vector(0,-1.5), 0.8, "So many questions but for now"));
		world.register(new Text(new Vector(0,-2.5), 0.8, "You have to go on"));
	}
	private void build5(World world){
		world.register(new Text(new Vector(0,4), 0.7, "These puzzles aren't that easy"));
		world.register(new Text(new Vector(0,3), 0.7, "You hardly manage to continue"));
		world.register(new Text(new Vector(0,2), 0.7, "When does it end ?"));
		world.register(new Text(new Vector(0,-1.5), 0.8, "So many questions but for now"));
		world.register(new Text(new Vector(0,-2.5), 0.8, "You have to go on"));
	}
	private void build6(World world){
		world.register(new Text(new Vector(0,4), 0.7, "It's getting colder"));
		world.register(new Text(new Vector(0,3), 0.7, "You feel you are almost at the end"));
		world.register(new Text(new Vector(0,2), 0.7, "Is my worm close by ?"));
		world.register(new Text(new Vector(0,-1.5), 0.8, "So many questions but for now"));
		world.register(new Text(new Vector(0,-2.5), 0.8, "You have to go on"));
	}
	private void build7(World world){
		world.register(new Text(new Vector(0,4), 0.7, "You disabled the snow cristal"));
		world.register(new Text(new Vector(0,3), 0.7, "and you beat the enemies but"));
		world.register(new Text(new Vector(0,2), 0.7, "where is your worm ?"));
		world.register(new Text(new Vector(0,-1.5), 0.8, "That door led you back outside"));
		world.register(new Text(new Vector(0,-2.5), 0.8, "When you find something unexpected"));
	}
	private void build8(World world){
		world.register(new Text(new Vector(0,4), 0.7, "You found your worm !"));
		world.register(new Text(new Vector(0,3), 0.7, "And slowed down the"));
		world.register(new Text(new Vector(0,2), 0.7, "neverending frost"));
		world.register(new Text(new Vector(0,1), 0.7, "you can't run forever"));
		world.register(new Text(new Vector(0,-2.5), 0.8, "What is awaiting you ?"));
		world.register(new Text(new Vector(0,-3.5), 0.8, "Keep warm.."));
	}
	private void build9(World world) {
		world.register(new Text(new Vector(0,4), 0.7, "What have you done ? !"));
		world.register(new Text(new Vector(0,3), 0.7, "Was it all worth it?"));
		world.register(new Text(new Vector(0,2), 0.7, "are you proud of yourself"));
		world.register(new Text(new Vector(0,1), 0.7, "you can't run forever"));
		world.register(new Text(new Vector(0,-2.5), 0.8, "How long can you survive?"));
		world.register(new Text(new Vector(0,-3.5), 0.8, "no one knows..."));
	}
	private void buildC(World world){

		world.register(new Text(new Vector(0,4), 0.7, "Code - CS-107"));
		world.register(new Text(new Vector(0,3), 0.7, "Code - Cedric Holzl"));
		world.register(new Text(new Vector(0,2), 0.7, "Code - Andrew Dobis"));
		world.register(new Text(new Vector(0,1), 0.7, "Sprites - 'kenney.nl'"));
		world.register(new Text(new Vector(0,0), 0.7, "Sprites - 'opengameart.org'"));
		world.register(new Text(new Vector(0,-1), 0.7, "Sprites - Drawn by us"));
		world.register(new Text(new Vector(0,-3.5), 0.9, "Thanks for playing"));
	}

}
