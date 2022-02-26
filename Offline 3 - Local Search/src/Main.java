import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException
    {
        //ArrayList<ArrayList<Integer> > test = new ArrayList<>();
        ArrayList<ArrayList<Box> > square = new ArrayList<>();

        //Scanner input = new Scanner(new File("d-10-01.txt.txt"));
        Scanner input = new Scanner(new File("d-15-01.txt.txt"));

        String s1 = input.nextLine();
        //System.out.println(s1);
        //char c = s1.charAt(2);
        //int c1 = Character.getNumericValue(c);
        String s = s1.substring(0,s1.length()-1);
        int c1 = Integer.parseInt(s.split("=")[1]);
        System.out.println(c1);
        //*** c1 is the dimension of the square

        System.out.println(c1+"\n");
        input.nextLine();
        input.nextLine();
        //int i=0;

        while(input.hasNext())
        {
            //square.add(new ArrayList<Integer>());
            //ArrayList<Integer> temp = new ArrayList<>();
            ArrayList<Box> t = new ArrayList<>();
            String str = input.nextLine();
            str = str.replaceAll("[^\\d]", " ");
            str = str.trim();
            str = str.replaceAll(" +", " ");
            //System.out.println(str);
            Scanner t1 = new Scanner(str);
            while (t1.hasNextInt())
            {
                int x = t1.nextInt();
                Box b = new Box(x);
                t.add(b);
                //temp.add(x);
                //System.out.println(x);
            }
            //test.add(temp);
            square.add(t);
        }
        //************** CLASS DECLARATION*******
        Quasi q = new Quasi(square,c1);
        q.print_mat();
        //q.print_dom();
        q.calcu_domain();
        //q.calcu_degree();
        System.out.println("\n After\n");
        q.print_dom();
        q.Fwd_Chk_bre();

        //q.brelaz();
        //q.domdeg();

        //System.out.println("\nmatrix:\n");
        /*for (int i=0; i<test.size(); i++)
        {
            for (int j=0; j<test.size(); j++)
            {
                System.out.println(i+"-"+j+" :"+test.get(i).get(j));
            }
        }*/
        /*for (int i=0; i<square.size(); i++)
        {
            for (int j=0; j<square.size(); j++)
            {
                System.out.println(i+"-"+j+" :"+square.get(i).get(j).getVal()+"-->"+square.get(i).get(j).isFlag());
                if(square.get(i).get(j).isFlag() == false)
                {
                    for(int k=1; k<=c1; k++)
                    {
                        square.get(i).get(j).domain.add(k);
                    }

                }
            }
        }*/
        /*for (int i=0; i<square.size(); i++)
        {
            for (int j=0; j<square.size(); j++)
            {
                //System.out.println(i+"-"+j+" :"+square.get(i).get(j).getVal()+"-->"+square.get(i).get(j).isFlag());
                if(square.get(i).get(j).isFlag() == false)
                {
                    for(int k=0; k<c1; k++)
                    {
                        System.out.print(" -> "+square.get(i).get(j).domain.get(k));
                    }
                    System.out.println();
                }
            }
        }*/

        // numbers have been extracted
    }

}
