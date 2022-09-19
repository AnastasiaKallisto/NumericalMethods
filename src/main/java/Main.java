public class Main {
    private static final int N = 100;
    private static final double ALPHA = 1E-3;
    private static final double BETA = 1.0;


    public static void main(String[] args) {
        // создаем матрицу
        double[][] matrix = {{3, 2, -5}, {2, -1, 3}, {1, 2, -1}};
        double[] f = {-1, 13, 9};
        double[] exactX = {3, 5, 4};
        double[] approximateX = {3.5, 4.6, 4};
        TableFunctions tableFunctions = new TableFunctions(f, matrix, approximateX, exactX);
        printHeadOfTable();
        printString(ALPHA, BETA, N, tableFunctions);

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

        System.out.println("| alpha | beta |matrix size| norma matric |norma obratnoi matrici" +
                "|nevyazka generac| norma ohibki |ornosit. norma oshibki| norma nevyazki |otnos. norma nevyazki|");
        /*for (int i = 0; i < 160; i++) {
            System.out.print("=");
        }
        System.out.println();*/
    }

    public static void printString(double alpha, double beta, int n, TableFunctions tableFunctions) {
        String str = String.format("|  %2.2f | %3.2f |  %5d    |  %2.8f  |    %4.12f    |   %2.8f   |   %2.6f   |     %2.10f     |    %f    |   %4.12f    |",
                alpha, beta, n,
                tableFunctions.getInverseMatrixNorm(), tableFunctions.getInverseMatrixNorm(),
                tableFunctions.getV_NevyazkaOfGeneration(),
                tableFunctions.getNormOfError_z(), tableFunctions.getJ_OtnosNormOfError(),
                tableFunctions.getNormOfNevyazka_r(), tableFunctions.getP_OtnosNormOfNevyazka());

        System.out.println(str);
        /**System.out.println("|" + alpha + "|" +
         beta + "|" +
         n + "|" +
         tableFunctions.getMatrixNorm() + "|" +
         tableFunctions.getInverseMatrixNorm() + "|" +
         tableFunctions.getV_NevyazkaOfGeneration() + "|" +
         tableFunctions.getNormOfError_z() + "|" +
         tableFunctions.getP_OtnosNormOfNevyazka() + "|" +
         tableFunctions.getNormOfNevyazka_r() + "|" +
         tableFunctions.getP_OtnosNormOfNevyazka() + "|");
         */
    }
}
