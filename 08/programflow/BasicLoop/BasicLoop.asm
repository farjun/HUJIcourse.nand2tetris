@256
D = A
@SP
M = D
@Sys.init$Return0
D = A
@SP
A = M
M = D
@SP
M=M+1
@LCL
D = M
@SP
A = M
M = D
@SP
M=M+1
@ARG
D = M
@SP
A = M
M = D
@SP
M=M+1
@THIS
D = M
@SP
A = M
M = D
@SP
M=M+1
@THAT
D = M
@SP
A = M
M = D
@SP
M=M+1
@SP
D = M
@0
D = D - A
@5
D = D - A
@ARG
M = D
@SP
D = M
@LCL
M = D
@Sys.init
0;JMP
(Sys.init$Return0)
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
@LCL
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
(LOOP_START)
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
@LCL
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
@LCL
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
@LOOP_START
D;JGT
@LCL
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
