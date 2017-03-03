import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.type.*;
import com.github.javaparser.ast.visitor.*;

public class Visitor{
	
	public static List<ClassObj> allClassObj;
	public static int classnum;
	
	public Visitor (int n ) {
		classnum = 0;
		allClassObj =  new ArrayList<ClassObj>();
	}
	
	public static class VisitClass extends  VoidVisitorAdapter {
		public void visit (ClassOrInterfaceDeclaration n, Object obj)  {
			if (n.getName() != null ) {
				ClassObj newone = new ClassObj(n.getName());
				newone.checkInterface = n.isInterface();
			
				
				VisitField vf = new VisitField();
				vf.visit(n, obj);
				newone.varList = vf.varList;
				
				newone.implementNode = n.getImplementedTypes();
				
				
				for (ClassOrInterfaceType c : n.getImplementedTypes()) {
					newone.implementList.add(c.getName().toString());
				//	System.out.println(n.getName() +" " + c.getName());
				}
			
				
				for (ClassOrInterfaceType c : n.getExtendedTypes()) {
					newone.extendList.add(c.toString());
				}
				
				VisitMethod vm = new VisitMethod();
				vm.visit(n, obj);
				newone.methodList = vm.methodList;
				//check for setter and getter
				for (MethodObj m: newone.methodList) {
		
					if (Modifier.getAccessSpecifier(m.mod).asString() == "public") {
						if (m.name.toLowerCase().contains("get")) {
							newone.getMethods.add(m.name);
						}
						if (m.name.toLowerCase().contains("set")) {
							newone.setMethods.add(m.name);
						}
					}
				}
				
				VisitConstructor vc = new VisitConstructor();
				vc.visit(n, obj);
				newone.constructorList = vc.constructorList;
		
				allClassObj.add(newone);	
			}
		}
	}
}
