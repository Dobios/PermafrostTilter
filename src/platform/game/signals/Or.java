package platform.game.signals;

public class Or implements Signal {
	private Signal left;
	private Signal right;

	public Or (Signal left, Signal right) {
		this.left = left;
		this.right = right;
	}

	@Override 
	public boolean isActive() {
		return left.isActive() || right.isActive();
	}
}

