Write a program that will convert a bag of **positive integers** and a target number (also an **integer**) into an expression that evaluates to the target number involving each integer **at most once**. The  
expression may use **addition, subtraction and multiplication**, and all **intermediate results must be  
positive** integers. Where such an expression cannot be formed, your program should handle this  
appropriately. If there is more than one correct solution for a particular input, it is sufficient for  
your program to generate one of these.

For example, given the integers 2, 3, 5, 6 and the target number 42, the program could return the expression (2 + 5) * 6.

**------------------------------------------**

Based on "The Countdown Problem, J. Func. Prog. (2002), 12 (6): pp 609-616" (see also http://www.cs.nott.ac.uk/~gmh/countdown.hs)
Brute force solution, possible improvements:
* use target number factorization as heuristic
* parallelize it 
* the best but ideologically different -- https://www.cs.ox.ac.uk/admissions/undergraduate/courses/softwaredemo/solver.txt

To run, type `sbt "run-main playground.countdown.Countdown"` or check tests.