import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * reads integers fron file into array list and return the arraylist
 *
 * @author (Ronen Lubin)
 * @version (28/03/2020)
 */

public class Reader 
{    
    /**
     * reads integers fron file into array list and return the arraylist
     *
     * @param  filePath  the name of the file
     * @return  list of integers
     */
    public static  ArrayList<Integer> readIntegers(String filePath) 
    {
        ArrayList<Integer> Integers = new ArrayList<>();      
        try 
        {
            Scanner scanner = new Scanner(new File(filePath));
            //while there are integers  in the file
            while(scanner.hasNextInt())
            {
                 Integers.add(scanner.nextInt());
            }
            scanner.close();
        } // if file is not found
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }        
        return Integers ;
    }   
}

