package movies.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import movies.models.Genre;

public class GenresDB {
	
	
	public static int addGenre(Genre g) {
		int status = 0;
		try {
			Connection con = JDBCconn.getConnection();
			PreparedStatement ps = con.prepareStatement("insert into genres(gen_title) values(?)");
			ps.setString(1, g.getTitle());
			status = ps.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	public static int removeGenre(int id) {
		int status = 0;
		try {
			Connection con = JDBCconn.getConnection();
			PreparedStatement ps = con.prepareStatement("delete from genres where gen_id=?");
			ps.setInt(1, id);
			status = ps.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	public static List<Genre> getAllGenres() {
		List<Genre> list = new ArrayList<Genre>();
		try {
			Connection con = JDBCconn.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from genre");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Genre g = new Genre();
				g.setId(rs.getInt(1));
				g.setTitle(rs.getString(2));
				list.add(g);
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

	public static List<Genre> getGenresByMovies(int mov_id) {
		List<Genre> list = new ArrayList<Genre>();
		try {
			Connection con = JDBCconn.getConnection();
			PreparedStatement ps = con.prepareStatement(
					"select genres.* from genres inner join movie_genres on genres.gen_id=movie_genres.gen_id where movie_genres.mov_id=?");
			ps.setInt(1, mov_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Genre g = new Genre();
				g.setId(rs.getInt(1));
				g.setTitle(rs.getString(2));
				list.add(g);
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}
	
	public static int getGenreID(String g) {
		int id=-1;
		try {
			Connection con = JDBCconn.getConnection();
			PreparedStatement ps = con.prepareStatement("select gen_id from genres where gen_title=?");
			ps.setString(1, g);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				id=rs.getInt(1);
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return id;
	}
	/*
	 * public static int updateGenre(Genre m) {
	 * 
	 * }
	 */
}
