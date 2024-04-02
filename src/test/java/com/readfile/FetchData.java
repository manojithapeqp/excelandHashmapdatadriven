package com.readfile;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class FetchData {

	public static void main(String[] args) throws IOException {
		ReadFile rf=new ReadFile();
		ArrayList<String> arr=rf.getData("login","testdata");
		System.out.println(arr.get(0));
		System.out.println(arr.get(1));
		System.out.println(arr.get(2));
		
		

	}

}
