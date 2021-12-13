import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day13 {

	ArrayList<String> instruction = new ArrayList<>();
	ArrayList<int[]> points = new ArrayList<>();
	int maxX = 0;
	int maxY = 0;
	public void part1() throws FileNotFoundException
	{
		long startTime = System.nanoTime();
		Scanner scan = new Scanner(new File("day13.txt"));
		boolean point = true;
		while (scan.hasNextLine())
		{
			String cur = scan.nextLine();
			if (point)
			{
				if (cur.length() == 0)
				{
					point = false;
				}
				else
				{
					//point
					String[] pointxy = cur.split(",");
					int[] temp = {Integer.valueOf(pointxy[0]), Integer.valueOf(pointxy[1])};
					points.add(temp);
					if (Integer.valueOf(pointxy[0]) > maxX)
					{
						maxX = Integer.valueOf(pointxy[0]);
					}
					if (Integer.valueOf(pointxy[1]) > maxY)
					{
						maxY = Integer.valueOf(pointxy[1]);
					}
				}
			}
			else
			{
				//instructions
				String[] pointxy = cur.split(" ");
				instruction.add(pointxy[2]);
			}
		}
		System.out.println(Arrays.deepToString(points.toArray()));
		System.out.println(instruction.toString());
		
		
		for (int i = 0; i < instruction.size(); i++)
		{
			handleInstruction(instruction.get(i));
		}
		
		
		System.out.println(Arrays.deepToString(points.toArray()));
		System.out.println(points.size());
		print();
		long endTime = System.nanoTime();
		System.out.println("Took "+(endTime - startTime) + " ns");
	}
	
	private void print()
	{
		HashSet<String> set = new HashSet();
		for (int[] arr : points)
		{
			set.add(String.valueOf(arr[0]) + ":" + String.valueOf(arr[1]));
		}
		System.out.println(set.toString());
		for (int i = 0; i < 10; i++)
		{
			for (int j = 0; j < maxX; j++)
			{
				if (set.contains(String.valueOf(j) + ":" + String.valueOf(i)))
				{
					System.out.print("#");
				}
				else
				{
					System.out.print(".");
				}
			}
			System.out.println();
		}
		
	
	}
	
	private void handleInstruction(String instruct)
	{
		System.out.println("handleInstruction:" + instruct);
		String[] argument = instruct.split("=");
		if (argument[0].equals("y"))
		{
			//y
			handleY(Integer.valueOf(argument[1]));
			prune();
		}
		else
		{
			//x
			handleX(Integer.valueOf(argument[1]));
			prune();
		}
	}
	
	private void prune()
	{
		HashSet<String> set = new HashSet();
		for (int i = points.size()-1; i >= 0; i--)
		{
			int[] xy = points.get(i);
			String s = String.valueOf(xy[0]) + String.valueOf(xy[1]);
			if (set.contains(s))
			{
				points.remove(i);
			}
			else
			{
				set.add(s);
			}
		}
	}
	
	private void handleY(int yPos)
	{
		for (int i = 0; i < points.size(); i++)
		{
			int[] cur = points.get(i);
			//System.out.println("before:" + Arrays.toString(cur));
			if (cur[1] > yPos)
			{
				points.get(i)[1] = yPos - (cur[1] - yPos);
			}
			//System.out.println("	after:" + Arrays.toString(cur));
		}
	}
	
	private void handleX(int xPos)
	{
		for (int i = 0; i < points.size(); i++)
		{
			int[] cur = points.get(i);
			System.out.println("before:" + Arrays.toString(cur));
			if (cur[0] > xPos)
			{
				points.get(i)[0] = xPos - (cur[0] - xPos);
			}
			System.out.println("	after:" + Arrays.toString(cur));
		}
	}
	
	
	
	
}
