import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by radhikadesai on 18/03/2016.
 */
public class BTTreeTest {

    @Test
    public void itTestsInitializationOfBTTree(){
        //Given
        List<Event> events;
        events = Arrays.asList(new Event(1,3),new Event(2,1),new Event(4,2),new Event(8,3),new Event(9,1),new Event(10,1));
        RBTree tree = new RBTree();
        //When
        Node root= tree.constructBSTFromList(events,0,events.size());
        //Then
   /*     assert root.getEvent().getId()==4;
        assert root.getLeft().getEvent().getId()==1;
        assert root.getLeft().getRight().getEvent().getId()==2;
        assert root.getRight().getEvent().getId()==9;
        assert root.getRight().getLeft().getEvent().getId()==8;
        assert root.parent==tree.nil;
        assert root.getLeft().parent==root;
        assert root.getRight().parent==root;
        assert root.getLeft().getRight().parent==root.getLeft();*/
        tree.inorderTraversal(root);
    }

    @Test
    public void itTestsIncrease(){
        //Given
        List<Event> events = new ArrayList<Event>();
        Event event = new Event(6,2);
        events = Arrays.asList(new Event(1,3),new Event(2,1),new Event(4,2),new Event(8,3),new Event(9,1));
        RBTree tree = new RBTree();
        //when
        tree.increase(event);
        //Then
    }
    @Test
    public void itTestsIncrease2(){
        //Given
        RBTree tree = new RBTree();
        Event root = new Event(5,2);
        Event e2 = new Event(6,2);
        Event e3 = new Event(7,2);
        //when
        tree.increase(root);
        tree.increase(e2);
        tree.increase(e3);
        //Then
        tree.inorderTraversal(tree.root);
    }
    @Test
    public void itTestsDecrease(){
        //Given
        RBTree tree = new RBTree();
        Event e1 = new Event(5,2);
        Event e2 = new Event(6,2);
        Event e3 = new Event(7,2);
        Event e4 = new Event(4,2);
        Event e5 = new Event(2,2);
        Event e6 = new Event(3,2);
        Event e7 = new Event(1,2);
        Event e8 = new Event(9,2);
        Event e9 = new Event(8,2);
        Event e10= new Event(10,3);
        //when
        tree.increase(e1);
        tree.increase(e2);
        tree.increase(e3);
        tree.increase(e4);
        tree.increase(e5);
        tree.increase(e6);
        tree.increase(e7);
        tree.increase(e8);
        tree.increase(e9);
        tree.decrease(e1);
        tree.decrease(e10);
        //Then
        tree.inorderTraversal(tree.root);
    }
    @Test
    public void itTestsCount(){
        //Given
        //Given
        RBTree tree = new RBTree();
        Event e1 = new Event(5,8);
        Event e2 = new Event(6,2);
        Event e3 = new Event(7,2);
        Event e4 = new Event(10,5);
        //when
        tree.increase(e1);
        tree.increase(e2);
        tree.increase(e3);
        tree.count(e4.getId());
        //Then

    }
    @Test
    public void itTestsRange(){
        //Given
        RBTree tree = new RBTree();
        Event e1 = new Event(12,8);
        Event e2 = new Event(5,2);
        Event e3 = new Event(15,2);
        Event e4 = new Event(13,5);
        Event e5 = new Event(17,5);
        Event e6 = new Event(14,5);
        Event e7 = new Event(3,5);
        Event e8 = new Event(4,5);
        Event e9 = new Event(10,5);
        Event e10 = new Event(7,3);
        Event e11 = new Event(11,5);
        Event e12 = new Event(6,7);
        Event e13 = new Event(8,5);
        //when
        tree.increase(e1);
        tree.increase(e2);
        tree.increase(e3);
        tree.increase(e4);
        tree.increase(e5);
        tree.increase(e6);
        tree.increase(e7);
        tree.increase(e8);
        tree.increase(e9);
        tree.increase(e10);
        tree.increase(e11);
        tree.increase(e12);
        tree.increase(e13);
        tree.inorderTraversal(tree.root);
        int total = tree.inRange(3,7);
        //Then
        System.out.println("Total is: "+total);
    }
    @Test
    public void itTestsNext(){
        //Given
        //Given
        RBTree tree = new RBTree();
        Event e1 = new Event(12,8);
        Event e2 = new Event(5,2);
        Event e3 = new Event(15,2);
        Event e4 = new Event(13,5);
        Event e5 = new Event(17,5);
        Event e6 = new Event(14,5);
        Event e7 = new Event(3,5);
        Event e8 = new Event(4,5);
        Event e9 = new Event(10,5);
        Event e10 = new Event(7,5);
        Event e11 = new Event(11,5);
        Event e12 = new Event(6,5);
        Event e13 = new Event(8,5);
        //when
        tree.increase(e1);
        tree.increase(e2);
        tree.increase(e3);
        tree.increase(e4);
        tree.increase(e5);
        tree.increase(e6);
        tree.increase(e7);
        tree.increase(e8);
        tree.increase(e9);
        tree.increase(e10);
        tree.increase(e11);
        tree.increase(e12);
        tree.increase(e13);
        tree.next(18);
    }
    @Test
    public void itTestsPrevious(){
        RBTree tree = new RBTree();
        Event e1 = new Event(12,8);
        Event e2 = new Event(5,2);
        Event e3 = new Event(15,2);
        Event e4 = new Event(13,5);
        Event e5 = new Event(17,5);
        Event e6 = new Event(14,5);
        Event e7 = new Event(3,5);
        Event e8 = new Event(4,5);
        Event e9 = new Event(10,5);
        Event e10 = new Event(7,5);
        Event e11 = new Event(11,5);
        Event e12 = new Event(6,5);
        Event e13 = new Event(8,5);
        //when
        tree.increase(e1);
        tree.increase(e2);
        tree.increase(e3);
        tree.increase(e4);
        tree.increase(e5);
        tree.increase(e6);
        tree.increase(e7);
        tree.increase(e8);
        tree.increase(e9);
        tree.increase(e10);
        tree.increase(e11);
        tree.increase(e12);
        tree.increase(e13);
        tree.previous(6);
    }
}
