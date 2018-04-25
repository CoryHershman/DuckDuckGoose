/*
 Programmer:Cory Hershman
 Class: Data Structures
 Instructor: Mr. Kennedy
 Assignment #: P0001
 Due Date: 10/13/2017
 Last Update: 10/13/2017
 Related Files: cLinkedList.java, Node.java, children.txt
 Description: This class uses a circularly linked list to play a game of Duck Duck Goose. Methods include: 
 isBored, pokemon, listKids, gooseSelection, and chase.
 */

package edu.frostburg.cosc310.Hershman;

import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class DuckDuckGoose {

	public static void main(String[] args) {
		
		//variables name, age, and gender will be used to create nodes
		String name;
		int age;
		String gender;
		
		//listDDG is the cLinkedList that will be used to keep track of the kids
		cLinkedList listDDG = new cLinkedList();
		Node it = new Node();					//The "it" node is the kid that is it
		int round = 0;							//Keeps track of the current round
		
		//Reads in from the file "children.txt" from the accompanying folder
		//Exception is caught if file is not found.
		try {
			Scanner scan = new Scanner(new File("children.txt"));
			
			//While loop scans in all the children and creates nodes for all of them
			//They are appended to listDDG
			while (scan.hasNext()) {
				name = scan.next();
				age = scan.nextInt();
				gender = scan.next();
				
				it = new Node(name, age, gender, null);
				listDDG.append(it);
			}
			
			
			it = listDDG.remove();					//The first kid is it
			DuckDuckGoose.listKids(listDDG, it);	//The kids that are playing are listed 
			
			//Game runs until 9 rounds have been played, or until less than 4 kids remain
			while(round <= 8 && listDDG.getSize() >= 3) {
				System.out.println();
				DuckDuckGoose.gooseSelection(listDDG, it);	//GooseSelection method called, it kid chooses a goose
				it = DuckDuckGoose.chase(listDDG, it);		//Chase method called, the loser of the chase is now it
				round = round + 1;							//One round has passed
				DuckDuckGoose.pokemon(listDDG, round);		//Pokemon method called, chance of kids getting bored
				DuckDuckGoose.listKids(listDDG, it);		//The kids that are still playing are listed
			} 
			
			//If 9 rounds have been played, an adult stops the kids
			if(round > 8) {
				System.out.println("Kids! It's snack time!");
			}
			//If less than 4 kids remain, the game is finished
			else if(listDDG.getSize() < 3) {
				System.out.println("Less than 4 kids remain, they decide to call it quits");
			}

		} catch (FileNotFoundException e) {
			System.out.println("file was not found");
			System.exit(0);
		}
	}
	
	//isBored method
	//Requires: The current round
	//Returns: True if the kid is bored, false if not
	public static boolean isBored(int round) {
		Random rand = new Random();
		int randNum = rand.nextInt(20) + 1;			//Generates a random number between 1-20
		
		if (randNum <= round) {						//Each round, the chance of boredom goes up by 5%
			return true;
		} else {
			return false;
		}
	}
	
	//pokemon method
	//Requires: The cLinkedList, and the current round
	//Returns: Nothing
	public static void pokemon(cLinkedList list, int round) {
		
		Node boredNode;								//boredNode is a temp storage place for kids that leave
		//For loop runs once for each kid in the list
		for (int i = 1; i <= list.getSize(); i++) {
			//Checks if the kid after curNode is bored
			//If kid is bored, he/she is removed from game and their information is output to console
			//If kid is not bored, curNode moves up
			if (DuckDuckGoose.isBored(round)) {
				boredNode = list.remove();
				System.out.println(boredNode.getName() + " " + boredNode.getAge() + " " + boredNode.getGender() + " left to play pokemon");
			} else {
				list.curNode = list.curNode.nextNode();
			}
		}
	}
	
	
	//listKids method
	//Requires: The cLinkedList, and the node of the "it" kid
	//Returns: Nothing
	public static void listKids(cLinkedList list, Node it) {
		Node curNode = list.curNode;
		System.out.println("\nThese are the kids that are playing:");
		//Prints out each kid in the list
		for(int i = 1; i <= list.getSize(); i++) {
			System.out.println(curNode.getName() + " " + curNode.getAge() + " " + curNode.getGender());
			curNode = curNode.nextNode();
		}
		//Prints out the kid that is "it"
		System.out.println("it: " + it.getName() + " " + it.getAge() + " " + it.getGender());
	}
	
	//gooseSelection method
	//Requires: The cLinkedList, and the node of the "it" kid
	//Returns: Nothing
	public static void gooseSelection(cLinkedList list, Node it) {
		Random rand = new Random();
		int randNum = rand.nextInt(list.getSize());		//Generates a number between 0 and list size - 1
		//Traverses through the list until the random number is reached
		for(int i = 0; i <= randNum; i++) {
			list.curNode = list.curNode.nextNode();
			System.out.println("\"Duck\"");
		}
		
		System.out.println("\"Goose!\"");     //Goose is the kid after curNode
		System.out.println(list.curNode.nextNode().getName() + " " + list.curNode.nextNode().getAge() + " " +
				list.curNode.nextNode().getGender() + " is the goose");
	}
	
	//chase method
	//Requires: The cLinkedList, and the node of the "it" kid
	//Returns: The loser of the chase, the kid that will be "it"
	//There is a 50% chance of any kid winning, regardless of age
	public static Node chase(cLinkedList list, Node it) {
		Random rand = new Random();
		int randNum = rand.nextInt(2);				//Generates either 0 or 1
		Node temp = new Node();
		
		//if the it player wins, the it player takes the goose's spot, and the goose becomes the new it player
		if(randNum == 0) {
			temp = list.remove();					//Goose is removed
			list.append(it);						//It is appended
			System.out.println(it.getName() + " beat " + temp.getName());
			it = temp;								//Goose becomes "it"
			System.out.println(it.getName() + " " + it.getAge() + " " + it.getGender() + " is now it");
		}
		//if the goose wins, list stays the same, same player is it
		else {
			temp = list.curNode.nextNode();
			System.out.println(temp.getName() + " beat " + it.getName());
			System.out.println(it.getName() + " " + it.getAge() + " " + it.getGender() + " is still it");
		}
		return it;
	}

}
