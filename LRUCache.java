package dp;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
	private class Node{
		int key;
		int value;
		Node prev;
		Node next;
		
	}
	
	private Map<Integer, Node> map= new HashMap<Integer, LRUCache.Node>();
	private Node head,tail;
	private int totalItems;
	private int capacity;
	
	public LRUCache(int capacity) {
		this.capacity=capacity;
		this.totalItems=0;
		head = new Node();
		tail = new Node();
		head.prev=null;
		head.next=tail;
		tail.next=null;
		tail.prev=head;
		
	}

	public int get(int key) {
		Node node=map.get(key);
		if(node==null) {
			return -1;
		}
		else {
			moveToHead(node);
			return node.value;
		}
	}

	public void put(int key, int value) {
		Node node=map.get(key);
		if(node==null) {
			Node newNode=new Node();
			newNode.key=key;
			newNode.value=value;
			map.put(key, newNode);
			addNode(newNode);
			this.totalItems++;
		}
		else {
			 node.value=value;
			 moveToHead(node);
		 }
		 if(totalItems > capacity){
	          removeAnItem();
	        } 
	}
	public void removeAnItem() {
		Node itemToRemove=tail.prev;
		map.remove(itemToRemove.key);
		--totalItems;
		removeNode(itemToRemove);
		
	}
	
	public void addNode(Node node) {
		node.prev=head;
		node.next=head.next;
		head.next.prev=node;
		head.next=node;
	}
	
	public void removeNode(Node node) {
		node.prev.next=node.next;
		node.next.prev=node.prev;
		
	}
	public void moveToHead(Node node) {
		removeNode(node);
	    addNode(node);
		//node.prev=this.head;
		//node.next=this.head.next;
		//this.head.next=node;
		//this.head.next.prev=node;
		
	}
	public static void main(String[] args) {
  //Running Example from LeetCode
		LRUCache cache = new LRUCache(2);
		cache.put(1, 1);
		cache.put(2, 2);
		System.out.println(cache.get(1));       // returns 1
		cache.put(3, 3);    // evicts key 2
		System.out.println(cache.get(2));       // returns -1 (not found)
		cache.put(4, 4);    // evicts key 1
		System.out.println(cache.get(1));       // returns -1 (not found)
		System.out.println(cache.get(3));       // returns 3
		System.out.println(cache.get(4));       // returns 4
	}
	
}
