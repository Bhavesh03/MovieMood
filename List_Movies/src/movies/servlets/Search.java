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
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		request.getRequestDispatcher("searchheader.html").include(request, response);
		
		String title="";
		String genre = "";
		int year = 0;
		if(request.getParameter("search")!=null) {
			title=request.getParameter("search");
		}else{
			if(request.getParameter("title")!=null&&!request.getParameter("title").isBlank()) {
				title=request.getParameter("title");
			}
			if(request.getParameter("genre")!=null&&!request.getParameter("genre").isBlank()) {
				genre = request.getParameter("genre");
			}
			if(request.getParameter("year")!=null&&!request.getParameter("year").isBlank()) {
				year = Integer.parseInt(request.getParameter("year"));
			}
		}
		
		List<Movie> movies=MoviesDB.searchMovies(title, genre, year);
		System.out.println(title);
		System.out.println(movies.size());
		out.println("<form action=\"Search\" method=\"post\" style=\"width:100%\">");
			out.println("<div class=\"form-group\">");
			out.println("<input type=\"text\" class=\"form-control\" name=\"title\" id=\"title1\" placeholder=\"Title\" value=\""+title+"\"/></div>");
			out.println("<div class=\"form-group\">");
			out.println("<input type=\"text\" class=\"form-control\" name=\"genre\" id=\"genre1\" placeholder=\"All Genres\"  value=\""+genre+"\"/></div>");
			out.println("<div class=\"form-group\">");
			out.println("<input type=\"text\" class=\"form-control\" name=\"year\" id=\"year1\" placeholder=\"All Years\" \"/></div>");
			out.println("<button type=\"submit\" class=\"btn btn-primary\" style=\"width:100%\">Search</button>");
		out.println("</form>");
		
		
		out.println("<div class=\"row\" style=\"padding-right:10px; padding-left:10px;\">");
			out.println("<div class=\"col-md-12 text-box\">Found "+movies.size()+" Movies</div>");
		out.println("</div>");
		
		out.println("<div class=\"row\" style=\"margin-left: 10px;\">");
		
			for(int i=0;i<movies.size();i++) {
				out.println("<div class=\"col-md-3 movie-view-search \">");
					out.println("<div class=\"row\" style=\"height:220px; background-position:center;background-size:cover; background-image:url('"+movies.get(i).getImage()+"')\"></div>");
						out.println("<div class=\"row\">");
							out.println("<div class=\"col-md-12\" style=\"color: black\";>");
							out.println("<p style=\"font-size:large;font-weight: bold;\">"+movies.get(i).getTitle()+"</p>");
							out.println("<p style=\"font-size:small;\">"+movies.get(i).getYear()+","+movies.get(i).getGenresAsString()+"</p>");
				out.println("</div></div></div>");
			}
		out.println("</div>");
			
		
		out.println("<ul class=\"pagination justify-content-center\" style=\"margin-left:10px\">");
			out.println("<li class=\"page-item\"><a class=\"page-link\" href=\"ViewByStart?start=a\">2</a></li>");
		out.println("</ul>");
		
		
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
