public class Helper {
    public int[][] fieldCopy(int[][] field) {
        int[][] newField = new int[9][9];
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                newField[y][x] = field[y][x];
            }
        }
        return newField;
    }

    public void printField(int[][] field) {
        System.out.println("-------------------------------");
        int rowCount = 1;
        for (int[] row : field) {
            System.out.print('|');
            int columnCount = 1;
            for (int val : row) {
                String c = "*";
                if (val != 0) {
                    c = Integer.toString(val);
                }
                System.out.print(' ' + c + ' ');
                if (columnCount++ % 3 == 0) {
                    System.out.print('|');
                }
            }
            System.out.println();
            if (rowCount++ % 3 == 0) {
                System.out.println("-------------------------------");
            }
        }
    }
}
