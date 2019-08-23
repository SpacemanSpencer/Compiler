/*
Second Pass*
Generates quads from a program, only run if passes syntaxTest.java
*/

import java.io.*;
import java.util.Scanner;

public class quadGen 
{
    
    public static void main(String [] args) 
    {
    	System.out.println("Enter the Name of the Program of which needs Quads to be Generated:");
        System.out.println("ie.) 'PgmIn0.txt'...'PgmIn9.txt'");
        //System.out.println("Try 'tokensRaw.txt'");
    	Scanner sc = new Scanner(System.in);
        String fileName = sc.nextLine();
        File fileQ = new File("Quads.txt");
        int tempcounter = 1;

        String line = null; //where input will be stored

        try 
        {
            if(!fileQ.exists()){
                fileQ.createNewFile();
            }

            //FileWriter writes to text file 
            PrintWriter writerQ = new PrintWriter(fileQ);
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);
            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            System.out.println("Creating Quads in 'Quads.txt'...");

            while((line = bufferedReader.readLine()) != null)
            {
                //writerQ.println(line); //writes what was in file to new file, line by line
                //System.out.println(line); //writes what was in file onto terminal, line by line
                
                String[] stack = line.split("[; ]+"); //'works' with PgmInX.txt
                //String[] stack = new String[]{line}; // attempting to make it work with tokensRaw.txt

                
                for(int i = 0; i < stack.length; i++)
                {         

                    switch(stack[i])
                    {
                        //case"IF":
                        //case"ELSE":
                        case "(":

                            break;
                        case ")":

                            break;
                        case "<":
                            writerQ.println(stack[i]+","+stack[i-1]+","+stack[i+1]+","+"Temp"+tempcounter);
                            tempcounter++;
                            break;
                        case ">":
                            writerQ.println(stack[i]+","+stack[i-1]+","+stack[i+1]+","+"Temp"+tempcounter);
                            tempcounter++;
                            break;
                        case "*":
                            if(stack[i-1].equals(")")) // ( M + N ) * Y
                            {
                                writerQ.println(stack[i]+","+stack[i-2]+","+stack[i+1]+","+"Temp"+tempcounter);
                                tempcounter++;
                                break;
                            }
                            else if(stack[i+1].equals("(")) // Y * ( M + N)
                            {
                                writerQ.println(stack[i]+","+stack[i-1]+","+stack[i+2]+","+"Temp"+tempcounter);
                                tempcounter++;
                                break;
                            }
                            else // Y * M
                            {
                                writerQ.println(stack[i]+","+stack[i-1]+","+stack[i+1]+","+"Temp"+tempcounter);
                                tempcounter++;
                                break;
                            }
                        case "/":
                           if(stack[i-1].equals(")")) // ( M + N ) / Y
                            {
                                writerQ.println(stack[i]+","+stack[i-2]+","+stack[i+1]+","+"Temp"+tempcounter);
                                tempcounter++;
                                break;
                            }
                            else if(stack[i+1].equals("(")) // Y / ( M + N)
                            {
                                writerQ.println(stack[i]+","+stack[i-1]+","+stack[i+2]+","+"Temp"+tempcounter);
                                tempcounter++;
                                break;
                            }
                            else // Y / M
                            {
                                writerQ.println(stack[i]+","+stack[i-1]+","+stack[i+1]+","+"Temp"+tempcounter);
                                tempcounter++;
                                break;
                            }
                        case "+":
                            if(stack[i-1].equals(")")) // ( M + N ) + Y
                            {
                                writerQ.println(stack[i]+","+stack[i-2]+","+stack[i+1]+","+"Temp"+tempcounter);
                                tempcounter++;
                                break;
                            }
                            else if(stack[i+1].equals("(")) // Y + ( M + N)
                            {
                                writerQ.println(stack[i]+","+stack[i-1]+","+stack[i+2]+","+"Temp"+tempcounter);
                                tempcounter++;
                                break;
                            }
                            else // Y + M
                            {
                                writerQ.println(stack[i]+","+stack[i-1]+","+stack[i+1]+","+"Temp"+tempcounter);
                                tempcounter++;
                                break;
                            }
                        case "-":
                            if(stack[i-1].equals(")")) // ( M + N ) - Y
                            {
                                writerQ.println(stack[i]+","+stack[i-2]+","+stack[i+1]+","+"Temp"+tempcounter);
                                tempcounter++;
                                break;
                            }
                            else if(stack[i+1].equals("(")) // Y - ( M + N)
                            {
                                writerQ.println(stack[i]+","+stack[i-1]+","+stack[i+2]+","+"Temp"+tempcounter);
                                tempcounter++;
                                break;
                            }
                            else // Y - M
                            {
                                writerQ.println(stack[i]+","+stack[i-1]+","+stack[i+1]+","+"Temp"+tempcounter);
                                tempcounter++;
                                break;
                            }
                        case "=":
                            if(stack[i-1].equals(")")) // ( M + N ) = Y
                            {
                                writerQ.println(stack[i]+","+stack[i-2]+","+stack[i+1]+","+"Temp"+tempcounter);
                                tempcounter++;
                                break;
                            }
                            else if(stack[i+1].equals("(")) // Y = ( M + N)
                            {
                                writerQ.println(stack[i]+","+stack[i-1]+","+stack[i+2]+","+"Temp"+tempcounter);
                                tempcounter++;
                                break;
                            }
                            else // Y = M
                            {
                                writerQ.println(stack[i]+","+stack[i-1]+","+stack[i+1]+","+"Temp"+tempcounter);
                                tempcounter++;
                                break;
                            }

                        default:
                            //do nothing
                            break;
                    }  // end of switch
                
                }  // end of for

            } //end of while

            System.out.println("Quads Generated!");
            // Always close files.
            writerQ.close();
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