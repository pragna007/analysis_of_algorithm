import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;


// class to implement QUEUE PARSER
public class QueueHTMLParser
{
//main function containing implementation 
   public static void main(String [] args) throws IOException, InterruptedException
   {
   // getting local time in nano seconds
	   long start = System.nanoTime();

	   int i =1;
	   
	   // creating QUEUE to parse HTML
		Queue<String> QUEUE = new LinkedList<>();

// buffere reader to read data from hTML
	   BufferedReader inreader = new BufferedReader(new FileReader("Mockp.html"));
       String data;
       // combining buffer streams to analyze file data
       StringBuilder string = new StringBuilder();

       while ((data = inreader.readLine()) != null) {
           string.append(data);
       }
		ArrayList<String> QUEUEHelper = new ArrayList<String>();

		String filecontent = string.toString();
	// checkking file with the pattern required to get tags separated
       Pattern pattern = Pattern.compile("<(.*?)>");
       
       Matcher matcher = pattern.matcher(filecontent);
       
       int b=0;
       // if content in the file with contains the patterns
       while (matcher.find()) {
           String topictoken = matcher.group(1);
           // getting tags with its values // separating tag from whole line
           StringTokenizer Tokenizer = new StringTokenizer(topictoken, " ");
           
           while (Tokenizer.hasMoreTokens())
           
           {	String firstcap=""; 

           firstcap = firstcap +Tokenizer.nextToken();

		// checking if file contains these tags mentioned below        	
           if (firstcap.equals("!doctype")|| firstcap.equals("!DOCTYPE") ||firstcap.equals("meta") || firstcap.equals("!--") )        	   {
               
        		   System.out.println("\nVALID SKIPPING");
        	   }
        	   else {


        		   System.out.println("\nNew tag to consider : ("+firstcap+")");
			// if these tags occurs // skipping these tags as well as they dont have the ending tags
               if (firstcap.equals("source") || firstcap.equals("br") || firstcap.equals("link") ||firstcap.equals("img") )
        	   {	
        		   break;        		   
        	   }
 
 // adding first element in the queue
 // applying QUEUE parser mechanism to get the results 
               if (b==0)
               {
               
               QUEUE.add(firstcap);
               
               QUEUEHelper.add(firstcap);
               b=b+1;
               }
               else
               
               {
               // if tag is not first one to add in queue  	
                  String QElement = QUEUE.element();
            	  
                  String value = QUEUEHelper.get(b-1);
               		
                  String CheckValue = "/"+value;
// checking the vaue to compare with current tags 
               System.out.println("\nComparing "+firstcap+"---"+CheckValue);

            	   if (firstcap.equals(CheckValue))
            	   {
            	   // if condition met to remove tag from QUEUE
                       System.out.println("\nTag Removed : <"+value +">---<"+CheckValue+">");
                       QUEUE.remove();
                       
                       QUEUEHelper.remove(b-1);
                       b=b-1;
            	   }
            	   else
            	   {
            	   // if condition didnt met to remove tags
            	   // Adding required tags in the QUEUe
                       QUEUE.add(firstcap);
                       
                       QUEUEHelper.add(firstcap);
                       
                       QUEUE.add(value);
                       
                       b=b+1;
                       
                       System.out.println("\nTag Added | New QUEUE Size : "+(b-1));     
            	   }
               }
               }
               break;
           }
           
       }
       System.out.println("QUEUE After Complete Checking : "+ QUEUEHelper);
       if (b==0)
       {       
    	   System.out.println("\nAll HTML TAGS in File Is Closed Accurately");
       }
       else
       {
    	   System.out.println("\nFile TAGS are not accurately closed");
       }
       	
       	//results of the Above analysis is mentioned above	
       

       
       inreader.close();
       long end = System.nanoTime();
       long execution = end - start;
       // displaying the execution time calculated above 
       System.out.println("Execution time: " + execution + " nanoseconds");
   }
   
}
