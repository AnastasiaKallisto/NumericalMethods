public class MethodMinNevyazok {
    /**
     * A- матрица, b- вектор правых частей, X-вектор результат, eps- точность
     **/
    public static int MinNev(double[][] A, double[] b, double[] X, double eps) {
        int N = A.length;
        int count = 0;//  количество итераций
        double[] Ax;
        double[] Ar;
        double[] r = new double[N];
        double[] TempX = new double[N];
        double maxi = 0.0, Tau = 0.0, TempTau = 0.0;

        for (int i = 0; i < N; i++) {
            TempX[i] = 0;//первое приближение задаём нулевым
        }
        do {
            Ax = MatrixFunctions.multiplyMatrixOnVector(A, TempX);
            for (int i = 0; i < N; i++) {
                r[i] = Ax[i] - b[i];// невязка
            }
            Ar = MatrixFunctions.multiplyMatrixOnVector(A, r);
            Tau = 0.0;
            TempTau = 0.0;
            for (int i = 0; i < N; i++) {
                Tau += Ar[i] * r[i];
                TempTau += Ar[i] * Ar[i];
            }
            Tau = Tau / TempTau;
            for (int i = 0; i < N; i++) {
                X[i] = TempX[i] - Tau * r[i];
            }

            maxi = Math.abs(X[0] - TempX[0]);
            for (int i = 0; i < N; i++) {
                if (Math.abs(X[i] - TempX[i]) > maxi)
                    maxi = Math.abs(X[i] - TempX[i]);
                TempX[i] = X[i];
            }
            count++;
        } while (maxi >= eps);
        return count;
    }

    public static void main(String[] args) {
        int N = 4;
        double[][] a = {{1, -1, 3, 1}, {4, -1, 5, 4}, {2, -2, 4, 1}, {1, -4, 5, -1}};
        double[] b = {5, 4, 6, 3};
        double[] answer = {9, 18, 10, -16};

        double[][] a_t = MatrixFunctions.getTransposedMatrix(a);
        double[][] a_sim = MatrixFunctions.multiplyMatrixOnMatrix(a, a_t);
        double[] X = new double[N];
        double eps = 0.0000000001;
        double[] b_t = MatrixFunctions.multiplyMatrixOnVector(a_t, b);
        int k = MinNev(a_sim, b_t, X, eps);

        System.out.println("a:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(" " + a[i][j]);
            }
            System.out.println();
        }
        System.out.println("b: ");
        for (int i = 0; i < N; i++) {
            System.out.print(b[i] + " ");
        }
        System.out.println();

        System.out.println("a_sim:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(" " + a_sim[i][j]);
            }
            System.out.println();
        }
        System.out.println("b_t: ");
        for (int i = 0; i < N; i++) {
            System.out.print(b_t[i] + " ");
        }
        System.out.println();
        System.out.println("Kol-vo iter: " + k);
        System.out.println("X:");
        for (int i = 0; i < N; i++) {
            System.out.print(X[i] + " ");
        }
    }

    // невязка r_j = A * approx_x_j - f

    // ошибка z_j = approx_x_j - exact_x

    // параметр t_j  = скал произв (A * r_j, r_j) / скал произв (A * r_j, A * r_j)

    // H_j = t_j * E

    // T_j = E - t_j * A

    // approx_x_j+1 = approx_x_j - H_j * r_j

    // либо

    // approx_x_j+1 = T_j * approx_x_j - H_j * f ???
}
