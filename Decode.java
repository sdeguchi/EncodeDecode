// From the UW
//
// Decode prompts the user for the name of a binary (encoded) input file and a
// code file name and produces as output a text file containing the original
// file.  Assumes that the binary input file was creaed by Encode.

import java.io.*;
import java.util.*;

public class Decode {
	public static final int CHAR_MAX = 256; // max char value to be encoded

	public static void main(String[] args) /* throws IOException */{
		System.out.println("This program decodes a file with a Huffman code.");
		System.out.println();

		Scanner console = new Scanner(System.in);
		System.out.print("encoded file name? ");
		String inFile = console.nextLine();
		System.out.print("code file name? ");
		String codeFile = console.nextLine();
		System.out.print("output file name? ");
		String outputFile = console.nextLine();
		console.close();

		// open code file and construct tree
		// need to read sequences of bits that represent encoded characters 
		//and figure out what the original characters were.  
		//Start at the top of the tree and read bits 
		//from the input stream, going left or right 
		//depending on whether the stream returns a 0 or 1. 
		//A leaf node indicates the end of an encoded sequence.  
		//When you reach a leaf node, write the integer code for that character 
		//to the output file by calling the following method 
		//from the PrintStream class: 
		
		
		try {
			Scanner codeInput = new Scanner(new File(codeFile));
			HuffmanTree t = new HuffmanTree(codeInput);
//t.prefix(t.root);
			
//			System.out.println();
//			System.out.println();
//			System.out.println();
//			System.out.println();
//			System.out.println();
//			System.out.println();
//			System.out.println();
//			System.out.println();
			
			
			// open encoded file, open output, decode
			BitInputStream input = new BitInputStream(inFile);
			PrintStream output = new PrintStream(new File(outputFile));
			t.decode(input, output, CHAR_MAX);
			output.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
