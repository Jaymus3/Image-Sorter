package org.sturtevantauto.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MakeModelInterface {
	public static boolean foundmake = false;
	public static void CheckMakeModelIndex(String model) throws IOException
	{
		String line;
		File makemodelindex = new File("/Users/sturtevantauto/Documents/workspace/MakeModelIndex.txt");
			BufferedReader reader = new BufferedReader(new FileReader(makemodelindex));
			while ((line = reader.readLine()) != null)
		    {
				if(line.contains(model))
				{
					//System.out.println("We had the make indexed!");
					line = line.replace(model, "");
					line = line.replace(" ", "");
					CarDefinitions.setMake(line);
					System.out.println("It's a " + CarDefinitions.getMake());
					foundmake = true;
				}
		    }
			reader.close();
	}
	public static void WriteMakeModelIndex(String model, String make) throws IOException
	{
		File makemodelindex = new File("/Users/sturtevantauto/Documents/workspace/MakeModelIndex.txt");
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(makemodelindex, true)));
	    out.println(make + " " + model);
		out.close();
	}

}
