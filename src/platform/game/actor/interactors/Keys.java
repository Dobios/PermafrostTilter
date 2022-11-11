package platform.game.actor.interactors;

public enum Keys {
	RED("keys/key.red"), BLUE("keys/key.blue"), 
	YELLOW("keys/key.yellow"), GREEN("keys/key.green"),
	GRED("keys/gem.red"), GBLUE("keys/gem.blue"), 
	GYELLOW("keys/gem.yellow"), GGREEN("keys/gem.green"),
	COAL("keys/ore.coal"), DIAMOND("keys/ore.diamond"), 
	EMERALD("keys/ore.emerald"), GOLD("keys/ore.gold"), 
	IRON("keys/ore.iron"), RUBY("keys/ore.ruby"), 
	SILVER("keys/ore.silver"), WOOD("keys/wood");

	private String text;
	
	Keys(String text) {
		this.text = text;
	}


	public String getText() {
		return this.text;
	}

}

