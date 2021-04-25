package com.xy.jp.service.mapperDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author yang
 * @since 2020/11/11
 */
@RestController
public class mapperController {
    @Autowired
    private ChapterMapper chapterMapper;
}
