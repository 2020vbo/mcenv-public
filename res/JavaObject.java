import twitter4j.*;       //set the classpath to lib\twitter4j-core-4.0.4.jar
import java.util.List;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.util.*;

public static class JavaObject
{
   private static PrintStream consolePrint;

   public void print(double x, double y) throws Exception
   {
      consolePrint = System.out; // this preserves the standard output so we can get to it later      
   
      // PART 1
      // set up classpath and properties file
          
      Scrapper bigBird = new Scrapper(consolePrint);
      bigBird.sampleInvestigate(x,y);
   }//end main         
      
}//end driver   

class Scrapper 
{
   private Twitter twitter;
   private PrintStream consolePrint;
   private List<Status> statuses;
   private List<String> terms;
   private String popularWord;
   private int frequencyMax;
  
   public Scrapper(PrintStream console)
   {
      // Makes an instance of Twitter - this is re-useable and thread safe.
      // Connects to Twitter and performs authorizations.
      twitter = TwitterFactory.getSingleton(); 
      consolePrint = console;
      statuses = new ArrayList<Status>();
      terms = new ArrayList<String>();
   }
   
   
   public void sampleInvestigate (double a, double b) throws Exception
   {
      PrintStream fileOut = new PrintStream("tweets.txt");
      System.setOut(fileOut);
      ArrayList<String> words = new ArrayList<String>();
      try{
         Scanner bob = new Scanner(new File("environWords.txt")); 
         String commonWord = "";
         while(bob.hasNextLine())
         {
            words.add(bob.nextLine());
            
         }
      }
      catch(IOException e)
      {
         System.out.println("File Not Found");
      }
      for(int y = 0; y < words.size(); y++)
      {
         Query query = new Query(words.get(y));
         //query.setCount(100);
         query.setGeoCode(new GeoLocation(a,b), 5, Query.MILES);
         query.setSince("2015-12-1");
         try 
         {
            QueryResult result = twitter.search(query);
            System.out.println("Count : " + result.getTweets().size()) ;
            for (Status tweet : result.getTweets())
            {
               System.out.println("@"+tweet.getUser().getName()+ ": " + tweet.getText()); 
               System.out.println(tweet.getCreatedAt()); 
               //System.out.print(tweet.getGeoLocation().getLongitude());
               //System.out.println(tweet.getGeoLocation().getLatitude());
            }
         } 
         catch (TwitterException e) 
         {
            e.printStackTrace();
         } 
      }
      System.out.println(); 
   }  

}  

