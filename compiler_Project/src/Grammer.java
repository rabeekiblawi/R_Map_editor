import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grammer {

	Lexer lex;
	String input = "";
	String output = "";
	String newVarIdent;
	ArrayList<String> validVariables;
	String finalOutput="";
	boolean Error=false;
	//ArrayList<String>usedVariables;
	private String outputPath;
	
	public Grammer(Lexer lex,String outputPath) throws FileNotFoundException, UnsupportedEncodingException {
		this.lex = lex;
		input = lex.input;
		this.outputPath=outputPath;
		newVarIdent="get"+lex.identifierRe;
		validVariables=validVariables();
		checkForErrors();
	output+=Error;	
		if(Error==false){
			System.out.println("\nNo errors ");	
			output+="\nNo errors ";
		}
		System.out.println(validVariables);
		outPutFileGenarator();
	}
	
	//===========================================================================
		private ArrayList<String> validVariables( ){
			ArrayList<String> out=new ArrayList<String>();
			Pattern pattern=Pattern.compile(newVarIdent);
			Matcher matcher=pattern.matcher(input);
			while(matcher.find()){
				if(matcher.group().length()!=0){
			if(	!( out.toString().contains(matcher.group().replace("get ", "")))){
				out.add(matcher.group().replace("get ", ""));
				}else{
					Error=true;
					System.out.println("first if"+Error);
					output+="\nduplicate decliration found !";
				}
				}
				
				
			}//end while 
			return out;
		}
	//=============================================================================

		
	/**RULES*******************************RULES***********************RULES***************************RULES***********************************/	
	public boolean checkForErrors() {
		for (ArrayList<String> line : lex.table) {
		String s=(Arrays.toString(lex.lang));
		//===========================RULE : first token is defined in language=========================
				if (!s.contains(line.get(0))) {
					System.out.println("second if"+Error);
					Error=true;
					System.out.println("\nerror @line "+(lex.table.indexOf(line))+" : "+line.get(0)+" is not a command");
				output+=("\nerror @line "+(lex.table.indexOf(line))+" : "+line.get(0)+" is not a command");
				}//===========================RULE : a variable should be defined=========================
				else if(!validVariables.contains(line.get(1))){
					System.out.println("\n"+line.get(1)+" @"+(lex.table.indexOf(line)+1)+",2 "+" is not defined or valid variable  ");
					output+=("\n"+line.get(1)+" @"+(lex.table.indexOf(line)+1)+",2 "+" is not defined or valid variable  ");
					Error=true;
				}//===========================RULE : arguments have a regular expression  ===================
				else if((line.get(0).contains("get")&&!(line.get(2).matches("[0-9]+")&&line.get(3).matches("[0-9]+")
						&&line.get(4).matches("[0-9]+")&&line.get(5).matches("[0-9]+")&&line.get(6).matches(".")))){
					
				/*	boolean[] test={((line.get(0).contains("get"))),(line.get(2).matches("[0-9]")),(line.get(3).matches("[0-9]")
							),(line.get(4).matches("[0-9]")),(line.get(5).matches("[0-9]"))};
					System.out.println( Arrays.toString(test));*/
					Error=true;
					System.out.println("\nerror @ line "+lex.table.indexOf(line)+"	 :	invalid input for "+line.get(1));
					output+=("\nerror @ line "+lex.table.indexOf(line)+"	 :	invalid input for "+line.get(1));
					
				}else if((line.get(0).contains("paint")&&!(line.get(2).matches("[0-9]+")&&line.get(3).matches("[0-9]+")
						&&line.get(4).matches("[0-9]+")&&line.get(5).matches(".")))){
					System.out.println("\nerror @ line "+lex.table.indexOf(line)+"	 :	invalid arguments for paint command  ");
					output+=("\nerror @ line "+lex.table.indexOf(line)+"	 :	invalid arguments for paint command  ");
					
					Error=true;
				}else if (line.get(line.size()-1).contains(".")){
					System.out.println("statment should end with a .");
					
				}
				//=============================================================================
		}
		return Error;
		}		
	
	public void outPutFileGenarator() throws FileNotFoundException, UnsupportedEncodingException{
		output+=Error;
		if(!Error){
			for(String var : validVariables){
				for(ArrayList<String> line: lex.table){
					
				System.out.println(var);
				boolean A=line.contains(var);
				boolean B=line.contains("get");
				System.out.println(A+" "+B);
				if(line.contains(var)&&line.contains("get")){
					
					finalOutput+=var+" "+line.get(2)+" "+line.get(3)+" "+line.get(4)+" "+line.get(5)+" ";
				}else if(line.contains(var)&&line.contains("paint")){
					finalOutput+=line.get(2)+" "+line.get(3)+" "+line.get(4)+" ";
				}if(line==lex.table.get(lex.table.size()-1)){
					finalOutput+="\n";
				}
					
				
			}
		}
		System.out.println(finalOutput);
		PrintWriter writer = new PrintWriter(outputPath, "UTF-8");
		writer.print(finalOutput);
		
		writer.close();
	
		}
	
	}

	
	
}

