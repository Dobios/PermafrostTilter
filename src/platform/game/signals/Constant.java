package platform.game.signals;

public class Constant implements Signal {
	private boolean constant;
	
	public Constant (boolean constant) {
		this.constant = constant;
	}
	
	public boolean isActive() {
		return constant;
	}
}
