package my_package;

public class Maschine {
	
	public int add(int a , int b) {
		int result = a+b;
		return result;
		
	}
	
	public int sub(int a , int b) {
		int result = a-b;
		return result;
		
	}
	
	public int mul(int a , int b) {
		int result = a*b;
		return result;
		
	}
	
	
	public int div(int a, int b) {
	    int result = 0;
	    
	    if (b != 0) {
	        result = a / b;
	    }
	    
	    return result;
	}
	

}
