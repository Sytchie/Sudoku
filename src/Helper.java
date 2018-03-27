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
}
