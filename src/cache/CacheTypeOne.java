package cache;

public class CacheTypeOne extends AbstractCache {

    @Override
    protected int getNumBlocks() {
        return 1024;
    }

    @Override
    protected int getNumBlockSet() {
        return 512;
    }

    @Override
    protected int getNumWordsPerBlock() {
        return 16;
    }

    @Override
    protected boolean isWriteAllocatePolicy() {
        return true;
    }

    @Override
    protected boolean isWriteBackPolicy() {
        return true;
    }

}
