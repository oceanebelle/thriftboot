namespace java.swift com.oceanebelle.thriftboot.echo
namespace java com.oceanebelle.thriftboot.echo
enum TOperation {
  ADD = 1,
  SUBTRACT = 2,
  MULTIPLY = 3,
  DIVIDE = 4
}
exception TInvalidRequestException {
}
service TEchoService {
   string echo(1:string str1, 2:TOperation op) throws (1:TInvalidRequestException invalidRequest);
   i32 calculate(1:i32 num1, 2:i32 num2, 3:TOperation op) throws (1:TInvalidRequestException invalidRequest);
}