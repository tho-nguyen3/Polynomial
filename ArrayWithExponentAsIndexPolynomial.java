import java.util.ArrayList;
import java.util.Scanner;


public class ArrayWithExponentAsIndexPolynomial implements PolynomialInterface
{ 
	private ArrayList <Integer> polynomial = new ArrayList<Integer>(100);
	String polyString;
	int exponent, coeff;
	

	// Constructor 
	public ArrayWithExponentAsIndexPolynomial ()
	{
		for (int i = 0; i <100; i++ )
		{
			polynomial.add(i, 0);
		}

		coeff = 0;
		exponent = 0;
		polyString = null;
	}
	public ArrayWithExponentAsIndexPolynomial (String s)
	{
//		System.out.println("array size: " + polynomial.size());
		for (int i = 0; i <100; i++ )
		{
			polynomial.add(i, 0);
		}

		polyString = s;
		readPolynomial();
		
	}


	
	public PolynomialInterface add(PolynomialInterface other)
	{
		ArrayWithExponentAsIndexPolynomial otherPolynomial = 
				(ArrayWithExponentAsIndexPolynomial) other;
		ArrayWithExponentAsIndexPolynomial sum = new ArrayWithExponentAsIndexPolynomial();
				
			for(int i = 0; i<otherPolynomial.polynomial.size(); i++)
			{				
				sum.polynomial.add(i,this.polynomial.get(i) + otherPolynomial.polynomial.get(i));
			}
	
		return sum;
	}
	public PolynomialInterface subtract(PolynomialInterface other)
	{
		
		ArrayWithExponentAsIndexPolynomial otherPolynomial = 
				(ArrayWithExponentAsIndexPolynomial) other;
		ArrayWithExponentAsIndexPolynomial dif = new ArrayWithExponentAsIndexPolynomial();
				
			for(int i = 0; i<otherPolynomial.polynomial.size(); i++)
			{		
				dif.polynomial.add(i,this.polynomial.get(i) - otherPolynomial.polynomial.get(i));
			}
	
		return dif;
	}
	
	public void readPolynomial()
	{
		
		boolean neg = false;
		String coeffString ="";
		


		for ( int i = 0; i < this.polyString.length(); i++) //34x^23+23x^21-13x-34
		{
//			System.out.println("2 S length " + s.length());
//			System.out.println("s = " + s);
			if (this.polyString.charAt(0) == '-')
			{
				neg = true;
				this.polyString = this.polyString.substring(1);
//				System.out.println("s = " + s);
				i=0;
			}
			if (this.polyString.charAt(i) == 'x')
			{
//				System.out.println("i =  " + i);
//				System.out.println("s = " + s);
				
				String exponentString = "";
				if (this.polyString.charAt(i+1) == '^') //23x^345
				{

					int index = i +2;
//					System.out.println("index =  " + index);
					while((index < this.polyString.length() ) && (Character.isDigit(this.polyString.charAt(index))))
					{
//						System.out.println("index =  " + index);
						exponentString = exponentString + this.polyString.charAt(index);
						index++;
					}

					exponent = Integer.parseInt(exponentString);
					coeffString = this.polyString.substring(0,i);
					this.polyString =this.polyString.substring(index);
					
					
				}


				else // 3x
				{
					exponent = 1;
					coeffString = this.polyString.substring(0,i);
					this.polyString = this.polyString.substring(i+1);
					
				}
//				System.out.println("exponent = " + exponent);


				if (i == 0)
				{
					coeff = 1;
				}
				else 
				{
					
					coeff = Integer.parseInt(coeffString.replaceAll("[\\D]", ""));
				}
//				System.out.println("coeff = " + coeff);

				if(neg)
				{
//					System.out.println("neg add exponent = " + exponent);
//					System.out.println("neg add coeff = " + coeff);

					polynomial.set(exponent, 0 - coeff);
					neg= false;

				}
				else 
				{
//					System.out.println(" add exponent = " + exponent);
//					System.out.println(" add coeff = " + coeff);
					polynomial.set(exponent, coeff);

				}
				
//				System.out.println("s = " + s);
				i = 0;
				
			}
		}
		if (this.polyString.length() > 0)
		{

			coeff = Integer.parseInt(this.polyString.replaceAll("[\\D]", ""));
			exponent = 0;
//			System.out.println("coeff = " + coeff);
			if(neg)
			{
//				System.out.println("neg add exponent = " + exponent);
//				System.out.println("neg add coeff = " + coeff);
				polynomial.set(exponent, 0 - coeff);
				neg= false;

			}
			else 
			{
//				System.out.println(" add exponent = " + exponent);
//				System.out.println(" add coeff = " + coeff);
				polynomial.set(exponent, coeff);

			}
		}
	
		
	}
	
	public String toString()
	{
//		System.out.println("array size = " + polynomial.size() );
		String result = ""; 
//		System.out.println("poly size = " + polynomial.size());
		for (int i =  polynomial.size() -1; i >=0; i --)
		{ 
			if (polynomial.get(i) !=0)
			{

//				System.out.println("expoent = " + i);
//				System.out.println("coeff = " + polynomial.get(i) );

				if (polynomial.get(i) >= 0)
				{
					result = result + "+";
				}
				
				if (i ==0)
				{
					result = result + polynomial.get(i) ;
				}
				if (i == 1)
				{
					result = result + polynomial.get(i) + "x" ;
				}
				else if (i != 0 && i!=1 && polynomial.get(i) != 0)
				{ 
				//	System.out.println("coeff     " + polynomial.get(i));
					result = result + polynomial.get(i) + "x^" + (i);
				}

			}

		}	
		if ( result.charAt(0) == '+')
		{result = result.substring(1);}

		return result; 

	}
}
