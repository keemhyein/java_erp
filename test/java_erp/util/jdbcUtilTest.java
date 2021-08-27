package java_erp.util;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class jdbcUtilTest {

	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void testGetConnection() {
		System.out.println("testGetConnection()");
		Connection con = jdbcUtil.getConnection();
		Assert.assertNotNull(con);
		System.out.println("con >> " + con);
	}

}
