package com.dreamcode.SQUARETRADE.DAOs;

public interface GeneralTree<T> {
    T root();
    boolean isLeaf();
    int height();
    int heightOfNode(String category);
    void addCategory(GeneralTree<T> subTree);
    T search(String category);
    int getNumberOfSubtrees();
}
