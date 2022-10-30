public class MethodMinNevyazok {
    /**
     * A- матрица, b- вектор правых частей, X-вектор результат, eps- точность
     **/
    double[] x;
    int count;

    public static void main(String[] args) {
        double[][] A = {{1000, 5, 3, 1}, {4, 10, 5, 4}, {2, 52, 4, 1}, {1, 4, 5, 0}};
        double[] x = {9, 18, 10, -16};
        double[] b = MatrixFunctions.multiplyMatrixOnVector(A, x);
        double[] answer = new double[4];
        MethodMinNevyazok m = new MethodMinNevyazok(A, b, answer, 1E-3);
        System.out.println(answer[0] + " " + answer[1] + " " + answer[2] + " " + answer[3]);
        System.out.println(x[0] + " " + x[1] + " " + x[2] + " " + x[3]);
    }

    public MethodMinNevyazok(double[][] A, double[] f, double[] x_j, double eps) {
        count = getExactX(A, f, x_j, eps);
    }

    public int getCount(){
        return count;
    }

    public double[] getX(){
        return x;
    }

    public int getExactX(double[][] A, double[] f, double[] x_j, double eps) {
        /*double determinant = MatrixFunctions.calculateDeterminant(A);
        if (Double.compare(determinant ,0) < 1E-15)
            throw new IllegalArgumentException("Determinant is 0, there is no decision");
        if (determinant < 0)
            throw new IllegalArgumentException("Determinant < 0, method can't work");*/
        int n = f.length;
        int count = 0;
        double feps = eps * TableFunctions.calculateCubicNormOfVector(f);
        double[][] E = new double[n][n];
        for (int i = 0; i < n; i++) {
            E[i][i] = 1;
        }
        double[] r_j, x_j_plus_1;
        double t_j;
        while (true) {
            count++;
            r_j = get_r_j(A, x_j, f);
            t_j = get_t_j(A, r_j);
            x_j_plus_1 = get_x_j_plus_1(t_j, x_j, f, A, E);
            if (TableFunctions.calculateCubicNormOfVector(r_j) < feps) {
                break;
            }
            x_j = x_j_plus_1;
        }
        x = x_j;
        System.out.println("Kol-vo iter = " + count);
        return count;
    }

    // параметр t_j  = скал произв (A * r_j, r_j) / скал произв (A * r_j, A * r_j)
    public static double get_t_j(double[][] A, double[] r_j) {
        double[] Ar_j = MatrixFunctions.multiplyMatrixOnVector(A, r_j);
        double numerator = VectorFunctions.getScalarMultV1onV2(Ar_j, r_j);
        double denumerator = VectorFunctions.getScalarMultV1onV2(Ar_j, Ar_j);
        double answer = numerator / denumerator;
        return answer;
    }

    // невязка r_j = A * x_j - f
    public static double[] get_r_j(double[][] A, double[] X_j, double[] f) {
        return TableFunctions.calculateR_Nevyazka(A, X_j, f);
    }

    // x_j+1 = (E - t_j * A) * x_j + t_j * f
    public static double[] get_x_j_plus_1(double t_j, double[] x_j, double[] f, double[][] A, double[][] E) {
        double[][] coef1 = MatrixFunctions.matrixMinusMatrix(E, MatrixFunctions.multiplyMatrixOnNumber(A, t_j));
        double[] x_j_plus_1 = VectorFunctions.V1plusV2(
                MatrixFunctions.multiplyMatrixOnVector(coef1, x_j),
                VectorFunctions.multiplyVectorOnNumber(f, t_j));
        return x_j_plus_1;
    }
}
