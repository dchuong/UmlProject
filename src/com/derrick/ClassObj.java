package com.derrick;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.TypeParameter;

public class ClassObj {
	public class Relation{
		public boolean needClass1 = false;
		public String line;
		public String class1;
		public String class2;
		public String rel1;
		public String rel2;
		
		public void setLine(String line) {
			this.line = line;
		}
		public void setClass1(String class1) {
			this.class1 = class1;
		}
		public void setClass2(String class2) {
			this.class2 = class2;
		}
		public void setRel1 (String rel1) {
			this.rel1 = rel1;
		}
		public void setRel2 (String rel2 ) {
			this.rel2 = rel2;
 		}
	}

	public SimpleName name;
	public boolean checkInterface;
	public NodeList<ClassOrInterfaceType> implementNode;
	public List <VariableObj> varList = new ArrayList<VariableObj>();
	public List<Relation> relationList = new ArrayList<Relation>();
	public List<String> implementList = new ArrayList<String>();
	public List<String> extendList = new ArrayList<String>();
	//methods
	public List<MethodObj> methodList = new ArrayList<MethodObj>();
	public List<MethodObj> realMethodList = new ArrayList<MethodObj>();
	
	//setter and getter
	public List<String> getMethods = new ArrayList<String>();
	public List<String> setMethods = new ArrayList<String>();
	//constructors
	public List<ConstructorObj> constructorList = new ArrayList<ConstructorObj>();
	
	public List<String> addMethods = new ArrayList<String>();
	public List<String> addConstructors = new ArrayList<String>();
	public List<String> addVariable = new ArrayList<String>();
	//variable declaration
	public List<String> variableDecList = new ArrayList<String>();
	
	public ClassObj(SimpleName name) {
		this.name = name;
	}
}
