package SGK;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.io.FileReader;

public class GiaoDien
{
    private JFrame khungHinh;
    private JPanel panelChinh;
    private TuychinhPanel vePanel;
    private JScrollPane bangPane;
    private JScrollPane bieudoPane;
    private JTable table;
    private JButton themBtn;
    private JButton xoaBtn;
    private JButton docFileBtn;
    private JButton luufileBtn;
    private JButton computeBtn;
    private JLabel thoigGianChoLabel;
    private JLabel thoiGianchoKetquaLabel;
    private JLabel tatcaLabel;
    private JLabel tatcaKetquaLabel;
    @SuppressWarnings("rawtypes")
	private JComboBox option;
    private DefaultTableModel model;
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public GiaoDien()
    {
        model = new DefaultTableModel(new String[]{"Tên sách", "Thời điểm đặt hàng", "Thời gian sản xuất", "Độ ưu tiên", "Thời gian chờ", "Thời gian hoàn thành"}, 0);
        
        table = new JTable(model);
        table.setFillsViewportHeight(true);
        bangPane = new JScrollPane(table);
        bangPane.setBounds(25, 25, 700, 250);
        
        themBtn = new JButton("Thêm hàng");
        themBtn.setBounds(460, 280, 85, 25);
        
        themBtn.setFont(new Font(Font.DIALOG, Font.PLAIN, 10));
        themBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                model.addRow(new String[]{"", "", "", "", "", ""});
            } 
        });
        
        xoaBtn = new JButton("Xóa hàng");
        xoaBtn.setBounds(640, 280, 85, 25);
        xoaBtn.setFont(new Font(Font.DIALOG, Font.PLAIN, 10));
        xoaBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                
                if (row > -1) {
                    model.removeRow(row);
                }
            }
        });
        
        docFileBtn = new JButton("Tải tệp");
        docFileBtn.setBounds(370, 280, 85, 25);
        docFileBtn.setFont(new Font(Font.DIALOG, Font.PLAIN, 10));
        docFileBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            	JFileChooser fileChooser = new JFileChooser();
            	FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
            	fileChooser.setFileFilter(filter);
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                  File selectedFile = fileChooser.getSelectedFile();
                  try {
                	  if(selectedFile==null)
                		  return;
                	  BufferedReader br = new BufferedReader(new FileReader(selectedFile.getAbsolutePath()));

            	      String line = br.readLine();
            	      if(line==null)
            	    	  return;
            	      while (line != null) {
            	          ArrayList<String> input = new ArrayList<String>(Arrays.asList(line.split("\\s+")));
            	          if(input.size() < 3 || input.size() > 4)
            	          {
            	        	  br.close();
            	        	  return;
            	          }
            	          input.add("");
            	          input.add("");
            	          model.addRow(input.toArray());
            	          line = br.readLine();
            	      }
            	      br.close();

				} catch (IOException e1) {
					e1.printStackTrace();
					}
                }
                
            } 
        });
        luufileBtn = new JButton("Lưu");
        luufileBtn.setBounds(550, 280, 85, 25);
        luufileBtn.setFont(new Font(Font.DIALOG, Font.PLAIN, 10));
        luufileBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            	JFileChooser fileChooser = new JFileChooser();
            	fileChooser.setDialogTitle("Lưu file");   
            	FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
            	fileChooser.setFileFilter(filter); 
            	int userSelection = fileChooser.showSaveDialog(null);
            	 
            	if (userSelection == JFileChooser.APPROVE_OPTION) {
            	    File fileToSave = fileChooser.getSelectedFile();
            	    try (PrintWriter out = new PrintWriter(fileToSave.getAbsolutePath()+".txt")) {
            	    	for (int i = 0; i < model.getRowCount(); i++)
                        {
            	    		for(int j=0; j<6; j++) {
            	    			out.print(String.valueOf(model.getValueAt(i, j)));
            	    			if(j!=5) {
            	    				out.print(" ");
            	    			}
            	    		}
            	    		out.println();
                        }
            	    } catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
            	}
            } 
        });
        vePanel = new TuychinhPanel();
        vePanel.setBackground(Color.WHITE);
        bieudoPane = new JScrollPane(vePanel);
        bieudoPane.setBounds(25, 310, 700, 100);
        
        thoigGianChoLabel = new JLabel("Thời gian chờ trung bình:");
        thoigGianChoLabel.setBounds(25, 425, 180, 25);
        tatcaLabel = new JLabel("Thời gian hoàn thành trung bình:");
        tatcaLabel.setBounds(25, 450, 210, 25);
        thoiGianchoKetquaLabel = new JLabel();
        thoiGianchoKetquaLabel.setBounds(215, 425, 180, 25);
        tatcaKetquaLabel = new JLabel();
        tatcaKetquaLabel.setBounds(265, 450, 180, 25);
        
        option = new JComboBox(new String[]{"SRT","SJF" ,"RR", "FCFS", "PP" });
        option.setBounds(640, 420, 85, 20);
        
        computeBtn = new JButton("Lập lịch");
        computeBtn.setBounds(640, 450, 85, 25);
        computeBtn.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        computeBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) option.getSelectedItem();
                SGKLaplich scheduler;
                
                switch (selected) {
                    case "FCFS":
                        scheduler = new FCFSLaplich();
                        break;
                    case "SJF":
                        scheduler = new SJFLaplich();
                        break;
                    case "SRT":
                        scheduler = new SRTLaplich();
                        break;
                    case "PP":
                        scheduler = new PriorityPreemptiveLaplich();
                        break;
                    case "RR":
                        String tq = JOptionPane.showInputDialog("Mức Thời Gian");
                        if (tq == null) {
                            return;
                        }
                        scheduler = new RoundRobinLaplich();
                        scheduler.setTimeQuantum(Integer.parseInt(tq)); 
                        break;
                    default:
                        return;
                }
                
                for (int i = 0; i < model.getRowCount(); i++)
                {
                    String process = (String) model.getValueAt(i, 0);
                    int at = Integer.parseInt((String) model.getValueAt(i, 1));
                    int bt = Integer.parseInt((String) model.getValueAt(i, 2));
                    int pl;
                    
                    if ( selected.equals("PP"))
                    {
                        if (!model.getValueAt(i, 3).equals(""))
                        {
                            pl = Integer.parseInt((String) model.getValueAt(i, 3));
                        }
                        else
                        {
                            pl = 1;
                        }
                    }
                    else
                    {
                        pl = 1;
                    }
                                        
                    scheduler.add(new Hang(process, at, bt, pl));
                }
                
                scheduler.process();
                
                for (int i = 0; i < model.getRowCount(); i++)
                {
                    String process = (String) model.getValueAt(i, 0);
                    Hang row = scheduler.getRow(process);
                    model.setValueAt(row.getWaitingTime(), i, 4);
                    model.setValueAt(row.getTurnaroundTime(), i, 5);
                }
                
                thoiGianchoKetquaLabel.setText(Double.toString(scheduler.getAverageWaitingTime())+" giây");
                tatcaKetquaLabel.setText(Double.toString(scheduler.getAverageTurnAroundTime())+" giây");
                
                vePanel.setTimeline(scheduler.getTimeline());
            }
        });
        
        panelChinh = new JPanel(null);
        panelChinh.setPreferredSize(new Dimension(750, 500));
        panelChinh.add(bangPane);
        panelChinh.add(themBtn);
        panelChinh.add(xoaBtn);
        panelChinh.add(docFileBtn);
        panelChinh.add(luufileBtn);
        panelChinh.add(bieudoPane);
        panelChinh.add(thoigGianChoLabel);
        panelChinh.add(tatcaLabel);
        panelChinh.add(thoiGianchoKetquaLabel);
        panelChinh.add(tatcaKetquaLabel);
        panelChinh.add(option);
        panelChinh.add(computeBtn);
        
        khungHinh = new JFrame("Lập lịch sản xuất SGK");
        khungHinh.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        khungHinh.setVisible(true);
        khungHinh.setResizable(false);
        khungHinh.add(panelChinh);
        khungHinh.pack();
    }
    
    public static void main(String[] args)
    {
        new GiaoDien();
    }
    
    @SuppressWarnings("serial")
	class TuychinhPanel extends JPanel
    {   
        private List<Sukien> timeline;
        
        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            
            if (timeline != null)
            {          
                for (int i = 0; i < timeline.size(); i++)
                {
                    Sukien event = timeline.get(i);
                    int x = 60 * (i + 1);
                    int y = 20;
                    
                    g.drawRect(x, y, 60, 30);
                    g.setFont(new Font("Segoe UI", Font.BOLD, 13));
                    g.drawString(event.getProcessName(), x + 10, y + 20);
                    g.setFont(new Font("Segoe UI", Font.PLAIN, 11));
                    g.drawString(Integer.toString(event.getStartTime()), x - 5, y + 45);
                    
                    if (i == timeline.size() - 1)
                    {
                        g.drawString(Integer.toString(event.getFinishTime()), x + 47, y + 45);
                    }
                }
            }
        }
        
        public void setTimeline(List<Sukien> timeline)
        {
            this.timeline = timeline;
            repaint();
        }
    }
}
