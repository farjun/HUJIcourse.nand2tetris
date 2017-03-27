// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input.
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel;
// the screen should remain fully black as long as the key is pressed. 
// When no key is pressed, the program clears the screen, i.e. writes
// "white" in every pixel;
// the screen should remain fully clear as long as no key is pressed.


@8191
D = A
@MAX_LOOPS
M = D // MAX_LOOPS = 8191
@COLOR
M = 0

(WAIT)
@KBD
D = M // check what is the status of the keyboard.
@WHITE
D;JEQ // go to WHITE if the value is 0
@COLOR
D = M
@WAIT
D;JLT // if the color is lesser then 0 then the screen is already black and no need to redye.
@COLOR
M = -1 // set the color to 1 and dye the screen.
@DYE
0;JMP 

(WHITE)
@COLOR
D = M
@WAIT
D;JEQ // if the color is 0 then the screen is already white and no need to redye.
@COLOR
M = 0 // set the color to 0 and dye the screen.
@DYE
0;JMP

(DYE)
@i
M = 0 // i = 0

@SCREEN
D = A
@SCREEN_POS
M = D // SCREEN POSITION IS THE CURRENT LOCATION ON THE SCREEN

(LOOP) 
@i
D = M
@MAX_LOOPS
D = D - M // while i <= MAX_LOOPS
@WAIT
D; JGT

@COLOR
D = M
@SCREEN_POS
A = M
M = D // DYES THE SCREEN WITH BLACK IF COLOR = -1 OR WHITE IF COLOR = 0

@SCREEN_POS
M = M + 1 // ADVANCE THE POSITION TO DYE

@i
M = M + 1 // ADVANCE THE LOOP COUNTER

@LOOP
0;JMP

