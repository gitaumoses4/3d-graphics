package com.graphics.utils;

import java.lang.reflect.Array;

public class ArrayStream {
    private float[][] items;

    public ArrayStream(float[][] items) {
        this.items = items;
    }

    static ArrayStream of(float[][] items) {
        return new ArrayStream(items);
    }

    void read(ReadListener listener) {
        if (items != null) {
            for (int i = 0; i < items.length; i++) {
                for (int j = 0; j < items[i].length; j++) {
                    listener.accept(i, j, items[i][j]);
                }
            }
        }
    }

    ArrayStream write(float[][] to, WriteListener listener) {
        if (items != null) {
            for (int i = 0; i < items.length; i++) {
                for (int j = 0; j < items[i].length; j++) {
                    to[i][j] = listener.modify(i, j, items[i][j]);
                }
            }
        }
        return this;
    }

    ArrayStream write(WriteListener listener) {
        return this.write(this.items, listener);
    }


    float[][] get() {
        return this.items;
    }

    public static Float[] box(float... f) {
        Float[] result = new Float[f.length];
        for (int i = 0; i < f.length; i++) {
            result[i] = f[i];
        }
        return result;
    }

    public static Float[][] box(float[]... f) {
        Float[][] result = new Float[f.length][];
        for (int i = 0; i < f.length; i++) {
            result[i] = box(f[i]);
        }
        return result;
    }


    public interface ReadListener {
        void accept(int i, int j, float value);
    }

    public interface WriteListener {
        float modify(int i, int j, float value);
    }
}
