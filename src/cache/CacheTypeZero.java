/**
 * Student Name  : Moon Taeui
 * Student ID No : 2016312021
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
