package platform.game.level;

public enum Levels {
	DREAM(0,new Dream()), OUTSIDE1(1,new Outside1()), OUTSIDE2(2,new Outside2()), 
	CAVE1(3,new Cave1()),CAVE2(4,new Cave2()),CAVE3(5,new Cave3()), 
	CAVEEND(6, new CaveEnd()), HAPPYEND(7, new HappyEnd()), END(8,new Menu()), BADEND(9,new Survival()),
	MENU(-1,new Menu()), CREDITS(-2,new Menu()), EGG(-3,new Menu());

	private int id;
	private Level level;

	Levels(int id, Level level) {
		this.id = id;
		this.level = level;
	}

	public int getId() {
		return this.id;
	}
	
	public Level getLevel(){
		return this.level;
	}

}
