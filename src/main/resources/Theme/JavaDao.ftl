package ${packageName?default('cctv.dao')};

import java.util.List;

import cctv.bean.${beanName};

public interface ${beanName}Dao{

    /**
     * 添加
     * @param ${beanName?lower_case}
     */
    Integer add${beanName}(${beanName} ${beanName?lower_case});

    /**
     * 根据id查找
     * @param id
     */
    ${beanName} get${beanName}ById(Integer id);

    /**
     * 根据多个id查找实体
     * @param idList
     */
    List<${beanName}> get${beanName}sByIds(List<Integer> idList);

    /**
     * 根据id删除
     * @param id
     */
    Integer del${beanName}ById(Integer id);

    /**
     * 根据多个id删除
     * @param idList
     */
    Integer del${beanName}sByIds(List<Integer> idList);

    /**
     * 根据id更新
     * @param ${beanName?lower_case}
     */
    Integer update${beanName}ById(${beanName} ${beanName?lower_case});

    /**
     * 获取记录总数
     * @param ${beanName?lower_case}
     */
    int get${beanName}ListCount(${beanName} ${beanName?lower_case});

    <#if hasPage>
    /**
     * 分页查找
     * @param ${beanName?lower_case}
     */
    List<${beanName}> get${beanName}ListWithPage(${beanName} ${beanName?lower_case});
    </#if>
}