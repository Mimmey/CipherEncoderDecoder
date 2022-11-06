package org.mimmey.cryptolab1.ellipticcurves;

import org.junit.jupiter.api.Test;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.operations.EllipticCurvesLowLevelOperations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LowLevelOperationsTest {

    @Test
    public void checkFindAllNumbersInString() {
        String str = "1524,848jrejenvjevjernc88r;mekrnv0krmeklr1";

        List<Integer> found = EllipticCurvesLowLevelOperations.findAllNumbersInString(str);

        List<Integer> expected = List.of(1524, 848, 88, 0, 1);
        assertEquals(expected, found);
    }
}
