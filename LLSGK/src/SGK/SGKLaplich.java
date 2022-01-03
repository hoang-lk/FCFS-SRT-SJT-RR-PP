package SGK;

import java.util.ArrayList;
import java.util.List;

public abstract class SGKLaplich
{
    private final List<Hang> rows;
    private final List<Sukien> timeline;
    private int timeQuantum;
    
    public SGKLaplich()
    {
        rows = new ArrayList();
        timeline = new ArrayList();
        timeQuantum = 1;
    }
    
    public boolean add(Hang row)
    {
        return rows.add(row);
    }
    
    public void setTimeQuantum(int timeQuantum)
    {
        this.timeQuantum = timeQuantum;
    }
    
    public int getTimeQuantum()
    {
        return timeQuantum;
    }
    
    public double getAverageWaitingTime()
    {
        double avg = 0.0;
        
        for (Hang row : rows)
        {
            avg += row.getWaitingTime();
        }
        
        return avg / rows.size();
    }
    
    public double getAverageTurnAroundTime()
    {
        double avg = 0.0;
        
        for (Hang row : rows)
        {
            avg += row.getTurnaroundTime();
        }
        
        return avg / rows.size();
    }
    
    public Sukien getEvent(Hang row)
    {
        for (Sukien event : timeline)
        {
            if (row.getProcessName().equals(event.getProcessName()))
            {
                return event;
            }
        }
        
        return null;
    }
    
    public Hang getRow(String process)
    {
        for (Hang row : rows)
        {
            if (row.getProcessName().equals(process))
            {
                return row;
            }
        }
        
        return null;
    }
    
    public List<Hang> getRows()
    {
        return rows;
    }
    
    public List<Sukien> getTimeline()
    {
        return timeline;
    }
    
    public abstract void process();
}
