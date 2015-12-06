package assignment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import assignment.business.User;
import assignment.business.UserAccessPriveledge;
import assignment.exceptions.DaoException;


public class UserDao extends Dao {
	
	
	/**Bans a user.
	 * @param username The user name of user to ban.
	 **/
	public void banUser(String username) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = this.getConnection();
			statement = connection.prepareStatement(UserDaoSchema.BAN_USER);
			statement.setString(1, "Banned");
			statement.setString(2, username);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("There was an error banning the user.");
		} finally {
			try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("Could not " + e.getMessage());
            }
		}
	}
	/**
	 * Inserts a new user into the database.
	 * 
	 * @param The user to insert into the database.
	 * Note: All attributes of the user must be set.
	 * Otherwise a DaoExcpetion will be thrown.
	 * 
	 * @throws DaoException If any attributes in the user is null.
	 * Or if there was another SQL error.
	 * */
	public void insertUserIntoDatabase(User user) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = this.getConnection();
			
			statement = connection.prepareStatement(UserDaoSchema.INSERT_USER);
			
			statement.setString(1, user.getPassword());
			statement.setString(2, user.getUsername());
			statement.setString(3, user.getFirstName());
			statement.setString(4, user.getLastName());
			statement.setString(5, user.getAccessPriveledge().toString());
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("Could not insert user into the database");
		} finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("Could not " + e.getMessage());
            }
        }
	}
	
	
	
	/**
	 * Retrieves a user from the database with the given user name and password.
	 * @param username The user name.
	 * @param password The password for the user.
	 * @throws DaoException.
	 * @*/
    public User findUserByUsernamePassword(String username, String password) throws DaoException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            con = this.getConnection();
            
            ps = con.prepareStatement(UserDaoSchema.SELECT_USER);
            ps.setString(1, username);
            ps.setString(2, password);
            
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
            	int userId = resultSet.getInt("ID");
                String _username = resultSet.getString("USERNAME");
                String _password = resultSet.getString("PASSWORD");
                String lastname = resultSet.getString("LASTNAME");
                String firstname = resultSet.getString("FIRSTNAME");
                String access = resultSet.getString("ACCESSPRIVELEDGE");
                
                
                user = new User(
                		userId, 
                		firstname, 
                		lastname, 
                		_username, 
                		_password, 
                		UserAccessPriveledge.stringForPriveledge(access));
            }
        } catch (SQLException e) {
            throw new DaoException("findUserByUsernamePassword " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("findUserByUsernamePassword" + e.getMessage());
            }
        }
        return user;  
    }
       
    
    public User[] getAllUsers() throws DaoException {
    	ArrayList<User> users = new ArrayList<>();
    	Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        
        try {
        	conn = this.getConnection();
        	ps = conn.prepareStatement(UserDaoSchema.SELECT_ALL_USERS);
        	resultSet = ps.executeQuery();
        	
        	while (resultSet.next()) {
        		int userId = resultSet.getInt("ID");
        		String username = resultSet.getString("USERNAME");
                String password = resultSet.getString("PASSWORD");
                String lastname = resultSet.getString("LASTNAME");
                String firstname = resultSet.getString("FIRSTNAME");
                String access = resultSet.getString("ACCESSPRIVELEDGE");
                
                users.add(new User(
                		userId, 
                		firstname, 
                		lastname, 
                		username, 
                		password, 
                		UserAccessPriveledge.stringForPriveledge(access)));
        	}
        } catch (SQLException e) {
            throw new DaoException("findUserByUsernamePassword " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    freeConnection(conn);
                }
            } catch (SQLException e) {
                throw new DaoException("findUserByUsernamePassword" + e.getMessage());
            }
        }
        return users.toArray(new User[users.size()]);
    }
    
    private final class UserDaoSchema {
    	public static final String SELECT_ALL_USERS = "SELECT * FROM User";
    	public static final String SELECT_USER = "SELECT * FROM USER WHERE USERNAME = ? AND PASSWORD = ?";
    	public static final String INSERT_USER = "INSERT INTO User VALUES(DEFAULT, ?, ?, ?, ?, ?)";
    	public static final String BAN_USER = "UPDATE User SET accesspriveledge = ? WHERE username = ?;";
    }
}
