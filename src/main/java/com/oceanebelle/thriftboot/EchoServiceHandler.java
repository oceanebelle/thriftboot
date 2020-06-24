package com.oceanebelle.thriftboot;

import com.oceanebelle.thriftboot.echo.TEchoService;
import com.oceanebelle.thriftboot.echo.TInvalidRequestException;
import com.oceanebelle.thriftboot.echo.TOperation;
import org.apache.thrift.TException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class EchoServiceHandler implements TEchoService.Iface {

    @Override
    public String echo(String str1, TOperation op) throws TInvalidRequestException, TException {
        return str1;
    }

    @Override
    public int calculate(int num1, int num2, TOperation op) throws TInvalidRequestException, TException {
        if (op == TOperation.ADD) {
            return num1 + num2;
        }

        if (op == TOperation.MULTIPLY) {
            return num1 * num2;
        }

        if (op == TOperation.SUBTRACT) {
            return num1 - num2;
        }

        if (op == TOperation.DIVIDE) {
            return num1 / num2;
        }

        return 0;
    }
}