import java.util.HashSet;

public class Solver {
    private boolean isFinished(int[][] field) {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (field[y][x] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public int[][] solve(int[][] field) {
        System.out.println("Automatic solving not fully implemented yet!");
        Helper helper = new Helper();
        Checker checker = new Checker();
        int[][] newField = helper.fieldCopy(field);
        while (!isFinished(newField)) {
            HashSet[][] available = new HashSet[9][9];
            for (int x = 0; x < 9; x++) {
                for (int y = 0; y < 9; y++) {
                    available[y][x] = checker.availabilityPoint(newField, x, y);
                }
            }
            boolean toBreak = false;
            for (int x = 0; x < 9; x++) {
                for (int y = 0; y < 9; y++) {
                    if (newField[y][x] == 0 && available[y][x].size() == 1) {
                        newField[y][x] = (int) available[y][x].toArray()[0];
                        toBreak = true;
                        break;
                    }
                }
                if (toBreak) {
                    break;
                }
            }
        }
        return newField;
    }
}
