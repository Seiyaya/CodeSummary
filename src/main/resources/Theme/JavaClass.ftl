package ${packageName?default('com.seiyaya.bean')};

import java.io.Serializable;

public class ${className?default('')} <#if hasPage>extends BasePageBean</#if> implements Serializable {
    ${fields?default('')};
}