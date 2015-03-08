package cn.pzhu.jsj.pts.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.pzhu.jsj.pts.dto.MenuDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/*.xml"})
public class MenuServiceTest {
	 @Autowired
	 private MenuService menuService;

    @Test
    public void menu(){
    	List<MenuDto> menus = menuService.findByRole("ROLE_TEACHER");
    	for(MenuDto dto : menus){
    		System.out.println(dto.getName());
    		for(MenuDto d : dto.getMenus()){
    			System.out.println("\t"+d.getName()+": "+d.getUrl());
    		}
    	}
    }
    
}
