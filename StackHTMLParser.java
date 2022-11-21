import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;


public class StackHTMLParser
{
   public static void main(String [] args) throws IOException, InterruptedException
   {
	   long start = System.nanoTime();


	   int i =1;
		Stack<String> stack = new Stack<String>();

	   BufferedReader inreader = new BufferedReader(new FileReader("Mockp.html"));
       String data;
       StringBuilder string = new StringBuilder();
       while ((data = inreader.readLine()) != null) {
           string.append(data);
       }
       String filecontent = string.toString();
       Pattern pattern = Pattern.compile("<(.*?)>");
       Matcher matcher = pattern.matcher(filecontent);
       int b=0;
       while (matcher.find()) {
           String topictoken = matcher.group(1);
           b=b+1;
           
           StringTokenizer Tokenizer = new StringTokenizer(topictoken, " ");
           
           while (Tokenizer.hasMoreTokens())
           
           {	String firstcap=""; 
        	   firstcap = firstcap +Tokenizer.nextToken();
        	   if (firstcap.equals("!doctype")|| firstcap.equals("!DOCTYPE") ||firstcap.equals("meta") || firstcap.equals("!--") )
        	   {
                   System.out.println("\nVALID SKIPPING");
        	   }
        	   else {
        	   

               System.out.println("\nNew tag to consider : ("+firstcap+")");
        	   if (firstcap.equals("source") || firstcap.equals("br") || firstcap.equals("link") ||firstcap.equals("img") )
        	   {	
        		   break;        		   
        	   }
               if (i==1)
               {
            	   stack.push(firstcap);
                   System.out.println("\nAdded | Stack Size : "+stack.size());
            	   i=i+1;
               }
               else {
            	   String value = stack.pop();
            	   String CheckValue = "/"+value;

            	   if (firstcap.equals(CheckValue))
            	   {
                       System.out.println("\nTag Removed : <"+value +">---<"+CheckValue+">");
                       System.out.println("Removed | Stack Size : "+stack.size());
            	   }
            	   else
            	   {
            		   stack.push(value);
            		   stack.push(firstcap); 
                       System.out.println("\nNew Added Stack Size : "+stack.size());
                       System.out.println("Stack After Addtions : "+ stack);
            	   }  
               }               
        	   }
               break;
           }
       }
       System.out.println("\nStack After Addtions : "+ stack);

       if (stack.size()==0)
       {       
    	   System.out.println("\nAll HTML TAGS in File Is Closed Accurately");
       }
       else
       {
    	   System.out.println("\nFile TAGS are not accurately closed");
       }
       
       inreader.close();
       long end = System.nanoTime();
       long execution = end - start;
       System.out.println("Execution time: " + execution + " nanoseconds");
   }
   
}