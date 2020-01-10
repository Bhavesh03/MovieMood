package movies.models;

public class Movie {
	private int id,duration,year;
	private String title, lang, image;
	private Genre[] genre;

	public Movie() {
		super();
	}

	public Movie(int duration,int year, String title, String lang, String image, Genre[] genre) {
		this.duration = duration;
		this.year=year;
		this.title = title;
		this.lang = lang;
		this.image = image;
		this.genre = genre;
	}
	
	public Movie(int id, int duration,int year, String title, String lang, String image,Genre[] genre) {
		this(duration,year,title,lang,image,genre);
		this.id = id;	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	public Genre[] getGenres() {
		return genre;
	}
	
	public void setGenres(Genre[] genres) {
		this.genre = genres;
	}
	
	public String getGenresAsString() {
		String str="";
		for(Genre g : genre) {
			str+=g.toString()+" ";
		}
		return str;
	}
	public String toString() {
		String str="";
		str+="Title:"+title+" Duration:"+duration+" Language:"+lang+" Genres:";
		for(Genre g : genre) {
			str+=g.toString()+",";
		}
		return str; 
	}
}
