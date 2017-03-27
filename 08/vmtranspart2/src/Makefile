###############################################################################
#
# Makefile for Java project
#
# Students:
# Gal suchetzky, ID 208562975, galsuchetzky
# Omer Ben Haim, ID 305785529, omerbh1
#
###############################################################################

JAVAC=javac
SRCS=*.java
EXEC=VMtranslator

TAR=tar
TARFLAGS=cvf
TARNAME=project7.tar
TARSRCS=$(SRCS) $(EXEC) README Makefile

all: compile

compile:
	$(JAVAC) $(SRCS)
	chmod +x $(EXEC)

tar:
	$(TAR) $(TARFLAGS) $(TARNAME) $(TARSRCS)

clean:
	rm -f *.class *~
