import java.io.*;
import java.util.*;

public class SLL
{
	Token firstLink;
	Token lastLink;

	public void insertFirst(String tok)
	{
		Token theNewLink = new Token(tok);

		if(isEmpty())
		{
			lastLink = theNewLink;
		}
		else
		{
			firstLink.previous = theNewLink;
		}

		theNewLink.next = firstLink;
		firstLink = theNewLink;
	}//end of insertFirst

	public void insertLast(String tok)
	{
		Token theNewLink = new Token(tok);

		if(isEmpty())
		{
			firstLink = theNewLink;
		}
		else
		{
			lastLink.next = theNewLink;
			theNewLink.previous = lastLink;
		}

		lastLink = theNewLink;
	}//end of insertLast

	public static void main(String[] args)
	{
		System.out.println("Enter the Name of the raw Syntax file to be analyzed:");
        System.out.println("ie.) 'syntaxRaw.txt'");
        Scanner sc = new Scanner(System.in);
        String fileName = sc.nextLine();
        File fileS = new File("Syntax.txt");
        String line = null; //where input will be stored

        try
        {
        	if(!fileS.exists()){
                fileS.createNewFile();
            }
            //linked list 
			SLL theList = new SLL();
			// FileReader reads text files 
            FileReader fileReader = new FileReader(fileName);
            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);





            System.out.println("Creating Syntax Results in 'Syntax.txt'...");

            while((line = bufferedReader.readLine()) != null)
            {
            	String[] stack = line.split(" ");

            	for(int i = 0; i < stack.length; i++)
            	{
            		theList.insertLast(stack[i]);
            	}

            }// stored data into linked list


            theList.display();














		}//end of try block
        catch(FileNotFoundException ex) 
        {
            System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) 
        {
            System.out.println("Error reading file '" + fileName + "'");
        }


	}//end of main

	public boolean isEmpty()
	{
		return(firstLink == null);
	}//end of isEmpty

	public void display()
	{
		Token theLink = firstLink;

		while(theLink != null)
		{
			theLink.display();

			System.out.println("Next Link: " + theLink.next);

			theLink = theLink.next;

			System.out.println();
		}
	}//end of display

}//end of SLL 

class Token
{
	public String tok;

	public Token next;

	public Token previous;

	public Token(String tok)
	{
		this.tok = tok;
	}

	public void display()
	{
		System.out.println("Current Token: " + tok);
	}
}//end of Token class

class TokenIterator
{
	Token currToken;
	Token prevToken;

	SLL theTokens;

	TokenIterator(SLL theTokens)
	{
		this.theTokens = theTokens;

		currToken = theTokens.firstLink;
		prevToken = theTokens.lastLink;
	}

	public boolean hasNext()
	{
		if(currToken.next != null)
		{
			return true;
		}
		return false;
	}

	public Token next()
	{
		if(hasNext())
		{
			prevToken = currToken;
			currToken = currToken.next;

			return currToken;
		}
		return null;
	}
}//end of iterator