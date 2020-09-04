import java.util.* ;
import java.lang.* ;
import java.io.FileNotFoundException;
/**
 * MAX_MIN HEAP driver
 *
 * @author (Ronen lubin)
 * @version (28/03/2020)
 */

public class Max_Min
{
   public static void main()
   {      
       Scanner scan = new Scanner(System.in) ;
       // reads input from given file  by using the read method;
       System.out.print("Enter the name of the file to read the numbers from :") ;
       final String FILE_PATH = scan.next();
       ArrayList<Integer> list =Reader.readIntegers(FILE_PATH);       
      
       System.out.print("Enter 'build' to build the heap and 'exit' to exit :") ;
       String read = scan.next().toLowerCase(); 
       // initilazing the heap
       Build_Heap pile ;
       
       do // builds a new heap or exit the method
       {
          if ( read.equals("exit") ) // in this case the user chose to exit
          {
              System.out.println("GOOD BYE") ; 
              return ;
          }
          else if ( read.equals("build")) 
               ;//builds the heap after the do statement due to compiling issues
          else  
          {
              System.out.println("please re-enter only 'build' or 'exit' :") ;
              read = scan.next().toLowerCase(); 
          }
       }
       while (! read.equals("exit") && ! read.equals("build"));
       pile = new Build_Heap (list) ;
       // all the different options
       System.out.println("\nEnter 'exit', 'max', 'min' ,'insert', "+
       "'delete', 'print', 'sort' , 'build' or 'help': ") ;
       
       read = scan.next().toLowerCase();        
       while (! read.equals("exit"))
       {
           switch(read) 
           {
              case "max":// extracts max from the heap
                 System.out.println("the max is: "+pile.heap_extract_Max()) ;
                 break;
              case "min":// extracts max from the heap
                 System.out.println("the min is: "+pile.heap_extract_Min()) ;
                 break;
              case "insert": // inserts value to the heap
                 System.out.println("enter the number to insert: ") ;
                 int num = scan.nextInt();
                 pile.heap_insert(num);
                 break;
              case "delete":// deletes value to the heap
                 System.out.println("enter the indx to delete: ") ;
                 int indx = scan.nextInt();
                 pile.heap_delete(indx);
                 break;
              case "print": // prints the values in the heap
                 pile.heap_print() ;
                 break;
              case "sort":// sorts the values in the heap
                 pile.heap_sort() ;
                 break;
              case "build":// builds max min heap, use it after sorting
                 pile.buildAfterSorted() ;
                 break; 
              case "help":// guideline to the user 
                 System.out.println("type min to extract the minimun value from the pile\n"+
                 "type max to extract the maximum value from the pile\n"+
                 "type insert in oreder to insert new elment to the pile\n"+
                 "type delete in oreder to delete element from the pile\n"+
                 "type print in oreder to print the pile\n"+
                 "type sort in oreder to sort the pile in increasing oreder\n"+
                 "type build in oreder to build min max heap after sorting");                
                 break; 
              default:
                 System.out.println("please enter a leagal value such as "+
                " ('min','max','insert','delete', 'print', 'sort', 'build' or 'help'\n") ;
           }
           
           System.out.println("\nEnter 'exit', 'max', 'min' ,'insert', "+
           "'delete', 'print', 'sort' , 'build' or 'help' \n") ;
           read = scan.next().toLowerCase(); 
       }
       // prints after the user chose to exit the program
       System.out.println("GOOD BYE") ; 
   }          
}