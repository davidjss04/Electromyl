package com.tifasz.solution;

public interface IController {
    default boolean save() {
        return false;
    }
}
