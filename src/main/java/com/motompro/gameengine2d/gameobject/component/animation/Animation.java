package com.motompro.gameengine2d.gameobject.component.animation;

import java.awt.image.BufferedImage;
import java.util.List;

public class Animation {

    private final String name;
    private final List<AnimationKeyFrame> keyFrames;
    private final boolean loop;
    private final double loopDelay;
    private List<BufferedImage> sprites;

    protected Animation(String name, List<AnimationKeyFrame> keyFrames, boolean loop, double loopDelay) {
        this.name = name;
        this.keyFrames = keyFrames;
        keyFrames.sort((key1, key2) -> {
            if(key1.getTime() == key2.getTime())
                return 0;
            return (key1.getTime() > key2.getTime()) ? 1 : -1;
        });
        this.loop = loop;
        this.loopDelay = loopDelay;
    }

    public String getName() {
        return name;
    }

    public List<AnimationKeyFrame> getKeyFrames() {
        return keyFrames;
    }

    public boolean doLoop() {
        return loop;
    }

    public double getLoopDelay() {
        return loopDelay;
    }

    public List<BufferedImage> getSprites() {
        return sprites;
    }

    public void setSprites(List<BufferedImage> sprites) {
        this.sprites = sprites;
    }

    public BufferedImage getCurrentImage(double time) {
        for(int i = 0; i < keyFrames.size(); i++) {
            if(i == keyFrames.size() - 1)
                return sprites.get(sprites.size() - 1);
            if(time >= keyFrames.get(i).getTime() && time < keyFrames.get(i + 1).getTime())
                return sprites.get(i);
        }
        return sprites.get(0);
    }

    public static AnimationBuilder builder() {
        return new AnimationBuilder();
    }
}
