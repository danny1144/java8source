##  put
put函数大致的思路为：

- 对key的hashCode()做hash，然后再计算桶的index;
- 如果没碰撞直接放到桶bucket里；
- 如果碰撞了，以链表的形式存在buckets后；
- 如果碰撞导致链表过长(大于等于TREEIFY_THRESHOLD)，就把链表转换成红黑树（若数组容量小于MIN_TREEIFY_CAPACITY，不进行转换而是进行resize操作）
- 如果节点已经存在就替换old value(保证key的唯一性)
- 如果表中实际元素个数超过阈值(超过load factor*current capacity)，就要resize


##  总结

- Java8中hash计算是通过key的hashCode()的高16位异或低16位实现的，既保证高低bit都能参与到hash的计算中，又不会有太大的开销。
- 数组大小n总是2的整数次幂，计算下标时直接( hash & n-1)
- 分配内存统一放在resize()中，包括创建后首次put时初始化数组和存放元素个数超过阈值时扩容。
- Java8引入红黑树，当链表长度达到8， 执行treeifyBin，当桶数量达到64时，将链表转为红黑树，否则，执行resize()。
- 判断Node是否符合，首先判断哈希值要相等，但因为哈希值不是唯一的，所以还要对比key是否相等，最好是同一个对象，能用＝＝对比，否则要用equals()
## 建议
- String类型的key，不能用==判断或者可能有哈希冲突时，尽量减少长度
- 在集合视图迭代的时间与桶的数量加上映射的数量成正比，若迭代性能很重要，不要设置太高的初始容量或过小的负载因子
- 如果映射很多，创建HashMap时设置充足的初始容量(预计大小/负载因子 + 1）会比让其自动扩容获得更好的效率，一方面减少了碰撞可能，另一方面减少了resize的损耗
- 迭代器是fail-fast的，迭代器创建后如果进行了结构修改（增加或删除一个映射）且不是使用iterator的remove方法，会努力抛出ConcurrentModificationException，所以不能依赖该异常保证程序运行正确，而只可用于检测bug



 **其中tableSizeFor(initialCapacity)返回最近的不小于输入参数的2的整数次幂。比如10，则返回16**
```
static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
 

```

原理如下：
**先说5个移位操作，会使cap的二进制从最高位的1到末尾全部置为1**


- 假设cap的二进制为01xx…xx。
- 对cap右移1位：01xx…xx，位或：011xx…xx，使得与最高位的1紧邻的右边一位为1，
- 对cap右移2位：00011x..xx，位或：01111x..xx，使得从最高位的1开始的四位也为1，
- 以此类推，int为32位，所以在右移16位后异或最多得到32个连续的1，保证从最高位的1到末尾全部为1。

- 最后让结果+1，就得到了最近的大于cap的2的整数次幂。


- int n = cap - 1;

- 让cap-1再赋值给n的目的是令找到的目标值大于或等于原值。如果cap本身是2的幂，如8（1000(2)），不对它减1而直接操作，将得到16。

- 通过tableSizeFor()，保证了HashMap容量始终是2的次方，在通过hash寻找index时就可以用逻辑运算来替代取余，即hash％n用hash&(n -1)替代

