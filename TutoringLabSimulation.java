import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TutoringLabSimulation
{
    private int minInterArrivalTime; 
    private int maxInterArrivalTime;
    private int minServiceTime;
    private int maxServiceTime;
    private int numOfQueues;
    private int numOfStudents;
    private TextField minArr, maxArr, minSer, maxSer, numQs, numStds;
    private JLabel avgTime, maxStdsInQ;
    private JPanel panel;
    private JFrame frame;
    
    //Initialize max queue size to 0
    private int maxQueueSize = 0;
    //Initialize total wait time to 0
    private int totalTime = 0;

    public static void main(String[] args)
    {  
        TutoringLabSimulation labSim = new TutoringLabSimulation();
        labSim.enterData();
    }

    private void enterData()
    {
        frame = new JFrame("Queue Simulation Program");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        panel.setLayout(null);
        JLabel argsLabel = new JLabel("Input Arguments:");
        JLabel minArrLabel = new JLabel("Minimum Inter-Arrival Time");
        JLabel maxArrLabel = new JLabel("Maximum Inter-Arrival Time");
        JLabel minSerLabel = new JLabel("Minimum Service Time");
        JLabel maxSerLabel = new JLabel("Maximum Service Time");
        JLabel numQsLabel = new JLabel("Number of Queues");
        JLabel numStdsLabel = new JLabel("Number of Students");
        JLabel avgTimeLabel = new JLabel("Average Wait Time: ");
        JLabel maxStdsInQLabel = new JLabel("Largest number of students waiting in a queue: ");
        argsLabel.setBounds(5, 5, 150, 25);
        minArrLabel.setBounds(15, 30, 170, 25);
        maxArrLabel.setBounds(15, 55, 170, 25);
        minSerLabel.setBounds(15, 80, 170, 25);
        maxSerLabel.setBounds(15, 105, 170, 25);
        numQsLabel.setBounds(15, 130, 170, 25);
        numStdsLabel.setBounds(15, 155, 115, 25);
        avgTimeLabel.setBounds(95, 225, 115, 25);
        maxStdsInQLabel.setBounds(15, 250, 270, 25);
        panel.add(argsLabel);
        panel.add(minArrLabel);
        panel.add(maxArrLabel);
        panel.add(minSerLabel);
        panel.add(maxSerLabel);
        panel.add(numQsLabel);
        panel.add(numStdsLabel);
        panel.add(avgTimeLabel);
        panel.add(maxStdsInQLabel);

        minArr = new TextField(20);
        maxArr = new TextField(20);
        minSer = new TextField(20);
        maxSer = new TextField(20);
        numQs = new TextField(20);
        numStds = new TextField(20);
        
        minArr.setBounds(190, 30, 50, 20);
        maxArr.setBounds(190, 55, 50, 20);
        minSer.setBounds(190, 80, 50, 20);
        maxSer.setBounds(190, 105, 50, 20);
        numQs.setBounds(190, 130, 50, 20);
        numStds.setBounds(190, 155, 50, 20);
        
        panel.add(minArr);
        panel.add(maxArr);
        panel.add(minSer);
        panel.add(maxSer);
        panel.add(numQs);
        panel.add(numStds);       

        JButton button1 = new JButton("Simulate");
        JButton button2 = new JButton("Clear");
        button1.setBounds(2, 180, 165, 35);
        button2.setBounds(167, 180, 165, 35);     
        panel.add(button1);
        panel.add(button2);

        frame.add(panel);
        frame.setSize(350,330);
        frame.setVisible(true);

        ActionListener actionListener1 = new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                minInterArrivalTime = Integer.parseInt(minArr.getText()); 
                maxInterArrivalTime = Integer.parseInt(maxArr.getText());
                minServiceTime = Integer.parseInt(minSer.getText());
                maxServiceTime = Integer.parseInt(maxSer.getText());
                numOfQueues = Integer.parseInt(numQs.getText());
                numOfStudents = Integer.parseInt(numStds.getText());
                
                runSimulation();
                
                avgTime = new JLabel(Integer.toString(totalTime));
                maxStdsInQ = new JLabel(Integer.toString(maxQueueSize));
                avgTime.setBounds(210, 225, 50, 25);
                maxStdsInQ.setBounds(285, 250, 50, 25);
                panel.add(avgTime);
                panel.add(maxStdsInQ);
                panel.repaint();                
            }          
        };
        button1.addActionListener(actionListener1);
        
        ActionListener actionListener2 = new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                minArr.setText("");
                maxArr.setText("");
                minSer.setText("");
                maxSer.setText("");
                numQs.setText("");
                numStds.setText("");
                avgTime.setText("");
                maxStdsInQ.setText("");
            }          
        };
        button2.addActionListener(actionListener2);  
    }

    private void runSimulation()
    {
        //Random number generator
        Random generator = new Random();

        //Create next arrival event (arrival time, service time, completion time)
        int arrivalTime = (int)(generator.nextDouble() * (maxInterArrivalTime - minInterArrivalTime) + minInterArrivalTime);
        int serviceTime = (int)(generator.nextDouble() * (maxServiceTime - minServiceTime) + minServiceTime);
        int completionTime = arrivalTime + serviceTime;
        
        int newServiceTime = 0;
        int newCompletionTime = completionTime;
        int newArrivalTime = arrivalTime;
                        
        //Initialize time to 0
        int time = 0;        
        int stdIndex = 0;
        
        StudentQueue stdQueue = new StudentQueue();
        
        //Loop n times
        while(stdIndex < numOfStudents)
        {       
            System.out.println("Time is: " + time);
            if (time == arrivalTime)
            {
                System.out.println("STUDENT NOW BEING SERVED");
                System.out.print("Arrival Time - " + arrivalTime);
                System.out.print(" Service Time - " + serviceTime);
                System.out.println(" Completion Time - " + completionTime);
                
                newArrivalTime += (int)(generator.nextDouble() * (maxInterArrivalTime - minInterArrivalTime) + minInterArrivalTime);
                newServiceTime = (int)(generator.nextDouble() * (maxServiceTime - minServiceTime) + minServiceTime);
                newCompletionTime += newServiceTime;
                
            }
            
            if (time == newArrivalTime)
            {               
                stdQueue.addStudent(newArrivalTime, newServiceTime, newCompletionTime);
                stdIndex++;
                
                System.out.println("STUDENT ADDED TO QUEUE");
                System.out.print("Arrival Time - " + newArrivalTime);
                System.out.print(" Service Time - " + newServiceTime);
                System.out.println(" Completion Time - " + newCompletionTime);
                               
                if(stdQueue.size()>maxQueueSize)
                {
                    maxQueueSize = stdQueue.size();
                }

                newArrivalTime += (int)(generator.nextDouble() * (maxInterArrivalTime - minInterArrivalTime) + minInterArrivalTime);
                newServiceTime = (int)(generator.nextDouble() * (maxServiceTime - minServiceTime) + minServiceTime);
                newCompletionTime += newServiceTime;
            }   

            if(time == completionTime)            
            {
                totalTime = totalTime + completionTime - arrivalTime - serviceTime;
                
                arrivalTime = stdQueue.getStudent().getArrivalTime();
                serviceTime = stdQueue.getStudent().getServiceTime();
                completionTime = stdQueue.getStudent().getCompletionTime();
                
                System.out.println("STUDENT NOW BEING SERVED");
                System.out.print("Arrival Time - " + arrivalTime);
                System.out.print(" Service Time - " + serviceTime);
                System.out.println(" Completion Time - " + completionTime);
                                
                stdQueue.removeStudent();
            }
            time++;
        }
        //Output stats (max queue size, average wait time)
        System.out.println("Max queue size is: " + maxQueueSize);
        System.out.println("Average wait time is: " + (totalTime/maxQueueSize));
    }
}
