import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    class DLinkedNode{
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
        public DLinkedNode(){}
        public DLinkedNode(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    DLinkedNode dummyHead;
    DLinkedNode dummyTail;

    int capacity;
    int size;

    Map<Integer, DLinkedNode> cache;

    public LRUCache(int capacity){
        cache = new HashMap<Integer, DLinkedNode>();
        this.capacity = capacity;
        size = 0;
        dummyHead = new DLinkedNode();
        dummyTail = new DLinkedNode();
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }

    public int get(int key){
        DLinkedNode temp = cache.get(key);
        if(temp == null){
            return -1;
        }
        moveToHead(temp);
        return temp.value;
    }
    public void put(int key, int value){
        DLinkedNode temp = cache.get(key);
        if(temp == null){
            temp = new DLinkedNode(key, value);
            addToHead(temp);
            cache.put(key, temp);
            ++size;
            if(size>capacity){
                removeTail();
                --size;
            }

        }else{
            temp.value = value;
            moveToHead(temp);
        }
    }
    public void addToHead(DLinkedNode node){
        node.next = dummyHead.next;
        node.next.prev = node;
        dummyHead.next = node;
        node.prev = dummyHead;
    }
    public void moveToHead(DLinkedNode node){
        removeNode(node);
        addToHead(node);
    }
    public void removeTail(){
        DLinkedNode tail = dummyTail.prev;
        int key = tail.key;
        cache.remove(key);
        dummyTail.prev = tail.prev;
        dummyTail.prev.next = dummyTail;
    }
    public void removeNode(DLinkedNode node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public static void main(String[] args) {
        LRUCache l = new LRUCache(2);
        l.put(1,1);
        l.put(2,2);
        int a = l.get(1);
        System.out.println(a);

    }
}

