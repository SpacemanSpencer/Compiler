//First Pass*
//Reads in a program and removes all deadspaces from it

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>

void DeadSpace(char* source, char* dest);

int main()
{
	FILE *in;
	char inName[21];
	FILE *out;

	char full[1024];
	char reduced[1024];

	printf("Enter the name of the ORIGINAL program with DeadSpaces:\n");
    printf("ie.) 'PgmIn0.txt'...'PgmIn9.txt'\n");
	scanf("%s", inName);

	in = fopen(inName, "r");
	out = fopen("PgmOut.txt", "w");

	while(!feof(in))
	{
		fgets(full, 1024,in);  //reads from input file

		DeadSpace(full, reduced);  //function to remove whitespace

		fputs(reduced, out);  //prints to output file
	}

   printf("PgmOut.txt has been created for further use!\n");

	fclose(in);
	fclose(out);

	return 0;
}


void DeadSpace(char* source, char* dest)
{
	char* a = source;
	char* b = dest;
	while(*a != 0)
	{
    	*b = *a++;
    	if(*b != 32 && *b != '\n' /*&& *b != 9*/)
      	b++;
  	}
  	*b = 0;
}