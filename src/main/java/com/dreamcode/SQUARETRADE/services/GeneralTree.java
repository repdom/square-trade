package com.dreamcode.SQUARETRADE.services;
import com.dreamcode.SQUARETRADE.models.Category;

import java.util.List;

public interface GeneralTree<T> {
    T root();
    boolean isLeaf();
    int height();
    int heightOfNode(String category);
    void addCategory(GeneralTree<T> subTree);
    T search(String category);
    int getNumberOfSubtrees();
}
