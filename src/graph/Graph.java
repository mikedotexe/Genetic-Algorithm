/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 * at least squareroot of sizeN need to return positive for evaluation (not -1)
 * no duplicates in population
 */

package graph;

import java.lang.Integer;
import java.lang.Object;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 * @author Mike Purterson
 */
public class Graph {
    private static boolean v = false; //verbose mode
    private static long beginTime;
    private static long endTime;
    private int sizeN;  // number of nodes present
    private boolean adjMatrix[][]; // keep track of edges between nodes
    private Fxrandom rand;  // random number generator
    
    /**
     * This is the public constructor for the graph class.  Size is the number
     * of vertices in the graph, cnn in the connectivity of the graph (the
     * probability of there being an edge between two arbitrary nodes); cnn must
     * be between 0.0 * 1.0 inclusive; and seed is the seed value for the
     * random number generator.  Use 0 for a random seed.
     * @param size Number of vertices in the graph.
     * @param cnn Connectivity of the graph.
     * @param seed Random number generator seed.
     */
    public Graph(int size, double cnn, long seed)
    {
        // perform error checking
        if(size <= 0)
            throw new IllegalArgumentException(
            "Graph constructor: size must be > 0.");
        if((cnn < 0.0) || (cnn > 1.0))
            throw new IllegalArgumentException(
            "Graph constructor: connectivity must be between 0.0 & 1.0.");
        if(seed < 0)
            throw new IllegalArgumentException(
            "Graph constructor: seed must be >= 0.");
        
        // assign private variables
        sizeN = size;
        rand = new Fxrandom(seed);
        // allocate space for adjacency matrix.
        adjMatrix = new boolean[sizeN][sizeN];
    
        // generate a set of edge connections
        for(int i = 0; i < sizeN; i++)
        {
            for(int j = i+1; j < sizeN; j++)
            {
                // initialize edges according to a weighted coin flip.
                // if cnn = 0.1, there will be a 10% chance of creating an edge
                // between vertices i and j.  Note that there will never be an
                // edge between a vertex and itself.
                adjMatrix[j][i] = adjMatrix[i][j] = rand.boolBernoulli(cnn);
                //testing code
                /*
                if(adjMatrix[i][j])
                {
                    System.out.print("adjMatrix[");
                    System.out.print(i);
                    System.out.print("],[");
                    System.out.print(j);
                    System.out.println("] is in the graph.");
                }
                */
            }
        }
    }
    
    /**
     * This is the public constructor for the graph class.  Size is the number
     * of vertices in the graph, cnn in the connectivity of the graph (the
     * probability of there being an edge between two arbitrary nodes); cnn must
     * be between 0.0 * 1.0 inclusive.  The random number generator uses the
     * system clock as a seed.
     * @param size Number of vertices in the graph.
     * @param cnn Connectivity of the graph.
     */
    public Graph(int size, double cnn)
    {
        this(size,cnn,0);
    }
    
    /*
     * Function: total
     * Description: given an array of boolean values, total returns the number
     * of boolean values set to 1.
     */
    
    /**
     * Given an array of boolean values, total returns the number of values
     * set to 1.
     * @param bitSet
     * @return
     */
    public int total(boolean bitSet[])
    {
        int cnt = 0;
        for(int i = 0; i < bitSet.length; i++) // used to be i < sizeN
        {
            if(bitSet[i]) cnt++;
        }
        return cnt;
    }
    
    /**
     * Returns true if there is an edge between vertices v1 & v2
     * @param v1 first vertex index
     * @param v2 second vertex index
     * @return true if there is an edge between v1 & v2, false otherwise
     */
    public boolean checkEdgePresent(int v1, int v2)
    {
        if((v1 < 0) || (v2 < 0)) // illegal vertex values
            return(false);
        if((v1 >= sizeN) || (v2 >= sizeN)) // illegal vertex values
            return(false);
        return(adjMatrix[v1][v2]);
    }
    
    /**
     * Does the same thing as checkEdgePresent, but without error checking
     * @param v1 first vertex index
     * @param v2 second vertex index
     * @return true if there is an edge between v1 & v2, false otherwise
     */
    private boolean edgePresent(int v1, int v2)
    {
        return(adjMatrix[v1][v2]);
    }
    

    /**
     * Given a boolean array of size = number of vertices in
     * the graph, print the array as a string of 1's & 0's, up to 50 per line
     * @param bitSet the set of boolean values to print
     */
    public void printBitset(boolean[] bitSet)
    {
        if(bitSet.length != sizeN)
            throw new IllegalArgumentException(
                "Graph.printBitSet: bitSet parameter != sizeN.");
    
        // print set, in groups of 50
        for(int i = 0; i < sizeN; i++)
        {
            if((i+1)%50 == 0)
            {
                if(bitSet[i])
                    System.out.println(1);
                else System.out.println(0);
            }
            else if(bitSet[i])
                System.out.print(1 + "  ");
            else System.out.print(0 + "  ");
        }
        System.out.println();
    }
    

    
    /**
     * Generates an independent set (a set of vertices such that there are no
     * edges between any two vertices in the set) using a greedy solution.
     * @return A boolean array of length = number of vertices in the graph. A
     * value is true in the array if that vertex is in the set.
     */
    public boolean[] greedySolution()
    {
        int i;
        // independent set of vertices to build; initially empty
        boolean set[] = new boolean[sizeN];
        for(i=0; i< sizeN; i++) set[i] = false;
        
        // TO DO: IMPLEMENT A GREEDY SOLUTION HERE!
        
        // return the identified set.
        return set;
    }
    
    
    /**
     * Evaluates the passed boolean set to determine whether this set is an
     * independent set of vertices in our graph.  If it is, the number of
     * vertices in the set is returned.  If it is not, then -1 is returned.
     * @param set An array of boolean values equal in length to the number of
     * vertices in the graph.
     * @return The number of vertices in the set if the set is an independent
     * set.  If it is not, -1 is returned.
     */
    public int evaluateSet(boolean[] set)
    {
        // normally, I would do some error checking here, but to save some
        // computational time, I'm going to skip it and assume that
        // set.length == sizeN;
        int setSize = 0;
        boolean independent = true;
        
        for(int i = 0; i < sizeN; i++)
        {
            if(set[i])
            {
                for(int j = i+1; j < sizeN; j++)
                {
                    if(set[j] && adjMatrix[i][j]) 
                    {
                        independent = false;
                        break;
                    }
                }
                if(!independent) break;
                setSize++;
            }
        }
        if(independent) return setSize;
        return -1;
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int n = 40;
        
        // for command-line purposes
        for (String s: args) {
            n = Integer.parseInt(s);
        }        
        
        Graph g = new Graph(n,0.15,19);
        g.beginTime = System.currentTimeMillis(); // set timer
        
        //begin debug of 10 x 10 /*
        boolean[] temp = new boolean[n];
        if (v) System.out.println("\t"+"0  1  2  3  4  5  6  7  8  9");
        boolean[][] track = new boolean[n][n];
        for (int i=0; i<n; i++){
            for (int j = 0; j < n; j++) {
                //System.out.println(i + " is " + g.adjMatrix[0][i]);
                temp[j] = g.adjMatrix[i][j];
                track[i][j] = g.adjMatrix[i][j];
            }
            if (v) System.out.print("Row " + i + ": \t");
            if (v) g.printBitset(temp);
            //System.out.println();
            
        }
        if (v) System.out.print("TOTALS:\t");
        for (int i = 0; i < n; i++) {
            if (v) System.out.print(g.total(track[i])+"  ");
        }
        if (v) System.out.println();
        //end debug of 10 x 10 */
        
        boolean[] exhaustSet = g.exhaustiveSolution();
        //exhaustSet[3] = true; test to make sure it breaks the evaluation
        int evalExhaust = g.evaluateSet(exhaustSet);
        System.out.println("Evalutation of exhaustive solution: "+evalExhaust);
        
        System.out.println("Bitset of exhaustive set :");
        g.printBitset(exhaustSet);
        
        //boolean[] gaSet = gaSolution(n);
        
        /*
        RandomIndividual test1 = new RandomIndividual(n, 0.05, 0);
        System.out.println("Bitset of random set :");
        g.printBitset(test1.bitSet);
        System.out.println("\nsheesh\n");
        int evalRandom = g.evaluateSet(test1.bitSet);
        System.out.println("Evalutation of random solution: "+evalRandom);
         */
        
        // find a greedy solution to the independent set problem for this graph
        
        System.out.println();
        
        boolean[] greedySet = g.greedySolutionMike();
        int evalGreedy = g.evaluateSet(greedySet);
        System.out.println("Evalutation of greedy solution: "+evalGreedy);
        /*
        boolean[] indSet = g.greedySolution();
        
        System.out.println("Solution:");
        g.printBitset(indSet);
        System.out.println("Solution contains " + g.total(indSet) + " vertices.");
        System.out.println("Evaluate Set returns: " + g.evaluateSet(indSet));
         */
    }
    
    public boolean[] exhaustiveSolution(){
        // Initialize
        boolean[] set = new boolean[sizeN];
        Arrays.fill(set, Boolean.FALSE);

        //Get length of how the length
        //Integer strLen = Integer.toBinaryString();
        Double step1 = Math.pow(2, sizeN);
        Integer step2 = step1.intValue();
        String step3 = Integer.toBinaryString(step2-1);
        int step4 = step3.length();
        //System.out.println("step1: "+step1);
        //System.out.println("step2: "+step2);
        //System.out.println("step3: "+step3);
        //System.out.println("the length of the bitstring should be "+step4);
        
        char[] allZerosArr = new char[step4];
        Arrays.fill(allZerosArr, '0');
        String binN = new String(allZerosArr);
        //System.out.println("ALL ZEROS: "+binN);
        //String mike0 = "19";
        StringBuilder boolBitString = new StringBuilder(binN);
        StringBuilder bbsLoop = null;
        //mike.append("19");
        //System.out.println("MIKE: "+mike);
        //int difference = boolBitString.length() - binN.length();
        //System.out.println("trying to use this number "+difference);
        //System.out.println("final thing should be short: "+mike.substring(difference));
        int max = 0; // sets the maximum of all the exahustive searches
        for (int i = 0; i < Math.pow(2, sizeN); i++) {
            //binN = Integer.toBinaryString(i);
            bbsLoop = new StringBuilder(boolBitString);
            bbsLoop.append(Integer.toBinaryString(i));
            //System.out.println("bbs is "+bbsLoop);
            //System.out.println("binary bitstring of "+i+": "+Integer.toBinaryString(i));
            String paddedBS = bbsLoop.substring(bbsLoop.length() - binN.length());
            //System.out.println(paddedBS);
            //System.out.println("binary bitstring of "+i+": "+bbsLoop.substring(bbsLoop.length() - binN.length()));
            boolean[] setinLoop = new boolean[sizeN];
            System.arraycopy(set, 0, setinLoop, 0, sizeN);
            for (int j = 0; j < sizeN; j++) { // loops through 2^sizeN
                if (paddedBS.charAt(j)=='1') setinLoop[j] = true;
            }
            //System.out.println("printing:");
            //printBitset(setinLoop);
            if (evaluateSet(setinLoop) > max) set = setinLoop;
        }
        
        System.out.println("Exhaustive solution: ");
        printBitset(set);
        
        
        return set;
    }
    
    /* Generates the total number of vertices that are independent
     * @return A boolean array of length = number of vertices in the graph. A
     * value is true in the array if that vertex is in the set.
     * 
     * For exhaustive, if indep list is empty, add vertex.
     * If total connections on row > 0, create a temp list of connections,
     * compare with indep list. If it exists in indep, keep iterating. Otherwise add to indep.
     */
    
    public static boolean[] gaSolution(int sizeN){
        boolean set[] = new boolean[sizeN];
        //for(int i=0; i< sizeN; i++) set[i] = false;
        Arrays.fill(set, Boolean.FALSE);

        HashMap<boolean[],Integer> map = new HashMap<boolean[],Integer>();
        ValueComparator bvc =  new ValueComparator(map);
        TreeMap<boolean[],Integer> sorted_map = new TreeMap(bvc);
        
        double randy = .3;
        int seed = 0;
        int popSize = 10;
        ArrayList<RandomIndividual> population = new ArrayList(popSize);
        
        RandomIndividual singleton = new RandomIndividual(sizeN); // creates all false of sizeN
        Fxrandom gaRand = new Fxrandom(seed);
        for (int i = 0; i < popSize; i++) {
            singleton = new RandomIndividual(sizeN, randy, gaRand);
            population.add(singleton);
            map.put(singleton.bitSet, singleton.total(singleton.bitSet));
        }
        
        
        //Done making population, next check to see if it's a worthy population to go with
        
        

        /*
        map.clear();
        boolean[] b1 = {true, false, false, false, false};
        boolean[] b2 = {true, true, false, false, false};
        boolean[] b3 = {true, true, true, false, false};
        boolean[] b4 = {true, true, true, true, false};
        boolean[] b5 = {true, true, true, true, true};
        boolean[] b6 = {false, false, false, true, true};
        
        //map.put(b1,99.5);
        map.put(b1,singleton.total(b1));
        map.put(b2,singleton.total(b2));
        map.put(b3,singleton.total(b3));
        map.put(b4,singleton.total(b4));
        map.put(b5,singleton.total(b5));
        map.put(b6,singleton.total(b6));
         */
         
        System.out.println("unsorted map");
        for (boolean[] key : map.keySet()) {
            System.out.print("key/value: ");
            for (boolean k : key) {
                System.out.print(k+" ");
            }
            System.out.print("=>"+map.get(key)+"\n");
        }

        sorted_map.putAll(map);

        System.out.println("SORTED map:");
        for (boolean[] key : sorted_map.keySet()) {
            System.out.print("key/value: ");
            for (boolean k : key) {
                System.out.print(k+" ");
            }
            System.out.print("=>"+map.get(key)+"\n");
        }        
        
        //POPULATE left HashMap
        int rightNum = (int) Math.ceil(sizeN/2);
        int leftNum = sizeN - rightNum;
        System.out.println("Left bitSet will contain "+leftNum+" entries.");
        System.out.println("Right bitSet will contain "+rightNum+" entries.");
        
        HashMap<boolean[],boolean[]> left = new HashMap<boolean[],boolean[]>();
        LeftRightComparator lc =  new LeftRightComparator(left);
        TreeMap<boolean[],boolean[]> left_sorted = new TreeMap(lc);
        HashMap<boolean[],boolean[]> right = new HashMap<boolean[],boolean[]>();
        LeftRightComparator rc =  new LeftRightComparator(right);
        TreeMap<boolean[],boolean[]> right_sorted = new TreeMap(rc);
        
        Iterator it = sorted_map.keySet().iterator();
        while (it.hasNext()){
            boolean[] p = (boolean[]) it.next();
            boolean[] leftHalf = new boolean[sizeN];
            Arrays.fill(leftHalf, Boolean.FALSE); //populate as all false
            for (int i = 0; i < leftNum; i++) {
                leftHalf[i] = p[i];
            }
            left.put(p, leftHalf); 
        }
        
        System.out.println("sizeN="+sizeN);
        Iterator itRight = sorted_map.keySet().iterator();
        while (itRight.hasNext()){
            boolean[] p = (boolean[]) itRight.next();
            boolean[] rightHalf = new boolean[sizeN];
            Arrays.fill(rightHalf, Boolean.FALSE);
            for (int i = 0; i < rightNum; i++) {
                rightHalf[leftNum+i] = p[leftNum+i];
                System.out.println("RIGHT ADDING i="+i+" with val="+p[leftNum+i]);
            }
            right.put(p, rightHalf);
        }
        
        left_sorted.putAll(left);
        right_sorted.putAll(right);
        
        System.out.println("LEFT hashmap:");
        for (boolean[] key : left.keySet()) {
            System.out.print("key/value: ");
            for (boolean k : key) {
                System.out.print(k+" ");
            } // VERY IMPORTANT, DO NOT DELETE BELOW //System.out.print("=>"+map.get(left.get(key))+"\n");
            System.out.print(" => ");
            for (boolean l : left.get(key)){
                System.out.print(l+" ");
            }
            System.out.print("\n");
        } 
        
        System.out.println("LEFT SORTED treemap:");
        for (boolean[] key : left_sorted.keySet()) {
            System.out.print("key/value: ");
            for (boolean k : key) {
                System.out.print(k+" ");
            } // VERY IMPORTANT, DO NOT DELETE BELOW //System.out.print("=>"+map.get(left.get(key))+"\n");
            System.out.print(" => ");
            for (boolean l : left.get(key)){
                System.out.print(l+" ");
            }
            System.out.print("\n");
        }
        
        System.out.println("RIGHT hashmap:");
        for (boolean[] key : right.keySet()) {
            System.out.print("key/value: ");
            for (boolean k : key) {
                System.out.print(k+" ");
            } // VERY IMPORTANT, DO NOT DELETE BELOW //System.out.print("=>"+map.get(left.get(key))+"\n");
            System.out.print(" => ");
            for (boolean l : right.get(key)){
                System.out.print(l+" ");
            }
            System.out.print("\n");
        }     
        
        System.out.println("RIGHT SORTED treemap:");
        for (boolean[] key : right_sorted.keySet()) {
            System.out.print("key/value: ");
            for (boolean k : key) {
                System.out.print(k+" ");
            } // VERY IMPORTANT, DO NOT DELETE BELOW //System.out.print("=>"+map.get(left.get(key))+"\n");
            System.out.print(" => ");
            for (boolean l : right.get(key)){
                System.out.print(l+" ");
            }
            System.out.print("\n");
        }        

        
        return set;
    }
    
    public boolean[] greedySolutionMike(){
        int i;
        // independent set of vertices to build; initially empty
        boolean set[] = new boolean[sizeN];
        for(i=0; i< sizeN; i++) set[i] = false;
        
        // set up indep to keep track of independent vertices
        List indep = new ArrayList<String>();        
        
        

        /*
        System.out.println("howmany in 7");
        System.out.println(columnTotal(7));
        System.out.println("howmany in 8");
        System.out.println(columnTotal(8));
        System.out.println("howmany in 9");
        System.out.println(columnTotal(9));
         */
        
        List cur = new ArrayList();
        for (int j = 0; j < sizeN; j++) { // j can be seen as columns (or entry)
            // if indep is empty add first one
            if (indep.isEmpty()) {
                if (v) System.out.println("db. initial adding j="+j);
                indep.add(j); // this is for debugging to see which vertices are chosen
                set[j] = true;
            }
            // compare to see if value is already in indep, if so iterate
            if (indep.contains(j)) continue;
            // is j connected to anything listed in indep?
            if (v) this.printIndep(indep);

            // loop through however large the indep list is
            boolean dontadd = false;
            for (int k = 0; k < indep.size(); k++) {
                // cur is List of what k connects to
                cur = touchesWhat((Integer) indep.get(k));
                if (v) System.out.println("Listing what "+indep.get(k).toString()+" contains:");
                if (v) this.printIndep(cur);
                if (v) System.out.println("");
                if (cur.contains(j)) {
                    if (v) System.out.println("found j="+j+" in CUR");
                    dontadd = true;
                    break;
                }
            }
            if (!dontadd){
                if (v) System.out.println("adding j="+j+" to indep");
                indep.add(j); // this is for debugging to see which vertices are chosen
                set[j] = true;
            }

        }
        
        System.out.println("Greedy solution:");
        //while (it.hasNext()){
        
        System.out.println("Total items claimed as independent vertices: "+indep.size());
        /* This was not being shown on large values
        for (int j = 0; j < indep.size(); j++) {
            System.out.print(indep.get(j) +" ");
        }
        System.out.println();
         * 
         */
        
        System.out.println("Size n in (n x n): "+sizeN);
        endTime = System.currentTimeMillis();
        //System.out.println("endtime "+endTime);
        //System.out.println("begintime "+beginTime);
        double elapsed = endTime - beginTime;
        elapsed = elapsed / 1000;
        System.out.println(elapsed + " seconds to complete exhaustive search.");
        //System.out.println((endTime - beginTime)/1000);
        
        return set;
    }
    
    /* Get column totals
     * col is which column (or row) to evaluate 
     */
    public int columnTotal(int col){
        boolean[] colVals = new boolean[sizeN];
        for (int j = 0; j < sizeN; j++) {
            colVals[j] = adjMatrix[col][j];
        }
        return this.total(colVals);
    }
    
    public List touchesWhat(int col) {
        List set = new ArrayList();
        for (int j = 0; j < sizeN; j++) {
            if (adjMatrix[col][j] == true){
                set.add(j);
            }
        }        
        return set;
    }
    
    public void printIndep(List indep){
        System.out.println("=============================");
        for (int j = 0; j < indep.size(); j++) {
            System.out.print(indep.get(j) +" ");
        }        
        System.out.println();
        System.out.println("=============================");
    }
        

}