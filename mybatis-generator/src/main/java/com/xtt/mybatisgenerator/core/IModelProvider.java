package com.xtt.mybatisgenerator.core;

import com.xtt.mybatisgenerator.model.IModel;

/**
 * 提供模板数据模型
 * <p>File：IModelProvider.java</p>
 * <p>Title: IModelProvider</p>
 * <p>Description:IModelProvider</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: Code Farm</p>
 *
 * @author xtt
 * @version 1.0
 */
public interface IModelProvider
{
    /**
     * 得到模板的数据模型
     *
     * @return {@link IModel}
     * @throws Exception
     * @author xtt
     */
    IModel getModel() throws Exception;
}
