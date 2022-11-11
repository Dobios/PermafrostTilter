package platform.game.actor.GUI;

import platform.game.actor.Difficulty;
import platform.util.Input;
import platform.util.Vector;

public class SetDifficulty extends Button{
	private Difficulty diffSelect = Difficulty.values()[0];
	private int selected = -1;

	public SetDifficulty(Vector position, double height, Cursor cursor) {
		super(position,height,"",cursor);
	}
	@Override
	public void update(Input input) {
		super.update(input);
		if(selected==-1){
			selected = getWorld().getDifficulty().ordinal();
			super.setText(getWorld().getDifficulty().getText());
		}
		if(super.getActive() && input.getMouseButton(1).isPressed()){
			++selected;
			if (selected >= Difficulty.values().length)
				selected = 0;
			diffSelect = Difficulty.values()[selected];
			getWorld().setDifficulty(diffSelect);
			super.setText(getWorld().getDifficulty().getText());
		}
		
	}

}
