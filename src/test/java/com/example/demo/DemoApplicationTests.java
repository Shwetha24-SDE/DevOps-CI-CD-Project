package com.example.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DemoApplicationTests {

    @Test
    public void testHome() {
        String expected = "Hello";
        String actual = "Hello";
        assertEquals(expected, actual);
    }
}