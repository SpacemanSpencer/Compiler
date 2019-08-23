//checks syntax of program based upon tokens classifictation and the precedence hard programed here
//if syntax fails, error counter will show how many errors
//if syntax passes, we get 0 errors
import java.io.*;
import java.util.*;


public class syntaxDLL{

	Token firstlink;
	Token lastlink;

	/*public void insertFront(String tok){
		Token newTok = new Token(tok);

		if(isEmpty()){
			lastlink = newTok;
		}
		else
		{
			firstlink.prev = newTok;
		}

		newTok.next = firstlink;
		firstlink = newTok;
	}*/

	public void insert(String tok){
		Token newTok = new Token(tok);

		if(isEmpty()){
			firstlink = newTok;
		}
		else
		{
			lastlink.next = newTok;
			newTok.prev = lastlink;
		}

		lastlink = newTok;
	}

	public boolean isEmpty(){
		return(firstlink == null);
	}

	public void display(){
		Token thetok = firstlink;

		try
        {

			File file1 = new File("Syntax.txt");

			if(!file1.exists()){
                file1.createNewFile();
            }

            PrintWriter writer1 = new PrintWriter(file1);

            while(thetok != null){
				thetok.display();

				switch(thetok)
				{
					case "<ProgramName>":
						System.out.println("Next Token: " + thetok.next +"\n");
						writer1.println("Next Token: " + thetok.next +"\n");
						thetok = thetok.next;
						break;
				}
				//System.out.println("Next Token: " + thetok.next +"\n");
				//writer1.println("Next Token: " + thetok.next +"\n");
				//thetok = thetok.next;

			




			}
		}
        catch(FileNotFoundException ex) 
        {
            System.out.println("Unable to open file ");                
        }
        catch(IOException ex) 
        {
            System.out.println("Error reading file ");
        }
		
	}

	public void syntaxCheck(){
		Token thetok = firstlink;
		int errors = 0;

		try
        {

			File file2 = new File("Syntax.txt");

			if(!file2.exists()){
                file2.createNewFile();
            }

            PrintWriter writer2 = new PrintWriter(file2);

		System.out.println("checking syntax...\n");
		while(thetok != null)
		{
			//handles start
			if(Token.thetok.equals("$CLASS")) //"$CLASS"
			{
				System.out.print(thetok + " < "); 
				thetok.prev = thetok;
				thetok = thetok.next;
				System.out.print(thetok);
			}
			//handles pgm names  //"$CLASS"    "<ProgramName>"
			else if(thetok.prev.equals(thetok.prev) && thetok.equals(thetok))
			{
				System.out.print(thetok);
				thetok.prev = thetok;
				thetok = thetok.next;
				System.out.print(thetok);
			}
			//handles '{'
			else if(thetok.prev.equals("<ProgramName>") && thetok.equals("$LB") || thetok.prev.equals("$RP") && thetok.equals("$LB") || thetok.prev.equals("$ELSE") && thetok.equals("$LB") || thetok.prev.equals("$LB") && thetok.equals("$LB")  )
			{
				thetok.prev = thetok;
				thetok = thetok.next;
			}
			//handles '}'
			else if(thetok.prev.equals("<semi>") && thetok.equals("$RB") || thetok.prev.equals("$RB") && thetok.equals("$RB") )
			{
				thetok.prev = thetok;
				thetok = thetok.next;
			}
			//handles keyword CONST
			else if(thetok.prev.equals("$LB") && thetok.equals("$CONST") || thetok.prev.equals("<semi>") && thetok.equals("$CONST") )
			{
				thetok.prev = thetok;
				thetok = thetok.next;
			}
			//handles keyword INT
			else if(thetok.prev.equals("$LB") && thetok.equals("$INT") ||thetok.prev.equals("<semi>") && thetok.equals("$INT"))
			{
				thetok.prev = thetok;
				thetok = thetok.next;
			}
			//handles keyword VAR
			else if(thetok.prev.equals("$LB") && thetok.equals("$VAR") ||thetok.prev.equals("<semi>") && thetok.equals("$VAR"))
			{
				thetok.prev = thetok;
				thetok = thetok.next;
			}
			//handles constVar
			else if(thetok.prev.equals("$CONST") && thetok.equals("<constVar>"))
			{
				thetok.prev = thetok;
				thetok = thetok.next;
			}
			//handles NumLit
			else if(thetok.prev.equals("<assignment>") && thetok.equals("<NumLit>") || thetok.prev.equals("<addOp>") && thetok.equals("<NumLit>") || thetok.prev.equals("<subOp>") && thetok.equals("<NumLit>") || thetok.prev.equals("<mOp>") && thetok.equals("<NumLit>") || thetok.prev.equals("<relOp>") && thetok.equals("<NumLit>") || thetok.prev.equals("$LP") && thetok.equals("<NumLit>") )
			{
				thetok.prev = thetok;
				thetok = thetok.next;
			}
			//handles variables
			else if(thetok.prev.equals("<assignment>") && thetok.equals("<var>") || thetok.prev.equals("<addOp>") && thetok.equals("<var>") || thetok.prev.equals("<subOp>") && thetok.equals("<var>") || thetok.prev.equals("<mOp>") && thetok.equals("<var>") || thetok.prev.equals("<relOp>") && thetok.equals("<var>") || thetok.prev.equals("$LP") && thetok.equals("<var>") || thetok.prev.equals("$LB") && thetok.equals("<var>") || thetok.prev.equals("<semi>") && thetok.equals("<var>"))
			{
				thetok.prev = thetok;
				thetok = thetok.next;
			}
			//handles assignment
			else if(thetok.prev.equals("<constVar>") && thetok.equals("<assignment>") || thetok.prev.equals("<var>") && thetok.equals("<assignment>"))
			{
				thetok.prev = thetok;
				thetok = thetok.next;
			}			
			//handles semi
			else if(thetok.prev.equals("<var>") && thetok.equals("<semi>") || thetok.prev.equals("<NumLit>") && thetok.equals("<semi>") || thetok.prev.equals("$RP") && thetok.equals("<semi>") )
			{
				thetok.prev = thetok;
				thetok = thetok.next;
			}
			//handles '('
			else if(thetok.prev.equals("$IF") && thetok.equals("$LP") || thetok.prev.equals("<assignment>") && thetok.equals("$LP") || thetok.prev.equals("<addOp>") && thetok.equals("$LP") || thetok.prev.equals("<subOp>") && thetok.equals("$LP") || thetok.prev.equals("<mOp>") && thetok.equals("$LP") || thetok.prev.equals("<relOp>") && thetok.equals("$LP") || thetok.prev.equals("$LP") && thetok.equals("$LP") || thetok.prev.equals("$LB") && thetok.equals("$LP") || thetok.prev.equals("<semi>") && thetok.equals("$LP"))
			{
				thetok.prev = thetok;
				thetok = thetok.next;
			}
			//handles ')'
			else if(thetok.prev.equals("<NumLit>") && thetok.equals("$RP") || thetok.prev.equals("<var>") && thetok.equals("$RP"))
			{
				thetok.prev = thetok;
				thetok = thetok.next;
			}
			//handles addOp
			else if(thetok.prev.equals("<NumLit>") && thetok.equals("<addOp>") || thetok.prev.equals("<var>") && thetok.equals("<addOp>") || thetok.prev.equals("$RP") && thetok.equals("<addOp>"))
			{
				thetok.prev = thetok;
				thetok = thetok.next;
			}
			//handles subOp
			else if(thetok.prev.equals("<NumLit>") && thetok.equals("<subOp>") || thetok.prev.equals("<var>") && thetok.equals("<subOp>") || thetok.prev.equals("$RP") && thetok.equals("<subOp>"))
			{
				thetok.prev = thetok;
				thetok = thetok.next;
			}
			//handles mOp
			else if(thetok.prev.equals("<NumLit>") && thetok.equals("<mOp>") || thetok.prev.equals("<var>") && thetok.equals("<mOp>") || thetok.prev.equals("$RP") && thetok.equals("<mOp>"))
			{
				thetok.prev = thetok;
				thetok = thetok.next;
			}
			//handles relOp
			else if(thetok.prev.equals("<NumLit>") && thetok.equals("<relOp>") || thetok.prev.equals("<var>") && thetok.equals("<relOp>") || thetok.prev.equals("$RP") && thetok.equals("<relOp>"))
			{
				thetok.prev = thetok;
				thetok = thetok.next;
			}
			//handles comma
				else if(thetok.prev.equals("<var>") && thetok.equals("<comma>"))
				{
					thetok.prev = thetok;
					thetok = thetok.next;
				}
				//handles if
				else if(thetok.prev.equals("<semi>") && thetok.equals("$IF") || thetok.prev.equals("$RB") && thetok.equals("$IF"))
				{
					thetok.prev = thetok;
					thetok = thetok.next;
				}
				//handles else
				else if(thetok.prev.equals("$RB") && thetok.equals("$ELSE"))
				{
					thetok.prev = thetok;
					thetok = thetok.next;
				}
				//handles unrecognized
				else if(thetok.equals("?<var>?"))
				{
					writer2.println("Syntax Error: Unrecognized syntax, found: ?<var>?");
					thetok.prev = thetok;
					thetok = thetok.next;
				}
				else  //when error'd, increment
				{
					writer2.println("error @: " + thetok);
					errors++;
				}
			}//end while

			if(errors == 0)
			{
				writer2.println("Syntax returns with "+ errors + " found");
			}
			else
			{
				writer2.println("Syntax returns with "+ errors + " found");
			}

		}
        catch(FileNotFoundException ex) 
        {
            System.out.println("Unable to open file ");                
        }
        catch(IOException ex) 
        {
            System.out.println("Error reading file ");
        }
	}

	

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
            //linked list for checking syntax
            syntaxDLL list = new syntaxDLL();

            //FileWriter writes to text file 
            PrintWriter writerS = new PrintWriter(fileS);
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);
            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            System.out.println("Creating Syntax Results in 'Syntax.txt'...");

            while((line = bufferedReader.readLine()) != null)
            {
            	String[] stack = line.split(" ");

            	for(int i = 0; i < stack.length; i++)
            	{
            		list.insert(stack[i]);
            	}

            }// stored data into linked list

            //shows the linked list, current token: XXX 
            					//   next token: YYY
            list.display();

            //now error checking
            list.syntaxCheck();

        }
        catch(FileNotFoundException ex) 
        {
            System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) 
        {
            System.out.println("Error reading file '" + fileName + "'");
        }

	}

}


class Token {
	public String tok;

	public Token next;
	public Token prev;

	public Token(String tok){
		this.tok = tok;
	}

	public void display(){
		try
        {

			File file3 = new File("Syntaxshown.txt");

			if(!file3.exists()){
                file3.createNewFile();
            }

            PrintWriter writer3 = new PrintWriter(file3);

            switch(tok)
            {
            	case "$CLASS":
            		writer3.println("Start Token: " + tok);
            		System.out.println("Start Token: " + tok);
            		break;
            	case "<ProgramName>":
            		writer3.println("Current Token: " + tok);
            		System.out.println("Current Token: " + tok);
            		break;
            }



            /*if(tok.equals("$CLASS"))
            {
            	writer3.println("Start Token: " + tok);
            	System.out.println("Start Token: " + tok);
            	state++;
            }*/

            if(!tok.equals("$CLASS"))
            {
            	writer3.println("Current Token: " + tok);
            	System.out.println("Current Token: " + tok);
            }
            
		}
        catch(FileNotFoundException ex) 
        {
            System.out.println("Unable to open file ");                
        }
        catch(IOException ex) 
        {
            System.out.println("Error reading file ");
        }
		
	}

	public String toString(){
		return tok;
	}

}


// An iterator provides an easy way to cycle through all
// the objects in a LinkedList

class TokenIterator{
	
	Token currTok; // The current focus Token
	Token prevTok; // The previous Token
	
	DoubleEndedLinkedList theTokens;
	
	// hasNext, next, remove are common iterator methods
	
	NeighborIterator(DoubleEndedLinkedList theTokens){
		
		this.theTokens = theTokens;
		
		currTok = theTokens.firstLink;
		prevTok = theTokens.lastLink;
		
	}
	
	public boolean hasNext(){
		
		if(currTok.next != null){
			
			return true;
			
		}
		
		return false;
		
	}
	
	public Neighbor next(){
		
		if(hasNext()){
			
			prevTok = currTok;
			currTok = currTok.next;
			
			return currTok;
			
		}
		
		return null;
		
	}
	
}	


















