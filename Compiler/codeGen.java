/*
Second Pass*
Generates code from quads, only run if passes syntaxTest.java
*/


import java.io.*;
import java.util.Scanner;

public class codeGen 
{
    
    public static void main(String [] args) 
    {
    	System.out.println("Enter the Name of the File of which needs Assembly Code to be Generated:");
        System.out.println("ie.) 'Quads.txt'");
    	Scanner sc = new Scanner(System.in);
        String fileName = sc.nextLine();
        File fileG = new File("Code.txt");
        int lCount = 1;

        String line = null; //where input will be stored

        try 
        {
            if(!fileG.exists()){
                fileG.createNewFile();
            }

            //FileWriter writes to text file 
            PrintWriter writerG = new PrintWriter(fileG);
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);
            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            System.out.println("Generating Assembly Code in 'Code.txt'...");

            while((line = bufferedReader.readLine()) != null)
            {
                //writerG.println(line);  //writes what was in file to new file, line by line
                //System.out.println(line);  //writes what was in file onto terminal
                //System.out.println("next...");
                String[] quads = line.split(",");

                switch(quads[0])
                {
                    case "<":
                        writerG.println("mov ax, [" + quads[1] + "]\ncmp ax, [" + quads[2] + "]\nJGE Label" + lCount + "\n");
                        lCount++;
                        break;
                    case ">":
                        writerG.println("mov ax, [" + quads[1] + "]\ncmp ax, [" + quads[2] + "]\nJLE Label" + lCount + "\n");
                        lCount++;
                        break;
                    case "+":
                        writerG.println("mov ax, [" + quads[1] + "]\nadd ax, [" + quads[2] + "]\nmov [" + quads[3] + "], ax\n");
                        break;
                    case "-":
                        writerG.println("mov ax, [" + quads[1] + "]\nsub ax, [" + quads[2] + "]\nmov [" + quads[3] + "], ax\n");
                        break;
                    case "/":
                        writerG.println("mov ax, [" + quads[1] + "]\ndiv ax, [" + quads[2] + "]\nmov [" + quads[3] + "], ax\n");
                        break;
                    case "*":
                        writerG.println("mov ax, [" + quads[1] + "]\nmul ax, [" + quads[2] + "]\nmov [" + quads[3] + "], ax\n");
                        break;
                    case "=":
                        writerG.println("mov ax, [" + quads[2] + "]\nmov [" + quads[1] + "], ax\n");
                        break;
                }

            } //end of while

            System.out.println("Assembly Code Generated!");
            // Always close files.
            writerG.close();
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