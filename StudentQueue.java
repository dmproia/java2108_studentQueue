import java.util.Queue;
import java.util.LinkedList;

public class StudentQueue 
{
    private Queue<Student> theQueue;
           
    public StudentQueue() 
    {
        theQueue = new LinkedList<Student>();
    }
    
   public boolean isEmpty()
    {
        return theQueue.isEmpty();
    }
    
    public int size()
    {
        return theQueue.size();
    }
    
    public void addStudent(int arrivalTime, int serviceTime, int completionTime)
    {
    	theQueue.add(new Student(arrivalTime, serviceTime, completionTime));
    }
    
    public Student getStudent()
    {
    	return theQueue.peek();
    }
    
    public void removeStudent()
    {
    	theQueue.remove();
    }
}
