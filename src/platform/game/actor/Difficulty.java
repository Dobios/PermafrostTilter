package platform.game.actor;

public enum Difficulty  {
	NORMAL("Normal"), HARDCORE("Hardcore");
	
	private String text;

	Difficulty(String text) {
		this.text = text;
	}
	public String getText() {
		return this.text;
	}

}

