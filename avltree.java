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
		//System.out.println("bf is"+bf);
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
		
		//System.out.println(n.data);
		return n;
	}

	Node DeleteNode(Node n, String data){
         if (n==null){
			 return  n;
		 }
		 else if (maxStr(data,n.data) ==data){
                 n.right=DeleteNode(n.right,data);
		 }
		 else if (maxStr(data,n.data) ==n.data){
			 n.left=DeleteNode(n.left,data);
		 }
		 else {
			 if ((n.right==null || n.left==null)){
				 Node newnode=null;
				 if(n.right!=null){ newnode=n.right; }
				 else if (n.left!=null){ newnode=n.left; }
				 if(newnode==null){
					 newnode=n;
					 n=null;
				 }else{
					 n=newnode;
				 }newnode=null;
			 }
			 else {
				 Node t=minimumNode(n.right);
				 n.data=t.data;
				 n.right=DeleteNode(n.right,t.data);
			 }
		 }
		 if(n==null){
			 return n;
		 }
		 n.height=max(height(n.right),height(n.left))+1;
		 if(balancedfactor(n)>1){
			 if(balancedfactor(n.left)>=0){
				 return rightRotate(n);
			 }else{
				 n.left=leftRotate(n.left);
				 return rightRotate(n);
			 }
		 }else if (balancedfactor(n)<-1){
			 if(balancedfactor(n.right)<=0){
				 return leftRotate(n);
			 }else {
				 n.right=rightRotate(n.right);
				 return rightRotate(n);
			 }
		 }
       return n;
	}
	// Find the minimum node on left side
	public Node minimumNode(Node node){
		Node result = node;
		while (result.left != null)
		{
			result = result.left;
		}
		return result;
	}
	String maxStr(String data1,String data2) {
		int length,samenum=0;
		String maxstr="";
		if(data1.length()>data2.length()) {
			maxstr=data1;
			length=data2.length();
		} else if(data1.length()<data2.length()){
			maxstr=data2;
			length=data1.length();
		}else {
			samenum=1;
			length=data1.length();
		} 
		
		for(int i=0;i<length;i++) {
			if(data1.charAt(i)>data2.charAt(i)) {   
				return data1 ;
				
			}else if(data2.charAt(i)>data1.charAt(i)) {
				return data2;
			}
		}
		
		//in case the word already exists
		if(samenum==1) {
		return "sameword"; 
		}else {
			return maxstr;
			
		}
	 
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
	              System.out.println("size i file for word "+data+" is "+data.length());
	              mydictionary.root=mydictionary.insertnode(mydictionary.root,data);
	              size=size+1;

	            }
	            myReader.close();
	          } catch (FileNotFoundException e) {
	            System.out.println("An error occurred.");
	            e.printStackTrace();
	          }
		System.out.println("if you want to:" );
		System.out.println("Print dictionary size enter 0" );
		System.out.println("insert a world enter 1" );
		System.out.println("Look-up a word enter 2" );
		System.out.println("remove a word enter 3" );
		System.out.println("Batch look-ups enter 4" );
		System.out.println("Batch deletions enter 5" );
		System.out.println("End the program enter 6");
		int str= sc.nextInt();


		if(str!=6) {
            if(str==0){System.out.println(mydictionary.height(mydictionary.root));}

			if(str==1) {
				System.out.println("Enter your word" );
				String a= sc.next();
				if(mydictionary.searchnode(mydictionary.root,a)!=null) {
					System.out.println(" Word already in the dictionary!");
				}else {

					mydictionary.root=mydictionary.insertnode(mydictionary.root, a);
					mydictionary.preOrder(mydictionary.root);
				}
			}else if (str==2) {
				System.out.println("Enter your word" );
				String a= sc.next();
				if(mydictionary.searchnode(mydictionary.root,a)!=null) {
					System.out.println("YES");
				}else {
					System.out.println("No");
				}


			}else if (str==3) {
				System.out.println("Enter your word" );
				String a= sc.next();
				if(mydictionary.searchnode(mydictionary.root,a)!=null) {
					System.out.println("YES");
					mydictionary.preOrder(mydictionary.root);
					mydictionary.DeleteNode(mydictionary.root,a);
					System.out.println();
					mydictionary.preOrder(mydictionary.root);


				}else {
					System.out.println("Error !The word is not found");
				}
			}else if (str==4) {
				int sizeQueries=0;
				int found =0;
				try {
					File myObj = new File("queries.txt");
					Scanner myReader = new Scanner(myObj);
					while (myReader.hasNextLine()) {
						String data = myReader.nextLine();
						if(mydictionary.searchnode(mydictionary.root,data)!=null) {
							System.out.println(data+"--> YES");
							found=found+1;}
						else {System.out.println(data+"--> NO");}
						sizeQueries=sizeQueries+1;

					}
					System.out.println("Queries file contains :"+sizeQueries);
					System.out.println("Total number of found words in mydictionary : "+found);
					myReader.close();
				} catch (FileNotFoundException e) {
					System.out.println("An error occurred.");
					e.printStackTrace();
				}
			}if(str==5){
				try {
					File myObj = new File("deletions.txt");
					Scanner myReader = new Scanner(myObj);
					while (myReader.hasNextLine()) {
						String data = myReader.nextLine();

						if(mydictionary.searchnode(mydictionary.root,data)!=null) {
							mydictionary.DeleteNode(mydictionary.root,data);
						}}
					myReader.close();
					System.out.println("dictionary after deletions: ");
					mydictionary.preOrder(mydictionary.root);
				} catch (FileNotFoundException e) {
					System.out.println("An error occurred.");
					e.printStackTrace();
				}
			}

			else {
				///

				System.out.println(" ");

				///
			}

		}

	}


}

