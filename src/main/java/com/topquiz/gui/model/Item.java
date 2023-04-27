package com.topquiz.gui.model;

public class Item<Value1, Value2> {
    private Value1 value1;
    private Value2 value2;

    public Item(Value1 value1, Value2 value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public Value1 getValue1() {
        return value1;
    }

    public void setValue1(Value1 value1) {
        this.value1 = value1;
    }

    public Value2 getValue2() {
        return value2;
    }

    public void setValue2(Value2 value2) {
        this.value2 = value2;
    }
}
