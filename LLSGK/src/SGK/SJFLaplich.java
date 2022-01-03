package SGK;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SJFLaplich extends SGKLaplich
{
    @Override
    public void process()
    {
        Collections.sort(this.getRows(), (Object o1, Object o2) -> {
            if (((Hang) o1).getArrivalTime() == ((Hang) o2).getArrivalTime())
            {
                return 0;
            }
            else if (((Hang) o1).getArrivalTime() < ((Hang) o2).getArrivalTime())
            {
                return -1;
            }
            else
            {
                return 1;
            }
        });
        
        List<Hang> rows = HamChung.deepCopy(this.getRows());
        int time = rows.get(0).getArrivalTime();
        
        while (!rows.isEmpty())
        {
            List<Hang> availableRows = new ArrayList();
            
            for (Hang row : rows)
            {
                if (row.getArrivalTime() <= time)
                {
                    availableRows.add(row);
                }
            }
            
            Collections.sort(availableRows, (Object o1, Object o2) -> {
                if (((Hang) o1).getBurstTime() == ((Hang) o2).getBurstTime())
                {
                    return 0;
                }
                else if (((Hang) o1).getBurstTime() < ((Hang) o2).getBurstTime())
                {
                    return -1;
                }
                else
                {
                    return 1;
                }
            });
            
            Hang row = availableRows.get(0);
            this.getTimeline().add(new Sukien(row.getProcessName(), time, time + row.getBurstTime()));
            time += row.getBurstTime();
            
            for (int i = 0; i < rows.size(); i++)
            {
                if (rows.get(i).getProcessName().equals(row.getProcessName()))
                {
                    rows.remove(i);
                    break;
                }
            }
        }
        
        for (Hang row : this.getRows())
        {
            row.setWaitingTime(this.getEvent(row).getStartTime() - row.getArrivalTime());
            row.setTurnaroundTime(row.getWaitingTime() + row.getBurstTime());
        }
    }
}
