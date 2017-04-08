package com.derrick;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.utils.Pair;

//Only Include Public Methods
public class VisitMethod extends VoidVisitorAdapter {
	public 	List <MethodObj> methodList = new ArrayList<MethodObj>();
	
	public void visit (MethodDeclaration n, Object obj){
		MethodObj mObj = new MethodObj();
		
		mObj.returnName = n.getType().toString();
		mObj.name = n.getName().toString();
		mObj.mod = n.getModifiers();
	//	System.out.println("TEST");
		
		//System.out.println(n.getDeclarationAsString());
		for (Parameter name: n.getParameters()) {
			//System.out.println(name.toString());
			String line = name.toString();
			int middle = name.toString().indexOf(' ');
			String type = line.substring(0, middle);
			String pname = line.substring(middle +1, line.length());
			
			Pair<String, String> temp = new Pair<String, String>(type, pname);
			mObj.parameterList.add(temp);
			
		}
		
		
		methodList.add(mObj);
	}
}
