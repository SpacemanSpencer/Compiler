#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int main()
{
	FILE *in;
	char inName[21] = {"PgmOut.txt"};
	FILE *out;

	char s[1024];
	char symTable[256][5];
	int n;

	in = fopen(inName, "r");
	if(in == NULL){
		printf("Input file does not exist! Terminating!\n");
		exit(1);
	}
	out = fopen("symbolTable.txt", "w");
	if(out == NULL){
		printf("Could not create report! Terminating!\n");
		exit(1);
	}

	fscanf(in, "%s", s); // reads in and stores pgm, no deadspaces

	fprintf(out, "Token\t\tClass\t\tValue\t\tAddress\t\tSegment\n");  // header of table





/*
	switch ()
	{
		case : //  = 

			break;	
		case : //  = 

			break;
		case : //  = 

			break;
		case : //  = 

			break;
		case : //  = 

			break;
		case : //  = 

			break;	
		case : //  = 

			break;
		case : //  = 

			break;
		case : //  = 

			break;
		case : //  = 

			break;
		case : //  = 

			break;	
		case : //  = 

			break;
		case : //  = 

			break;
		case : //  = 

			break;
		case : //  = 

			break;
		case : //  = 

			break;	
		case : //  = 

			break;
		case : //  = 

			break;
		case : //  = 

			break;
		case : //  = 

			break;
		case : //  = 

			break;	
		case : //  = 

			break;
		case : //  = 

			break;
		case : //  = 

			break;
		case : //  = 

			break;  
	}
*/


	fclose(in);
	fclose(out);

	return 0;
}