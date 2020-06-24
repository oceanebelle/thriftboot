package com.oceanebelle.thriftboot;

import com.oceanebelle.thriftboot.echo.TEchoService;
import com.oceanebelle.thriftboot.echo.TOperation;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.THttpClient;
import org.apache.thrift.transport.TTransport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ThriftBootApplicationTest {

	@Autowired
	protected TProtocolFactory protocolFactory;

	@LocalServerPort
	protected int port;

	protected TEchoService.Client client;

	@BeforeEach
	public void setUp() throws Exception {
		TTransport transport = new THttpClient("http://localhost:" + port + "/echo");

		TProtocol protocol = protocolFactory.getProtocol(transport);

		client = new TEchoService.Client(protocol);
	}
	@Test
	public void testAdd() throws Exception {
		assertEquals(5, client.calculate(2, 3, TOperation.ADD));
	}
	@Test
	public void testSubtract() throws Exception {
		assertEquals(3, client.calculate(5, 2, TOperation.SUBTRACT));
	}

	@Test
	public void testMultiply() throws Exception {
		assertEquals(10, client.calculate(5, 2, TOperation.MULTIPLY));
	}

	@Test
	public void testDivide() throws Exception {
		assertEquals(2, client.calculate(10, 5, TOperation.DIVIDE));
	}

//	@Test(expected = TInvalidRequestException.class)
//	public void testDivisionByZero() throws Exception {
//		client.calculate(10, 0, TOperation.DIVIDE);
//	}

}
