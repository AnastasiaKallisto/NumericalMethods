import java.util.ArrayList;
import java.util.List;

public class MatrixInversionReflectionMethod {
    private List<double[]> e1;
    private List<double[]> a1;
    private List<double[][]> U_i;
    private double[][] L;
    private double[][] L_Inversion;
    private double[][] A;
    private double[][] A_Inversion;

    public MatrixInversionReflectionMethod(double[][] matrix) {
        A = MatrixFunctions.getCopyOfMatrix(matrix);
        int n = A.length;
        e1 = new ArrayList<>(n + 1);
        a1 = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            double[] e1_array = new double[n - i + 1];
            e1_array[0] = 1; // а остальные нули
            e1.set(i, e1_array);
            // а вот элементы a1 так просто не посчитать, они будут потихоньку получаться...
        }
        a1.set(0, A[0]);
    }

    // тензорное произведение векторов, v2 как будто строка, v1 как столбец
    // НАПИСАНО
    public double[][] calculateTensorMultiplicationOfVectors(double[] v1, double[] v2) {
        int n = v1.length;
        int m = v2.length;
        double[][] answer = new double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                answer[i][j] = v1[i] * v2[j];
            }
        }
        return answer;
    }

    // поиск матрицы отражения U_w (от вектора w)
    // НАПИСАНО
    public double[][] calculateU_w(double[] w) {
        int n = w.length;
        double[][] U_w = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                U_w[i][j] = -2 * w[i] * w[j];
                // нет смысла вызывать сначала тензорное произведение а потом еще раз гнать цикл n^2
                if (i == j) {
                    U_w[i][j] += 1;
                }
            }
        }
        return U_w;
    }

    //поиск U_i
    // НАПИСАНО
    public double[][] calculateU_i(int i, double[][] U_w, int n) {
        // n - размер исходной матрицы A
        double[][] answer = new double[n][n];
        for (int j = 0; j < i - 1; j++) {
            answer[j][j] = 1;
        }
        for (int j = i - 1; j < n; j++) {
            for (int k = i - 1; k < n; k++) {
                answer[j][k] = U_w[j - i + 1][k - i + 1];
            }
        }
        return answer;
    }

    // поиск x^(i)
    public double[] calculateX_i(int i, int n) {
        double[] a1_actual = a1.get(i - 1);
        double a1_norm = TableFunctions.calculateCubicNormOfVector(a1_actual);
        double[] e1_actual = e1.get(i); // получаем вектор размерностью n - i + 1
        double[] a1_norm_multyply_e1 = VectorFunctions.multiplyVectorOnNumber(e1_actual, a1_norm);
        double[] numerator = VectorFunctions.V1minusV2(a1_actual, a1_norm_multyply_e1);
        double divider = TableFunctions.calculateCubicNormOfVector(numerator);
        double[] answer = VectorFunctions.multiplyVectorOnNumber(numerator, 1.0 / divider);
        return answer;
    }


    // нужно рекурсивное вычисление всех x^(i) с помощью a_1^(i-1), U(x^(i)), U_i,
    // public static void function(double[][] matrix)


    // public static double[][] getA_next(double[][] U_w, double[][] A)

    // получение обратной матрицы L^(-1) к верхнетреугольной матрице L
    public static double[][] getLInversion(double[][] L) {
        // получение описано тут https://studfile.net/preview/5793013/
        return new double[L.length][L.length];
    }


}
