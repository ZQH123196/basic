package com.example.tree.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zqh
 * @since 2022-11-15
 */
@TableName("tree_node")
public class TreeNode implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("node_name")
    private String nodeName;

    @TableField("path_string")
    private String pathString;

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }
    public String getPathString() {
        return pathString;
    }

    public void setPathString(String pathString) {
        this.pathString = pathString;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
            "nodeName=" + nodeName +
            ", pathString=" + pathString +
        "}";
    }
}
