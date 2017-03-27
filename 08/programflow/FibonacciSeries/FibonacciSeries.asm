@256
D = A
@SP
M = D
@ARG
D = M
@1
D = D + A
A = D
D = M
@SP
A = M
M = D
@SP
M=M+1
@SP
A = M - 1
D = M
@poped
M = D
@SP
M=M-1
@R3
D = A
@1
D = D + A
@tempAddress
M = D
@poped
D = M
@tempAddress
A = M
M = D
@0
D = A
@SP
A = M
M = D
@SP
M=M+1
@SP
A = M - 1
D = M
@poped
M = D
@SP
M=M-1
@THAT
D = M
@0
D = D + A
@tempAddress
M = D
@poped
D = M
@tempAddress
A = M
M = D
@1
D = A
@SP
A = M
M = D
@SP
M=M+1
@SP
A = M - 1
D = M
@poped
M = D
@SP
M=M-1
@THAT
D = M
@1
D = D + A
@tempAddress
M = D
@poped
D = M
@tempAddress
A = M
M = D
@ARG
D = M
@0
D = D + A
A = D
D = M
@SP
A = M
M = D
@SP
M=M+1
@2
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
@poped
M = D
@SP
M=M-1
@ARG
D = M
@0
D = D + A
@tempAddress
M = D
@poped
D = M
@tempAddress
A = M
M = D
(MAIN_LOOP_START)
@ARG
D = M
@0
D = D + A
A = D
D = M
@SP
A = M
M = D
@SP
M=M+1
@SP
A = M - 1
D = M
@TruthVal
M = D
@SP
M=M-1
@TruthVal
D = M
@COMPUTE_ELEMENT
D;JGT
@END_PROGRAM
0;JMP
(COMPUTE_ELEMENT)
@THAT
D = M
@0
D = D + A
A = D
D = M
@SP
A = M
M = D
@SP
M=M+1
@THAT
D = M
@1
D = D + A
A = D
D = M
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
@SP
A = M - 1
D = M
@poped
M = D
@SP
M=M-1
@THAT
D = M
@2
D = D + A
@tempAddress
M = D
@poped
D = M
@tempAddress
A = M
M = D
@R3
D = A
@1
D = D + A
A = D
D = M
@SP
A = M
M = D
@SP
M=M+1
@1
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
@SP
A = M - 1
D = M
@poped
M = D
@SP
M=M-1
@R3
D = A
@1
D = D + A
@tempAddress
M = D
@poped
D = M
@tempAddress
A = M
M = D
@ARG
D = M
@0
D = D + A
A = D
D = M
@SP
A = M
M = D
@SP
M=M+1
@1
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
@poped
M = D
@SP
M=M-1
@ARG
D = M
@0
D = D + A
@tempAddress
M = D
@poped
D = M
@tempAddress
A = M
M = D
@MAIN_LOOP_START
0;JMP
(END_PROGRAM)
