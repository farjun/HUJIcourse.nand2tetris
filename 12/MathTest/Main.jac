// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/12/MathTest/Main.jack

/** Test program for the OS Math class. */
class Main {

	static Array twoToJ;

    /** Performs various mathematical operations, using calls to the Math class methods. */
    function void main() {
        var Array r;          // stores the test results;

        var int j, res;
		let twoToJ = Array.new(16);
        let j=0;
        let res = 1;
        while (j < 16) {
            let twoToJ[j] = res;
            let res = res + res;
            let j = j + 1;
        }

        //do Output.printInt(Main.sqrt(4));
        //do Output.printInt(Main.divide(16,8));
        //do Output.println();
        //do Output.printInt(Main.divide(28,7));
        //do Output.println();
  		//do Output.printInt(111);

        let r = 8000;
    
        let r[0] = 2 * 3;                  // 6
        do Output.printInt(r[0]);
        do Output.println();
        let r[1] = r[0] * (-30);           // 6 * (-30) = -180
        do Output.printInt(r[1]);
        do Output.println();

        let r[2] = r[1] * 100;             // (-180) * 100 = -18000
        do Output.printInt(r[2]);
        do Output.println();

        let r[3] = 1 * r[2];               // 1 * (-18000) = -18000
        do Output.printInt(r[3]);
        do Output.println();
        let r[4] = r[3] * 0;               // 0
        do Output.printInt(r[4]);
        do Output.println();

        let r[5] = 9 / 3;                  // 3
        do Output.printInt(r[5]);
        do Output.println();
        let r[6] = (-18000) / 6;           // -3000
        do Output.printInt(r[6]);
        do Output.println();
        let r[7] = 32766 / (-32767);       // 0
        do Output.printInt(r[7]);
        do Output.println();

        let r[8] = Math.sqrt(9);           // 3
        do Output.printInt(r[8]);
        do Output.println();
        let r[9] = Math.sqrt(32767);       // 181
        do Output.printInt(r[9]);
        do Output.println();

        let r[10] = Math.min(345, 123);    // 123
        do Output.printInt(r[10]);
        do Output.println();
        let r[11] = Math.max(123, -345);   // 123
        do Output.printInt(r[11]);
        do Output.println();
        let r[12] = Math.abs(27);          // 27
        do Output.printInt(r[12]);
        do Output.println();
        let r[13] = Math.abs(-32767);      // 32767
        do Output.printInt(r);
        //do Output.println();
        return;
    }

    function int abs(int x) {
    		if (x<0) {
    			return -x;
    		}
    		return x;
        }

    /** Returns the greater number. */
    function int max(int a, int b) {
        if (a>b){
            return a;
        }
        return b;
    }

    /** Returns the smaller number. */
    function int min(int a, int b) {
        if (a<b){
            return a;
        }
        return b;
    }

    function int sqrt(int x) {
        var int y, n, temp, j;
        let n = 16;
        let j = 16;
        let temp = twoToJ[Math.divide(n, 2)];


        let y = 0;
        while (~(j < 0)) {
            if (((~(((y + temp) * (y + temp)) > x)) & (((y + temp) * (y + temp)) > 0))) {
                let y = y + temp;
            }

            let j = j - 1;
            let temp = Math.divide(temp, 2);
        }
        return y;
    }

 	function int divideHelper(int x, int y) {
    		var int q;
    		if (x < y){
    			return 0;
    		}
    		let q = Main.divide(x,2*y);
    		if ((x-(2*q*y))<y) {
    		    return 2*q;
    		}
    		return 2*q+1;
    	}

        function int divide(int x, int y) {
    		var int neg;
    		let neg  = 1;
            if (x < 0) {
                let neg = -neg;
            }
            if (y < 0) {
                let neg = -neg;
            }

            return neg * Main.divideHelper(Math.abs(x), Math.abs(y));
        }

    function int multiply(int x, int y) {

		var int sum, n, temp, j, shiftedX;
		let sum = 0;
        let shiftedX = x;
        let n = 16;
        let temp = 1;
        let j = 0;

        //while ((x > temp) | (y > temp)) {
        //    let n = n + 1;
        //    let temp = temp + temp;
        //}

        let temp = 1;
        while (j < n) {
            if ((temp & y) = temp) {
                let sum = sum + shiftedX;
            }
            let j = j + 1;
            let temp = temp + temp;
            let shiftedX = shiftedX + shiftedX;
        }
		return sum;
	}


}
