package SGK;

import java.util.ArrayList;
import java.util.List;

public class HamChung
{
    public static List<Hang> deepCopy(List<Hang> oldList)
    {
        List<Hang> newList = new ArrayList();
        
        for (Hang row : oldList)
        {
            newList.add(new Hang(row.getProcessName(), row.getArrivalTime(), row.getBurstTime(), row.getPriorityLevel()));
        }
        
        return newList;
    }
}
