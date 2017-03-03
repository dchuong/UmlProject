import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.utils.Pair;

import net.sourceforge.plantuml.SourceStringReader;

public class UmlProject {
	
	public static List<String> tempMO = new ArrayList<String>();
	public static List<ClassObj> methodClassObj = new ArrayList<ClassObj>();
	public static void main(String[] args) throws FileNotFoundException {
		 
        String Input = null, Output = null;
        if (args.length == 2) {
        	try {
        		Input = args[0];
        		Output = args[1];
        	} catch (Exception e) {
        		return;
        	}
        }
        
        File[] files = new File(Input).listFiles();

        
        Visitor visitor = new Visitor(0);
        for (File oneFile : files) {
        	if (oneFile.getName().contains(".java")) {
				CompilationUnit comUnit = JavaParser.parse(oneFile);
				new Visitor.VisitClass().visit(comUnit, null);
        	}
        }
        //method line
        for(ClassObj c1 : visitor.allClassObj) {
            boolean methodCase = true;
    		for (MethodObj m: c1.methodList) {
    			for (Pair<String, String> p : m.parameterList) {
    				for (ClassObj c2 : visitor.allClassObj) {
	    				String className = c2.name.toString();
	    				//System.out.println(p.a + " " + c1.name.toString());
	    				if(!c1.checkInterface && className.equalsIgnoreCase(p.a) && c2.checkInterface) {
	    					//System.out.println("here");
	    					methodCase = false;
	    					if (!c2.addMethods.contains(c1.name.toString()))
	    						c2.addMethods.add(c1.name.toString());
	    				//	m.addMethodObj = false;
	    				}
        			}
    			}
    			if (methodCase) {
    				c1.realMethodList.add(m);
    			}
    		}
        }
        
        //constructor line
        for(ClassObj c1 : visitor.allClassObj) {
        	for (ConstructorObj cObj: c1.constructorList) {
    			for (Pair<String, String> p : cObj.parameterList) {
    				for (ClassObj c2: visitor.allClassObj) {
    					String className = c2.name.toString();
    					if (!c1.checkInterface && className.equals(p.a) ) {
    						if (!c2.addConstructors.contains(c1.name.toString()))
    						c2.addConstructors.add(c1.name.toString());
    					}		
    				}
    			}
        	}
        }
        
        //variable line
        for(ClassObj c1 : visitor.allClassObj) {
        	for (String varName: c1.variableDecList ) {
	    		for (ClassObj c2: visitor.allClassObj) {
	    			String className = c2.name.toString();
	    			
    			//	System.out.println("@ "+ className + " "+ varName);
    				if (!c1.checkInterface && className.equals(varName)) {
    					if (!c2.addMethods.contains(c1.name.toString())) {
    						c2.addMethods.add(c1.name.toString());
    					}
    				}
    			}
    				
    		}
        }
        //check for java setters and getter
        MethodObj removeMethod;
        for(ClassObj c1 : visitor.allClassObj) {
        	for (String getName: c1.getMethods) {
        		
        		int index = getName.indexOf("get") + 3;
        		int index2 = getName.indexOf("set") + 3;
        		
        		String withoutGet = getName.substring(index, getName.length());

				for (String setName: c1.setMethods) {
	        		String withoutSet = setName.substring(index2, getName.length());
	        		if(setName.contains(withoutGet)) {
	        			for (VariableObj v: c1.varList) {
	        				if(v.name.equalsIgnoreCase(withoutGet) || v.name.equalsIgnoreCase(withoutSet)) {
	        					removeMethod = findJavaSetterAndGetter (getName, c1.realMethodList);
	        					if(removeMethod != null) {
	        						c1.realMethodList.remove(removeMethod);
	        					}
	        					removeMethod = findJavaSetterAndGetter (setName, c1.realMethodList);
	        					if(removeMethod != null) {
	        						c1.realMethodList.remove(removeMethod);
	        					}
	        				}
	        			}
	        		}
				}
        	}
        }
        //when defining field can urn off visibility
        String plantSyntax = "@startuml\nskinparam classAttributeIconSize 0\n";
        plantSyntax += parser(visitor);
        System.out.println(plantSyntax);
        
        //create the image
        FileOutputStream imageOut = new FileOutputStream(Output);
      
        SourceStringReader reader = new SourceStringReader(plantSyntax);
        System.out.println("\n Creating Image " + Output + "\n");
        
        try {
		 reader.generateImage(imageOut);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
    }
	
	public static String parser(Visitor v) {
		String t = "";
		List<String> onlyClasses = new ArrayList<String>();
		
		for (ClassObj c: v.allClassObj) {
			onlyClasses.add(c.name.toString());
		}
		
		for (ClassObj c: v.allClassObj) {
			//System.out.println(c.name.toString());
			for (VariableObj var: c.varList) {
				ClassObj.Relation findRel = c.new Relation();
				setLine(findRel, c);
			
				if (onlyClasses.contains(var.type)) {
					var.display = false;
					findRel.setRel2("\"1\"");
					findRel.setClass2(var.type);
					checkLine(findRel, c, v.allClassObj);
				}
				if (var.type.contains("Collection") && var.type.contains("<") && var.type.contains(">")) {
					int front = var.type.indexOf('<') + 1;
					int back = var.type.indexOf('>');
				
					if (onlyClasses.contains(var.type.substring(front, back))){
						var.display = false;
						findRel.setClass2(var.type.substring(front, back));
						findRel.setRel2("\"*\"");
						findRel.needClass1 = true;
						checkLine(findRel, c, v.allClassObj);
					}
	
				}
			}
		}
		for (ClassObj c: v.allClassObj) {
			if (c.checkInterface) 
				t += "interface " + c.name.toString() + "<<interface>>{\n";
			else if (!c.checkInterface)
				t += "class " + c.name.toString() + " {\n";
			for (VariableObj var: c.varList) {
				if (var.display) {
					if (Modifier.getAccessSpecifier(var.mod).asString() == "public")
						t += " + " + var.name + " : " + var.type + "\n";
					else if (Modifier.getAccessSpecifier(var.mod).asString() == "private") 
						t += " - " + var.name + " : " + var.type + "\n" ;
				}
			}
			//Constructor
			for (ConstructorObj cObj: c.constructorList) {
				if (Modifier.getAccessSpecifier(cObj.mod).asString() == "public") {
					t+= " + " + cObj.name + "(";
					for(Pair<String, String> p :cObj.parameterList) {
						t += p.b + " : " + p.a;
					}
					t += ")" + "\n";
				}
			}
			//if something is in the parameter (name: type)
			for (MethodObj met: c.realMethodList) {
				if (Modifier.getAccessSpecifier(met.mod).asString() == "public") {
					t += "+ " + met.name + "(";
					for(Pair<String, String> p :met.parameterList) {
						t += p.b + " : " + p.a;
					}
					t += ")" + " : " + met.returnName + "\n";
					
				}
			}
			
		
			t += "}\n";
		}
		for (ClassObj c: v.allClassObj) {
			for (ClassObj.Relation rel: c.relationList) {
				if (rel.needClass1 && rel.rel1 != null)
					t += rel.class1 + " " +rel.rel1+ " "+ rel.line + " "+ rel.rel2 + " " + rel.class2 + "\n";
				else if (rel.needClass1 && rel.rel1 == null) {
					t += rel.class1 + " "+ rel.line + " "+ rel.rel2 + " " + rel.class2 + "\n";
				}
				else if (!rel.needClass1)
					t += rel.class1 + " " + rel.line + " "+ rel.rel2 + " " + rel.class2 + "\n";
			}
		}
		
		// need to fix method setter and getter 
		for (ClassObj c: v.allClassObj) {
			//dotted line with black arrow ..>
			//add methods and variable 
			for (String name: c.addMethods) {
				if (c.checkInterface) {
				//System.out.println("string class" +p.a + " class name " + c.name.toString());
					t += name+ "..>" + c.name.toString() + ":uses\n";
				}
			}
		
			for (String name: c.addConstructors) {
			//	System.out.println(name + " " + c.name.toString());
				if (c.checkInterface){
					t += name+ "..>" + c.name.toString() + "\n";
				}
			}

		
			//System.out.println(c.checkInterface + " " +  c.name.toString());
			for(String name : c.implementList) {
					//System.out.println(name + " "+ c.name.toString());
					t +=  name + "<|.. " +  c.name.toString()+"\n";
			}
	
		}
		
		//solid line with triangle arrow
		for (ClassObj c: v.allClassObj) {
			if (!c.checkInterface) {
				for (String name: c.extendList) {
					t += name + "^-- "+ c.name.toString()  + "\n";
				}
			}
		}
		return t + "@enduml\n";
	}
	
	
	public static boolean checkIfInterface(List<ClassObj> all, String name) {
		boolean check = false;
		for(ClassObj c: all) {
			//System.out.println(name + " "+ c.name);
			if (name.equalsIgnoreCase(c.name.toString())) 
				
				check = c.checkInterface;
				return check;
				//System.out.println(name + " " + c.name.toString() + " " + check);
		}
		return check;
	}
	public static void checkLine(ClassObj.Relation temp, ClassObj obj, List<ClassObj> all) {
		boolean checkRelation = false;
		for (ClassObj relationObj: all) {
			for (ClassObj.Relation rel: relationObj.relationList) {
				if(relationObj.name.toString().equals(temp.class2)
					&& 	rel.class2.equals(temp.class1)) 
				{
					rel.rel1 = temp.rel2;
					checkRelation = true;
					
				}
			}
		}
		if (!checkRelation) {
			obj.relationList.add(temp);
		}
	}
	
	public static MethodObj findJavaSetterAndGetter (String name, List<MethodObj> list) {
		boolean found = false;
		for (MethodObj mObj: list) {
			if(mObj.name.equalsIgnoreCase(name)) {
				return mObj;
			}
		}
		return null;
		
	}
	public static void setLine(ClassObj.Relation findRelation, ClassObj c ) {
		findRelation.setLine("--");
		findRelation.setClass1(c.name.toString());
	}
 }