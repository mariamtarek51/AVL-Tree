package ds2lab1;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
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
		
		
		Node rchild=n.right; 
		Node t=rchild.left;
		
		rchild.left=n;
		n.right=t;
		
		n.height = max(height(n.left), height(n.right)) + 1;
		rchild.height = max(height(rchild.left), height(rchild.right)) + 1;
		
		
		
		return rchild ;
	}
	Node rightRotate(Node n) {
		
		Node lchild=n.left; 
		Node t=lchild.right;
		
		lchild.right=n;
		n.left=t;
		
		n.height = max(height(n.left), height(n.right)) + 1;
		lchild.height = max(height(lchild.left), height(lchild.right)) + 1;
		
		
		
		return lchild;
	} 
	Node searchnode(Node n,String data) { 
		
		
		if(n==null) {
			;
			return null;
		}
	
		if(data.equals(n.data)) {
			
			return n;
		}
		String biggerStr =maxStr(n.data,data);
		
		if(biggerStr.equals(data)) {
			return searchnode(n.right,data);
		}else if(biggerStr.equals("sameword")) {
			return n;
		}else {
			return searchnode(n.left,data);
		}
		
	
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
		
		
		for(int i=0;i<length-1;i++) {
			
			if(data1.charAt(i)>data2.charAt(i)) {   
				return data1 ;
				
			}else if(data2.charAt(i)>data1.charAt(i)) {
				return data2;
			}
		}
		//in case the word already exists
		return "sameword"; 
		
	 
	}

	public static void main(String[] args) { 
		Scanner sc= new Scanner(System.in);
		avltree tree=new avltree();
		
		
		  // tree.root = tree.insertnode(tree.root, "kn");
		//   System.out.println("heey"+"20");
	    //   tree.root = tree.insertnode(tree.root, "cd");
	     //   System.out.println("heey"+"30");
	    //   tree.root = tree.insertnode(tree.root, "bm");
	    ////   tree.root = tree.insertnode(tree.root, "bm");
	    //   tree.root = tree.insertnode(tree.root, "al");
	    //    tree.preOrder(tree.root);
	    //    System.out.println(tree.root);
	    //    tree.searchnode(tree.root,"kn");
	     //   tree.searchnode(tree.root,"norah"); 
	        //System.out.println((tree.root.data));
	     //   System.out.println( tree.height(tree.root)-1);
	        
	        int size=0;
	        avltree mydictionary=new avltree();  
	        try {
	            File myObj = new File("dictionary.txt");
	            Scanner myReader = new Scanner(myObj);
	            while (myReader.hasNextLine()) {
	              String data = myReader.nextLine();
	              mydictionary.root=mydictionary.insertnode(mydictionary.root, data);
	              size=size+1;
	              
	            }
	            myReader.close();
	          } catch (FileNotFoundException e) {
	            System.out.println("An error occurred.");
	            e.printStackTrace();
	          }
	        System.out.println(mydictionary.root.data);
	        mydictionary.preOrder(mydictionary.root);
	        System.out.println(size); 
	       // mydictionary.searchnode(mydictionary.root,"egg");
	        System.out.println("if you want to:" );
	        System.out.println("insert a world enter 1" );
	        System.out.println("Look-up a word enter 2" );
	        System.out.println("remove a word enter 3" ); 
	        System.out.println("End the program enter 4");
	        int str= sc.nextInt(); 
	        
	        	
	        if(str!=4) {
	        	System.out.println("Enter your word" ); 
		        String a= sc.next();
		        
		        if(str==1) {
		        	if(mydictionary.searchnode(mydictionary.root,a)!=null) {
		        		System.out.println(" Word already in the dictionary!");
		        	}else {
		        	
		        mydictionary.root=mydictionary.insertnode(mydictionary.root, a);
		        mydictionary.preOrder(mydictionary.root);
		        }
		        }else if (str==2) {
		        	
		        	if(mydictionary.searchnode(mydictionary.root,a)!=null) {
		        		System.out.println("YES");
		        	}else {
		        		System.out.println("No");
		        	}
		        	
		        	
		        }else {
		        	///
		        	
		        	System.out.println("remove node");
		        	
		        	///
		        }
	        	
	        }
	        
	        
	        }
	    
	        
		// TODO Auto-generated method stub

	}
