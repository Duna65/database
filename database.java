//Software Design 2, Made by Dallin Lyman
//takes 5 sets data and assigns it to 5 fields and then reads it out of the UserData file.
package sd2;
import java.io.File;//imports
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class database {
    public static void main(String[] args) {
        try {
      File File = new File("UserData.txt");//creates the file
      if (File.createNewFile()) {//tests if there is already a file
        System.out.println("File created: " + File.getName());
      } else {
        System.out.println("already have a file! :)");//if you have a file already
      }
        } catch (IOException e) {
        System.out.println("An error occurred.");//error message
        e.printStackTrace();
    }
    Scanner test = new Scanner(System.in);//asks you if you want to use the previous data set
    System.out.println("Use previous previous information? \"y\" for yes, and \"n\" for no (Don't use on first use)");
    String yesorno = test.nextLine();
if(true==yesorno.contains("n")){//tests if you entered yes or no, and if no, it writes to UserData
    try {
      FileWriter Write = new FileWriter("UserData.txt"); //creates a writer object
      BufferedWriter write = new BufferedWriter(Write); //buffered writer
      Scanner in = new Scanner(System.in);//user input
      for(int i=1;i<=5;i++){
        System.out.println("put info for person " + i);//puts in data for 5 lines
        if (i!=1){//skips a line cuz a bug happens if I don't
        in.nextLine();
    }
        System.out.println("First, put in your first AND last name");
        String a = in.nextLine();
        System.out.println("Second, put in your age");
        float b = in.nextFloat();
        System.out.println("Third, tell me if you have a driver's liscence \"y\" for yes, and \"n\" for no ");
        String c = in.next();
        System.out.println("Fourth, put in your zip code");
        int d = in.nextInt();
        System.out.println("Last, put in your lucky number");
        int e = in.nextInt();
        String[] name = {};//splits your names on any of the characters
        if (a.contains(";")==true) {
            name = a.split(";");//splits the user's name at the character that it uses
        } else if(a.contains("-")==true){
            name = a.split("-");
        } else if(a.contains("_")==true){
            name = a.split("_");
        } else if(a.contains(" ")==true){
            name = a.split(" ");
        } else if(a.contains(",")==true){
            name = a.split(",");
        } else if(a.contains(".")==true){
            name = a.split(".");
        }
        try {
            if (name[1].isEmpty() == false) {//writes the split first and last names
                String firstn = name[0];
                String lastn = name[1]; 
                write.write("\""+ firstn + "\"" + " " + "\"" + lastn + "\"" + " ");//writes the input to the file
        }
        } catch (Exception ex) {
            System.out.println("Please put in your name right!");//if the name is wrong
        }
      write.write(b + " ");//writes the data, and some of it has markers so it doesn't get confused
      write.write("\""+"*"+c + "\" ");
      write.write(d + " ");
      write.write("\"" + "\\" + e + " " + "\"");
      write.newLine();
      name[0]="";
      name[1]="";//clears name for the next input
    }
    in.close();  
      write.close();
      test.close();
        } catch (IOException e) {
      System.out.println("An error! :O");//bad message
      e.printStackTrace();
    }
}
    int ynum = 0; // #'s of yesses and no's
    int nnum = 0;
    float tage = 0;//total ages added together
    int ages = 0;//amount of ages
    float aage = 0;//average age
    String[] favnum = {"e","e","l","l","i"};//array for the favorite numbers
    int fni = 0;//index for the favorite numbers
    System.out.println("List of zip codes:");
         try {
      File File0 = new File("UserData.txt");//new file read
      Scanner Read = new Scanner(File0);//new scanner
      while (Read.hasNextLine()) {//reads the next file line
        String data = Read.nextLine();
        StringTokenizer st = new StringTokenizer(data," ");//separates the data 
      while (st.hasMoreTokens()) {//reads the tokens and prints them
            String tokn = st.nextToken().replace(",","");//makes the next token a variable, and removes commas
          if(false==tokn.contains("\"")){//tests if the words have quotes
            float ci = Float.parseFloat(tokn);//changes the variable into a float and names it "ci"
            if(ci>=200){
                System.out.println(ci);//prints zip codes
              }
            if (ci<=200) {//ages, counts up total and divides it by the number of entries
              tage+=ci;
              ++ages;
              }
          }
            if(true==tokn.contains("*")){//counts the number of yes's and no's
                if(tokn.contains("y")){
                    ++ynum; 
                }else if (tokn.contains("n")){
                    ++nnum;
                }else{
                  System.out.println("put in your yes's and no's right!!!");//if there's anything other than a y or n
                }
            }
            if(true==tokn.contains("\\")){//assigns the token to an array
              favnum[fni]=tokn;
              ++fni;
          }
        }
      }
        Read.close();
        aage = tage / ages;//averages the ages
        int numofmatch = 0;//number of matches with the array
        int lastmatch = 0;//comparison of matches
        String common = "";//most common variable
        for (int i = 0;i<favnum.length;i++){//finds the most common string by comparing the current string with the rest of the array. Then it compares the amount of matches with the highest matched string
          numofmatch = 0;
          for(int n = 0;n<favnum.length;n++){
            if(n==i){
              continue;
            }
            if (true==favnum[i].matches(favnum[n])){
              numofmatch++;
            }
          }
          if(numofmatch>=lastmatch){
            common=favnum[i];
            lastmatch = numofmatch;
          }
        }
        common=common.replace("\"", "");//replaces the markers on the string
        common=common.replace("\\", "");
        System.out.println("Most common favorite number: " + common);//prints the results
        System.out.println("Average age: " + aage);
        System.out.println("Number of driver's liscences:    Yes: " + ynum +"    No: " + nnum);
        }catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          } 
    }
}
