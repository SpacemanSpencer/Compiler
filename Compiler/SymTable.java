/*
First Pass*
Uses Token Table to create Symbol Table 
*/

import java.io.*;
import java.util.*;

public class SymTable 
{
    
    public static void main(String [] args) 
    {
        System.out.println("Starting Symbol Table creator");
        System.out.println("Enter the Name of the File containing the Token Table: ");
        System.out.println("ie.) 'tokenTable.txt'");
        Scanner sc = new Scanner(System.in);
        String fileName = sc.nextLine();
        File fileS = new File("symbolTable.txt");
        int count = 0, vLim = 20, countC = 0, trackerC = 0, addr = 0;
        String line = null; //where input will be stored

        try 
        { 
            if(!fileS.exists()){
                fileS.createNewFile();
            }

            //FileWriter writes to text file 
            PrintWriter writerS = new PrintWriter(fileS);

            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);
            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            System.out.println("Creating Symbol Table in 'SymbolTable.txt'...");
            writerS.println("Symbol\tClassification\tValue\tAddress\tSegment");

            while((line = bufferedReader.readLine()) != null)
            {
                String copy = line;
                String[] columns = line.split("\t");
                //System.out.println("file reads:");
                //System.out.println(columns[0]); //writes what was in first column of the file onto terminal

                char[] letts = copy.toCharArray(); //converts string to char array
                
                for(int i = 0; i < columns.length; i+=3) //i++ will print everything, i+=3 prints only the tokens
                {
                    
                    //System.out.println(toks[i]); //prints array 1 char per line
                    switch(columns[i+2])
                    {
                        case "<ProgramName>":
                            writerS.println(columns[i]+"\t"+columns[i+2]+"\t\t"+addr+"\tCS");
                            break;
                        case "<constVar>":
                            writerS.print(columns[i]+"\t"+columns[i+2]+"\t");
                            countC++;
                            break;
                        case "<var>":
                            if(count < vLim)
                            {
                                writerS.println(columns[i]+"\t"+columns[i+2]+"\t\t???\t"+addr+"\tDS"); addr+=2;
                            }
                            break;
                        case "<NumLit>":
                            if(countC != trackerC) //count < 20
                            {
                                writerS.println(columns[i]+"\t"+addr+"\tDS"); addr+=2; trackerC++;
                            }
                            else
                            {
                                writerS.println(columns[i]+"\t"+columns[i+2]+"\t"+columns[i]+"\t"+addr+"\tDS"); addr+=2;
                            }
                            break;
                        default:
                            //do nothing
                            break;
                    }

//shows the tokens alone
//System.out.println(columns[i]);
count++;
//shows the classification alone
//System.out.println(columns[i+2]);

                }//end for

            }//end while   

            System.out.println("Symbol Table is Complete");
            // Always close files.
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
