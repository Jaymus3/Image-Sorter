package org.sturtevantauto.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {
	public static boolean CheckIfCarIndexed(String stock) throws IOException
	{
		boolean thebool = false;
		String line;
		File carindex = new File("/Users/sturtevantauto/Documents/workspace/IndexedCars.txt");
			BufferedReader reader = new BufferedReader(new FileReader(carindex));
			while ((line = reader.readLine()) != null)
		    {
				if(line.contains(CarDefinitions.getStock()))
				{
					System.err.println("Car already indexed!");
					thebool = true;
				}
		    }
			reader.close();
			return thebool;
	}
	public static void LogCar(String stock) throws IOException
	{
		File makemodelindex = new File("/Users/sturtevantauto/Documents/workspace/IndexedCars.txt");
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(makemodelindex, true)));
		out.println(stock);
		out.close();
	}

}
