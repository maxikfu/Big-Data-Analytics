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

public class RQ_CALL {

	public static final String dbType = "column";
	public static String O1 = "N";
	public static String O2 = "N";
	public static String O3 = "N";
	public static String O4 = "y";
	public static String intermedProjections = "N";
	public static String createIndex1 = "N";
	public static String createIndex2 = "N";
	//public static final int depth = 2;
	public static void main(String[] args) {
		int recursiveDepth = 10;
		String tableName = "small_graph" + recursiveDepth;
                //O3= args[0];

		String inputFile = tableName + "_groupopt.txt";
		String inputArgument = "file=" + inputFile + ",O1=" + O1 + ",O2="
			+ O2 + ",O3=" + O3 + ",O4=" + O4 + "," + "database=" + dbType + ","
			+ "intermediateProjections="+intermedProjections+",createIndex1="+createIndex1+",createIndex2="+createIndex2;

		try {
			rq.parser(inputArgument, Integer.parseInt(tableName.substring(tableName.length() - 2)));
		} catch (Exception e) {
			System.out.println("rq parser throws an exception"+e);
		}
		System.out.println(inputArgument);
	}

}
