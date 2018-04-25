/*
 Programmer:Cory Hershman
 Class: Data Structures
 Instructor: Mr. Kennedy
 Assignment #: P0001
 Due Date: 10/13/2017
 Last Update: 10/13/2017
 Related Files: DuckDuckGoose.java, Node.java, children.txt
 Description: This class can be used to create a general purpose circularly linked list, or one to be used for 
 Duck Duck Goose, Musical Chairs, or similar program. Methods include: isEmpty, getSize, append, remove.
 */

package edu.frostburg.cosc310.Hershman;

public class cLinkedList {
	
	//instance variables for the cLinkedList object
	public Node curNode;
	public Node sucNode;
	private int size = 0;
	
	//Empty constructor, cLinkedList starts with null paramaters to be filled in later
	public cLinkedList() {
		curNode = null;
		size = 0;
	}
	
	//isEmpty method
	//Requires: Nothing
	//Returns: True if the list has no nodes, false if the list does
	public boolean isEmpty() {
		if(size == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//getSize method
	//Requires: Nothing
	//Returns: The number of nodes in the list
	public int getSize() {
		return size;
	}
	
	//append method
	//Requires: The node, n, that will be appended
	//Returns: Nothing
	public void append(Node n) {
		if(curNode == null) {				//If the list has no nodes, the appended node becomes the only node
			curNode = n;
		}
		else if (curNode.hasNextNode()) {	//Otherwise, add the node between the current node and the node after it
			sucNode = curNode.nextNode();
			curNode.setNext(n);
			n.setNext(sucNode);
		}
		else {
			curNode.setNext(n);
			n.setNext(curNode);
		}
		size = size + 1;					//The list is 1 node longer
	}
	
	//remove method
	//Requires: Nothing
	//Returns: The Node that was removed
	public Node remove() {
		sucNode = curNode.nextNode();
		curNode.setNext(sucNode.nextNode());
		size = size - 1;					//The list is 1 node shorter
		return sucNode;
	}
}
