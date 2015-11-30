
package assignment.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import assignment.business.Article;
import assignment.business.User;
import assignment.exceptions.DaoException;

public class ArticleDao extends Dao {
	
	
	public void addArticle(Article article, User user) throws DaoException {
		Connection connection = null;
        PreparedStatement addArticleStatement = null;
        PreparedStatement addArticleCreatedStatement = null;
        
        try {
        	connection = this.getConnection();
        	addArticleStatement = connection.prepareStatement(ArticleDAOSchema.ADD_ARTICLE, 
        			PreparedStatement.RETURN_GENERATED_KEYS);
        	
        	// Insert into the article table.
        	addArticleStatement.setString(1, article.getTitle());
        	addArticleStatement.setString(2, article.getContents());
        	addArticleStatement.executeUpdate();
        	int articleid;
        	
        	// Get the auto incremented key from the article table.
        	articleid = getGeneratedKeys(addArticleStatement, 1);
        	
        	// Insert into the articleCreated table
        	addArticleCreatedStatement = connection.prepareStatement(ArticleDAOSchema.ADD_ARTICLE_CREATED);
        	addArticleCreatedStatement.setString(1, article.getDateCreated());
        	addArticleCreatedStatement.setInt(2, articleid);
        	addArticleCreatedStatement.setInt(3, user.getId());
        	addArticleCreatedStatement.executeUpdate();
        	
        	
        } catch (SQLException e) {
        	e.printStackTrace();
        } finally {
        	try {
        		if (addArticleStatement != null) {
                    addArticleStatement.close();
                }
        		if (addArticleCreatedStatement != null) {
        			addArticleCreatedStatement .close();
        		}
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("getArticle." + e.getMessage());
            }
		}
	}

	
	/**
	 * Gets the key generated from an insert on a table
	 * 
	 * @param statement The statement used.
	 * @param column The index of the column where the key will be generated.
	 * @return The value of the generated key if it was successful.
	 * @throws DaoException if a key could not be found.
	 *  */
	private int getGeneratedKeys(PreparedStatement statement, int columnIndex) throws DaoException {
		try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
			 if (generatedKeys.next()) { 
				 return generatedKeys.getInt(columnIndex);
			 } else {
				 throw new DaoException("Could not find generated key.");
			 }
		 } catch(SQLException e) {
			throw new DaoException("Could not find generated key"); 
		 }	
	}
	
	
	
	/**
	 * Gets an article from the database according to its id.
	 * 
	 * @param id The of the article
	 * @return The article.
	 * */
	public Article getArticle(int id) throws DaoException {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Article article = null;
        
        try {
        	connection = this.getConnection();
        	
        	preparedStatement = connection.prepareStatement(ArticleDAOSchema.GET_ARTICLE);
        	preparedStatement.setInt(1, id);
        	
        	resultSet = preparedStatement.executeQuery();
        	while (resultSet.next()) {
        		int _id = resultSet.getInt("id");
        		String title = resultSet.getString("title");
        		String contents = resultSet.getString("content");
        		String date = resultSet.getString("date");
        		
        		article = new Article(_id, title, contents, date);
        	}
        } catch (SQLException e) {
        	e.printStackTrace();
        } finally {
        	try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("getArticle." + e.getMessage());
            }
		}
        return article;
	}
	
	
	
	public Article[] getAllArticles() throws DaoException {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Article> articles = new ArrayList<>();
		
		try {
			conn = this.getConnection();
			preparedStatement = conn.prepareStatement(ArticleDAOSchema.GET_ALL_ARTICLES);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				int _id = resultSet.getInt("id");
        		String title = resultSet.getString("title");
        		String contents = resultSet.getString("content");
        		String date = resultSet.getString("dateAdded");
        		int authorID = resultSet.getInt("userid");
        		User author = new User();
        		author.setId(authorID);
        		
        		articles.add(new Article(_id, title, contents, date, author));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
        	try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conn != null) {
                    freeConnection(conn);
                }
            } catch (SQLException e) {
                throw new DaoException("getArticle." + e.getMessage());
            }
		}
		return articles.toArray(new Article[articles.size()]);
	}
	
	
	
	/**
	 * All transactions and selects to query the Article and
	 * ArticleCreated table.
	 * */
	private final class ArticleDAOSchema {
		/**
		 * Query to get an article from the database.
		 * */
		public final static String GET_ARTICLE = "SELECT * FROM Article WHERE id = ?";
		
		/**
		 * Query to get all articles within the database.
		 * */
		public final static String GET_ALL_ARTICLES = "select * from article"
				+ "join articlecreated on article.id = articlecreated.articleid;";
		
		/**
		 * Transaction to add an article to the database.
		 * */
		public final static String ADD_ARTICLE = "INSERT INTO Article VALUES(DEFAULT, ?, ?)";
		
		/**
		 * Transaction to add to the ArticleCreated table, which is a weak entity an needs to be
		 * updated every time an article is added to the database.
		 * */
		public final static String ADD_ARTICLE_CREATED = "INSERT INTO ArticleCreated VALUES(?, ?, ?)";
		
	}
}
