public class Main {

    public static void main(String[] args) {
        printHeadOfTable();
        // создаем матрицу
        double[][] matrix = {{3,2,-5},{2,-1,3},{1,2,-1}};
        double[] f = {-1,13,9};
        double[] exactX = {3,5,4};
        double[] approximateX = {3.5, 4.6, 4};
        TableFunctions tableFunctions = new TableFunctions(f, matrix, approximateX, exactX);

    }



    public static void printHeadOfTable(){
        System.out.println("|  альфа  |  бета  |  размер матрицы  |  норма матрицы  | норма обратной матрицы  " +
                "|  невязка генерации  | норма ошибки  |  относит. норма ошибки  |  норма невязки  |  относ. норма невязки  |");
    }

    public static void printString(int alpha, int beta, int n, TableFunctions tableFunctions){


    }
}
