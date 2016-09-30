// AddRemoveTest.java

// Simple applet illustrating use of add/removeChildren fields.

import vrml.*;
import vrml.field.*;
import vrml.node.*;
//import vrml.external.Browser;
      
public class Sentinel extends Script
{
	static int yCoord[][] =
	{
//        0   1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19  20  21  22  23  24  25		
		{ 9, 10, 10, 10, 10, 10, 10,  9,  9,  9,  9,  9,  9,  8,  8,  8,  8,  9,  9,  9,  9,  9,  8,  7,  6,  6 },
		{ 9, 10, 10, 11, 11, 10, 10, 10, 10, 10, 10, 10, 10,  9,  9,  9,  9, 10, 10, 10, 10,  9,  8,  7,  7,  7 },
		{ 9, 10, 10, 11, 11, 10, 10, 10, 10, 10, 11, 11, 11,  9, 10, 10, 10, 10, 10, 10, 10,  9,  8,  8,  8,  8 },
		{ 9, 10, 10, 10, 10, 10,  9,  9, 10, 10, 11, 11, 11, 11, 10, 10, 10, 10, 10, 10, 10, 10,  9,  9,  9,  9 },
		{ 9, 10, 10, 10, 10,  9,  9,  9,  9, 10, 11, 11, 11, 11, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 },
		{ 9, 10, 10, 10, 10,  9,  9,  9,  9, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 },
		{ 9, 10, 10, 10, 10,  9,  9,  9,  9,  9,  9,  9,  9,  9,  9,  9, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 },
		{ 9, 10, 10, 10, 10,  9,  9,  9,  9,  9,  8,  8,  8,  8,  8,  8,  9,  9,  9, 10, 10, 10, 10, 10, 10, 10 },
		{ 8,  9, 10, 10, 10,  9,  9,  9,  9,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8,  9,  9,  9,  9,  9,  9,  9 },
		{ 8,  9, 10, 10, 10,  9,  9,  8,  8,  8,  8,  8,  8,  8,  7,  7,  8,  8,  8,  9,  9,  9,  9,  9,  9,  9 },
		{ 9,  9, 10, 10, 10, 10,  9,  8,  8,  8,  8,  8,  8,  8,  7,  7,  7,  7,  7,  8,  8,  9,  9,  9,  9,  9 },
		{ 9, 10, 11, 11, 11, 10, 10,  9,  9,  9,  9,  9,  8,  8,  7,  7,  7,  7,  7,  8,  8,  9,  9,  9,  9,  9 },
		{ 9, 10, 11, 11, 11, 11, 10, 10,  9,  9,  9,  9,  8,  8,  7,  7,  7,  7,  7,  8,  8,  9,  9,  9,  9,  9 },
		{ 9, 10, 11, 11, 11, 11, 10, 10, 10, 10,  9,  9,  8,  8,  7,  7,  8,  8,  8,  8,  8,  9,  9,  9,  9,  9 },
		{ 9, 10, 10, 11, 11, 10, 10, 10, 10, 10,  9,  9,  8,  7,  7,  7,  8,  8,  9,  9,  9,  9,  9,  9,  9,  9 },
		{ 9, 10, 10, 10, 10, 10,  9,  9,  9,  9,  9,  9,  9,  7,  7,  7,  8,  8,  9,  9,  9,  9,  9,  9,  9,  9 },
		{ 9, 10, 10, 10, 10, 10,  9,  8,  8,  9,  9,  9,  8,  7,  7,  7,  8,  8,  9,  9,  9,  9,  9,  9,  9,  8 },
		{ 9, 10, 10, 10, 10, 10,  8,  8,  8,  8,  9,  9,  8,  7,  7,  7,  8,  8,  9,  9, 10, 10,  9,  9,  9,  8 },
		{ 9, 10, 10, 10, 10, 10,  8,  8,  8,  8,  9,  9,  8,  7,  7,  7,  7,  8,  9,  9, 10, 10,  9,  9,  8,  7 },
		{ 9, 10, 10, 10, 10, 10,  9,  8,  8,  8,  9,  9,  8,  7,  7,  7,  7,  8,  9,  9,  9,  9,  9,  9,  8,  7 }
	};
	protected MFNode addChildren;
	protected SFNode Sentinel;
	protected SFNode Grond;
	protected SFNode coord;
	protected SFNode boulder;
	protected SFNode boulder1;
		
	protected void SetupGrid()
	{
		int row, col;
		int i, j, k;
		int p1, p2, p3, p4;
		int c1, c2, c3, c4;

		int RowSize = yCoord.length;
		int ColSize = yCoord[0].length;
		int color = 1;
		float[][] point;
		int[] coordIndex;
		int[] colorIndex;

		MFVec3f set_point= (MFVec3f) ((Node) coord.getValue()).getEventIn("set_point");
		MFInt32 set_coordIndex = (MFInt32) ((Node) Grond.getValue()).getEventIn("set_coordIndex");
		MFInt32 set_colorIndex = (MFInt32) ((Node) Grond.getValue()).getEventIn("set_colorIndex");
								
		i = RowSize * ColSize;
		point = new float[i][3];
		coordIndex = new int[10000];
		colorIndex = new int[10000];
		for (row = RowSize - 1; row >= 0; row--)
		{
			for (col = ColSize - 1; col >= 0; col--)
			{
				--i;
				point[i][0] = col;
				point[i][1] = yCoord[row][col];
				point[i][2] = row;
			}
		}
		j = 0;
		k = 0;
		for (row = 0; row < RowSize - 1; row++)
		{
			for (col = 0; col < ColSize - 1; col++)
			{
				p1 = yCoord[row][col];
				p2 = yCoord[row][col + 1];
				p3 = yCoord[row + 1][col + 1];
				p4 = yCoord[row + 1][col];
				c1 = row * ColSize + col;
				c2 = c1 + 1;
				c3 = c2 + ColSize;
				c4 = c1 + ColSize;
			
				if (p1 != p2 && p1 != p3 && p1 != p4 || p3 != p1 && p3 != p2 && p3 != p4)
				{
					coordIndex[j++] = c1;	
					coordIndex[j++] = c2;	
					coordIndex[j++] = c3;	
					coordIndex[j++] = -1;
					colorIndex[k++] = 4;
					coordIndex[j++] = c1;	
					coordIndex[j++] = c3;	
					coordIndex[j++] = c4;	
					coordIndex[j++] = -1;	
					colorIndex[k++] = 4;
				}
				else if (p2 != p1 && p2 != p3 && p2 != p4 || p4 != p1 && p4 != p2 && p4 != p3)
				{
					coordIndex[j++] = c1;	
					coordIndex[j++] = c2;	
					coordIndex[j++] = c4;	
					coordIndex[j++] = -1;	
					colorIndex[k++] = 4;
					coordIndex[j++] = c2;	
					coordIndex[j++] = c3;	
					coordIndex[j++] = c4;	
					coordIndex[j++] = -1;	
					colorIndex[k++] = 4;
				}
				else
				{
					coordIndex[j++] = c1;	
					coordIndex[j++] = c2;	
					coordIndex[j++] = c3;	
					coordIndex[j++] = c4;
					coordIndex[j++] = -1;
					if (p1 == p2 && p1 == p3 && p1 == p4)
					{
						if (row % 2 == 0)
						{
							if (col % 2 == 0)
								colorIndex[k++] = 1;
							else
								colorIndex[k++] = 2;
						}
						else
						{
							if (col % 2 == 0)
								colorIndex[k++] = 2;
							else
								colorIndex[k++] = 1;
						}
					}
					else
						colorIndex[k++] = 4;
				}
			}
		}
		set_point.setValue(point);
		set_coordIndex.setValue(j, coordIndex);
		set_colorIndex.setValue(k, colorIndex);
	}
//	protected int i;
	public void initialize()
	{
		Sentinel = (SFNode) getField("Sentinel");
		Grond = (SFNode) getField("Grond");
		coord = (SFNode) getField("coord");
		boulder = (SFNode) getField("boulder");
//boulder1 = (SFNode) getField("boulder1");
		addChildren = (MFNode) ((Node) Sentinel.getValue()).getEventIn("addChildren");
		SetupGrid();
//		i = 0;
	}

	protected ConstSFVec3f hit;
	public void processEvent(Event e)
	{
		if (e.getName().equals("hitPoint_changed"))
		{
//			Browser.print("hit");
			hit = (ConstSFVec3f) e.getValue();
//			Browser.print(hit.getX() + "," + hit.getY() + "," +hit.getZ());
		}
		else if (e.getName().equals("touchTime"))
		{
//			SFNode b = (SFNode) boulder.clone();
			System.out.println("Hello, world!");
//			SFNode b = new SFNode(boulder.getValue());
			SFNode b = boulder;
//			SFNode b;
//			b = i == 0 ? boulder : boulder1;
//			i = i + 1;

//String[] urls;
//urls = new String[1];
//urls[0] = "Boulder2.wrl";
//Browser.getBrowser().createVrmlFromURL(urls, this, "nodesLoaded");
			SFVec3f trans = (SFVec3f) ((Node) b.getValue()).getEventIn("set_translation");
			trans.setValue(hit);
			addChildren.addValue(b);
		}
	}
}
