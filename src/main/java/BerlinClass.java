import com.sun.org.apache.xpath.internal.SourceTree;

import javax.print.DocFlavor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by wm86 on 16/12/2016.
 */
public class BerlinClass {

    private static  String time = null;
    public static int hourInt = 0, minInt= 0, secInt =0;
    public static String secEven = "O";
    public static String hour5[] = new String[]{"O","O","O","O"};
    public static String hour1[] = new String[]{"O","O","O","O"};
    public static String min5[] = new String[]{"O","O","O","O","O","O","O","O","O","O","O"};
    public static String min1[] = new String[]{"O","O","O","O"};

    public static void main(String[] args) {

        System.out.println("Welcome to Nik's Berlin Clock!!!");
        System.out.println("Please enter a time in the following format:");
        System.out.println("hh:mm:ss");

        time = getTime();

        String parsedTime = parser (time);
        if (parsedTime !=null){
            System.out.println(parsedTime);
        } else {
            convertToBerlinClock();
            displayBerlinClock();
        }
    }

    public static void convertToBerlinClock (){

        // set Yellow circular light
        if ((secInt % 2) == 0) {
            secEven="Y";
        }

        // set top row
        if (hourInt > 4) {
            for (int i=hourInt/5; i>0; i-- ){
                hour5[i-1]="R";
            }
        }

        // set second row
        for (int i =hourInt % 5; i>0; i--){
            hour1[i-1]= "R";
        }

        // set third row
        if (minInt > 4) {
            for (int i = minInt / 5; i>0; i--) {
                if ((i%3) ==0){
                    min5[i-1]="R";
                }
                else {
                    min5[i-1] = "Y";
                }
            }
        }

        //set forth row
        for (int i= minInt % 5; i>0; i--){
            min1[i-1]="Y";
        }

    }


    public static void displayBerlinClock() {

        System.out.println("                       * *");
        System.out.println("                     *     *");
        System.out.println("                    *   "+ secEven + "   *");
        System.out.println("                     *     *");
        System.out.println("                       * *");
        System.out.println("╔=========╗╔=========╗╔=========╗╔=========╗");
        System.out.println("║    "+hour5[0]+"    ║║    "+hour5[1]+"    ║║    "+hour5[2]+"    ║║    "+hour5[3]+"    ║");
        System.out.println("╚=========╝╚=========╝╚=========╝╚=========╝");
        System.out.println("╔=========╗╔=========╗╔=========╗╔=========╗");
        System.out.println("║    "+hour1[0]+"    ║║    "+hour1[1]+"    ║║    "+hour1[2]+"    ║║    "+hour1[3]+"    ║");
        System.out.println("╚=========╝╚=========╝╚=========╝╚=========╝");
        System.out.println("╔=╗╔=╗╔=╗╔=╗╔=╗╔=╗╔=╗╔=╗╔=╗╔=╗╔=╗");
        System.out.println("║"+min5[0]+"║║"+min5[1]+"║║"+min5[2]+"║║"+min5[3]+"║║"+min5[4]+"║║"+min5[5]+"║║"+min5[6]+"║║"+min5[7]+"║║"+min5[8]+"║║"+min5[9]+"║║"+min5[10]+"║");
        System.out.println("╚=╝╚=╝╚=╝╚=╝╚=╝╚=╝╚=╝╚=╝╚=╝╚=╝╚=╝");
        System.out.println("╔=========╗╔=========╗╔=========╗╔=========╗");
        System.out.println("║    "+min1[0]+"    ║║    "+min1[1]+"    ║║    "+min1[2]+"    ║║    "+min1[3]+"    ║");
        System.out.println("╚=========╝╚=========╝╚=========╝╚=========╝");

    }

    public  static String getTime (){
        InputStream is = null;
        BufferedReader br = null;
        String line = null;

        try {
            is = System.in;
            br = new BufferedReader(new InputStreamReader(is));
            line = br.readLine();

        }
        catch (IOException ioe) {
            System.out.println("Exception while reading input " + ioe);
        }
        finally {
            // close the streams using close method
            try {
                if (br != null) {
                    br.close();
                }
            }
            catch (IOException ioe) {
                System.out.println("Error while closing stream: " + ioe);
            }
        }

        return line;
    } // getTime()

    public static String parser (String str){

        String returnMsg = null;
        StringBuffer sb = new StringBuffer(str);
        String delim1 = ":";
        String myChar = "";
        String hourStr = "", minStr ="", secStr ="";

        int len = sb.length();
        if (len != 8) {
            return "Incorrect time entered (length is not what is expected), please try again";
        }

        hourStr = sb.substring(0,2);
        minStr = sb.substring(3,5);
        secStr = sb.substring(6,8);

        try {
            hourInt = new Integer(hourStr);
            minInt = new Integer(minStr);
            secInt = new Integer(secStr);
        } catch (NumberFormatException nfe){
            return "Please only enter numbers for hh mm ss";
        } catch (Exception ex){
            System.out.println("Caught exception when converting to Integer:" + ex.getMessage());

        }

        if ((hourInt < 0) || (hourInt >24))  {
            return "error in HH:must be between 0 and 24";
        }

        if ((minInt < 0) || (minInt > 59)){
            return "error in MM: must be between 0 and 59";
        }

        if ((secInt < 0) || (secInt > 59)){
            return "error in SS: must be between 0 and 59";
        }
        return returnMsg;
    }

}
