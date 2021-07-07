package br.edu.iftm.ecommerce.servlet;

import br.edu.iftm.ecommerce.util.Config;
import br.edu.iftm.ecommerce.util.MimeTypes;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author danilo
 */
@WebServlet(name = "ImgemServlet", urlPatterns = {"/imagens"}, initParams = {
    @WebInitParam(name = "codigo", value = "404")})
public class ImgemServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nomeArquivo = request.getParameter("codigo");
        FileInputStream file = null;
        try {
            file = new FileInputStream(Config.FOLDER_IMAGES+nomeArquivo);
        } catch (FileNotFoundException ex){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        String extensao = nomeArquivo.substring(nomeArquivo.lastIndexOf(".")+1);
        String mimeType = MimeTypes.getMimeType(extensao);
        Long tamanho = file.getChannel().size();
        
        response.setContentType(mimeType);
        response.setContentLengthLong(tamanho);
        response.getOutputStream().write(file.readAllBytes());
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
