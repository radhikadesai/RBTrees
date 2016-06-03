import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class bbst {
    public static void main(String[] args) throws IOException {
        // take input file from arguments
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        FileReader inputFile = new FileReader(args[0] + ".txt");
        BufferedReader file = new BufferedReader(inputFile);
        int size = Integer.parseInt(file.readLine());
        List<Event> events = new ArrayList<>();
        String line;
        //Add events to list
        while ((line = file.readLine()) != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(line, " ");
            while (stringTokenizer.hasMoreElements()) {
                Integer key = Integer.parseInt(stringTokenizer.nextElement().toString());
                Integer value = Integer.parseInt(stringTokenizer.nextElement().toString());
                events.add(new Event(key,value));
            }
        }
        RBTree tree = new RBTree();
        tree.constructBSTFromList(events,0,size);
        inputFile.close();
        file.close();

        while ((line = br.readLine()) != null&&!line.equals("quit")) {

            if(line.isEmpty())
                continue;
            StringTokenizer stringTokenizer = new StringTokenizer(line, " ");
            while (stringTokenizer.hasMoreElements()) {
                Integer id,count1;
                Event event;
                String command = stringTokenizer.nextElement().toString();
                //read from commands.txt
                switch (command.toString()) {
                    case "increase":
                        id = Integer.parseInt(stringTokenizer.nextElement().toString());
                        count1 = Integer.parseInt(stringTokenizer.nextElement().toString());
                        event=new Event(id,count1);
                        tree.increase(event);
                        break;
                    case "reduce":
                        id = Integer.parseInt(stringTokenizer.nextElement().toString());
                        count1 = Integer.parseInt(stringTokenizer.nextElement().toString());
                        event=new Event(id,count1);
                        tree.decrease(event);
                        break;
                    case "count":
                        id = Integer.parseInt(stringTokenizer.nextElement().toString());
                        tree.count(id);
                        break;
                    case "inrange":
                        id = Integer.parseInt(stringTokenizer.nextElement().toString());
                        count1 = Integer.parseInt(stringTokenizer.nextElement().toString());
                        int range= tree.inRange(id, count1);
                        System.out.println(range);
                        break;
                    case "next":
                        id = Integer.parseInt(stringTokenizer.nextElement().toString());
                        tree.next(id);
                        break;

                    case "previous":
                        id = Integer.parseInt(stringTokenizer.nextElement().toString());
                        tree.previous(id);
                        break;
                }
            }
        }
        br.close();
    }
}
