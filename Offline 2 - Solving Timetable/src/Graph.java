import java.io.*;
import java.util.*;

public class Graph {

    ArrayList<ArrayList<String> > student_details = new ArrayList<>();//total
    List<Course> course_details = new ArrayList<>();//c_obj
    List<Course> duplicate = new ArrayList<>();
    List<Course> sorted = new ArrayList<>();
    List<Course> kempe = new ArrayList<>();
    List<Course> save = new ArrayList<>();

    HashMap<String, Integer> current = new HashMap<>();

    int first = 0;
    int second = 0;


    public Graph(ArrayList<ArrayList<String> > m, List<Course> c)
    {
        this.student_details = m;
        this.course_details = c;
    }

    public float penalty()
    {
        int p=0;
        float final_penalty =0;
        for(int i=0;i<student_details.size();i++)//for printing student.txt properly
        {
            int cnt = 0;
            List<Course> rcv ;
            List<Course> pen = new ArrayList<>();

            for(int j=0;j<student_details.get(i).size();j++)
            {
                Course c1= list_traversal(student_details.get(i).get(j));
                pen.add(c1);
            }

            rcv = sort_penalty(pen);
            for(int k=0; k<rcv.size()-1; k++)
            {
                cnt= rcv.get(k).final_color - rcv.get(k+1).final_color ;
                if(cnt == 1)
                {
                    p = p + 16;
                }
                if(cnt == 2)
                {
                    p = p + 8;
                }
                if(cnt == 3)
                {
                    p = p + 4;
                }
                if(cnt == 4)
                {
                    p = p + 2;
                }
                if(cnt == 5)
                {
                    p = p + 1;
                }
                if(cnt > 5)
                {
                    p = p + 0;
                }
            }

        }

        float m = p;
        float n = student_details.size();

        final_penalty = m/n;

        return final_penalty;

    }

    public List sort_penalty(List<Course> c)
    {
        int x;
        x = c.size();
        for (int i = 0; i < x-1; i++)
        {
            for (int j = 0; j < x-i-1; j++)
            {
                if(c.get(j + 1).final_color > c.get(j).final_color)
                {
                    Collections.swap(c, j, j+1);
                }

            }

        }
        return c;
    }


    public Course list_traversal(String x)
    {
        for(int i=0;i<course_details.size();i++)
        {
            if( x.equalsIgnoreCase(course_details.get(i).name) )
            {
                return course_details.get(i);
            }
        }
        return null;
    }

    public void calcu()// for finding neighbour nodes
    {
        for(int i=0;i<student_details.size();i++)//for printing student.txt properly
        {
            for(int j=0;j<student_details.get(i).size();j++)
            {
                for(int k=0;k<student_details.get(i).size();k++)
                {
                    if( !(student_details.get(i).get(j).equalsIgnoreCase(student_details.get(i).get(k))) )
                    {
                        Course c1= list_traversal(student_details.get(i).get(j));
                        Course c2= list_traversal(student_details.get(i).get(k));
                        if( !(c1.find(c2.name)) )
                        {
                            c1.add(c2);
                        }
                        if( !(c2.find(c1.name)) )
                        {
                            c2.add(c1);
                        }
                    }
                }
            }
        }
        float p = 0;
        float x = 0;
        sort_degree();
        coloring();
        p=penalty();
        System.out.println("\nBefore Kempe Chain Penalty: "+p);

        for (int i=0; i<1000; i++)
        {
            p=penalty();
            kempe_chain();
            x=penalty();

            if(p>x)
            {
                continue;
            }
            else
            {
                apply3();
            }
            save.clear();

        }
        try (FileWriter f = new FileWriter("yor83_sol.txt")) {
            for(int i=0;i<sorted.size();i++)
            {
                f.write("Index:"+i+"  Name:"+sorted.get(i).name+"  Color:"+sorted.get(i).final_color+"  Degree:"+sorted.get(i).d+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\nAfter Kempe Chain Penalty: "+penalty());
    }

    public void apply3()
    {
        for(int i=0;i<save.size();i++)
        {
            if(save.get(i).final_color == first)
            {
                save.get(i).final_color = second;
            }
            if(save.get(i).final_color == second)
            {
                save.get(i).final_color = first;
            }
        }
    }


    public void sort_degree()
    {
        duplicate = course_details;

        int cnt = 0;
        int x;
        x=duplicate.size();

        for (int i = 0; i < x-1; i++)
        {
            for (int j = 0; j < x-i-1; j++)
            {
                if(duplicate.get(j + 1).d > duplicate.get(j).d)
                {
                    Collections.swap(duplicate, j, j+1);
                }

            }

        }

        sorted = duplicate;

    }

    //print
    public void print_graph()
    {
        for(int i=0;i<course_details.size();i++)
        {
            course_details.get(i).print_course_class();
        }
    }

    public void coloring()
    {
        for(int i=0;i<sorted.size();i++)
        {
            for(int j=1; j<=sorted.size();j++)
            {
                if( sorted.get(i).nbr_clr_chk(j) )
                {
                    sorted.get(i).color_set(j);
                    break;
                }
                else
                {
                    continue;
                }
            }
        }

    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public void kempe_chain()
    {
        kempe = sorted;
        int x;

        while (true)
        {
            x = getRandomNumber(0,kempe.size()-1);
            if(kempe.get(x).ok())
            {
                break;
            }
        }
        Course t = kempe.get(x);
        Course t1 = kempe.get(x).get_nbr();

        save.add(t);
        save.add(t1);

        int temp = t.final_color;
        t.final_color = t1.final_color;
        t1.final_color = temp;

        int temp1 = t.final_color;
        int temp2 = t1.final_color;

        bfs(t,t1,temp1,temp2);//System.out.println("********\n"+ t.name+" "+t1.name);

        sorted = kempe;
    }

    public void bfs( Course x1, Course x2, int t1, int t2)
    {
        LinkedList<Course> queue = new LinkedList<Course>();
        Course t;
        queue.add(x1);
        x1.visit = true;

        while (queue.size() != 0)
        {
            t = queue.poll();
            for(int i=0; i<t.arrli.size();i++)
            {
                if(t.arrli.get(i).visit == false)
                {
                    t.arrli.get(i).visit = true;

                    if(t.arrli.get(i).final_color == t1 && t.arrli.get(i) != x2)
                    {
                        t.arrli.get(i).final_color = t2;
                        save.add(t.arrli.get(i));
                    }
                    if(t.arrli.get(i).final_color == t2 && t.arrli.get(i) != x2)
                    {
                        t.arrli.get(i).final_color = t1;
                        save.add(t.arrli.get(i));
                    }
                    queue.add(t.arrli.get(i));
                }
            }
        }

    }

}
