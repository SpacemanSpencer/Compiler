//checks syntax of program based upon tokens classifictation and the precedence programed here
//if syntax fails, error will show you it was not expecting what it got
//if syntax passes, we get a cute message and we start generating quads
import java.io.*;
import java.util.*;


public class syntaxDLL{

	Token firstlink;
	Token lastlink;

	public void insertFront(String tok){
		Token newTok = new Token(tok);

		if(isEmpty()){
			lastlink = newTok;
		}

		newTok.next = firstlink;
		firstlink = newTok;
	}

	//insert in last postition, prob wont need
	public void insertRear(String tok){
		Token newTok = new Token(tok);

		if(isEmpty()){
			firstlink = newTok;
		}
		else
		{
			lastlink.next = newTok;
		}

		lastlink = newTok;
	}

	public boolean isEmpty(){
		return(firstlink == null);
	}

	public void display(){
		Token thetok = firstlink;

		while(thetok != null){
			thetok.display();
			System.out.println("Next Link: " + thetok.next);
			thetok = thetok.next;
			System.out.println();
		}
	}

	public static void main(String[] args)
	{
		System.out.println("Enter the Name of the Program of which needs Quads to be Generated:");
        System.out.println("ie.) 'PgmIn0.txt'...'PgmIn9.txt'");
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
            		list.insertFront(stack[i]);
            	}

            }

            list.display();

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
		System.out.println("current token: " + tok);
	}

	public String toString(){
		return tok;
	}

}




























