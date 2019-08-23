/*
java program that tests syntax of a program after it has been tokenized in the lexical scanner
program will throw an error and quit if syntax is not followed
item that breaks the syntax is shown

------------------------------------------------------------------------------------------

Grammar for Syntax[S]:

<S> -> $CLASS <ProgramName> $LB <BLOCK> $RB

<BLOCK> -> <constDef> <varDef> <STMT>

<constDef> -> <empty> 
		|	$CONST <constList> <semi>

<constList> -> <constList> <comma> <var> <assignment> <NumLit>
		|	<var> <assignment> <NumLit>

<varDef> -> <empty>
		|	$VAR <varList> <semi>
		|	$INT <intList> <semi>

<varList> -> <varList> <comma> <var>
		|	<var>

<intList> -> <intList> <comma> <var>
		|	<var>

<STMT> -> <empty>
		| <simpleSTMT>
		| <compoundSTMT>
		| <STMTlist>
		| <ifSTMT>

<simpleSTMT> -> <var> <assignment> <EXPR> <semi>

<compoundSTMT> -> $LB <STMTlist> $RB

<STMTlist> -> <STMTlist> <comma> <STMT> 
		|	<STMT> 

<ifSTMT> -> $IF $LP <BE> <STMT>
		| <ifSTMT> $ELSE <STMT>

<BE> -> <EXPR> <relOp> <EXPR>

<EXPR> -> <Math1Op> <TERM>
		| <EXPR> <addOp <TERM>

<Math1Op> -> <addOp>
		| <subOp>

<TERM> -> <FAC>
		| <TERM> <Math2Op> <FAC>

<Math2Op> -> <mOp>
		|	<divOp>

<FAC> -> <var>
		| <NumLit>
		| $LP <EXPR> $RP
------------------------------------------------------------------------------------------
*/


import java.io.*;
import java.util.*;
import java.lang.*; 

public class syntaxTest
{
	public static void main(String[] args)
	{
		System.out.println("Enter the Name of the raw Syntax file to be analyzed:");
        System.out.println("ie.) 'syntaxRaw.txt'");
        Scanner sc = new Scanner(System.in);
        String fileName = sc.nextLine();
        File fileSyn = new File("syntaxResults.txt");
        String line = null; //where input will be stored
        int error = 0;
        boolean stop = false;

        try
        {
        	if(!fileSyn.exists()){
                fileSyn.createNewFile();
            }

			// FileReader reads text files 
            FileReader fileReader = new FileReader(fileName);
            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            System.out.println("Creating file with the Results in 'syntaxResults.txt'...");

            PrintWriter sWriter = new PrintWriter(fileSyn);

            while((line = bufferedReader.readLine()) != null)
            {
            	String[] stack = line.split(" ");

            	for(int i = 0; i < stack.length; i++)
            	{

            		switch(stack[i])
            		{
            			case "$CLASS":
            				if(stack[0].equals("$CLASS"))
            				{
            					sWriter.println("Received Expected FIRST Token: " + stack[i]);
            				}
            				else
            				{
            					error++;
            					stop = true;
            				}
            				break;
            			case "<ProgramName>":
            				if(stack[i-1].equals("$CLASS") && stack[i+1].equals("$LB"))
            				{
            					sWriter.println("Received Expected Token: " + stack[i]);
            				}
            				else{
            					error++;
            					stop = true;
            				}
            				break;
            			case "$LB":
            				if(stack[i-1].equals("<ProgramName>") || stack[i-1].equals("$RP") || stack[i-1].equals("$ELSE"))
            				{
            					sWriter.println("Received Expected Token: " + stack[i]);
            				}
            				else{
            					error++;
            					stop = true;
            				}
            				break;
            			case "$RB":
            				if(i == stack.length - 1)
            				{
            					sWriter.println("Received Expected LAST Token: " + stack[i]);
            				}
            				else if(stack[i-1].equals("<semi>") || stack[i-1].equals("$RB")) 
            				{
            					sWriter.println("Received Expected Token: " + stack[i]);
            				}
            				else{
            					error++;
            					stop = true;
            				}
            				break;
            			case "<semi>":
            				if(stack[i-1].equals("<var>") || stack[i-1].equals("<NumLit>") || stack[i-1].equals("$RP")) 
            				{
            					sWriter.println("Received Expected Token: " + stack[i]);
            				}
            				else{
            					error++;
            					stop = true;
            				}
            				break;
            			case "$CONST":
            				if(stack[i-1].equals("$LB") && stack[i+1].equals("<constVar>") || stack[i-1].equals("<semi>") && stack[i+1].equals("<constVar>")) 
            				{
            					sWriter.println("Received Expected Token: " + stack[i]);
            				}
            				else{
            					error++;
            					stop = true;
            				}
            				break;
            			case "$INT":
            				if(stack[i-1].equals("$LB") && stack[i+1].equals("<var>") || stack[i-1].equals("<semi>") && stack[i+1].equals("<var>")) 
            				{
            					sWriter.println("Received Expected Token: " + stack[i]);
            				}
            				else{
            					error++;
            					stop = true;
            				}
            				break;
            			case "$VAR":
            				if(stack[i-1].equals("$LB") && stack[i+1].equals("<var>") || stack[i-1].equals("<semi>") && stack[i+1].equals("<var>")) 
            				{
            					sWriter.println("Received Expected Token: " + stack[i]);
            				}
            				else{
            					error++;
            					stop = true;
            				}
            				break;
            			case "$IF":
            				if(stack[i-1].equals("<semi>") && stack[i+1].equals("$LP") || stack[i-1].equals("$RB") && stack[i+1].equals("$LP") || stack[i-1].equals("$ELSE") && stack[i+1].equals("$LP") ) 
            				{
            					sWriter.println("Received Expected Token: " + stack[i]);
            				}
            				else{
            					error++;
            					stop = true;
            				}
            				break;
            			case "$ELSE":
            				if(stack[i-1].equals("$RB") && stack[i+1].equals("$LB") || stack[i-1].equals("$RB") && stack[i+1].equals("$IF") ) 
            				{
            					sWriter.println("Received Expected Token: " + stack[i]);
            				}
            				else{
            					error++;
            					stop = true;
            				}
            				break;
            			case "<constVar>":
            				if(stack[i-1].equals("$CONST") && stack[i+1].equals("<assignment>") || stack[i-1].equals("$CONST") && stack[i+1].equals("<semi>")) 
            				{
            					sWriter.println("Received Expected Token: " + stack[i]);
            				}
            				else{
            					error++;
            					stop = true;
            				}
            				break;
            			case "<var>":
            				if(stack[i-1].equals("$VAR") && stack[i+1].equals("<assignment>") || stack[i-1].equals("$VAR") && stack[i+1].equals("<comma>") || stack[i-1].equals("<semi>") && stack[i+1].equals("<assignment>") || stack[i-1].equals("$INT") && stack[i+1].equals("<assignment>") || stack[i-1].equals("$INT") && stack[i+1].equals("<comma>") || stack[i-1].equals("<comma>") && stack[i+1].equals("<comma>") || stack[i-1].equals("<comma>") && stack[i+1].equals("<semi>") || stack[i-1].equals("$LB") && stack[i+1].equals("<assignment>")) 
            				{
            					sWriter.println("Received Expected Token: " + stack[i]);
            				}
            				else if(stack[i-1].equals("<addOp>") && stack[i+1].equals("$RP") || stack[i-1].equals("<subOp>") && stack[i+1].equals("$RP") || stack[i-1].equals("<mOp>") && stack[i+1].equals("$RP") || stack[i-1].equals("<relOp>") && stack[i+1].equals("$RP") || stack[i-1].equals("<mOp>") && stack[i+1].equals("<subOp>") || stack[i-1].equals("<mOp>") && stack[i+1].equals("<addOp>") || stack[i-1].equals("<mOp>") && stack[i+1].equals("<semi>") || stack[i-1].equals("<subOp>") && stack[i+1].equals("<semi>") || stack[i-1].equals("<addOp>") && stack[i+1].equals("<semi>"))
            				{
            					sWriter.println("Received Expected Token: " + stack[i]);
            				}
            				else if(stack[i-1].equals("$LP") && stack[i+1].equals("<addOp>") || stack[i-1].equals("$LP") && stack[i+1].equals("<subOp>") || stack[i-1].equals("$LP") && stack[i+1].equals("<mOp>") ||stack[i-1].equals("$LP") && stack[i+1].equals("<relOp>") || stack[i-1].equals("<assignment>") && stack[i+1].equals("<mOp>") || stack[i-1].equals("<assignment>") && stack[i+1].equals("<addOp>") || stack[i-1].equals("<assignment>") && stack[i+1].equals("<subOp>"))
            				{
            					sWriter.println("Received Expected Token: " + stack[i]);
            				}
            				else{
            					error++;
            					stop = true;
            				}
            				break;
            			case "<NumLit>":
            				if(stack[i-1].equals("$VAR") && stack[i+1].equals("<assignment>") || stack[i-1].equals("$VAR") && stack[i+1].equals("<comma>") || stack[i-1].equals("<semi>") && stack[i+1].equals("<assignment>") || stack[i-1].equals("$INT") && stack[i+1].equals("<assignment>") || stack[i-1].equals("$INT") && stack[i+1].equals("<comma>") || stack[i-1].equals("<comma>") && stack[i+1].equals("<comma>") || stack[i-1].equals("<comma>") && stack[i+1].equals("<semi>") || stack[i-1].equals("$LB") && stack[i+1].equals("<assignment>") || stack[i-1].equals("<assignment>") && stack[i+1].equals("<semi>")) 
            				{
            					sWriter.println("Received Expected Token: " + stack[i]);
            				}
            				else if(stack[i-1].equals("<addOp>") && stack[i+1].equals("$RP") || stack[i-1].equals("<subOp>") && stack[i+1].equals("$RP") || stack[i-1].equals("<mOp>") && stack[i+1].equals("$RP") || stack[i-1].equals("<relOp>") && stack[i+1].equals("$RP") || stack[i-1].equals("<mOp>") && stack[i+1].equals("<subOp>") || stack[i-1].equals("<mOp>") && stack[i+1].equals("<addOp>") || stack[i-1].equals("<mOp>") && stack[i+1].equals("<semi>") || stack[i-1].equals("<subOp>") && stack[i+1].equals("<semi>") || stack[i-1].equals("<addOp>") && stack[i+1].equals("<semi>"))
            				{
            					sWriter.println("Received Expected Token: " + stack[i]);
            				}
            				else if(stack[i-1].equals("$LP") && stack[i+1].equals("<addOp>") || stack[i-1].equals("$LP") && stack[i+1].equals("<subOp>") || stack[i-1].equals("$LP") && stack[i+1].equals("<mOp>") ||stack[i-1].equals("$LP") && stack[i+1].equals("<relOp>") || stack[i-1].equals("<assignment>") && stack[i+1].equals("<mOp>") || stack[i-1].equals("<subOp>") && stack[i+1].equals("<addOp>") || stack[i-1].equals("<addOp>") && stack[i+1].equals("<subOp>"))
            				{
            					sWriter.println("Received Expected Token: " + stack[i]);
            				}
            				else{
            					error++;
            					stop = true;
            				}
            				break;
            			case "$LP":
            				if(stack[i-1].equals("$IF") && stack[i+1].equals("<var>") || stack[i-1].equals("$IF") && stack[i+1].equals("<NumLit>"))
            				{
            					sWriter.println("Received Expected Token: " + stack[i]);
            				}
            				else if(stack[i-1].equals("<assignment>") && stack[i+1].equals("<var>") || stack[i-1].equals("<mOp>") && stack[i+1].equals("<var>") || stack[i-1].equals("<subOp>") && stack[i+1].equals("<var>") || stack[i-1].equals("<addOp>") && stack[i+1].equals("<var>") || stack[i-1].equals("<assignment>") && stack[i+1].equals("<NumLit>") || stack[i-1].equals("<mOp>") && stack[i+1].equals("<NumLit>") || stack[i-1].equals("<subOp>") && stack[i+1].equals("<NumLit>") || stack[i-1].equals("<addOp>") && stack[i+1].equals("<NumLit>") )
            				{
            					sWriter.println("Received Expected Token: " + stack[i]);
            				}
            				else{
            					error++;
            					stop = true;
            				}
            				break;
            			case "$RP":
            				if(stack[i-1].equals("<var>") && stack[i+1].equals("$LB") || stack[i-1].equals("<NumLit>") && stack[i+1].equals("$LB"))
            				{
            					sWriter.println("Received Expected Token: " + stack[i]);
            				}
            				else if(stack[i-1].equals("<var>") && stack[i+1].equals("<assignment>") || stack[i-1].equals("<var>") && stack[i+1].equals("<mOp>") || stack[i-1].equals("<var>") && stack[i+1].equals("<subOp>") || stack[i-1].equals("<var>") && stack[i+1].equals("<addOp>") || stack[i-1].equals("<NumLit>") && stack[i+1].equals("<assignment>") || stack[i-1].equals("<NumLit>") && stack[i+1].equals("<mOp>") || stack[i-1].equals("<NumLit>") && stack[i+1].equals("<subOp>") || stack[i-1].equals("<NumLit>") && stack[i+1].equals("<addOp>") )
            				{
            					sWriter.println("Received Expected Token: " + stack[i]);
            				}
            				else{
            					error++;
            					stop = true;
            				}
            				break;
            			case "<comma>":
            				if(stack[i-1].equals("<var>") && stack[i+1].equals("<var>"))
            				{
            					sWriter.println("Received Expected Token: " + stack[i]);
            				}
            				else{
            					error++;
            					stop = true;
            				}
            				break;
            			case "<relOp>":
            				if(stack[i-1].equals("<var>") && stack[i+1].equals("<var>") || stack[i-1].equals("<var>") && stack[i+1].equals("<NumLit>") || stack[i-1].equals("<NumLit>") && stack[i+1].equals("<NumLit>") || stack[i-1].equals("<NumLit>") && stack[i+1].equals("<var>"))
            				{
            					sWriter.println("Received Expected Token: " + stack[i]);
            				}
            				else{
            					error++;
            					stop = true;
            				}
            				break;
            			case "<assignment>":
            				if(stack[i-1].equals("<var>") && stack[i+1].equals("<var>") || stack[i-1].equals("<var>") && stack[i+1].equals("<NumLit>") || stack[i-1].equals("<constVar>") && stack[i+1].equals("<NumLit>") || stack[i-1].equals("<var>") && stack[i+1].equals("$LP") )
            				{
            					sWriter.println("Received Expected Token: " + stack[i]);
            				}
            				else{
            					error++;
            					stop = true;
            				}
            				break;
            			case "<mOp>":
            				if(stack[i-1].equals("<var>") && stack[i+1].equals("<var>") || stack[i-1].equals("<var>") && stack[i+1].equals("<NumLit>") || stack[i-1].equals("<NumLit>") && stack[i+1].equals("<NumLit>") || stack[i-1].equals("<NumLit>") && stack[i+1].equals("<var>")) 
            				{
            					sWriter.println("Received Expected Token: " + stack[i]);
            				}
            				else if(stack[i-1].equals("$RP") && stack[i+1].equals("<var>") || stack[i-1].equals("$RP") && stack[i+1].equals("<NumLit>"))
            				{
            					sWriter.println("Received Expected Token: " + stack[i]);
            				}
            				else if(stack[i-1].equals("<var>") && stack[i+1].equals("$LP") || stack[i-1].equals("<NumLit>") && stack[i+1].equals("$LP"))
            				{
            					sWriter.println("Received Expected Token: " + stack[i]);
            				}
            				else{
            					error++;
            					stop = true;
            				}
            				break;
            			case "<addOp>":
            				if(stack[i-1].equals("<var>") && stack[i+1].equals("<var>") || stack[i-1].equals("<var>") && stack[i+1].equals("<NumLit>") || stack[i-1].equals("<NumLit>") && stack[i+1].equals("<NumLit>") || stack[i-1].equals("<NumLit>") && stack[i+1].equals("<var>")) 
            				{
            					sWriter.println("Received Expected Token: " + stack[i]);
            				}
            				else if(stack[i-1].equals("$RP") && stack[i+1].equals("<var>") || stack[i-1].equals("$RP") && stack[i+1].equals("<NumLit>"))
            				{
            					sWriter.println("Received Expected Token: " + stack[i]);
            				}
            				else if(stack[i-1].equals("<var>") && stack[i+1].equals("$LP") || stack[i-1].equals("<NumLit>") && stack[i+1].equals("$LP"))
            				{
            					sWriter.println("Received Expected Token: " + stack[i]);
            				}
            				else{
            					error++;
            					stop = true;
            				}
            				break;
            			case "<subOp>":
            				if(stack[i-1].equals("<var>") && stack[i+1].equals("<var>") || stack[i-1].equals("<var>") && stack[i+1].equals("<NumLit>") || stack[i-1].equals("<NumLit>") && stack[i+1].equals("<NumLit>") || stack[i-1].equals("<NumLit>") && stack[i+1].equals("<var>")) 
            				{
            					sWriter.println("Received Expected Token: " + stack[i]);
            				}
            				else if(stack[i-1].equals("$RP") && stack[i+1].equals("<var>") || stack[i-1].equals("$RP") && stack[i+1].equals("<NumLit>"))
            				{
            					sWriter.println("Received Expected Token: " + stack[i]);
            				}
            				else if(stack[i-1].equals("<var>") && stack[i+1].equals("$LP") || stack[i-1].equals("<NumLit>") && stack[i+1].equals("$LP"))
            				{
            					sWriter.println("Received Expected Token: " + stack[i]);
            				}
            				else{
            					error++;
            					stop = true;
            				}
            				break;

            			default: //expecting "?<var>?" or anything that is not defined within my grammar
            				error++;
            				stop = true;
            				break;
            		}//switch(stack[i])

            		if(stop == true) //terminate program if error is found
            		{
            			i++; //doing this b/c array that uses 'i' starts at 0 and "normal" people might get confused
            			sWriter.println("Syntax returned an error on the "+ i + " token!");
            			//ALWAYS, ALWAYS, ALWAYS CLOSE FILES!
            			sWriter.close();
            			bufferedReader.close();

            			System.out.print("Syntax Failed!!! Quiting is advised!!!");
            			System.exit(1);
            		}
            	}
            	sWriter.println();
            	sWriter.println("Syntax has completed properly.");
            	sWriter.println("Syntax returned " + error + " errors!");

            }//end of try

            //ALWAYS, ALWAYS, ALWAYS CLOSE FILES!
            sWriter.close();
            bufferedReader.close();

		}//end of try block
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