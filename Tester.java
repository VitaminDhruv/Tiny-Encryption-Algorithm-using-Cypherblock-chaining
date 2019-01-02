//package Acme.Crypto;
 
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Tester {
	public static void main(String args[])
	{
		Scanner s1=new Scanner(System.in);
		Random rand = new Random();
		// We need to change this key to string if we are dealing with the string!
		
		int[] key = {15,17,20,25};		
		//instantiating a key
		CBCmode cbc = new CBCmode(key);					//instantiating a TEA class
		// Initializaing vector should be also the string as it will be use for the first block of given text
		int IV[] = {10,11};		//generating a random IV
		/*
		int[] temp= {1001,2001};
		System.out.println(temp[1]);
		int[] store=new int[2];
		store=cbc.encrypt(temp,IV);
		System.out.println(store[1]);
		
		store=cbc.decrypt(store, IV);
		System.out.println(store[1]);
		*/
		
		
		//Takindg user input string
		System.out.println("Enter your text: ");
		String text=s1.nextLine();
		
		//Converting string into char array
		char[] t=text.toCharArray();
//		char[] test2= new char[0];
//		if(test1.length%2==0)
//		{
//
//			for(int i=0;i<test1.length;i++) 
//			{
//				test1 = Arrays.copyOf(test1, i + 1);// increment by +1
//				test2[test2.length+1]=' ';
//			
//			}
//			System.out.print(test2.length);
//		}
//		//converting an char[] to Ascci int[] 
//		char[] t=new char[0];
//		
//		if(test1.length>test2.length) 
//		{
//			t=new char[test1.length];
//		}
//		else {
//			t=new char[test2.length];
//		}
		
		
		
		int[] text1=new int [t.length];
		for(int i=0;i<text1.length;i++) 
		{
			text1[i]=(int) t[i];
			//text1[i]=text1[i];
			//System.out.println(text1[i]);
		}
		
		//We need to create one int[] for temp block		
		// int is 32 bits so will pass 2 size array block
		
		int[] temp=new int[2];
		
		
		//To know when to apply IV or the previous encyrpted block
		boolean firstTime = true;		
		// Arraylist to store all the encrypted result
		// We are going  to add encrypted result into the cipher array  or block and we gonna use that cipher for next block incryption
//		ArrayList cipherlist=new ArrayList();//Creating arraylist    
//		ArrayList EncrypetedBLock=new ArrayList<>();

		
		
	//	int cipherText[] = new int[text1.length];
		int[] cipherText=new int[2];
		//boolean check = true;			//to catch where the reading from the file is stopped
		//storing the cipher text result
		int[] result=new int[text1.length];
		int[] cipher=new int[2];
		
		int x=text1.length;
		// loop !!
		// thats the main issue here
		

	/*----------------------------------Encryption----------------------------------------*/
		
		
		for(int i=0;i<x/2;i++)
		{
			temp[0]=text1[2*i];
			
			temp[1]=text1[2*i+1];
			//check=true;
		
			if(firstTime)
			{		//if true, the block is passed with IV to be encrypted by TEA algorithm
				cipher = cbc.encrypt(temp, IV);
				firstTime = false;		//set firstTime to false sense IV is only encrypted in the first block
				//System.out.print(i+"block");
				//System.out.println(cipher[0]+""+cipher[1]);
			}
			else 
			{// pass the block with the previous increpted block
				
				
				System.out.print("");
				
				cipher = cbc.encrypt(temp, cipherText);
				//System.out.println(i+" block:"+cipher[0]+""+cipher[1]);
			
			}
			
			// We need to add result cipher into cipher[] so that we can use it into another bloc encryption
			cipherText[0]=cipher[0];
			cipherText[1]=cipher[1];
			result[2*i]=cipher[0];
			result[2*i+1]=cipher[1];
			
				
		}
		
		System.out.println("Encrypted text: ");
		for(int i=0;i<result.length;i++) 
		{
			System.out.print(result[i]);
		}
		System.out.println("");
		//System.out.println(Arrays.toString(result));
		//System.out.println("Your text is converted in block of cipher text: "+Arrays.toString(result));
		
		/*----------------------------------Decryption----------------------------------------*/
		
		//for decryption we are passing each encrypted block with previous encrypted block as cipher text
		//decrypted array lenght will be same as text1 length
		int[] decrypted=new int[text1.length];
		
		int[] copyCipher = new int[2];
		firstTime = true;
		int plain[] = new int[2];
		//check = true;
		int[] temp2=new int[2];
		
		for(int i=0;i<x/2;i++) 
		{
			// we will start decrypting 1st block using IVy what we have used to encypt
			// so lets store first 2 char of cipher and store it in plain so we can cast 
			temp[0]=result[2*i];
			temp[1]=result[2*i+1];
			
			if(firstTime) 
			{
				
				//temp = (int[])EncrypetedBLock.get(i);

				plain=cbc.decrypt(temp, IV);
				firstTime=false;
				//System.out.println(i+" block is decrypted into: "+Arrays.toString(plain));
			}
			else 
			{
				//we need  to assign previous encrypted block as cipher text
				temp2[0]=result[2*i-2];
				temp2[1]=result[2*i-1];
			//	System.out.println("previous block is: "+result[0]+result[1]);
				
				plain=cbc.decrypt(temp,temp2 );
				//System.out.println(i+" th blok is descrypted into: "+Arrays.toString(plain));
			}
			
			decrypted[2*i]=plain[0];
			decrypted[2*i+1]=plain[1];
			
		
		}
		System.out.println("-----");
		
		//System.out.println("Decrypted into:"+Arrays.toString(decrypted));
		System.out.print("Decrypted into: ");
		char j=0;
		//int i=0;
		char result1[]=new char[text1.length];
		for(int i:decrypted) {
			result1[j]=(char)i;
			System.out.print(result1[j]);
			//System.out.print(j);
			//result[i]=j;
			//System.out.println(result[i]);
			j++;
		}
		
		//System.out.print(Arrays.toString(result1));
		//Now we are working with the decryption of each block
		// for decryption of one block we need one block with previous encrypted block
	//	System.out.println(Arrays.toString(result1));
	
	
	
	
	
	}
		




}
