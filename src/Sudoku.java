import java.util.ArrayList;
import java.util.Scanner;

public class Sudoku {
    private static void printField(int[][] field) {
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

    private static void play(int[][] fieldParam) {
        Scanner scanner = new Scanner(System.in);
        Helper helper = new Helper();
        Checker checker = new Checker();
        Generator generator = new Generator();
        Solver solver = new Solver();
        int[][] startField = generator.generatePuzzle(fieldParam, 40);
        int[][] field = helper.fieldCopy(startField);
        boolean cont = true;
        while (cont) {
            printField(field);
            System.out.print("Input: ");
            String input = scanner.nextLine();
            if (input.equals("")) {
                System.out.println("Invalid input!");
                continue;
            }
            switch (input) {
                case "exit":
                    cont = false;
                    break;
                case "reset":
                    field = helper.fieldCopy(startField);
                    break;
                case "check":
                    ArrayList<int[]> errors = checker.checkField(field);
                    if (errors.size() != 0) {
                        StringBuilder errOut = new StringBuilder("Errors: ");
                        for (int[] error : errors) {
                            errOut.append("(").append(error[0] + 1).append(", ").append(error[1] + 1).append("), ");
                        }
                        errOut = new StringBuilder(errOut.substring(0, errOut.length() - 2));
                        System.out.println(errOut);
                    } else {
                        System.out.println("No errors found!");
                    }
                    break;
                case "solve":
                    //TODO
                    break;
                case "show":
                    field = helper.fieldCopy(fieldParam);
                    break;
                default:
                    int[] inputValues = new int[3];
                    boolean valid = true;
                    for (int i = 0; i < 3; i++) {
                        inputValues[i] = Character.getNumericValue(input.charAt(i));
                        if (inputValues[i] < 1 || inputValues[i] > 9) {
                            System.out.println("Invalid input!");
                            valid = false;
                            break;
                        }
                    }
                    if (valid) {
                        field[inputValues[1] - 1][inputValues[0] - 1] = inputValues[2];
                    }
            }
        }
    }

    public static void main(String[] args) {
        Generator generator = new Generator();
        int[][] solution = generator.generateSolution();
        play(solution);
    }
}
