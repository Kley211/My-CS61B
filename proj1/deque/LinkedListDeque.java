package deque;

import java.util.Iterator;
import java.util.Objects;

public class LinkedListDeque<T> implements Deque<T>
{
    //as a unit for processing
    public class Node
    {
        public T item;
        public Node prev;
        public Node next;

        public Node(T item,Node prev,Node next)
        {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }

    }

    private int size;
    private Node sentinel;

    //No-argument constructor
    public LinkedListDeque()
    {
        size = 0;
        sentinel = new Node(null,null,null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    //创建一个结点，其前指向哨兵，后指向原哨兵的后面，修改哨兵和其后面结点的指向
    @Override
    public void addFirst(T item)
    {
        Node i = new Node(item,sentinel,sentinel.next);
        sentinel.next.prev = i;
        sentinel.next = i;
        size += 1;
    }

    //思路同上
    @Override
    public void addLast(T item)
    {
        Node i = new Node(item,sentinel.prev,sentinel);
        sentinel.prev.next = i;
        sentinel.prev = i;
        size += 1;
    }


    @Override
    public int size()
    {
        return size;
    }

    @Override
    public void printDeque()
    {
        System.out.print("(");
        for(int i = 0; i < this.size();i++)
        {
            System.out.print(get(i) + " ");
        }
        System.out.print(")");
        System.out.println();
    }
    //修改First,sentinel,First.next的指向，将First从队列中剔除
    @Override
    public T removeFirst()
    {
        if(size <= 0){return null;}

            Node Frist = new Node(sentinel.next.item, sentinel, sentinel.next.next);
            T removeitem = Frist.item;
            Frist.next.prev = sentinel;
            sentinel.next = Frist.next;

            Frist.next = Frist.prev = null;
            size -= 1;
            return removeitem;


    }

    @Override
    public T removeLast()
    {
        if(size <= 0) {return null;}
        Node Last = sentinel.prev;
        T removeitem = Last.item;
        sentinel.prev = Last.prev;
        Last.prev.next = sentinel;

        Last.prev = Last.next = null;
        size -= 1;
        return removeitem;
    }

    //迭代得到index处的数据，注意范围
    @Override
    public T get(int index)
    {
        if(index > size - 1 || index < 0)
        {
            return null;
        }
        Node iterate = sentinel.next;
        for(int i = 0; i < index;i++)
        {
            iterate = iterate.next;
        }
        return iterate.item;
    }



    //辅助方法，是实现递归获取的具体方法，但不暴露给外部
    private T getRecursive(Node start, int index) {
        if (index == 0) {
            return start.item;
        } else {
            return getRecursive(start.next, index - 1);
        }
    }

    //外界可见的递归获取方法，只处理边界情况，一般情况交给内部方法处理，
    public T getRecursive(int index) {
        if (index > size - 1) {
            return null;
        }
        return getRecursive(sentinel.next, index);
    }

    public Iterator<T> iterator()
    {
        return new LinkedListDequeIterator();
    }

    //why LinkedListDequeIterator haven't <T>???
    //because this class is a subclass,it inherits LinkedListDeque's <T>，if you write<T>,it will override previous <T>.
    public class LinkedListDequeIterator implements Iterator<T>
    {
        private int i;

        public LinkedListDequeIterator()
        {
            i = 0;
        }

        @Override
        public boolean hasNext()
        {
            return i < size;
        }

        @Override
        public T next()
        {
            T returnitem = get(i);
            i += 1;
            return returnitem;
        }


    }
    @Override
    public boolean equals(Object o)
    {
        if(o == this){return true;}

        if(!(o instanceof Deque) || ((Deque<?>) o).size() != size)
        {
            return false;
        }

        for(int i = 0; i < size; i++)
        {
            Object k = ((Deque<?>) o).get(i);
            if(!(this.get(i).equals(k)))
            {
                return false;
            }

        }
        return true;
    }

}