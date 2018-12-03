/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shivay
 */
public class signupservlet extends HttpServlet {

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
//        String uname = request.getParameter("email");
//         String pswd = request.getParameter("password");
        try (PrintWriter out = response.getWriter()) {
            Class.forName("com.mysql.jdbc.Driver");  
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/mydb?useSSL=false&allowPublicKeyRetrieval=true","root","shivay10");   
            PreparedStatement stmt = conn.prepareStatement("insert into register values(?,?,?,?)");  
            String u = request.getParameter("Usrname");
            String e = request.getParameter("email");
            String pw = request.getParameter("Pass");
            String pd = request.getParameter("Pswd");
            if(pw.equals(pd)==true)
               // out.println("Same Password");
                response.sendRedirect("login.html");
            else{
                reload();
            }
            out.println(pw);
            stmt.setString(1,u);
            stmt.setString(2,e);  
            stmt.setString(3,pw);
            stmt.setString(4,pd);
            int i=stmt.executeUpdate();
             if(i>0)
            out.println(" records inserted");  
            //conn.close();  
            
            
           
        }
        catch(Exception e)
    {
        System.out.println(e);
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

    private void reload() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
