// File name: projects/04/divide/Divide.asm

// Divides 2 integers.
// The program input will be at R13,R14 while the result R13/R14 will
// be store at R15.
// The remainder should be discarded.
// Assumption: The remainder is discarded.
@R13
D=M
@n
M=D // get n

@R14
D=M
@m
M=D // get m

@c
M=1 // initiate a counter c

(LOOP1)
@m
D=M
@n
D=M-D
@NEXT
D;JLT //jump if m>n

@c
M=M<< // c*=2
@m
M=M<< // m*=2
@LOOP1
0;JMP

(NEXT)
@res
M=0 // initiates a variable for the result

(LOOP2)
@c
D=M-1
@END
D;JEQ //jump if c==1

@c
M=M>> // c/=2
@m
M=M>> // m/=2

@m
D=M
@n
D=M-D
@LOOP2
D;JLT //jump if m>n

@c
D=M
@res
M=M+D // res += c

@m
D=M
@n
M=M-D // n -= m

@LOOP2
0;JMP

(END)
@res
D=M
@R15
M=D
