package org.example;

public class Gen<T> {
    private T defaultValue;

    public Gen(T defaultValue) {
        this.defaultValue = defaultValue;
    }

    public T getValue(T value) {
        if (value == null) {
            return defaultValue;
        }
        return value;
    }
}
