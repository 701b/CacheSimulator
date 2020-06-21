/**
 * Student Name  : Moon Taeui
 * Student ID No : 2016312021
 */

package cache;

public interface Cache {

    public static final int BYTES_OF_WORD = 4;
    public static final int MAX_MEMORY_ADDRESS = 0xFFFFFFFF;

    public boolean save(long dataAddress);
    public boolean load(long dataAddress);
    public int getWritingCount();

}
