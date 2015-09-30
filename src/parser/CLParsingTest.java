package parser;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import core.Context;

public class CLParsingTest {
    public static void main(String[] args) {
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input="";
            Boolean finished = false;
            // Create parser
            BearParser parser=new BearParser(new Context());
            while(!finished){
                System.out.print("Parse>");
                input = reader.readLine(); //Read input
                if(input.equals("exit"))
                    finished = true;
                else if(!input.endsWith(";")) // Artificially add SEMICOLON token
                    input+=";";
                try{
                    parser.parse(new BearScanner(new java.io.StringReader(input))); // Try to parse                            
                }catch(Exception e){
                    System.out.println("No match");
                }
            }
        }catch (Exception e) {
                    // TODO: handle exception
        }
    }
}
