/*To add:
 * determinant
 * rref
 * ref
 * inverse
 * get row/column by index
 * isInNullSpace/colSpace
 * are columns Linerally independent?
 * */

public class matrix {
	private double[][] matrix;
	private int r;
	private int c;
	public matrix(int rows, int columns){
		if(!(rows >= 0 && columns >=0)){
			rows = columns = 0;
			System.out.println("Invalid value for rows and/or columns passed to the matrix contructor, rows or columns must be 0 or greater.");
		}
		this.matrix = new double [rows][columns];
		this.r = rows;
		this.c = columns;	
	}
	public matrix(double[][] data){
		this.matrix = data;
		this.r = data.length;
		if(this.r < 1)
			this.c = 0;
		else this.c = this.matrix[0].length;
	}
	public boolean isSquare(){
		return (this.r==this.c);
	}
	public matrix oneMatrix(){
		if(this.isSquare()){
			matrix r = new matrix(this.r,this.c);
			for(int i=0; i<r.matrix.length; i++){
				for(int j=0; j<r.matrix[i].length;j++){
					r.matrix[i][j] = 1.0;
				}
			}
			return r;
		}
		else 
			return new matrix(0,0);
	}
	public String toString(){
		String output = "[\n";
		for(int i=0; i<this.matrix.length; i++){
			output += "\t[";
			for(int j=0; j<this.matrix[i].length;j++){
				output += this.matrix[i][j] + ", ";
			}
			output += "]\n";
		}
		return output+"]";
	}
	public boolean isSameDimension(matrix n){
		return (this.c==n.c && this.r==n.r);
	}
	public void add(matrix n){
		if(this.isSameDimension(n)){
			for(int i=0; i<n.matrix.length; i++){
				for(int j=0; j<n.matrix[i].length;j++){
					this.matrix[i][j] += n.matrix[i][j];
				}
			}
		}
		else System.out.println("Invalid dimensions!");
	}
	public void subtract(matrix n){
		if(this.isSameDimension(n)){
			for(int i=0; i<n.matrix.length; i++){
				for(int j=0; j<n.matrix[i].length;j++){
					this.matrix[i][j] -= n.matrix[i][j];
				}
			}
		}
		else System.out.println("Invalid dimensions!");
	}
	public boolean canMultiply(matrix n){
		return(this.c == n.r);
	}
	public matrix times(matrix n){
		if(this.canMultiply(n)){
			matrix product = new matrix(this.r, n.c);
			for(int i=0; i<product.r;i++){
				//fill entries of first row in product matrix
				for(int j=0; j<product.c; j++){
					//This will calculate the sum to fill our the jth entry in row i of the product matrix
					for (int k=0; k<n.matrix.length; k++){
						 product.matrix[i][j] += this.matrix[i][k]*n.matrix[k][j];
					}
				}
			}
			return product;
		}
		else{
			System.out.println("Invalid dimensions!");
			return new matrix(0,0);
		}
	}
	public matrix identityMatrix() {
		matrix ret;
		if(this.isSquare()){
			ret = new matrix(this.r, this.c);
			for(int i=0; i<ret.r; i++)
				ret.matrix[i][i] = 1;
			ret.c = this.c;
			ret.r = this.r;
			return ret;
		}
		else{
			System.out.println("Invalid dimensions!");
			ret = new matrix(0,0);
			ret.c = 0;
			ret.r = 0;
			return ret;
		}
	}
	public double[][] getMatrixEntries(){
		return this.matrix;
	}
	public double getRowCount(){
		return this.r;
	}
	public double getColumnCount(){
		return this.c;
	}
	public matrix getTranspose(){
		if(this.c < 1 || this.r < 1){
			System.out.println("Invalid number of rows and/or columns.");
			return new matrix(0,0);
		}
		else{
			matrix ret = new matrix(this.c, this.r);
			for(int i=0; i<this.r; i++){
				for(int j=0; j<this.c; j++){
					ret.matrix[j][i] = this.matrix[i][j];
				}
			}
			return ret;
		}
	}
	public double euclideanInnerProduct(matrix n){ 
		//This is like the dot product with vectors, multiply corresponding components and sum up those products.
		if(this.isSameDimension(n)){
			double val = 0.0;
			for(int i=0; i<this.r; i++){
				for(int j=0; j<this.c; j++){
					val += (this.matrix[i][j]*n.matrix[i][j]);
				}
			}
			return val;
		}
		else{
			System.out.println("Invalid dimensions.");
			return 0.0;
		}			
	}
}
