@17
D = A
@SP
A = M
M = D
@SP
M=M+1
@17
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
@17
D = A
@SP
A = M
M = D
@SP
M=M+1
@16
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
@NEG1
D;JLT
@POS1
D;JGT
(EQSGN1)
@Y
D = M
@X
D = M - D
@CONT1
0;JMP
(NEG1)
@Y
D = M
@EQSGN1
D;JLT
D = -1
@CONT1
0;JMP
(POS1)
@Y
D = M
@EQSGN1
D;JGT
D = 1
(CONT1)
@TRUE1
D;JEQ
D=0
@CONTINUE1
0;JMP
(TRUE1)
D = -1
(CONTINUE1)
@SP
A = M
M = D
@SP
M=M+1
@16
D = A
@SP
A = M
M = D
@SP
M=M+1
@17
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
@NEG2
D;JLT
@POS2
D;JGT
(EQSGN2)
@Y
D = M
@X
D = M - D
@CONT2
0;JMP
(NEG2)
@Y
D = M
@EQSGN2
D;JLT
D = -1
@CONT2
0;JMP
(POS2)
@Y
D = M
@EQSGN2
D;JGT
D = 1
(CONT2)
@TRUE2
D;JEQ
D=0
@CONTINUE2
0;JMP
(TRUE2)
D = -1
(CONTINUE2)
@SP
A = M
M = D
@SP
M=M+1
@892
D = A
@SP
A = M
M = D
@SP
M=M+1
@891
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
@NEG3
D;JLT
@POS3
D;JGT
(EQSGN3)
@Y
D = M
@X
D = M - D
@CONT3
0;JMP
(NEG3)
@Y
D = M
@EQSGN3
D;JLT
D = -1
@CONT3
0;JMP
(POS3)
@Y
D = M
@EQSGN3
D;JGT
D = 1
(CONT3)
@TRUE3
D;JLT
D=0
@CONTINUE3
0;JMP
(TRUE3)
D = -1
(CONTINUE3)
@SP
A = M
M = D
@SP
M=M+1
@891
D = A
@SP
A = M
M = D
@SP
M=M+1
@892
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
@NEG4
D;JLT
@POS4
D;JGT
(EQSGN4)
@Y
D = M
@X
D = M - D
@CONT4
0;JMP
(NEG4)
@Y
D = M
@EQSGN4
D;JLT
D = -1
@CONT4
0;JMP
(POS4)
@Y
D = M
@EQSGN4
D;JGT
D = 1
(CONT4)
@TRUE4
D;JLT
D=0
@CONTINUE4
0;JMP
(TRUE4)
D = -1
(CONTINUE4)
@SP
A = M
M = D
@SP
M=M+1
@891
D = A
@SP
A = M
M = D
@SP
M=M+1
@891
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
@NEG5
D;JLT
@POS5
D;JGT
(EQSGN5)
@Y
D = M
@X
D = M - D
@CONT5
0;JMP
(NEG5)
@Y
D = M
@EQSGN5
D;JLT
D = -1
@CONT5
0;JMP
(POS5)
@Y
D = M
@EQSGN5
D;JGT
D = 1
(CONT5)
@TRUE5
D;JLT
D=0
@CONTINUE5
0;JMP
(TRUE5)
D = -1
(CONTINUE5)
@SP
A = M
M = D
@SP
M=M+1
@32767
D = A
@SP
A = M
M = D
@SP
M=M+1
@32766
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
@NEG6
D;JLT
@POS6
D;JGT
(EQSGN6)
@Y
D = M
@X
D = M - D
@CONT6
0;JMP
(NEG6)
@Y
D = M
@EQSGN6
D;JLT
D = -1
@CONT6
0;JMP
(POS6)
@Y
D = M
@EQSGN6
D;JGT
D = 1
(CONT6)
@TRUE6
D;JGT
D=0
@CONTINUE6
0;JMP
(TRUE6)
D = -1
(CONTINUE6)
@SP
A = M
M = D
@SP
M=M+1
@32766
D = A
@SP
A = M
M = D
@SP
M=M+1
@32767
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
@NEG7
D;JLT
@POS7
D;JGT
(EQSGN7)
@Y
D = M
@X
D = M - D
@CONT7
0;JMP
(NEG7)
@Y
D = M
@EQSGN7
D;JLT
D = -1
@CONT7
0;JMP
(POS7)
@Y
D = M
@EQSGN7
D;JGT
D = 1
(CONT7)
@TRUE7
D;JGT
D=0
@CONTINUE7
0;JMP
(TRUE7)
D = -1
(CONTINUE7)
@SP
A = M
M = D
@SP
M=M+1
@32766
D = A
@SP
A = M
M = D
@SP
M=M+1
@32766
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
@NEG8
D;JLT
@POS8
D;JGT
(EQSGN8)
@Y
D = M
@X
D = M - D
@CONT8
0;JMP
(NEG8)
@Y
D = M
@EQSGN8
D;JLT
D = -1
@CONT8
0;JMP
(POS8)
@Y
D = M
@EQSGN8
D;JGT
D = 1
(CONT8)
@TRUE8
D;JGT
D=0
@CONTINUE8
0;JMP
(TRUE8)
D = -1
(CONTINUE8)
@SP
A = M
M = D
@SP
M=M+1
@57
D = A
@SP
A = M
M = D
@SP
M=M+1
@31
D = A
@SP
A = M
M = D
@SP
M=M+1
@53
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
@Y
D = M
@X
D = M + D
@SP
A = M
M = D
@SP
M=M+1
@112
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
@Y
D = M
@X
D = M - D
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
@Y
D = M
D = -D
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
@Y
D = M
@X
D = M & D
@SP
A = M
M = D
@SP
M=M+1
@82
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
@Y
D = M
@X
D = M | D
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
@Y
D = M
D = !D
@SP
A = M
M = D
@SP
M=M+1
