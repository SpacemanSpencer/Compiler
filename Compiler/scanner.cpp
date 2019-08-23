#include <iostream>
#include <stdio.h>
#include <string>
#include <fstream>

using namespace std;

int main()
{
	char fileName[21];
	string input;

	cout << "Enter name of file to tokenize: \n";
    cin >> fileName;

    ifstream infile;
    infile.open(fileName);//open the text file
    if (!infile) {
        cout << "Unable to open file\n";
        exit(1); // terminate when error
    }

    infile >> input; //stores the pgm with no deadspaces into string
    cout << input << endl;	//shows (hopefully) pgm with no deadspaces
    cout << endl;

    cout << "Tokens\t" << "Classification" << endl;

    //loop that will process each char of the string
    for(int i = 0; i < input.length(); i++) 
    {
    	if(input.find("CLASS"))
    	{
    		cout << input[i] << "if catch" << endl;
    	}
    	 

    	switch(input[i])
    	{
    		case '+':
    			cout << input[i] << "\t<addOp>" << endl;
    			break;
    		case '-':
    			cout <<  input[i] << "\t<subOp>" << endl;
    			break;
    		case '*':
    			cout <<  input[i] << "\t<mOp>" << endl;
    			break;
    		case '/':
    			cout <<  input[i] << "\t<mOp>" << endl;
    			break;
    		case '=':
    			cout <<  input[i] << "\t<assign>" << endl;
    			break;
    		case ';':
    			cout <<  input[i] << "\t<semi>" << endl;
    			break;
    		case '{':
    			cout <<  input[i] << "\t<LB>" << endl;
    			break;
    		case '}':
    			cout <<  input[i] << "\t<RB>" << endl;
    			break;
    		case '(':
    			cout <<  input[i] << "\t<LP>" << endl;
    			break;
    		case ')':
    			cout <<  input[i] << "\t<RP>" << endl;
    			break;
    		case ',':
    			cout <<  input[i] << "\t<comma>" << endl;
    			break;
    		case '<':
    			cout <<  input[i] << "\t<relOp>" << endl;
    			break;
    		case '>':
    			cout <<  input[i] << "\t<relOp>" << endl;
    			break;
    		
    			

    		default:
    			cout << input[i] << "\t\t\tmonkaS" << endl;
    			break;
    	}

    	//cout << input[i] << endl;

    }





    return 0;
}