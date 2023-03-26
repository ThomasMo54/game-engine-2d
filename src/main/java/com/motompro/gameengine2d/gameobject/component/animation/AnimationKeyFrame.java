package com.motompro.gameengine2d.gameobject.component.animation;

import com.motompro.gameengine2d.math.Vector2;

public class AnimationKeyFrame {

    private final Vector2 framePos;
    private final double time;

    protected AnimationKeyFrame(Vector2 framePos, double time) {
        this.framePos = framePos;
        this.time = time;
    }

    public Vector2 getFramePos() {
        return framePos;
    }

    public double getTime() {
        return time;
    }
}
