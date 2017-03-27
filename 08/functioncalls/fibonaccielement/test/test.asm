@256
D = A
@SP
M = D
@$Return0
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
($Return0)



//function Main.a 0
(Main.a)



//push constant 1
@1
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



//function Sys.init 0
(Sys.init)



//call Main.a 0
@Sys.init$Return1
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
@Main.a
0;JMP
(Sys.init$Return1)



//label WHILE
(Sys.init$WHILE)



//goto WHILE
@Sys.init$WHILE
0;JMP
