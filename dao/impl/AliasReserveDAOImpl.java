package net.gobro.plauen.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

import javax.sql.DataSource;

import net.gobro.plauen.dao.AliasReserveDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;


@Repository
public class AliasReserveDAOImpl implements AliasReserveDAO {
	private static final Logger LOG = LoggerFactory
			.getLogger(AliasReserveDAOImpl.class);

	@Autowired
	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Integer getAlias(Integer gameId) {
		Connection con = getConnection();
		CallableStatement cstmt = null;
		Integer alias = null;
		try {
			cstmt = con.prepareCall("call plauen.getAlias( ?, ?, ?)");
			cstmt.setInt(1, gameId);
			cstmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			cstmt.registerOutParameter(3, Types.INTEGER);
			cstmt.execute();
			int returnedValue = cstmt.getInt(3);
			alias = new Integer(returnedValue);
		} catch (SQLException e) {
			LOG.debug("Unable to fetch free alias", e);
			alias = null;
		} finally {
			silentlyClose(con, cstmt, null);
		}

		return alias;
	}

	@Override
	public void freeReserves(Long gameDuration) {
		Connection con = getConnection();
		CallableStatement cstmt = null;
		try {
			cstmt = con.prepareCall("call plauen.clearOldReserves(?)");
			Timestamp ts = new Timestamp(System.currentTimeMillis() + gameDuration);
			cstmt.setTimestamp(0, ts);
			cstmt.execute();
		} catch (SQLException e) {
			LOG.debug("Unable to fetch free old alias reserves", e);
		} finally {
			silentlyClose(con, cstmt, null);
		}
	}

	private Connection getConnection() {
		Connection conn = DataSourceUtils.getConnection(dataSource);
		return conn;
	}

	/**
	 * Close database resources silently.
	 * @param con if null than ignored
	 * @param cstmt if null than ignored
	 * @param rs if null than ignored
	 */
	protected void silentlyClose(Connection con, CallableStatement cstmt, ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				// Maybe log here
			}
		}
		if(cstmt != null) {
			try {
				cstmt.close();
			} catch (Exception e) {
				// Maybe log here
			}
		}
		if(con != null) DataSourceUtils.releaseConnection(con, dataSource);
	}
}
