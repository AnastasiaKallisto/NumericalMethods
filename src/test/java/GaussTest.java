import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GaussTest {
    @Test
    public void simpleTest() {
        double[] solutions = {-1, 13, 9};
        double[][] matrix = {{3, 2, -5}, {2, -1, 3}, {1, 2, -1}};
        double[] result = {3, 5, 4};

        Gauss gauss = new Gauss(matrix, solutions);
        double[] res = gauss.getResult();
        assertArrayEquals(result, res, 1E-6);
    }

    @Test
    public void simpleTest2() {

        double[] solutions = {1, 2, 0};
        double[][] matrix = {{4, 2, -1}, {5, 3, -2}, {3, 2, -3}};
        double[] result = {-1, 3, 1};

        Gauss gauss = new Gauss(matrix, solutions);
        double[] res = gauss.getResult();

        assertArrayEquals(result, res, 1E-6);
    }
}
