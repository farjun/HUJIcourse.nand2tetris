@261
D = A
@SP
M = D
@Sys.init
0;JMP



//function Class1.set 0
(Class1.set)



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



//pop static 0
@SP
A = M - 1
D = M
@poped
M = D
@SP
M=M-1
@poped
D = M
@Class10
M = D



//push argument 1
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



//pop static 1
@SP
A = M - 1
D = M
@poped
M = D
@SP
M=M-1
@poped
D = M
@Class11
M = D



//push constant 0
@0
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



//function Class1.get 0
(Class1.get)



//push static 0
@Class10
D = M
@SP
A = M
M = D
@SP
M=M+1



//push static 1
@Class11
D = M
@SP
A = M
M = D
@SP
M=M+1



//sub
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



//function Class2.set 0
(Class2.set)



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



//pop static 0
@SP
A = M - 1
D = M
@poped
M = D
@SP
M=M-1
@poped
D = M
@Class20
M = D



//push argument 1
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



//pop static 1
@SP
A = M - 1
D = M
@poped
M = D
@SP
M=M-1
@poped
D = M
@Class21
M = D



//push constant 0
@0
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



//function Class2.get 0
(Class2.get)



//push static 0
@Class20
D = M
@SP
A = M
M = D
@SP
M=M+1



//push static 1
@Class21
D = M
@SP
A = M
M = D
@SP
M=M+1



//sub
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



//push constant 6
@6
D = A
@SP
A = M
M = D
@SP
M=M+1



//push constant 8
@8
D = A
@SP
A = M
M = D
@SP
M=M+1



//call Class1.set 2
@Class1.set$Return0
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
@2
D = D - A
@5
D = D - A
@ARG
M = D
@SP
D = M
@LCL
M = D
@Class1.set
0;JMP
(Class1.set$Return0)



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



//push constant 23
@23
D = A
@SP
A = M
M = D
@SP
M=M+1



//push constant 15
@15
D = A
@SP
A = M
M = D
@SP
M=M+1



//call Class2.set 2
@Class2.set$Return1
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
@2
D = D - A
@5
D = D - A
@ARG
M = D
@SP
D = M
@LCL
M = D
@Class2.set
0;JMP
(Class2.set$Return1)



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



//call Class1.get 0
@Class1.get$Return2
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
@Class1.get
0;JMP
(Class1.get$Return2)



//call Class2.get 0
@Class2.get$Return3
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
@Class2.get
0;JMP
(Class2.get$Return3)



//label WHILE
(WHILE)



//goto WHILE
@WHILE
0;JMP
