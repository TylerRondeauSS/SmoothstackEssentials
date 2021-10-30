package com.ss.basics5.one;
//@author TylerRondeau
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class App {

	public static void main(String[] args) {
		App run = new App();
		List<String> lamStr = Arrays.asList("Java","String","Array", "eString", "aString", "aText", "e", "abc", "acd", "assda");
		List<Integer> lamInt = Arrays.asList(1,2,120,13,3,71,121,5665);
		System.out.println("Initial String List : ");
		lamStr.stream().forEach(name->System.out.println(name));
		//sort by string length and reprint
		System.out.println("\nSorted by length(naturally) String List : ");
		run.sortLongShort(lamStr);
		//sort backward by string length and reprint
		System.out.println("\nSorted by length(short to long) String List : ");
		run.sortShortLong(lamStr);
		//sort strings alphabetically by first letter only
		System.out.println("\nSort by first character(ignoring case) : ");
		run.sortAlph(lamStr);
		//sort strings starting with e first
		System.out.println("\nSorted by first character being e(ignoring case) : ");
		run.sortEFirst(lamStr);
		//repeat with helper function
		System.out.println("\nSort by first character e with helper : ");
		run.sortEHelped(lamStr);
		//Return comma separated string based on given list of integers
		System.out.println("\nComma separated integers with odd/even : ");
		String comInt = run.commaInts(lamInt);
		System.out.println(comInt);
		//return a list of all strings that start with a and have 3 letters
		System.out.println("\nAll strings that start with a and have 3 letters : ");
		List<String> a3Str = run.a3(lamStr);
		System.out.println(a3Str);
	}
	
	public void sortLongShort(List<String> lamStr) {
		lamStr.stream().sorted((s1,s2) -> s2.length() - s1.length()).forEach(name->System.out.println(name));
	}
	
	public void sortShortLong(List<String> lamStr) {
		lamStr.stream().sorted((s1,s2) -> s1.length() - s2.length()).forEach(name->System.out.println(name));
	}
	
	public void sortAlph(List<String> lamStr) {
		//tried shorter form println
		lamStr.stream().sorted((s1,s2) -> s1.toLowerCase().charAt(0) - s2.toLowerCase().charAt(0))
		.forEach(System.out::println);
	}
	
	public void sortEFirst(List<String> lamStr) {
		//? operator essentially is an if, 0 returns if the case is true, 1 if false
		lamStr.stream().sorted(Comparator.comparingInt(w -> w.toLowerCase().charAt(0) == 'e' ? 0:1))
		.forEach(name->System.out.println(name));
	}
	
	public void sortEHelped(List<String> lamStr) {
		String[] strArr = lamStr.toArray(new String[0]);
		Arrays.sort(strArr, (s1,s2) -> sortEHelper(s1,s2));
		lamStr = Arrays.asList(strArr);
		lamStr.stream().forEach(System.out::println);
	}
	
	public static Integer sortEHelper(String word, String word2) {
		if(word.toLowerCase().charAt(0) == 'e') {
			return -1;
		}
		if(word2.toLowerCase().charAt(0) == 'e') {
			return 1;
		}
		return 0;
	}
	
	public String commaInts(List<Integer> lamInt) {
		return lamInt.stream().map(i -> i%2 == 0 ? "e" + i: "o" + i).collect(Collectors.joining(","));
	}
	
	public List<String> a3(List<String> lamStr) {
		//Longer notation not using the ? operator from previously
		return lamStr.stream().filter(w -> {
			if(w.toLowerCase().charAt(0) == 'a' && 3 == w.length()) return true;
			return false;
		}).toList();
	}
}
