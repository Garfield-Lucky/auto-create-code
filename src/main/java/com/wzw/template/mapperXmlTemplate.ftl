<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${package}.mapper.${entityName}Mapper">

    <sql id="Base_Column_List">

     ${columnName}
    </sql>

    <select id="qry${entityName}List" resultType="${package}.entity.${entityName}">
        SELECT <include refid="Base_Column_List" />
        FROM  ${tableName}
    </select>

</mapper>
