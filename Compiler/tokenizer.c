#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int main()
{
	FILE *in;
	char inName[21] = {"PgmOut.txt"};
	FILE *out;

	char s[1024];
	char tokenTable[256][2];
	int n;

	in = fopen(inName, "r");
	if(in == NULL){
		printf("Input file does not exist! Terminating!\n");
		exit(1);
	}
	out = fopen("tokens.txt", "w");
	if(out == NULL){
		printf("Could not create report! Terminating!\n");
		exit(1);
	}

	fscanf(in, "%s", s); // reads in and stores pgm, no deadspaces

	fprintf(out, "Token\t\tClassification\n");  // header of table




	switch (n) //switch with c only works with ints
	{
		case 0: // 0 = Class

			break;
		case 1: // 1 = {

			break;
		case 2: // 2 = COMPOUND STMTS

			break;
		case 3: // 3 = INTEGER

			break;
		case 4: // 4 = IF

			break;
		case 5: // 5 = THEN

			break;
		case 6: // 6 = ELSE

			break;
		case 7: // 7 = -

			break;
		case 8: // 8 = *

			break;
		case 9: // 9 = /

			break;
		case 10: // 10 = >

			break;
		case 11: // 11 = >=

			break;



	/*	case : //  = 

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

			break;  */		
	}

	fclose(in);
	fclose(out);

	return 0;
}