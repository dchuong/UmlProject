import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.type.*;
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

	public List<ClassObj> allClassObj =  new ArrayList<ClassObj>();

	
	public void visit(ClassOrInterfaceDeclaration n, Object obj ) {
		ClassObj temp = new ClassObj(n.getName());
		temp.checkInterface = n.isInterface();
		
		VisitMethod vm = new VisitMethod();
		vm.visit(n, obj);
		temp.variables = vm.variables;
		
		allClassObj.add(temp);
		
		if (!n.isInterface()) {
			if(!n.getImplementedTypes().isEmpty()) {
				for (int i = 0; i < n.getImplementedTypes().size(); i++ ) {
					String test = n.getImplementedTypes().get(i).toString();
					System.out.println(test);
				}
			}
		}
		
		
	}
}
