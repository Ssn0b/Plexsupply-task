# Factorial Calculator

This is a console-based multi-threaded Java program to calculate the factorial for each number from the input file.

## Requirements

- 1 thread reads data from input.txt file
- 1 thread writes the calculation results to the output.txt file in the required format.
- Thread pool N (the size is set in the console) is used for calculating the factorial for each number.
- The reader thread transfers the read data to the thread pool for calculation. After receiving the result, it is transferred from the thread pool to the write stream.
- The program adds throttling to the pool of threads for calculation, allowing a maximum of 100 numbers to be processed in 1 second. This rule applies to all threads for calculation.

## Recommended ThreadPool Size

The ideal thread pool size depends on the hardware resources available and the nature of the tasks being executed. As a general recommendation:

- For a system with 4 CPU cores, a `threadPoolSize` of 4 or a multiple of 4 (e.g., 8, 12) might be appropriate.

## Execution Time Considerations

Due to the added throttling to process a maximum of 100 numbers in 1 second, the program execution might take longer, especially when dealing with a large number of input values.

For programs dealing with a significant number of calculations, be prepared to wait longer than the usual processing time to accommodate the throttling and achieve the desired rate limit.

## Contributing

If you find any issues or have suggestions for improvements, feel free to open an issue or submit a pull request. Your contributions are welcome!

Happy calculating!
