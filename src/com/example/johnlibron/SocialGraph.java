package com.example.johnlibron;

import java.util.ArrayList;
import java.util.List;

public class SocialGraph {
	
	private List<String> names;
	private boolean[][] graph;

	public SocialGraph() {
		graph = new boolean[10][10];
		names = new ArrayList<>();
		
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph[i].length; j++) {
				if (i == j) {
					graph[i][j] = true;
				} else {
					graph[i][j] = false;
				}
			}
		}
		
		names.add("Lennard");
		names.add("Cherry");
		names.add("John");
		names.add("Julrecha");
		names.add("Kat");
		names.add("Vince");
		names.add("Ann");
		names.add("Michael");
		names.add("Joe");
		names.add("Charice");
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph[i].length; j++) {
				if (i != j && graph[i][j]) {
					sb.append(names.get(i) + "-" + names.get(j) + ", ");
				}
			}
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(sb.lastIndexOf(",")).toString();
		}
		return sb.toString();
	}
	
	private void addFriend(int i, int j) {
		if (i != j) {
			graph[i][j] = true;
		}
	}
	
	private void addFriend(String personOne, String personTwo) {
		int i = names.indexOf(personOne);
		int j = names.indexOf(personTwo);
		addFriend(i, j);
	}
	
	private int countFriends(int i) {
		int count = 0;
		for (int j = 0; j < graph[i].length; j++) {
			if (i != j && graph[i][j]) {
				count++;
			}
		}
		return count;
	}
	
	private int countFriends(String person) {
		int i = names.indexOf(person);
		return countFriends(i);
	}
	
	private int countEnemies(int i) {
		int count = 0;
		for (int j = 0; j < graph[i].length; j++) {
			if (i != j && !graph[i][j]) {
				count++;
			}
		}
		return count;
	}
	
	private int countEnemies(String person) {
		int i = names.indexOf(person);
		return countEnemies(i);
	}
	
	private int countCommonFriends(int i, int j) {
		List<Integer> listOne = new ArrayList<>(); 
		List<Integer> listTwo = new ArrayList<>();
		for (int col = 0; col < graph[i].length; col++) {
			if (i != col && graph[i][col]) {
				listOne.add(col);
			}
			if (j != col && graph[j][col]) {
				listTwo.add(col);
			}
		}
		listOne.retainAll(listTwo);
		return listOne.size();
	}
	
	private int countCommonFriends(String personOne, String personTwo) {
		int i = names.indexOf(personOne);
		int j = names.indexOf(personTwo);
		return countCommonFriends(i, j);
	}
	
	private int countCommonEnemies(int i, int j) {
		List<Integer> listOne = new ArrayList<>(); 
		List<Integer> listTwo = new ArrayList<>(); 
		for (int col = 0; col < graph[i].length; col++) {
			if (i != col && !graph[i][col]) {
				listOne.add(col);
			}
			if (j != col && !graph[j][col]) {
				listTwo.add(col);
			}
		}
		listOne.retainAll(listTwo);
		return listOne.size();
	}
	
	private int countCommonEnemies(String personOne, String personTwo) {
		int i = names.indexOf(personOne);
		int j = names.indexOf(personTwo);
		return countCommonEnemies(i, j);
	}
	
	private int findBestFriends(int i) {
		int bestFriend = -1;
		int friends = 0;
		for (int row = 0; row < graph.length; row++) {
			if (i != row) {
				int count = countCommonFriends(i, row);
				if (count > friends) {
					bestFriend = row;
					friends = count;
				}
			}
		}
		if (bestFriend == -1) {
			bestFriend = i;
		}
		return bestFriend;
	}
	
	private String findBestFriends(String person) {
		int i = names.indexOf(person);
		return names.get(findBestFriends(i));
	}
	
	private String friendliest() {
		List<Integer> friendsList = new ArrayList<>(); 
		int friendliest = -1;
		int friends = 0;
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph[i].length; j++) {
				if (i != j && graph[i][j]) {
					friendsList.add(j);
				}
			}
			if (!friendsList.isEmpty() && friendsList.size() > friends) {
				friends = friendsList.size();
				friendliest = i;
			}
			friendsList.clear();
		}
		if (friendliest == -1) {
			return "none";
		}
		return names.get(friendliest);
	}
	
	private String loner() {
		List<Integer> friendsList = new ArrayList<>(); 
		int loner = -1;
		int friends = graph[0].length - 1;
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph[i].length; j++) {
				if (i != j && graph[i][j]) {
					friendsList.add(j);
				}
			}
			if (!friendsList.isEmpty() && friendsList.size() < friends) {
				friends = friendsList.size();
				loner = i;
			}
			friendsList.clear();
		}
		if (loner == -1) {
			return "none";
		}
		return names.get(loner);
	}
	
	private boolean recluse(int i) {
		for (int j = 0; j < graph[i].length; j++) {
			if (i != j && graph[i][j]) {
				return false;
			}
		}
		return true;
	}
	
	private boolean recluse(String person) {
		int i = names.indexOf(person);
		return recluse(i);
	}
	
	private boolean famous(int i) {
		for (int j = 0; j < graph[i].length; j++) {
			if (i != j && !graph[i][j]) {
				return false;
			}
		}
		return true;
	}
	
	private boolean famous(String person) {
		int i = names.indexOf(person);
		return famous(i);
	}
	
	public static void main(String args[]) {
		SocialGraph socialGraph = new SocialGraph();
		
		socialGraph.addFriend("Cherry", "Vince");
		socialGraph.addFriend("Cherry", "John");
		socialGraph.addFriend("Cherry", "Julrecha");
		socialGraph.addFriend("Cherry", "Kat");
		socialGraph.addFriend("Cherry", "Charice");
		socialGraph.addFriend("Lennard", "Joe");
		socialGraph.addFriend("Lennard", "Michael");
		socialGraph.addFriend("Cherry", "Michael");
		socialGraph.addFriend("Cherry", "Ann");
		socialGraph.addFriend("Cherry", "Lennard");
		socialGraph.addFriend("Cherry", "Joe");
		socialGraph.addFriend("Lennard", "Charice");
		socialGraph.addFriend("Vince", "Kat");
		socialGraph.addFriend("Vince", "Charice");
		socialGraph.addFriend("Vince", "Michael");
		socialGraph.addFriend("Vince", "Cherry");
		socialGraph.addFriend("Kat", "Michael");
		socialGraph.addFriend("Kat", "Vince");
		socialGraph.addFriend("Kat", "Cherry");
		socialGraph.addFriend("Kat", "Lennard");
		socialGraph.addFriend("Charice", "Cherry");

		System.out.println("Social Group: " + socialGraph.toString());
		
		System.out.println("Count friends of Vince : " + socialGraph.countFriends("Vince"));
		System.out.println("Count enemies of Vince : " + socialGraph.countEnemies("Vince"));
		
		System.out.println("Count common friends of Vince and Kat : " + socialGraph.countCommonFriends("Vince", "Kat"));
		System.out.println("Count common enemies of Vince and Kat : " + socialGraph.countCommonEnemies("Vince", "Kat"));
		
		System.out.println("Best friend of Kat in the group : " + socialGraph.findBestFriends("Kat"));
		
		System.out.println("Friendlist in the group : " + socialGraph.friendliest());
		System.out.println("Loner in the group : " + socialGraph.loner());
			
		System.out.println("Is Charice recluse? " + (socialGraph.recluse("Charice") ? "Yes" : "No" ));
		System.out.println("Is Joe recluse? " + (socialGraph.recluse("Joe") ? "Yes" : "No" ));
		
		System.out.println("Is Cherry famous? " + (socialGraph.famous("Cherry") ? "Yes" : "No" ));
		System.out.println("Is Kat famous? " + (socialGraph.famous("Kat") ? "Yes" : "No" ));
	}
}
