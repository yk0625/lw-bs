package com.xtt.mybatisgenerator.model;

import java.util.Map;

/**
 * 模板数据模型
 * <p>File：IModel.java</p>
 * <p>Title: IModel</p>
 * <p>Description:IModel</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: Code Farm</p>
 * 
 * @author xtt
 * @version 1.0
 */
public interface IModel
{
    /**
     * 获取描述信息
     * 
     * @author xtt
     * @return
     */
    String getDisplayDescription();
    
    /**
     * 为模板路径提供数据
     * 
     * @author xtt
     * @param fileModel
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    void mergeFilePathModel(Map fileModel) throws Exception;
    
    /**
     * 获取模型名称
     * 
     * @author xtt
     * @return
     */
    String getTemplateModelName();
}
