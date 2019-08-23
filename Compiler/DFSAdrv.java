//Table driven deterministic finite state automaton.  In file DFSAdrv.java.

import java.applet.Applet;
import java.util.*;
import java.awt.*;
import java.awt.event.*;


class FSA{
  private int stateTable[][]; //={{4,1,8},{2,5,8},{3,6,7}};

  public FSA(){	   //Default credit policy.
     stateTable = new int[3][3]; 
     stateTable[0][0] = 4; stateTable[0][1] = 1; stateTable[0][2] = 8;
     stateTable[1][0] = 2; stateTable[1][1] = 5; stateTable[1][2] = 8;
     stateTable[2][0] = 3; stateTable[2][1] = 6; stateTable[2][2] = 7;
  }

  public FSA(int tabData[], int nrows, int ncols){  //User supplied credit policy.
    stateTable = new int[nrows][ncols];
    for(int row = 0; row < nrows; row++)
	  for(int col = 0; col < ncols; col++) 
	    stateTable[row][col] = tabData[row*3+col];
  }

  public String ProcessRequest(int resp[]){
    boolean finished = false;
	String policy = " ";
	int nextState = 0;
	do{
	  switch(nextState){
	  case 0: nextState = stateTable[0][resp[0]]; break;
	  case 1: nextState = stateTable[1][resp[1]]; break;
	  case 2: nextState = stateTable[2][resp[2]]; break;
	  case 3: policy = "Approve the order.\n";
	          finished = true; break;
	  case 4: policy = "Approve the order and notify parent!\n";
	          finished = true; break;
	  case 5: policy = "Reject order and kick the bum out!!\n";
	          finished = true; break;
	  case 6: policy = "Reject the order\n"; finished = true; break;
	  case 7: policy = "Approve the order and notify management.\n";
	          finished = true; break;
	  case 8: policy = "Illegal user transition.\n";
	          finished = true; break;
	  case 9: policy = "Illegal user input!\n"; finished = true;
	  }
	}while(!finished);
	return policy;
  }
}

 
public class DFSAdrv extends Applet
             implements ActionListener {
   // GUI components
   Label prompt;
   TextField input;
   TextArea output;

   public void init()
   {
      prompt = new Label( "Respond with 'n' for no, 'y' for yes, & f for fair." );
      input = new TextField( 50 );	// Can edit by default.
      input.addActionListener( this );
      output = new TextArea( 15, 40 );
      output.setEditable( false );	// Turn off default ability to edit.
	output.setText("Separate answers to the\nfollowing with one\nor more spaces.\n\n"
	              + "Special clearance?\nCredit okay?\nPay Experience?\n\n");
      add( prompt );  add( input );  add( output );
   }

   private int AnswerToNumeric(String str){
     if( str.equals("y") | str.equals("Y") ) return 0;
	 if( str.equals("n") | str.equals("N") ) return 1;
	 if( str.equals("f") | str.equals("F") ) return 2;
	 return 9; //Illegal input.
   }

   public void actionPerformed( ActionEvent event )
   {  int quesResp[] = new int[3];
      int ndx = 0;
	  String resp, decision;
	  int table2[] = { 4, 1, 8, 2, 5, 8, 3, 6, 5 };
	  FSA TableDrivenDFSA1 = new FSA();
	  FSA TableDrivenDFSA2 = new FSA(table2, 3, 3);	 // Table in in three row and columns.
	  
        String stringToTokenize = event.getActionCommand(); //Read text field.
	  StringTokenizer responces = new StringTokenizer( stringToTokenize);

	  output.setText("Separate answers to the following \nwith one or more spaces"
	                 + "(y, n, f).\n\n"
	                 + "Special clearance?\nCredit okay?\nPay Experience?\n\n");

	  while(responces.hasMoreTokens()){
	    resp = responces.nextToken();
		quesResp[ndx++] = AnswerToNumeric(resp);
	  }

	  decision = TableDrivenDFSA1.ProcessRequest(quesResp);
	  output.append("Decision by FSA 1\n");
	  output.append(decision + "\n\n");
	  decision = TableDrivenDFSA2.ProcessRequest(quesResp);
	  output.append("Decision by FSA 2\n");
	  output.append(decision);
	  input.setText(" ");
	
   }
}
