//
// EVENTLIST.JAVA
// Skeleton code for your EventList collection type.
//
import java.util.*;

class EventList {
    
    Random randseq;
    int max=Integer.MAX_VALUE;
    int min=Integer.MIN_VALUE;
    Event[] head=new Event[1000];
    
    Event[] tail=new Event[1000];
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
    for(int i=0;i<1000;i++)
    {
    	head[i]=new Event(min,null);
    	head[i].height=1000;
    	head[i].next=tail;
    }

    for(int i=0;i<1000;i++)
    {
    	tail[i]=new Event(max,null);
    	tail[i].height=1000;
    	tail[i].prev=head;
    }
    
    
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
	    //System.out.println(pillar[0].height+"gaodu");
	    int l=head[0].height-1;
	    Event[] x=head;
	    while(l>=0)
	    {

	    	Event[] y=x[l].next;
	    	
	    	//System.out.println(y[l].year);
	    	
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
	    			
	    			pillar[l].prev=x;
                    y[l].prev=pillar;
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
			node[l].prev=x;

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
	    Event[] result=new Event[1000];
	    while(l>=0)
	    {
	    	Event[] y=x[l].next;
	    	if(y[l].year<=year)
	    	{
	    		x=y;
	    	}
	    	else
	    	{
	    		if(l==0)
	    		{
	    			if(y[0].prev==head)
	    			{
	    				return null;
	    			}
	    			int i=1;
	    			Event node=y[0].prev[0];
	    			result[0]=node;
                    while(node.prev[0].year==node.year)
                    {
                    	node=node.prev[0];
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
               result[0]=y[0];
               int i=1;
               Event[] node=y[0].next;
               Event[] pre=y[0].prev;
               while(node[0].year<=last)
               {
            	   result[i]=node[0];
            	   i++;
            	   node=node[0].next;
               }
               while(pre[0].year>=first)
               {
            	   result[i]=pre[0];
            	   i++;
            	   pre=pre[0].prev;
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
