public class ReflectionMethod {
    private static final int SIZE = 3;//размерность матрицы
    private static final double ALPHA = 1e-9;//min по модулю собственноу число
    private static final int BETA = 1;// max по модулю собственное число



/*
    void mygen(double[][], double[][], int, double, double, int, int, int, int);


    void myMethod(double[][], double[], double[][], double[]);*/

    public static void obrHod(double[][] A, double[] B, double[] X) {
        //Обратный ход метода Гаусса
        for (int i = SIZE - 1; i >= 0; i--) {
            double sum = 0;
            for (int j = SIZE - 1; j > i; j--) {
                sum += X[j] * A[i][j];
            }
            X[i] = (B[i] - sum) / A[i][i];
        }
    }

    public static void main(String[] args) {

        double alpha = ALPHA;
        double beta = BETA;

        double[][] tryMatrix = new double[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            tryMatrix[i] = new double[SIZE];
        }

        double[][] tryMatrix_inv = new double[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            tryMatrix_inv[i] = new double[SIZE];
        }

        Gen.mygen(tryMatrix, tryMatrix_inv, SIZE, alpha, beta, 1, 2, 0, 1);

        double[] try_B = new double[SIZE];
        for (int i = 0; i < SIZE; i++) {
            try_B[i] = Math.sin(i);
        }

        double[] try_X = new double[SIZE];
        for (int i = 0; i < SIZE; i++) {
            try_X[i] = 0;
            for (int j = 0; j < SIZE; j++) {
                try_X[i] += tryMatrix[i][j] * try_B[j];
            }
        }

        System.out.println("Матрица tryMatr:");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(tryMatrix[i][j] + " ");
            }
            System.out.print("\n");
        }

        System.out.println("Правая ее часть: ");
        for (int i = 0; i < SIZE; i++) {
            System.out.print(try_X[i] + " ");
        }
        System.out.print("\n");


        //исходная матрица
        double[][] A = new double[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            A[i] = new double[SIZE];
        }

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                A[i][j] = 0;
            }
        }

        //матрица обратная
        double[][] A_inv = new double[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            A_inv[i] = new double[SIZE];
        }

        //вектор правой части
        double[] B = new double[SIZE];

        //заполнение матрицы простой структуры
        //mygen(A, A_inv, size, alpha, beta, 1, 2, 1, 1);

        //заполнение матрицы симметричная
        Gen.mygen(A, A_inv, SIZE, alpha, beta, 1, 2, 0, 1);

        //заполнение матрицы жорданова клетка
        //mygen ( A, A_inv, size, alpha, beta, 0, 0, 2, 1 );

	/*
	A[0][0] = 4;
	A[0][1] = 1;
	A[0][2] = 1;

	A[1][0] = 3;
	A[1][1] = 1;
	A[1][2] = 2;

	A[2][0] = 0;
	A[2][1] = 1;
	A[2][2] = -1;

	B[0] = 6;
	B[1] = 6;
	B[2] = 0;
	*/

        //копия исходной матрицы (чтобы исходная не попортилась)
        double[][] A_copy = new double[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            A_copy[i] = new double[SIZE];
        }
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                A_copy[i][j] = A[i][j];
            }
        }

        //копия вектора правой части
        double[] B_copy = new double[SIZE];
        for (int i = 0; i < SIZE; i++) {
            B_copy[i] = Math.sin(i);
            //B_copy[i] = B[i];
        }

        //
        double[] try_X2 = new double[SIZE];
        for (int i = 0; i < SIZE; i++) {
            try_X2[i] = 0;
            for (int j = 0; j < SIZE; j++) {
                try_X2[i] += A_copy[i][j] * B_copy[j];
            }
        }
        //

        //копия исходной обратной матрицы
        double[][] A_inf_copy = new double[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            A_inf_copy[i] = new double[SIZE];
        }
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                A_inf_copy[i][j] = A_inv[i][j];
            }
        }

        //матрица результатов
        double[][] A_res = new double[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            A_res[i] = new double[SIZE];
        }

        //вектор результатов (правая часть после метода отражений)
        double[] B_res = new double[SIZE];

        //вектор Хов - решение СЛАУ
        double[] X = new double[SIZE];

        System.out.println("Система линейных уравнений: ");

        //печать матрицы до применения метода
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(A_copy[i][j] + " ");
            }
            System.out.print("\n");
        }

        System.out.println("Правая часть");

        //печать правой части до применение метода
        for (int i = 0; i < SIZE; i++) {
            System.out.print(B_copy[i] + " ");
        }

        System.out.println("\n\n");
        System.out.println("------------------------------");

        //проба метода, результат в A_copy
        //myMethod(A_copy, B_copy, A_res, B_res);
        myMethod(A_copy, try_X2, A_res, B_res);

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(A_res[i][j] + " ");
            }
            System.out.print("\n");

        }

        System.out.println("Правая часть");

        for (int i = 0; i < SIZE; i++) {
            System.out.print(B_res[i] + " ");
        }

        System.out.print("\n");
        ;

        obrHod(A_res, B_res, X);

        System.out.println("Решение СЛАУ");
        for (int i = 0; i < SIZE; i++) {
            System.out.println("X" + i + " : " + X[i]);
        }
    }

    public static void myMethod(double[][] A, double[] B, double[][] A_res, double[] B_res) {
        //норма вектора s
        double norma_s = 0;

        //знаменатель в расчете w
        double denominator = 0;

        //создание и заполнение копии исходной матрицы
        double[][] A_copy = new double[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            A_copy[i] = new double[SIZE];
        }
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                A_copy[i][j] = A[i][j];
            }
        }

        //создание и заполнение копии исходной правой части
        double[] B_copy = new double[SIZE];
        for (int i = 0; i < SIZE; i++) {
            B_copy[i] = B[i];
        }

        //переменная для пробега по строкам
        int str = 0;

        //транспонированная унитарная матрица

        //матрица отражений TODO:(*1)
        double[][] U = new double[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            U[i] = new double[SIZE];
        }

        for (int q = 0; q < SIZE - 1; q++) {
            //размер на каждом шаге уменьшается и чтбы каждый раз не писать так длинно, сделана эта переменная
            int work_size = SIZE - q;

            //создаем и заполняем рабочую копию СЛАУ
            double[][] A_work = new double[work_size][work_size];
            for (int i = 0; i < work_size; i++) {
                A_work[i] = new double[work_size];
            }
            for (int i = 0; i < work_size; i++) {
                for (int j = 0; j < work_size; j++) {
                    A_work[i][j] = A_copy[q + i][q + j];
                }
            }

            //создаем и заполняем рабочую копию правой части
            double[] B_work = new double[work_size];
            for (int i = 0; i < work_size; i++) {
                B_work[i] = B_copy[q + i];
            }

            //создаем единичную матрицу
            double[][] E = new double[work_size][work_size];
            for (int i = 0; i < work_size; i++) {
                E[i] = new double[work_size];
            }
            for (int i = 0; i < work_size; i++) {
                for (int j = 0; j < work_size; j++) {
                    if (i == j) {
                        E[i][j] = 1;
                    } else {
                        E[i][j] = 0;
                    }
                }
            }

            //создаем и заполняем вектор столбцов
            double[] s = new double[work_size];
            for (int i = 0; i < work_size; i++) {
                s[i] = A_work[i][0];
            }

            //создаем унитарную матрицу
            double[] w = new double[work_size];

            //создаем транспонированную унитарную матрицу
            double[][] wt = new double[SIZE][1];
            wt[0] = new double[work_size];

            //создаем матрицу отражений
//            double[][] U = new double[work_size][work_size];//TODO:разобраться что к чему (*1)
            for (int i = 0; i < work_size; i++) {
                U[i] = new double[work_size];
            }

            //создаем еденичный вектор
            double[] e = new double[work_size];
            e[0] = 1;
            for (int i = 1; i < work_size; i++) {
                e[i] = 0;
            }
            /////////////////////////////////////////блок созданий окончен

            /////////////////////////////////////////работа метода

            //вычисление нормы ||s||
            norma_s = 0;
            for (int i = 0; i < work_size; i++) {
                norma_s += Math.pow(s[i], 2);
            }
            norma_s = Math.sqrt(norma_s);

            //вычисление унитарной матрицы
            /////////////////////////////////////////
            //вычисление знаменателя ||s - ||s|| * e|| - это чило
            denominator = 0;
            double[] buf_e = new double[work_size];
            for (int i = 0; i < work_size; i++) {
                buf_e[i] = e[i] * norma_s;
            }
            double[] buff_s = new double[work_size];
            for (int i = 0; i < work_size; i++) {
                buff_s[i] = s[i] - buf_e[i];
            }
            for (int i = 0; i < work_size; i++) {
                denominator += Math.pow(buff_s[i], 2);
            }
            denominator = Math.sqrt(denominator);

            //вычисление числителя s - ||s|| * e - это вектор
            for (int i = 0; i < work_size; i++) {
                buf_e[i] = e[i] * norma_s;
            }
            for (int i = 0; i < work_size; i++) {
                buff_s[i] = s[i] - buf_e[i];
            }

            //унитарная матрица
            for (int i = 0; i < work_size; i++) {
                w[i] = buff_s[i] / denominator;
            }

            //транспонированная унитарная матрица
            for (int i = 0; i < work_size; i++) {
                wt[0][i] = w[i];
            }
            /////////////////////////////////////////

            //вычисление матрицы отражений U = E - 2*w*wt
            /////////////////////////////////////////
            //создание матрицы произведения
            double[][] multi_ws = new double[SIZE][work_size];
            for (int i = 0; i < work_size; i++) {
                multi_ws[i] = new double[work_size];
            }

            //умножение 2*w*wt - это вектор
            for (int i = 0; i < work_size; i++) {
                for (int j = 0; j < work_size; j++) {
                    multi_ws[i][j] = w[j] * wt[0][i];
                    multi_ws[i][j] *= 2;
                }
            }

            //получение матрицы отражений U = E - multi_ws
            for (int i = 0; i < work_size; i++) {
                for (int j = 0; j < work_size; j++) {
                    U[i][j] = E[i][j] - multi_ws[i][j];
                }
            }
            /////////////////////////////////////////

            //Умножение U*A - матрица
            double[][] multi_UA = new double[SIZE][work_size];
            for (int i = 0; i < work_size; i++) {
                multi_UA[i] = new double[work_size];
            }

            for (int i = 0; i < work_size; i++) {
                for (int j = 0; j < work_size; j++) {
                    multi_UA[i][j] = 0;
                    for (int k = 0; k < work_size; k++) {
                        multi_UA[i][j] += U[i][k] * A_copy[k + q][j + q];
                    }
                }
            }

            //Умножение U*B - вектор
            double[] multi_UB = new double[work_size];

            for (int i = 0; i < work_size; i++) {
                multi_UB[i] = 0;
                for (int j = 0; j < work_size; j++) {
                    multi_UB[i] += U[i][j] * B_copy[j + q];
                }
            }

            //записываем полученные значения в копию исходной матрицы
            for (int i = 0; i < work_size; i++) {
                for (int j = 0; j < work_size; j++) {
                    A_copy[q + i][q + j] = multi_UA[i][j];
                }
            }

            //записываем полученные значения в копию исходного вектора правой части
            for (int i = 0; i < work_size; i++) {
                B_copy[q + i] = multi_UB[i];
            }

            //получаем обратно треугольную матрицу
            /////////////////////////////////////////работа метода окончена

            //выдача полученных результатов
            //обратно треугольная матрица
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    A_res[i][j] = A_copy[i][j];
                }
            }

            //вектор правой части
            for (int i = 0; i < SIZE; i++) {
                B_res[i] = B_copy[i];
            }
        }
    }
}
