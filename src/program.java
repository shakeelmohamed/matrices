public class program {
	public static void main(String[] args) {
		matrix m = new matrix(2,2);
		matrix r = m.identityMatrix();
		double[][] data = new double[2][8];
		int q = 1;
		for(int i = 0; i<data.length; i++){
			for(int j=0; j<data[i].length; j++){
				data[i][j] = q;
				q++;
			}
		}
		
		matrix l = new matrix(data);
		matrix p = r.times(l);
		System.out.println(p.toString());
	}

}
