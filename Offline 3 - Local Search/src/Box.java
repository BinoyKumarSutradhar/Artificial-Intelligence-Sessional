import java.io.*;
import java.util.*;

public class Box {
    int val; // ei box e je value thakbe
    List<Integer> domain ; // domain list
    // calculate_domain() method lekha lagbe

    //CORDINATE
    int X;
    int Y;

    int ratio;
    int degree;
    boolean flag;// flag=true hole value assigned, otherwise unassigned

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public int getRatio() {
        return ratio;
    }

    public void setRatio(int ratio) {
        this.ratio = ratio;
    }

    public Box(int x)
    {
        val = x;
        if(x==0)
        {
            flag = false;
        }
        else
        {
            flag = true;
        }
        domain = new ArrayList<>();
        degree = 0;
        ratio = 0;
        X = -1;
        Y = -1;
    }

    public Box(Box b)
    {
        this.val = b.val;
        this.flag = b.flag;
        domain = new ArrayList<>();
        this.degree = b.degree;
        this.ratio = b.ratio;
        domain = new ArrayList<>();
        for (int i=0; i<b.domain.size(); i++)
        {
            domain.add(b.domain.get(i));
        }
        X = b.X;
        Y = b.Y;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void setDomain(int x)
    {
        domain.add(x);
    }

    public List<Integer> getDomain() {
        return domain;
    }
}
