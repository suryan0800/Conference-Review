import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import java.util.*;

public class Conference extends Frame implements ActionListener {

	Connection con;

	HashMap<String, Author> author;
	HashMap<String, Reviewer> reviewer;
	HashMap<String, Paper> paper;
	ArrayList<Ratings> ratings;

	Iterator<Ratings> ratings_iterator;

	Set<Map.Entry<String, Author>> author_set;
	Iterator<Map.Entry<String, Author>> author_iterator;

	Set<Map.Entry<String, Reviewer>> reviewer_set;
	Iterator<Map.Entry<String, Reviewer>> reviewer_iterator;

	Set<Map.Entry<String, Paper>> paper_set;
	Iterator<Map.Entry<String, Paper>> paper_iterator;

	Author aa;
	Reviewer rr;
	Paper pp;
	Ratings ra;

	Label title,pap;
	Button add_author, add_reviewer, add_paper, add_ratings,view_ratings;
	
	int last;
	
	TextField paperno;

	Conference() {
		super("Conference Review");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/examples?useSSL=false", "root", "root");
			
			ResultSet rs4 = con.createStatement().executeQuery("select no from paper;");
			rs4.last();
			last = Integer.parseInt(rs4.getString("no"));
			

		} catch (Exception e) {
			System.out.println(e);
		}
		
		

		author = new HashMap<String, Author>();
		reviewer = new HashMap<String, Reviewer>();
		paper = new HashMap<String, Paper>();
		ratings = new ArrayList<Ratings>();

		setLayout(null);
		title = new Label("Conference Review");
		title.setBounds(200, 40, 200, 20);
		title.setFont(new Font("Head", Font.BOLD, 15));
		add(title);

		add_author = new Button("Add Author");
		add_author.setBounds(200, 100, 100, 25);
		add(add_author);

		add_reviewer = new Button("Add Reviewer");
		add_reviewer.setBounds(200, 150, 100, 25);
		add(add_reviewer);

		add_paper = new Button("Add Paper");
		add_paper.setBounds(200, 200, 100, 25);
		add(add_paper);

		add_ratings = new Button("Add Ratings");
		add_ratings.setBounds(200, 250, 100, 25);
		add(add_ratings);
		
		paperno = new TextField(20);
		paperno.setBounds(100,300, 100, 30);
		add(paperno);
		
		pap = new Label("Paper No : ");
		pap.setBounds(30,300,70,30);
		add(pap);
		
		view_ratings = new Button("View Ratings");
		view_ratings.setBounds(250, 300, 100, 30);
		add(view_ratings);
		
		add_author.addActionListener(this);
		add_reviewer.addActionListener(this);
		add_paper.addActionListener(this);
		add_ratings.addActionListener(this);
		view_ratings.addActionListener(this);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent a) {
				setVisible(false);

				System.exit(0);
			}
		});

		setSize(500, 500);
		setBackground(Color.LIGHT_GRAY);
		setVisible(true);

		this.getAuthorDatabase();
		this.getReviewerDatabase();
		this.getPaperDatabase();
		this.getRatingsDatabase();

	}

	public void actionPerformed(ActionEvent a) {
		String event = a.getActionCommand();

		if (event.equals("Add Author")) {
			aa = new Author(this);
			aa.setVisible(true);
		}

		if (event.equals("Add Reviewer")) {
			rr = new Reviewer(this);
			rr.setVisible(true);
		}

		if (event.equals("Add Paper")) {
			
			pp = new Paper(this, author,last);
			pp.setVisible(true);
		}

		if (event.contentEquals("Add Ratings")) {
			ra = new Ratings(this, reviewer, paper);
			ra.setVisible(true);
			
		}
			
		if(event.contentEquals("View Ratings"))
		{
			for(Ratings me: ratings)
			{
				if(me.paper.paper_no.equals(paperno.getText()))
				{
				
					me.committee.setVisible(false);
					me.email.setVisible(false);
					me.submit.setEnabled(false);
					me.next.setEnabled(false);
					me.display();
					me.setVisible(true);
				}
			}
			
		}
		
	}

	void submitReviewer() {
		reviewer.put(rr.getEmail(), rr);
		reviewer_set = reviewer.entrySet();
		reviewer_iterator = reviewer_set.iterator();
		this.addToReviewerTable();
	}

	void submitPaper() {
		last++;
		paper.put(pp.getNumber(), pp);
		paper_set = paper.entrySet();
		paper_iterator = paper_set.iterator();
		this.addToPaperTable();
	}

	void submitAuthor() {
		author.put(aa.getEmail(), aa);
		author_set = author.entrySet();
		author_iterator = author_set.iterator();
		this.addToAuthorTable();

	}

	void submitRatings() {
		ratings.add(ra);
		ratings_iterator = ratings.iterator();
		this.addToRatingsTable();
	}

	void hasNextAuthor() {
		if (author_iterator != null) {
			if (author_iterator.hasNext()) {

				author_iterator.next().getValue().display();
			} else {
				if (author.entrySet() != null) {
					author_set = author.entrySet();
					author_iterator = author_set.iterator();
				}
			}
		}
	}

	void hasNextReviewer() {
		if (reviewer_iterator != null) {

			if (reviewer_iterator.hasNext()) {

				reviewer_iterator.next().getValue().display();
			} else {
				if (reviewer.entrySet() != null) {
					reviewer_set = reviewer.entrySet();
					reviewer_iterator = reviewer_set.iterator();
				}
			}
		}

	}

	void hasNextPaper() {
		if (paper_iterator != null) {

			if (paper_iterator.hasNext()) {

				paper_iterator.next().getValue().display();
			} else {
				if (paper.entrySet() != null) {
					paper_set = paper.entrySet();
					paper_iterator = paper_set.iterator();
				}
			}
		}
	}

	void hasNextRatings() {
		if (ratings_iterator != null) {
			if (ratings_iterator.hasNext()) {
				ratings_iterator.next().display();
			} else {
				ratings_iterator = ratings.iterator();
			}
		}

	}

	void getReviewerDatabase() {
		try {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from reviewer");
			while (rs.next()) {
				rr = new Reviewer(this);
				rr.email = rs.getString("email");
				rr.first_name = rs.getString("fname");
				rr.last_name = rs.getString("lname");
				rr.phone_number = rs.getString("phone");
				rr.affiliation = rs.getString("affiliation");
				rr.topics_of_interest = rs.getString("topics");
				reviewer.put(rr.getEmail(), rr);

			}

			reviewer_set = reviewer.entrySet();
			reviewer_iterator = reviewer_set.iterator();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	void getAuthorDatabase() {
		try {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from author");
			while (rs.next()) {
				aa = new Author(this);
				aa.email = rs.getString("email");
				aa.first_name = rs.getString("fname");
				aa.last_name = rs.getString("lname");
				aa.phone_number = rs.getString("phone");
				author.put(aa.getEmail(), aa);

			}

			author_set = author.entrySet();
			author_iterator = author_set.iterator();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	void getPaperDatabase() {
		try {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from paper");
			
			while (rs.next()) {
				pp = new Paper(this, author,last);
				pp.paper_no = rs.getString("no");
				pp.paper_title = rs.getString("title");
				pp.paper_abstract = rs.getString("abstract");
				pp.name_of_file = rs.getString("file");
				if (rs.getString("email") != null)
					pp.author[0] = author.get(rs.getString("email"));
				if (rs.getString("email2") != null)
					pp.author[1] = author.get(rs.getString("email2"));
				if (rs.getString("email3") != null)
					pp.author[2] = author.get(rs.getString("email3"));
				if (rs.getString("email4") != null)
					pp.author[3] = author.get(rs.getString("email4"));

				paper.put(pp.getNumber(), pp);

			}

			paper_set = paper.entrySet();
			paper_iterator = paper_set.iterator();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	void getRatingsDatabase() {
		try {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from ratings");
			
			
			while (rs.next()) {
				ra = new Ratings(this, reviewer, paper);
				ra.paper = paper.get(rs.getString("paperno"));
				ra.viewer = reviewer.get(rs.getString("email"));
				ra.tech_merit = rs.getInt("merit");
				ra.readability = rs.getInt("readability");
				ra.originality = rs.getInt("origin");
				ra.relavance = rs.getInt("relav");
				ra.recommendation = (rs.getInt("recom") == 1) ? true : false;
				ra.comment_author = rs.getString("comment1");
				ra.comment_review_committee = rs.getString("comment2");

				ratings.add(ra);

			}

			ratings_iterator = ratings.iterator();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	void addToAuthorTable() {
		try {
			String query = " insert into author (email,fname, lname,phone) values (?, ?, ?, ?)";

			// create table author(email varchar(20),fname varchar(20), lname varchar(20),phone varchar(20));
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, aa.email);
			preparedStmt.setString(2, aa.first_name);
			preparedStmt.setString(3, aa.last_name);
			preparedStmt.setString(4, aa.phone_number);

			preparedStmt.execute();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	void addToReviewerTable() {
		try {
			String query = " insert into reviewer (email,fname, lname,phone,affiliation,topics)"
					+ " values (?, ?, ?, ?, ?, ?)";

			// create table reviewer(email varchar(20),fname varchar(20), lname varchar(20),phone varchar(20),affiliation varchar(20),topics varchar(20));
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, rr.email);
			preparedStmt.setString(2, rr.first_name);
			preparedStmt.setString(3, rr.last_name);
			preparedStmt.setString(4, rr.phone_number);
			preparedStmt.setString(5, rr.affiliation);
			preparedStmt.setString(6, rr.topics_of_interest);

			preparedStmt.execute();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	void addToPaperTable() {

		try {
			String query = " insert into paper" + " values (?, ?, ?, ?, ?, ?, ?, ?)";

			// create table paper(no varchar(20),title varchar(20),abstract varchar(20),file varchar(20),email varchar(20),email2 varchar(20),email3 varchar(20),email4 varchar(20));
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, pp.paper_no);
			preparedStmt.setString(2, pp.paper_title);
			preparedStmt.setString(3, pp.paper_abstract);
			preparedStmt.setString(4, pp.name_of_file);
			if (pp.author[0] != null)
				preparedStmt.setString(5, pp.author[0].email);
			else
				preparedStmt.setString(5, null);
			if (pp.author[1] != null)
				preparedStmt.setString(6, pp.author[1].email);
			else
				preparedStmt.setString(6, null);
			if (pp.author[2] != null)
				preparedStmt.setString(7, pp.author[2].email);
			else
				preparedStmt.setString(7, null);
			if (pp.author[3] != null)
				preparedStmt.setString(8, pp.author[3].email);
			else
				preparedStmt.setString(8, null);

			preparedStmt.execute();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	void addToRatingsTable() {
		try {
			String query = " insert into ratings" + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

			// create table ratings(merit int,readability int,origin int,relav int,recom int,comment1 varchar(20),comment2 varchar(20),paperno varchar(20),email varchar(20));
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1, ra.tech_merit);
			preparedStmt.setInt(2, ra.readability);
			preparedStmt.setInt(3, ra.originality);
			preparedStmt.setInt(4, ra.relavance);
			preparedStmt.setInt(5, ((ra.recommendation == true) ? 1 : 0));
			preparedStmt.setString(6, ra.comment_author);
			preparedStmt.setString(7, ra.comment_review_committee);
			preparedStmt.setString(8, ra.paper.paper_no);
			preparedStmt.setString(9, ra.viewer.email);

			preparedStmt.execute();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void main(String args[]) {
		new Conference();
	}
}
