package platform.game.actor.block;

public enum Material {
	GRASS("grass"), SNOW("dirtsnow",0.001), SNOWS("stonesnow",0.001), STONEICE("stoneice"), STONE("castle"),ICE("ice",0.7);

	private String text;
	private double friction = 0.01;

	Material(String text, double friction) {
		this.text = text;
		this.friction = friction;
	}
	
	Material(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}
	
	public double getFriction(){
		return this.friction;
	}

}
