import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;

class Polynomial{
	public double[] coefficients;
	public int[] exponents;

	public Polynomial(){
		coefficients = new double[]{0};
		exponents = new int[]{0};
	}

	public Polynomial(double[] coefficients, int[] exponents){
		this.coefficients = coefficients;
		this.exponents = exponents;
	}

	public Polynomial(File file) throws FileNotFoundException{
		Scanner input = new Scanner(file);
		String poly = input.nextLine();
		String[] s = poly.split("\\+");
		System.out.println(poly);
		HashMap<Integer, Double> p = new HashMap<>();

		for (String c : s) {
			String[] temp = c.split("-");
			for(int i = 0; i < temp.length; i++){
				String[] t2 = temp[i].split("x");
				if(t2.length == 1){
					if(i > 0){
						p.put(0, -Double.valueOf(t2[0]));
					} else {
						p.put(0, Double.valueOf(t2[0]));
					}
					continue;
				}

				if(p.containsKey(Integer.valueOf(t2[1]))){
					if(i > 0){
						p.put(Integer.valueOf(t2[1]), p.get(Integer.valueOf(t2[1])) - Double.valueOf(t2[0]));
					} else {
						p.put(Integer.valueOf(t2[1]), p.get(Integer.valueOf(t2[1])) + Double.valueOf(t2[0]));
					}
				}
				else{
					if(i > 0){
						p.put(Integer.valueOf(t2[1]), -Double.valueOf(t2[0]));
					} else {
						p.put(Integer.valueOf(t2[1]), Double.valueOf(t2[0]));
					}
				}
			}
		}

		coefficients = new double[p.size()];
		exponents = new int[p.size()];
		int i = 0;
		for (int key : p.keySet()) {
			coefficients[i] = p.get(key);
			exponents[i] = key;
			i++;
		}
	}

	public Polynomial add(Polynomial polynomial){
		HashMap<Integer, Double> dict = new HashMap<>();
		for(int i = 0; i < coefficients.length; i++){
			dict.put(exponents[i], coefficients[i]);
		}

		for(int i = 0; i < polynomial.coefficients.length; i++){
			if(dict.containsKey(polynomial.exponents[i])){
				dict.put(polynomial.exponents[i], dict.get(polynomial.exponents[i]) + polynomial.coefficients[i]);
			} else {
				dict.put(polynomial.exponents[i], polynomial.coefficients[i]);
			}
		}

		double[] temp_c = new double[dict.size()];
		int[] temp_e = new int[dict.size()];
		int i = 0;
		for (int key : dict.keySet()) {
			temp_c[i] = dict.get(key);
			temp_e[i] = key;
			i++;
		}

		return new Polynomial(temp_c, temp_e);
	}

	public Polynomial multiply(Polynomial polynomial){
		HashMap<Integer, Double> dict = new HashMap<>();

		for(int i = 0; i < exponents.length; i++){
			for(int j = 0; j < polynomial.exponents.length; j ++){
				int e = exponents[i] + polynomial.exponents[j];
				double c = coefficients[i] * polynomial.coefficients[j];
				if(dict.containsKey(e)){
					if(dict.get(e) + c == 0){
						dict.remove(e); // remove if coefficient is 0
					} else {
						dict.put(e, dict.get(e) + c);
					}
				} else {
					dict.put(e, c);
				}
			}
		}

		double[] temp_c = new double[dict.size()];
		int[] temp_e = new int[dict.size()];
		int i = 0;
		for (int key : dict.keySet()) {
			temp_c[i] = dict.get(key);
			temp_e[i] = key;
			i++;
		}

		return new Polynomial(temp_c, temp_e);
	}

    public double evaluate(double x){
        double sum = 0;
        for(int i = 0; i < coefficients.length; i++){
            sum += coefficients[i] * Math.pow(x, exponents[i]);
        }
        return sum;
    }

    public boolean hasRoot(double x){
        return evaluate(x) == 0;
    }

	public void saveToFile(String path) throws FileNotFoundException{
		try (PrintStream output = new PrintStream(path)) {
			String polynomial = "";
			for(int i = 0; i < exponents.length; i++){
				if(i > 0){
					if(coefficients[i] > 0){
						polynomial += "+";
					}
					polynomial += Double.toString(coefficients[i]);
					polynomial += "x" + Integer.toString(exponents[i]);
				} else{
					polynomial += Double.toString(coefficients[i]);
				}
			}
			output.println(polynomial);
		}
	}
}