/**
 * Student Name  : Moon Taeui
 * Student ID No : 2016312021
 */

package cache;

public class DataInstruction {

    private long dataAddress;
    private boolean isLoad;


    public DataInstruction(long dataAddress, boolean isLoad) {
        this.dataAddress = dataAddress;
        this.isLoad = isLoad;
    }


    public long getDataAddress() {
        return dataAddress;
    }

    public boolean isLoad() {
        return isLoad;
    }
}

