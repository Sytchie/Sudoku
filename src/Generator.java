import java.util.ArrayList;
import java.util.Random;

public class Generator {
    private int[][] removeNumber(int[][] field, int n) {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (field[y][x] == n) {
                    field[y][x] = 0;
                }
            }
        }
        return field;
    }

    public int[][] generateSolution() {
        Random random = new Random();
        Checker checker = new Checker();
        int[][] field = new int[9][9];
        long startTime = System.currentTimeMillis();
        for (int n = 1; n < 10; n++) {
            if (System.currentTimeMillis() - startTime > 50) {
                n = 1;
                startTime = System.currentTimeMillis();
            }
            int repeatCount = 0;
            for (int squareX = 0; squareX < 3; squareX++) {
                for (int squareY = 0; squareY < 3; squareY++) {
                    ArrayList<Integer> available = new ArrayList<>();
                    for (int i = 0; i < 9; i++) {
                        available.add(i);
                    }
                    boolean cont = false;
                    do {
                        if (available.size() == 0) {
                            field = removeNumber(field, n);
                            available = new ArrayList<>();
                            for (int i = 0; i < 9; i++) {
                                available.add(i);
                            }
                            squareX = 0;
                            squareY = 0;
                            if (++repeatCount == 10) {
                                n--;
                            }
                        }
                        int r = available.get(random.nextInt(available.size()));
                        available.remove(new Integer(r));
                        int x = squareX * 3 + r % 3;
                        int y = squareY * 3 + r / 3;
                        if (field[y][x] == 0 && checker.checkPoint(field, x, y, n)) {
                            field[y][x] = n;
                            cont = true;
                        }
                    } while (!cont);
                }
            }
        }
        return field;
    }

    public int[][] generatePuzzle(int[][] fieldParam, int startNumberCount) {
        Random random = new Random();
        Helper helper = new Helper();
        int[][] field = helper.fieldCopy(fieldParam);
        for (int i = 0; i < 81 - startNumberCount; i++) {
            int x = random.nextInt(9);
            int y = random.nextInt(9);
            if (field[y][x] != 0) {
                field[y][x] = 0;
            } else {
                i--;
            }
        }
        return field;
    }
}
