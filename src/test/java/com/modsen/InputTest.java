package com.modsen;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.modsen.service.ConverterService;
import com.modsen.service.impl.ConverterServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

public class InputTest {
    static ConverterService converter;

    @BeforeAll
    static void beforeAll() {
        converter = new ConverterServiceImpl();

    }

    @Test
    public void toNoOperationTest() {
        assertEquals("$1,00", converter.convert("$5-$4"));
        assertEquals("$9,00", converter.convert("($5+$4)"));
        assertEquals("9,00р", converter.convert("5р+4р"));
        assertEquals("1,00р", converter.convert("(5р-4р)"));
    }

    @Test
    public void toRublesOperationTest() {
        assertEquals("422,55р", converter.convert("toRubles($5,00)"));
        assertEquals("197,75р", converter.convert("toRubles($2,34)"));
    }

    @Test
    public void toRublesTestWithSum() {
        assertEquals("1039,47р", converter.convert("toRubles($5,2 + $2,1 + $5)"));
    }

    @Test
    public void toDollarsOperationTest() {
        assertEquals("$12,00", converter.convert("toDollars(1000р)"));
        assertEquals("$1,62", converter.convert("toDollars(135,1р)"));
    }

    @Test
    public void toDollarsOperationTestWithSum() {
        assertEquals("$12,00", converter.convert("toDollars(500р+500р)"));
        assertEquals("$1,62", converter.convert("toDollars(35,1р+100р)"));
    }

    @Test
    void expressionsWithMultipleOperations() {
        assertEquals("718,34р", converter.convert("toRubles($5,2 + $2,1 + toDollars(100р))"));
        assertEquals("529,88р", converter.convert("toRubles(toDollars(100р + 5р - toRubles($13,5 - $10)) + $5,2 + $2,1 + toDollars(100р + 5р))"));
    }


}
