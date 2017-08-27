Write a function that will return the **longest common (discontiguous) subsequence** of two Strings.

For example, given "AABACDA" and "DACBBCAD", it can return "ABCD". If there is more than one correct solution for a particular input, it is sufficient for your program to return one of these.
 
**------------------------------------------**

Since there are no additional constraints on input data, "classical" dynamic programming solution (O(length1 * length2) time / memory complexity) is used. For example, for "almost identical" strings exists algorithm with time complexity O(max_length * edit_difference).

To run, type `sbt "run-main playground.lcs.LCS"` or check tests.