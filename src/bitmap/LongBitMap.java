package bitmap;

/**
 * @author weizheng
 * @Description BitMap 的实现
 * @date 2022/01/29
 */
public class LongBitMap {
    private long[] bits;

    public LongBitMap(int max) {
        // 每个long类型能存64位，数组大小至少为1，所以要存储max个数需要的long个数为(max+64)/64
        this.bits = new long[(max + 64) / 64];
    }

    public void add(int num) {
        // num >> 6 等价于 num/64 ，确定添加的数在数组的哪个位置
        // num & 63 等价于 num%64 , 确定添加的数在目标long的哪一个bit位
        // | 用于将目标bit位变为1
        bits[num >> 6] |= (1L << (num & 63));
    }

    public void delete(int num) {
        bits[num >> 6] &= ~(1L << (num & 63));
    }

    public boolean contains(int num) {
        return (bits[num >> 6] & (1L << (num & 63))) != 0;
    }

}
