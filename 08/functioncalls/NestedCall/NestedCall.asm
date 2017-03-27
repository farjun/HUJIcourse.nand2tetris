@261
D = A
@SP
M = D
@Sys.init
0;JMP



//function Sys.init 0
(Sys.init)



//call Sys.main 0
@Sys.main$Return0
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
@Sys.main
0;JMP
(Sys.main$Return0)



//pop temp 1
@SP
A = M - 1
D = M
@poped
M = D
@SP
M=M-1
@R5
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



//label LOOP
(LOOP)



//goto LOOP
@LOOP
0;JMP



//function Sys.main 0
(Sys.main)



//push constant 123
@123
D = A
@SP
A = M
M = D
@SP
M=M+1



//call Sys.add12 1
@Sys.add12$Return1
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
@1
D = D - A
@5
D = D - A
@ARG
M = D
@SP
D = M
@LCL
M = D
@Sys.add12
0;JMP
(Sys.add12$Return1)



//pop temp 0
@SP
A = M - 1
D = M
@poped
M = D
@SP
M=M-1
@R5
D = A
@0
D = D + A
@tempAddress
M = D
@poped
D = M
@tempAddress
A = M
M = D



//push constant 246
@246
D = A
@SP
A = M
M = D
@SP
M=M+1



//return
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



//function Sys.add12 3
(Sys.add12)
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
@0
D = A
@SP
A = M
M = D
@SP
M=M+1



//push argument 0
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



//push constant 12
@12
D = A
@SP
A = M
M = D
@SP
M=M+1



//add
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



//return
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
