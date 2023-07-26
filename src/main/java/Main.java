import java.io.*;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        int threadPoolSize = readThreadPoolSizeFromConsole();
        ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);

        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {

            String line;
            while ((line = reader.readLine()) != null) {
                int num = parseInput(line.trim());
                if (num >= 0) {
                    Future<BigInteger> futureResult = executor.submit(new FactorialTask(num));
                    try {
                        BigInteger result = futureResult.get();
                        writer.write(num + " = " + result);
                        writer.newLine();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                    Thread.sleep(10);
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

    private static int readThreadPoolSizeFromConsole() {
        Scanner scanner = new Scanner(System.in);
        int threadPoolSize;
        do {
            System.out.print("Enter thread pool size: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Enter an integer: ");
                scanner.next();
            }
            threadPoolSize = scanner.nextInt();
        } while (threadPoolSize <= 0);
        return threadPoolSize;
    }

    private static int parseInput(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static class FactorialTask implements Callable<BigInteger> {
        private final int number;

        public FactorialTask(int number) {
            this.number = number;
        }

        @Override
        public BigInteger call() {
            return calculateFactorial(number);
        }

        private BigInteger calculateFactorial(int n) {
            if (n <= 1)
                return BigInteger.ONE;

            BigInteger result = BigInteger.ONE;
            for (int i = 2; i <= n; i++) {
                result = result.multiply(BigInteger.valueOf(i));
            }
            return result;
        }
    }
}
