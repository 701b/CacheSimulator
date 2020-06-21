package cache;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCache implements Cache {

    private static class Block {
        private boolean isValid;
        private boolean isDirty;
        private int tagBit;

        public Block() {
            this.isValid = false;
            this.tagBit = 0;
        }
    }

    private static class BlockSet {
        private List<Block> blockList = new ArrayList<>();
        private int writingCount = 0;

        public BlockSet(int numBlocks) {
            for (int index = 0; index < numBlocks; index++) {
                this.blockList.add(new Block());
            }
        }

        public boolean load(int tagBit) {
            for (Block block : blockList) {
                if (block.isValid && block.tagBit == tagBit) {
                    // hit
                    blockList.remove(block);
                    blockList.add(block);

                    return true;
                }
            }

            // miss
            Block block = blockList.remove(0);

            block.isValid = true;
            block.tagBit = tagBit;
            blockList.add(block);

            if (block.isDirty) {
                writingCount++;
                block.isDirty = false;
            }

            return false;
        }

        public boolean save(int tagBit, boolean isWriteBackPolicy, boolean isWriteAllocatePolicy) {
            for (Block block : blockList) {
                if (block.isValid && block.tagBit == tagBit) {
                    // hit
                    blockList.remove(block);
                    blockList.add(block);

                    if (isWriteBackPolicy) block.isDirty = true;
                    else writingCount++;

                    return true;
                }
            }

            // miss
            if (isWriteAllocatePolicy) {
                Block block = blockList.remove(0);

                block.isValid = true;
                block.tagBit = tagBit;
                blockList.add(block);

                if (isWriteBackPolicy) {
                    if (block.isDirty) {
                        writingCount++;
                    }

                    block.isDirty = true;
                }
            } else {
                writingCount++;
            }

            return false;
        }
    }


    private List<BlockSet> setList;


    public AbstractCache() {
        this.setList = new ArrayList<>();

        for (int index = 0; index < getNumBlockSet(); index++) {
            setList.add(new BlockSet(getNumBlocks() / getNumBlockSet()));
        }
    }

    @Override
    public boolean load(long dataAddress) {
        int tag = getTag(dataAddress);
        int index = getIndex(dataAddress);
        BlockSet blockSet = setList.get(index);

        return blockSet.load(tag);
    }

    @Override
    public boolean save(long dataAddress) {
        int tag = getTag(dataAddress);
        int index = getIndex(dataAddress);
        BlockSet blockSet = setList.get(index);

        return blockSet.save(tag, isWriteBackPolicy(), isWriteAllocatePolicy());
    }

    @Override
    public int getWritingCount() {
        int writingCount = 0;

        for (BlockSet blockSet : setList) {
            writingCount += blockSet.writingCount;
        }

        return writingCount;
    }

    protected abstract int getNumBlocks();
    protected abstract int getNumBlockSet();
    protected abstract int getNumWordsPerBlock();
    protected abstract boolean isWriteAllocatePolicy();
    protected abstract boolean isWriteBackPolicy();

    private int getTag(long dataAddress) {
        return (int) (dataAddress >>> (lg(MAX_MEMORY_ADDRESS) - getTagBit()));
    }

    private int getTagBit() {
        return lg(MAX_MEMORY_ADDRESS) - (lg(BYTES_OF_WORD) + lg(getNumWordsPerBlock()) + lg(getNumBlockSet()));
    }

    private int getIndex(long dataAddress) {
        return (int) ((dataAddress & ((long) (Math.pow(2, lg(MAX_MEMORY_ADDRESS) - getTagBit()) - 1))) >>> (lg(MAX_MEMORY_ADDRESS) - (getTagBit() + getIndexBit())));
    }

    private int getIndexBit() {
        return lg(getNumBlockSet());
    }

    private int lg(int x) {
        return (int) (Math.log(x) / Math.log(2));
    }
}