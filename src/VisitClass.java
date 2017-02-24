import java.util.List;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.*;

/* from JavaParser
public void visit(final ClassOrInterfaceDeclaration n, final A arg) {
        n.getExtendedTypes().forEach( p -> p.accept(this, arg));
        n.getImplementedTypes().forEach( p -> p.accept(this, arg));
        n.getTypeParameters().forEach( p -> p.accept(this, arg));
        n.getMembers().forEach( p -> p.accept(this, arg));
        n.getName().accept(this, arg);
        n.getAnnotations().forEach( p -> p.accept(this, arg));
        n.getComment().ifPresent( l -> l.accept(this, arg));
    } 
 */
public class VisitClass extends VoidVisitorAdapter{

	public SimpleName className;
	public List<ClassOrInterfaceType> extendList;
	public boolean checkInterface;
	public List<VariableDeclarator> variables;
	public List <VariableObj> allVar;
	@SuppressWarnings("unchecked")
	public void visit(ClassOrInterfaceDeclaration n, Object obj ) {
		
		className = n.getName();
		extendList = n.getExtendedTypes();
		checkInterface = n.isInterface();
		
		
		// visit fields
		VisitMethod vm = new VisitMethod();
		vm.visit(n, obj);
		this.allVar = vm.getAllVar();
	
		
		
		
		
		if (!n.isInterface()) {
		
			if(!n.getImplementedTypes().isEmpty()) {
				for (int i = 0; i < n.getImplementedTypes().size(); i++ ) {
					String test = n.getImplementedTypes().get(i).toString();
					System.out.println(test);
				}
			}
		}
	}
	
	public List<VariableDeclarator> getVariables () {
		return this.variables;
	}
}
