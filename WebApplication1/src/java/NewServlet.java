/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yuga-pt2098
 */
public class NewServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Connection c = null;
      Statement stmt = null;
      int scno=0;
        try {
            /* TODO output your page here. You may use following sample code. */
            PrintWriter out = response.getWriter();
            Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/sooraz",
            "postgres", "postgres");
         //c.setAutoCommit(false);
         System.out.println("Opened database successfully");

         stmt = c.createStatement();
         ResultSet rs = stmt.executeQuery( "SELECT * FROM questionanswer;" );
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewServlet at " + request.getContextPath() + "</h1>");
            out.println("<table border=2>");
            out.println("<tr>");
            out.println("<th>");out.println("serial no.");out.println("</th>");
            out.println("<th>");out.println("question");out.println("</th>");
            out.println("<th>");out.println("answer");out.println("</th>");
            out.println("</tr>");
            while ( rs.next() ) {
                out.println("<tr>");
            String  question = rs.getString("question");
            String source  = rs.getString("source");
            boolean check=rs.getBoolean("verified");
            out.println("<td>");out.println( ++scno );out.println("</td>");
            out.println("<td>");out.println( question );out.println("</td>");
            out.println("<td>");out.println( source );out.println("</td>");
            out.println("<td>");out.println( rs.getString("answer") );out.println("</td>");
            if(check){
                out.println("<td>");out.println( "verified" );out.println("</td>");}
            else
                out.println("<td>");out.println( "not verified" );out.println("</td>");
            out.println("</tr>");
         }
            out.println("</table><br><br>");
out.println("Add Questions");
            out.println("<form action = \"/WebApplication1/Include1\" method = \"POST\">\n" +
"         Question: <input type = \"text\" name = \"question\">\n" +
"         <br />\n" +
"         Answer: <input type = \"text\" name = \"answer\" />\n" +
"         <input type = \"submit\" value = \"Add question\" />\n" +
"      </form>");  
            
out.println("import from directory of .csv file");
            out.println("<form action=\"/WebApplication1/Import\" method=\"post\"  enctype=\"multipart/form-data\">\n" +
"         \n" +
"            <label for=\"file\">File</label>\n" +
"            <input type=\"file\" name=\"file\" id=\"file\" required>\n" +
"          \n" +
"          		          	\n" +
"	        <button type=\"submit\">Submit</button>\n" +
"	         	         \n" +
"</form>");             
            out.println("</body>");
            out.println("</html>");
        rs.close();
         stmt.close();
         c.close();
        }
        catch ( IOException | ClassNotFoundException | SQLException e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
