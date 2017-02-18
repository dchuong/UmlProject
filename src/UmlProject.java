import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

public class UmlProject {
	public static void main(String[] args) {
        String Input = null, Output = null;
        if (args.length == 2) {
        	try {
        		Input = args[0];
        		Output = args[1];
        	} catch (Exception e) {
        		return;
        	}
        }
        
        System.out.println(Input + " " + Output + "\n");
        File[] files = new File(Input).listFiles();
        
        for (File oneFile : files) {
        	if (oneFile.getName().contains(".java")) {
            	System.out.println(oneFile.getName());
            	try {
					FileReader reader = new FileReader(oneFile);
		
			
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	
        	}
        	
        }
    }
}
