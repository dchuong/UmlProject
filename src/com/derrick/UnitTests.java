package com.derrick;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UnitTests {
	private String plantuml;
	private int fileNumber;
	private String[] classBreak;
	UnitTests(String plantuml, int fileNumber) {
		//plantuml = plantuml.replaceAll("\\s", "");
		plantuml = plantuml.replace("@startuml\nskinparam classAttributeIconSize 0", "");
		
		//this.classBreak = plantuml.split("class");
		Pattern classP = Pattern.compile("class.*[^\\{]");
		List<String> temp = new ArrayList<String>();
		Matcher classM = classP.matcher(plantuml);
		
		Pattern innerP = Pattern.compile("\\{(.*?)*\\}");
	//	System.out.println("\\{(.*?)");
		Matcher innerM = innerP.matcher(plantuml);
		
		
		while (classM.find()) {
			String className = classM.group().replace("{", "");
			temp.add(className);
			System.out.println(className);
		}
		
		while (innerM.find()) {
			System.out.println(innerM.group());
		}

		this.plantuml = plantuml;
		this.fileNumber = fileNumber;
	}
	
	@Test 
	public void testAll() {
		this.testPlant1();
	}
	@Test
	public void testPlant1() {
		/*
		for (String c: classBreak) {
			System.out.println(c);
		}
		*/
		//System.out.println(classBreak);
		switch (fileNumber) {
		case 1:
			//System.out.println(plantuml);
			break;
		}
	}
	
}
