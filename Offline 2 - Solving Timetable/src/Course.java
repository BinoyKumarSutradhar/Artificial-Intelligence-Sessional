import java.io.*;
import java.util.*;

public class Course {

    String name;
    String contains;
    int d;
    int final_color;
    boolean visit;

    ArrayList<Course> arrli = new ArrayList<>();


    public Course(String x, String y)
    {
        this.name = x;
        this.contains = y;
        this.d=0;
        this.final_color=0;
        visit=false;

    }


    public void print_course_class()
    {
        System.out.println("Name:"+name+"  Contains:"+contains+"  Degree:"+d+"\n");

        for(int i=0;i<arrli.size();i++)
        {
            System.out.print("->"+arrli.get(i).name);
        }
        System.out.println("\n*******\n");
    }

    public void add(Course x)
    {
        arrli.add(x);
        d++;
    }

    public boolean find(String x)
    {
        for(int i=0;i<arrli.size();i++)
        {
            if( x.equalsIgnoreCase(arrli.get(i).name) )
            {
                return true;
            }
        }
        return false;
    }

    public void color_set(int i)
    {
        final_color = i;
    }

    public boolean nbr_clr_chk(int x)
    {
        for(int i=0;i<arrli.size();i++)
        {
            if( x == arrli.get(i).final_color )
            {
                return false;
            }
        }
        return true;
    }

    public boolean ok()
    {
        if(arrli.size()!= 0)
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    public Course get_nbr()
    {
        Course x = arrli.get(0);
        return x;
    }


}
