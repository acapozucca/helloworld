package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datalayer.APIDataManagement;



/**
 * Servlet implementation class APIServlet
 */
@WebServlet(description = "API DB Servlet", urlPatterns = { "/select" , "/insert"}, initParams = {@WebInitParam(name="id",value="1"),@WebInitParam(name="name",value="venkat")})
public class APIServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String HTML_START="<html><body>";
	public static final String HTML_END="</body></html>";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public APIServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try{

			Date date = new Date();

	 		PrintWriter out = response.getWriter();

			APIDataManagement dbAPI = new APIDataManagement(); 

			HashMap<Integer, String> map = dbAPI.Db_TEST_Select();

			String date_info = "<h2 id='hi' >Data in table TEST collected at " + date + "!</h2><br/>";
			

			String body_info = "";

			int i=1;
			for (String value : map.values()) {
				body_info = body_info + "<h3 id='id' > In row " + i + " the values are " + value +  "</h3><br/>";
				i++;
			}
			

			out.println(HTML_START + date_info +  body_info + HTML_END);



		}
		catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}




	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {

			System.out.println("Running API Servlet for INSERT");

			String name = request.getParameter("name"); 
			String value = request.getParameter("value"); 



			APIDataManagement dbAPI = new APIDataManagement(); 

			int res = dbAPI.Db_TEST_Insert(name, value);
			PrintWriter out = response.getWriter();

			if (res==0) {

				out.println("<html><body><b>Successfully Inserted"
						+ "</b></body></html>"); 
			}else {
				out.println("<html><body><b>Failed Inserted"
						+ "</b></body></html>"); 
			}


		}catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
	}

}