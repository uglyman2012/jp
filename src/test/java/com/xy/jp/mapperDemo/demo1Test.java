package com.xy.jp.mapperDemo;

import com.xy.jp.bean.Student;
import com.xy.jp.service.mapperDemo.Chapter;
import com.xy.jp.service.mapperDemo.ChapterMapper;
import com.xy.jp.service.mapperDemo.ChapterModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yang
 * @since 2020/11/10
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class demo1Test {
    @Autowired
    private ChapterMapper chapterMapper;
    @Test
   public void test12(){
        Chapter chapter = new Chapter();
        chapter.setId(99L);
        chapter.setParentId(55L);
        chapter.setDescription("pp");
        chapter.setName("小丽");
        chapter.setSortNum(996);
        Student student = new Student();
        student.setAge(11);
        student.setName("ppp");

        Chapter chapter2 = new Chapter();
        chapter2.setId(99L);
        chapter2.setParentId(55L);
        chapter2.setDescription("pp");
        chapter2.setName("小丽");
        chapter2.setSortNum(996);
        chapter.setStudent(student);
        List<Chapter> list = new ArrayList<>();
        list.add(chapter);
        list.add(chapter2);
        ////ChapterModel chapterModel = chapterMapper.entityToModel(chapter);
        ////ChapterModel chapterModel = chapterMapper.entityToModel2(chapter,"小刚刚");
        List<ChapterModel> chapterModel = chapterMapper.entityToModel3(list);
        System.out.println(chapterModel.toString());

    }
     @Test
     public void test13(){
         Student student = new Student();
         int age = student.getAge();
         Integer  a=1;
         if (a+age>0){
             System.out.println("========");
         }

     }
}
