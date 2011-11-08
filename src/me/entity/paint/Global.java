package me.entity.paint;

import java.util.ArrayList;

public class Global
{
	public ArrayList<String> painters = new ArrayList<String>();
	public Paint parent;
	
	public Global(Paint paint)
	{
		this.parent = paint;
	}
}
