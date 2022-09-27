package test;

import java.util.*;
public class Fourte {

	static ArrayList<String> combinations=new ArrayList<>();
	static ArrayList<Integer> permuted_numbers=new ArrayList<>();
	static int target=0;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		combinations.addAll(Arrays.asList("+++","++-","+--","-++","--+","---","-+-","+-+","***","**+","+**","++*","+*+","*+*","*++","**-","-**","--*","-*-","*-*","*--","-*+","///","*/*","**/","/**","//*","*//","*/+","*+/","*-/","/*+","/+*","/-*","/*-","--/","++/","+/-","-/+","/-+","/+-"));
		System.out.println("Enter input values as \nTarget sum and Numbers:");
		target=sc.nextInt();
		ArrayList<Integer> numbers=new ArrayList<>();
		numbers.add(sc.nextInt());
		numbers.add(sc.nextInt());
		numbers.add(sc.nextInt());
		numbers.add(sc.nextInt());
		
		check_combinations(numbers);
		
	}
	
	public static void check_combinations(ArrayList<Integer> numbers)
	{
		int temp_number=0,digits=0;
		while(digits<numbers.size())
		{
			temp_number=(temp_number*10)+numbers.get(digits);
			digits++;
		}
		//System.out.println(temp_number);
		find_number_combination(temp_number,digits);
	}
	
	public static void find_number_combination(int temp_number,int digits)
	{
		int permut_number=temp_number;
		int reminder=0,quotient=0;
		boolean permuted=false;
		String answer="";
		permuted_numbers.add(permut_number);
		while(!permuted)
		{
			reminder=permut_number%10;
			quotient=permut_number/10;
			permut_number=(int)(Math.pow(10, digits-1)*reminder)+quotient;
			if(permut_number==temp_number)
				permuted=true;
			else
				permuted_numbers.add(permut_number);
				
		}
		//System.out.println(permuted_numbers);
		for(Integer operand:permuted_numbers)
		{
			answer=check_operation(operand);
			if(!answer.isEmpty())
				break;
		}
		System.out.println(answer);
		
	}
	
	public static String check_operation(int operand)
	{
		ArrayList<Integer> each_number=new ArrayList<>();
		StringBuilder equation=new StringBuilder();
		int digit_count=0,length=4;
		while(digit_count<length)
		{
			each_number.add(operand%10);
			operand=operand/10;
			digit_count++;
		}
		for(String oper_combination:combinations)
		{
			int derived_target=each_number.get(0);
			equation.append(derived_target);
			for(int index=0,num_index=1;index<oper_combination.length() && num_index<each_number.size();index++,num_index++)
			{
				if(oper_combination.charAt(index)=='+')
				{
					derived_target=derived_target+each_number.get(num_index);
					equation.append("+");
					equation.append(each_number.get(num_index));
				}
				else if(oper_combination.charAt(index)=='-')
				{
					derived_target=derived_target-each_number.get(num_index);
					equation.append("-");
					equation.append(each_number.get(num_index));
				}
				else if(oper_combination.charAt(index)=='*')
				{
					derived_target=derived_target*each_number.get(num_index);
					equation.append("*");
					equation.append(each_number.get(num_index));
				}
				else if(oper_combination.charAt(index)=='/')
				{
					derived_target=(int)(derived_target/each_number.get(num_index));
					equation.append("/");
					equation.append(each_number.get(num_index));
				}
			}
			if(target==derived_target)
			{
				return equation.toString();
			}
			else
			{
				equation.delete(0, equation.length());
			}
		}
		return equation.toString();
	}

	
}
