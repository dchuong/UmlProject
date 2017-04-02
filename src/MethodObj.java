import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.utils.Pair;

public class MethodObj {
	public String name;
	public String returnName;
	public EnumSet<Modifier> mod;
	List<Pair<String,String>> parameterList = new ArrayList<Pair<String, String>>();
	List<String> usesList = new ArrayList<String>();
	boolean addMethodObj = true;
}
