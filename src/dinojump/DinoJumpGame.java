package dinojump;

import dinojump.manager.InputManager;
import dinojump.util.Avatar;
import dinojump.util.Position;

public class DinoJumpGame {
    public static void main(String[] args) {

        DinoJump.getInstance().startGameLoop(20);
        InputManager.getInstance();
        Avatar.getInstance().setPosition(new Position(Renderer.getInstance().getScreenWidth() / 2 - Renderer.getInstance().getAvatarDimensions() / 2, Renderer.getInstance().getScreenHeight() / 2));
    }
}
