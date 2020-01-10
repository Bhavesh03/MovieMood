package movies.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import movies.models.Genre;
import movies.models.Movie;

public class MoviesDB {

	private static int getMovieID(Movie m) {
		int id = 0;
		try {
			Connection con = JDBCconn.getConnection();
			PreparedStatement ps = con.prepareStatement(
					"select mov_id from movie where mov_title=? and mov_year= ? and mov_lang=? and mov_dura=? and mov_img=?");
			ps.setString(1, m.getTitle());
			ps.setInt(2, m.getYear());
			ps.setString(3, m.getLang());
			ps.setInt(4, m.getDuration());
			ps.setString(5, m.getImage());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt(1);
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return id;
	}

	private static int addGenresMovies(Movie m) {
		int status = 1;
		int movID = getMovieID(m);
		try {
			Connection con = JDBCconn.getConnection();
			PreparedStatement ps = con.prepareStatement("insert into movie_genres(mov_id,gen_id) values(?,?)");
			for (Genre genre : m.getGenres()) {
				ps.setInt(1, movID);
				ps.setInt(2, genre.getId());
				status = status & ps.executeUpdate();
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	public static int addMovie(Movie m) {
		int status = 1;
		try {
			Connection con = JDBCconn.getConnection();
			PreparedStatement ps = con
					.prepareStatement("insert into movie(mov_title,mov_year,mov_lang,mov_dura,mov_img) values(?,?,?,?,?)");
			ps.setString(1, m.getTitle());
			ps.setInt(2, m.getYear());
			ps.setString(3, m.getLang());
			ps.setInt(4, m.getDuration());
			ps.setString(5, m.getImage());
			status = ps.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		status = status & addGenresMovies(m);
		return status;
	}

	public static int removeMovie(int mov_id) {
		int status = 0;
		try {
			Connection con = JDBCconn.getConnection();
			PreparedStatement ps = con.prepareStatement("delete from movie where mov_id=?");
			ps.setInt(1, mov_id);
			status = ps.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	public static List<Movie> getAllMovies() {
		List<Movie> list = new ArrayList<Movie>();
		List<Genre> genres = null;
		try {
			Connection con = JDBCconn.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from movie");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Movie m = new Movie();
				m.setId(rs.getInt(1));
				m.setTitle(rs.getString(2));
				m.setYear(rs.getInt(3));
				m.setLang(rs.getString(4));
				m.setDuration(rs.getInt(5));
				m.setImage(rs.getString(6));
				genres = GenresDB.getGenresByMovies(rs.getInt(1));
				m.setGenres(genres.toArray(new Genre[genres.size()]));
				list.add(m);
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

	public static List<Movie> getMoviesByGenre(Genre g) {
		List<Movie> list = new ArrayList<Movie>();
		List<Genre> genres = null;
		try {
			Connection con = JDBCconn.getConnection();
			PreparedStatement ps = con.prepareStatement(
					"select movie.* from movie inner join movie_genres on movie.mov_id=movie_genres.mov_id where movie_genres.gen_id=?");
			ps.setInt(1, g.getId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Movie m = new Movie();
				m.setId(rs.getInt(1));
				m.setTitle(rs.getString(2));
				m.setYear(rs.getInt(3));
				m.setLang(rs.getString(4));
				m.setDuration(rs.getInt(5));
				m.setImage(rs.getString(6));
				genres = GenresDB.getGenresByMovies(rs.getInt(1));
				m.setGenres(genres.toArray(new Genre[genres.size()]));
				list.add(m);
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

	public static List<Movie> getPapularMovie() {
		List<Movie> list = new ArrayList<Movie>();
		List<Genre> genres = null;
		try {
			Connection con = JDBCconn.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from movie");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Movie m = new Movie();
				m.setId(rs.getInt(1));
				m.setTitle(rs.getString(2));
				m.setYear(rs.getInt(3));
				m.setLang(rs.getString(4));
				m.setDuration(rs.getInt(5));
				m.setImage(rs.getString(6));
				genres = GenresDB.getGenresByMovies(rs.getInt(1));
				m.setGenres(genres.toArray(new Genre[genres.size()]));
				list.add(m);
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

	public static List<Movie> searchMovies(String title,String genre,int year) {
		List<Movie> list = new ArrayList<Movie>();
		List<Genre> genres = null;
		int gen_id = GenresDB.getGenreID(genre);
		
		String _gen_id,_year;
		
		if(title==null||title.isBlank()) {
			title="is not null";
		}
		else {
			title="="+title;
		}
		if(gen_id==-1) {
			_gen_id="is not null";
		}else {
			_gen_id="="+gen_id;
		}
		
		if(year==0) {
			_year="is not null";
		}else {
			_year="="+year;
		}
		
		String query ="SELECT * FROM movie WHERE mov_title "+title+" AND mov_year "+_year+" AND mov_Id IN(SELECT movie_genres.mov_Id FROM movie_genres WHERE movie_genres.gen_id "+_gen_id+" ) order by mov_year DESC";
		try {
			Connection con = JDBCconn.getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Movie m = new Movie();
				m.setId(rs.getInt(1));
				m.setTitle(rs.getString(2));
				m.setYear(rs.getInt(3));
				m.setLang(rs.getString(4));
				m.setDuration(rs.getInt(5));
				m.setImage(rs.getString(6));
				genres = GenresDB.getGenresByMovies(rs.getInt(1));
				m.setGenres(genres.toArray(new Genre[genres.size()]));
				list.add(m);
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}
	/*
	 * public static List<Movie> getMoviesByLanguage(String lang) {
	 * 
	 * }
	 * 
	 * public static List<Movie> getMoviesByDuration(int duration) {
	 * 
	 * }
	 * 
	 * public static int updateMovie(Movie m) {
	 * 
	 * }
	 */
}
