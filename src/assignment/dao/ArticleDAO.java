
package assignment.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Connection;

import assignment.business.Article;
import assignment.exceptions.DaoException;

public class ArticleDao extends Dao {
	
	
	public void addArticle(Article article) throws DaoException {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
        	connection = this.getConnection();
        	preparedStatement = connection.prepareStatement(ArticleDAOSchema.ADD_ARTICLE);
        	
        	
        	preparedStatement.setString(2, article.getTitle());
        	preparedStatement.setString(3, article.getContents());
        	preparedStatement.setString(4, article.getDateCreated());
        	
        	resultSet = preparedStatement.executeQuery();
        	
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
	}
	
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
        		String contents = resultSet.getString("body");
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
		public final static String ADD_ARTICLE = "INSERT INTO Article VALUES(?, ?, ?, ?)";
	}
}
