package ds2lab1;
 class Node{
	 String data;
	 int height;
	 Node right; //right child
	 Node left; //left child
	 Node(String d){
		 height=1;
		 data=d;
	 }
 }
public class avltree {
	Node root; 
	int max(int a, int b) {
        if(a>b)
        	return a;
        return b;
    }
	int height(Node n) {
        if (n == null)
            return 0;
 
        return n.height;
    } 
	int balancedfactor(Node n) { 
		if(n== null)
			return 0;
		
		
		return height(n.left) - height(n.right);
	} 
	Node leftRotate(Node n) {
		return n;
	}
	Node rightRotate(Node n) {
		return n;
	}
	Node insertnode(Node n,String data) { 
		if(n==null) { //in case its the first element 
			return (new Node(data));
		} 
		String biggerStr =maxStr(n.data,data); 
		if(biggerStr==n.data) {
			n.left=insertnode(n.left,data);
		}else if (biggerStr==data) {
			n.right=insertnode(n.right,data);
			
		}else {
			return n;
		} 
		n.height=1+max(height(n.left),height(n.right));
		int bf= balancedfactor(n);
		//RR case
		biggerStr =maxStr(data,n.right.data);
		if(bf<-1&&biggerStr==data) {
			return leftRotate(n);
		}
		//RL case 
		if(bf<-1&&biggerStr==n.right.data) {
			n.right = rightRotate(n.right);
            return leftRotate(n);
		}
		//LL case 
		biggerStr =maxStr(data,n.left.data);
		if(bf>1&&biggerStr==n.left.data) {
			return rightRotate(n);
		}
		//LR case 
		
		if(bf>1&&biggerStr==data) {
			n.left = leftRotate(n.left);
            return rightRotate(n);
		}
		
		
		
		
		return n;
	} 
	
	String maxStr(String data1,String data2) {
		int length;
		if(data1.length()>data2.length()) {
			length=data1.length();
		} else {
			length=data2.length();
		}
		
		for(int i=0;i<length;i++) {
			if(data1.charAt(i)>data2.charAt(i)) {   
				return data1 ;
				
			}else if(data2.charAt(i)>data1.charAt(i)) {
				return data2;
			}
		}
		return "same"; 
		
	 
	}

	public static void main(String[] args) { 
		avltree tree=new avltree();
		tree.root=tree.insertnode(tree.root,"mariam");
		// TODO Auto-generated method stub

	}

}
