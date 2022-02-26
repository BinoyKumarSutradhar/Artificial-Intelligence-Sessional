import java.util.Comparator;

public class sort_degree implements Comparator<Box> {

    public int compare(Box a, Box b)
    {
        return a.domain.size() - b.domain.size();
    }
}
