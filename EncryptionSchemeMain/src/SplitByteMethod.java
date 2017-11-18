// Method used to split the half blocks into 8 sub-parts.

import java.nio.ByteBuffer;
import java.util.ArrayList;


public void splitBlock(ArrayList<byte[]> halfBlock)
    {
        for(int i = 0; i<halfBlock.size();i++)
        {
            byte [] splitBytes = ByteBuffer.allocate(Byte.SIZE/8).put(halfBlock.get(i)).array();
            splitBlockArray.add(splitBytes);
        }
    }
