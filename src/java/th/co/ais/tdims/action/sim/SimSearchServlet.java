/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.tdims.action.sim;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import th.co.ais.tdims.util.CharacterUtil;

/**
 *
 * @author POOL_LAPTOP
 */
public class SimSearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {   
        String mobileNo = CharacterUtil.removeNull(request.getParameter("mobileNo"));
        System.err.println(" mobileNo ::=="+mobileNo);
        request.setAttribute("mobileNo", mobileNo);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/sim/sim-search.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

}
