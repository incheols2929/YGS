package geomex.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * HashMap를 이용하는 Logger를 등록하고 관리하는 클래스
 * 
 * @author 김 경 호(geovlet@naver.com)
 */
public class LogManager {
    static private volatile LogManager manager = null;
    private Map<String, Logger> loggers = null;

    /**
     * 멀티 Thread상황에서 유효한 싱글톤 DCL(Double-checked Locking) <br>
     * 반드시 자바 5 버전 이상의 JVM 환경에서 인스턴스 변수에 volatile 키워드를 사용해야만 한다
     * 
     * @return LogManager
     */
    public static LogManager getManager() {
        if (manager == null) {
            synchronized (LogManager.class) {
                if (manager == null) {
                    manager = new LogManager();
                }
            }
        }
        return manager;
    }

    /**
     * 생성자
     */
    private LogManager() {
        // WeakHashMap쓰지말것 절대로... 김경호
        loggers = Collections.synchronizedMap(new HashMap<String, Logger>());
    }

    /**
     * Logger를 등록한다.
     * 
     * @param name 로거이름
     * @param s Logger 객체
     */
    public void addLogger(String name, Logger s) {
        loggers.put(name, s);
    }

    /**
     * 로거 사이즈를 얻는다
     * 
     * @return int : 로거 사이즈
     */
    public int size() {
        return loggers.size();
    }

    /**
     * 주어진 name키로 등록된 Logger를 리턴한다. <br>
     * 만약 등록된 Logger가 없으며 return 생성하고 반환한다.
     * 
     * @param name 로거이름
     * @return Logger : 로거가 존재한다. <br>
     *         null : 해당이름의 로거가 없다.
     */
    public Logger getLogger(String name) {
        return loggers.get(name);
    }

    /**
     * LogManager를 정리한다.
     */
    public synchronized void destroy() {
        try {
            Iterator<Logger> it = loggers.values().iterator();
            while (it.hasNext()) {
                (it.next()).close();
            }
            System.out.println("LogManager Destroyed.");
        } catch (Exception e) {}
        loggers.clear();
        loggers = null;
    }
}
