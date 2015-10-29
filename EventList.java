//
// EVENTLIST.JAVA
// Skeleton code for your EventList collection type.
//
import java.util.*;

class EventList {
    
    Random randseq;
    int max=Integer.MAX_VALUE;
    int min=Integer.MIN_VALUE;
    Event[] head=new Event[1];
    
    Event[] tail=new Event[1];
    ////////////////////////////////////////////////////////////////////
    // Here's a suitable geometric random number generator for choosing
    // pillar heights.  We use Java's ability to generate random booleans
    // to simulate coin flips.
    ////////////////////////////////////////////////////////////////////
    
    int randomHeight()
    {
	int v = 1;
	while (randseq.nextBoolean()) { v++; }
	return v;
    }

    
    //
    // Constructor
    //
    public EventList()
    {
	randseq = new Random(58243); // You may seed the PRNG however you like.

    	head[0]=new Event(min,null);
    	head[0].height=1;
    	head[0].next=tail;
    	tail[0]=new Event(max,null);
    	tail[0].height=1;
 
    
    }

    
    //
    // Add an Event to the list.
    //
    public void insert(Event e)
    {
	    int t=randomHeight();
	    int eyear=e.year;
	    String edes=e.description;
	    Event[] pillar=new Event[t];
	    for(int i=0;i<t;i++)
	    {
	    	pillar[i]=new Event(eyear,edes);
	    	pillar[i].height=t;
	    }
	    if(t>head[0].height)
	    {
	    	int h=head[0].height;
	    	int newh=h*2;
	    	Event[] temhead=head;
	    	Event[] temtail=tail;
	    	head=new Event[newh];
	    	tail=new Event[newh];
	    	for(int p=0;p<newh;p++)
	    	{
	    		if(p<h)
	    		{
	    			head[p]=new Event(temhead[p].year,temhead[p].description);
	    		    head[p].height=newh;
	    		    head[p].next=temhead[p].next;
	    		    tail[p]=new Event(temtail[p].year,temtail[p].description);
	    		    tail[p].height=newh;

	    		}
	    		else
	    		{
	    			head[p]=new Event(min,null);
	    	    	head[p].height=newh;
	    	    	head[p].next=tail;
	    	    	tail[p]=new Event(max,null);
	    	    	tail[p].height=newh;

	    			
	    		}
	    	}

	    }
	    //System.out.println(pillar[0].height+"gaodu");
	    int l=head[0].height-1;
	    Event[] x=head;
	    while(l>=0)
	    {

	    	Event[] y=x[l].next;
	    	
	    	//System.out.println(y[l].year);
	    	

	    	if(y[0].year<e.year)
	    	{

	    		x=y;
	    	}
	    	else
	    	{
	    		if(l<=pillar[0].height-1)
	    		{   

	    			pillar[l].next=y;
	    			


	    			x[l].next=pillar;
	    		}
	    		l--;
	    	}
	    }
    }

    
    //
    // Remove all Events in the list with the specified year.
    //
    public void remove(int year)
    {

	int l=head[0].height-1;
	Event[] x=head;
	while(l>=0)
	{
		Event[] y=x[l].next;
		if(y[l].year==year)
		{
			Event[] node=y[l].next;
			x[l].next=node;


		}
		else if(y[l].year<year)
		{
			x=y;
		}
		else
		{
			l--;
		}
	}
    }
    
    //
    // Find all events with greatest year <= input year
    //
    public Event [] findMostRecent(int year)
    {
	    int l=head[0].height-1;
	    Event[] x=head;
	    Event[] z=x;
	    Event[] result=new Event[1000];
	    while(l>=0)
	    {
	    	Event[] y=x[l].next;
	    	while((y[l].next!=null)&&(y[l].next[l].year==y[l].year))
	    	{
	    		y=y[l].next;
	    	}
	    	if((y[l].next!=null)&&(y[l].next[l].year<=year))
	    	{
	    		x=y;
	    	}
	    	else
	    	{
	    		if(l==0)
	    		{

	    			int i=1;
	    			Event node=x[0].next[0];
	    			result[0]=node;
                    while(node.next[0].year==node.year)
                    {
                    	node=node.next[0];
                    	result[i]=node;
                    	i++;
                    }
                    Event[] finalResult=new Event[i];
                    for(int j=0;j<=i-1;j++)
                    {
                 	   int ryear=result[j].year;
                 	   String rdes=result[j].description;
                 	   finalResult[j]=new Event(ryear,rdes);
                    }
                    return finalResult;
	    		}
	    		l--;
	    	}
	    }
	return null;
    }
    
    
    //
    // Find all Events within the specific range of years (inclusive).
    //
    public Event [] findRange(int first, int last)
    {
    	int l=head[0].height-1;
    	Event[] x=head;
    	Event[] result=new Event[1000];
    	
    	while(l>=0)
    	{
    		Event[] y=x[l].next;
    		if((y[l].year>=first)&&(y[l].year<=last))
    		{
    			if(l==0)
    			{
    			     result[0]=y[0];
                     int i=1;
                     Event[] node=y[0].next;
                     while(node[0].year<=last)
                     {
            	         result[i]=node[0];
            	         i++;
            	         node=node[0].next;
                     }

                     Event[] finalResult=new Event[i];
                     for(int j=0;j<=i-1;j++)
                     {
            	         int ryear=result[j].year;
            	         String rdes=result[j].description;
            	         finalResult[j]=new Event(ryear,rdes);
                     }
                     return finalResult;
    			}
                l--;

    		}
    		else if(y[l].year<first)
    		{
    			x=y;
    		}
    		else
    		{
    			l--;
    		}
    	}
	return null;
    }
}
