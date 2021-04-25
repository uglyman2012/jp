package com.xy.jp.service.mapperDemo;

import com.xy.jp.bean.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yang
 * @since 2020/11/10
 */
@Mapper(componentModel = "spring",uses =Student.class)
public  interface ChapterMapper {
    //@Mappings({ @Mapping(source = "chapter.name", target = "groupName"),@Mapping(source = "chapter.id", target = "fid",qualifiedByName ="changeTos2" ) })
    //@Mapping(source = "chapter.parentId", target = "parentName",qualifiedByName ="changeTos" )
    //ChapterModel entityToModel(Chapter chapter);
    @Mapping(target = "groupName",source = "groupName")
    ChapterModel entityToModel2(Chapter chapter,String groupName);

    List<ChapterModel> entityToModel3(List<Chapter> chapter);
    @Named("changeTos")
    default  String changeTos(Long parentIds) {
        if (parentIds != null) {
            return "998ppp";
        }
        return null;
    }
    @Named("changeTos2")
    default  String changeTos2(Long parentIds) {
        if (parentIds != null) {
            return "9988";
        }
        return null;
    }
}
