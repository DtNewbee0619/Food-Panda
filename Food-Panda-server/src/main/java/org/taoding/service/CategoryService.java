package org.taoding.service;

import org.taoding.dto.CategoryDTO;
import org.taoding.dto.CategoryPageQueryDTO;
import org.taoding.entity.Category;
import org.taoding.result.PageResult;

import java.util.List;

/**
 * @Date 6/25/24 11:50
 * @Author Tao Ding
 * @Description: TODO
 */
public interface CategoryService {

    /**
     * 新增分类
     * @param categoryDTO
     */
    void save(CategoryDTO categoryDTO);

    /**
     * 分页查询分类
     * @param categoryPageQueryDTO
     * @return
     */
    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * 根据id删除分类
     * @param id
     */
    void deleteById(Long id);

    /**
     * 更新分类
     * @param categoryDTO
     */
    void update(CategoryDTO categoryDTO);

    /**
     * 启用禁用分类
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);

    /**
     * 类型查询分类
     * @param type
     * @return
     */
    List<Category> list(Integer type);
}
