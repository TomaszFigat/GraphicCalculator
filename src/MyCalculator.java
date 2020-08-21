import java.util.ArrayList;
import java.util.Iterator;
import java.math.*;
public class MyCalculator{
private String equation;

public MyCalculator(String equation) {
	this.equation=addBrackets(equation,2);
	
	//ystem.out.println("To jest wynik: " +brackets(this.equation));
	//System.out.println(solve(this.equation));
}
public double solveEquation() {
	return brackets(this.equation);
}
public String addBrackets (String equation,int lvl) {
	String eq1="";
	String eq2="";
	int brackL=0, brackR=0;
	String operation="";
	
	ArrayList<String> downOperations = new ArrayList<>();
	
	for(int i=0; i<equation.length(); i++) {
		if(((equation.charAt(i)=='*'||equation.charAt(i)=='/')&&lvl==1)||((equation.charAt(i)=='^'||equation.charAt(i)=='p')&&lvl==2)) {
			switch(equation.charAt(i)) {
			case '*':operation=operation+equation.charAt(i);
					downOperations.add("+");
					downOperations.add("-");
//					downOperations.add("(");
//					downOperations.add(")");
					
					
					break;
			case '/':operation=operation+equation.charAt(i);
					downOperations.add("+");
					downOperations.add("-");
//					downOperations.add("(");
//					downOperations.add(")");
//					
					break;
			case '^':operation=operation+equation.charAt(i);
					downOperations.add("+");
					downOperations.add("-");
					downOperations.add("*");
					downOperations.add("/");
				
					break;	
			case 'p':operation=operation+equation.charAt(i);
					downOperations.add("+");
					downOperations.add("-");
					downOperations.add("*");
					downOperations.add("/");
					;
					break;
			
			}
		
			int beginLeft= i;
			int beginRight= i+1;
			
			for(int j=beginLeft+1; j>=0 ; j--) {
				boolean stat=false;
				for(String x : downOperations) {
					
					
				if(equation.charAt(j)==x.charAt(0) || j==0) {
					
					eq1=equation.substring(0, j!=0 ? j+1 : j)+"("+equation.substring(j!=0 ? j+1 : j, beginLeft);
					System.out.println("to jest 1: "+eq1);
					
					brackL=j;
					stat=true;
					break;
				}
				
				}
				if(stat)break;
			}
			for(int z=beginRight; z<equation.length() ; z++) {
				boolean stat=false;
				for(String x : downOperations) {
					
				if(equation.charAt(z)==x.charAt(0) ||z==equation.length()-1) {
					eq2=equation.substring(beginRight, z!=equation.length()-1 ? z : z+1)+")"+equation.substring(z!=equation.length()-1 ? z : z+1, z+1);
					brackR=z+1;
					stat=true;
					System.out.println("to jest 2: "+eq2);
					break;
				}
				
				}
				if(stat)break;
			}
			
			equation=eq1+operation+eq2  +addBrackets(equation.substring(brackR,equation.length()),2);
			break;
		}
		
	}
	if(--lvl>0) {
		
		equation =addBrackets(equation,lvl);
	}
	return equation;
}
public double brackets (String equation) {
	System.out.println("this is "+ equation);
	int rounds=0;
	for(int i=0; i<equation.length(); i++) 
		if(equation.charAt(i)=='(')
			rounds++;

	int beg=0;
	int end=0;
	
	do
	for(int i=0; i<equation.length(); i++) {
		if(equation.charAt(i)=='(') {
			
			beg=i ;
//			rounds++;
		}
		if(equation.charAt(i)==')') {
			end=i;
			System.out.println(equation.substring(end+1,equation.length()));
			//beg>0 ? "" : equation.substring(end+1,equation.length()-1);
			equation =  (beg==0 ? "" : equation.substring(0,beg)) +
						Double.toString(solve(equation.substring(beg+1, end)))+
						(end==0 ? "": equation.substring(end+1,equation.length()));
						
		rounds--;
		break;
		}
		}
	while(rounds>0);
	System.out.println(equation+" ------------------tu jest juz po nawiasach");
	return solve(equation);
	}
public double solve(String equation) {
	System.out.println(equation + " co to tutaj mamy");
	for(int i=equation.length()-1; i>=0; i--) {
		if(equation.charAt(i)=='+') {
			System.out.println(i);
			return add(equation.substring(0, i),equation.substring(i+1, equation.length()));
		}
		if(equation.charAt(i)=='-') {
			return substract(equation.substring(0, i),equation.substring(i+1, equation.length()));
		}
		if(equation.charAt(i)=='*') {
			return multiply(equation.substring(0, i),equation.substring(i+1, equation.length()));
		}
		if(equation.charAt(i)=='/') {
			return divide(equation.substring(0, i),equation.substring(i+1, equation.length()));
		}
	}
	System.out.println("------------  "+equation);
	return Double.parseDouble(equation);
}
	//Double.parseDouble(equation1)-solve(equation2)
public double add(String equation1, String equation2) {
	
	return solve(equation1)+Double.parseDouble(equation2);
}
public double substract(String equation1, String equation2) {
	
	return solve(equation1)-Double.parseDouble(equation2);
}
public double multiply(String equation1, String equation2) {
	
	return solve(equation1)*Double.parseDouble(equation2);
}
public double divide(String equation1, String equation2) {
	
	return solve(equation1)/Double.parseDouble(equation2);
}

}
