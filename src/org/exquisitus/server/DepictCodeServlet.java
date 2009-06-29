
package org.exquisitus.server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.ext.typeinfo.HasAnnotations;

/**
 * Servlet implementation class DepictCodeServlet
 */
public class DepictCodeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    
	private static final String initPage = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\n";
	
	private static final List<String> reservedWord = new ArrayList<String>();
	
	private static final String INLINECSS = 
		"body { background-color: #FFEEFF; }" + 
		"span.special { color : mediumblue; }" +
		"span.color { font-weight:bold ; color: red; }";

	static {
		
		reservedWord.add("private");
		reservedWord.add("public");
		reservedWord.add("import");
		reservedWord.add("static");
		reservedWord.add("final");
	//	reservedWord.add("class"); ARGH
		reservedWord.add("interface");
		reservedWord.add("int");
		reservedWord.add("long");
		reservedWord.add("extends");
		reservedWord.add("super");
		reservedWord.add("new");
		reservedWord.add("void");
		reservedWord.add("throws");
		reservedWord.add("for");
		reservedWord.add("while");
		reservedWord.add("null");
		reservedWord.add("do");
		reservedWord.add("return");
		reservedWord.add("protected");
		reservedWord.add("package");
		
		}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepictCodeServlet() {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	private String createContent(BufferedReader input) throws IOException {
		
		String line = null;
		
		StringBuffer sb = new StringBuffer();
		
		sb.append(initPage);

		sb.append("<html><head><style type=\"text/css\">" +
		INLINECSS +	"</style></head><body>");
		
		while (( line = input.readLine()) != null) {
			
			sb.append("<span class=\"special\">" + colorReservedWords(line) + "</span>");
		    sb.append("<br/>");
		}
		
		sb.append("</body></html>");

		return sb.toString();
	}
	
	private String colorReservedWords(String line) {
	
		line = line.replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;"); // replace \t tabs
		
		for (String str : reservedWord)
		{
			if (line.contains(str))
				line = line.replace(str, "<span class=\"color\">" + str + "</span>");
		}
		return line;
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.reset();
		response.setContentType("text/html");

		String myfile = request.getParameter("file");
		
		if (myfile == null) {
			response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
			return;
		}
		
		// EXAMPLE : myfile = "org.exquisitus.server.EJB3ProxyServlet";
		myfile = myfile.replace('.', '/');
		myfile += ".java";
		
		String realPath = getServletContext().getRealPath("");
		realPath = realPath.substring(0, realPath.length()-4); // remove "/war"
		realPath += "src/"; // added "src/" ! :-)
		
		FileReader file = new FileReader(realPath + myfile);

		if (file == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		PrintWriter pw = response.getWriter();
				
		pw.print(createContent(new BufferedReader(file)));
		
		pw.flush();
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);		
	}

}
