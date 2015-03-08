package cn.pzhu.jsj.pts.common;


/**
 * Created by IntelliJ IDEA.
 * User: cxw
 * Date: 11-7-27
 * Time: 下午1:12
 * To change this template use File | Settings | File Templates.
 */
public class PageJsonUtil {

    public static Integer getTotal(Integer records, Integer row) {
        Integer total = records / row;
        if (total * row < records) {
            total++;
        }
        return total;
    }

    public static Integer getPage(Integer page, Integer total) {
        if (page <= 0) {
            page = 1;
            return page;
        }
        if (page > total) {
            page = total;
            return page;
        }
        return page;
    }

    public static Integer getStartNum(Integer page, Integer row) {
        return (page - 1) * row;
    }
}
