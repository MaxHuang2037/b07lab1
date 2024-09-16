class Polynomial{
	public double[] p;

	public Polynomial(){
		p = new double[]{0};
	}

	public Polynomial(double[] polynomial){
		p = polynomial;
	}

	public Polynomial add(Polynomial polynomial){
		double[] temp;
		if(polynomial.p.length > p.length){
			temp = polynomial.p.clone();
			for(int i = 0; i < p.length; i++){
				temp[i] += p[i];
		    }
		}
		else{
		    temp = p.clone();
		    for(int i = 0; i < polynomial.p.length; i++){
				temp[i] += polynomial.p[i];
		    }
		}
		
		return new Polynomial(temp);
	}

    public double evaluate(double x){
        double sum = 0;
        for(int i = 0; i < p.length; i++){
            sum += p[i] * Math.pow(x, i);
        }
        return sum;
    }

    public boolean hasRoot(double x){
        return evaluate(x) == 0;
    }
}
