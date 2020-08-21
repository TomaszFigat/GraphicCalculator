
public class ChartPolynomialEquation {
	double x;
	String equation;
	public ChartPolynomialEquation( String equation) {
//		this.x=x;
		this.equation=equation;
		
	}
	double score(double x) {
		this.x=x;
		return(solve(this.equation,0));
	}
	double solve(String eq, int i0) {
		String number= "";
		int pp;
		for(int i =i0; i<eq.length(); i++) {
			
			if(eq.charAt(i)=='x') {
				if(number.equals("+")||number.equals("-")||number.equals("")) 
					number+="1";
				return power(eq,i,Double.parseDouble(number));
				}
			
			
				//if(i0!=0)
				if(i>i0 &&( eq.charAt(i)=='+' || eq.charAt(i)=='-')) {
					return Double.parseDouble(number)+solve(eq,i);
				}
				number+=eq.charAt(i);
				
		}
		return Double.parseDouble(number);
	}
	
	
	double power(String eq,int i,double pp) {
		boolean powSt=true;
		if(eq.charAt(i)!=eq.length()-1) {
			String number="";
			//System.out.println(i+"das");
			//System.out.println(i+1+"  uuuuuuuuuu: "+eq.length() );
			if(i+1==eq.length()) {
				return pp*this.x;
			}
			for(int j=i+1; j<eq.length() ; j++) {
				//System.out.println(j+ " to jest j");
				
				if(eq.charAt(i+1)!='^') {
					//System.out.println(this.x);
					return pp*this.x+solve(eq,j);
				}
				else
					powSt=true;
			
				if(j>i+2&& (eq.charAt(j)=='+'||eq.charAt(j)=='-')) {
					//solve(eq,j+2);
					//System.out.println(number);
					return pp*( Math.pow(this.x, Double.parseDouble(number)))+solve(eq,j);			
				}
				if(j>i+1) {
					number+=eq.charAt(j);
					if(j==eq.length()-1) {
						for(int sb=0; sb < number.length(); sb++)
							if(powSt) {
								return pp*(Math.pow(this.x, Double.parseDouble(number)));
							}
						return Double.parseDouble(number);
					}
				}
			}	
		}
		return 1;
	}
}
