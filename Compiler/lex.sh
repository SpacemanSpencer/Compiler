#!/bin/bash
#this script will compile & run my lex programs
#First Pass*
gcc -o lex lex.c
./lex
javac tokenizer.java
java tokenizer
javac SymTable.java
java SymTable