// A Double Ended LinkedList has a reference to 
// the first and last Link in the List

public class SyntaxLinkedList {
	
	Token firstLink;
	Token lastLink;
	
	public void insertInFirstPosition(String tok){
		
		Token theNewLink = new Token(tok);
		
		// If no items in the list add the new Link
		// to lastLink in the LinkedList
		
		if(isEmpty()){
			
			lastLink = theNewLink;
			
		} /* FOR DOUBLY LINKED LIST */ else {
			
			firstLink.previous = theNewLink;
			
		} // END OF DOUBLY LINKED LIST ADDITION
		
		// DOUBLY LINKED LIST
		// Just like you can go forward in the list with next
		// with a doubly linked list you can go backwards
		// because it also has a previous as well as a next
			
		// Assign the reference to the previous 
		// firstLink and assign the new Link
		// to firstLink in LinkedList
			 
		theNewLink.next = firstLink;
			
		firstLink = theNewLink;
		
	}
	
	public void insertInLastPosition(String tok){
		
		Token theNewLink = new Token(tok);
		
		// If empty put the new Token in first position
		
		if(isEmpty()){
			
			firstLink = theNewLink;
			
		} else {
			
			// Assign the last Tokens next to the new Token
			
			lastLink.next = theNewLink;
			
			theNewLink.previous = lastLink; // FOR DOUBLY LINKED LIST
			
		}
			
		lastLink = theNewLink;
		
	}
	
	
	public static void main(String[] args) {
		
		DoubleEndedLinkedList theLinkedList = new DoubleEndedLinkedList();
		
		
		theLinkedList.insertInFirstPosition("Mark Evans");
		theLinkedList.insertInFirstPosition("Piers Polkiss");
		theLinkedList.insertInFirstPosition("Doreen Figg");
		theLinkedList.insertInLastPosition("Petunia Dursley");
	
		
		theLinkedList.display();
		
		System.out.println("\n");
		
		// Send the LinkedList to the iterator
		
		TokenIterator Tokens = new TokenIterator(theLinkedList);
		
		// Get the first Token and display
		
		Tokens.currentToken.display();
		
		// Is there another?
		
		System.out.println(Tokens.hasNext());
		
		// Switch to the next Token
		
		Tokens.next();
		
		Tokens.currentToken.display();
		
		Tokens.remove();
		
		Tokens.currentToken.display();
		
	}
	
	// Returns true if LinkList is empty
	
	public boolean isEmpty(){
				
		return(firstLink == null);
				
	}
			
			
			
		
	public void display(){
			
		Token theLink = firstLink;
			
		while(theLink != null){
				
			theLink.display();
				
			System.out.println("Next Link: " + theLink.next);
				
			theLink = theLink.next;
				
			System.out.println();
				
		}
			
	}	

}

class Token {
	
	public String tok;
	
	public Token next; 
	
	public Token previous; // Used with Doubly Linked List
	
	public Token(String tok){
		
		this.tok = tok;
		
	}
	
	public void display(){
		
		System.out.println("Current token: "+tok );
		
	}
	
	public String toString(){
		
		return tok;
		
	}
	
}

// An iterator provides an easy way to cycle through all
// the objects in a LinkedList

class TokenIterator{
	
	Token currentToken; // The current focus Token
	Token previousToken; // The previous Token
	
	DoubleEndedLinkedList theTokens;
	
	// hasNext, next, remove are common iterator methods
	
	TokenIterator(DoubleEndedLinkedList theTokens){
		
		this.theTokens = theTokens;
		
		currentToken = theTokens.firstLink;
		previousToken = theTokens.lastLink;
		
	}
	
	public boolean hasNext(){
		
		if(currentToken.next != null){
			
			return true;
			
		}
		
		return false;
		
	}
	
	public Token next(){
		
		if(hasNext()){
			
			previousToken = currentToken;
			currentToken = currentToken.next;
			
			return currentToken;
			
		}
		
		return null;
		
	}
	
	public void remove(){
		
		// If at the beginning of the list
		
		if(previousToken == null){
			
			theTokens.firstLink = currentToken.next;
			
		} else {
			
			previousToken.next = currentToken.next;
			
			// If at end of list
			
			if(currentToken.next == null){
				
				// Assign first link as the current link
				
				currentToken = theTokens.firstLink;
				previousToken = null;
				
			} else {
				
				currentToken = currentToken.next;
				
			}
			
		}
		
	}
	
}