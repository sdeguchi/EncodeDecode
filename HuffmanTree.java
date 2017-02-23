import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeMap;

public class HuffmanTree<E extends Comparable<E>> {	
	public HuffmanNode root;
	
	public HuffmanTree(Scanner codeInput){
		this.root= new HuffmanNode(null,null);
		while(codeInput.hasNext()){
			int name = Integer.parseInt(codeInput.nextLine());
			String path = codeInput.nextLine();

			if(path !=null){
			createSubtree(name,path,root);
		}
			else{
				break;
				}
			}
		}
	public void prefix(HuffmanNode root){
		ArrayList<HuffmanNode> nodeList = new ArrayList<HuffmanNode>();
		if(root == null){
			return;
		}
		else{
			nodeList.add(root);
			prefix(root.left);
			prefix(root.right);	
		}
		
		for(HuffmanNode node: nodeList){
		}	
	}

	public void checkTree(Map<Integer,String>map,HuffmanNode root,String path){
		
		if (root != null) {
			// call checknode for left and right
		if(root.left!=null && root.right!=null){
		checkNode(map, root.left,path+"0");
		checkNode(map, root.right,path+"1");
		}
		else{
			map.put(root.name, path);
		}
		}
		else{
			return;
		}
	}
	
	public void createSubtree(int name,String path, HuffmanNode root){
		if(path.length()==1){
			if(Integer.parseInt(path)==0){
				root.left=new HuffmanNode(name, 0);
			}
			else{
				root.right=new HuffmanNode(name, 0);
			}
			return;
		}
		else{
				
		int dir=Integer.parseInt(path.substring(0,1));
		path=path.substring(1);	
		if(dir==0){
			if(root.left == null){
				root.left=new HuffmanNode(0, 0);
			}
			createSubtree(name,path, root.left);			
		}
		else{
			if(root.right == null){
				root.right=new HuffmanNode(0, 0);
			}
			createSubtree(name,path, root.right);
		}
		}
	}
	public HuffmanTree(int[]  count) {
		 Queue<HuffmanNode> allNodes=new PriorityQueue<HuffmanNode>();
		for(int i = 0; i< count.length; i++){
			if(count[i]>0){
				HuffmanNode node = new HuffmanNode(i, count[i]);
				allNodes.add(node);
			}
		}	
		// add the EOF to the end of the queue
		HuffmanNode EOFnode = new HuffmanNode(256, 1);
		allNodes.add(EOFnode);
		
		while(allNodes.size()>1){
			HuffmanNode n1 =allNodes.poll();
			HuffmanNode n2 =allNodes.poll();
			HuffmanNode subRoot = new HuffmanNode(null,n1.count+n2.count,n1,n2);	    
			allNodes.offer(subRoot);
		}
		
		// when only one node in the queue, set it to the root
			root = allNodes.poll();
	}
	public void write(PrintStream output) {

		Map<Integer,String>m=new TreeMap<Integer,String>();
		String path="";
		checkNode(m, root,path);

				//print out key name and value path
		for(int i:m.keySet()){
				output.println(i);
				output.println(m.get(i));
		}
	
	}
	
	public void checkNode(Map<Integer,String>map,HuffmanNode root,String path){
		
		if (root != null) {
			// call checknode for left and right
		if(root.left!=null && root.right!=null){
		checkNode(map, root.left,path+"0");
		checkNode(map, root.right,path+"1");
		}
		else{
			map.put(root.name, path);
		}
		}
		else{
			return;
		}
	}
	public void decode(BitInputStream input, PrintStream output, int CHAR_MAX){
		
			int bit = input.readBit();

//		if(root==null){
//			
//		}
		HuffmanNode subRoot = root;
		ArrayList<Integer> textChar = new ArrayList<Integer>();
		
		do{
			if(bit == 0){

				subRoot = subRoot.left;
				if(subRoot.left== null){
					textChar.add(subRoot.name);
					
					subRoot = root;
					
				}
			}
			else{

				subRoot = subRoot.right;
				if(subRoot.left == null){
					textChar.add(subRoot.name);
					subRoot = root;
				}
			}
			bit = input.readBit();
			
		}while(bit > -1);

	
	
		for(int i: textChar){
			output.write(i);
		}
	
	
	}
	
	public Integer findLeaf(HuffmanNode root,int bit,BitInputStream input){
		if(root==null || bit == -1 ){
			return 256;
		}

		if(bit == 0){
			if(root.left== null){
				
				return root.name;
			}
			else{
				bit =input.readBit();
				return findLeaf(root.left, bit,input);
			}
		}
		else{
				if(root.right== null){
					return root.name;
				}
				else{
					bit =input.readBit();
					 return findLeaf(root.right, bit,input);
				}
			
		}				
	}
public class HuffmanNode implements  Comparable<HuffmanNode>{
	public Integer name;
	public Integer count;
	public HuffmanNode left;
	public HuffmanNode right;
	public HuffmanNode(	Integer name, Integer count) {
		this.name  = name;
		this.count = count;
	}
	
	public HuffmanNode(Integer name, Integer count, HuffmanNode node1, HuffmanNode node2) {
		this.name = name;
		this.count = count;
		if (node1.count <= node2.count) {
			this.left = node1;
			this.right = node2;
		} else {
			this.left = node2;
			this.right = node1;
		}
	}
	
	
	public HuffmanNode(Integer count) {
		this.name   = null;
		this.count = count;
	}
	
	public String toString(){
	return ""+name+" "+ count;
}
	
	@Override
	public int compareTo(HuffmanNode o) {
		if (this.count==o.count){
			return 0;
		}
		else if(this.count>o.count){
			return 1;
		}
		else{
			return -1;
		}
	
}
}
}
