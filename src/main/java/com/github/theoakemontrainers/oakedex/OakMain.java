package com.github.theoakemontrainers.oakedex;

import com.github.theoakemontrainers.oakedex.dblookups.Info;
import com.github.theoakemontrainers.oakedex.dblookups.Lookup;
import com.github.theoakemontrainers.oakedex.dblookups.Search;

import net.nixill.databases.DBConnection;

public class OakMain {
	private static final DBConnection conn;
	public static final Lookup look;
	public static final Info info;
	public static final Search search;
	
	// this has to be up here but is ok
	static {
		conn = new DBConnection("pokedex.db");
		look = new Lookup(conn);
		info = new Info(conn);
		search = new Search(conn);
	}
	
	public static void main(String[] args) {
		new OakMenu().setVisible(true);
	}
	
}
