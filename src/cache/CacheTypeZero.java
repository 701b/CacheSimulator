/**
 * Student Name  : 2016312021
 * Student ID No.: Moon Taeui
 */

package cache;

/**
 *
 */
public class CacheTypeZero extends AbstractCache {

    @Override
    protected int getNumBlocks() {
        return 1024;
    }

    @Override
    protected int getNumBlockSet() {
        return 1024;
    }

    @Override
    protected int getNumWordsPerBlock() {
        return 4;
    }

    @Override
    protected boolean isWriteAllocatePolicy() {
        return false;
    }

    @Override
    protected boolean isWriteBackPolicy() {
        return false;
    }

}
