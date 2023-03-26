package com.motompro.gameengine2d.gameobject.component.animation;

import com.motompro.gameengine2d.gameobject.component.exception.AnimationComponentException;
import com.motompro.gameengine2d.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class AnimationBuilder {

    private String name;
    private final List<AnimationKeyFrame> keyFrames = new ArrayList<>();
    private boolean loop = false;
    private double loopDelay = 0;

    public AnimationBuilder name(String name) {
        this.name = name;
        return this;
    }

    public AnimationBuilder addKeyFrame(int frameX, int frameY, double time) {
        keyFrames.add(new AnimationKeyFrame(Vector2.of(frameX, frameY), time));
        return this;
    }

    public AnimationBuilder addKeyFrame(Vector2 framePos, double time) {
        keyFrames.add(new AnimationKeyFrame(Vector2.of((int) framePos.getX(), (int) framePos.getY()), time));
        return this;
    }

    public AnimationBuilder loop(boolean loop) {
        this.loop = loop;
        return this;
    }

    public AnimationBuilder loopDelay(double loopDelay) {
        this.loopDelay = loopDelay;
        return this;
    }

    protected Animation build() {
        if(name == null)
            throw new AnimationComponentException("name is null");
        if(keyFrames.isEmpty())
            throw new AnimationComponentException("no keyframe provided");
        return new Animation(name, keyFrames, loop, loopDelay);
    }
}
