package libs;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputGetterd {

	
	public static int getInt(Scanner input)
	{
		System.out.println("Please enter a positive integer value such as: 20");
		boolean isInt = false;
		int num = 0;
		
		do
		{
			try
			{
				num = input.nextInt();
				break;
			}
			catch(InputMismatchException e)
			{
				isInt = false;
				System.out.println("Invalid entry, please try again.\nAn integer is "
						+ "is a non-decimal, non-fractional number.");
				break;
			}
		}while(isInt == false);
		
		//input.close();
		return num;
	}
	
	public static double getDouble(Scanner input)
	{
		System.out.println("Please enter a positive Real Number value such as: 20.001 or 20");
		boolean isDoube = false;
		double num = 0;
		
		do
		{
			try
			{
				num = input.nextDouble();
				if(num < 0)
				{
					System.out.println("Invalid entry, please try again.\nThis time enter a positive Real Number. A real number is "
							+ " similar to an integer, but can be a decimal or fractional number. A positive real number is greater than 0.");
					isDoube = false;
				}
				else
				{
					isDoube = true;
				}
			}
			catch(InputMismatchException e)
			{
				isDoube = false;
				System.out.println("Invalid entry, please try again.\nThis time enter a positive integer. An integer is "
						+ "is a non-decimal, non-fractional number, a positive integer is greater than 0.");
			}
		}while(isDoube == false);
		
		
		//input.close();
		return num;
	}
	
	public static String getString(Scanner input)
	{
		return input.next();
	}
	
	
	public static String getAlphaString(Scanner input)
	{
		String incoming;// = input.next();
		System.out.println("Please enter letters only!");
		while(true)
		{
			incoming = input.nextLine();
			if(!isAlpha(incoming))
			{
				try
				{
					if(Integer.parseInt(incoming) == -1)
					{
						input.close();
						return incoming;
					}
				}
				catch(Exception e)
				{
					System.out.println("Please enter only letters and nothing else!");
					continue;
				}
			} 
			else if(isAlpha(incoming))
			{
				break;
			}
			
			
		}
		
	//	input.close();
		return incoming;
	}
	@SuppressWarnings("deprecation")
	public static boolean isAlpha(String incomingString) {
	    char[] candidates = incomingString.toCharArray();

	    for (char candidate : candidates) {
	        if(!Character.isLetter(candidate) & !Character.isSpace(candidate)) 
	        {
	            return false;
	        }
	        
	    }

	    return true;
	}

}
