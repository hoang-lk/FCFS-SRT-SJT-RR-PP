package SGK;

import java.util.Collections;
import java.util.List;

public class FCFSLaplich extends SGKLaplich
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
        
        List<Sukien> timeline = this.getTimeline();
        
        for (Hang row : this.getRows())
        {
            if (timeline.isEmpty())
            {
                timeline.add(new Sukien(row.getProcessName(), row.getArrivalTime(), row.getArrivalTime() + row.getBurstTime()));
            }
            else
            {
                Sukien event = timeline.get(timeline.size() - 1);
                timeline.add(new Sukien(row.getProcessName(), event.getFinishTime(), event.getFinishTime() + row.getBurstTime()));
            }
        }
        
        for (Hang row : this.getRows())
        {
            row.setWaitingTime(this.getEvent(row).getStartTime() - row.getArrivalTime());
            row.setTurnaroundTime(row.getWaitingTime() + row.getBurstTime());
        }
    }
}
