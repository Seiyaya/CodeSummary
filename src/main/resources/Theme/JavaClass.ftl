package ${packageName?default('cctv.bean')};

import java.io.Serializable;

public class ${className?default('')} <#if hasPage>extends BasePageBean</#if> implements Serializable {
    ${fields?default('')};
}