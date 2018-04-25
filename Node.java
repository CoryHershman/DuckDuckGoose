/*
 Programmer:Cory Hershman
 Class: Data Structures
 Instructor: Mr. Kennedy
 Assignment #: P0001
 Due Date: 10/13/2017
 Last Update: 10/13/2017
 Related Files: cLinkedList.java, DuckDuckGoose.java, children.txt
 Description: This class can be used to create a node to be used with a linked list. Methods include:
 nextNode, hasNextNode, toString, setNext, getName, getAge, getGender
 */

package edu.frostburg.cosc310.Hershman;

public class Node {
	
	//Instance variables for the Node object
	private String name;
	private int age;
	private String gender;
	private Node next;
	
	//Empty constructor, Node starts with null paramaters to be filled in later
	public Node() {
		name = null;
		age = 0;
		gender = null;
		next = null;
	}
	
	//Node constructor, specifically made for DuckDuckGoose, not reusable for other projects
	//Requires: name of kid, age of kid, gender of kid, the next kid in the list
	public Node(String na, int a, String g, Node n) {
		name = na;
		age = a;
		gender = g;
		next = n;
	}
	
	//nextNode method
	//Requires: Nothing
	//Returns: The Node after the calling Node
	public Node nextNode() {
		return next;
	}
	
	//hasNextNode method
	//Requires: Nothing
	//Returns: Boolean; false if no node exists after the current node. True otherwise.
	public boolean hasNextNode() {
		if(next == null) {
			return false;
		}
		else {
			return true;
		}
	}
	
	//toString method
	//Requires: Nothing
	//Returns: String containing the name, age, and gender of the kid, seperated by commas and spaces
	public String toString() {
		String s = name + ", " + age + ", " + gender;
		return s;
	}
	
	//setNext method
	//Requires: Node n
	//Returns: Nothing
	//This method sets required Node to be the calling Node's next Node
	public void setNext(Node n) {
		next = n;
	}
	
	//getName method
	//Requires: Nothing
	//Returns: the name of the kid
	//Only reusable for Nodes that have a name
	public String getName() {
		return name;
	}
	
	//getAge method
	//Requires: Nothing
	//Returns: The age of the kid
	//Only reusable for Nodes that have an age
	public int getAge() {
		return age;
	}
	
	//getGender method
	//Requires: Nothing
	//Returns: the gender of the kid
	//Only reusable for Nodes that have a gender
	public String getGender() {
		return gender;
	}
	
	
	
}
