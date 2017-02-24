import java.util.List;

import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.type.ClassOrInterfaceType;

public class ClassObj {
	public SimpleName name;
	public List<ClassOrInterfaceType> extendList = null;
	public boolean checkInterface;
	public List <VariableObj> variables = null;
	public List<String> interfaceDec = null;
	
	public ClassObj(SimpleName name) {
		this.name = name;
	}
}