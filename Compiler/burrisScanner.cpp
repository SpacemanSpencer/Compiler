/* In file Scan1Obj.cpp -- renamed to burrisScanner.cpp
Scanner to find strings delimited by spaces. Each token must
be surrounded by one or more spaces. Must be hand tailored if
the delimiter is changed from a space.  The scanner will not 
separate terminals in the grammar from non-terminals unless 
they are separated by spaces.  For example, it fails to separate
"joe+ sam" into "joe", "+", and "sam" but rather returns 
"joe+" and "sam".
*/

#include <iostream>
#include <stdio.h>
#include <iomanip>
#include <string.h>


class StringTokenizer{

private:
    char strExp[80];
    char tokenArray [40][30];  //Max 40 tokens of 30 char per line.
    int numTokens;
    int nextNdx;

    void ParseTokens(char strExp[], char strPtArray[10][30], int& numTokens);

    void GetStrExp(char strExp[]);


public:
    StringTokenizer();

    void ReadAndParseString();

    int TotalTokens(){ return numTokens; }

    void SetToFirstTokenNdx(){ nextNdx = 0; }

    int HasMoreTokens(){ 
	if(nextNdx < numTokens) return 1; else return 0; 
    }


    char* NextToken(){
	if(nextNdx < numTokens)
          return tokenArray[nextNdx++];
	else
	    return "Out of tokens";
	}
};

 

StringTokenizer::StringTokenizer(){ 
	numTokens = 0;  nextNdx = 0;
}
    
void StringTokenizer::ParseTokens(char strExp[], char strPtArray[10][30], 
                                  int& numTokens){
    int countTokens = -1;
    int strNdx = 0;
    int strLen = strlen(strExp);
    int tokenNdx;
    char aToken[31];

    while(strNdx < strLen){  // Build the next token.
	   tokenNdx = 0;
	   // Skipleading blanks.
	   while( (strNdx < strLen) & (strExp[strNdx]==' ') ){	strNdx++;}
	   // Build token delimited by a space or end of string.
	   while( (strNdx < strLen) & (strExp[strNdx] != ' ') ){
		    aToken[tokenNdx++] = strExp[strNdx++];
	   }
	   aToken[tokenNdx] = '\0';
	   strcpy(strPtArray[++countTokens], aToken);
    }
    numTokens = countTokens + 1; 
    nextNdx = 0;
}


void StringTokenizer::GetStrExp(char strExp[]){  //Get string including all chars to \n.
    int ndx = 0;
    char aChar;

    cout << "Enter expresion to parse: " << endl;
    scanf("%c", &aChar);
    do{
       strExp[ndx++] = aChar;
       scanf("%c", &aChar);
    }
    while(aChar != '\n'  );
    strExp[ndx] = '\0';
}   

void StringTokenizer::ReadAndParseString(){
     GetStrExp(strExp); //Read input to end of line.
    ParseTokens(strExp, tokenArray, numTokens);
}

 

void main(){
	StringTokenizer LexicalAnalyzer;
	char again = 'y';


    while( again == 'y' | again == 'Y'){
	  // Call lexical analyzer to read and parse input.
      LexicalAnalyzer.ReadAndParseString();

	  //Print all tokens in input.
	  LexicalAnalyzer.SetToFirstTokenNdx();
      while( LexicalAnalyzer.HasMoreTokens() ) 
	     cout << LexicalAnalyzer.NextToken() << endl;

	  cout << "\nEnter another string (y or n): ";
	  cin >> again;
	}
}
