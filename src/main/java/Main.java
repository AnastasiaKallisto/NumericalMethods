public class Main {
    private static int N = 3;
    private static double ALPHA = 1.0;
    private static double BETA = 10.0;


    public static void main(String[] args) {
//        System.out.println("\n\nTable for Generated matrices");
//        Gen general = new Gen();
//        System.out.println("������� ���������");
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
//            general.mygen(matrix, invertedMatrix, N, ALPHA, BETA, 1, 2, 1, 1); // ������� ���������
//            exactX = new Gauss(matrix, f).getResult();
//            tableFunctions = new TableFunctions(f, matrix, approximateX, exactX);
//            System.out.println("Simple structure");
//            printString(ALPHA, BETA, N, tableFunctions);
//            general.mygen(matrix, invertedMatrix, N, ALPHA, BETA, 0, 0, 1, 1); // ��������� �����
//            exactX = new Gauss(matrix, f).getResult();
//            tableFunctions = new TableFunctions(f, matrix, approximateX, exactX);
//            System.out.println("Jord form");
//            printString(ALPHA, BETA, N, tableFunctions);
//        }
        /**
         * sign_law ��������� ��������:
         * -1: �����-�� ������������� ������
         * 0: �����-�� ������������� ������
         *
         * lambda_law ��������� ��������:
         * 1: �����-�� ������������� �����
         * 2: �����-�� ������������� �����
         *
         * variant ��������� ��������:
         * 0: ������������ �������
         * 1: ������� ������� ���������
         * 2: ���� ��������� ������ 2�2 ��� ����������� �.�.
         *
         * schema ������ ��������� �������� 1*/

    }


    public static void printHeadOfTable() {
        /*System.out.println("|  �����  |  ����  |  ������ �������  |  ����� �������  | ����� �������� �������  " +
                "|  ������� ���������  | ����� ������  |  �������. ����� ������  |  ����� �������  |  �����. ����� �������  |");*/

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
