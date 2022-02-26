import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        File file1 = new File("C:\\Users\\Binoy\\Desktop\\3-2\\318\\Offline-2\\1605072\\Inputs\\yor83_c.txt");
        File file2 = new File("C:\\Users\\Binoy\\Desktop\\3-2\\318\\Offline-2\\1605072\\Inputs\\yor83_s.txt");
        Scanner sc1 = new Scanner(file1);
        Scanner sc2 = new Scanner(file2);

        int course_count = 0;
        int student_count = 0;
        List<Course> c_obj = new ArrayList<>();//course.txt
        ArrayList<ArrayList<String> > total = new ArrayList<>();//student.txt
        Graph g;

        while(sc1.hasNextLine())
        {
            Scanner t1 = new Scanner(sc1.nextLine());
            course_count = course_count + 1;

            while(t1.hasNext())
            {
                String s1 = t1.next();
                String s2 = t1.next();

                Course c = new Course(s1,s2);
                c_obj.add(c);

            }

        }

        while(sc2.hasNextLine())
        {
            Scanner t2 = new Scanner(sc2.nextLine());
            student_count = student_count + 1;
            ArrayList<String> temp = new ArrayList<>();

            while(t2.hasNext())
            {
                String s = t2.next();
                temp.add(s);
            }
            total.add(temp);
        }


        g = new Graph(total, c_obj);
        g.calcu();

    }

}
