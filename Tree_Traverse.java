class Node<T> implements Comparable<T>{
         private Node<T> left;
         private Node<T> right;
         public T data;
         
         public Node(T data){
            this.data=data;
         }
         public Node<T> getLeft(){return this.left;}
         public void setLeft(Node<T> left){this.left=left;};
         public Node<T> getRight(){return this.right;}
         public void setRight(Node<T> right){this.right=right;}      
         public String toString(){return this.data.toString();}
         public int compareTo(Object o){            
            Node<T> oo=(Node<T>)o;            
            if(this.data==oo.data) return 0;
            if(this.data.hashCode()>oo.data.hashCode())return 1;
            else return -1;
         }
         
         public static void preOrderRecursive(Node<?> n){ //similar to recursive DFS
            if(n!=null){
               System.out.print(n+" ");
               preOrderRecursive(n.getLeft());            
               preOrderRecursive(n.getRight());
            }
         }
         
         public static void inOrderRecursive(Node<?> n){
            if(n!=null){
               inOrderRecursive(n.getLeft());
               System.out.print(n+" ");
               inOrderRecursive(n.getRight());
            }
         }
          public static int count;
          public static void inOrderRecursive(Node<?> n,int k){
             count=k;
             if(n!=null && count>0){       
                inOrderRecursive(n.getRight(),k);
                if(count>0)System.out.print(n+","+count+"  "); 
                --count;
                k=count;
                inOrderRecursive(n.getLeft(),k);
             }
          }
         
         public static void postOrderRecursive(Node<?> n){
            if(n!=null){
               postOrderRecursive(n.getLeft());
               postOrderRecursive(n.getRight());
               System.out.print(n+" ");
            }
            /* //another writing method.
            private void visit(ArrayList<Integer> res, TreeNode root) {
               if (root == null) return;
               visit(res, root.left);
               visit(res, root.right);
               res.add(root.val);
            }
            */
         }
         public static void levelOrder(Node<?> n){          //similart to iterative BFS
            if(n==null) return;
            LinkedList<Node<?>> queue=new LinkedList<Node<?>>();
            queue.add(n);
            while(!queue.isEmpty()){
               Node<?> node=queue.removeFirst();
               System.out.print(node+" ");
               if(node.getLeft()!=null) queue.add(node.getLeft());
               if(node.getRight()!=null) queue.add(node.getRight());
            }
         }
         ////////////////////////////////////////    use Stack for iterative
         public static void preOrderIterative(Node<?> root){    
            if(root==null) return;
            else{
               Stack<Node<?>> ss=new Stack<Node<?>>();
               while(true){
                  while(root!=null){
                     System.out.print(root+" ");
                     ss.push(root);
                     root=root.getLeft();
                  }
                  if(ss.isEmpty()) break;
                  root=ss.pop();
                  root=root.getRight();
               }
            }
         }
         public static void inOrderIterative(Node<?> root){
            if(root==null) return;
            Stack<Node<?>> ss=new Stack<Node<?>>();
            while(true){
               while(root!=null){
                  ss.push(root);
                  root=root.getLeft();
               }
               if(ss.isEmpty()) break;
               root=ss.pop();
               System.out.print(root+" ");
               root=root.getRight();
            }
         }
          /* In-Order traversal Iterative without stack, work well.
         public ArrayList<Integer> inorderTraversal(TreeNode root) {
            ArrayList<Integer> ans = new ArrayList<Integer>();
            TreeNode p = root;
            while (p != null) {
                if (p.left == null) {
                    ans.add(p.val);
                    p = p.right;
                } else {
                    TreeNode temp = p.left;
                    while (temp.right != null && temp.right != p) {
                        temp = temp.right;
                    }
                    if (temp.right == null) {
                        temp.right = p;
                        p = p.left;
                    } else {
                        ans.add(p.val);
                        temp.right = null;
                        p = p.right;
                    }
                }
            }
            return ans;
         }
         */
         public static void postOrderIterative(Node<?> root){
            if(root==null) return;
            Stack<Node<?>> ss=new Stack<Node<?>>();
            while(true){
               if(root!=null){
                  if(root.getRight()!=null) ss.push(root.getRight());
                  ss.push(root);
                  root=root.getLeft();
                  continue;
               }
               if(ss.isEmpty()) break;
               root=ss.pop();  // it is the leaf now.
               if(root.getRight()!=null && !ss.isEmpty() && root.getRight()==ss.peek()){
                  ss.pop();
                  ss.push(root);
                  root=root.getRight();
               } else{
                  System.out.print(root+" ");
                  root=null;
               }
            }              
         }
         public static void postOrderIterativeWithTwoStacks(Node<?> root){
            if(root==null) return;
            Stack<Node<?>> ss=new Stack<Node<?>>();
            Stack<Node<?>> output=new Stack<Node<?>>();
            ss.push(root);
            while(!ss.isEmpty()){               
               output.push(ss.peek());
               root=ss.pop();
               if(root.getLeft()!=null) ss.push(root.getLeft());
               if(root.getRight()!=null) ss.push(root.getRight());
            }
            while(!output.isEmpty()){
               System.out.print(output.pop()+" ");
            }
         }

         public static LinkedList<Node<?>> postOrderIterative2(Node<?> root) {
            Stack<Node<?>> stack = new Stack<Node<?>>();
            LinkedList<Node<?>> returnNodes = new LinkedList<Node<?>>();
            while (true) {
               if (root != null) {
                   if (returnNodes.contains(root)) {
                    returnNodes.add(stack.pop());
                    root = null;
                   } else {
                    stack.push(root);
                    root = root.getLeft();
                   }
               } else {
                   if (stack.isEmpty()) break;
                   else if(stack.peek().getRight() == null){
                       root = stack.pop();
                       returnNodes.add(root);
                       if (root == stack.peek().getRight()){
                        returnNodes.add(stack.pop());
                       }
                   }                    
                   if(!stack.isEmpty()) root = stack.peek().getRight();
                   else root = null;                   
               }
            }
            return returnNodes;
         }
      }
