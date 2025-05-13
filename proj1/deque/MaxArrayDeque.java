package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T>{

    private Comparator<T> comparator;

    //creates a MaxArrayDeque with the given Comparator
    public MaxArrayDeque(Comparator<T> c)
    {
        super();
        comparator = c;
    }

    //returns the maximum element in the deque as governed by the previously given Comparator
    public T max()
    {
        return max(comparator);
    }


    public T max(Comparator<T> c)
    {
        if(size() == 0)
        {
           return null;
        }

        T MaxItem = get(0);

        for(int i = 0; i < size(); i++)
        {
            T curItem = get(i);
            if(c.compare(MaxItem,curItem) < 0)
            {
                MaxItem = curItem;
            }
        }
        return MaxItem;
    }
}
