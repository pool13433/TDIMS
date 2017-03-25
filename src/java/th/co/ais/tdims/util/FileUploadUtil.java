package th.co.ais.tdims.util;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

public class FileUploadUtil {
	 
	public static String uploadFile(HttpServletRequest request, FileItem item, String pathUploadTo) {
		String root = request.getServletContext().getRealPath("/");
		File file = null;
		try {
			if (!item.isFormField()) {
				file = new File(root,pathUploadTo);
				if (!file.exists()) {
					file.mkdirs(); 
				}
				
				file = new File(file, item.getName());
				item.write(file);
				
				System.out.println(file.getPath());
			}

			return pathUploadTo + "/" + item.getName();
		} catch (FileUploadException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}
