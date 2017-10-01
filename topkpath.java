import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class topkpath {
  public static String query="";
  public static String sourcevertex,destinationvertex,kPaths;
  public static String recDepth;
  public static void main(String[] args) {
    HelperFunctions helper;
    sourcevertex=args[0];
    destinationvertex=args[1];
    recDepth=args[2];
    kPaths= args[3];
    helper= new HelperFunctionsColumn();
    try {
      //System.out.println("works?");
      query = "select i,j,v from (select *, rank() over (partition by i,j order by v) as rank from equ) as temp where rank <="+kPaths+" and i ="+sourcevertex+" and j= "+destinationvertex+" and d <= "+recDepth+";";
      //System.out.println(query);
      //query = "select * from small_graph;";
      helper.getConnection("topkpathFile");
      //helper.executeQuery_modified(query);
      helper.executeQuery_modified(query);
    } catch (Exception e) {
      System.out.println("topkpath throws an exception"+e);
    }
  }
}
