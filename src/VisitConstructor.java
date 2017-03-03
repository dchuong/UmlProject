import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.utils.Pair;

public class VisitConstructor extends VoidVisitorAdapter{
	public 	List <ConstructorObj> constructorList = new ArrayList<ConstructorObj>();
	public void visit (ConstructorDeclaration n, Object obj){
		ConstructorObj cObj = new ConstructorObj();
		cObj.mod = n.getModifiers();
		cObj.name = n.getName().toString();
		
		for (Parameter name: n.getParameters()) {
			//System.out.println(name.toString());
			String line = name.toString();
			int middle = name.toString().indexOf(' ');
			String type = line.substring(0, middle);
			String pname = line.substring(middle +1, line.length());
			Pair<String, String> temp = new Pair<String, String>(type, pname);
			cObj.parameterList.add(temp);
		}
		constructorList.add(cObj);
	}
}
