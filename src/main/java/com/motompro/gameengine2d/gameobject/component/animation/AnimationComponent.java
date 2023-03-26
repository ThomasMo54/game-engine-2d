package com.motompro.gameengine2d.gameobject.component.animation;

import com.motompro.gameengine2d.gameobject.Updatable;
import com.motompro.gameengine2d.gameobject.component.Component;
import com.motompro.gameengine2d.gameobject.component.exception.AnimationComponentException;
import com.motompro.gameengine2d.math.Vector2;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AnimationComponent extends Component implements Updatable {

    private final Map<Vector2, BufferedImage> sprites = new HashMap<>();
    private final Map<String, Animation> animations = new HashMap<>();
    private double currentTime = 0;
    private double animationEndTime = 0;
    private double delayTime = 0;
    private Animation currentAnimation;

    public AnimationComponent(BufferedImage spriteSheet, Vector2 spriteSize) {
        int spriteWidth = (int) spriteSize.getX();
        int spriteHeight = (int) spriteSize.getY();
        for(int y = 0; y < spriteSheet.getHeight() / spriteHeight; y++)
            for(int x = 0; x < spriteSheet.getWidth() / spriteWidth; x++)
                sprites.put(new Vector2(x, y), spriteSheet.getSubimage(x * spriteWidth, y * spriteHeight, spriteWidth, spriteHeight));
    }

    @Override
    public void update(double deltaTime) {
        if(currentAnimation == null)
            return;
        if(currentTime >= animationEndTime) {
            if(!currentAnimation.doLoop()) {
                currentAnimation = null;
                return;
            }
            delayTime += deltaTime;
            if(delayTime >= currentAnimation.getLoopDelay()) {
                delayTime = 0;
                currentTime = 0;
            }
            return;
        }
        currentTime += deltaTime;
    }

    public BufferedImage getCurrentSprite() {
        if(currentAnimation == null)
            return null;
        return currentAnimation.getCurrentImage(currentTime);
    }

    public void createAnimation(AnimationBuilder builder) {
        Animation animation = builder.build();
        animation.setSprites(animation.getKeyFrames().stream()
                .map(keyFrame -> sprites.get(keyFrame.getFramePos()))
                .collect(Collectors.toList()));
        animations.put(animation.getName(), animation);
    }

    public void startAnimation(String animationName) {
        if(animationName == null || animationName.equals(currentAnimation.getName()))
            return;
        Animation animation = animations.get(animationName);
        if(animation == null)
            throw new AnimationComponentException("animation \"" + animationName + "\" does not exist");
        this.currentAnimation = animation;
        this.animationEndTime = animation.getKeyFrames().get(animation.getKeyFrames().size() - 1).getTime();
    }

    public void stopAnimation() {
        this.currentAnimation = null;
    }

    @Override
    public List<Class<? extends Component>> getComponentRequirements() {
        return List.of();
    }

    @Override
    public boolean isUnique() {
        return true;
    }
}
