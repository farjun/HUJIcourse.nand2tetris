@261
D = A
@SP
M = D
@Sys.init
0;JMP
(SimpleFunction.test)
@0
D = A
@SP
A = M
M = D
@SP
M=M+1
@0
D = A
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
@LCL
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
@LCL
D = M
@FRAME
M = D
@FRAME
D = M
@5
D = D- A
A = D
D = M
@RET
M = D
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
D = D + 1
@SP
M = D
@FRAME
D = M
@4
D = D- A
A = D
D = M
@LCL
M = D
@FRAME
D = M
@3
D = D- A
A = D
D = M
@ARG
M = D
@FRAME
D = M
@2
D = D- A
A = D
D = M
@THIS
M = D
@FRAME
D = M
@1
D = D- A
A = D
D = M
@THAT
M = D
@RET
A = M
0;JMP
