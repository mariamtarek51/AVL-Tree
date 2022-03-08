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
	void preOrder(Node n) {
        if (n != null) {
            System.out.print(n.data + " ");
            preOrder(n.left);
            preOrder(n.right);
        }
    }
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
		System.out.println("leftrotate"+n.data);
		
		Node rchild=n.right; 
		Node t=rchild.left;
		
		rchild.left=n;
		n.right=t;
		
		n.height = max(height(n.left), height(n.right)) + 1;
		rchild.height = max(height(rchild.left), height(rchild.right)) + 1;
		
		
		
		return rchild ;
	}
	Node rightRotate(Node n) {
		System.out.println("rightrotate"+n.data);
		Node lchild=n.left; //9
		Node t=lchild.right;
		
		lchild.right=n;
		n.left=t;
		
		n.height = max(height(n.left), height(n.right)) + 1;
		lchild.height = max(height(lchild.left), height(lchild.right)) + 1;
		
		
		
		return lchild;
	} 
	boolean searchnode(Node n,String data) { 
		if(n==null) {
			System.out.println("No,this element doesn't exist");
			return false;
		}
		if(n.data==data) {
			System.out.println("yes");
			return true;
		}
		String biggerStr =maxStr(n.data,data);
		if(biggerStr==data) {
			searchnode(n.right,data);
		}else {
			searchnode(n.left,data);
		} 
		
		
		
		return true;
		
	}
	Node insertnode(Node n,String data) { 
		
		
		if(n==null) { //in case its the first element 
			System.out.println("the rooot");
			return (new Node(data));
		} 
		String biggerStr =maxStr(n.data,data); 
		if(biggerStr==n.data) {
			System.out.println("leeeft");
			n.left=insertnode(n.left,data);
		}else if (biggerStr==data) {
			System.out.println("Riggght");
			n.right=insertnode(n.right,data);
			
		}else {
			return n;
		} 
		n.height=1+max(height(n.left),height(n.right));
		int bf= balancedfactor(n);
		System.out.println("bf is"+bf);
		if(bf!=1&&bf!=-1&&bf!=0) {
			if(bf<-1) {
		//RR case
		biggerStr =maxStr(data,n.right.data);
		if(biggerStr==data) {
			return leftRotate(n);
		}
		//RL case 
		if(biggerStr==n.right.data) {
			n.right = rightRotate(n.right);
            return leftRotate(n);
		}
			}
		
			if(bf>1) {
		//LL case 
		biggerStr =maxStr(data,n.left.data);
		if(biggerStr==n.left.data) {
			return rightRotate(n);
		}
		//LR case 
		
		if(biggerStr==data) {
			n.left = leftRotate(n.left);
            return rightRotate(n);
		}
			}
		
		}
		
		System.out.println(n.data);
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
		System.out.println("heey"+"10");
		
		   tree.root = tree.insertnode(tree.root, "kn");
		   System.out.println("heey"+"20");
	       tree.root = tree.insertnode(tree.root, "cd");
	        System.out.println("heey"+"30");
	        tree.root = tree.insertnode(tree.root, "bm");
	        tree.root = tree.insertnode(tree.root, "bm");
	        tree.root = tree.insertnode(tree.root, "al");
	        tree.preOrder(tree.root);
	        tree.searchnode(tree.root,"kn");
	        tree.searchnode(tree.root,"norah");
	        
		// TODO Auto-generated method stub

	}

}