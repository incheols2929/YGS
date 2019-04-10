package geomex.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * 로그메세지를 파일로 기록하는 클래스 <br>
 * Queue에 로그데이터를 임시로 저장하며 Thread가 Queue의 데이터를 꺼내어 파일에 기록한다.<br>
 * 로그화일은 일별로 저장된다.<br>
 * 로그화일 예) 로그파일명2007-02-20.txt
 * 
 * @author 김 경 호(geovlet@naver.com)
 */
public class FileLogger extends Logger implements Runnable {

    private Queue<String> queue;
    private String logPath = "/";
    private String prefix = "Log";
    private boolean isRun = true;
    private boolean withConsole = false;
    private Thread worker = null;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(200);
        sb.append("prefix=").append(prefix);
        sb.append(" ,level= ").append(logLevel);
        sb.append(" ,console=").append(withConsole);
        sb.append(" ,path=").append(logPath);
        return sb.toString();
    }

    /**
     * 생성자
     * 
     * @param logPath 로그기록 경로
     * @param prefix 화일접두사
     * @param level 로그레벨
     * @param withConsole 콘솔에 출력할지 여부
     */
    public FileLogger(String logPath, String prefix, int level,
        boolean withConsole) {
        queue = new Queue<String>(50);
        this.logPath = logPath;
        this.prefix = prefix;
        this.withConsole = withConsole;
        this.logLevel = level;
        worker = new Thread(this, "[" + prefix + "FileLogger]");
        worker.start();
    }

    /**
     * 표준 출력 장치인 console에 출력할 것인지 여부를 설정한다.
     * 
     * @param withConsole
     */
    public synchronized void setWithConsole(boolean withConsole) {
        this.withConsole = withConsole;
    }

    /**
     * Log를 기록할 경로 지정
     * 
     * @param path
     */
    public synchronized void setPath(String path) {
        logPath = path;
    }

    /**
     * 로그화일 접두사명을 지정한다.
     * 
     * @param fName
     */
    public synchronized void setPrefix(String fName) {
        prefix = fName;
    }

    @Override
    public void debug(String msg) {
        if (getLevel() == DEBUG) {
            queue.add(format("DEBUG", msg));
        }
    }

    @Override
    public void info(String msg) {
        if (getLevel() <= INFO) {
            queue.add(format("INFO", msg));
        }
    }

    @Override
    public void warn(String msg) {
        // debug or warn 경우
        if (getLevel() <= WARN) {
            queue.add(format("WARN", msg));
        }
    }

    @Override
    public void error(String msg) {
        // debug or warn or error일 경우
        if (getLevel() <= ERROR) {
            queue.add(format("ERROR", msg));
        }
    }

    /**
     * Queue에 저장된 LogData를 자동으로 꺼내어 파일로 기록한다. <br>
     * 날짜가 바뀌면 자동으로 날짜화일 생성
     */

    @Override
    public void run() {
        // info(prefix + " Logger Started.");
        PrintWriter out = null;
        String oldFileName = "";
        StringBuilder sb = new StringBuilder(100);

        while (isRun && Thread.currentThread() == worker) {
            // 현재 년월일을 구한다.
            String day = DateUtil.getStrDay();
            String year = day.substring(0, 4);
            try {
                sb.setLength(0);
                // 해당년의 디렉토리가 없으면 년도 디렉토리를 생성한다.
                File f = new File(sb.append(logPath).append(File.separator)
                    .append(year).toString());
                if (!f.exists()) {
                    f.mkdirs();
                }
                // 로그를 기록할 전체 파일명을 생성한다.
                sb.setLength(0);
                String logFile = sb.append(f.getAbsolutePath())
                    .append(File.separator).append(prefix).append("_")
                    .append(day).append(".txt").toString();

                // 새로생성한 화일명이 이전화일명과 같지 않으면 Writer를 새로생성한다.
                if (!logFile.equalsIgnoreCase(oldFileName)) {
                    closeWriter(out);
                    out = new PrintWriter(new FileWriter(logFile, true));
                    oldFileName = logFile;
                }
                // Queue에서 로그데이터를 꺼낸다
                if (queue == null)
                    break;
                String msg = queue.remove();
                if (msg != null) {
                    out.println(msg);
                    out.flush();
                    if (withConsole) {
                        System.out.println(msg);
                    }
                }
            } catch (Exception e) {
                closeWriter(out);
                oldFileName = "";
            }
        }
        closeWriter(out);
        // info(prefix + " Logger Stoped.");
    }

    /**
     * PrintWriter를 close한다.
     * 
     * @param out
     */
    private void closeWriter(PrintWriter out) {
        try {
            out.close();
        } catch (Exception a) {}
        out = null;
    }

    @Override
    public synchronized void close() {
        this.isRun = false;
        try {
            queue.clear();
            queue.notifyAll();
        } catch (Exception e) {}

        try {
            worker.notifyAll();
        } catch (Exception e) {}
    }
}
