package geomex.utils;

/**
 * <PRE>
 * 파일명   : ColsoleLogger.java
 * 파일설명 : 로그메세지를 Console 화면으로 출력한다
 * 수정이력 : 
 *       2013. 6. 19.  김경호  : 최초작성
 * 
 * </PRE>
 * 
 */
public class ConsoleLogger extends Logger {

    /**
     * 기본 생성자
     */
    public ConsoleLogger() {}

    @Override
    public void debug(String msg) {
        if (logLevel == DEBUG) {
            System.out.print(format("DEBUG", msg));
        }
    }

    @Override
    public void info(String msg) {
        if (logLevel <= INFO) {
            System.out.print(format("INFO", msg));
        }
    }

    @Override
    public void warn(String msg) {
        // debug or warn 경우
        if (logLevel <= WARN) {
            System.out.print(format("WARN", msg));
        }
    }

    @Override
    public void error(String msg) {
        // debug or warn or error일 경우
        if (logLevel <= ERROR) {
            System.out.print(format("ERROR", msg));
        }
    }

    @Override
    public void close() {}
}
