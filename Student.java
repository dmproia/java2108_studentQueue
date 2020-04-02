public class Student 
{
    private int arrivalTime;
    private int serviceTime;
    private int completionTime;
    
    public Student(int arrivalTime, int serviceTime, int completionTime) 
    {
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.completionTime = completionTime;
    }

    public Student(int arrivalTime, int serviceTime) 
    {
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }
    
    public int getArrivalTime() 
    {
        return arrivalTime;
    }

    public int getServiceTime() 
    {
        return serviceTime;
    }

    public int getCompletionTime() 
    {
        return completionTime;
    }

    public void setCompletionTime(int completionTime) 
    {
        this.completionTime = completionTime;
    }

    
}
