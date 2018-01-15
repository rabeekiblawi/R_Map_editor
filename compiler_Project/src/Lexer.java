import java.util.ArrayList;
import java.util.regex.*;

public class Lexer {
			
	String input;
	String output = "";
	String identifierRe="[\\s|][A-Za-z][A-Za-z0-9]*";//^ means first of file or nothing before
	
	String nonIdentifierNonNumericRe="(([\\s|^])[0-9]+)([a-z]+[0-9a-z]*)([\\s]?)";
	String srcCoordinatesRe="([\\s][0-9]+\\s+[0-9]+\\s+[0-9]+\\s+[0-9]+\\s*)";
	String[] lang = {" get ","paint"};
	ArrayList<String> tokens = new ArrayList<String>();
	ArrayList<ArrayList<String>> table;
	ArrayList<String> validTokens;
	//=======================================================================
	public Lexer(String i) {
		input=i;
		createTokens();
		createTable(tokens);
	}	
//=========================================================================
	
//==========================================================================
	public void checkIfIdentfier() {
		String err="";
		String accaptedState="",errorState="";
		String newVar="";
		ArrayList<String> errors=new ArrayList<String>();
		//if(input.startsWith("start:")){
			accaptedState+=" "+check(input,identifierRe);
			errors.add(" "+check(input,nonIdentifierNonNumericRe));

			System.out.print("\naccepted:"+accaptedState+"\nerrors:");
			for(String s : errors){
				err+=errorState+=" "+s;
				err+=" "+findWord(s);
			}
			System.out.print(err);
		/*	}else{
				output+="\nError : can't find start";
			}*/
		
		}
	//===========================================================================
	public void checkIfValidNumeric(){
		String accaptedState="";
		accaptedState+=" "+check(input,srcCoordinatesRe);
		System.out.print("\naccepted:"+accaptedState);
		
	}
	
	
	
	//===========================================================================
	public String check(String input,String regexp ){
		String out="";
		Pattern pattern=Pattern.compile(regexp);
		Matcher matcher=pattern.matcher(input);
		
		while(matcher.find()){
			
			if(matcher.group().length() !=0){
				out+=" "+matcher.group();
					
			}
			
		}
	return out;	
	}
	/**************************** CREATING*TABLE*OF*TOKENS **********************************************************/
	// =======================================================================
	public ArrayList<String> createTokens() {
		char[] file = input.toCharArray();//chunk it into chars 
		boolean sp =false;//found space flag
		String temp = "";//temporarly variable for storing meaningfull tokens
		for (char c : file) {//let c the first char in the array then equals the second 
		/*********for************/
			if (Character.isLetterOrDigit(c)) {//if c is letter or digit 
				temp += c;//add it to the temp var 
				sp = false;//a letter is not a space ..

			}	else if (((c == ' '  && sp == false)|| c=='.')) {// we found a space or . or : 
				sp = true; //trigger the space flag 
				if (!temp.isEmpty())
					tokens.add(temp);//add token to token array
				if (c == '.') {
					tokens.add(".");
				}
				System.out.print(temp);
				temp = "";
			}
		}/*********end for********/

		/*
		 * for( String a: tokens){ System.out.print("<TOKEN :"+a+">"); }
		 */
		System.out.print(tokens.toString());
		return tokens;

	}

	//============================================================================
	public ArrayList<ArrayList<String>> createTable(ArrayList<String> tokens) {
		ArrayList<String> sentence = new ArrayList<String>();
		ArrayList<ArrayList<String>> program = new ArrayList<ArrayList<String>>();
		for (String s : tokens) {
			sentence.add(s);//3am btekbar el sentence ...awal mara kanet start
System.out.print(s);

			if (s == ".") {
				program.add((ArrayList<String>) sentence.clone());												// sentence
				sentence.clear();
			}
		}
		// output=program.toString();
		table=program;
		return program;
	}
	//=============================================================================
	public void printTokenAt(int line, int word,
			ArrayList<ArrayList<String>> program) {
		System.out.print(program.get(line).get(word));
		System.out.print( "token at :" + line + " " + word + "  :"
				+ (program.get(line).get(word)));
	}
	//==============================================================================
	public String findWord(String word){
		;
		String coordinates="";
		for(int i=0;i<table.size();i++){
			
			for(String temp:table.get(i)){
				if(word.contains(temp)){
					System.out.print(temp);
					coordinates+="\nline :"+(i+1)+"word :  "+(table.get(i).indexOf(temp)+1);
					
				}else{System.out.print("");}
			}
			
		}
		
		System.out.print(coordinates);
		return coordinates;
	
	}
	
	// ===============================================================
	
	public void printTable() {
		System.out.print("\n#########TABLE################\n"); 
		for (ArrayList<String> sent : table) {

			for (String word : sent) {

				System.out.print(word + " | ");
				
			}
			System.out.println( );
		
		}
		System.out.print("#########END_OF_TABLE##########\n");
	}
	// =================================================================
	/********************************************************************************************************************/
	
}
