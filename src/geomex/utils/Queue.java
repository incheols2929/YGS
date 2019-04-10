package geomex.utils;

/**
 * Queue클래스
 * 
 * @author 김 경 호(geovlet@naver.com)
 */
public class Queue<E> {

    private transient E[] elementData;
    private int capacity;
    private int dataSize;
    private final long waitTime = 100L;
    private boolean enabled = true;

    private int front;
    private int rear;

    /**
     * 생성자 initialCapacity = 10
     */
    public Queue() {
        this(10);
    }

    /**
     * 생성자
     * 
     * @param initialCapacity 초기 저장소 사이즈
     */
    @SuppressWarnings("unchecked")
    public Queue(int initialCapacity) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal Capacity: "
                + initialCapacity);
        elementData = (E[]) new Object[initialCapacity];
        capacity = initialCapacity;
    }

    /**
     * 데이터 갯수를 얻는다.
     * 
     * @return int : 데이터 갯수
     */
    public synchronized int size() {
        return dataSize;
    }

    /**
     * Empty여부를 검사한다.
     * 
     * @return true : 데이터가 없다. <br>
     *         false : 데이터가 있다.
     */
    public synchronized boolean isEmpty() {
        return dataSize == 0;
    }

    /**
     * 저장소를 체크하여 부족하면 저장소를 자동으로 늘린다.
     * 
     * @param minCapacity 요구 크기
     */
    @SuppressWarnings("unchecked")
    private void ensureCapacity(int minCapacity) {
        int oldCapacity = elementData.length;
        if (minCapacity > oldCapacity) {
            int newCapacity = (oldCapacity * 3) / 2 + 1;
            if (newCapacity < minCapacity)
                newCapacity = minCapacity;
            E[] newData = (E[]) new Object[newCapacity];
            int x = 0;
            for (x = 0; x < capacity; x++) {
                E obj = peek();
                if (obj == null)
                    break;
                newData[x] = obj;
            }
            elementData = newData;
            capacity = elementData.length;
            rear = 0;
            front = x;
            dataSize = front + 1;
        }
    }

    /**
     * 데이터를 추가한다.
     * 
     * @param e 추가할 데이터
     */
    public synchronized void add(E e) {
        if (e == null)
            return;
        dataSize++;
        if ((front + 1) % capacity == rear) {
            ensureCapacity(capacity + 1);
        }
        elementData[front] = e;
        front = (front + 1) % capacity;
        try {
            notifyAll();
        } catch (Exception ex) {}
    }

    /**
     * 데이터를 꺼낸다. <br>
     * 데이터가 없다면 데이터가 추가 될때까지 기다리며 <br>
     * 데이터가 추가되는 즉시 데이터를 꺼내 반환한다.
     * 
     * @return E 저장된 데이터
     */
    public synchronized E remove() {
        while (enabled) {
            E answer = peek();
            if (answer != null) {
                return answer;
            }
            try {
                wait(waitTime);
            } catch (InterruptedException e) {}
        }
        return null;
    }

    /**
     * 데이터를 꺼낸다. <br>
     * 데이터가 없다면 데이터 추가를 기다린다. <br>
     * 만약 timeout동안 데이터 추가가 없으면 null을 반환하고 <br>
     * 데이터 추가가 있으면 해당 데이터를 반환한다.
     * 
     * @param timeout 데이터 추가 대기시간
     * @return E : 저장된 데이터 <br>
     *         null : 데이터가 없다.
     */
    public synchronized E remove(long timeout) {
        E answer = peek();
        if (answer == null) {
            try {
                wait(timeout);
            } catch (InterruptedException e) {}
            answer = peek();
        }
        return answer;
    }

    /**
     * 저장된 데이터를 꺼낸다. 꺼낼 데이터가 없으면 null을 반환한다.
     * 
     * @return E : 저장 데이터 <br>
     *         null : 데이터가 없다
     */
    private E peek() {
        if (front == rear)
            return null;
        E oldValue = elementData[rear];
        if (oldValue != null) {
            dataSize--;
            elementData[rear] = null;
            rear = (rear + 1) % capacity;
        }
        return oldValue;
    }

    /**
     * 데이터를 즉시 반환한다. 데이터가 없으면 null을 반환한다. <br>
     * 가장 처음에 저장된 데이터를 반환한다.(FIFO)
     * 
     * @return E : 저장 데이터 <br>
     *         null : 데이터가 없다
     */

    public synchronized E removeNoWait() {
        return peek();
    }

    /**
     * 저장된 모든 데이터를 제거한다.
     */
    public synchronized void clear() {
        enabled = false;
        for (int i = 0; i < elementData.length; i++) {
            elementData[i] = null;
        }
        dataSize = front = rear = 0;
        try {
            notifyAll();
        } catch (Exception e) {}
    }
    /**
     * <pre>
     * private void dump() {
     *     System.out.println(&quot;size &quot; + size());
     *     for (int x = 0; x &lt; elementData.length; x++) {
     *         System.out.print(&quot;/&quot; + elementData[x]);
     *     }
     *     System.out.println(&quot;&quot;);
     * }
     * 
     * public static void main(String a[]) {
     *     Queue&lt;String&gt; q = new Queue&lt;String&gt;(5);
     *     q.add(new String(&quot;1&quot;));
     *     q.add(new String(&quot;2&quot;));
     *     q.add(new String(&quot;3&quot;));
     *     q.add(new String(&quot;4&quot;));
     *     q.dump();
     *     System.out.println(&quot;get &quot; + q.removeNoWait());
     *     System.out.println(&quot;get &quot; + q.removeNoWait());
     *     q.dump();
     *     q.add(new String(&quot;5&quot;));
     *     q.dump();
     *     q.add(new String(&quot;6&quot;));
     *     q.dump();
     *     q.add(new String(&quot;7&quot;));
     *     q.dump();
     *     q.add(new String(&quot;8&quot;));
     *     q.dump();
     * }
     * </pre>
     */

}
