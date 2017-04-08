package com.derrick;


import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;


public class VisitField extends VoidVisitorAdapter {
	public List<VariableDeclarator> var;
	public 	List <VariableObj> varList;
	
	public VisitField() {
		varList = new ArrayList<VariableObj>();
	}

	public void visit (FieldDeclaration n, Object obj)  {
		EnumSet<Modifier> mod = n.getModifiers();

		for (VariableDeclarator v : n.getVariables()) {
			//get the info for each variable
			VariableObj temp = new VariableObj();
			temp.mod = mod;
			temp.name = v.getName().toString();
			temp.type = v.getType().toString();
			if (temp.type.contains("[]")) {
				temp.type = temp.type.replace("[]", "(*)");
			}
			if (temp.type.contains("collection")) {
				temp.display = false;
			}
			varList.add(temp);
			//System.out.println(mod+ " " + temp.name +" " +  temp.type);
		}
	}
}
