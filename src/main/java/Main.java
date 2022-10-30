public class Main {
    private static int N = 3;
    private static double ALPHA = 1.0;
    private static double BETA = 10.0;


    public static void main(String[] args) {
//        System.out.println("\n\nTable for Generated matrices");
//        Gen general = new Gen();
//        System.out.println("Простая структура");
//        N = 100;
//        System.out.println(N);
//        for (int i = 1; i < 15; i++) {
//            ALPHA = 1;
//            BETA = Math.pow(10, i);
//            approximateX = new double[N];
//            f = new double[N];
//            for (int j = 0; j < N; j++) {
//                approximateX[j] = Math.sin(j);
//                f[j] = (j/ N - 1);
//            }
//            general.mygen(matrix, invertedMatrix, N, ALPHA, BETA, 1, 2, 1, 1); // простая структура
//            exactX = new Gauss(matrix, f).getResult();
//            tableFunctions = new TableFunctions(f, matrix, approximateX, exactX);
//            System.out.println("Simple structure");
//            printString(ALPHA, BETA, N, tableFunctions);
//            general.mygen(matrix, invertedMatrix, N, ALPHA, BETA, 0, 0, 1, 1); // жорданова форма
//            exactX = new Gauss(matrix, f).getResult();
//            tableFunctions = new TableFunctions(f, matrix, approximateX, exactX);
//            System.out.println("Jord form");
//            printString(ALPHA, BETA, N, tableFunctions);
//        }
        /**
         * sign_law принимает значения:
         * -1: какое-то распределение знаков
         * 0: какое-то распределение знаков
         *
         * lambda_law принимает значения:
         * 1: какое-то распределение чисел
         * 2: какое-то распределение чисел
         *
         * variant принимает значения:
         * 0: симметричная матрица
         * 1: матрица простой структуры
         * 2: одна Жорданова клетка 2х2 при минимальном с.з.
         *
         * schema всегда принимает значение 1*/

    }


    public static void printHeadOfTable() {
        /*System.out.println("|  альфа  |  бета  |  размер матрицы  |  норма матрицы  | норма обратной матрицы  " +
                "|  невязка генерации  | норма ошибки  |  относит. норма ошибки  |  норма невязки  |  относ. норма невязки  |");*/

        System.out.println("|     alpha     | beta  |size|  norma matric  |norma obratnoi matrici" +
                "|nevyazka generac| norma ohibki |ornosit. norma oshibki| norma nevyazki |otnos. norma nevyazki|");
    }

    public static void printString(double alpha, double beta, int n, TableFunctions tableFunctions) {
        String str = String.format("|  %e | %5.2f | %d |  %e  |    %e      |  %e  |   %e   |     %e     |    %e    |   %e    |",
                alpha, beta, n,
                tableFunctions.getInverseMatrixNorm(), tableFunctions.getInverseMatrixNorm(),
                tableFunctions.getV_NevyazkaOfGeneration(),
                tableFunctions.getNormOfError_z(), tableFunctions.getJ_OtnosNormOfError(),
                tableFunctions.getNormOfNevyazka_r(), tableFunctions.getP_OtnosNormOfNevyazka());

        System.out.println(str);
    }
}
