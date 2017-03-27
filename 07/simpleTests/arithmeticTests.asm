@256
D = A
@SP
M = D
@5
D = A
@SP
A = M
M = D
@SP
M=M+1
@5
D = A
@SP
A = M
M = D
@SP
M=M+1
@SP
A = M - 1
D = M
@Y
M = D
@SP
M=M-1
@SP
A = M - 1
D = M
@X
M = D
@SP
M=M-1
@X
D = M
@NEG0
D;JLT
@POS0
D;JGT
(EQSGN0)
@Y
D = M
@X
D = M - D
@CONT0
0;JMP
(NEG0)
@Y
D = M
@EQSGN0
D;JLT
D = -1
@CONT0
0;JMP
(POS0)
@Y
D = M
@EQSGN0
D;JGT
D = 1
(CONT0)
@TRUE0
D;JEQ
D=0
@CONTINUE0
0;JMP
(TRUE0)
D = -1
(CONTINUE0)
@SP
A = M
M = D
@SP
M=M+1
