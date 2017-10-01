/*
Copyright (C) 2017 Wellington Cabrera, Huy Hoang, Carlos Ordonez

Redistribution and use in source and binary forms, with or without modification,
are permitted provided that the following conditions are met:

1. Redistributions of source code must retain the above copyright notice,
this list of conditions and the following disclaimer.

2. Redistributions in binary form must reproduce the above copyright notice,
this list of conditions and the following disclaimer in the documentation
and/or other materials provided with the distribution.

3. Neither the name of the copyright holder nor the names of its contributors
may be used to endorse or promote products derived from this software
without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
OF THE POSSIBILITY OF SUCH DAMAGE.
*/
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class HelperFunctions {
	protected Connection connection;
	protected PrintWriter sqloutput;
	public static final String TABLE = "table";

	public HelperFunctions() {
		connection = null;
	}

	public abstract void getConnection(String fileName);

	public abstract void createTempTable(Optimizations opt, CRV crvObj,
			SelectQuery selectQuery);

	public abstract void createTempJoinTable(Optimizations opt, CRV crvObj,
			SelectQuery selectQuery, String tableName, String selectionCondition);

	public int executeQuery(String query) {
		try {
			Statement statement = connection.createStatement();
			sqloutput.println(query);
			sqloutput.println();
			ResultSet rs = statement.executeQuery(query);
			rs.next();
			return rs.getInt(1);


		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Error while executing query " + query);
		}
		return -1;
	}
	//########################################################################
	public void executeQuery_modified(String query) {
		try {
			Statement statement = connection.createStatement();
			sqloutput.println(query);
			sqloutput.println();
			ResultSet rs = statement.executeQuery(query);
			int x = 1;
			while(rs.next()){
    		System.out.println(rs.getString(1).trim() + " "
				+ rs.getString(2).trim()+" "+ rs.getString(3).trim());
    		x++;
			}


		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Error while executing query " + query);
		}
		//return "Didn't work";
	}
	//#########################################################################
	public void executeRefresh() {
		try {
			Statement statement = connection.createStatement();
			sqloutput.println("SELECT START_REFRESH()");
			sqloutput.println();
			ResultSet rs = statement.executeQuery("SELECT START_REFRESH()");
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Error while executing refresh query ");
		}
	}

	public void executeCreateQuery(String query) {
		try {
			Statement statement = connection.createStatement();
			sqloutput.println(query);
			sqloutput.println();
			statement.executeUpdate(query);

		} catch (Exception e) {
			System.out.println("Error while executing query " + query);
			System.out.println(e);
			System.exit(0);
		}
	}

	public void executeDropQuery(String tableName,String type) {
		try {
			Statement statement = connection.createStatement();
			String dropIfExistsQuery = "DROP "+type+" IF EXISTS " + tableName+" CASCADE";
			sqloutput.println(dropIfExistsQuery);
			sqloutput.println();
			statement.executeUpdate(dropIfExistsQuery);

		} catch (Exception e) {
			System.out.println("Error while executing drop query ");
			System.out.println(e);
			System.exit(0);
		}
	}

	public abstract void semiNaiveRecursion(Optimizations opt, CRV crvObj,
			SelectQuery selectQuery);

	public abstract void apply_Optimizations_On_Temporary_Table(String targetTable,
			String sourceTable, Optimizations opt, CRV crvObj,
			SelectQuery selectQuery, String selectionCondition);

	public void deleteIntermediateTables(int d) {
		executeDropQuery("E",TABLE);
		for (int i = 1; i < d; i++) {
			String tableName = "R" + String.valueOf(i);
			executeDropQuery(tableName,TABLE);
		}
	}


	public abstract void updateStartTime(String dataset,Optimizations opt,int depth) ;

	public void updateEndTime() {
		String query="update colrq_timetrack set endtime=timestamp 'now' where endtime is null";
		executeCreateQuery(query);
	}
}
