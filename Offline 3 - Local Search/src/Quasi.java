import java.io.*;
import java.util.*;


public class Quasi {

    ArrayList<ArrayList<Box> > square ;
    int dim;
    //List<Box> unassigned = new ArrayList<>();
    List<Box> unassigned;
    public static int nodes = 0;
    public static int fails = 0;

    public Quasi(ArrayList<ArrayList<Box> > x, int y)
    {
        square = x;
        dim = y;
        unassigned = new ArrayList<>();
    }

    public Quasi(Quasi q)
    {
        this.dim = q.dim;
        this.square = new ArrayList<>();
        for (int i=0; i<dim; i++)
        {
            ArrayList<Box> t = new ArrayList<>();
            for (int j=0; j<dim; j++)
            {
                Box b = new Box(q.square.get(i).get(j));
                t.add(b);
            }
            square.add(t);
        }

        this.unassigned = new ArrayList<>();
        for (int i=0; i<q.unassigned.size(); i++)
        {
            Box b = new Box(q.unassigned.get(i));
            unassigned.add(b);
        }
    }

    public void print_mat()
    {
        System.out.println("\nmatrix:\n");
        /*for (int i=0; i<test.size(); i++)
        {
            for (int j=0; j<test.size(); j++)
            {
                System.out.println(i+"-"+j+" :"+test.get(i).get(j));
            }
        }*/
        for (int i=0; i<square.size(); i++)
        {
            for (int j=0; j<square.size(); j++)
            {
                //System.out.print(i+"-"+j+" :"+square.get(i).get(j).getVal()+"-->"+square.get(i).get(j).isFlag());
                //System.out.print(square.get(i).get(j).getVal()+"-->"+square.get(i).get(j).isFlag());
                System.out.print(square.get(i).get(j).getVal());
                System.out.print("   ");

                if(square.get(i).get(j).isFlag() == false)
                {
                    square.get(i).get(j).X = i;
                    square.get(i).get(j).Y = j;
                    for(int k=1; k<=dim; k++)
                    {
                        square.get(i).get(j).domain.add(k);
                    }
                    unassigned.add(square.get(i).get(j));
                }
            }
            System.out.println();
        }
        /*for (int i=0; i<square.size(); i++)
        {
            for (int j=0; j<square.size(); j++)
            {
                //System.out.println(i+"-"+j+" :"+square.get(i).get(j).getVal()+"-->"+square.get(i).get(j).isFlag());
                if(square.get(i).get(j).isFlag() == false)
                {
                    for(int k=0; k<dim; k++)
                    {
                        System.out.print(" -> "+square.get(i).get(j).domain.get(k));
                    }
                    System.out.println();
                }
            }
        }*/
        print_dom();
    }

    public void print_dom()
    {
        /*for (int i=0; i<square.size(); i++)
        {
            for (int j=0; j<square.size(); j++)
            {
                if(square.get(i).get(j).isFlag() == false)
                {
                    for(int k=0; k<dim; k++)
                    {
                        System.out.print(" -> "+square.get(i).get(j).domain.get(k));
                    }
                    System.out.println();
                }
            }
        }*/
        System.out.println("Domain list:");
        for (Box e:unassigned) {
            for(int k=0; k<e.domain.size(); k++)
            {
                System.out.print("-> "+e.domain.get(k));
            }
            System.out.print("&& Degree:"+e.degree+"\n");
            //System.out.println();
        }
    }

    public void calcu_domain()
    {
        unassigned = new ArrayList<>();
        for (int i=0; i<square.size(); i++)
        {
            for (int j=0; j<square.size(); j++)
            {
                if(square.get(i).get(j).isFlag() == false)
                {
                    List<Integer> dup = square.get(i).get(j).getDomain();
                    for (int m=0; m<dim; m++)
                    {
                            int x = square.get(i).get(m).val;
                            if(dup.contains(x))
                            {
                                dup.remove(new Integer(x));
                            }
                    }
                    for (int m=0; m<dim; m++)
                    {
                            int x = square.get(m).get(j).val;
                            if(dup.contains(x))
                            {
                                dup.remove(new Integer(x));
                            }
                    }
                    unassigned.add(square.get(i).get(j));
                }
            }
        }
        calcu_degree();
    }

    public void calcu_degree()
    {
        for (int i=0; i<square.size(); i++)
        {
            for (int j=0; j<square.size(); j++)
            {
                if(square.get(i).get(j).isFlag() == false)
                {
                    square.get(i).get(j).degree = 0;
                    for (int m=0; m<dim; m++)
                    {
                       if(m!=j)
                       {
                           if(square.get(i).get(m).flag == false)
                           {
                               square.get(i).get(j).degree++;
                           }
                       }
                    }
                    for (int m=0; m<dim; m++)
                    {
                        if(m!=i)
                        {
                            if(square.get(m).get(j).flag == false)
                            {
                                square.get(i).get(j).degree++;
                            }
                        }
                    }
                }
            }
        }

        //brelaz();
    }


    public void brelaz()
    {
        //List<Box> unassigned = a;
        Collections.sort(unassigned, new sort_degree());
        print_dom();
        /*for (int  i=1; i<unassigned.size(); i++)
        {
            Box a = unassigned.get(i-1);
            Box b = unassigned.get(i);
            if(b.domain.size() == a.domain.size())
            {
                if(b.degree > a.degree)
                {
                    Collections.swap(unassigned, i, i-1);
                }
            }
        }*/
        int n = unassigned.size();
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
            {
                Box a = unassigned.get(j);
                Box b = unassigned.get(j+1);
                if(b.domain.size() == a.domain.size())
                {
                    if(b.degree > a.degree)
                    {
                        Collections.swap(unassigned, j, j+1);
                    }
                }

            }
        System.out.println("\nAfter brelaz:");
        if(unassigned.size() != 0)
        {
            print_dom();
        }


    }

    public void domdeg()
    {
        int s_degree = (dim*2)-2;
        double r =(double) s_degree;
        int n = unassigned.size();
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
            {
                Box a = unassigned.get(j);
                Box b = unassigned.get(j+1);
                double p =(double) a.domain.size();
                double q =(double) b.domain.size();
                double div1 = p/r;
                double div2 = q/r;
                if(div2 < div1)
                {
                    Collections.swap(unassigned, j, j+1);
                }

            }
        //System.out.println("\nAfter domdeg:");
        //print_dom();
    }

    public boolean Fwd_Chk_bre()
    {
        calcu_domain();
        if(unassigned.size() == 0)
        {
            System.out.println("\nsuccessful\n");
            print_mat();
            System.out.println("Total nodes: "+nodes+"\n");
            System.out.println("Total fails: "+fails+"\n");
            return true;
        }
        brelaz();
        for(int i=0; i<unassigned.size(); i++)
        {
            //nodes++;
            for (int j=0; j<unassigned.get(i).domain.size(); j++)
            {
                //fails++;
                Quasi rec = new Quasi(this);
                int m = unassigned.get(i).X;
                int n = unassigned.get(i).Y;
                rec.square.get(m).get(n).val = unassigned.get(i).domain.get(j);
                rec.square.get(m).get(n).flag = true;
                rec.square.get(m).get(n).degree = 0;
                rec.square.get(m).get(n).domain = new ArrayList<>();
                rec.unassigned.remove(i);

                boolean bull = rec.fwd();
                if(bull == true)
                {
                    nodes++;
                    return rec.Fwd_Chk_bre();
                }
                else
                {
                    nodes++;
                    fails++;
                    continue;
                }
            }
        }
        return true;
    }

    public boolean fwd()
    {
        Quasi q = new Quasi(this);
        q.calcu_domain();
        for (int i=0; i<q.dim; i++)
        {
            for (int j=0; j<q.dim; j++)
            {
                if(q.square.get(i).get(j).val == 0)
                {
                    if(q.square.get(i).get(j).domain.size() == 0)
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
