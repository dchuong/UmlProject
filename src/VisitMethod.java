import java.util.EnumSet;
import java.util.List;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class VisitMethod extends VoidVisitorAdapter {
	public List<VariableDeclarator> variables;
	public EnumSet<Modifier> mod;
	/* from JavaParser voidVisitor
	   public void visit(final MethodDeclaration n, final A arg) {
	        n.getBody().ifPresent( l -> l.accept(this, arg));
	        n.getType().accept(this, arg);
	        n.getName().accept(this, arg);
	        n.getParameters().forEach( p -> p.accept(this, arg));
	        n.getThrownExceptions().forEach( p -> p.accept(this, arg));
	        n.getTypeParameters().forEach( p -> p.accept(this, arg));
	        n.getAnnotations().forEach( p -> p.accept(this, arg));
	        n.getComment().ifPresent( l -> l.accept(this, arg));
	    }
	    only include public methods - ignore private, package, protected 
	 */
	
	public void visit(MethodDeclaration n, Object obj) {
		System.out.println("method declaration");	
	}
	
	/* from JavaParser voidVisitor
	public void visit(final FieldDeclaration n, final A arg) {
        n.getVariables().forEach( p -> p.accept(this, arg));
        n.getAnnotations().forEach( p -> p.accept(this, arg));
        n.getComment().ifPresent( l -> l.accept(this, arg));
    }
    */
	public void visit(FieldDeclaration n, Object obj) {
		variables = n.getVariables();
		mod = n.getModifiers();
	
		//System.out.print(n.toString());
		for (VariableDeclarator v : variables) {
			System.out.println(v.toString() +" " +  v.getType());
		}
	}
	

}
