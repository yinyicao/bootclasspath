package io.github.alphahinex.bootclasspath.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDAO {

    int customCount();

}
