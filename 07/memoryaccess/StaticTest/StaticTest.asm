
@256
D = A
@SP
M = D
@111
D = A
@SP
A = M
M = D
@SP
M=M+1
@333
D = A
@SP
A = M
M = D
@SP
M=M+1
@888
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
@poped
D = M
@StaticTest.8
M = D


@SP
A = M - 1
D = M
@poped
M = D
@SP
M=M-1
@poped
D = M
@StaticTest.3
M = D


@SP
A = M - 1
D = M
@poped
M = D
@SP
M=M-1
@poped
D = M
@StaticTest.1
M = D

@StaticTest.3
D = M
@SP
A = M
M = D
@SP
M=M+1

@StaticTest.1
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

@StaticTest.8
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
