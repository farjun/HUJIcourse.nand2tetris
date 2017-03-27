// File name: projects/04/Sort.asm

// sorts the given array.
// the address of the array is located in R14 and the kength of it is located in R15.
// sorting algorithm: bubble sort.

@unsorted 	// initiates a flag that helps determine if the array is sorted of not,
M = 1		// 0 means sorted and 1 means unsorted.

(LOOP)
@unsorted
D = M
@END
D; JEQ // Jumps to the end if the array is sorted.

@unsorted
M = 0

@R15
D = M

@R15
M = M - 1 // decreases the size of the array for the next run.

@stepsToGo
M = D - 1 // stepsToGo is a variable to keep track after the steps left for the current run.
D = M
@END
D; JEQ // if stepsToGo is 0 then the algorithm is over and the array is sorted.

@R14
D = M
@currAddress // initiates the array pointer.
M = D

@unsorted 	// initiates a flag that helps determine if the array is sorted of not,
M = 0 		// 0 means sorted and 1 means unsorted.


(STEP)
@currAddress
A = M
D = M // D is the current value
A = A + 1
D = D - M // M is the next value and D is the difference
@NO_SWAMP
D; JGE // if D is not negative then the current value and the following one are sorted.
@unsorted
M = 1 // a swamp was required therefore the array might not be sorted.

// The next line swamps the current value and the next value. 
@currAddress
A = M
D = M // D is the current value
@temp
M = D // temp is the current value
@currAddress
A = M
A = A + 1
D = M // D is the next value
@currAddress
A = M
M = D // the current value is the next value
@temp
D = M // D is temp
@currAddress
A = M
A = A + 1
M = D // The next value is the current value

(NO_SWAMP)

@stepsToGo
M = M - 1
D = M
@LOOP
D; JEQ // if stepsToGo is 0 then this run over the array is over and the smllest element is at the end.

@currAddress
M = M + 1

@STEP
0;JMP

(END)