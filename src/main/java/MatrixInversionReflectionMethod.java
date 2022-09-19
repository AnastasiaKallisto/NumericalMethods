import java.util.ArrayList;
import java.util.List;

// поиск обратной матрицы методом отражений
public class MatrixInversionReflectionMethod {
    private List<double[]> e1;
    private List<double[]> a1;
    private List<double[][]> U_i;
    private double[][] L;
    private double[][] L_Inversion;
    private double[][] A_i;
    private double[][] A_Inversion; // ответ

    public MatrixInversionReflectionMethod(double[][] matrix) {
        A_i = matrix;
        int n = matrix.length;
        e1 = new ArrayList<>(n + 1);
        a1 = new ArrayList<>(n);
        U_i = new ArrayList<>(n);
        a1.set(0, matrix[0]);
        L = matrix;
        double[][] U_multiplier = null; // множитель, содержащий в себе U_(n-1)* U_(n-2)*...*U_1

        for (int i = 1; i < n; i++) {
            double[] e1_array = new double[n - i + 1];
            e1_array[0] = 1; // а остальные нули
            e1.set(n - i + 1, e1_array); // размерность соответствует порядковому номеру

            double[] x_i_plus = calculateX_i(i, A_i.length);
            double[] x_i_minus = VectorFunctions.multiplyVectorOnNumber(x_i_plus, -1);
            double[][] U_x_i = calculateExactU_x_i(x_i_plus,x_i_minus,i);
            U_i.set(i, calculateU_i(i, U_x_i, n));

            if (i == 1){
                U_multiplier = U_i.get(i);
            } else {
                U_multiplier = MatrixFunctions.multiplyMatrixOnMatrix(U_i.get(i), U_multiplier);
            }
            A_i = MatrixFunctions.multiplyMatrixOnMatrix(U_x_i, A_i); // на месте элемента 1 1 должна стоять норма a_1, снизу нули
            A_i = MatrixFunctions.getMinorOfMatrix(0,0,A_i);

            L = MatrixFunctions.multiplyMatrixOnMatrix(U_i.get(i), L);
        }
        L_Inversion = calculateLInversion(L);
        A_Inversion = MatrixFunctions.multiplyMatrixOnMatrix(L_Inversion, U_multiplier);
    }

    public double[][] getA_Inversion() {
        return A_Inversion;
    }

    // определение точной матрицы U(x^(i)) (рассмотрение варианта x^(i) и x^(i) умнож. на - 1)
    // НАПИСАНО
    public double[][] calculateExactU_x_i(double[] x_i_plus, double[] x_i_minus, int i) {
        double[] right_part = e1.get(A_i.length - i + 1);
        double[][] U_x_i_plus = calculateU_x_i(x_i_plus);
        double[][] U_x_i_minus = calculateU_x_i(x_i_minus);

        right_part = VectorFunctions.multiplyVectorOnNumber(right_part, TableFunctions.calculateCubicNormOfVector(a1.get(i - 1)));
        double[] left_part_x_i_plus = MatrixFunctions.multiplyMatrixOnVector(U_x_i_plus, a1.get(i-1));
        double[] left_part_x_i_minus = MatrixFunctions.multiplyMatrixOnVector(U_x_i_minus, a1.get(i-1));

        if (left_part_x_i_plus.equals(right_part)){
            return U_x_i_plus;
        }
        if (left_part_x_i_minus.equals(right_part)){
            return U_x_i_minus;
        }
        throw new ArithmeticException("хуета");
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

    // поиск матрицы отражения U_x_i (от вектора x_i)
    // НАПИСАНО
    public double[][] calculateU_x_i(double[] x_i) {
        int n = x_i.length;
        double[][] U_x_i = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                U_x_i[i][j] = -2 * x_i[i] * x_i[j];
                // нет смысла вызывать сначала тензорное произведение а потом еще раз гнать цикл n^2
                if (i == j) {
                    U_x_i[i][j] += 1;
                }
            }
        }
        return U_x_i;
    }

    //поиск U_i
    // НАПИСАНО
    public double[][] calculateU_i(int i, double[][] U_x_i, int n) {
        // n - размер исходной матрицы A
        double[][] answer = new double[n][n];
        for (int j = 0; j < i - 1; j++) {
            answer[j][j] = 1;
        }
        for (int j = i - 1; j < n; j++) {
            for (int k = i - 1; k < n; k++) {
                answer[j][k] = U_x_i[j - i + 1][k - i + 1];
            }
        }
        return answer;
    }

    // поиск x^(i) со знаком +
    // НАПИСАНО
    public double[] calculateX_i(int i, int n) {
        double[] a1_actual = a1.get(i - 1);
        double a1_norm = TableFunctions.calculateCubicNormOfVector(a1_actual);
        double[] e1_actual = e1.get(n - i + 1); // получаем вектор размерностью n - i + 1
        double[] a1_norm_multyply_e1 = VectorFunctions.multiplyVectorOnNumber(e1_actual, a1_norm);
        double[] numerator = VectorFunctions.V1minusV2(a1_actual, a1_norm_multyply_e1);
        double divider = TableFunctions.calculateCubicNormOfVector(numerator);
        double[] answer = VectorFunctions.multiplyVectorOnNumber(numerator, 1.0 / divider);
        return answer;
    }

    // получение обратной матрицы L^(-1) к верхнетреугольной матрице L
    public double[][] calculateLInversion(double[][] L) {
        return MatrixFunctions.calculateInverseMatrix(L);
    }


}
