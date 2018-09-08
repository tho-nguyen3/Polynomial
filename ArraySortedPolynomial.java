import java.util.ArrayList;


public class ArraySortedPolynomial implements PolynomialInterface 
{
	private ArrayList <TermNode> polynomial = new ArrayList<TermNode>(100);

	private String polyString;
	int numElements=0;


	private class TermNode 
	{
		private int coeff, exp;
		private TermNode link;
		public TermNode()
		{
			coeff = 0;
			exp = 0;
			link = null;

		}
		public TermNode (int newCoeff, int newExp)
		{
			coeff = newCoeff;
			exp = newExp;
			link = null;
		}
		public int getCoeff()
		{
			return coeff;
		}
		public int getExp()
		{
			return exp;
		}
		public int addTerm(TermNode other)
		{
			return this.getCoeff() + other.getCoeff();
		}
		public int subtractTerm(TermNode other)
		{
			return this.getCoeff() - other.getCoeff();
		}
		public int zeroSubtract()
		{
			return 0 -this.getCoeff();
		}
		public int compareTo(TermNode other)
		{
			if (this.getExp() < other.getExp())
				return -1;
			else if (this.getExp() > other.getExp())
				return 1;
			else 
				return 0;

		}
		public String printTerm()
		{
			String term ="";
			if (this.getExp() == 1)
			{ 
				if(this.getCoeff() > 0)
					term = term +  "+" + this.getCoeff() + "x"; 
				else 
					term = term +  this.getCoeff() + "x";	
			}
			else if (this.getCoeff() != 0 && this.getExp() == 0 )
			{ 
				if(this.getCoeff() > 0)
					term = term +  "+" + this.getCoeff() ; 
				else 
					term = term +  this.getCoeff() ;	

			}
			else if (this.getCoeff() != 0 )
			{ 
				if(this.getCoeff() > 0)
					term = term + "+" +this.getCoeff() + "x^" + this.getExp();
				else 
					term = term  +this.getCoeff() + "x^" + this.getExp();
			}

			return term;
		}
	}


	public ArraySortedPolynomial(String s) 
	{
		TermNode newTerm = new TermNode();
		for (int i = 0; i <10; i++ )
		{
			polynomial.add(i, newTerm);
		}
		polyString = s;
		readPolynomial();

	}

	public ArraySortedPolynomial()
	{
		TermNode newTerm = new TermNode();
		for (int i = 0; i <10; i++ )
		{
			polynomial.add(i, newTerm);
		}
		polyString = null;
	}

	
	public PolynomialInterface add(PolynomialInterface other)
	{
		ArraySortedPolynomial otherPoly = (ArraySortedPolynomial) other;
		ArraySortedPolynomial merge = new ArraySortedPolynomial(); //merge poly is unsorted
		ArraySortedPolynomial temp = new ArraySortedPolynomial();
		
		int leftFirst = 0, leftLast = this.numElements-1; 
		int rightFirst= leftLast +1; 
		int rightLast = rightFirst + otherPoly.numElements-1;
		
	
		for (int index = leftFirst; index <= leftLast; index ++)
		{
			setNodeAtIndex(merge,index, this, index);
		}

		int otherIndex=0;
		for (int index = rightFirst; index <= rightLast; index ++)
		{
			setNodeAtIndex(merge, index, otherPoly, otherIndex);
			otherIndex++;
		}

		int index = leftFirst;
		int saveFirst = leftFirst;
		
		while ((leftFirst <= leftLast) && (rightFirst <= rightLast))
		{
			if (merge.polynomial.get(leftFirst).compareTo(merge.polynomial.get(rightFirst)) >0)
			{
				setNodeAtIndex(temp, index, merge, leftFirst);
				leftFirst++;
			}

			else if ((merge.polynomial.get(leftFirst).compareTo(merge.polynomial.get(rightFirst)) ==0))
			{
				int newCoeff = merge.polynomial.get(leftFirst).addTerm(merge.polynomial.get(rightFirst));
				int newExp = merge.polynomial.get(leftFirst).getExp();
				TermNode newTerm = new TermNode (newCoeff, newExp);
				setNodeAtIndex(temp, newTerm,  index);
				leftFirst++;
				rightFirst++;
			}
			else 
			{
				setNodeAtIndex(temp, index, merge, rightFirst);
				rightFirst++;
			}
			index++;

			}
		while (leftFirst <= leftLast)
		{
			temp.polynomial.set(index, merge.polynomial.get(leftFirst));
			leftFirst++;
			index++;
		}
		while (rightFirst <= rightLast)
		{
			temp.polynomial.set(index, merge.polynomial.get(rightFirst));
			rightFirst++;
			index++;
		}
		
		for (index = saveFirst; index <= rightLast; index++)
			merge.polynomial.set(index, temp.polynomial.get(index));
			return merge;
}

		public PolynomialInterface subtract(PolynomialInterface other)
		{
			ArraySortedPolynomial otherPoly = (ArraySortedPolynomial) other;
			ArraySortedPolynomial merge = new ArraySortedPolynomial(); //merge poly is unsorted
			ArraySortedPolynomial temp = new ArraySortedPolynomial();
			
			int leftFirst = 0, leftLast = this.numElements-1; 
			int rightFirst= leftLast +1; 
			int rightLast = rightFirst + otherPoly.numElements-1;
			
			for (int index = leftFirst; index <= leftLast; index ++)
			{
				setNodeAtIndex(merge,index, this, index);
			}
	
			int otherIndex=0;
			for (int index = rightFirst; index <= rightLast; index ++)
			{

				setNodeAtIndex(merge, index, otherPoly, otherIndex);
				otherIndex++;
			}


			int index = leftFirst;
			int saveFirst = leftFirst;
			while ((leftFirst <= leftLast) && (rightFirst <= rightLast))
			{
				if (merge.polynomial.get(leftFirst).compareTo(merge.polynomial.get(rightFirst)) >0)
				{
					setNodeAtIndex(temp, index, merge, leftFirst);
					leftFirst++;
				}

				else if ((merge.polynomial.get(leftFirst).compareTo(merge.polynomial.get(rightFirst)) ==0))
				{
					int newCoeff = merge.polynomial.get(leftFirst).subtractTerm(merge.polynomial.get(rightFirst));
					int newExp = merge.polynomial.get(leftFirst).getExp();
					TermNode newTerm = new TermNode (newCoeff, newExp);
					setNodeAtIndex(temp, newTerm,  index);
					leftFirst++;
					rightFirst++;
				}
				else 
				{
					int newCoeff = merge.polynomial.get(rightFirst).zeroSubtract();
					int newExp = merge.polynomial.get(rightFirst).getExp();
					TermNode newTerm = new TermNode (newCoeff, newExp);
					setNodeAtIndex(temp, newTerm,  index);
					rightFirst++;

				}
				index++;

				}
			while (leftFirst <= leftLast)
			{
				temp.polynomial.set(index, merge.polynomial.get(leftFirst));
				leftFirst++;
				index++;
			}
			while (rightFirst <= rightLast)
			{
				temp.polynomial.set(index, merge.polynomial.get(rightFirst));
				rightFirst++;
				index++;
			}
			

			for (index = saveFirst; index <= rightLast; index++)
				merge.polynomial.set(index, temp.polynomial.get(index));
				return merge;
		}

		public void readPolynomial() 
		{

			boolean neg = false;
			String coeffString ="";
			int exponent, coeff;



			for ( int i = 0; i < this.polyString.length(); i++) //34x^23+23x^21-13x-34
			{

				if (this.polyString.charAt(0) == '-')
				{
					neg = true;
					this.polyString = this.polyString.substring(1);
			
					i=0;
				}
				if (this.polyString.charAt(i) == 'x')
				{
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


					if (i == 0)
					{
						coeff = 1;
					}
					else 
					{

						coeff = Integer.parseInt(coeffString.replaceAll("[\\D]", ""));
					}

					if(neg)
					{
						TermNode term = new TermNode (0-coeff, exponent);
						addNode(term);
						neg= false;

					}
					else 
					{
						TermNode term = new TermNode (coeff, exponent);
						addNode(term);

					}

					i = 0;

				}
			}
			if (this.polyString.length() > 0)
			{

				coeff = Integer.parseInt(this.polyString.replaceAll("[\\D]", ""));
				exponent = 0;
				if(neg)
				{
					TermNode term = new TermNode (0-coeff, exponent);
					addNode(term);
					neg= false;

				}
				else 
				{
					TermNode term = new TermNode (coeff, exponent);
					addNode(term);

				}
			}


		}
		public void setNodeAtIndex(ArraySortedPolynomial p1, int i1,ArraySortedPolynomial p2,int i2)
		{
			p1.numElements++;
			p1.polynomial.set(i1, p2.polynomial.get(i2));
			
		}
		public void setNodeAtIndex(ArraySortedPolynomial p1, TermNode node, int index)
		{
			p1.numElements++;
			p1.polynomial.set(index,node);
			
		}

		public void addNode (TermNode newTerm)
		{
			this.numElements++;
			TermNode tempTerm;
			int location = 0;
			while (location < this.polynomial.size()-1)
			{
				tempTerm = (TermNode) this.polynomial.get(location);
				if (tempTerm.compareTo(newTerm) > 0)
					//poly term < add term
					location++;
				else 
					break;
			}
			for (int index = this.polynomial.size()-1; index > location; index--)
			{
				this.polynomial.set(index, this.polynomial.get(index -1));
			}
			this.polynomial.set(location, newTerm);
		}

		public String toString()
		{
//			System.out.println(this.numElements);
			String result = ""; 
			for (int i = 0; i < this.numElements;i ++)
			{
				result = result + this.polynomial.get(i).printTerm();

			}
			if (result.charAt(0) == '+')
			{result= result.substring(1);}
			return result; 

		}



	}
