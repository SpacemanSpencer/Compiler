/*
First Pass*
Creates a file that holds Token Table 
        a file that has Tokens ONLY
	a file that has Programs Syntax
*/

import java.io.*;
import java.util.*;

public class tokenizer 
{
    
    public static void main(String [] args) 
    {
        System.out.println("Starting tokenizer....");
    	System.out.println("Enter the Name of the File to tokenize: ");
        System.out.println("ie.) 'PgmOut.txt'");
    	Scanner sc = new Scanner(System.in);
        String fileName = sc.nextLine();
        File file = new File("tokenTable.txt");
        File rawFile1 = new File("tokensRaw.txt");
        File rawFile2 = new File("syntaxRaw.txt");

        String line = null; //where input will be stored

        try 
        {
            if(!file.exists()){
                file.createNewFile();
            }

            if(!rawFile1.exists()){
                rawFile1.createNewFile();
            }

            if(!rawFile2.exists()){
                rawFile2.createNewFile();
            }

            //FileWriter writes to text file 
            PrintWriter writer = new PrintWriter(file);  //writes table
            PrintWriter writerR = new PrintWriter(rawFile1); //writes raw tokens
            PrintWriter writerS = new PrintWriter(rawFile2); //writes raw classifications - syntax file
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);
            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null)
            {
            	//System.out.println("file reads:");
                //System.out.println(line); //writes what was in file onto terminal
                System.out.println("Creating Token Table in 'tokenTable.txt'...");
                System.out.println("Creating Raw Tokens in 'tokensRaw.txt'...");
                System.out.println("Creating Syntax file in 'syntaxRaw.txt'...");
                String copy = line;
                String temp = line;
                writer.println("Tokens\t\tClassification");

				char[] letts = line.toCharArray(); //converts string to char array
				
				for(int i = 0; i < letts.length; i++)
				{
					//System.out.println(toks[i]); //prints array 1 char per line
                    
					 // switch statement with string data type 
        			switch (copy.substring(i, i+1)) 
        			{
        				case "+": 
            				writer.println(letts[i] + "\t\t<addOp>");
                            writerR.println(letts[i]); 
                            writerS.print("<addOp> ");
            				break;
            			case "-": 
            				writer.println(letts[i] + "\t\t<subOp>");
                            writerR.println(letts[i]); 
                            writerS.print("<subOp> ");
            				break; 
        				case "/": 
            				//writer.println(letts[i] + "\t\t<mOp>");
                            //writerR.println(letts[i]);
                            //writerS.print("<mOp> ");
            				//break; 
        				case "*": 
            				writer.println(letts[i] + "\t\t<mOp>");
                            writerR.println(letts[i]);
                            writerS.print("<mOp> ");
            				break; 
        				case "=": 
            				writer.println(letts[i] + "\t\t<assignment>");
                            writerR.println(letts[i]);
                            writerS.print("<assignment> ");
            				break; 
        				case ";": 
            				writer.println(letts[i] + "\t\t<semi>"); 
                            writerR.println(letts[i]); 
                            writerS.print("<semi> ");
            				break;
            			case ",": 
            				writer.println(letts[i] + "\t\t<comma>");
                            writerR.println(letts[i]);
                            writerS.print("<comma> ");
            				break; 
        				case "{": 
            				writer.println(letts[i] + "\t\t$LB");
                            writerR.println(letts[i]);
                            writerS.print("$LB ");
            				break; 
        				case "}": 
            				writer.println(letts[i] + "\t\t$RB"); 
                            writerR.println(letts[i]);
                            writerS.print("$RB ");
            				break; 
        				case "<": 
            				//writer.println(letts[i] + "\t\t<relOp>"); 
                            //writerR.println(letts[i]);
                            //writerS.print("<relOp> "); 
            				//break;
            			case ">": 
            				writer.println(letts[i] + "\t\t<relOp>"); 
                            writerR.println(letts[i]);
                            writerS.print("<relOp> "); 
            				break;
            			case "(": 
            				writer.println(letts[i] + "\t\t$LP");
                            writerR.println(letts[i]); 
                            writerS.print("$LP ");
            				break;
            			case ")": 
            				writer.println(letts[i] + "\t\t$RP");
                            writerR.println(letts[i]); 
                            writerS.print("$RP ");
            				break;
                            //
                        case "A":
                        case "B":
                        case "C": //keywords begins with C
                            if(letts[i+1] == 'L')
                            {
                                if(letts[i+2] == 'A'){if(letts[i+3] == 'S'){if(letts[i+4] == 'S'){writer.println(line.substring(i, i+5)+"\t\t$CLASS");  writerR.println(line.substring(i, i+5));  writerS.print("$CLASS "); i+=4;}}} 
                            }
                            else if(letts[i+1] == 'O')
                            {
                                if(letts[i+2] == 'N'){if(letts[i+3] == 'S'){if(letts[i+4] == 'T'){writer.println(line.substring(i, i+5)+"\t\t$CONST");  writerR.println(line.substring(i, i+5));   writerS.print("$CONST "); i+=4;}}}
                            }
                            else
                            {
                                if(letts[i-1]== 'T')//checks if var is CONST
                                {
                                    if(letts[i-2] == 'S'){if(letts[i-3] == 'N'){if(letts[i-4] == 'O'){if(letts[i-5] == 'C'){writer.println(letts[i] + "\t\t<constVar>");  writerR.println(letts[i]);    writerS.print("<constVar> ");}}}}
                                }
                                else 
                                {
                                    writer.println(letts[i] + "\t\t<var>");  writerR.println(letts[i]);  writerS.print("<var> ");
                                }
                            }
                            break;
                        case "I": //keyword begins with I
                            if(letts[i+1] == 'F')
                            {
                                writer.println(line.substring(i, i+2)+"\t\t$IF");  writerR.println(line.substring(i, i+2));   writerS.print("$IF ");  i+=1;
                            }
                            else if(letts[i+1] == 'N')
                            {
                                if(letts[i+2] == 'T'){writer.println(line.substring(i, i+3)+"\t\t$INT");  writerR.println(line.substring(i, i+3));    writerS.print("$INT "); i+=2;}
                            }
                            else
                            {
                                if(letts[i-1]== 'T')//checks if CONST
                                {
                                    if(letts[i-2] == 'S'){if(letts[i-3] == 'N'){if(letts[i-4] == 'O'){if(letts[i-5] == 'C'){writer.println(letts[i] + "\t\t<constVar>");  writerR.println(letts[i]);   writerS.print("<constVar> ");}}}}
                                }
                                else 
                                {
                                    writer.println(letts[i] + "\t\t<var>");  writerR.println(letts[i]);    writerS.print("<var> ");
                                }
                            }
                            break;
                        case "E": //keyword begins with E
                            if(letts[i+1] == 'L')
                            {
                                if(letts[i+2] == 'S'){if(letts[i+3] == 'E'){writer.println(line.substring(i, i+4)+"\t\t$ELSE");  writerR.println(line.substring(i, i+4));    writerS.print("$ELSE "); i+=3;}}
                            }
                            else
                            {
                                if(letts[i-1]== 'T')//checks if CONST
                                {
                                    if(letts[i-2] == 'S'){if(letts[i-3] == 'N'){if(letts[i-4] == 'O'){if(letts[i-5] == 'C'){writer.println(letts[i] + "\t\t<constVar>");  writerR.println(letts[i]);    writerS.print("<constVar> ");}}}}
                                }
                                else 
                                {
                                    writer.println(letts[i] + "\t\t<var>");  writerR.println(letts[i]);   writerS.print("<var> ");
                                }
                            }
                            break;
                        case "T": //keyword begins with T
                            if(letts[i+1] == 'H')
                            {
                                if(letts[i+2] == 'E'){if(letts[i+3] == 'N'){writer.println(line.substring(i, i+4)+"\t\t$THEN");  writerR.println(line.substring(i, i+4));   writerS.print("$THEN "); i+=3;}}
                            }
                            else
                            {
                                if(letts[i-1]== 'T')//checks if CONST
                                {
                                    if(letts[i-2] == 'S'){if(letts[i-3] == 'N'){if(letts[i-4] == 'O'){if(letts[i-5] == 'C'){writer.println(letts[i] + "\t\t<constVar>");  writerR.println(letts[i]);    writerS.print("<constVar> ");}}}}
                                }
                                else 
                                {
                                    writer.println(letts[i] + "\t\t<var>");  writerR.println(letts[i]);  writerS.print("<var> ");
                                }
                            }
                            break;
                        case "D":
                        //case "E":
                        case "F":
                        case "G":
                        case "H":
                        //case "I":
                        case "J":
                        case "K":
                        case "L":
                        case "M":
                        case "N":
                        case "O":
                        case "P":
                            if(letts[i+1] == 'g')
                            {
                                if(letts[i+2] == 'm'){if(letts[i+3] == '0' || letts[i+3] == '1' ||letts[i+3] == '2'||letts[i+3]=='3'||letts[i+3]=='4'||letts[i+3] == '5'||letts[i+3]=='6'||letts[i+3]=='7'||letts[i+3] == '8'||letts[i+3]=='9'){writer.println(line.substring(i, i+4)+"\t\t<ProgramName>");  writerR.println(line.substring(i, i+4));  writerS.print("<ProgramName> "); i+=3;}}
                            }
                            else
                            {
                                if(letts[i-1]== 'T')//checks if CONST
                                {
                                    if(letts[i-2] == 'S'){if(letts[i-3] == 'N'){if(letts[i-4] == 'O'){if(letts[i-5] == 'C'){writer.println(letts[i] + "\t\t<constVar>");  writerR.println(letts[i]);   writerS.print("<constVar> ");}}}}
                                }
                                else 
                                {
                                    writer.println(letts[i] + "\t\t<var>");  writerR.println(letts[i]);  writerS.print("<var> ");
                                }
                            }
                            break;
                        case "Q":
                        case "R":
                        case "S":
                        //case "T":
                        case "U":
                        case "V":
                            if(letts[i+1] == 'A')
                            {
                                if(letts[i+2] == 'R'){writer.println(line.substring(i, i+3)+"\t\t$VAR");  writerR.println(line.substring(i, i+3));   writerS.print("$VAR ");  i+=2;}
                            }
                            else
                            {
                                if(letts[i-1]== 'T')//checks if CONST
                                {
                                    if(letts[i-2] == 'S'){if(letts[i-3] == 'N'){if(letts[i-4] == 'O'){if(letts[i-5] == 'C'){writer.println(letts[i] + "\t\t<constVar>");  writerR.println(letts[i]);  writerS.print("<constVar> ");}}}}
                                }
                                else 
                                {
                                    writer.println(letts[i] + "\t\t<var>");  writerR.println(letts[i]);  writerS.print("<var> ");
                                }
                            }
                            break;
                        case "W":
                        case "Y":
                        case "X":
                        case "Z":
                            writer.println(letts[i] + "\t\t<var>");  writerR.println(letts[i]);  writerS.print("<var> ");
                            break;
                        case "0":
                            if(letts[i+1] == '0' || letts[i+1] == '1' ||letts[i+1] == '2'||letts[i+1]=='3'||letts[i+1]=='4'||letts[i+1] == '5'||letts[i+1]=='6'||letts[i+1]=='7'||letts[i+1] == '8'||letts[i+1]=='9')
                            {
                                writer.println(line.substring(i, i+2)+"\t\t<NumLit>");  writerR.println(line.substring(i, i+2));  writerS.print("<NumLit> "); i+=1;
                                if(letts[i+2] == '0' || letts[i+2] == '1' ||letts[i+2] == '2'||letts[i+2]=='3'||letts[i+2]=='4'||letts[i+2] == '5'||letts[i+2]=='6'||letts[i+2]=='7'||letts[i+2] == '8'||letts[i+2]=='9'){
                                    writer.println(line.substring(i, i+3)+"\t\t<NumLit>");  writerR.println(line.substring(i, i+3));  writerS.print("<NumLit> ");  i+=2;
                                    if(letts[i+3] == '0' || letts[i+3] == '1' ||letts[i+3] == '2'||letts[i+3]=='3'||letts[i+3]=='4'||letts[i+3] == '5'||letts[i+3]=='6'||letts[i+3]=='7'||letts[i+3] == '8'||letts[i+3]=='9'){
                                        writer.println(line.substring(i, i+4)+"\t\t<NumLit>");  writerR.println(line.substring(i, i+4));  writerS.print("<NumLit> ");  i+=3;
                                        if(letts[i+4] == '0' || letts[i+4] == '1' ||letts[i+4] == '2'||letts[i+4]=='3'||letts[i+4]=='4'||letts[i+4] == '5'||letts[i+4]=='6'||letts[i+4]=='7'||letts[i+4] == '8'||letts[i+4]=='9'){
                                            writer.println(line.substring(i, i+5)+"\t\t<NumLit>");  writerR.println(line.substring(i, i+5));  writerS.print("<NumLit> ");  i+=4;
                                        }
                                    }
                                }
                            }
                            else
                            {
                                writer.println(letts[i] + "\t\t<NumLit>");  writerR.println(letts[i]); writerS.print("<NumLit> ");
                            }
                            break;
                        case "1":
                            if(letts[i+1] == '0' || letts[i+1] == '1' ||letts[i+1] == '2'||letts[i+1]=='3'||letts[i+1]=='4'||letts[i+1] == '5'||letts[i+1]=='6'||letts[i+1]=='7'||letts[i+1] == '8'||letts[i+1]=='9')
                            {
                                writer.println(line.substring(i, i+2)+"\t\t<NumLit>");  writerR.println(line.substring(i, i+2));  writerS.print("<NumLit> "); i+=1;
                                if(letts[i+2] == '0' || letts[i+2] == '1' ||letts[i+2] == '2'||letts[i+2]=='3'||letts[i+2]=='4'||letts[i+2] == '5'||letts[i+2]=='6'||letts[i+2]=='7'||letts[i+2] == '8'||letts[i+2]=='9'){
                                    writer.println(line.substring(i, i+3)+"\t\t<NumLit>");  writerR.println(line.substring(i, i+3));  writerS.print("<NumLit> ");  i+=2;
                                    if(letts[i+3] == '0' || letts[i+3] == '1' ||letts[i+3] == '2'||letts[i+3]=='3'||letts[i+3]=='4'||letts[i+3] == '5'||letts[i+3]=='6'||letts[i+3]=='7'||letts[i+3] == '8'||letts[i+3]=='9'){
                                        writer.println(line.substring(i, i+4)+"\t\t<NumLit>");  writerR.println(line.substring(i, i+4));  writerS.print("<NumLit> ");  i+=3;
                                        if(letts[i+4] == '0' || letts[i+4] == '1' ||letts[i+4] == '2'||letts[i+4]=='3'||letts[i+4]=='4'||letts[i+4] == '5'||letts[i+4]=='6'||letts[i+4]=='7'||letts[i+4] == '8'||letts[i+4]=='9'){
                                            writer.println(line.substring(i, i+5)+"\t\t<NumLit>");  writerR.println(line.substring(i, i+5));  writerS.print("<NumLit> ");  i+=4;
                                        }
                                    }
                                }
                            }
                            else
                            {
                                writer.println(letts[i] + "\t\t<NumLit>");  writerR.println(letts[i]); writerS.print("<NumLit> ");
                            }
                            break;
                        case "2":
                            if(letts[i+1] == '0' || letts[i+1] == '1' ||letts[i+1] == '2'||letts[i+1]=='3'||letts[i+1]=='4'||letts[i+1] == '5'||letts[i+1]=='6'||letts[i+1]=='7'||letts[i+1] == '8'||letts[i+1]=='9')
                            {
                                writer.println(line.substring(i, i+2)+"\t\t<NumLit>");  writerR.println(line.substring(i, i+2));  writerS.print("<NumLit> "); i+=1;
                                if(letts[i+2] == '0' || letts[i+2] == '1' ||letts[i+2] == '2'||letts[i+2]=='3'||letts[i+2]=='4'||letts[i+2] == '5'||letts[i+2]=='6'||letts[i+2]=='7'||letts[i+2] == '8'||letts[i+2]=='9'){
                                    writer.println(line.substring(i, i+3)+"\t\t<NumLit>");  writerR.println(line.substring(i, i+3));  writerS.print("<NumLit> ");  i+=2;
                                    if(letts[i+3] == '0' || letts[i+3] == '1' ||letts[i+3] == '2'||letts[i+3]=='3'||letts[i+3]=='4'||letts[i+3] == '5'||letts[i+3]=='6'||letts[i+3]=='7'||letts[i+3] == '8'||letts[i+3]=='9'){
                                        writer.println(line.substring(i, i+4)+"\t\t<NumLit>");  writerR.println(line.substring(i, i+4));  writerS.print("<NumLit> ");  i+=3;
                                        if(letts[i+4] == '0' || letts[i+4] == '1' ||letts[i+4] == '2'||letts[i+4]=='3'||letts[i+4]=='4'||letts[i+4] == '5'||letts[i+4]=='6'||letts[i+4]=='7'||letts[i+4] == '8'||letts[i+4]=='9'){
                                            writer.println(line.substring(i, i+5)+"\t\t<NumLit>");  writerR.println(line.substring(i, i+5));  writerS.print("<NumLit> ");  i+=4;
                                        }
                                    }
                                }
                            }
                            else
                            {
                                writer.println(letts[i] + "\t\t<NumLit>");  writerR.println(letts[i]); writerS.print("<NumLit> ");
                            }
                            break;
                        case "3":
                            if(letts[i+1] == '0' || letts[i+1] == '1' ||letts[i+1] == '2'||letts[i+1]=='3'||letts[i+1]=='4'||letts[i+1] == '5'||letts[i+1]=='6'||letts[i+1]=='7'||letts[i+1] == '8'||letts[i+1]=='9')
                            {
                                writer.println(line.substring(i, i+2)+"\t\t<NumLit>");  writerR.println(line.substring(i, i+2));  writerS.print("<NumLit> "); i+=1;
                                if(letts[i+2] == '0' || letts[i+2] == '1' ||letts[i+2] == '2'||letts[i+2]=='3'||letts[i+2]=='4'||letts[i+2] == '5'||letts[i+2]=='6'||letts[i+2]=='7'||letts[i+2] == '8'||letts[i+2]=='9'){
                                    writer.println(line.substring(i, i+3)+"\t\t<NumLit>");  writerR.println(line.substring(i, i+3));  writerS.print("<NumLit> ");  i+=2;
                                    if(letts[i+3] == '0' || letts[i+3] == '1' ||letts[i+3] == '2'||letts[i+3]=='3'||letts[i+3]=='4'||letts[i+3] == '5'||letts[i+3]=='6'||letts[i+3]=='7'||letts[i+3] == '8'||letts[i+3]=='9'){
                                        writer.println(line.substring(i, i+4)+"\t\t<NumLit>");  writerR.println(line.substring(i, i+4));  writerS.print("<NumLit> ");  i+=3;
                                        if(letts[i+4] == '0' || letts[i+4] == '1' ||letts[i+4] == '2'||letts[i+4]=='3'||letts[i+4]=='4'||letts[i+4] == '5'||letts[i+4]=='6'||letts[i+4]=='7'||letts[i+4] == '8'||letts[i+4]=='9'){
                                            writer.println(line.substring(i, i+5)+"\t\t<NumLit>");  writerR.println(line.substring(i, i+5));  writerS.print("<NumLit> ");  i+=4;
                                        }
                                    }
                                }
                            }
                            else
                            {
                                writer.println(letts[i] + "\t\t<NumLit>");  writerR.println(letts[i]); writerS.print("<NumLit> ");
                            }
                            break;
                        case "4":
                            if(letts[i+1] == '0' || letts[i+1] == '1' ||letts[i+1] == '2'||letts[i+1]=='3'||letts[i+1]=='4'||letts[i+1] == '5'||letts[i+1]=='6'||letts[i+1]=='7'||letts[i+1] == '8'||letts[i+1]=='9')
                            {
                                writer.println(line.substring(i, i+2)+"\t\t<NumLit>");  writerR.println(line.substring(i, i+2));  writerS.print("<NumLit> "); i+=1;
                                if(letts[i+2] == '0' || letts[i+2] == '1' ||letts[i+2] == '2'||letts[i+2]=='3'||letts[i+2]=='4'||letts[i+2] == '5'||letts[i+2]=='6'||letts[i+2]=='7'||letts[i+2] == '8'||letts[i+2]=='9'){
                                    writer.println(line.substring(i, i+3)+"\t\t<NumLit>");  writerR.println(line.substring(i, i+3));  writerS.print("<NumLit> ");  i+=2;
                                    if(letts[i+3] == '0' || letts[i+3] == '1' ||letts[i+3] == '2'||letts[i+3]=='3'||letts[i+3]=='4'||letts[i+3] == '5'||letts[i+3]=='6'||letts[i+3]=='7'||letts[i+3] == '8'||letts[i+3]=='9'){
                                        writer.println(line.substring(i, i+4)+"\t\t<NumLit>");  writerR.println(line.substring(i, i+4));  writerS.print("<NumLit> ");  i+=3;
                                        if(letts[i+4] == '0' || letts[i+4] == '1' ||letts[i+4] == '2'||letts[i+4]=='3'||letts[i+4]=='4'||letts[i+4] == '5'||letts[i+4]=='6'||letts[i+4]=='7'||letts[i+4] == '8'||letts[i+4]=='9'){
                                            writer.println(line.substring(i, i+5)+"\t\t<NumLit>");  writerR.println(line.substring(i, i+5));  writerS.print("<NumLit> ");  i+=4;
                                        }
                                    }
                                }
                            }
                            else
                            {
                                writer.println(letts[i] + "\t\t<NumLit>");  writerR.println(letts[i]); writerS.print("<NumLit> ");
                            }
                            break;
                        case "5":
                            if(letts[i+1] == '0' || letts[i+1] == '1' ||letts[i+1] == '2'||letts[i+1]=='3'||letts[i+1]=='4'||letts[i+1] == '5'||letts[i+1]=='6'||letts[i+1]=='7'||letts[i+1] == '8'||letts[i+1]=='9')
                            {
                                writer.println(line.substring(i, i+2)+"\t\t<NumLit>");  writerR.println(line.substring(i, i+2));  writerS.print("<NumLit> "); i+=1;
                                if(letts[i+2] == '0' || letts[i+2] == '1' ||letts[i+2] == '2'||letts[i+2]=='3'||letts[i+2]=='4'||letts[i+2] == '5'||letts[i+2]=='6'||letts[i+2]=='7'||letts[i+2] == '8'||letts[i+2]=='9'){
                                    writer.println(line.substring(i, i+3)+"\t\t<NumLit>");  writerR.println(line.substring(i, i+3));  writerS.print("<NumLit> ");  i+=2;
                                    if(letts[i+3] == '0' || letts[i+3] == '1' ||letts[i+3] == '2'||letts[i+3]=='3'||letts[i+3]=='4'||letts[i+3] == '5'||letts[i+3]=='6'||letts[i+3]=='7'||letts[i+3] == '8'||letts[i+3]=='9'){
                                        writer.println(line.substring(i, i+4)+"\t\t<NumLit>");  writerR.println(line.substring(i, i+4));  writerS.print("<NumLit> ");  i+=3;
                                        if(letts[i+4] == '0' || letts[i+4] == '1' ||letts[i+4] == '2'||letts[i+4]=='3'||letts[i+4]=='4'||letts[i+4] == '5'||letts[i+4]=='6'||letts[i+4]=='7'||letts[i+4] == '8'||letts[i+4]=='9'){
                                            writer.println(line.substring(i, i+5)+"\t\t<NumLit>");  writerR.println(line.substring(i, i+5));  writerS.print("<NumLit> ");  i+=4;
                                        }
                                    }
                                }
                            }
                            else
                            {
                                writer.println(letts[i] + "\t\t<NumLit>");  writerR.println(letts[i]); writerS.print("<NumLit> ");
                            }
                            break;
                        case "6":
                            if(letts[i+1] == '0' || letts[i+1] == '1' ||letts[i+1] == '2'||letts[i+1]=='3'||letts[i+1]=='4'||letts[i+1] == '5'||letts[i+1]=='6'||letts[i+1]=='7'||letts[i+1] == '8'||letts[i+1]=='9')
                            {
                                writer.println(line.substring(i, i+2)+"\t\t<NumLit>");  writerR.println(line.substring(i, i+2));  writerS.print("<NumLit> "); i+=1;
                                if(letts[i+2] == '0' || letts[i+2] == '1' ||letts[i+2] == '2'||letts[i+2]=='3'||letts[i+2]=='4'||letts[i+2] == '5'||letts[i+2]=='6'||letts[i+2]=='7'||letts[i+2] == '8'||letts[i+2]=='9'){
                                    writer.println(line.substring(i, i+3)+"\t\t<NumLit>");  writerR.println(line.substring(i, i+3));  writerS.print("<NumLit> ");  i+=2;
                                    if(letts[i+3] == '0' || letts[i+3] == '1' ||letts[i+3] == '2'||letts[i+3]=='3'||letts[i+3]=='4'||letts[i+3] == '5'||letts[i+3]=='6'||letts[i+3]=='7'||letts[i+3] == '8'||letts[i+3]=='9'){
                                        writer.println(line.substring(i, i+4)+"\t\t<NumLit>");  writerR.println(line.substring(i, i+4));  writerS.print("<NumLit> ");  i+=3;
                                        if(letts[i+4] == '0' || letts[i+4] == '1' ||letts[i+4] == '2'||letts[i+4]=='3'||letts[i+4]=='4'||letts[i+4] == '5'||letts[i+4]=='6'||letts[i+4]=='7'||letts[i+4] == '8'||letts[i+4]=='9'){
                                            writer.println(line.substring(i, i+5)+"\t\t<NumLit>");  writerR.println(line.substring(i, i+5));  writerS.print("<NumLit> ");  i+=4;
                                        }
                                    }
                                }
                            }
                            else
                            {
                                writer.println(letts[i] + "\t\t<NumLit>");  writerR.println(letts[i]); writerS.print("<NumLit> ");
                            }
                            break;
                        case "7":
                            if(letts[i+1] == '0' || letts[i+1] == '1' ||letts[i+1] == '2'||letts[i+1]=='3'||letts[i+1]=='4'||letts[i+1] == '5'||letts[i+1]=='6'||letts[i+1]=='7'||letts[i+1] == '8'||letts[i+1]=='9')
                            {
                                writer.println(line.substring(i, i+2)+"\t\t<NumLit>");  writerR.println(line.substring(i, i+2));  writerS.print("<NumLit> "); i+=1;
                                if(letts[i+2] == '0' || letts[i+2] == '1' ||letts[i+2] == '2'||letts[i+2]=='3'||letts[i+2]=='4'||letts[i+2] == '5'||letts[i+2]=='6'||letts[i+2]=='7'||letts[i+2] == '8'||letts[i+2]=='9'){
                                    writer.println(line.substring(i, i+3)+"\t\t<NumLit>");  writerR.println(line.substring(i, i+3));  writerS.print("<NumLit> ");  i+=2;
                                    if(letts[i+3] == '0' || letts[i+3] == '1' ||letts[i+3] == '2'||letts[i+3]=='3'||letts[i+3]=='4'||letts[i+3] == '5'||letts[i+3]=='6'||letts[i+3]=='7'||letts[i+3] == '8'||letts[i+3]=='9'){
                                        writer.println(line.substring(i, i+4)+"\t\t<NumLit>");  writerR.println(line.substring(i, i+4));  writerS.print("<NumLit> ");  i+=3;
                                        if(letts[i+4] == '0' || letts[i+4] == '1' ||letts[i+4] == '2'||letts[i+4]=='3'||letts[i+4]=='4'||letts[i+4] == '5'||letts[i+4]=='6'||letts[i+4]=='7'||letts[i+4] == '8'||letts[i+4]=='9'){
                                            writer.println(line.substring(i, i+5)+"\t\t<NumLit>");  writerR.println(line.substring(i, i+5));  writerS.print("<NumLit> ");  i+=4;
                                        }
                                    }
                                }
                            }
                            else
                            {
                                writer.println(letts[i] + "\t\t<NumLit>");  writerR.println(letts[i]); writerS.print("<NumLit> ");
                            }
                            break;
                        case "8":
                            if(letts[i+1] == '0' || letts[i+1] == '1' ||letts[i+1] == '2'||letts[i+1]=='3'||letts[i+1]=='4'||letts[i+1] == '5'||letts[i+1]=='6'||letts[i+1]=='7'||letts[i+1] == '8'||letts[i+1]=='9')
                            {
                                writer.println(line.substring(i, i+2)+"\t\t<NumLit>");  writerR.println(line.substring(i, i+2));  writerS.print("<NumLit> "); i+=1;
                                if(letts[i+2] == '0' || letts[i+2] == '1' ||letts[i+2] == '2'||letts[i+2]=='3'||letts[i+2]=='4'||letts[i+2] == '5'||letts[i+2]=='6'||letts[i+2]=='7'||letts[i+2] == '8'||letts[i+2]=='9'){
                                    writer.println(line.substring(i, i+3)+"\t\t<NumLit>");  writerR.println(line.substring(i, i+3));  writerS.print("<NumLit> ");  i+=2;
                                    if(letts[i+3] == '0' || letts[i+3] == '1' ||letts[i+3] == '2'||letts[i+3]=='3'||letts[i+3]=='4'||letts[i+3] == '5'||letts[i+3]=='6'||letts[i+3]=='7'||letts[i+3] == '8'||letts[i+3]=='9'){
                                        writer.println(line.substring(i, i+4)+"\t\t<NumLit>");  writerR.println(line.substring(i, i+4));  writerS.print("<NumLit> ");  i+=3;
                                        if(letts[i+4] == '0' || letts[i+4] == '1' ||letts[i+4] == '2'||letts[i+4]=='3'||letts[i+4]=='4'||letts[i+4] == '5'||letts[i+4]=='6'||letts[i+4]=='7'||letts[i+4] == '8'||letts[i+4]=='9'){
                                            writer.println(line.substring(i, i+5)+"\t\t<NumLit>");  writerR.println(line.substring(i, i+5));  writerS.print("<NumLit> ");  i+=4;
                                        }
                                    }
                                }
                            }
                            else
                            {
                                writer.println(letts[i] + "\t\t<NumLit>");  writerR.println(letts[i]); writerS.print("<NumLit> ");
                            }
                            break;
                        case "9":
                            if(letts[i+1] == '0' || letts[i+1] == '1' ||letts[i+1] == '2'||letts[i+1]=='3'||letts[i+1]=='4'||letts[i+1] == '5'||letts[i+1]=='6'||letts[i+1]=='7'||letts[i+1] == '8'||letts[i+1]=='9')
                            {
                                writer.println(line.substring(i, i+2)+"\t\t<NumLit>");  writerR.println(line.substring(i, i+2));  writerS.print("<NumLit> "); i+=1;
                                if(letts[i+2] == '0' || letts[i+2] == '1' ||letts[i+2] == '2'||letts[i+2]=='3'||letts[i+2]=='4'||letts[i+2] == '5'||letts[i+2]=='6'||letts[i+2]=='7'||letts[i+2] == '8'||letts[i+2]=='9'){
                                    writer.println(line.substring(i, i+3)+"\t\t<NumLit>");  writerR.println(line.substring(i, i+3));  writerS.print("<NumLit> ");  i+=2;
                                    if(letts[i+3] == '0' || letts[i+3] == '1' ||letts[i+3] == '2'||letts[i+3]=='3'||letts[i+3]=='4'||letts[i+3] == '5'||letts[i+3]=='6'||letts[i+3]=='7'||letts[i+3] == '8'||letts[i+3]=='9'){
                                        writer.println(line.substring(i, i+4)+"\t\t<NumLit>");  writerR.println(line.substring(i, i+4));  writerS.print("<NumLit> ");  i+=3;
                                        if(letts[i+4] == '0' || letts[i+4] == '1' ||letts[i+4] == '2'||letts[i+4]=='3'||letts[i+4]=='4'||letts[i+4] == '5'||letts[i+4]=='6'||letts[i+4]=='7'||letts[i+4] == '8'||letts[i+4]=='9'){
                                            writer.println(line.substring(i, i+5)+"\t\t<NumLit>");  writerR.println(line.substring(i, i+5));  writerS.print("<NumLit> ");  i+=4;
                                        }
                                    }
                                }
                            }
                            else
                            {
                                writer.println(letts[i] + "\t\t<NumLit>");  writerR.println(letts[i]); writerS.print("<NumLit> ");
                            }
                            break;
                        	
        				default: //if something doesnt catch in the switch, its not recognized and lands here                         
                            writer.println(letts[i] + "\t\tNot recognized: ?<var>?");
                            writerR.println(letts[i]);
                            writerS.print("?<var>? ");
            		}//end switch

				}//end for

            }//end while   

            System.out.println("Tokenizer has completed");
            // Always close files.
            writer.close();
            writerR.close();
            writerS.close();
            bufferedReader.close();         
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
