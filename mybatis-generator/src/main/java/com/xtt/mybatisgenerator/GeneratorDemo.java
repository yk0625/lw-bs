package com.xtt.mybatisgenerator;

import com.xtt.mybatisgenerator.core.GeneratorFile;

/**
 * com.blocain.bitms.generator.GeneratorDemo Introduce
 * <p>File：com.blocain.bitms.generator.GeneratorDemo.java </p>
 * <p>Title: com.blocain.bitms.generator.GeneratorDemo </p>
 * <p>Description:com.blocain.bitms.generator.GeneratorDemo </p>
 * <p>Copyright: Copyright (c) 17/6/16</p>
 * <p>Company: Code Farm</p>
 *
 * @author xtt
 * @version 1.0
 */
public class GeneratorDemo
{
    public static void main(String[] args) throws Exception
    {
        GeneratorFile file = new GeneratorFile();
        file.generateCRUDByTables(new String[]{"order"});
    }
}
