import java.util.* ;
/**
 * max-min heap with all the different functions it has
 *
 * @author (ronen lubin)
 * @version (26/03/2020)
 */
public class Build_Heap
{
    // using data structue of arraylist in oreder to implement min max heap
    private ArrayList<Integer> heap ;

    /**
     * Constructor for max min heaps from given array of integers
     */
    public Build_Heap(ArrayList<Integer> arr)
    {
        // builds heap from given array list
        heap = arr; 
        for ( int i = heap.size()/2 ; i >= 0 ; i-- ) 
        {
            // same as regular max heap
            heapify(i);
        }
    }

    /**
     * arrange node in the tree accoring to the laws of max min heap
     *
     * @param  indx the index of the node in the array 
     */
    private void heapify(int indx)
    {
        // if indx is in even depth in the tree
        if ( deepness(indx) % 2 == 0 )        
            heapMax(indx) ;        
        else   // odd depth
            heapMin(indx) ;
    }
    
    /**
     * extracts the maximum value from the heap
     *
     * @return the maximum value
     */
    public int heap_extract_Max()
    {
        if (heap.size() <= 0 ) // if the array is empty
        {
             System.out.println("stack underflow");
             return Integer.MAX_VALUE ;
        }
        else // the array is not empty
            return heap_delete(0);
     }
    
    /**
     * extracts the minimum value from the heap
     *
     * @return the minimum value
     */
    public int heap_extract_Min()
    { 
        if (heap.size() <= 0 ) // if the array is empty
        {
             System.out.println("stack underflow");
             return Integer.MIN_VALUE ;
        }
        else
        {
            if (heap.size() == 1 ) // there is only one value in the array
                 return heap_delete(0);
            // in this case the min is the second value in the array
            else if ( heap.size() == 2)
                 return heap_delete(1);
            else // array has  two or more values in it
            {    // the min is either the second or the third value
                 // due to the structure of max min heap 
                if (heap.get(1) < heap.get(2))

                   return heap_delete(1);
                else
                   return heap_delete(2);
            }
        }
    }
    
    /**
     * insert value to the heap
     *
     * @param  key the value to insert
     */
    public void heap_insert(int key)
    { 
        // adds the value to the end of the array
        heap.add(key) ;
        // in this case the heap is orginized
        if (heap.size() == 1)
            return;
            
        int key_indx = heap.size()-1 ;
        if( deepness(key_indx) % 2 == 0 ) //even depth of the tree - max heap
        {
            // if the key parent is bigger then the key it breaks the max min
            if (heap.get(parent(key_indx)) > heap.get(key_indx))
            {
                swap(key_indx,parent(key_indx)) ;
                // checks if the parent is not breaking the max min heap
                insert_min(parent(key_indx));
            }
            else
                insert_max(key_indx);           
        }
        else // odd depth
        {
            // if the key parent is smaller then the key it breaks the max min
            if (heap.get(parent(key_indx)) < heap.get(key_indx))
            {
                swap(key_indx,parent(key_indx)) ;
                // checks if the parent is not breaking the max min heap
                insert_max(parent(key_indx));
            }
            else
                insert_min(key_indx);                
        }            
    }
       
    /**
     * deletes value from the heap
     *
     * @param indx of the deleted value
     */
    public int heap_delete(int indx)
    { 
        if ( indx >= heap.size() || indx < 0)
        {
           System.out.println("index is out of bound!");
           return Integer.MAX_VALUE ;
        }
         //swaps the indx with the last value in the array
        swap ( indx, heap.size()-1) ;
        int deleted = heap.remove(heap.size()-1);
        // in oreder to not get indx out of bound eror, if heap size is now 0
        if ( indx < heap.size() )
        {
             heapify (indx) ;//fixes the heap
        }
        return deleted ;
    }
    
    /**
     * sorts the array
     */
    public void heap_sort()
    { 
        int min;
        int heap_length = heap.size() ;
        ArrayList<Integer> sorted_list = new ArrayList<>();
        //extracts the minumum each time and pushes it to new arraylist
        for ( int i = 0 ; i < heap_length ; i++ ) 
        {
             min = heap_extract_Min() ;
             sorted_list.add(min);
        }
        //attach'es the sorted arraylist to the heap
        heap = sorted_list;
    }
    
    /**
     * build the max min heap after sorting that destoyed the structure
     */
    public void buildAfterSorted()
    {
        for ( int i = heap.size()/2 ; i >= 0 ; i-- ) 
        {
            heapify(i);
        }
    }
    
    /**
     * prints all the values in the array
     */
    public void heap_print()
    { 
        for  (int i =0 ; i< heap.size() ; i++) 
        {            
            System.out.println("the "+ i +" element is : "+heap.get(i) );
        }
    }
    
    private int parent(int indx)
    {
        return (indx+1)/2 -1 ;        
    }
    
    private int leftSon(int indx)
    {
        return indx*2+1 ;        
    }
    
    private int rightSon(int indx)
    {
        return indx*2+2 ;      
    }
    
    private int rightGrandSon1(int indx)
    {
        return indx*4+5 ;       
    }
    
    private int rightGrandSon2(int indx)
    {
        return indx*4+6 ;        
    }
    
    private int leftGrandSon1(int indx)
    {
        return indx*4+3 ;       
    }
    
    private int leftGrandSon2(int indx)
    {
        return indx*4+4 ;       
    }
    

    // the depth of the node in the heap
    private int deepness(int indx)
    {
        int counter = 0;
        indx++ ;
        while (indx != 1)
        {
            counter++ ;
            indx = indx/2 ;            
        }
        return counter ;
    }
    
    //replace two values in the array list
    private void swap(int indx1, int indx2)
    {
        int temp = heap.get(indx1) ;
        heap.set(indx1, heap.get(indx2) );
        heap.set(indx2, temp);
    }
    
    //heapifes the min levels of the tree
    private void heapMin(int indx)
    {
        // checks is indx has any children
        if ( indx*2+1 < heap.size() )
        {
            //finds the smallset  child or grandchild of indx
            int smallest = find_smallest( indx );
            //checks is smallest is grandson of indx
            if ( smallest >= indx*4+3)
            {
                if ( heap.get(smallest) < heap.get(indx) )
                {
                    swap(smallest,indx) ;
                    if (heap.get(parent(smallest)) < heap.get(smallest))
                         swap(smallest,parent(smallest)) ;
                }
                
                heapMin(smallest) ;
            }
            // smallest is not grandson of indx but is one of his sons
            else if( heap.get(smallest) < heap.get(indx) )
                swap(smallest,indx) ;           
        }
    }
    
    //heapifes the max levels of the tree
    private void heapMax(int indx)
    {
        // checks is indx has any children
        if ( indx*2+1 < heap.size() )
        {
            //finds the largest child or grandchild of indx
            int largest = find_largest( indx );
            //checks is largest is grandson of indx
            if (largest >= indx*4+3 )
            {
                if ( heap.get(largest) > heap.get(indx) )
                {
                    swap(largest,indx) ;
                    if (heap.get(parent(largest)) > heap.get(largest))
                         swap(largest,parent(largest)) ;
                }
                
                heapMax(largest) ;
            }
            // largest is not grandson of indx but is one of his sons
            else if( heap.get(largest) > heap.get(indx) )
                swap(largest,indx) ;           
        }
    }
    
    // finds the smallset  child or grandchild of indx
    private int find_smallest(int indx)
    {
        int right = rightSon(indx) ;
        int leftG1 = leftGrandSon1(indx) ;
        int leftG2 = leftGrandSon2(indx) ;
        int rightG1 = rightGrandSon1(indx) ;
        int rightG2 = rightGrandSon2(indx) ;
         
        int smallest = leftSon(indx) ;
        // checks who is smallest
        if ( right < heap.size() &&  heap.get(smallest) > heap.get(right) )
                smallest = right ;
        if ( leftG1 < heap.size() &&  heap.get(smallest) > heap.get(leftG1) )
                smallest = leftG1 ;    
        if ( leftG2 < heap.size() &&  heap.get(smallest) > heap.get(leftG2) )
                smallest = leftG2 ; 
        if ( rightG1 < heap.size() &&  heap.get(smallest) > heap.get(rightG1) )
                smallest = rightG1 ; 
        if ( rightG2 < heap.size() &&  heap.get(smallest) > heap.get(rightG2) )
                smallest = rightG2 ; 
             
        return smallest;       
    }
    
    // finds the largest child or grandchild of indx
    private int find_largest(int indx)
    {
        int right = rightSon(indx) ;
        int leftG1 = leftGrandSon1(indx) ;
        int leftG2 = leftGrandSon2(indx) ;
        int rightG1 = rightGrandSon1(indx) ;
        int rightG2 = rightGrandSon2(indx) ; 
        
        int largest = leftSon(indx) ;
        // checks who is the largest
        if ( right < heap.size() &&  heap.get(largest) < heap.get(right)  )
             largest = right ;
        if ( leftG1 < heap.size() &&  heap.get(largest) < heap.get(leftG1)  )
             largest = leftG1 ;    
        if ( leftG2 < heap.size() &&  heap.get(largest) < heap.get(leftG2)  )
             largest = leftG2 ; 
        if ( rightG1 < heap.size() &&  heap.get(largest) < heap.get(rightG1)  )
             largest = rightG1 ; 
        if ( rightG2 < heap.size() &&  heap.get(largest) < heap.get(rightG2)  )
             largest = rightG2 ; 
        
        return largest;
    }   
    
    private void insert_min(int indx)
    {
        // checks if indx has grandfather and his gradnfather 
        int grandfather = parent(parent(indx)) ;
        if ( grandfather >= 0 &&  heap.get(indx) < heap.get(grandfather) )
        {
            swap(indx, grandfather);
            insert_min(grandfather);
        }
    }
    
    private void insert_max(int indx)
    {
        // checks if indx has grandfather and his gradnfather 
        int grandfather = parent(parent(indx)) ;
        if ( grandfather >= 0 &&  heap.get(indx) > heap.get(grandfather) )
        {
            swap(indx, grandfather);
            insert_max(grandfather);
        }
    }
}
