import java.util.ArrayList;
import java.util.Scanner;

public class Sudoku {
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
            helper.printField(field);
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
                case "solve":
                    cont = false;
                    long startTime = System.currentTimeMillis();
                    field = solver.solve(field);
                    System.out.println("Solving took " + (System.currentTimeMillis() - startTime) + "ms");
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
                    if (!cont) {
                        helper.printField(field);
                    }
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
