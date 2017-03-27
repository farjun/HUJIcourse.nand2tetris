JAVAC=javac
JAVACFLAGS=

SRCS=*.java
EXEC=Assembler

all: compile

compile:
	$(JAVAC) $(JAVACFLAGS) $(SRCS)
	chmod +x $(EXEC)

tar:
	tar cfv project6.tar Assembler.java Assembler Makefile README

clean:
	rm -f *.class *~

