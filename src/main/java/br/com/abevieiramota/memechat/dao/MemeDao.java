package br.com.abevieiramota.memechat.dao;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.postgresql.largeobject.LargeObject;
import org.postgresql.largeobject.LargeObjectManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
// https://jdbc.postgresql.org/documentation/81/binary-data.html
public class MemeDao {

	private Connection conn;

	@Autowired
	public MemeDao(DataSource ds) throws SQLException {

		this.conn = ds.getConnection();
	}

	public InputStream find(long id) {
		try {
			this.conn.setAutoCommit(false);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		LargeObjectManager lobj = null;
		LargeObject obj = null;
		try {
			lobj = ((org.postgresql.PGConnection) this.conn).getLargeObjectAPI();
			try(PreparedStatement stmt = this.conn.prepareStatement("SELECT bytes FROM meme WHERE id = ?")) {
				stmt.setLong(1, id);
				ResultSet rs = stmt.executeQuery();
				if(!rs.next()) {
					throw new IllegalArgumentException("meme not found");
				}
				long oid = rs.getLong(1);
				obj = lobj.open(oid, LargeObjectManager.READ);
				
				byte buf[] = new byte[obj.size()];
				obj.read(buf, 0, obj.size());
				
				return new ByteArrayInputStream(buf);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (obj != null) {
				try {
					obj.close();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}
			try {
				this.conn.setAutoCommit(true);
			} catch (SQLException e1) {
				throw new RuntimeException(e1);
			}
		}

	}

	public void add(InputStream imgAsStream, long id) {
		try {
			this.conn.setAutoCommit(false);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		LargeObjectManager lobj = null;
		LargeObject obj = null;
		long oid;
		try {
			lobj = ((org.postgresql.PGConnection) this.conn).getLargeObjectAPI();
			oid = lobj.createLO(LargeObjectManager.READ | LargeObjectManager.WRITE);
			obj = lobj.open(oid, LargeObjectManager.WRITE);

			byte buf[] = new byte[2048];
			int s;
			while ((s = imgAsStream.read(buf, 0, 2048)) > 0) {
				obj.write(buf, 0, s);
			}

			try (PreparedStatement pstmt = this.conn.prepareStatement("INSERT INTO meme VALUES (?, ?)")) {
				pstmt.setLong(1, id);
				pstmt.setLong(2, oid);
				pstmt.executeUpdate();
				this.conn.commit();
			}
		} catch (SQLException | IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (obj != null) {
				try {
					obj.close();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}
			try {
				this.conn.setAutoCommit(true);
			} catch (SQLException e1) {
				throw new RuntimeException(e1);
			}
		}
	}
}
