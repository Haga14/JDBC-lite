package libs;
import java.util.Scanner;
import java.util.ArrayList;
public class InputGetter {
	public static ArrayList badInput = new ArrayList<String>(10);
	
	public static boolean isInt(String input)
	{
		double temp = 0;
		try {
			temp = Double.parseDouble(input);
			if(input.indexOf(".") == -1)
			{
			return true;	
			}
		}
		catch(Exception e)
		{
			
		}

	
		//input.close();
		return false;
	}
	
	public static boolean isDouble(String input)
	{
		boolean isDoube;
		double num = 0;

		try
		{
			num = Double.parseDouble(input);
			//System.out.println("Double!! " + input);
			isDoube = true;
			return true;
			
		}
		catch(Exception e)
		{
			isDoube = false;
		}

	
	
		//input.close();
		return isDoube;
	}
	
	public static boolean isAlphaString(String input)
	{
		String temp = "";
		for(int i = 0; i < input.length(); i++)
		{
			if(isInt(input.charAt(i) + "") || isDouble(input.charAt(i) + "") || isAlpha(input.charAt(i) + ""))
			{
				temp += input.charAt(i);
			}
			else
			{
				return false;
			}
		}
		return true;
	}
	
	
	/*public static boolean isAlphaString(Scanner input)
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
	}*/
	
	
	@SuppressWarnings("deprecation")
	public static boolean isAlpha(String incomingString) {
	    char[] candidates = incomingString.toCharArray();

	    for (char candidate : candidates) {
	        if(!Character.isLetter(candidate) & !Character.isWhitespace(candidate)) 
	        {
	            return false;
	        }
	        
	    }

	    return true;
	}

}
