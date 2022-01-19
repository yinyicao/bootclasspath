package io.github.alphahinex.bootclasspath.dao;

import io.github.alphahinex.bootclasspath.entity.CountryEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CountryDAO {

    List<CountryEntity> cc();

}
