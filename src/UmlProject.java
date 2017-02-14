import java.io.File;
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
        	System.out.println(oneFile);
        }
    }
}
