import java.io.*;
import java.util.Scanner;

//import LinkedList.Node;

/*
 * Programmer: Christopher Wells
 * G-00260513
 * 10/10/2018
 * 
 * 	The primary function of the class is to write the CSV file that will be used in the creation of the world.
 * The program will later be interfaced with an automated algorithm to provide input to this class.
 */



public class addRoom{

	//File IO variables
	private FileInputStream inputFile = null;
    private FileOutputStream outputFile = null;
    
    private String inputFileName = "";
    private String outputFileName = "";
    
    //Room Definition Variables
    private class Node<T>
	{
		Node NextNode;
		
	    private int roomID = 1;
	    private String roomDescription;
	    private char indorOutdoor;
	    private static final String north = "NORTH";
	    private static final String east = "EAST";
	    private static final String south = "SOUTH";
	    private static final String west = "WEST";
	    private int northConnecting = 0;
	    private String northDescription;
	    private int eastConnecting = 0;
	    private String eastDescription;
	    private int southConnecting = 0;
	    private String southDescription;
	    private int westConnecting = 0;
	    private String westDescription;
	    
		
	}
	
    private int numberOfRooms = 0;
    
    private Node head = null;
    private Node current = head;
    //Constructors
    
    addRoom()
    {
    	
    }
    
    addRoom( String infile, String outfile)
    {
    	this.defineOutputFileName(infile);
    	this.defineOutputFileName(outfile);
    	
    	this.openInputFile();
    	this.openOutputFile();
    }
    
    addRoom( String outfile) throws IOException
    {
    	
    	this.defineOutputFileName(outfile);
    	
    	
    	this.openOutputFile();
    	this.appendFromUser();
    	this.printToFile();
    }
    
    private void printToFile() throws IOException {
		// TODO Auto-generated method stub
    	String output = "";
    	output = Integer.toString(this.numberOfRooms);
    	output += '\n';
    	byte[] strToBytes = output.getBytes();
		this.outputFile.write(strToBytes);
		output = "";
		
		while ( this.head != null)
		{
			output += Integer.toString(this.head.roomID);
			output += ',';
			output += this.head.roomDescription;
			output += ',';
			output += this.head.indorOutdoor;
			output += '\n';
			strToBytes = output.getBytes();
			this.outputFile.write(strToBytes);
			output = "";
			output += this.head.north;
			output += ',';
			output += Integer.toString(this.head.northConnecting);
			output += "'";
			output += this.head.northDescription;
			output += '\n';
			strToBytes = output.getBytes();
			this.outputFile.write(strToBytes);
			
			output = "";
			output += this.head.south;
			output += "'";
			output += Integer.toString(this.head.southConnecting);
			output += "'";
			output += this.head.southDescription;
			output += '\n';
			strToBytes = output.getBytes();
			this.outputFile.write(strToBytes);
			output = "";
			output += this.head.east;
			output += "'";
			output += Integer.toString(this.head.eastConnecting);
			output += "'";
			output += this.head.eastDescription;
			output += '\n';
			strToBytes = output.getBytes();
			this.outputFile.write(strToBytes);
			output = "";
			output += this.head.west;
			output += "'";
			output += Integer.toString(this.head.westConnecting);
			output += "'";
			output += this.head.westDescription;
			output += '\n';
			strToBytes = output.getBytes();
			this.outputFile.write(strToBytes);
			head = head.NextNode;
		}
		outputFile.close();
			
	}

	//Internal methods 
    private void defineInputFileName(String name)
    {
    	this.inputFileName = name;
    }
    
    private void defineOutputFileName(String name)
    {
    	this.outputFileName = name;
    }
    
    private void openInputFile()
    {
    	try
    	{
    		inputFile = new FileInputStream(inputFileName);
    	}
    	catch(Exception e)
    	{
    		System.out.println("ERROR IN THE OPENING OF THE INPUT FILE");
    		System.exit(-1);
    	}
    }
	
    private void openOutputFile()
    {
    	try
    	{
    		outputFile = new FileOutputStream(outputFileName);
    	}
    	catch(Exception e)
    	{
    		System.out.println("ERROR IN THE OPENING OF THE INPUT FILE");
    		System.exit(-1);
    	}
    }
    
    //IO methods
    
    public void copyFromFile()
    {
    	
    }
    
    public void appendFromFile()
    {
    	
    }
    
    public void appendFromUser()
    {
    	this.getInputFromUser();
    }
    
    private void getInputFromUser() 
    {
		char contInput = 'y';
		
		char outdoor = 'o';
		int connectionNum;
		
		
		
		while ( (contInput == 'Y') || (contInput == 'y'))
		{
			Scanner scanner = new Scanner(System.in);
			this.numberOfRooms++;
			Node temp = new Node();
			System.out.println("Enter a description of the room:");
			String lineInput = scanner.nextLine();
			temp.roomDescription = lineInput;
			lineInput = "";
			temp.roomID = this.numberOfRooms;
			System.out.println("Is the room indor or outdoor i,o");
			outdoor = scanner.nextLine().charAt(0);
			
			if ( ( outdoor == 'i') || (outdoor == 'I'))
			{
				temp.indorOutdoor = 'i';
			}
			else
			{
				temp.indorOutdoor = 'o';
			}
			System.out.println("Enter a description of the north:");
			lineInput = scanner.nextLine();
			temp.northDescription = lineInput;
			lineInput = "";
			System.out.println("Enter a description of the east:");
			lineInput = scanner.nextLine();
			temp.eastDescription = lineInput;
			lineInput = "";
			System.out.println("Enter a description of the south:");
			lineInput = scanner.nextLine();
			temp.southDescription = lineInput;
			lineInput = "";
			System.out.println("Enter a description of the west:");
			lineInput = scanner.nextLine();
			temp.westDescription = lineInput;
			lineInput = "";
			System.out.println("Enter the room that connects to the north or 0 for no connection:");
			connectionNum = scanner.nextInt();
			temp.northConnecting = connectionNum;
			System.out.println("Enter the room that connects to the east or 0 for no connection:");
			connectionNum = scanner.nextInt();
			temp.eastConnecting = connectionNum;
			System.out.println("Enter the room that connects to the south or 0 for no connection:");
			connectionNum = scanner.nextInt();
			temp.southConnecting = connectionNum;
			System.out.println("Enter the room that connects to the west or 0 for no connection:");
			connectionNum = scanner.nextInt();
			temp.westConnecting = connectionNum;
			temp.NextNode = null;
			this.addNodeToList(temp);
			System.out.println("Is there another room?");
			lineInput = scanner.next();
			contInput = lineInput.charAt(0);
			scanner.reset();
		}
		
	}

	private void addNodeToList(Node temp) 
	{
		// TODO Auto-generated method stub
		if ( this.head == null)
		{
			this.head = temp;
			this.current = temp;
		}
		else
		{
			this.current.NextNode = temp;
			this.current = temp;
		}
		
	}

	public void appendFromAutomated()
    {
    	
    }
}
