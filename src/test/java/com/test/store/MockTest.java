package com.test.store;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class MockTest {

    @Test
    public void test1() {

        List list = mock(List.class);
        list.add("Red");
        list.add("Blue");
        list.add("Green");


        when(list.get(anyInt())).thenReturn("Black");
        when(list.get(0)).thenReturn("Yellow");

        System.out.println(list);
        System.out.println(list.size());

        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));

        verify(list).add("Red");
        verify(list).add("Blue");
        verify(list).add("Green");
        verify(list).add("Blue");



    }
}
