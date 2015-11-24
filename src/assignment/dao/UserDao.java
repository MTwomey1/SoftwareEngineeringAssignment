package assignment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import assignment.business.User;
import assignment.business.UserAccessPriveledge;
import assignment.exceptions.DaoException;


public class UserDao extends Dao {
	
		
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
            
            String query = "SELECT * FROM USER WHERE USERNAME = ? AND PASSWORD = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
            	int userId = resultSet.getInt("ID");
                String _username = resultSet.getString("USERNAME");
                String _password = resultSet.getString("PASSWORD");
                String lastname = resultSet.getString("LAST_NAME");
                String firstname = resultSet.getString("FIRST_NAME");
                user = new User(userId, firstname, lastname, _username, _password, UserAccessPriveledge.GUEST);
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
   
}
