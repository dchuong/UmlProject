import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class VisitVarDeclarExpr extends VoidVisitorAdapter {
	public List<String> variableDecList = new ArrayList<String>();
	
	public void visit (VariableDeclarationExpr n, Object obj){
		for (VariableDeclarator var : n.getVariables()) {
			variableDecList.add(var.getType().toString());
			System.out.println(var.getType().toString());
		}
	}
}
