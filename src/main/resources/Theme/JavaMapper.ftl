<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${package}${beanName}Mapper">
    <resultMap id="${beanName?lower_case}" type="${beanName}">
        <#list properties as property>
        <result property="${property.colName}" column="${property.colName}"></result>
        </#list>
    </resultMap>

    <sql id="Base_Column_List">
    <#list properties as property>${property.colName}<#if property_has_next>,</#if></#list>
    </sql>

    <!-- Select SQL -->
    <sql id="${beanName?lower_case}Selector">
        SELECT
        <if test="fields != null">
        ${r'${fields}'}
        </if>
        <if test="fields == null">
        <include refid="Base_Column_List"/>
        </if>
        FROM ${tableName}
    </sql>

    <sql id="${beanName?lower_case}ListWhere">
        <where>
        <#list properties as property>
            <if test="${property.colName} != null">
                and ${property.colName}=${r'#{'}${property.colName}${r'}'}
            </if>
        </#list>
        </where>
    </sql>

    <sql id="${beanName?lower_case}ListLimit">
        <if test="startRow != null">
            limit ${r'#{'}startRow${r'}'},${r'#{'}pageSize${r'}'}
        </if>
    </sql>

    <!-- 根据主键查询 -->
    <select id="get${beanName}ById" resultMap="${beanName?lower_case}" parameterType="hashMap">
        SELECT
        <include refid="Base_Column_List"/>
        FROM ${tableName}
        WHERE
        ${beanName?lower_case}Id=${r'#{'}${beanName?lower_case}Id${r'}'}
    </select>
    <select id="get${beanName}sByIds" resultMap="${beanName?lower_case}" parameterType="ArrayList">
        SELECT <include refid="Base_Column_List"/> FROM ${tableName}
        WHERE
        ${beanName?lower_case}Id in
        <foreach collection="list" separator="," item="id" open="(" close=")">
        ${r'#{id}'}
        </foreach>
    </select>

    <!-- 根据主键删除 -->
    <delete id="del${beanName}ById" parameterType="hashMap">
        DELETE FROM ${tableName}
        WHERE
        ${beanName?lower_case}Id=${r'#{'}${beanName?lower_case}Id${r'}'}
    </delete>

    <!-- 根据主键批量删除 -->
    <delete id="del${beanName}sByIds">
        DELETE FROM ${tableName}
        WHERE
        ${beanName?lower_case}Id in
        <foreach collection="list" separator="," item="id" open="(" close=")">
        ${r'#{id}'}
        </foreach>
    </delete>

    <!-- 根据主键更新 -->
    <update id="update${beanName}ById" parameterType="${beanName}">
        UPDATE ${tableName}
        <set>
            <trim suffixOverrides=",">
            <#list properties as property>
                <if test="${property.colName} != null">
                    ${property.colName} = ${r'#{'}${property.colName}${r'}'},
                </if>
            </#list>
            </trim>
        </set>
        WHERE
        ${beanName?lower_case}Id=${r'#{'}${beanName?lower_case}Id${r'}'}
    </update>
    <!-- 分页 -->
    <select id="get${beanName}ListWithPage" parameterType="${beanName}" resultMap="${beanName?lower_case}">
        <include refid="${beanName?lower_case}Selector" />
        <include refid="${beanName?lower_case}ListWhere" />
        <include refid="${beanName?lower_case}ListLimit" />
    </select>
    <!-- 数据总数 -->
    <select id="get${beanName}ListCount" parameterType="${beanName}" resultType="int">
        SELECT count(1) FROM ${tableName}
        <include refid="${beanName?lower_case}ListWhere" />
    </select>
    <!-- 数据集合 -->
    <select id="get${beanName}List" parameterType="${beanName}" resultMap="${beanName?lower_case}">
        <include refid="${beanName?lower_case}Selector" />
        <include refid="${beanName?lower_case}ListWhere" />
    </select>
    <!-- 添加 -->
    <insert id="add${beanName}" parameterType="${beanName}">
        INSERT INTO ${tableName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
        <#list properties as property>
            <if test="${property.colName} != null">
            ${property.colName},
            </if>
        </#list>
        </trim>
        VALUES
        <trim prefix="(" suffix=")" suffixOverrides=",">
        <#list properties as property>
            <if test="${property.colName} != null">
            ${r'#{'}${property.colName}${r'}'},
            </if>
        </#list>
        </trim>
    </insert>
</mapper>
