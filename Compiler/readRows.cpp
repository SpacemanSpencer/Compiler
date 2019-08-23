#include<iostream> 
#include<stdio.h>
#include<string>
#include<fstream>
using namespace std;

int main() {
    int x, y, z, i, numTasks;
    char fileName[21];
    int a[10], b[10], c[10];//size of array more than number of entries in data file
    
    printf("Enter name of file: \n");
    cin>>fileName;

    ifstream infile;
    infile.open(fileName);//open the text file
    if (!infile) {
        cout << "Unable to open file";
        exit(1); // terminate with error
    }
    i=0;
    infile>>numTasks;

    while (!infile.eof()) 
    {
    //To make three arrays for each column (a for 1st column, b for 2nd....)
    infile>>x>>y>>z;
    a[i]=x;
    b[i]=y;
    c[i]=z;
    //to add more columns
    //array[i]=variable; 
    i++;
    }   
    //end of while loop

    cout<<numTasks<<endl; //prints numTaks Expected
    for(int i = 0 ; i < numTasks; i++) //loop to print the stored Table
    {
    cout<<a[i]<<"\t"<< b[i]<<"\t"<<c[i]<<endl; // To print 1st entry (1st row), similarly we can print any row        
    }  

    infile.close();
}