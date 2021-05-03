package com.lwh147.rtms.backstage.common.pojo.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @description: 基础查询条件类
 * @author: lwh
 * @create: 2021/4/30 10:38
 * @version: v1.0
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BasicQuery {
    /**
     * 当前页号
     **/
    protected Integer pageNum = 1;
    /**
     * 每页数量
     **/
    protected Integer pageSize = 10;

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof BasicQuery)) {
            return false;
        } else {
            BasicQuery other = (BasicQuery) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$pageNum = this.getPageNum();
                Object other$pageNum = other.getPageNum();
                if (this$pageNum == null) {
                    if (other$pageNum != null) {
                        return false;
                    }
                } else if (!this$pageNum.equals(other$pageNum)) {
                    return false;
                }

                Object this$pageSize = this.getPageSize();
                Object other$pageSize = other.getPageSize();
                if (this$pageSize == null) {
                    return other$pageSize == null;
                } else {
                    return this$pageSize.equals(other$pageSize);
                }
            }
        }
    }

    public int hashCode() {
        int result = 1;
        Object $pageNum = this.getPageNum();
        result = result * 59 + ($pageNum == null ? 43 : $pageNum.hashCode());
        Object $pageSize = this.getPageSize();
        result = result * 59 + ($pageSize == null ? 43 : $pageSize.hashCode());
        return result;
    }

    public String toString() {
        return "BasicQuery(pageNum=" + this.getPageNum() + ", pageSize=" + this.getPageSize() + ")";
    }

    protected boolean canEqual(Object other) {
        return other instanceof BasicQuery;
    }
}
