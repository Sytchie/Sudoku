import java.util.ArrayList;
import java.util.HashSet;

public class Checker {
    private HashSet<Integer> availabilitySquare(int[][] field, int x, int y) {
        ArrayList<Integer> available = new ArrayList<>();
        for (int a = 1; a < 10; a++) {
            available.add(a);
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int nx = x / 3 * 3 + i;
                int ny = y / 3 * 3 + j;
                available.remove(new Integer(field[ny][nx]));
            }
        }
        return new HashSet<>(available);
    }

    private HashSet<Integer> availabilityColumn(int[][] field, int x) {
        ArrayList<Integer> available = new ArrayList<>();
        for (int a = 1; a < 10; a++) {
            available.add(a);
        }
        for (int y = 0; y < 9; y++) {
            available.remove(new Integer(field[y][x]));
        }
        return new HashSet<>(available);
    }

    private HashSet<Integer> availabilityRow(int[][] field, int y) {
        ArrayList<Integer> available = new ArrayList<>();
        for (int a = 1; a < 10; a++) {
            available.add(a);
        }
        for (int x = 0; x < 9; x++) {
            available.remove(new Integer(field[y][x]));
        }
        return new HashSet<>(available);
    }

    public boolean checkPoint(int[][] fieldParam, int x, int y, int n) {
        Helper helper = new Helper();
        int[][] field = helper.fieldCopy(fieldParam);
        field[y][x] = 0;
        HashSet<Integer> availableSquare = availabilitySquare(field, x, y);
        HashSet<Integer> availableColumn = availabilityColumn(field, x);
        HashSet<Integer> availableRow = availabilityRow(field, y);
        availableSquare.retainAll(availableRow);
        availableSquare.retainAll(availableColumn);
        return availableSquare.contains(n);
    }

    public ArrayList<int[]> checkField(int[][] field) {
        ArrayList<int[]> errors = new ArrayList<>();
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (field[y][x] != 0 && !checkPoint(field, x, y, field[y][x])) {
                    errors.add(new int[]{x, y});
                }
            }
        }
        return errors;
    }
}
