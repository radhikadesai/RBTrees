import java.util.List;

/**
 * Created by radhikadesai on 17/03/2016.
 */
public class RBTree {
    // Initialize root to nil
    Node nil=new Node(new Event(0,0),Color.BLACK,null,null,null);
    Node root = nil;

    //Constructing the RBTree
    public Node constructBSTFromList(List<Event> list, int start, int end){
        int height = (int)Math.ceil(Math.log(list.size())/ Math.log(2));
        Node node = constructTreeFromList(list,start,end,nil,1,height);
        return node;
    }

    public Node constructTreeFromList(List<Event> list, int start, int end,Node previous,int height,int maxHeight){
        if(start>end || (start+end)/2==list.size()){
            return nil;
        }
        int mid=(start+end)/2;
        Node node = new Node(list.get(mid),nil,nil);
        if(height==maxHeight)
            node.setColor(Color.RED);
        else
            node.setColor(Color.BLACK);
        node.parent=previous;
        if(previous==nil){
           root=node;
        }
        node.left=constructTreeFromList(list,start,mid-1,node,height+1,maxHeight);
        node.right=constructTreeFromList(list,mid+1,end,node,height+1,maxHeight);
        return node;
    }

    //INCREASE
    public void increase(Event event){
      Node element = searchForElement(root,event);
      if(element.getEvent().getId()!=event.getId()){
          insert(element,event);
      }
      else{
          int count = element.getEvent().getCount();
          count+=event.getCount();
          element.getEvent().setCount(count);
          System.out.println(element.getEvent().getCount());
      }
    }
    // Insert if node not present
    private void insert(Node parent,Event event) {
        Node newNode = new Node(event,nil,nil);
        newNode.setColor(Color.RED);
        newNode.parent=parent;
        if(parent==nil){
            root=newNode;
        }
        if(event.getId()>parent.getEvent().getId()){
            parent.right=newNode;
        }
        else {
            parent.left=newNode;
        }
        System.out.println(newNode.getEvent().getCount());

        fixInsert(newNode);
    }
    // Rebalance the tree after an insert
    private void fixInsert( Node newNode) {
        while(newNode.parent.getColor()==Color.RED){
            if(newNode.parent==newNode.parent.parent.getLeft()){
                Node y = newNode.parent.parent.getRight();
                if(y.getColor()==Color.RED){
                    newNode = reColor(newNode, y);
                }
                else {
                    if (newNode == newNode.parent.getRight()) {
                        newNode=newNode.parent;
                        leftRotate(newNode);
                    }
                    newNode.parent.setColor(Color.BLACK);
                    newNode.parent.parent.setColor(Color.RED);
                    rightRotate(newNode.parent.parent);
                }
            }
            else{
                Node y = newNode.parent.parent.getLeft();
                if(y.getColor()==Color.RED){
                    newNode = reColor(newNode, y);
                }
                else {
                    if (newNode == newNode.parent.getLeft()) {
                        newNode=newNode.parent;
                        rightRotate(newNode);
                    }
                    newNode.parent.setColor(Color.BLACK);
                    newNode.parent.parent.setColor(Color.RED);
                    leftRotate(newNode.parent.parent);
                }
            }
        }
        root.setColor(Color.BLACK);
    }

    //Right rotate
    private Node rightRotate( Node newNode) {
        Node y = newNode.getLeft();
        newNode.left=y.getRight();
        if(y.right!=nil){
            y.right.parent=newNode;
        }
        y.parent=newNode.parent;
        if(newNode.parent==nil){
            root=y;
        }
        else if(newNode==newNode.parent.getLeft()){
            newNode.parent.left=y;
        }
        else{
            newNode.parent.right=y;
        }
        y.right=newNode;
        newNode.parent=y;
        return root;
    }
    //LEft Rotate
    private Node leftRotate( Node newNode) {
        Node y = newNode.getRight();
        newNode.right=y.getLeft();
        if(y.left!=nil){
            y.left.parent = newNode;
        }
        y.parent=newNode.parent;
        if(newNode.parent==nil){
            root=y;
        }
        else if(newNode==newNode.parent.getLeft()){
            newNode.parent.left=y;
        }
        else{
            newNode.parent.right=y;
        }
        y.left=newNode;
        newNode.parent=y;
        return root;
    }
    // re-color nodes if uncle is red
    private Node reColor(Node newNode, Node y) {
        newNode.parent.setColor(Color.BLACK);
        y.setColor(Color.BLACK);
        newNode.parent.parent.setColor(Color.RED);
        newNode=newNode.parent.parent;
        return newNode;
    }
    // Searching for an element in the tree
    private Node searchForElement(Node node,Event event) {
        if(event.getId()==node.getEvent().getId()){
            return node;
        }
        if(event.getId()>node.getEvent().getId())
        {
            if(node.getRight()==nil||node==nil){
                return node;
            }
            return searchForElement(node.getRight(), event);
        }
        else {
            if(node.getLeft()==nil||node==nil){
                return node;
            }
            return searchForElement(node.getLeft(),event);
        }
    }
    // DECREASE
    public void decrease(Event event){
        Node element = searchForElement(root,event);
        if(element.getEvent().getId()!=event.getId()){
                System.out.println(0);
        }
        else{
            int count = element.getEvent().getCount();
            count-=event.getCount();
            if(count>0){
                element.getEvent().setCount(count);
                System.out.println(element.getEvent().getCount());
            }
            else {
                delete(element);
                System.out.println(0);
            }
        }
    }
    // Transplant while deleting
    public void transplant(Node element, Node child) {
        if(element.parent == this.nil) {
            root = child;
        } else if(element == element.parent.getLeft()) {
            element.parent.setLeft(child);
        } else {
            element.parent.setRight(child);
        }

        child.parent=element.parent;
    }
    // Delete
    private void delete(Node element) {
        Color origColor = element.getColor();
        Node newNode;
        if(element.getLeft() == nil) {
            newNode = element.getRight();
            transplant(element, element.getRight());
        } else if(element.getRight() == nil) {
            newNode = element.getLeft();
            transplant(element, element.getLeft());
        } else {
            Node y;
            for(y = element.getRight(); y.getLeft() != nil; y = y.getLeft()) {
                ;
            }
            origColor = y.getColor();
            newNode = y.getRight();
            if(y.parent == element) {
                newNode.parent=y;
            } else {
                this.transplant(y, y.getRight());
                y.setRight(element.getRight());
                y.getRight().parent=y;
            }

            transplant(element, y);
            y.setLeft(element.getLeft());
            y.getLeft().parent=y;
            y.setColor(element.getColor());
        }

        if(origColor == Color.BLACK) {
            deleteFixUp(newNode);
        }
    }
    // Rebalancing the tree after delete
    public void deleteFixUp(Node newNode){
        while(newNode!=root && newNode.getColor()==Color.BLACK){
            if(newNode==newNode.parent.getLeft()){
                Node w=newNode.parent.getRight();
                if(w.getColor()==Color.RED){
                    w.setColor(Color.BLACK);
                    newNode.parent.setColor(Color.RED);
                    leftRotate(newNode.parent);
                    w=newNode.parent.getRight();
                }
                if(w.getLeft().getColor()==Color.BLACK && w.getRight().getColor()==Color.BLACK){
                    w.setColor(Color.RED);
                    newNode=newNode.parent;
                }
                else{
                    if(w.getRight().getColor()==Color.BLACK){
                        w.getLeft().setColor(Color.BLACK);
                        w.setColor(Color.RED);
                        rightRotate(w);
                        w=newNode.parent.getRight();
                    }
                    w.setColor(newNode.parent.getColor());
                    newNode.parent.setColor(Color.BLACK);
                    w.getRight().setColor(Color.BLACK);
                    leftRotate(newNode.parent);
                    newNode=root;
                }
            }
            else{
                Node w=newNode.parent.getLeft();
                if(w.getColor()==Color.RED){
                    w.setColor(Color.BLACK);
                    newNode.parent.setColor(Color.RED);
                    rightRotate(newNode.parent);
                    w=newNode.parent.getLeft();
                }
                if(w.getRight().getColor()==Color.BLACK && w.getLeft().getColor()==Color.BLACK){
                    w.setColor(Color.RED);
                    newNode=newNode.parent;
                }
                else{
                    if(w.getLeft().getColor()==Color.BLACK){
                        w.getRight().setColor(Color.BLACK);
                        w.setColor(Color.RED);
                        leftRotate(w);
                        w=newNode.parent.getLeft();
                    }
                    w.setColor(newNode.parent.getColor());
                    newNode.parent.setColor(Color.BLACK);
                    w.getLeft().setColor(Color.BLACK);
                    rightRotate(newNode.parent);
                    newNode=root;
                }
            }
        }
        newNode.setColor(Color.BLACK);
    }
    // Count of the particular id
    public void count(int Id){
        Event event = new Event(Id,0);
        Node element= searchForElement(root,event);
        if(element.getEvent().getId()!=event.getId()){
            System.out.println(0);
        }
        else {
            System.out.println(element.getEvent().getCount());
        }
    }
    // inorder Traversal of the tree
    public void inorderTraversal(Node node){
        if(node.getLeft()!=nil) {
            inorderTraversal(node.getLeft());
        }
        System.out.println("Node --> " + node.getEvent().getId() + "Color "+node.getColor());
        if(node.getRight()!=nil) {
            inorderTraversal(node.getRight());
        }
    }
    public int inorderTraversal(Node node,int id1,int id2,int total){
        if(node.getEvent().getId()>=id1 && node.getEvent().getId()<=id2){
            total=total + node.getEvent().getCount();
        }
        if(node.getLeft()!=nil && node.getEvent().getId()>=id1) {
            total= inorderTraversal(node.getLeft(),id1,id2,total);
        }
        if(node.getRight()!=nil && node.getEvent().getId()<=id2) {
            total= inorderTraversal(node.getRight(),id1,id2,total);
        }
        return total;
    }
    //Calculate total count in a range
    public int inRange(int id1,int id2){
        return inorderTraversal(root,id1,id2,0);
    }
    //Search for the next smallest element
    public void next(int id){
        Node element = searchForElement(root,new Event(id,0));
        if(element.getEvent().getId()>id){
            System.out.println(element.getEvent().getId()+" "+element.getEvent().getCount());
        }
        else if(element.getRight()==nil){
          if(element==element.parent.getLeft()){
              System.out.println(element.parent.getEvent().getId()+" "+element.parent.getEvent().getCount());
          }
          else{
              do{
                  element=element.parent;
              }while(element==element.parent.getRight()&&element.parent!=nil);
              if(element.parent==nil){
                  System.out.println("0 0");
              }
              else
              {
                  System.out.println(element.parent.getEvent().getId()+" "+element.parent.getEvent().getCount());
              }
          }
        }
        else if(element.getRight()!=nil){
            element=element.getRight();
            while(element.getLeft()!=nil){
                element=element.getLeft();
            }
            System.out.println(element.getEvent().getId()+" "+element.getEvent().getCount());
        }
        else{
            System.out.println("0 0");
        }
    }
    //Search for the previous biggest element
    public void previous(int id){
        Node element = searchForElement(root,new Event(id,0));
        if(element.getEvent().getId()<id){
            System.out.println(element.getEvent().getId()+" "+element.getEvent().getCount());
        }
        else if(element.getLeft()==nil){
            if(element==element.parent.getRight()){
                System.out.println(element.parent.getEvent().getId()+" "+element.parent.getEvent().getCount());
            }
            else{
                do{
                    element=element.parent;
                }while(element==element.parent.getLeft()&&element.parent!=nil);
                if(element.parent==nil){
                    System.out.println("0 0");
                }
                else
                {
                    System.out.println(element.parent.getEvent().getId()+" "+element.parent.getEvent().getCount());
                }
            }
        }
        else if(element.getLeft()!=nil){
            element=element.getLeft();
            while(element.getRight()!=nil){
                element=element.getRight();
            }
            System.out.println(element.getEvent().getId()+" "+element.getEvent().getCount());
        }
        else{
            System.out.println("0 0");
        }
    }
}
