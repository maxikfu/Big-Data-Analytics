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

public class Optimizations {
	public String O1flag;
	public String O2flag;
	public String O3flag;
	public String O4flag;
	public boolean canOptimize=false;
	public String intermediateProjections;
	public String createIndex1;
	public String createIndex2;
	Optimizations() {
		O1flag="n";
		O2flag="n";
		O3flag="n";
		O4flag="n";
		intermediateProjections="n";
		createIndex1="n";
		createIndex2="n";
	}
	
}

class CRV {
	String query;
	String baseTable;
	String recursiveTable;
	String[] recursiveTableAttr;
	String baseStep;
	String[] baseStepProjections;
	String[] recursiveStepProjections;
	String recursiveStep;
	String joinCondition;
	String JoinAttrLeft;
    String JoinAttrRight;
    String JoinTableLeft;
    String JoinTableRight;
	String i,j,v,p,d;
	int recursionDepth;
	
	CRV(String query) {
		this.query=query;
	}
}

class SelectQuery {
	String query;
	boolean hasDistinct=false;
	boolean hasJoin=false;
	boolean hasWhere=false;
	boolean hasGroupBy=false;
	
	String whereCondition="";
	String optimizedWhereCondition="";
	String[] projectedColumns;
	String pAggregation=null;
	String vAggregation=null;
	
	String joinCondition=null;
	String joinLeftTable=null;
	String[] joinLeftTableAttr = new String[2];
	String[] joinRightTable = new String[2];
	String[] joinRightTableAttr = new String[2];
	String [] joinRightTableAlias=new String[2];
	
	int recursionDepth;
	
	public SelectQuery(String query) {
		this.query=query;
		this.recursionDepth=-1;
	}
}