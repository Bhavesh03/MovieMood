package movies.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movies.db.MoviesDB;
import movies.models.Movie;

/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")

public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Home() {
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Movie> m = MoviesDB.getPapularMovie();
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		request.getRequestDispatcher("header.html").include(request, response);
		
		out.println("<div class=\"row boxMain\" style=\"background-image:url('"+m.get(0).getImage()+"')\">");
			out.println("<div class=\"col-md-12\" style=\"color: white\";>");
				out.println("<p style=\"font-size: xx-large;font-weight: bold;\">"+m.get(0).getTitle()+"</p>");
				out.println("<p>"+m.get(0).getYear()+","+m.get(0).getGenresAsString()+"</p>");
				out.println("<p>Time "+m.get(0).getDuration()+"</p>");
		out.println("</div></div>");
		
		out.println("<div class=\"row\">");
			out.println("<div class=\"col-md-9\">");
				out.println("<div class=\"row\">");
		
				for(int i=0;i<3;i++) {
					out.println("<div class=\"col-md-3 box\" style=\"background-image:url('"+m.get(i).getImage()+"')\">");
						out.println("<div class=\"row\">");
							out.println("<div class=\"col-md-12\" style=\"color: white\";>");
							out.println("<p style=\"font-size:large;font-weight: bold;\">"+m.get(i).getTitle()+"</p>");
							out.println("<p style=\"font-size:small;\">"+m.get(i).getYear()+","+m.get(i).getGenresAsString()+"</p>");
					out.println("</div></div></div>");
				}
					
		out.println("</div></div></div>");
		
		
		out.println("</div>");
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
