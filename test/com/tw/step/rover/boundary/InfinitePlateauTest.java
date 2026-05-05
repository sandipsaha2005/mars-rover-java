package com.tw.step.rover.boundary;

import com.tw.step.rover.position.Coordinate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class InfinitePlateauTest {
    @Test
    void shouldAllowAnyCoordinate() {
        InfinitePlateau plateau = new InfinitePlateau();
        assertTrue(plateau.isWithin(new Coordinate(-10, 25)));
    }
}
