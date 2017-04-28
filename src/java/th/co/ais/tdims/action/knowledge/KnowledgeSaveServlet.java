/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.tdims.action.knowledge;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import th.co.ais.tdims.dao.KnowledgeDao;
import th.co.ais.tdims.model.Knowledge;
import th.co.ais.tdims.model.Profile;
import th.co.ais.tdims.util.FileUploadUtil;

/**
 *
 * @author Worawat
 */
public class KnowledgeSaveServlet extends HttpServlet {
final static Logger logger = Logger.getLogger(KnowledgeSaveServlet.class);
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageForword = "/jsp/login.jsp";
        try {
            
            Profile profile2 = (Profile)request.getSession().getAttribute("USER_PROFILE");
            String profileNow = profile2.getProfileId();
            int knlId = 0;
            String fileName = "";
            String id = "";
            String path = "";
            String type = "";
            String team = "";
            String detail = "";
           // Create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory();


            ServletContext servletContext = this.getServletConfig().getServletContext();
            File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(repository);
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> items = upload.parseRequest(request);
             for (FileItem item : items) {
			if (item.isFormField()) {
				String fieldname = item.getFieldName();
				if("knlId".equals(fieldname)){
					id = item.getString("UTF-8");
				}else if("fileName".equals(fieldname)){
					fileName = item.getString("UTF-8");
				}else if("id".equals(fieldname)){
					id = item.getString("UTF-8");
				}else if("type".equals(fieldname)){
					type = item.getString("UTF-8");
				}else if("detail".equals(fieldname)){
					detail = item.getString("UTF-8");
				}else if("team".equals(fieldname)){
					team = item.getString("UTF-8");
				}else if("path".equals(fieldname)){
					path = item.getString("UTF-8");
				}
		}
             }

            KnowledgeDao knowledgeDao = new KnowledgeDao();

            Knowledge knowledge = new Knowledge();
            
            
            knowledge.setCreateBy(profileNow);
            
            knowledge.setFileName(fileName);
            knowledge.setPath(path);
            knowledge.setTeamId(team);
            knowledge.setType(type);
            knowledge.setDetails(detail);
            
            
            
            
             logger.info("knlId ::=="+id);
            if(id.equals("")){
                logger.info(" create ");
                knowledgeDao.createKnowledge(knowledge);
            }else{
                logger.info(" update ");
                knowledge.setId(id);
                knowledgeDao.updateKnowledge(knowledge);
            }
           
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("save register error", e);
        }
        response.sendRedirect(request.getContextPath() + "/BudgetplanSearchServlet");
    }

}