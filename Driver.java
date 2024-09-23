import java.io.File;
import java.io.FileNotFoundException;

public class Driver {
    public static void main(String [] args) throws FileNotFoundException {
        Polynomial p = new Polynomial();
        System.out.println(p.evaluate(3));
        double [] c1 = {6,0,0,5};
        int [] e1 = {0,1,2,3};
        Polynomial p1 = new Polynomial(c1, e1);
        double [] c2 = {0,-2,0,0,-9};
        int [] e2 = {0,1,2,3,4};
        Polynomial p2 = new Polynomial(c2, e2);
        Polynomial s = p1.add(p2);
        System.out.println("s(0.1) = " + s.evaluate(0.1));
        if(s.hasRoot(1))
            System.out.println("1 is a root of s");
        else
            System.out.println("1 is not a root of s");
        
        /* lab 1 ans
        * 0.0
        * s(0.1) = 5.8041
        * 1 is a root of s
        */

        double [] c3 = {1,1};
        int [] e3 = {0,1};
        Polynomial p3 = new Polynomial(c3, e3);
        double [] c4 = {-1,1};
        int [] e4 = {0,1};
        Polynomial p4 = new Polynomial(c4, e4);
        Polynomial product = p3.multiply(p4);
        for(int i = 0; i < product.exponents.length; i++){
            System.out.print("(" + product.coefficients[i] + "X^" + product.exponents[i] + ")");
            if(i < product.exponents.length - 1){
                System.out.print(" + ");
            }
        }

        System.out.println("");
        File file = new File(System.getProperty("user.dir") + "\\text.txt");
        Polynomial p5 = new Polynomial(file);

        for(int i = 0; i < p5.exponents.length; i++){
            System.out.print("(" + p5.coefficients[i] + "X^" + p5.exponents[i] + ")");
            if(i < p5.exponents.length - 1){
                System.out.print(" + ");
            }
        }

        p5.saveToFile(System.getProperty("user.dir") + "\\test.txt");
    }
}
