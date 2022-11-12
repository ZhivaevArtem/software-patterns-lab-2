package com.zhivaevartemsaveg;

import java.util.Objects;

public class Ref<T> {
    public Ref(T v) {this.value=  v;}

    public Ref(){}

    public T value = null;

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    protected Object clone() {
        return new Ref<>(value);
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equals(this, obj);
    }

    @Override
    public String toString() {
        return Objects.toString(value);
    }
}
