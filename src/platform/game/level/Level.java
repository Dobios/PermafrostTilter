package platform.game.level;

import platform.game.actor.*;
import platform.util.Input;
import platform.util.Output;

/**
 * Base class for level factories, which provides fade in transition. Subclasses
 * are requires to override <code>register</code>.
 */
public abstract class Level extends Actor {
 ///UNCOMMENT ME WHEN NEEDED
    private double fadein;
    
    public Level() {
        fadein = 1.0;
    }
    
    @Override
    public int getPriority() {
        return Integer.MAX_VALUE;
    }

    @Override
    public void update(Input input) {
        fadein -= input.getDeltaTime();
        if (fadein <= 0.0)
            getWorld().unregister(this);
    }

    @Override
    public void draw(Input input, Output output) {
        output.drawSprite(getSprite("pixel.black"), output.getBox(), 0.0, fadein);
    }
    
    /** @return a new instance of default level */
    public static Level createDefaultLevel() {
        return new Menu();
    }
}
