package com.pixelutilitys;


public enum BlockRotation {
    Normal(0),
    CW(1),
    CCW(3),
    Rotate180(2);

    public int metadata;

    private BlockRotation(int metadata) {
        this.metadata = metadata;
    }

    public static BlockRotation getRotationFromMetadata(int metadata) {
        for (BlockRotation b : values())
            if (b.metadata == metadata)
                return b;
        return null;
    }
}
