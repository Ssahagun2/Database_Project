import java.sql.*;
import java.util.*;
import java.util.Scanner;
public class Pokedex
{
   public static void main(String[] args)
   {
      Scanner kb = new Scanner(System.in);
      String[] tableNames ={ "Pokemon", "Poke_description", "disadvantages", "evolutions", "moveset"};
      String url = "jdbc:mysql://cs.neiu.edu:3306/cs315sum19_sdsahagu?serverTimezone=UTC&user=sdsahagu&password=Pokemon32!";
      try
      {
         
         Connection conn = DriverManager.getConnection(url);
         Statement stat = conn.createStatement();
         String input = "";
         int t;
         boolean done = false; 
         String m = "";
         while(done == false)
         {
            System.out.print("Would you like to insert or view? Type insert or view: ");
            m = kb.next();
            m = m.toUpperCase();
            if(m.equals("VIEW")){
               printTableNames();
               t = kb.nextInt();
               m = view(t, tableNames, m);
               done = true;
               ResultSet rs = stat.executeQuery(m);
               if(t == 1){
                  while(rs.next()){
                     int nat = rs.getInt("nationalNum");
                     int reg = rs.getInt("regionalNum");
                     String name = rs.getString("name");
                     System.out.println(nat + " " + reg + " " + name);
                  }
               } else if(t == 2) { 
                  while(rs.next()){
                     int nat = rs.getInt("nationalNum");
                     int reg = rs.getInt("regionalNum");
                     String desc = rs.getString("description");
                     String t1 = rs.getString("typing1");
                     String t2 = rs.getString("typing2");
                     double h = rs.getDouble("height");
                     double w = rs.getDouble("weight");
                     System.out.println(nat + " " + reg + " " + desc + " " + t1 + " " + t2 + " " + h + " " + w);
                  }
               } else if(t == 3) {
                  while(rs.next())
                  {
                     String n = rs.getString("name");
                     String d1 = rs.getString("typeDis1");
                     String d2 = rs.getString("typeDis2");
                     String d3 = rs.getString("typeDis3");
                     String d4 = rs.getString("typeDis4");
                     String d5 = rs.getString("typeDis5");
                     String d6 = rs.getString("typeDis6");
                     String d7 = rs.getString("typeDis7");
                     int nat = rs.getInt("nationalNum");
                  }
               } else if(t == 4) {
                  while(rs.next())
                  {
                     int nat = rs.getInt("nationalNum");
                     int ev = rs.getInt("evoCount");
                     String ev1 = rs.getString("evo1");
                     String ev2 = rs.getString("evo2");
                     String ev3 = rs.getString("evo3");
                     String ev4 = rs.getString("evo4");
                     String ev5 = rs.getString("evo5");
                     String ev6 = rs.getString("evo6");
                     String ev7 = rs.getString("evo7");
                  }
               } else {
                  while(rs.next())
                  {
                     int nat = rs.getInt("nationalNum");
                     int reg = rs.getInt("regionalNum");
                     String m1 = rs.getString("move1");
                     String m2 = rs.getString("move2");
                     String m3 = rs.getString("move3");
                     String m4 = rs.getString("move4");
                  }
               }               
            }
            else if(m.equals("INSERT")){
               printTableNames();
               t = kb.nextInt();
               m = "INSERT ";
               m += insert(t,tableNames,m);
               stat.executeUpdate(m);
               done = true;
            }
            else{System.out.println("That isn't an option!");}
         }            
      } 
      catch(SQLException sqlx)   {sqlx.printStackTrace();}
      catch(InputMismatchException ime)   {System.out.println("ENTER NUMBERS ONLY");}

   }
   public static void printTableNames()
   {
      System.out.print("Enter table number:\n1 - Pokemon Table\n2 - Pokemon Description\n3 Possible Disadvantages\n4 Possible Evolutions\n5 Pokemon's moveset\nEnter choice: ");
   }
   public static String insert(int a, String[] arr, String dec)
   {
      String funcStr = "INSERT INTO ";
      Scanner kb = new Scanner(System.in);
      boolean done = false;
      while(!done)
      {
      
         if(a == 1)
         {
            funcStr += arr[a-1] + " " + "(nationalNum, " + "regionalNum, " + "name) VALUES" + " (";
            System.out.print("Enter National Pokedex number: ");
            funcStr+=kb.next() + ",";
            System.out.print("Enter Pokemon's Home Regional Number: ");
            funcStr+=kb.next() + ",";
            System.out.print("Enter Pokemon's name: ");
            funcStr+=kb.next() + ")";
            done = true;
         }
         if(a == 2)
         {
            funcStr += arr[a-1]+ " " + "(nationalNum, " + "regionalNum, " + "description, " + "typing1, " + "typing2, " + "height, " + "weight) VALUES" + " (";
            System.out.print("Enter National Pokedex number: ");
            funcStr+=kb.next() + ",";
            System.out.print("Enter Pokemon's Home Regional Number: ");
            funcStr+=kb.next() + ",";
            System.out.print("Enter Pokemon's description: ");
            funcStr+=kb.next() + ",";
            System.out.print("Enter Pokemon's first typing: ");
            funcStr += kb.next() + ",";
            System.out.print("If Pokemon has a second typing enter it here (If it does not have a second typing, type 'None'");
            funcStr += kb.next() + ",";
            System.out.print("Enter Pokemon's height ");
            funcStr+= kb.next() + ",";
            System.out.print("Enter Pokemon's weight ");
            funcStr+=kb.next() + ")";
            done = true;   
         }
         else if (a == 3)
         {
            funcStr += arr[a-1]+ " " + "(name, " + "typeDis1, " + "typeDis2, " + "typeDis3, " + "typeDis4, " + "typeDis5, " + "typeDis6, " + "typeDis7) VALUES" + " (";
            System.out.print("Enter Pokemon name: ");
            funcStr += kb.next() + ",";
            System.out.print("Enter up to 7 Disadvantages seperated by commas: ");
            funcStr += kb.next() + ")";
            done = true;
         }
         else if(a == 4)
         {
            funcStr += arr[a-1]+ " " + "(nationalNum, " + "evoCount, " + "evo1, " + "evo2, " + "evo3, " + "evo4, " + "evo5, " + "evo6, " + "evo7) VALUES" + " (";
            System.out.print("Enter Pokemon's National Number: ");
            funcStr += kb.next() + ",";
            System.out.print("Enter total number of evolutions: ");
            funcStr += kb.next() + ",";
            System.out.print("Enter names of evolutions (Up to 7 and seperated by commas): ");
            funcStr += kb.next() + ")";
            done = true;               
         }  
         else if(a == 5)
         {
            funcStr += arr[a-1]+ " " + "(nationalNum, " + "regionalNum, " + "move1, " + "move2, " + "move3, " + "move4) VALUES" + " (";
            System.out.print("Enter national number: ");
            funcStr += kb.next() + ",";
            System.out.print("Enter regional number: ");
            funcStr += kb.next() + ",";
            System.out.print("Enter moveset seperated by commas: ");
            funcStr += kb.next() + ")";
            done = true;
         }
      }
      return funcStr;
   }
   public static String view(int a, String[] arr, String dec)
   {
      boolean done = false;
      Scanner kb = new Scanner(System.in);
      String funcStr = "SELECT * FROM ";
      if(a == 1)
         funcStr += arr[a-1];
      else if(a == 2)
         funcStr += arr[a-1];
      else if(a == 3)
         funcStr += arr[a-1];
      else if(a == 4)
         funcStr += arr[a-1];
      else if(a == 5)
         funcStr += arr[a-1];
      return funcStr;
   }
}      
   
