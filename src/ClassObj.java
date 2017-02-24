import java.util.List;

import com.github.javaparser.ast.expr.SimpleName;

public class ClassObj {
	public SimpleName name;
	public boolean checkInterface;
	public List <VariableObj> variables = null;
	
	public ClassObj(SimpleName name) {
		this.name = name;
	}
}