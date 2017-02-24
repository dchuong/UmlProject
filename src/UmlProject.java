import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;

public class UmlProject {
	public static void main(String[] args) throws FileNotFoundException {
        String Input = null, Output = null;
        if (args.length == 2) {
        	try {
        		Input = args[0];
        		Output = args[1];
        	} catch (Exception e) {
        		return;
        	}
        }
        
        File[] files = new File(Input).listFiles();
        List<Object> bundle = new ArrayList<Object>();
        for (File oneFile : files) {
        	if (oneFile.getName().contains(".java")) {
				CompilationUnit comUnit = JavaParser.parse(oneFile);
				VisitClass vc = new VisitClass();
				vc.visit(comUnit, null);
				
				for (VariableObj var: vc.allVar) {
					System.out.println(var.mod+ " " + var.name +" " +  var.type);
				}
				String interCode = parser(vc);
				//System.out.println(interCode);
				//System.out.println(comUnit.toString() + "\n\n");
					
        	}
        }
        
        
    }
	
	public static String parser(VisitClass vc) {
		String fullText = "";
		if (vc.checkInterface)
			fullText += "Interface " + vc.className;
		else
			fullText += "class " + vc.className; 
		
		return fullText;
	}
 }
