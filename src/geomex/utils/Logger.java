package geomex.utils;

/**
 * Logger Abstract 클래스
 * 
 * @author 김 경 호(geovlet@naver.com)
 */
public abstract class Logger {
    public final static int DEBUG = 10;
    public final static int INFO = 20;
    public final static int WARN = 30;
    public final static int ERROR = 40;

    protected int logLevel = DEBUG; // Default 로그 기록 레벨

    /**
     * 로그기록이 가능한지 여부를 판단한다.
     * 
     * @param logger Logger
     * @param reqLevel 기록할 로그 레벨
     * @return true: Logger에 설정된 로그레벨이 요구되는 로그레벨 보다 작다 <br>
     *         false: 기록할수 없는(무시되는) 로그레벨 요청이다.
     */
    public static boolean canLog(Logger logger, int reqLevel) {
        return (logger.getLevel() <= reqLevel);
    }

    /**
     * 로그기록 레벨을 설정한다. <br>
     * DEBUG : DEBUG,INFO,WARN,ERROR 메세지를 기록한다. <br>
     * INFO : ERROR,WARN,INFO 메세지를 기록한다. <br>
     * WARN : WARN, ERROR 메세지를 기록한다. <br>
     * ERROR : ERROR 메세지를 기록한다. <br>
     * 
     * @param level 로그기록 레벨
     */

    public synchronized void setLevel(int level) {
        this.logLevel = level;
    }

    /**
     * 레벨명 을 숫자로 변환하낟.
     * 
     * @param level 로그레벨 문자명
     * @return 레벨 숫자
     */
    public static int getLevel(String level) {
        if (level.equalsIgnoreCase("DEBUG")) {
            return Logger.DEBUG;
        } else if (level.equalsIgnoreCase("WARN")) {
            return Logger.WARN;
        } else if (level.equalsIgnoreCase("ERROR")) {
            return Logger.ERROR;
        } else if (level.equalsIgnoreCase("INFO")) {
            return Logger.INFO;
        }
        return DEBUG;
    }

    /**
     * 로그기록 레벨을 얻는다
     * 
     * @return int : 로그기록 레벨
     */
    public synchronized int getLevel() {
        return this.logLevel;
    }

    /**
     * 로기 타입을 지정하여 로그를 기록한다.
     * 
     * @param level 로그타입(DEBUG,INFO,WARN,ERROR)
     * @param msg 로그메세지
     */
    public void log(int level, String msg) {
        switch (level) {
            case DEBUG:
                debug(msg);
                break;
            case INFO:
                info(msg);
                break;
            case WARN:
                warn(msg);
                break;
            case ERROR:
                error(msg);
                break;
        }
    }

    /**
     * 로그문자열 포맷으로 변환한다.
     * 
     * @param level 로그레벨
     * @param msg 메세지
     * @return 포맷문자열
     */
    protected String format(String level, String msg) {
        return new StringBuilder(512).append("[").append(level).append("] ")
            .append(DateUtil.formatDate(DateUtil.getStrSec()))
            .append(" >> ").append(StrUtil.removeCRLF(msg)).toString();
    }

    /**
     * 로거를 close 한다.
     */
    public abstract void close();

    /**
     * 디버그 메세지를 형식변환하여 기록한다. <br>
     * 로그기록 레벨이 DEBUG일 경우만 기록한다.
     * 
     * @param msg 디버그 메세지
     */
    public abstract void debug(String msg);

    /**
     * 에러 메세지를 형식변환하여 기록한다. <br>
     * 로그기록 레벨이 DEBUG,INFO,WARN일 경우만 기록한다.
     * 
     * @param msg 에러 메세지
     */
    public abstract void error(String msg);

    /**
     * 정보 메세지를 형식변환하여 기록한다. <br>
     * 로그기록 레벨이 DEBUG,INFO일 경우만 기록한다.
     * 
     * @param msg 정보 메세지
     */
    public abstract void info(String msg);

    /**
     * 경고 메세지를 형식변환하여 기록한다. <br>
     * 로그기록 레벨이 DEBUG,INFO,WARN일 경우만 기록한다.
     * 
     * @param msg 경고 메세지
     */
    public abstract void warn(String msg);

}
