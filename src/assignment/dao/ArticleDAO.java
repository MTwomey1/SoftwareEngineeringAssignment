
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
        	addArticleStatement = connection.prepareStatement(ArticleDAOSchema.ADD_ARTICLE);
        	
        	// Insert into the article table.
        	addArticleStatement.setString(1, article.getTitle());
        	addArticleStatement.setString(2, article.getContents());
        	int articleid = addArticleStatement.executeUpdate();
        	
        	// Insert into the articleCreated table
        	addArticleCreatedStatement = connection.prepareStatement(ArticleDAOSchema.ADD_ARTICLE_CREATED);
        	addArticleCreatedStatement.setString(1, article.getDateCreated());
        	addArticleCreatedStatement.setInt(2, articleid);
        	addArticleCreatedStatement.setInt(3, user.getId());
        	addArticleCreatedStatement.executeUpdate();
        	
        	System.out.println("QUERY: " + addArticleCreatedStatement.toString());
        	
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
        		String contents = resultSet.getString("body");
        		String date = resultSet.getString("date");
        		
        		articles.add(new Article(_id, title, contents, date));
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
	
	
	private final class ArticleDAOSchema {
		public final static String GET_ARTICLE = "SELECT * FROM Article WHERE id = ?";
		public final static String GET_ALL_ARTICLES = "SELECT * FROM Article";
		public final static String ADD_ARTICLE = "INSERT INTO Article VALUES(DEFAULT, ?, ?)";
		public final static String ADD_ARTICLE_CREATED = "INSERT INTO ArticleCreated VALUES(?, ?, ?)";
	}
}
